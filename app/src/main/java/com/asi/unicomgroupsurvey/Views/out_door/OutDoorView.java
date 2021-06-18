package com.asi.unicomgroupsurvey.Views.out_door;

import com.asi.unicomgroupsurvey.baseClass.BaseView;
import com.asi.unicomgroupsurvey.data.models.out_door.OutDoorImage;
import com.asi.unicomgroupsurvey.data.models.rooms.RoomDetails;

import java.util.List;


//class created for register function for main view
public interface OutDoorView extends BaseView {
    void showLoading();
    void hideLoading();

    void showOutDoorImages(List<OutDoorImage> outDoorImages);
}
