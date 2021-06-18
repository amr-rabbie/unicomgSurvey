package com.asi.unicomgroupsurvey.listClickListener;

import android.view.View;

import com.asi.unicomgroupsurvey.data.models.in_door.InDoorImage;
import com.asi.unicomgroupsurvey.data.models.rooms.RoomImage;

public interface RoomItemClick {

    void onItemClickDelete(int position, RoomImage roomImage, View view);
}
