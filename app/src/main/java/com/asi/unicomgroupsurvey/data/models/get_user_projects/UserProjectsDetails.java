package com.asi.unicomgroupsurvey.data.models.get_user_projects;

import com.google.gson.annotations.SerializedName;

public class UserProjectsDetails {

    @SerializedName("id")
    private  String id ;

    @SerializedName("pivot")
    Pivot pivot ;

    @SerializedName("project_name")
    private  String project_name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }
}
