package com.asi.unicomgroupsurvey.listClickListener;

import android.view.View;

import com.asi.unicomgroupsurvey.data.models.in_door.InDoorImage;
import com.asi.unicomgroupsurvey.data.models.sketch.Sketch;

public interface ItemClickListenerForSketch {
    void onItemClickDelete(int position, Sketch inDoorImage, View view);

}
