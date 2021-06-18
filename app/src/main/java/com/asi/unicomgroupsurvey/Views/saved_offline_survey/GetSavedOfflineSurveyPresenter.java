package com.asi.unicomgroupsurvey.Views.saved_offline_survey;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.asi.unicomgroupsurvey.baseClass.BasePresenter;
import com.asi.unicomgroupsurvey.data.ApisClient.ApiInterface;
import com.asi.unicomgroupsurvey.data.LocalSqlite.ItemDbHelper;
import com.asi.unicomgroupsurvey.data.models.FlagResponse;
import com.asi.unicomgroupsurvey.data.models.Surveys.Survey;
import com.asi.unicomgroupsurvey.data.models.get_Notes.Notes;
import com.asi.unicomgroupsurvey.data.models.in_door.InDoorImage;
import com.asi.unicomgroupsurvey.data.models.jops.Job;
import com.asi.unicomgroupsurvey.data.models.out_door.OutDoorImage;
import com.asi.unicomgroupsurvey.data.models.rooms.RoomDetails;
import com.asi.unicomgroupsurvey.data.models.sketch.Sketch;
import com.asi.unicomgroupsurvey.data.models.sync_office_data.InDoorDetails;
import com.asi.unicomgroupsurvey.data.models.sync_office_data.OutDoorDetails;
import com.asi.unicomgroupsurvey.data.models.sync_office_data.SketchDetails;
import com.asi.unicomgroupsurvey.data.models.sync_office_data.SyncOfficeDataRequest;
import com.asi.unicomgroupsurvey.data.models.sync_office_data.SyncOfflineDataResponse;
import com.asi.unicomgroupsurvey.di.DaggerApplication;
import com.asi.unicomgroupsurvey.helper.Constants;
import com.asi.unicomgroupsurvey.helper.Utilities;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class GetSavedOfflineSurveyPresenter implements BasePresenter<GetOfflineSavedSurveyView> {
    GetOfflineSavedSurveyView mView;
    boolean isLoaded = false;


    //inject api interface object

    @Inject
    ApiInterface mApiInterface;
    @Inject
    Context mContext;


    // create sqllit reference
    @Inject
    ItemDbHelper mItemDbHelper;

    boolean status = false;
    boolean saveRooms = false;
    boolean saveSketches = false;
    boolean saveOutDoorImages = false;
    boolean saveIndoorImages = false;


    @Override
    public void onAttach(GetOfflineSavedSurveyView view) {
        mView = view;
        mView.onAttache();
    }


    @Override
    public void onDetach() {
        mView = null;
    }

    //create Constructor to get reference of api interface object
    public GetSavedOfflineSurveyPresenter(Context context) {
        ((DaggerApplication) context).getAppComponent().inject(this);
    }



    // TODO From Survey Table
    public void getUserSurveys() {

        List<Survey> surveys = mItemDbHelper.getSurveyForUser(Constants.getuserId(mContext) , Constants.getProjectId(mContext));
        mView.showOfflineSavedSurvey(surveys);
    }

    public void getUserOfflineSurveyData(String officeId , Survey survey) {

        List<Job> jobs = mItemDbHelper.getJobsByUserId(Constants.getuserId(mContext) , officeId);
        List<RoomDetails> rooms = mItemDbHelper.getUserRoomsByUserIdSending(Constants.getuserId(mContext), officeId);
        List<Sketch> sketches = mItemDbHelper.getSketchOfficeByUserIdSending(Constants.getuserId(mContext) , officeId);
        List<OutDoorImage> outDoors = mItemDbHelper.getOutDoorOfficeByUserIdSending(Constants.getuserId(mContext) , officeId);
        List<InDoorImage> inDoors = mItemDbHelper.getInDoorOfficeByUserIdSending(Constants.getuserId(mContext) , officeId);
        List<Notes> notes = mItemDbHelper.getNotesByUserid(Constants.getuserId(mContext) , officeId);
        mView.showOfflineSavedSurveyAllData(survey ,jobs , rooms,sketches , outDoors , inDoors , notes);

    }



    public void syncOfflineDataPresenter(final String officeId , final SyncOfficeDataRequest syncOfficeDataRequest, final List<com.asi.unicomgroupsurvey.data.models.sync_office_data.RoomDetails> roomList,
                                         final List<SketchDetails> listSketches, final List<OutDoorDetails> listOutDoors, final List<InDoorDetails> listInDoors) {

        final boolean[] bigObjectAdded = {false};

        if(!Utilities.checkConnection(mContext)){
            checkConnection(false);

            mView.showMessage("من فضلك قم بالاتصال بالانترنت");
            mView.hideLoading();
            return;
        }

        mView.showLoading();
        mApiInterface.syncOfflineDataObservable("Bearer "+Constants.getuserToken(mContext) , syncOfficeDataRequest )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SyncOfflineDataResponse>() {
                    @Override
                    public final void onCompleted() {
                        if(bigObjectAdded[0])
                        saveRooms(officeId  , roomList, listSketches, listOutDoors, listInDoors);

                    }

                    @Override
                    public final void onError(Throwable e) {
                        mView.showMessage("من فضلك حاول مرة أخري");
                        mView.hideLoading();
                        Log.e("big object", e.getMessage());
                        bigObjectAdded[0] = false ;

                    }

                    @Override
                    public final void onNext(SyncOfflineDataResponse response) {

                        if (response.getSuccess().equals("success")) {
                            bigObjectAdded[0] = true ;
                        } else {
                            bigObjectAdded[0] = false ;
                            mView.hideLoading();
                            mView.showMessage("من فضلك حاول مرة أخري");
                        }
                        Log.e("Sr_info_ big Success:", response.getSuccess().toString());
                        Log.e("Sr_info_ big object:", response.getMessage().toString());
                    }
                });
    }


    public void saveRooms(final String officeId, final List<com.asi.unicomgroupsurvey.data.models.sync_office_data.RoomDetails> roomList,
                          final List<SketchDetails> listSketches, final List<OutDoorDetails> listOutDoors, final List<InDoorDetails> listInDoors) {
        final int[] counter = {0};
        if (roomList != null && roomList.size() != 0) {

            for (final com.asi.unicomgroupsurvey.data.models.sync_office_data.RoomDetails roomDetails : roomList) {
                //    Log.e(">>>>", roomDetails.toString());
                if (!Utilities.checkConnection(mContext)) {
                    checkConnection(false);

                    mView.showMessage("من فضلك قم بالاتصال بالانترنت");
                    return;
                }

                mView.showLoading();
                mApiInterface.saveRoom("Bearer " + Constants.getuserToken(mContext), roomDetails)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<SyncOfflineDataResponse>() {
                            @Override
                            public final void onCompleted() {
                                counter[0]++;
                                if (/*roomList.indexOf(roomDetails) == roomList.size() - 1 &&*/ counter[0] == roomList.size()) {
                                    if (saveRooms)
                                        saveSketches(officeId, listSketches, listOutDoors, listInDoors);
                                }
                                else {
                                    // Toast.makeText(mContext, "خطا في ارسال الغرف ", Toast.LENGTH_SHORT).show();
                                }

                            }

                            @Override
                            public final void onError(Throwable e) {
                                //mView.showMessage("فشل في ارسال بيانات الغرفه رقم " + counter[0] + "من فضلك حاول مره اخري ");
                                mView.hideLoading();
                                Log.e("saveRooms", e.getMessage());
                                // Toast.makeText(mContext, "saveRooms" + e.getMessage(), Toast.LENGTH_LONG).show();
                                saveRooms = false;

                            }

                            @Override
                            public final void onNext(SyncOfflineDataResponse response) {
                                if (response.getSuccess().equals("success")) {
                                    saveRooms = true;
                                    Log.e("saveRoomsstatus1", saveRooms + "");

                                } else {
                                    saveRooms = false;
                                    mView.hideLoading();
                                    mView.showMessage("من فضلك حاول مرة أخري");
                                }

                                Log.e("Sr_info_ save_room:", response.getMessage().toString());
                            }
                        });
            }
        } else {
            saveRooms = true;
            saveSketches(officeId, listSketches, listOutDoors, listInDoors);
        }


        Log.e("saveRoomsstatus", saveRooms + "");


    }

    public void saveSketches(final String officeId, final List<com.asi.unicomgroupsurvey.data.models.sync_office_data.SketchDetails> sketchDetails,
                             final List<OutDoorDetails> listOutDoors, final List<InDoorDetails> inDoorDetailslist) {
        final int[] counter = {0};
        if (sketchDetails != null && sketchDetails.size() != 0) {
            for (final com.asi.unicomgroupsurvey.data.models.sync_office_data.SketchDetails sketchDetails1 : sketchDetails) {
                if (!Utilities.checkConnection(mContext)) {
                    checkConnection(false);

                    mView.showMessage("من فضلك قم بالاتصال بالانترنت");
                    return;
                }

                mView.showLoading();
                mApiInterface.saveSketch("Bearer " + Constants.getuserToken(mContext), sketchDetails1)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<SyncOfflineDataResponse>() {
                            @Override
                            public final void onCompleted() {

                                counter[0]++;
                                if (/*sketchDetails.indexOf(sketchDetails1) == sketchDetails.size() - 1 && */counter[0] == sketchDetails.size()) {
                                    if (saveSketches)
                                        saveOutDoorImages(officeId, listOutDoors, inDoorDetailslist);
                                } else {
                                    // Toast.makeText(mContext, "خطا في ارسال صور الرسم التوضيحي ", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public final void onError(Throwable e) {
                                //mView.showMessage("فشل في ارسال صوره الرسم التوضيحي رقم " + counter[0] + "من فضلك حاول مره اخري ");
                                mView.hideLoading();

                                Log.e("saveSketches", e.getMessage());
                                // Toast.makeText(mContext, "saveSketches" + e.getMessage(), Toast.LENGTH_LONG).show();
                                saveSketches = false;

                            }

                            @Override
                            public final void onNext(SyncOfflineDataResponse response) {
                                if (response.getSuccess().equals("success")) {
                                    saveSketches = true;
                                } else {
                                    saveSketches = false;
                                    mView.hideLoading();
                                    mView.showMessage("من فضلك حاول مرة أخري");
                                }

                                Log.e("Sr_info_ save_sketches:", response.getMessage().toString());
                            }
                        });
            }

        } else {
            saveSketches = true;
            saveOutDoorImages(officeId, listOutDoors, inDoorDetailslist);
        }


    }

    private void saveOutDoorImages(final String officeId, final List<OutDoorDetails> listOutDoors, final List<InDoorDetails> indoorList) {

        final int[] counter = {0};

        if (listOutDoors != null && listOutDoors.size() != 0) {
            for (final com.asi.unicomgroupsurvey.data.models.sync_office_data.OutDoorDetails outDoorDetails : listOutDoors) {
                if (!Utilities.checkConnection(mContext)) {
                    checkConnection(false);

                    mView.showMessage("من فضلك قم بالاتصال بالانترنت");
                    return;
                }

                mView.showLoading();
                mApiInterface.saveOutDoor("Bearer " + Constants.getuserToken(mContext), outDoorDetails)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<SyncOfflineDataResponse>() {
                            @Override
                            public final void onCompleted() {

                                counter[0]++;
                                if (/*listOutDoors.indexOf(outDoorDetails) == listOutDoors.size() - 1 &&*/ counter[0] == listOutDoors.size()) {
                                    if (saveOutDoorImages)
                                        saveInDoorImages(officeId, indoorList);
                                } else {
                                    //  Toast.makeText(mContext, "خطاء في رسال صور المبني من الخارج ", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public final void onError(Throwable e) {
                                //mView.showMessage("فشل في ارسال الصوره الخارجيه رقم " + counter[0] + "من فضلك حاول مره اخري ");
                                mView.hideLoading();
                                Log.e("saveOutDoorImages", e.getMessage());
                                // Toast.makeText(mContext, "saveOutDoorImages" + e.getMessage(), Toast.LENGTH_LONG).show();
                                saveOutDoorImages = false;
                            }

                            @Override
                            public final void onNext(SyncOfflineDataResponse response) {
                                if (response.getSuccess().equals("success")) {
                                    saveOutDoorImages = true;
                                } else {
                                    saveOutDoorImages = false;
                                    mView.hideLoading();
                                    mView.showMessage("من فضلك حاول مرة أخري");
                                }
                                Log.e("Sr_info_ save_out_door:", response.getMessage().toString());
                            }
                        });
            }

        } else {
            saveOutDoorImages = true;
            saveInDoorImages(officeId, indoorList);
        }


    }


    public void saveInDoorImages(final String officeId  , final List<InDoorDetails> indoorimaheslist) {
        final int[] counter = {0};
        if (indoorimaheslist != null &&  indoorimaheslist.size() != 0) {
            for (final com.asi.unicomgroupsurvey.data.models.sync_office_data.InDoorDetails inDoorDetails1 : indoorimaheslist) {
                if (!Utilities.checkConnection(mContext)) {
                    checkConnection(false);

                    mView.showMessage("من فضلك قم بالاتصال بالانترنت");
                    return;
                }

                mView.showLoading();
                mApiInterface.saveIndoor("Bearer " + Constants.getuserToken(mContext), inDoorDetails1)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<SyncOfflineDataResponse>() {
                            @Override
                            public final void onCompleted() {

                                counter[0]++;
                                if (/*indoorimaheslist.indexOf(inDoorDetails1) == indoorimaheslist.size() - 1 && */counter[0] == indoorimaheslist.size()) {
                                    if (status) {
                                        is_done(officeId);
                                    }
                                    else {
                                        Toast.makeText(mContext, "لم يتم ارسال الباينات بالكامل حاول مره اخري ", Toast.LENGTH_SHORT).show();
                                        getUserSurveys();
                                        mView.hideLoading();
                                    }
                                }
                                else {
                                    // Toast.makeText(mContext, "خطا في ارسال صور المبني من الداخل", Toast.LENGTH_SHORT).show();
                                }

                            }

                            @Override
                            public final void onError(Throwable e) {
                                //mView.showMessage("فشل في ارسال الصوره الداخليه رقم " + counter[0] + "من فضلك حاول مره اخري ");
                                mView.hideLoading();

                                Log.e("saveInDoorImages", e.getMessage());
                                Toast.makeText(mContext, "saveInDoorImages" + e.getMessage(), Toast.LENGTH_LONG).show();
                                saveIndoorImages = false;
                                status = false;
                            }

                            @Override
                            public final void onNext(SyncOfflineDataResponse response) {
                                if (response.getSuccess().equals("success")) {
                                    saveIndoorImages = true;
                                    status = true;

                                } else {
                                    saveIndoorImages = false;
                                    status = false;
                                    mView.hideLoading();
                                    mView.showMessage("من فضلك حاول مرة أخري");
                                }

                                Log.e("Sr_info_ save_in_door:", response.getMessage().toString());
                            }
                        });
            }
        }
        else {

            is_done(officeId);
        }


    }


    int isDoneCounter = 0;

    public void is_done(final String officeId) {
        isDoneCounter++;
        mApiInterface.is_done("Bearer " + Constants.getuserToken(mContext), officeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<FlagResponse>() {
                    @Override
                    public final void onCompleted() {

                    }

                    @Override
                    public final void onError(Throwable e) {
                        Log.e("is_done", e.getMessage());
                        if (isDoneCounter < 3) {
                            is_done(officeId);
                        } else {
                            mView.hideLoading();
                            mView.showMessage("من فضلك حاول مرة أخري");
                        }

                    }

                    @Override
                    public final void onNext(FlagResponse response) {

                        Log.e("is_done", response.getMessage());
                        if (response.getSuccess().equals("success")) {
                            mItemDbHelper.deleteUploadedSurvey(Constants.getuserId(mContext), Constants.getProjectId(mContext), officeId);
                            mItemDbHelper.deleteUploadedJobs(Constants.getuserId(mContext), Constants.getProjectId(mContext), officeId);
                            mItemDbHelper.deleteUploadedRooms(Constants.getuserId(mContext), Constants.getProjectId(mContext), officeId);
                            mItemDbHelper.deleteUploadedSketch(Constants.getuserId(mContext), Constants.getProjectId(mContext), officeId);
                            mItemDbHelper.deleteUploadedOutDoors(Constants.getuserId(mContext), Constants.getProjectId(mContext), officeId);
                            mItemDbHelper.deleteUploadedInDoors(Constants.getuserId(mContext), Constants.getProjectId(mContext), officeId);
                            mItemDbHelper.deleteUploadedNotes(Constants.getuserId(mContext), Constants.getProjectId(mContext), officeId);
                            mItemDbHelper.deleteUploadedRoomImages(Constants.getuserId(mContext), Constants.getProjectId(mContext), officeId);
                            mView.hideLoading();
                            mView.showMessage("تم ارسال البيانات بالكامل ");
                            getUserSurveys();
                        } else {

                            if (isDoneCounter < 3) {
                                is_done(officeId);
                            } else {
                                mView.hideLoading();
                                mView.showMessage("من فضلك حاول مرة أخري");
                            }

                        }

                    }
                });

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

    public void showProgress(){
        mView.showLoading();

    }


    public void hideProgress(){
        mView.hideLoading();

    }
}
