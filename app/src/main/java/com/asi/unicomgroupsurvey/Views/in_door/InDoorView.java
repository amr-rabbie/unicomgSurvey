package com.asi.unicomgroupsurvey.Views.in_door;

import com.asi.unicomgroupsurvey.baseClass.BaseView;
import com.asi.unicomgroupsurvey.data.models.in_door.InDoorImage;
import com.asi.unicomgroupsurvey.data.models.out_door.OutDoorImage;

import java.util.List;


//class created for register function for main view
public interface InDoorView extends BaseView {
    void showLoading();
    void hideLoading();

    void showInDoorImages(List<InDoorImage> inDoorImages);
}
