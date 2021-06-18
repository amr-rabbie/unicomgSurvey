package com.asi.unicomgroupsurvey.data.models.comments;

import com.asi.unicomgroupsurvey.data.models.rooms.RoomImage;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RoomModel {
    @SerializedName("room_id")
    String room_id ;
    @SerializedName("office_data_id")
    String office_data_id ;
    @SerializedName("room_name")
    String room_name ;
    @SerializedName("room_count")
    String room_count ;
    @SerializedName("furniture")
    String furniture ;
    @SerializedName("room_images")
    List<RoomImageModel> roomImagesList ;
    @SerializedName("is_room_shared")
    String  is_mutual ;



    public RoomModel(String room_id, String office_data_id, String room_name, String room_count, String furniture, List<RoomImageModel> roomImagesList) {
        this.room_id = room_id;
        this.office_data_id = office_data_id;
        this.room_name = room_name;
        this.room_count = room_count;
        this.furniture = furniture;
        this.roomImagesList = roomImagesList;
    }

    public RoomModel() {
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getOffice_data_id() {
        return office_data_id;
    }

    public void setOffice_data_id(String office_data_id) {
        this.office_data_id = office_data_id;
    }

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

    public List<RoomImageModel> getRoomImagesList() {
        return roomImagesList;
    }

    public void setRoomImagesList(List<RoomImageModel> roomImagesList) {
        this.roomImagesList = roomImagesList;
    }

    public String getIs_mutual() {
        return is_mutual;
    }

    public void setIs_mutual(String is_mutual) {
        this.is_mutual = is_mutual;
    }

    @Override
    public String toString() {
        return "RoomModel{" +
                "room_id='" + room_id + '\'' +
                ", office_data_id='" + office_data_id + '\'' +
                ", room_name='" + room_name + '\'' +
                ", room_count='" + room_count + '\'' +
                ", furniture='" + furniture + '\'' +
                ", roomImagesList=" + roomImagesList +
                ", is_mutual='" + is_mutual + '\'' +
                '}';
    }
}
