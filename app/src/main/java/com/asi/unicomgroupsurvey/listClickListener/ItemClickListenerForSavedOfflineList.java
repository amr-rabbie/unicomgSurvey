package com.asi.unicomgroupsurvey.listClickListener;

import android.view.View;

import com.asi.unicomgroupsurvey.data.models.Surveys.Survey;

//this interface class for communication between Adapter and View
public interface ItemClickListenerForSavedOfflineList {
    void onItemClickNext(int position, Survey survey, View view);
    void onItemClickSaved(int position, Survey survey, View view);

}
