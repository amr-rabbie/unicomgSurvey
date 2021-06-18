package com.asi.unicomgroupsurvey.Views.get_user_projects;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;

import com.asi.unicomgroupsurvey.baseClass.BasePresenter;
import com.asi.unicomgroupsurvey.data.ApisClient.ApiInterface;
import com.asi.unicomgroupsurvey.data.LocalSqlite.ItemDbHelper;
import com.asi.unicomgroupsurvey.data.models.GetAllItemsResponse;
import com.asi.unicomgroupsurvey.data.models.Item;
import com.asi.unicomgroupsurvey.data.models.getCities.City;
import com.asi.unicomgroupsurvey.data.models.get_user_projects.GetUserProjectsResponse;
import com.asi.unicomgroupsurvey.data.models.get_user_projects.UserProjectsDetails;
import com.asi.unicomgroupsurvey.data.models.offices.OfficeResponse;
import com.asi.unicomgroupsurvey.data.models.offices.OfficeResponseDetails;
import com.asi.unicomgroupsurvey.data.models.positions.PositionResponse;
import com.asi.unicomgroupsurvey.data.models.positions.PositionResponseDetails;
import com.asi.unicomgroupsurvey.di.DaggerApplication;
import com.asi.unicomgroupsurvey.helper.Constants;
import com.asi.unicomgroupsurvey.helper.Utilities;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class GetUserProjectsPresenter implements BasePresenter<GetUserProjectView> {
    GetUserProjectView mView;
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
    public void onAttach(GetUserProjectView view) {
        mView = view;
        mView.onAttache();
    }



    @Override
    public void onDetach() {
        mView = null;
    }

    //create Constructor to get reference of api interface object
    public GetUserProjectsPresenter(Context context){
        ((DaggerApplication)context).getAppComponent().inject(this);
    }

    //this function created to load items from specific endpoint
    public void getUserProjectsPresenter() {
        if(!Utilities.checkConnection(mContext)){
           // checkConnection(false);

         List<UserProjectsDetails> userProjectsDetails= mItemDbHelper.getUserProjects(Constants.getuserId(mContext));
          mView.showCursorProject(userProjectsDetails);
            return;
        }

        mView.showLoading();
         mApiInterface.getUserProjects("Bearer "+Constants.getuserToken(mContext) , Constants.getuserId(mContext))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetUserProjectsResponse>() {
                    @Override
                    public final void onCompleted() {

                        mView.hideLoading();

                    }

                    @Override
                    public final void onError(Throwable e) {
                        mView.hideLoading();
                        Log.e(">>>>>>" , "Error in get projects " + e.getMessage()) ;
                        Toast.makeText(mContext, "جوده الانترنت سيئه ", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public final void onNext(GetUserProjectsResponse response) {
                        mView.getUserProjectsList(response);

                        if(!mItemDbHelper.getProjects().isEmpty())
                            mItemDbHelper.deleteSavedProjectsTable();

                        saveProjects(response.getUserProjectsDetailsList());




               }
                });
    }







    //TODO Save positions
    public void getPositions()
    {
        mApiInterface.getPositions()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PositionResponse>() {
                    @Override
                    public final void onCompleted() {


                        getOffices();
                    }

                    @Override
                    public final void onError(Throwable e) {
                        mView.hideLoading();
                        Log.e(">>>>>>" , "Error in get postitions " + e.getMessage()) ;
                    }

                    @Override
                    public final void onNext(PositionResponse response) {


                        if(!mItemDbHelper.getPositions().isEmpty())
                            mItemDbHelper.deleteSavedPositionTable();

                        savePositions(response.getPositionResponseDetails());
                    }
                });

    }

    // TODO save offices
    private void getOffices()
    {
        mApiInterface.getOffices()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<OfficeResponse>() {
                    @Override
                    public final void onCompleted() {

                        getUserProjectsPresenter();
                    }

                    @Override
                    public final void onError(Throwable e) {
                        mView.hideLoading();
                        Log.e(">>>>>>" , "Error in get offices " + e.getMessage()) ;
                    }

                    @Override
                    public final void onNext(OfficeResponse response) {


                        if(!mItemDbHelper.getOffices().isEmpty())
                            mItemDbHelper.deleteSavedOfficesTable();

                        saveOffices(response.getOfficeResponseDetails());
                    }
                });

    }


    private void saveProjects(List<UserProjectsDetails> userProjectsDetails) {
        mItemDbHelper.addProjects(userProjectsDetails , Constants.getuserId(mContext));
    }


    private void savePositions(List<PositionResponseDetails> positionResponses) {
        mItemDbHelper.addPositions(positionResponses);
    }

    private void saveOffices(List<OfficeResponseDetails> officeResponseDetails) {
        mItemDbHelper.addOffices(officeResponseDetails);
    }

    //store Realm data

    void checkConnection(boolean isConnected) {
        //check internet and  data not loaded
        if(isConnected  && !isLoaded){
            isLoaded = false;
         //   mView.showMessage(mContext.getString(R.string.connect_to_internet),Color.GREEN);
        }if(!isConnected && isLoaded){
            //offline check and  data loaded
          //  mView.showMessage(mContext.getString(R.string.offline),Color.WHITE);

        }else if(!isConnected && !isLoaded){
            //get offline  data using realm
            //mView.showMessage(mContext.getString(R.string.get_data_from_local),Color.WHITE);
        }else if(isConnected && isLoaded){
            //mView.showMessage(mContext.getString(R.string.connect_to_internet),Color.GREEN);
        }
    }



}
