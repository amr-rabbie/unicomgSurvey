package com.asi.unicomgroupsurvey.data.models.get_user_projects;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetUserProjectsResponse {

    @SerializedName("user_projects")
    private List<UserProjectsDetails> userProjectsDetailsList;

    public List<UserProjectsDetails> getUserProjectsDetailsList() {
        return userProjectsDetailsList;
    }

    public void setUserProjectsDetailsList(List<UserProjectsDetails> userProjectsDetailsList) {
        this.userProjectsDetailsList = userProjectsDetailsList;
    }
}
