package com.asi.unicomgroupsurvey.Views.comments;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.asi.unicomgroupsurvey.R;
import com.asi.unicomgroupsurvey.Views.Survey.UpdateSurveyActivity;
import com.asi.unicomgroupsurvey.Views.get_user_projects.GetUserProjectsActivity;
import com.asi.unicomgroupsurvey.Views.jobs.JobsActivity;
import com.asi.unicomgroupsurvey.Views.login.LoginActivity;
import com.asi.unicomgroupsurvey.Views.navigation_home.NavigationHomeActivity;
import com.asi.unicomgroupsurvey.Views.saved_offline_survey.GetOfflineSavedSurveyActivity;
import com.asi.unicomgroupsurvey.data.models.comments.Comment;
import com.asi.unicomgroupsurvey.di.DaggerApplication;
import com.asi.unicomgroupsurvey.helper.ConnectivityReceiver;
import com.asi.unicomgroupsurvey.helper.Constants;

import java.util.List;

import javax.inject.Inject;

public class CommentsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , CommentsView,
        ConnectivityReceiver.ConnectivityReceiverListener, CommentItemClick  {

    private List<Comment> comments;
    private RecyclerView mRecyclerView;
    private CommentAdapter adapter;
    private ProgressBar mLoadingIndicator;
    TextView tvEmptyList;

    @Inject
    CommentsPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        ((DaggerApplication) getApplication()).getAppComponent().inject(this);
        mainPresenter.onAttach(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView titleBar = findViewById(R.id.text_title_bar);
        titleBar.setText("تعلقيات الاداره ");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        mRecyclerView = (RecyclerView) findViewById(R.id.comments_list);
        adapter = new CommentAdapter(this);

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            LinearLayoutManager layoutManager
                    = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            mRecyclerView.setLayoutManager(layoutManager);
        }
        mRecyclerView.setHasFixedSize(true);

        mLoadingIndicator = findViewById(R.id.loading_indicator);
        tvEmptyList = findViewById(R.id.tvEmptyList);

       mainPresenter.loadComments(Constants.getuserToken(this) , Constants.getuserId(this));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.comments, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.show_saved_data_list) {
            Intent i=new Intent(CommentsActivity.this,GetOfflineSavedSurveyActivity.class);
            startActivity(i);
        } else if (id == R.id.add_new_survey) {

            Intent intent = new Intent(CommentsActivity.this , NavigationHomeActivity.class);
            intent.putExtra("project_name" , Constants.getProjectName(CommentsActivity.this));
            startActivity(intent);

        } else if (id == R.id.nav_change_project) {
            startActivity(new Intent(this, GetUserProjectsActivity.class));
        } else if (id == R.id.nav_comment) {
            startActivity(new Intent(this, CommentsActivity.class));
        } else if (id == R.id.nav_logout) {
            android.app.AlertDialog.Builder builder;
            builder = new android.app.AlertDialog.Builder(CommentsActivity.this);
            builder.setMessage("هل حقا تريد تسجيل الخروج ؟");
            builder.setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Constants.saveLoginData("", "", "", CommentsActivity.this);
                    Intent i = new Intent(CommentsActivity.this, LoginActivity.class);
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
        mLoadingIndicator.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mLoadingIndicator.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(String message, int mColor) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getComments(List<Comment> comments1) {
        try {

            if (comments1.isEmpty()) {
                // showMessage("Empty List", 1);
                tvEmptyList.setVisibility(View.VISIBLE);
            } else {
                comments = comments1;
                tvEmptyList.setVisibility(View.GONE);
                adapter.setCommentData(comments1, this);
                mRecyclerView.setAdapter(adapter);
            }

        }
        catch (Exception ee) {
            Log.e("Exception" , ee.getMessage()) ;
            Toast.makeText(this, "جودة الانترنت سيئه حاول مره اخري ", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {

    }

    @Override
    public void EditBtn(View v, int position) {
        Comment comment = comments.get(position);
       // showMessage("Edit Office Please ...", 1);
        mainPresenter.saveOfficeData(comment);

        Constants.saveUpdatedOfficeId(comment.getOffice_id() , CommentsActivity.this);
        Constants.saveProjectId(comment.getOffice().getProject_id() , CommentsActivity.this);


        //startActivity(new Intent(CommentsActivity.this , UpdateSurveyActivity.class));
        Intent i=new Intent(CommentsActivity.this,UpdateSurveyActivity.class);
        i.putExtra("cflag","comment");
        startActivity(i);


    }


}
