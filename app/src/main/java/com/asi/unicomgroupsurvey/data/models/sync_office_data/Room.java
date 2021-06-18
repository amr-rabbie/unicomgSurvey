package com.asi.unicomgroupsurvey.data.models.sync_office_data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Room {
    @SerializedName("rooms")
    private List<RoomDetails> rooms;

    public List<RoomDetails> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomDetails> rooms) {
        this.rooms = rooms;
    }

    @Override
    public String toString() {
        return "Room{" +
                "rooms=" + rooms +
                '}';
    }
}
