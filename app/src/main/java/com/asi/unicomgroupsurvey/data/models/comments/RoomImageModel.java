package com.asi.unicomgroupsurvey.data.models.comments;

import com.google.gson.annotations.SerializedName;

public class RoomImageModel {

    @SerializedName("image_id")
    String imageID;
    @SerializedName("room_id")
    String roomID;
    @SerializedName("user_id")
    String user_id;
    @SerializedName("project_id")
    String project_id;
    @SerializedName("office_id")
    String office_id;
    @SerializedName("image_bitmap")
    String imageBitmap;

    public String getImageID() {
        return imageID;
    }

    public void setImageID(String imageID) {
        this.imageID = imageID;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
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

    public String getImageBitmap() {
        return imageBitmap;
    }

    public void setImageBitmap(String imageBitmap) {
        this.imageBitmap = imageBitmap;
    }

    @Override
    public String toString() {
        return "RoomImageModel{" +
                "imageID='" + imageID + '\'' +
                ", roomID='" + roomID + '\'' +
                ", user_id='" + user_id + '\'' +
                ", project_id='" + project_id + '\'' +
                ", office_id='" + office_id + '\'' +
                ", imageBitmap='" + imageBitmap + '\'' +
                '}';
    }
}
