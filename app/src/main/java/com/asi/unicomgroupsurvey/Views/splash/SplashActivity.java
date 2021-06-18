package com.asi.unicomgroupsurvey.Views.splash;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.asi.unicomgroupsurvey.BuildConfig;
import com.asi.unicomgroupsurvey.R;
import com.asi.unicomgroupsurvey.Views.login.LoginActivity;
import com.asi.unicomgroupsurvey.data.models.getCities.City;
import com.asi.unicomgroupsurvey.data.models.getDistricts.District;
import com.asi.unicomgroupsurvey.data.models.getGovernors.Governor;
import com.asi.unicomgroupsurvey.di.DaggerApplication;
import com.asi.unicomgroupsurvey.helper.ConnectivityReceiver;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity implements SplashView,ConnectivityReceiver.ConnectivityReceiverListener {


    @Inject
    SplashPresenter mainPresenter;

    ProgressBar progressBar;

    private TextView txtVersion ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        progressBar = findViewById(R.id.progressBar2);
        ButterKnife.bind(this);
        //register some activity   for injections
        ((DaggerApplication) getApplication()).getAppComponent().inject(this);
        mainPresenter.onAttach(this);
        mainPresenter.loadGovernor();

        txtVersion = findViewById(R.id.textView_version);
        txtVersion.setText("© UNICOM GROUP "+BuildConfig.VERSION_NAME);

    }


    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        mainPresenter.checkConnection(isConnected);
    }


    @Override
    public void govSetProgress() {

           progressBar.setProgress(33 , true);


    }

    @Override
    public void citySetProgress() {

            progressBar.setProgress(70 , true);


    }

    @Override
    public void districtSetProgress() {

            progressBar.setProgress(100 , true);


    }

    @Override
    public void startLoginActivity() {

        startActivity(new Intent(this , LoginActivity.class));
    }

    @Override
    public void showMessage(String message, int mColor) {

    }

    @Override
    public void cashCitiesTable(ArrayList<City> cities) {

    }

    @Override
    public void cashGovernorTable(ArrayList<Governor> governors) {

    }

    @Override
    public void cashDistrictsTable(ArrayList<District> districts) {

    }


    @Override
    public void onAttache() {

    }

    @Override
    public void onDetach() {

    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }
}