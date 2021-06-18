package com.asi.unicomgroupsurvey.data.models.positions;

import com.google.gson.annotations.SerializedName;

public class PositionResponseDetails {

    @SerializedName("position_id")
    private  String position_id ;


    @SerializedName("project_id")
    private  String project_id ;

    @SerializedName("position_name")
    private  String position_name ;

    public PositionResponseDetails() {
    }

    public PositionResponseDetails(String position_id, String project_id, String position_name) {
        this.position_id = position_id;
        this.project_id = project_id;
        this.position_name = position_name;
    }

    public String getPosition_id() {
        return position_id;
    }

    public void setPosition_id(String position_id) {
        this.position_id = position_id;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getPosition_name() {
        return position_name;
    }

    public void setPosition_name(String position_name) {
        this.position_name = position_name;
    }
}
