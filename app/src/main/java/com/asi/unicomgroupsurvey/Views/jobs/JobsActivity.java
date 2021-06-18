package com.asi.unicomgroupsurvey.Views.jobs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.asi.unicomgroupsurvey.R;
import com.asi.unicomgroupsurvey.Views.add_rooms.RoomsActivity;
import com.asi.unicomgroupsurvey.Views.comments.CommentsActivity;
import com.asi.unicomgroupsurvey.Views.get_user_projects.GetUserProjectsActivity;
import com.asi.unicomgroupsurvey.Views.login.LoginActivity;
import com.asi.unicomgroupsurvey.Views.navigation_home.NavigationHomeActivity;
import com.asi.unicomgroupsurvey.Views.saved_offline_survey.GetOfflineSavedSurveyActivity;
import com.asi.unicomgroupsurvey.data.models.jops.Job;
import com.asi.unicomgroupsurvey.data.models.positions.PositionResponseDetails;
import com.asi.unicomgroupsurvey.di.DaggerApplication;
import com.asi.unicomgroupsurvey.helper.ConnectivityReceiver;
import com.asi.unicomgroupsurvey.helper.Constants;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class JobsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , JobsView, ConnectivityReceiver.ConnectivityReceiverListener, JobItemClick{

    private RecyclerView mRecyclerView;
    private JobAdapter jobAdapter;
    public ArrayList<PositionResponseDetails> positionResponseDetails;
    public ArrayList<Job> jobs;
    public static String jobTitle;
    public static String jobID;
    @Inject
    JobsPresenter mainPresenter;
    ProgressBar progressBar;
    LinearLayout tvEmptyList;
    TextView nextClick;
    TextView backClick;
    TextView add_job_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView titleBar = findViewById(R.id.text_title_bar);
        titleBar.setText("الوظائف");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ((DaggerApplication) getApplication()).getAppComponent().inject(this);
        mainPresenter.onAttach(this);

        ButterKnife.bind(this);
        tvEmptyList = findViewById(R.id.tvEmptyList);
        nextClick = findViewById(R.id.text_next_click);
        backClick = findViewById(R.id.text_back_click);
        mRecyclerView = findViewById(R.id.jobs_list);
        add_job_btn = findViewById(R.id.add_job_btn);
        add_job_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddDialog();
            }
        });
        findViewById(R.id.add_fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddDialog();
            }
        });
        jobAdapter = new JobAdapter(this);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        progressBar =  findViewById(R.id.progressBar);

        mainPresenter.getJobs(Constants.getProjectId(this), Constants.getOfficeId(this),
                Constants.getuserId(this));
        mainPresenter.getPositions(Constants.getProjectId(this));


        nextClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(jobs == null || jobs.isEmpty())
                {
                    Toast.makeText(JobsActivity.this, "من فضلك أدخل وظيفة علي الاقل", Toast.LENGTH_LONG).show();
                }
                else {
                    startActivity(new Intent(JobsActivity.this, RoomsActivity.class));
                }
            }
        });

        backClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            JobsActivity.super.onBackPressed();
           /* new AlertDialog.Builder(this)
                    .setMessage("هل أنت متاكد من الخروج من الصفحة ؟ ")
                    .setCancelable(false)
                    .setPositiveButton("متابعة", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            JobsActivity.super.onBackPressed();
                        }
                    })
                    .setNegativeButton("الغاء", null)
                    .show();*/
        }
    }

    /* @Override
     public boolean onCreateOptionsMenu(Menu menu) {
         // Inflate the menu; this adds items to the action bar if it is present.
         getMenuInflater().inflate(R.menu.jobs, menu);
         return true;
     }

     @Override
     public boolean onOptionsItemSelected(MenuItem item) {
         // Handle action bar item clicks here. The action bar will
         // automatically handle clicks on the Home/Up button, so long
         // as you specify a parent activity in AndroidManifest.xml.
         int id = item.getItemId();

         //noinspection SimplifiableIfStatement
         if (id == R.id.action_settings) {
             return true;
         }

         return super.onOptionsItemSelected(item);
     }
 */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.show_saved_data_list) {
            Intent i=new Intent(JobsActivity.this,GetOfflineSavedSurveyActivity.class);
            startActivity(i);
        } else if (id == R.id.add_new_survey) {
            startActivity(new Intent(JobsActivity.this , NavigationHomeActivity.class));
        } else if (id == R.id.nav_change_project) {
            startActivity(new Intent(this, GetUserProjectsActivity.class));
        } else if (id == R.id.nav_comment) {
            startActivity(new Intent(this, CommentsActivity.class));
        } else if (id == R.id.nav_logout) {
            android.app.AlertDialog.Builder builder;
            builder = new android.app.AlertDialog.Builder(JobsActivity.this);
            builder.setMessage("هل حقا تريد تسجيل الخروج ؟");
            builder.setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Constants.saveLoginData("", "", "", JobsActivity.this);
                    Intent i = new Intent(JobsActivity.this, LoginActivity.class);
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



    @Override
    public void onAttache() {

    }

    @Override
    public void onDetach() {

    }

    @Override
    public void showProgress() {

        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void startRoomActivity() {

    }

    @Override
    public void afterDelete(ArrayList<Job> jobs1) {
        if (jobs1.isEmpty()) {
          //  showMessage("Empty List", 1);
            tvEmptyList.setVisibility(View.VISIBLE);
            jobAdapter.setJobsData(jobs1, this);
            jobs = jobs1;
            mRecyclerView.setAdapter(jobAdapter);
        } else {
            tvEmptyList.setVisibility(View.GONE);
            jobAdapter.setJobsData(jobs1, this);
            jobs = jobs1;
            mRecyclerView.setAdapter(jobAdapter);
        }
    }

    @Override
    public void afterUpdate(ArrayList<Job> jobs1) {
        if (jobs1.isEmpty()) {
            tvEmptyList.setVisibility(View.VISIBLE);
        } else {
            tvEmptyList.setVisibility(View.GONE);
            jobAdapter.setJobsData(jobs1, this);
            jobs = jobs1;
            mRecyclerView.setAdapter(jobAdapter);

        }
    }

    @Override
    public void showMessage(String message, int mColor) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getJobs(ArrayList<Job> jobs1) {

        if (jobs1.isEmpty()) {
          //  showMessage("Empty List", 1);
            tvEmptyList.setVisibility(View.VISIBLE);
        } else {
            tvEmptyList.setVisibility(View.GONE);
            jobAdapter.setJobsData(jobs1, this);
            jobs = jobs1;
            mRecyclerView.setAdapter(jobAdapter);
        }

    }

    @Override
    public void afterAdd(ArrayList<Job> jobs1) {
        if (jobs1.isEmpty()) {
           // showMessage("Empty List", 1);
            tvEmptyList.setVisibility(View.VISIBLE);
        } else {
            tvEmptyList.setVisibility(View.GONE);
            jobAdapter.setJobsData(jobs1, this);
            jobs = jobs1;
            mRecyclerView.setAdapter(jobAdapter);
        }

    }

    @Override
    public void getPostionsNames(ArrayList<PositionResponseDetails> positions) {
        positionResponseDetails = positions;
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {

    }

    @Override
    public void deleteJob(View v, final int position) {
        android.app.AlertDialog.Builder builder;
        builder = new android.app.AlertDialog.Builder(JobsActivity.this);
        builder.setMessage("هل تريد مسح بيانات هذه الوظيفه ؟");
        builder.setPositiveButton("نعم", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Job job = jobs.get(position);
                mainPresenter.deleteJob(job);
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

    @Override
    public void updateJob(View v, int position) {

        Job job = jobs.get(position);
        showUpdateDialog(job);

    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    public void showAddDialog() {
        LayoutInflater li = LayoutInflater.from(this);
        final Dialog dialog = new Dialog(this);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.add_job_dialog);
        Button addBtn = dialog.findViewById(R.id.save_job_btn);
        final Button cancelBtn = dialog.findViewById(R.id.cancel_save_btn);
        Window window = dialog.getWindow();
        window.setLayout(DrawerLayout.LayoutParams.WRAP_CONTENT, DrawerLayout.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        final EditText notes = dialog.findViewById(R.id.job_note_edt);
        final EditText count = dialog.findViewById(R.id.job_count_edt);
        final Spinner spinnerJobs = dialog.findViewById(R.id.spinner_positions);
        spinnerJobs.setEnabled(true);
        //spinnerJobs.setPrompt("أختار الوظيفة");
        PositionAdapter positionAdapter = new PositionAdapter(this, R.layout.position_spinner_item,
                positionResponseDetails);
        spinnerJobs.setAdapter(positionAdapter);
        spinnerJobs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                jobTitle = positionResponseDetails.get(position).getPosition_name();
                jobID = positionResponseDetails.get(position).getPosition_id();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(jobTitle.equals("اختر الوظيفة")){
                    Toast.makeText(JobsActivity.this,
                            "برجاء اختيار الوظيفة", Toast.LENGTH_LONG).show();
                }
                else if (notes.getText().toString().trim().isEmpty() && count.getText().toString().trim().isEmpty() &&
                        jobTitle.equals("اختر الوظيفة")) {
                    Toast.makeText(JobsActivity.this, "برجاء ادخال جميع الحقول المطلوبة واختيار الوظيفة", Toast.LENGTH_LONG).show();


                } else if (count.getText().toString().trim().isEmpty()) {
                    count.setError("برجاء ادخال العدد");
                } else if (jobTitle.equals("--أختر--")) {
                    Toast.makeText(JobsActivity.this, "اختر ليست وظيفه", Toast.LENGTH_LONG).show();
                } else {

                    if (mainPresenter.isFounded(jobID , Constants.getOfficeId(JobsActivity.this) ,Constants.getProjectId(JobsActivity.this) , Constants.getuserId(JobsActivity.this))) {
                        Toast.makeText(JobsActivity.this, "هذه الوظيفة مضافة من قبل ", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(JobsActivity.this, "تم أضافة(" + jobTitle + ")كوظيفة جديدة ", Toast.LENGTH_LONG).show();
                        Job job = new Job(Constants.getuserId(JobsActivity.this), Constants.getProjectId(JobsActivity.this), Constants.getOfficeId(JobsActivity.this),
                                jobID,
                                count.getText().toString(),
                                notes.getText().toString(),
                                jobTitle);
                        mainPresenter.addJob(job);
                        //tvEmptyList.setVisibility(View.VISIBLE);
                    }

                    dialog.cancel();
                }

            }
        });

        dialog.show();

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
    }

    public void showUpdateDialog(Job job) {

        LayoutInflater li = LayoutInflater.from(this);
        final Dialog dialog = new Dialog(this);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.edit_job_dialog);
        Button addBtn = dialog.findViewById(R.id.save_job_btn);
        final Button cancelBtn = dialog.findViewById(R.id.cancel_save_btn);
        Window window = dialog.getWindow();
        window.setLayout(DrawerLayout.LayoutParams.WRAP_CONTENT, DrawerLayout.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);

        final EditText notes = dialog.findViewById(R.id.job_note_edt);
        notes.setText(job.getNote());
        final EditText count = dialog.findViewById(R.id.job_count_edt);
        count.setText(job.getCount());
        final Spinner spinnerJobs = dialog.findViewById(R.id.spinner_positions);

        spinnerJobs.setEnabled(false);
        PositionAdapter positionAdapter = new PositionAdapter(this, R.layout.position_spinner_item,
                positionResponseDetails);
        spinnerJobs.setAdapter(positionAdapter);


        int index = getIndex(positionResponseDetails, job);
        spinnerJobs.setSelection(getIndex(positionResponseDetails, job));

        final String oldJobId = job.getJob_id();

        spinnerJobs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                jobTitle = positionResponseDetails.get(position).getPosition_name();
                jobID = positionResponseDetails.get(position).getPosition_id();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (notes.getText().toString().trim().isEmpty() && count.getText().toString().trim().isEmpty() &&
                        jobTitle.equals("--أختر--")) {
                    Toast.makeText(JobsActivity.this, "برجاء ادخال جميع الحقول المطلوبة واختيار الوظيفة", Toast.LENGTH_LONG).show();
                } else if (count.getText().toString().trim().isEmpty()) {
                    count.setError("برجاء ادخال العدد");
                } else if (jobTitle.equals("--أختر--")) {
                    Toast.makeText(JobsActivity.this, "اختر ليست وظيفه", Toast.LENGTH_LONG).show();
                } else {
                    Job job = new Job(Constants.getuserId(JobsActivity.this), Constants.getProjectId(JobsActivity.this), Constants.getOfficeId(JobsActivity.this),
                            jobID,
                            count.getText().toString(),
                            notes.getText().toString(),
                            jobTitle);
                    mainPresenter.updateJob(oldJobId,job);
                    //  tvEmptyList.setVisibility(View.VISIBLE);
                    dialog.cancel();
                }
            }
        });

        dialog.show();

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
    }

    public int getIndex(ArrayList<PositionResponseDetails> positionResponseDetails, Job job) {

        for (PositionResponseDetails positionResponseDetails1 : positionResponseDetails) {

            if (job.getJob_id().equals(positionResponseDetails1.getPosition_id())) {
                return positionResponseDetails.indexOf(positionResponseDetails1);
            }

        }
        return 0;
    }
}

