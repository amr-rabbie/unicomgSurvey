package com.asi.unicomgroupsurvey.Views.add_rooms;

import com.asi.unicomgroupsurvey.baseClass.BaseView;
import com.asi.unicomgroupsurvey.data.models.get_user_projects.GetUserProjectsResponse;
import com.asi.unicomgroupsurvey.data.models.get_user_projects.UserProjectsDetails;
import com.asi.unicomgroupsurvey.data.models.rooms.RoomDetails;

import java.util.List;


//class created for register function for main view
public interface AddRoomsView extends BaseView {
    void showLoading();
    void hideLoading();
    void showHideAddRoomDialog(Long flag);
    void showHideEditRoomDialog(boolean flag);
    void showHideDeleteDialog(boolean flag);

    void showRoomsList(List<RoomDetails> roomDetails);
}
