package com.asi.unicomgroupsurvey.data.models.rooms;

import java.util.List;

public class RoomDetails {
    private String _ID ;
    private String user_id ;
    private String project_id ;
    private String office_id;
    private String roomName ;
    private String roomCount;
    private String roomFurniture;
    private List<RoomImage> roomImages ;
    private String is_mutual;
    public RoomDetails() {
    }

    public RoomDetails( String user_id, String project_id,
                       String office_id, String roomName, String roomCount, String roomFurniture,  List<RoomImage> roomImages , String is_mutual) {

        this.user_id = user_id;
        this.project_id = project_id;
        this.office_id = office_id;
        this.roomName = roomName;
        this.roomCount = roomCount;
        this.roomFurniture = roomFurniture;
        this.roomImages = roomImages;
        this.is_mutual = is_mutual;
    }

    public String get_ID() {
        return _ID;
    }

    public void set_ID(String _ID) {
        this._ID = _ID;
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

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(String roomCount) {
        this.roomCount = roomCount;
    }

    public String getRoomFurniture() {
        return roomFurniture;
    }

    public void setRoomFurniture(String roomFurniture) {
        this.roomFurniture = roomFurniture;
    }

    public List<RoomImage> getRoomImages() {
        return roomImages;
    }

    public void setRoomImages(List<RoomImage> roomImages) {
        this.roomImages = roomImages;
    }

    public String getIs_mutual() {
        return is_mutual;
    }

    public void setIs_mutual(String is_mutual) {
        this.is_mutual = is_mutual;
    }
}
