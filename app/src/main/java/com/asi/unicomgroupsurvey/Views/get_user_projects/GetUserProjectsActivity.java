package com.asi.unicomgroupsurvey.Views.get_user_projects;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.asi.unicomgroupsurvey.R;
import com.asi.unicomgroupsurvey.Views.Notes.NotesActivity;
import com.asi.unicomgroupsurvey.Views.navigation_home.NavigationHomeActivity;
import com.asi.unicomgroupsurvey.data.models.get_user_projects.GetUserProjectsResponse;
import com.asi.unicomgroupsurvey.data.models.get_user_projects.UserProjectsDetails;
import com.asi.unicomgroupsurvey.di.DaggerApplication;
import com.asi.unicomgroupsurvey.helper.Constants;
import com.asi.unicomgroupsurvey.helper.GpsTracker;
import com.asi.unicomgroupsurvey.helper.Utilities;
import com.asi.unicomgroupsurvey.listClickListener.ItemClickListener;

import java.util.List;

import javax.inject.Inject;

public class GetUserProjectsActivity extends AppCompatActivity implements GetUserProjectView , ItemClickListener {


    @Inject
    GetUserProjectsPresenter getUserProjectsPresenter ;

    GetUserProjectAdapter getUserProjectAdapter;

    RecyclerView recyclerViewShowUserProjects;

    ProgressBar progressBar;
    private GpsTracker gpsTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_user_projects);

        recyclerViewShowUserProjects = findViewById(R.id.recycleView_get_user_projects);

        progressBar = findViewById(R.id.progressBar_load_projects);


        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar);

        ((DaggerApplication)getApplication()).getAppComponent().inject(this);
                 getUserProjectsPresenter.onAttach(this);

                if(Utilities.checkConnection(GetUserProjectsActivity.this))
                 getUserProjectsPresenter.getPositions();
                else
                    getUserProjectsPresenter.getUserProjectsPresenter();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewShowUserProjects.setLayoutManager(linearLayoutManager);

        gpsTracker = new GpsTracker(this);
        if (gpsTracker.canGetLocation()) {

        }
        else {
            gpsTracker.showSettingsAlert();
        }

    }

    @Override
    public void showLoading() {

        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {

        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void getUserProjectsList(GetUserProjectsResponse getUserProjectsResponse) {


        getUserProjectAdapter = new GetUserProjectAdapter(this , getUserProjectsResponse.getUserProjectsDetailsList() ,this);
        recyclerViewShowUserProjects.setAdapter(getUserProjectAdapter);
    }

    @Override
    public void showCursorProject(List<UserProjectsDetails> userProjectsDetails) {
        getUserProjectAdapter = new GetUserProjectAdapter(this , userProjectsDetails ,this);
        recyclerViewShowUserProjects.setAdapter(getUserProjectAdapter);
    }


    @Override
    public void onAttache() {

    }

    @Override
    public void onDetach() {

    }




    @Override
    public void onItemClick(int position, UserProjectsDetails getUserProjectsDetails, View view) {
        Constants.saveProjectId(getUserProjectsDetails.getId() , this);
        Constants.saveProjectName(getUserProjectsDetails.getProject_name() , this);
        Intent intent = new Intent(GetUserProjectsActivity.this , NavigationHomeActivity.class);
        intent.putExtra("project_name" , getUserProjectsDetails.getProject_name());
        startActivity(intent);


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }
}
