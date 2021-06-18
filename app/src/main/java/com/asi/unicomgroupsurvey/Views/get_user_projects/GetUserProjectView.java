package com.asi.unicomgroupsurvey.Views.get_user_projects;

import android.database.Cursor;

import com.asi.unicomgroupsurvey.baseClass.BaseView;
import com.asi.unicomgroupsurvey.data.models.get_user_projects.GetUserProjectsResponse;
import com.asi.unicomgroupsurvey.data.models.get_user_projects.UserProjectsDetails;

import java.util.List;


//class created for register function for main view
public interface GetUserProjectView extends BaseView {
    void showLoading();
    void hideLoading();
    void showMessage(String message);
    void getUserProjectsList(GetUserProjectsResponse getUserProjectsResponse);
    void showCursorProject(List<UserProjectsDetails> userProjectsDetails);
}
