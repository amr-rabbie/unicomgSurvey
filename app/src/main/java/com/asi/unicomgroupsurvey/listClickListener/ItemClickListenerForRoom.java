package com.asi.unicomgroupsurvey.listClickListener;

import android.view.View;

import com.asi.unicomgroupsurvey.data.models.rooms.RoomDetails;


//this interface class for communication between Adapter and View
public interface ItemClickListenerForRoom {
    void onItemClick(int position, RoomDetails roomDetails, View view);
    void onItemClickDelete(int position, RoomDetails roomDetails, View view);

}
