package com.asi.unicomgroupsurvey.Views.saved_offline_survey;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.asi.unicomgroupsurvey.R;
import com.asi.unicomgroupsurvey.Views.Survey.UpdateSurveyActivity;
import com.asi.unicomgroupsurvey.Views.Survey.UpdateSurveyFragment;
import com.asi.unicomgroupsurvey.Views.comments.CommentsActivity;
import com.asi.unicomgroupsurvey.Views.get_user_projects.GetUserProjectsActivity;
import com.asi.unicomgroupsurvey.Views.login.LoginActivity;
import com.asi.unicomgroupsurvey.Views.navigation_home.NavigationHomeActivity;
import com.asi.unicomgroupsurvey.data.models.Surveys.Survey;
import com.asi.unicomgroupsurvey.data.models.get_Notes.Notes;
import com.asi.unicomgroupsurvey.data.models.in_door.InDoorImage;
import com.asi.unicomgroupsurvey.data.models.jops.Job;
import com.asi.unicomgroupsurvey.data.models.out_door.OutDoorImage;
import com.asi.unicomgroupsurvey.data.models.rooms.RoomDetails;
import com.asi.unicomgroupsurvey.data.models.rooms.RoomImage;
import com.asi.unicomgroupsurvey.data.models.sketch.Sketch;
import com.asi.unicomgroupsurvey.data.models.sync_office_data.InDoorDetails;
import com.asi.unicomgroupsurvey.data.models.sync_office_data.OutDoorDetails;
import com.asi.unicomgroupsurvey.data.models.sync_office_data.Room;
import com.asi.unicomgroupsurvey.data.models.sync_office_data.RoomImageDetails;
import com.asi.unicomgroupsurvey.data.models.sync_office_data.SketchDetails;
import com.asi.unicomgroupsurvey.data.models.sync_office_data.SyncOfficeDataRequest;
import com.asi.unicomgroupsurvey.di.DaggerApplication;
import com.asi.unicomgroupsurvey.helper.Constants;
import com.asi.unicomgroupsurvey.listClickListener.ItemClickListenerForSavedOfflineList;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

public class GetOfflineSavedSurveyActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener ,
        GetOfflineSavedSurveyView , ItemClickListenerForSavedOfflineList {



    private RecyclerView recyclerViewSurveys;

    @Inject
    GetSavedOfflineSurveyPresenter getSavedOfflineSurveyPresenter ;

    private GetSavedOfflineSurveyAdapter getSavedOfflineSurveyAdapter ;

    private ProgressBar progressBarUploadDataOffline ;

    private TextView txtNoSavedList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_show_saved_survey);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        TextView titleBar = findViewById(R.id.text_title_bar);
        titleBar.setText(" عرض البيانات المسجلة");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        recyclerViewSurveys = findViewById( R.id.recycle_view_offline_saved_survey);

        txtNoSavedList = findViewById(R.id.text_no_saved_list);

        progressBarUploadDataOffline = findViewById(R.id.progress_upload_data_offline);

        ((DaggerApplication) getApplication()).getAppComponent().inject(this);
         getSavedOfflineSurveyPresenter.onAttach(this);
        

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(GetOfflineSavedSurveyActivity.this);
        recyclerViewSurveys.setLayoutManager(linearLayoutManager);

        try {
            getSavedOfflineSurveyPresenter.getUserSurveys();
        }
        catch (Exception e)
        {
            Toast.makeText(GetOfflineSavedSurveyActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onItemClickNext(int position, Survey survey, View view) {

        // TODO Send userId , send project_id , send office_id

        Constants.saveUpdatedOfficeId(survey.getOffice_id() , GetOfflineSavedSurveyActivity.this);
        startActivity(new Intent(GetOfflineSavedSurveyActivity.this , UpdateSurveyActivity.class));
        //attachUpdateSurveyFragment();
    }

    private void attachUpdateSurveyFragment()
    {
        UpdateSurveyFragment updateSurveyFragment = new UpdateSurveyFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction().addToBackStack("update_survey_fragment");
        ft.replace(R.id.frameLayout_container , updateSurveyFragment)
                .commit();
    }



    @Override
    public void onItemClickSaved(int position, Survey survey, View view) {
        getSavedOfflineSurveyPresenter.getUserOfflineSurveyData(survey.getOffice_id(), survey);
        //GetSavedOfflineSurveyAdapter getSavedOfflineSurveyAdapter = new GetSavedOfflineSurveyAdapter(GetOfflineSavedSurveyActivity.this, surveys, this);
        //recyclerViewSurveys.setAdapter(getSavedOfflineSurveyAdapter);
    }


    List<Survey> surveys = new ArrayList<>()  ;

    @Override
    public void showMessage(String message) {
        Toast.makeText(GetOfflineSavedSurveyActivity.this, ""+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        progressBarUploadDataOffline.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {

        progressBarUploadDataOffline.setVisibility(View.GONE);
    }

    @Override
    public File getSketchImage() {
        return fileSketch;
    }

    @Override
    public void showOfflineSavedSurvey(List<Survey> surveys) {

          if(surveys.size() != 0)
          {
              txtNoSavedList.setVisibility(View.GONE);
              recyclerViewSurveys.setVisibility(View.VISIBLE);
              GetSavedOfflineSurveyAdapter getSavedOfflineSurveyAdapter = new GetSavedOfflineSurveyAdapter(GetOfflineSavedSurveyActivity.this, surveys, this);
              recyclerViewSurveys.setAdapter(getSavedOfflineSurveyAdapter);

              this.surveys = surveys;
          }
          else {
              txtNoSavedList.setVisibility(View.VISIBLE);
              recyclerViewSurveys.setVisibility(View.GONE);


          }

    }


    File fileSketch ;
    @Override
    public void showOfflineSavedSurveyAllData(Survey survey , List<Job> jobs, List<RoomDetails> rooms, List<Sketch> sketches,
                                              List<OutDoorImage> outDoorImages, List<InDoorImage> inDoorImages, List<Notes> notes) {
        getSavedOfflineSurveyPresenter.showProgress();
        try {
            SyncOfficeDataRequest  syncOfficeDataRequest = new SyncOfficeDataRequest();

            syncOfficeDataRequest.setUser_id(survey.getUser_id());
            syncOfficeDataRequest.setOffice_id(survey.getOffice_id());
            syncOfficeDataRequest.setPhone(survey.getPhone());


            syncOfficeDataRequest.setCity_name(survey.getCity_name());
            syncOfficeDataRequest.setDistrict_name(survey.getDistrict_name());

            if (notes.size() != 0) {

                String location[] = notes.get(notes.size()-1).getLocation().split("--");
                syncOfficeDataRequest.setLatitude(location[0]);
                syncOfficeDataRequest.setLongitude(location[1]);
                syncOfficeDataRequest.setVisit_date(notes.get(notes.size() - 1).getVisit_date());
                syncOfficeDataRequest.setGeneral_notes(notes.get(notes.size()-1).getNotes());

                syncOfficeDataRequest.setElectricty(notes.get(notes.size()-1).getElectricty());
                syncOfficeDataRequest.setWater(notes.get(notes.size()-1).getWater());
                syncOfficeDataRequest.setBill_end(notes.get(notes.size()-1).getBill_end());
                syncOfficeDataRequest.setColunms(notes.get(notes.size()-1).getColunms());
                syncOfficeDataRequest.setDoors(notes.get(notes.size()-1).getDoors());
                syncOfficeDataRequest.setSecondary(notes.get(notes.size()-1).getSecondary());
                syncOfficeDataRequest.setCamersnet(notes.get(notes.size()-1).getCamersnet());
            } else {
                syncOfficeDataRequest.setVisit_date("1970-01-01");
                syncOfficeDataRequest.setLatitude("0.0");
                syncOfficeDataRequest.setLongitude("0.0");
            }

            syncOfficeDataRequest.setIs_network(survey.getIsNetwork());
            syncOfficeDataRequest.setIs_internet(survey.getHasInternet());
            syncOfficeDataRequest.setShift_type(survey.getShiftType());



            syncOfficeDataRequest.setOwnership_type(survey.getOwnerShipType());
            syncOfficeDataRequest.setComputer_count(survey.getComputer_count());
            syncOfficeDataRequest.setPrinters_count(survey.getPrinters_count());
            syncOfficeDataRequest.setScanners_count(survey.getScanners_count());

            syncOfficeDataRequest.setInternet_speed(survey.getInternetSeed());
            syncOfficeDataRequest.setComputer_notes(survey.getComputer_notes());
            syncOfficeDataRequest.setScanners_notes(survey.getScanners_notes());
            syncOfficeDataRequest.setPrinters_notes(survey.getPrinters_notes());


            syncOfficeDataRequest.setNet_inside(survey.getNet_inside());
            syncOfficeDataRequest.setNet_outside(survey.getNet_outside());
            syncOfficeDataRequest.setIns_out_notes(survey.getIns_out_notes());
            syncOfficeDataRequest.setOthers_macs(survey.getOthers_macs());
            syncOfficeDataRequest.setOthers_macs_notes(survey.getOthers_macs_notes());
            syncOfficeDataRequest.setTwo_offices(survey.getTwo_offices());
            syncOfficeDataRequest.setOther_office_id(survey.getOther_office_id());
            syncOfficeDataRequest.setOther_office_name(survey.getOther_office_name());

            syncOfficeDataRequest.setIs_arabic(survey.getIs_arabic());
            syncOfficeDataRequest.setIs_kurdish(survey.getIs_kurdish());
            syncOfficeDataRequest.setBoth(survey.getBoth());


            syncOfficeDataRequest.setAddress(survey.getAddress());


            List<com.asi.unicomgroupsurvey.data.models.sync_office_data.RoomDetails> roomList = new ArrayList<>();
            List<com.asi.unicomgroupsurvey.data.models.sync_office_data.Job> jobArrayList = new ArrayList<>();


            for (int i = 0 ; i < rooms.size() ; i++){
                com.asi.unicomgroupsurvey.data.models.sync_office_data.RoomDetails room = new com.asi.unicomgroupsurvey.data.models.sync_office_data.RoomDetails();
                room.setRoom_name(rooms.get(i).getRoomName());
                room.setRoom_count(rooms.get(i).getRoomCount());
                room.setFurniture(rooms.get(i).getRoomFurniture());
                room.setRoomImages(getRommImages(rooms.get(i).getRoomImages()));
                room.setIs_mutual(rooms.get(i).getIs_mutual());
                room.setOffice_id(survey.getOffice_id());
                roomList.add(room);

            }


            Room room1 = new Room();
            room1.setRooms(roomList);


            for (int i = 0 ; i < jobs.size() ; i++){
                com.asi.unicomgroupsurvey.data.models.sync_office_data.Job job = new com.asi.unicomgroupsurvey.data.models.sync_office_data.Job();
                job.setPosition_id(jobs.get(i).getJob_id());
                job.setCount(jobs.get(i).getCount());
                job.setNote(jobs.get(i).getNote());

                jobArrayList.add(job);
            }

/*

        Bitmap bitmapSketch = null;
        String encoded = "" ;
        if(sketches.size() != 0) {
             bitmapSketch = sketches.get(0).getSketch_image();
        }
        if(bitmapSketch != null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmapSketch.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);

        }
*/

            List<SketchDetails> listSketches = new ArrayList<>();
            Bitmap bitmapSketchDetails;
            ByteArrayOutputStream byteArrayOutputStreamSketches;
            byte[] byteArraySketches ;
            String encodedSketch ;
            for(int i = 0 ; i < sketches.size() ; i++)
            {
                SketchDetails sketchDetails = new SketchDetails();
                bitmapSketchDetails =  sketches.get(i).getSketch_image();
                byteArrayOutputStreamSketches = new ByteArrayOutputStream();
                bitmapSketchDetails.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStreamSketches);
                byteArraySketches = byteArrayOutputStreamSketches.toByteArray();
                encodedSketch = Base64.encodeToString(byteArraySketches, Base64.DEFAULT);
                sketchDetails.setImage(encodedSketch);
                sketchDetails.setImg_desc(sketches.get(i).getSketch_image_Desc());

                sketchDetails.setProject_id(survey.getProject_id());
                sketchDetails.setOffice_id(survey.getOffice_id());
                sketchDetails.setUser_id(survey.getUser_id());
                listSketches.add(sketchDetails);
            }



            List<InDoorDetails> listInDoors = new ArrayList<>();
            Bitmap bitmapInDoors;
            ByteArrayOutputStream byteArrayOutputStreamInDoors;
            byte[] byteArrayInDoors ;
            String encodedInDoor ;
            for(int i = 0 ; i < inDoorImages.size() ; i++)
            {
                InDoorDetails inDoorDetails = new InDoorDetails();
                bitmapInDoors =  inDoorImages.get(i).getInDoorImageBitmap();
                byteArrayOutputStreamInDoors = new ByteArrayOutputStream();
                bitmapInDoors.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStreamInDoors);
                byteArrayInDoors = byteArrayOutputStreamInDoors.toByteArray();
                encodedInDoor = Base64.encodeToString(byteArrayInDoors, Base64.DEFAULT);
                inDoorDetails.setImage(encodedInDoor);
                inDoorDetails.setImg_desc(inDoorImages.get(i).getIndoorImageDesc());
                inDoorDetails.setOffice_id(survey.getOffice_id());
                listInDoors.add(inDoorDetails);
            }


            // TODO ImagesOutDoor
            List<OutDoorDetails> listOutDoors = new ArrayList<>();
            Bitmap bitmapOutDoors;
            ByteArrayOutputStream byteArrayOutputStreamOutDoors;
            byte[] byteArrayOutDoors ;
            String encodedOutDoor ;
            for(int i = 0 ; i < outDoorImages.size() ; i++)
            {
                OutDoorDetails outDoorDetails = new OutDoorDetails();

                bitmapOutDoors =  outDoorImages.get(i).getOutDoorImageBitmap();
                byteArrayOutputStreamOutDoors = new ByteArrayOutputStream();
                bitmapOutDoors.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStreamOutDoors);
                byteArrayOutDoors = byteArrayOutputStreamOutDoors .toByteArray();
                encodedOutDoor = Base64.encodeToString(byteArrayOutDoors, Base64.DEFAULT);
                outDoorDetails.setImage(encodedOutDoor);
                outDoorDetails.setImg_desc(outDoorImages.get(i).getOutDoorImageDesc());
                outDoorDetails.setOffice_id(survey.getOffice_id());
                listOutDoors.add(outDoorDetails);
            }


            syncOfficeDataRequest.setMorning_shift_from(survey.getMorning_shift_from());
            syncOfficeDataRequest.setMorning_shift_to(survey.getMorning_shift_to());
            syncOfficeDataRequest.setEvening_shift_from(survey.getEvening_shift_from());
            syncOfficeDataRequest.setEvening_shift_to(survey.getEvening_shift_to());

//        syncOfficeDataRequest.setRooms(roomList);
            syncOfficeDataRequest.setPositions(jobArrayList);
//        syncOfficeDataRequest.setSketches(listSketches);
//        syncOfficeDataRequest.setInDoorImages(listInDoors);
//        syncOfficeDataRequest.setOutDoorImages(listOutDoors);


            getSavedOfflineSurveyPresenter.syncOfflineDataPresenter(survey.getOffice_id(), syncOfficeDataRequest, roomList, listSketches, listOutDoors, listInDoors);


        } catch (OutOfMemoryError outOfMemoryError) {
            getSavedOfflineSurveyPresenter.hideProgress();
            Toast.makeText(this, "من فضلك اعد المحاوله ", Toast.LENGTH_SHORT).show();
        }


    }


    @Override
    public void reSendTheDataAnotherTry() {
       // getSavedOfflineSurveyPresenter.syncOfflineDataPresenter(syncOfficeDataRequest1);

    }

    @Override
    public void onAttache() {

    }

    @Override
    public void onDetach() {

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
            startActivity(new Intent(GetOfflineSavedSurveyActivity.this , GetUserProjectsActivity.class));
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.show_saved_data_list) {
            Intent i=new Intent(GetOfflineSavedSurveyActivity.this,GetOfflineSavedSurveyActivity.class);
            startActivity(i);
        } else if (id == R.id.add_new_survey) {

            Intent intent = new Intent(GetOfflineSavedSurveyActivity.this , NavigationHomeActivity.class);
            intent.putExtra("project_name" , Constants.getProjectName(GetOfflineSavedSurveyActivity.this));
            startActivity(intent);

        } else if (id == R.id.nav_change_project) {
            startActivity(new Intent(this, GetUserProjectsActivity.class));
        } else if (id == R.id.nav_comment) {
            startActivity(new Intent(this, CommentsActivity.class).setFlags(FLAG_ACTIVITY_CLEAR_TOP));
        } else if (id == R.id.nav_logout) {
            android.app.AlertDialog.Builder builder;
            builder = new android.app.AlertDialog.Builder(GetOfflineSavedSurveyActivity.this);
            builder.setMessage("هل حقا تريد تسجيل الخروج ؟");
            builder.setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Constants.saveLoginData("", "", "", GetOfflineSavedSurveyActivity.this);
                    Intent i = new Intent(GetOfflineSavedSurveyActivity.this, LoginActivity.class);
                    startActivity(i);
                    dialog.dismiss();
                }
            });
            builder.setNegativeButton("لا", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            builder.show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public List<RoomImageDetails> getRommImages(List<RoomImage> roomImages){

        List<RoomImageDetails> roomImageDetails = new ArrayList<>();
        Bitmap bitmapRoomImageDetails;
        ByteArrayOutputStream byteArrayOutputStreamRoomImages;
        byte[] byteArrayRoomImages ;
        String encodedRommImage ;
        for(int i = 0 ; i < roomImages.size() ; i++)
        {
            RoomImageDetails roomImageDetails1 = new RoomImageDetails();
            bitmapRoomImageDetails =  roomImages.get(i).getImageBitmap();
            byteArrayOutputStreamRoomImages = new ByteArrayOutputStream();
            bitmapRoomImageDetails.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStreamRoomImages);
            byteArrayRoomImages = byteArrayOutputStreamRoomImages.toByteArray();
            encodedRommImage = Base64.encodeToString(byteArrayRoomImages, Base64.DEFAULT);
            roomImageDetails1.setImage_bitmap(encodedRommImage);
            roomImageDetails1.setImage_id(roomImages.get(i).getImageID());
            roomImageDetails1.setOffice_id(roomImages.get(i).getOffice_id());
            roomImageDetails1.setProject_id(roomImages.get(i).getProject_id());
            roomImageDetails1.setUser_id(roomImages.get(i).getUser_id());
            roomImageDetails1.setRoom_id(roomImages.get(i).getRoomID());
            roomImageDetails.add(roomImageDetails1);
        }
        return roomImageDetails ;
    }
}
