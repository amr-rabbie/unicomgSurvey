package com.asi.unicomgroupsurvey.data.models.sync_office_data;

import com.google.gson.annotations.SerializedName;

public class RoomImageDetails {
    @SerializedName("image_id")
    String image_id;
    @SerializedName("room_id")
    String room_id;
    @SerializedName("user_id")
    String user_id;
    @SerializedName("project_id")
    String project_id;
    @SerializedName("office_id")
    String office_id;
    @SerializedName("image_bitmap")
    String image_bitmap;

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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

    public String getImage_bitmap() {
        return image_bitmap;
    }

    public void setImage_bitmap(String image_bitmap) {
        this.image_bitmap = image_bitmap;
    }

    @Override
    public String toString() {
        return "RoomImageDetails{" +
                "image_id='" + image_id + '\'' +
                ", room_id='" + room_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", project_id='" + project_id + '\'' +
                ", office_id='" + office_id + '\'' +
                ", image_bitmap='" + image_bitmap + '\'' +
                '}';
    }
}
