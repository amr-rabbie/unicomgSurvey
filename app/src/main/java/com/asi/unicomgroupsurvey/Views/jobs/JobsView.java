package com.asi.unicomgroupsurvey.Views.jobs;

import com.asi.unicomgroupsurvey.baseClass.BaseView;
import com.asi.unicomgroupsurvey.data.models.jops.Job;
import com.asi.unicomgroupsurvey.data.models.positions.PositionResponseDetails;

import java.util.ArrayList;

public interface JobsView extends BaseView {

    void showProgress();
    void hideProgress();
    void startRoomActivity();
    void afterDelete(ArrayList<Job> jobs);
    void afterUpdate(ArrayList<Job> jobs);
    void showMessage(String message, int mColor);
    void getJobs(ArrayList<Job> jobs);
    void afterAdd(ArrayList<Job> jobs);
    void getPostionsNames(ArrayList<PositionResponseDetails> jobs);
}
