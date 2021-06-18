package com.asi.unicomgroupsurvey.listClickListener;

import android.view.View;

import com.asi.unicomgroupsurvey.data.models.out_door.OutDoorImage;
import com.asi.unicomgroupsurvey.data.models.rooms.RoomDetails;


//this interface class for communication between Adapter and View
public interface ItemClickListenerForOutDoorImage {
    void onItemClickDelete(int position, OutDoorImage outDoorImage, View view);

}
