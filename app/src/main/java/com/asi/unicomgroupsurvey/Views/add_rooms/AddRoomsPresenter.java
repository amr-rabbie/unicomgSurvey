package com.asi.unicomgroupsurvey.Views.add_rooms;

import android.content.Context;

import com.asi.unicomgroupsurvey.Views.get_user_projects.GetUserProjectView;
import com.asi.unicomgroupsurvey.baseClass.BasePresenter;
import com.asi.unicomgroupsurvey.data.ApisClient.ApiInterface;
import com.asi.unicomgroupsurvey.data.LocalSqlite.ItemDbHelper;
import com.asi.unicomgroupsurvey.data.models.get_user_projects.GetUserProjectsResponse;
import com.asi.unicomgroupsurvey.data.models.get_user_projects.UserProjectsDetails;
import com.asi.unicomgroupsurvey.data.models.offices.OfficeResponse;
import com.asi.unicomgroupsurvey.data.models.offices.OfficeResponseDetails;
import com.asi.unicomgroupsurvey.data.models.positions.PositionResponse;
import com.asi.unicomgroupsurvey.data.models.positions.PositionResponseDetails;
import com.asi.unicomgroupsurvey.data.models.rooms.RoomDetails;
import com.asi.unicomgroupsurvey.data.models.rooms.RoomImage;
import com.asi.unicomgroupsurvey.data.models.sync_office_data.Room;
import com.asi.unicomgroupsurvey.di.DaggerApplication;
import com.asi.unicomgroupsurvey.helper.Constants;
import com.asi.unicomgroupsurvey.helper.Utilities;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class AddRoomsPresenter implements BasePresenter<AddRoomsView> {
    AddRoomsView mView;
    boolean isLoaded = false;


    //inject api interface object

    @Inject
    ApiInterface mApiInterface;
    @Inject
    Context mContext;


    // create sqllit reference
    @Inject
    ItemDbHelper mItemDbHelper;
    @Override
    public void onAttach(AddRoomsView view) {
        mView = view;
        mView.onAttache();
    }



    @Override
    public void onDetach() {
        mView = null;
    }

    //create Constructor to get reference of api interface object
    public AddRoomsPresenter(Context context){
        ((DaggerApplication)context).getAppComponent().inject(this);
    }

    //this function created to load items from specific endpoint
    public void saveRoomsOfflinePresenter(String roomName , String roomCount , String roomFurniture , List<RoomImage>  roomImages , String is_mutual) {
        List<RoomImage>  roomImages2 = new ArrayList<>();
        for (RoomImage room: roomImages) {

            roomImages2.add(new RoomImage(Constants.getuserId(mContext) , Constants.getProjectId(mContext) , Constants.getOfficeId(mContext) , room.getImageBitmap() ));

        }
            RoomDetails roomDetails = new RoomDetails();
            roomDetails.setUser_id(Constants.getuserId(mContext));
            roomDetails.setProject_id(Constants.getProjectId(mContext));
            roomDetails.setOffice_id(Constants.getOfficeId(mContext)); // TODO update value from the local shared preference not static hard coded
            roomDetails.setRoomName(roomName);
            roomDetails.setRoomCount(roomCount);
            roomDetails.setRoomFurniture(roomFurniture);
            roomDetails.setRoomImages(roomImages2);
            roomDetails.setIs_mutual(is_mutual);
            saveRooms(roomDetails);



    }



    public  void getUserRooms()
    {
        if(!mItemDbHelper.getUserRooms(Constants.getProjectId(mContext) , Constants.getOfficeId(mContext) ,Constants.getuserId(mContext)).isEmpty()) {

            List<RoomDetails> roomDetails = mItemDbHelper.getUserRooms(Constants.getProjectId(mContext), Constants.getOfficeId(mContext), Constants.getuserId(mContext));
            mView.showRoomsList(roomDetails);
        }


    }


    public void addTmageForRoom(RoomImage roomImage)

    {
        mItemDbHelper.addRoomImage(roomImage) ;
    }

    public  void updateRooms(String roomId , RoomDetails roomDetails )
    {
      boolean  returnFlag =   mItemDbHelper.updateRoom( roomDetails.getRoomName(),roomDetails.getRoomCount() , roomDetails.getRoomFurniture() , roomId , roomDetails.getIs_mutual());

     if(returnFlag == true)
     {
         mView.showHideEditRoomDialog(returnFlag);
         List<RoomDetails> roomDetailsList = mItemDbHelper.getUserRooms(Constants.getProjectId(mContext), Constants.getOfficeId(mContext), Constants.getuserId(mContext));
         mView.showRoomsList(roomDetailsList);
     }

        }



    public  void deleteRooms(String roomId)
    {
        boolean  returnFlag =   mItemDbHelper.deleteRoom(roomId);

        if(returnFlag == true)
        {
            mView.showHideDeleteDialog(returnFlag);
            List<RoomDetails> roomDetailsList = mItemDbHelper.getUserRooms(Constants.getProjectId(mContext), Constants.getOfficeId(mContext), Constants.getuserId(mContext));
            mView.showRoomsList(roomDetailsList);
        }

    }


    private void saveRooms(RoomDetails roomDetails) {
        long flag = mItemDbHelper.addRooms(roomDetails);
        mView.showHideAddRoomDialog(flag);
        getUserRooms();

    }

    void checkConnection(boolean isConnected) {
        //check internet and  data not loaded
        if(isConnected  && !isLoaded){
            isLoaded = false;
         //   mView.showMessage(mContext.getString(R.string.connect_to_internet),Color.GREEN);
        }if(!isConnected && isLoaded){
            //offline check and  data loaded
          //  mView.showMessage(mContext.getString(R.string.offline),Color.WHITE);

        }else if(!isConnected && !isLoaded){
            //get offline  data using realm
            //mView.showMessage(mContext.getString(R.string.get_data_from_local),Color.WHITE);
        }else if(isConnected && isLoaded){
            //mView.showMessage(mContext.getString(R.string.connect_to_internet),Color.GREEN);
        }
    }

    public List<RoomImage> getAllRoomImagesForEditing(String project_id, String office_id, String user_id, String id) {
        return mItemDbHelper.getRoomImages(project_id , office_id , user_id , id);

    }

    public void deleteImageFromRoom(RoomImage roomImage) {
        mItemDbHelper.deleteRoomImage(roomImage.getImageID());
    }

    public boolean isMutualedWithOtherOffice(String projectId , String officeId , String userId ){
        return   mItemDbHelper.gettwoofficesstatus(projectId , officeId , userId);
    }

    public String getOfficeName(String project , String office , String user) {
        return mItemDbHelper.getSecondOfficeName(project , office , user) ;
    }
}
