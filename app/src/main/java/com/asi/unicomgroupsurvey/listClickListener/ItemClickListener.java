package com.asi.unicomgroupsurvey.listClickListener;

import android.view.View;

import com.asi.unicomgroupsurvey.data.models.get_user_projects.UserProjectsDetails;


//this interface class for communication between Adapter and View
public interface ItemClickListener {
    void onItemClick(int position,  UserProjectsDetails getUserProjectsDetails, View view);

}
