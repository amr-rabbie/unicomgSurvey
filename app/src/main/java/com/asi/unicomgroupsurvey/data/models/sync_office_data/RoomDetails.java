package com.asi.unicomgroupsurvey.data.models.sync_office_data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by belal on 1/17/19.
 */

public class RoomDetails {

    @SerializedName("office_id")
    private String office_id;

    @SerializedName("room_name")
    private String room_name;


    @SerializedName("room_count")
    private String room_count;


    @SerializedName("furniture")
    private String furniture;

    @SerializedName("roomImages")
    private List<RoomImageDetails> roomImages;

    @SerializedName("is_mutual")
    private String is_mutual;

    public String getRoom_name() {

        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public String getRoom_count() {
        return room_count;
    }

    public void setRoom_count(String room_count) {
        this.room_count = room_count;
    }

    public String getFurniture() {
        return furniture;
    }

    public void setFurniture(String furniture) {
        this.furniture = furniture;
    }

    public List<RoomImageDetails> getRoomImages() {
        return roomImages;
    }

    public void setRoomImages(List<RoomImageDetails> roomImages) {
        this.roomImages = roomImages;
    }

    public String getIs_mutual() {
        return is_mutual;
    }

    public void setIs_mutual(String is_mutual) {
        this.is_mutual = is_mutual;
    }

    public String getOffice_id() {
        return office_id;
    }

    public void setOffice_id(String office_id) {
        this.office_id = office_id;
    }

    @Override
    public String toString() {
        return "RoomDetails{" +
                "office_id='" + office_id + '\'' +
                ", room_name='" + room_name + '\'' +
                ", room_count='" + room_count + '\'' +
                ", furniture='" + furniture + '\'' +
                ", roomImages=" + roomImages +
                ", is_mutual='" + is_mutual + '\'' +
                '}';
    }
}
