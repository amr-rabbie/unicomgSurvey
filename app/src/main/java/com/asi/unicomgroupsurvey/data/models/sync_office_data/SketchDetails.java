package com.asi.unicomgroupsurvey.data.models.sync_office_data;

import com.google.gson.annotations.SerializedName;

public class SketchDetails {

    @SerializedName("image")
    private String image ;
    @SerializedName("img_desc")
    private String img_desc;

    @SerializedName("project_id")
    private String project_id;

    @SerializedName("office_id")
    private String office_id;

    @SerializedName("user_id")
    private String user_id;

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

    @Override
    public String toString() {
        return "SketchDetails{" +
                "image='" + image + '\'' +
                ", img_desc='" + img_desc + '\'' +
                ", project_id='" + project_id + '\'' +
                ", office_id='" + office_id + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }
}
