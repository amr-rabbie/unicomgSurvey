package com.asi.unicomgroupsurvey.Views.jobs;

import android.content.Context;

import com.asi.unicomgroupsurvey.baseClass.BasePresenter;
import com.asi.unicomgroupsurvey.data.ApisClient.ApiInterface;
import com.asi.unicomgroupsurvey.data.LocalSqlite.ItemDbHelper;
import com.asi.unicomgroupsurvey.data.models.getGovernors.Governor;
import com.asi.unicomgroupsurvey.data.models.jops.Job;
import com.asi.unicomgroupsurvey.di.DaggerApplication;
import com.asi.unicomgroupsurvey.helper.Constants;

import java.util.ArrayList;

import javax.inject.Inject;

public class JobsPresenter implements BasePresenter<JobsView> {
    JobsView mView;
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
    public void onAttach(JobsView view) {
        mView = view;
        mView.onAttache();
    }

    @Override
    public void onDetach() {
        mView = null;
    }

    //create Constructor to get reference of api interface object
    public JobsPresenter(Context context) {
        ((DaggerApplication) context).getAppComponent().inject(this);
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


    public void getJobs(String project_id, String office_id, String user_id) {
        mView.showProgress();
        mView.getJobs( mItemDbHelper.getJobs(project_id, office_id, user_id));
        mView.hideProgress();
    }

    public void getPositions(String project_id) {
          mView.getPostionsNames(  mItemDbHelper.getCertainPosition(project_id));
    }

    public boolean isFounded(String jobID , String officeID , String projectId ,String userId ) {
        return mItemDbHelper.getACertainJob(jobID,  officeID ,  projectId , userId);
    }

    public void addJob(Job job){
        mItemDbHelper.addJob(job);
        mView.afterAdd( mItemDbHelper.getJobs(job.getProject_id() , job.getOffice_id() , job.getUser_id()));

    }


    public boolean updateJob(String jobId,Job job) {
        boolean ss = mItemDbHelper.updateJob( jobId , job);
        if(ss)
        mView.afterUpdate( mItemDbHelper.getJobs(job.getProject_id() , job.getOffice_id() , job.getUser_id()));
        return ss;
    }
    public boolean deleteJob(Job job) {
        boolean ss = mItemDbHelper.deleteJob(job);
        mView.afterDelete( mItemDbHelper.getJobs(job.getProject_id() , job.getOffice_id() , job.getUser_id()));
        return ss;
    }

    public Boolean getstatus(){
        return mItemDbHelper.gettwoofficesstatus(Constants.getuserId(mContext) , Constants.getProjectId( mContext) , Constants.getOfficeId(mContext));
    }
}
