package com.asi.unicomgroupsurvey.data.models.comments;

import com.google.gson.annotations.SerializedName;

public class PositionModel {
    @SerializedName("position_name")
    String position_name ;
    @SerializedName("position_id")
    String position_id ;
    @SerializedName("project_id")
    String project_id ;

    public PositionModel(String position_name, String position_id) {
        this.position_name = position_name;
        this.position_id = position_id;
    }

    public PositionModel() {
    }

    public String getPosition_name() {
        return position_name;
    }

    public void setPosition_name(String position_name) {
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

    @Override
    public String toString() {
        return "PositionModel{" +
                "position_name='" + position_name + '\'' +
                ", position_id='" + position_id + '\'' +
                ", project_id='" + project_id + '\'' +
                '}';
    }
}
