package com.asi.unicomgroupsurvey.data.models.rooms;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;

public class RoomImage {

    @SerializedName("imageID")
    private String imageID;
    @SerializedName("roomID")
    private String roomID ;
    @SerializedName("user_id")
    private String user_id ;
    @SerializedName("project_id")
    private String project_id ;
    @SerializedName("office_id")
    private String office_id;
    @SerializedName("imageBitmap")
    private Bitmap imageBitmap;

    private int minimumStatus ;
    public RoomImage() {
        this.minimumStatus = 0 ;
    }

    public RoomImage( String user_id, String project_id, String office_id, Bitmap imageBitmap) {
        this.user_id = user_id;
        this.project_id = project_id;
        this.office_id = office_id;
        this.imageBitmap = imageBitmap;
        this.minimumStatus = 0 ;
    }


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

    public Bitmap getImageBitmap() {
        return imageBitmap;
    }

    public void setImageBitmap(Bitmap imageBitmap) {
        this.imageBitmap = imageBitmap;
    }

    public int getMinimumStatus() {
        return minimumStatus;
    }

    public void setMinimumStatus(int minimumStatus) {
        this.minimumStatus = minimumStatus;
    }

    @Override
    public String toString() {
        return "RoomImage{" +
                "imageID='" + imageID + '\'' +
                ", roomID='" + roomID + '\'' +
                ", user_id='" + user_id + '\'' +
                ", project_id='" + project_id + '\'' +
                ", office_id='" + office_id + '\'' +
                ", imageBitmap=" + imageBitmap +
                ", minimumStatus=" + minimumStatus +
                '}';
    }
}
