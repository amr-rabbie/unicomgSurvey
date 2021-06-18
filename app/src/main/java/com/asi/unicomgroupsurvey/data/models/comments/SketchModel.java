package com.asi.unicomgroupsurvey.data.models.comments;

import com.google.gson.annotations.SerializedName;

public class SketchModel {


    @SerializedName("image")
    String image;
    @SerializedName("img_desc")
    String img_desc;

    @SerializedName("project_id")
    String project_id ;
    @SerializedName("office_id")
    String office_id ;
    @SerializedName("user_id")
    String user_id ;
    @SerializedName("sketches_id")
    String sketches_id ;
    @SerializedName("office_data_id")
    String office_data_id ;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImg_desc() {
        return img_desc;
    }

    public void setImg_desc(String img_desc) {
        this.img_desc = img_desc;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getOffice_id() {
        return office_id;
    }

    public void setOffice_id(String office_id) {
        this.office_id = office_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getSketches_id() {
        return sketches_id;
    }

    public void setSketches_id(String sketches_id) {
        this.sketches_id = sketches_id;
    }

    public String getOffice_data_id() {
        return office_data_id;
    }

    public void setOffice_data_id(String office_data_id) {
        this.office_data_id = office_data_id;
    }

    @Override
    public String toString() {
        return "SketchModel{" +
                "image='" + image + '\'' +
                ", img_desc='" + img_desc + '\'' +
                ", project_id='" + project_id + '\'' +
                ", office_id='" + office_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", sketches_id='" + sketches_id + '\'' +
                ", office_data_id='" + office_data_id + '\'' +
                '}';
    }
}
