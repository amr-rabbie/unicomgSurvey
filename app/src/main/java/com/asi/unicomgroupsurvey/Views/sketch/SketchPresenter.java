package com.asi.unicomgroupsurvey.Views.sketch;

import android.content.Context;
import com.asi.unicomgroupsurvey.baseClass.BasePresenter;
import com.asi.unicomgroupsurvey.data.ApisClient.ApiInterface;
import com.asi.unicomgroupsurvey.data.LocalSqlite.ItemDbHelper;
import com.asi.unicomgroupsurvey.data.models.getGovernors.Governor;
import com.asi.unicomgroupsurvey.data.models.out_door.OutDoorImage;
import com.asi.unicomgroupsurvey.data.models.sketch.Sketch;
import com.asi.unicomgroupsurvey.di.DaggerApplication;
import com.asi.unicomgroupsurvey.helper.Constants;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class SketchPresenter implements BasePresenter<SketchView> {

    SketchView mView;
    boolean isLoaded = false;
    //inject api interface object
    @Inject
    ApiInterface mApiInterface;
    @Inject
    Context mContext;
    // create sqllit reference
    @Inject
    ItemDbHelper mItemDbHelper;

    @Override
    public void onAttach(SketchView view) {
        mView = view;
        mView.onAttache();
    }

    @Override
    public void onDetach() {
        mView = null;
    }

    //create Constructor to get reference of api interface object
    public SketchPresenter(Context context) {
        ((DaggerApplication)context).getAppComponent().inject(this);
    }

    private void saveGovernors(ArrayList<Governor> governors) {
        mItemDbHelper.addGovernors(governors);
    }

    void checkConnection(boolean isConnected) {
        //check internet and  data not loaded
        if (isConnected && !isLoaded) {
            // loadGovernor();
            isLoaded = false;
            //   mView.showMessage(mContext.getString(R.string.connect_to_internet),Color.GREEN);
        }
        if (!isConnected && isLoaded) {
            //offline check and  data loaded
            //  mView.showMessage(mContext.getString(R.string.offline),Color.WHITE);

        } else if (!isConnected && !isLoaded) {
            //get offline  data using realm
            //mView.showMessage(mContext.getString(R.string.get_data_from_local),Color.WHITE);
            //     mView.updateList(mItemDbHelper.getAllItems());
        } else if (isConnected && isLoaded) {
            //mView.showMessage(mContext.getString(R.string.connect_to_internet),Color.GREEN);
        }
    }

    public void saveSketch(Sketch sketch) {
        mItemDbHelper.addSketch(sketch);
        mView.afterAdd(getSketch(sketch.getProject_id() , sketch.getOffice_id() , sketch.getUser_id() ));
    }

    public void deleteSketch(String off , String user , String pro , String sketchId) {
        mItemDbHelper.deletSketch(off , user , pro ,sketchId );
        mView.afterDelete(getSketch(pro, off , user ));
    }

    public List<Sketch> getSketch(String projectId , String officeId , String userId){
       return mItemDbHelper.getSketchOffice(projectId,officeId,userId);
    }


}

