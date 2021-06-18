package com.asi.unicomgroupsurvey.listClickListener;

import android.view.View;

import com.asi.unicomgroupsurvey.data.models.in_door.InDoorImage;
import com.asi.unicomgroupsurvey.data.models.out_door.OutDoorImage;


//this interface class for communication between Adapter and View
public interface ItemClickListenerForInDoorImage {
    void onItemClickDelete(int position, InDoorImage inDoorImage, View view);

}
