package com.asi.unicomgroupsurvey.Views.out_door;

import android.content.Context;

import com.asi.unicomgroupsurvey.Views.add_rooms.AddRoomsView;
import com.asi.unicomgroupsurvey.baseClass.BasePresenter;
import com.asi.unicomgroupsurvey.data.ApisClient.ApiInterface;
import com.asi.unicomgroupsurvey.data.LocalSqlite.ItemDbHelper;
import com.asi.unicomgroupsurvey.data.models.out_door.OutDoorImage;
import com.asi.unicomgroupsurvey.data.models.rooms.RoomDetails;
import com.asi.unicomgroupsurvey.di.DaggerApplication;
import com.asi.unicomgroupsurvey.helper.Constants;

import java.util.List;

import javax.inject.Inject;


public class OutDoorPresenter implements BasePresenter<OutDoorView> {
    OutDoorView mView;
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
    public void onAttach(OutDoorView view) {
        mView = view;
        mView.onAttache();
    }



    @Override
    public void onDetach() {
        mView = null;
    }

    //create Constructor to get reference of api interface object
    public OutDoorPresenter(Context context){
        ((DaggerApplication)context).getAppComponent().inject(this);
    }

    //this function created to load items from specific endpoint
    public void saveOutDoorOfflinePresenter(OutDoorImage outDoorImage) {
             OutDoorImage outDoorImage1 = new OutDoorImage();
             outDoorImage1.setOutDoorUserId(Constants.getuserId(mContext));
             outDoorImage1.setOutDoorProjectId(Constants.getProjectId(mContext));
             outDoorImage1.setOutDoorOfficeId(Constants.getOfficeId(mContext)); // TODO update value from the local shared preference not static hard coded
             outDoorImage1.setOutDoorImageBitmap(outDoorImage.getOutDoorImageBitmap());
             outDoorImage1.setOutDoorImageDesc(outDoorImage.getOutDoorImageDesc());
             saveOutDoorImage(outDoorImage1);



    }



    public  void getOutDoorImages()
    {
        if(!mItemDbHelper.getOutDoorOffice(Constants.getProjectId(mContext) , Constants.getOfficeId(mContext) ,Constants.getuserId(mContext)).isEmpty()) {

            List<OutDoorImage> outDoorImages = mItemDbHelper.getOutDoorOffice(Constants.getProjectId(mContext), Constants.getOfficeId(mContext), Constants.getuserId(mContext));
            mView.showOutDoorImages(outDoorImages);
        }
    }

    public List<OutDoorImage> getNimberOfOutDoorImages(){
       return  mItemDbHelper.getOutDoorOffice(Constants.getProjectId(mContext), Constants.getOfficeId(mContext), Constants.getuserId(mContext));
    }

    public  void deleteOutDoorImage(String outDoorId)
    {
        boolean  returnFlag =   mItemDbHelper.deleteOutDoorImage(outDoorId);

        if(returnFlag == true)
        {
            List<OutDoorImage> outDoorImagesList = mItemDbHelper.getOutDoorOffice(Constants.getProjectId(mContext), Constants.getOfficeId(mContext), Constants.getuserId(mContext));
            mView.showOutDoorImages(outDoorImagesList);
        }

    }

/*
    public  void getUserRooms()
    {
        if(!mItemDbHelper.getUserRooms(Constants.getProjectId(mContext) , Constants.getOfficeId(mContext) ,Constants.getuserId(mContext)).isEmpty()) {

            List<RoomDetails> roomDetails = mItemDbHelper.getUserRooms(Constants.getProjectId(mContext), Constants.getOfficeId(mContext), Constants.getuserId(mContext));
            mView.showRoomsList(roomDetails);
        }


    }

    public  void updateRooms(String roomId , RoomDetails roomDetails)
    {
      boolean  returnFlag =   mItemDbHelper.updateRoom( roomDetails.getRoomName(),roomDetails.getRoomCount() , roomDetails.getRoomFurniture() , roomId);

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


*/

    private void saveOutDoorImage(OutDoorImage outDoorImage) {
        long flag = mItemDbHelper.addOutDoorImage(outDoorImage);
        //mView.showHideAddRoomDialog(flag);
       // getUserRooms();
        getOutDoorImages();

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



}
