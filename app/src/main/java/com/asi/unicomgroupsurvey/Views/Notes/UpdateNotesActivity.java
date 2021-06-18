package com.asi.unicomgroupsurvey.Views.Notes;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.asi.unicomgroupsurvey.R;
import com.asi.unicomgroupsurvey.Views.Sucess.SendingDataSucessActivity;
import com.asi.unicomgroupsurvey.Views.comments.CommentsActivity;
import com.asi.unicomgroupsurvey.Views.get_user_projects.GetUserProjectsActivity;
import com.asi.unicomgroupsurvey.Views.login.LoginActivity;
import com.asi.unicomgroupsurvey.Views.navigation_home.NavigationHomeActivity;
import com.asi.unicomgroupsurvey.Views.saved_offline_survey.GetOfflineSavedSurveyActivity;
import com.asi.unicomgroupsurvey.data.models.get_Notes.Notes;
import com.asi.unicomgroupsurvey.di.DaggerApplication;
import com.asi.unicomgroupsurvey.helper.Constants;
import com.asi.unicomgroupsurvey.helper.GpsTracker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UpdateNotesActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {

    @Inject
    NotesPresenter notesPresenter;



        @BindView(R.id.edt_notes)
        EditText edt_notes;
        @BindView(R.id.tvVisitDate)
        TextView tvVisitDate;
        @BindView(R.id.tvLatLang)
        TextView tvLatLang;
        private GpsTracker gpsTracker;
        private double latitude;
        private double longitude;
        @BindView(R.id.btnSendData)
        Button btnSendData;
    String formattedDate ;

    @BindView(R.id.chk_electricty)
    CheckBox chk_electricty;
    @BindView(R.id.chk_water)
    CheckBox chk_water;
    @BindView(R.id.chk_bill_end)
    CheckBox chk_bill_end;
    @BindView(R.id.chk_colunms)
    CheckBox chk_colunms;
    @BindView(R.id.chk_doors)
    CheckBox chk_doors;
    @BindView(R.id.chk_secondary)
    CheckBox chk_secondary;
    @BindView(R.id.chk_net_cams)
    CheckBox chk_net_cams;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        ((DaggerApplication)getApplication()).getAppComponent().inject(this);
        ButterKnife.bind(this);
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());
        //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
          formattedDate = df.format(c.getTime());
        // formattedDate have current date/time
        tvVisitDate.setText(formattedDate);
        tvLatLang.setText(String.valueOf(latitude) + " -- " + String.valueOf(longitude));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        TextView titleBar =findViewById(R.id.text_title_bar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        titleBar.setText("حفظ المعاينه");

      Notes notes =  notesPresenter.getNotesForUpdate();

      if(notes != null)
      {
        edt_notes.setText(""+notes.getNotes());
        tvLatLang.setText(""+notes.getLocation());
      }
        //getLocation();




    }

    private void getData2() {
        String userid=Constants.getuserId(UpdateNotesActivity.this);
        String officeid=Constants.getOfficeId(UpdateNotesActivity.this);
        String projectid=Constants.getProjectId(UpdateNotesActivity.this);


        List<Notes> notesoffline=notesPresenter.getdataByUseridOfficeidProjectid(userid ,officeid ,projectid);

        for(int i=0; i < notesoffline.size() ; i++){
            Notes notes = notesoffline.get(i);
            Toast.makeText(UpdateNotesActivity.this, notes.getNotes() + " - " + notes.getLocation(), Toast.LENGTH_SHORT).show();
        }
    }

    private void getData() {
        List<Notes> notesoffline=notesPresenter.getdata();
        for(int i=0; i < notesoffline.size() ; i++){
            Notes notes = notesoffline.get(i);
            Toast.makeText(UpdateNotesActivity.this, notes.getNotes() + " - " + notes.getLocation(), Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            UpdateNotesActivity.super.onBackPressed();

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //getLocation();

        /*final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                SharedPreferences prefs = getPreferences(MODE_PRIVATE);
                String restoredText = prefs.getString("latitude", null);
                if (restoredText != null)
                {


                    latitude = Double.parseDouble(Constants.getlat(NotesActivity.this));
                    longitude = Double.parseDouble(Constants.getlang(NotesActivity.this));
                }

                tvLatLang.setText(String.valueOf(latitude) + " -- " + String.valueOf(longitude));
            }
        }, 4000);*/

        final ProgressDialog pd = new ProgressDialog(UpdateNotesActivity.this);
        pd.setMessage("loading");
        pd.show();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                gpsTracker = new GpsTracker(UpdateNotesActivity.this);
                if (gpsTracker.canGetLocation()) {
                    latitude = gpsTracker.getLatitude();
                    longitude = gpsTracker.getLongitude();

                    /*SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
                    editor.putString("latitude", String.valueOf(latitude));
                    editor.putString("longitude", String.valueOf(longitude));
                    editor.apply();*/

                    String lang= String.valueOf(longitude);
                    String lat= String.valueOf(latitude);

                    //Constants.savelanglat(lang,lat,UpdateNotesActivity.this);

                    Notes notes =  notesPresenter.getNotesForUpdate();

                    if(notes != null) {

                        if(notes.getNotes() != null) {
                            edt_notes.setText(notes.getNotes() + "");
                        }else{
                            edt_notes.setText("");
                        }

                        tvLatLang.setText("" + notes.getLocation());

                        String electricty = notes.getElectricty() + "";
                        if (electricty.equals("1")) {
                            chk_electricty.setChecked(true);
                        } else {
                            chk_electricty.setChecked(false);
                        }

                        String water = notes.getWater() + "";
                        if (water.equals("1")) {
                            chk_water.setChecked(true);
                        } else {
                            chk_water.setChecked(false);
                        }

                        String bill_end = notes.getBill_end() + "";
                        if (bill_end.equals("1")) {
                            chk_bill_end.setChecked(true);
                        } else {
                            chk_bill_end.setChecked(false);
                        }

                        String doors = notes.getDoors() + "";
                        if (doors.equals("1")) {
                            chk_doors.setChecked(true);
                        } else {
                            chk_doors.setChecked(false);
                        }

                        String colunms = notes.getColunms() + "";
                        if (colunms.equals("1")) {
                            chk_colunms.setChecked(true);
                        } else {
                            chk_colunms.setChecked(false);
                        }

                        String secondary = notes.getSecondary() + "";
                        if (secondary.equals("1")) {
                            chk_secondary.setChecked(true);
                        } else {
                            chk_secondary.setChecked(false);
                        }

                        String camers = notes.getCamersnet() + "";
                        if (camers.equals("1")) {
                            chk_net_cams.setChecked(true);
                        } else {
                            chk_net_cams.setChecked(false);
                        }

                        getLocation();
                    }


                    } else {
                    //gpsTracker.showSettingsAlert();
                }
                tvLatLang.setText(String.valueOf(latitude) + " -- " + String.valueOf(longitude));
                pd.dismiss();
            }
        }, 3000);
    }



    public void getLocation(){
        final ProgressDialog pd = new ProgressDialog(UpdateNotesActivity.this);
        pd.setMessage("loading");
        pd.show();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                gpsTracker = new GpsTracker(UpdateNotesActivity.this);
                if (gpsTracker.canGetLocation()) {
                    latitude = gpsTracker.getLatitude();
                    longitude = gpsTracker.getLongitude();

                    /*SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
                    editor.putString("latitude", String.valueOf(latitude));
                    editor.putString("longitude", String.valueOf(longitude));
                    editor.apply();*/

                    String lang= String.valueOf(longitude);
                    String lat= String.valueOf(latitude);

                    Constants.savelanglat(lang,lat,UpdateNotesActivity.this);

                } else {
                    gpsTracker.showSettingsAlert();
                }
                tvLatLang.setText(String.valueOf(latitude) + " -- " + String.valueOf(longitude));
                pd.dismiss();
            }
        }, 3000);
    }





    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.show_saved_data_list) {
            Intent i=new Intent(UpdateNotesActivity.this,GetOfflineSavedSurveyActivity.class);
            startActivity(i);
        } else if (id == R.id.add_new_survey) {
            Intent intent = new Intent(UpdateNotesActivity.this , NavigationHomeActivity.class);
            intent.putExtra("project_name" , Constants.getProjectName(UpdateNotesActivity.this));
            startActivity(intent);

        } else if (id == R.id.nav_change_project) {
            startActivity(new Intent(this, GetUserProjectsActivity.class));
        } else if (id == R.id.nav_comment) {
            startActivity(new Intent(this, CommentsActivity.class));
        } else if (id == R.id.nav_logout) {
            Constants.saveLoginData("", "", "", this);
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void logOut() {

    }

    public void saveData(View view) {

        try {
            String userid=Constants.getuserId(UpdateNotesActivity.this);
            String officeid=Constants.getOfficeId(UpdateNotesActivity.this);
            String projectid=Constants.getProjectId(UpdateNotesActivity.this);

            String note=edt_notes.getText().toString();
            String  loc=tvLatLang.getText().toString();
            String vacdate=tvVisitDate.getText().toString();

            String electricty;
            if(chk_electricty.isChecked()){
                electricty="1";
            }else{
                electricty="0";
            }

            String water;
            if(chk_water.isChecked()){
                water="1";
            }else{
                water="0";
            }

            String bill_end;
            if(chk_bill_end.isChecked()){
                bill_end="1";
            }else{
                bill_end="0";
            }

            String colunms;
            if(chk_colunms.isChecked()){
                colunms="1";
            }else{
                colunms="0";
            }

            String doors;
            if(chk_doors.isChecked()){
                doors="1";
            }else{
                doors="0";
            }

            String secondary;
            if(chk_secondary.isChecked()){
                secondary="1";
            }else{
                secondary="0";
            }

            String camers;
            if(chk_net_cams.isChecked()){
                camers="1";
            }else{
                camers="0";
            }


            notesPresenter.updateNotes(""+note , formattedDate ,electricty ,water ,bill_end ,colunms ,doors ,secondary , camers );

            Intent i=new Intent(UpdateNotesActivity.this,SendingDataSucessActivity.class);
            startActivity(i);
        } catch (Exception ex) {
            Toast.makeText(UpdateNotesActivity.this, "من فضلك اعد المحاوله ", Toast.LENGTH_SHORT).show();
            Log.e("Exception" , ex.getMessage());

        }



    }



}
