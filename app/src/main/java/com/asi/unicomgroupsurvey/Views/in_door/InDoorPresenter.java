package com.asi.unicomgroupsurvey.Views.in_door;

import android.content.Context;

import com.asi.unicomgroupsurvey.Views.out_door.OutDoorView;
import com.asi.unicomgroupsurvey.baseClass.BasePresenter;
import com.asi.unicomgroupsurvey.data.ApisClient.ApiInterface;
import com.asi.unicomgroupsurvey.data.LocalSqlite.ItemDbHelper;
import com.asi.unicomgroupsurvey.data.models.in_door.InDoorImage;
import com.asi.unicomgroupsurvey.data.models.out_door.OutDoorImage;
import com.asi.unicomgroupsurvey.di.DaggerApplication;
import com.asi.unicomgroupsurvey.helper.Constants;

import java.util.List;

import javax.inject.Inject;


public class InDoorPresenter implements BasePresenter<InDoorView> {
    InDoorView mView;
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
    public void onAttach(InDoorView view) {
        mView = view;
        mView.onAttache();
    }



    @Override
    public void onDetach() {
        mView = null;
    }

    //create Constructor to get reference of api interface object
    public InDoorPresenter(Context context){
        ((DaggerApplication)context).getAppComponent().inject(this);
    }

    //this function created to load items from specific endpoint
    public void saveInDoorOfflinePresenter(InDoorImage inDoorImage) {
             InDoorImage inDoorImage1 = new InDoorImage();
        inDoorImage1.setInDoorUserId(Constants.getuserId(mContext));
        inDoorImage1.setInDoorProjectId(Constants.getProjectId(mContext));
        inDoorImage1.setInDoorOfficeId(Constants.getOfficeId(mContext)); // TODO update value from the local shared preference not static hard coded
        inDoorImage1.setInDoorImageBitmap(inDoorImage.getInDoorImageBitmap());
        inDoorImage1.setIndoorImageDesc(inDoorImage.getIndoorImageDesc());
             saveInDoorImage(inDoorImage1);



    }



    public  void getInDoorImages()
    {
        if(!mItemDbHelper.getInDoorOffice(Constants.getProjectId(mContext) , Constants.getOfficeId(mContext) ,Constants.getuserId(mContext)).isEmpty()) {

            List<InDoorImage> inDoorImages = mItemDbHelper.getInDoorOffice(Constants.getProjectId(mContext), Constants.getOfficeId(mContext), Constants.getuserId(mContext));
            mView.showInDoorImages(inDoorImages);
        }
    }

    public  void deleteOutDoorImage(String outDoorId)
    {
        boolean  returnFlag =   mItemDbHelper.deleteInDoorImage(outDoorId);

        if(returnFlag == true)
        {
            List<InDoorImage> inDoorImages = mItemDbHelper.getInDoorOffice(Constants.getProjectId(mContext), Constants.getOfficeId(mContext), Constants.getuserId(mContext));
            mView.showInDoorImages(inDoorImages);
        }

    }

    public List<InDoorImage> getIndoorImages(){
       return mItemDbHelper.getInDoorOffice(Constants.getProjectId(mContext), Constants.getOfficeId(mContext), Constants.getuserId(mContext));
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

    private void saveInDoorImage(InDoorImage inDoorImage) {
        long flag = mItemDbHelper.addInDoorImage(inDoorImage);
        //mView.showHideAddRoomDialog(flag);
       // getUserRooms();
        getInDoorImages();

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
