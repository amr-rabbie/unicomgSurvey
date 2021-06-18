package com.asi.unicomgroupsurvey.Views.splash;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.asi.unicomgroupsurvey.baseClass.BasePresenter;
import com.asi.unicomgroupsurvey.data.ApisClient.ApiInterface;
import com.asi.unicomgroupsurvey.data.LocalSqlite.ItemDbHelper;
import com.asi.unicomgroupsurvey.data.models.getCities.City;
import com.asi.unicomgroupsurvey.data.models.getCities.GetCitiesResponse;
import com.asi.unicomgroupsurvey.data.models.getDistricts.District;
import com.asi.unicomgroupsurvey.data.models.getDistricts.GetDistrictsResponse;
import com.asi.unicomgroupsurvey.data.models.getGovernors.GetGovernorsResponse;
import com.asi.unicomgroupsurvey.data.models.getGovernors.Governor;
import com.asi.unicomgroupsurvey.di.DaggerApplication;
import com.asi.unicomgroupsurvey.helper.Utilities;

import java.util.ArrayList;
import java.util.logging.Handler;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SplashPresenter implements BasePresenter<SplashView> {
    SplashView mView;
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
    public void onAttach(SplashView view) {
        mView = view;
        mView.onAttache();
    }

    @Override
    public void onDetach() {
        mView = null;
    }

    //create Constructor to get reference of api interface object
    public SplashPresenter(Context context){
        ((DaggerApplication)context).getAppComponent().inject(this);
    }

    //this function created to load items from specific endpoint
    public void loadGovernor() {
        if(!Utilities.checkConnection(mContext)){

            checkConnection(false);

            return;
        }


      //  mView.showLoading();
        mApiInterface.getGovernors()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetGovernorsResponse>() {
                    @Override
                    public final void onCompleted() {
                        mView.govSetProgress();
                        loadCities();

                    }
                    @Override
                    public final void onError(Throwable e) {

                    }
                    @Override
                    public final void onNext(GetGovernorsResponse response) {
                        isLoaded = true;
                        mView.cashGovernorTable(response.getGovernors());
                        if (!mItemDbHelper.getGovernors().isEmpty())
                        mItemDbHelper.deleteSavedGovernors();

                        saveGovernors(response.getGovernors());
                    }
                });

    }

    private void loadCities() {
        if (!Utilities.checkConnection(mContext)) {
            checkConnection(false);
            return;
        }
        mApiInterface.getCities()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetCitiesResponse>() {
                    @Override
                    public final void onCompleted() {
                        mView.citySetProgress();
                        loadDistricts();

                    }

                    @Override
                    public final void onError(Throwable e) {
                       // mView.hideLoading();
                    }

                    @Override
                    public final void onNext(GetCitiesResponse response) {
                        isLoaded = true;
                        mView.cashCitiesTable(response.getCities());
                        if(!mItemDbHelper.getCities().isEmpty())
                        mItemDbHelper.deleteSavedCities();

                        saveCities(response.getCities());
                    }
                });

    }

    private void loadDistricts() {
        if (!Utilities.checkConnection(mContext)) {
            checkConnection(false);
            return;
        }
        mApiInterface.getDistricts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetDistrictsResponse>() {
                    @Override
                    public final void onCompleted() {
                        mView.districtSetProgress();
                        mView.startLoginActivity();
                   //     Toast.makeText(mContext, "تم تحميل البيانات ", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public final void onError(Throwable e) {
                     //   mView.hideLoading();
                    }

                    @Override
                    public final void onNext(GetDistrictsResponse response) {
                        isLoaded = true;
                        mView.cashDistrictsTable(response.getDistricts());
                        if (!mItemDbHelper.getDistricts().isEmpty())
                        mItemDbHelper.deleteSavedDistricts();
                        saveDistricts(response.getDistricts());
                    }
                });

    }

    //store Realm data
    private void saveGovernors(ArrayList<Governor> governors) {
        mItemDbHelper.addGovernors(governors);
    }
    private void saveCities(ArrayList<City> cities) {
        mItemDbHelper.addCities(cities);
    }
    private void saveDistricts(ArrayList<District> districts) {
        mItemDbHelper.addDistricts(districts);
    }



    void checkConnection(boolean isConnected) {
        //check internet and  data not loaded
        if(isConnected  && !isLoaded){
            loadGovernor();
            isLoaded = false;
         //   mView.showMessage(mContext.getString(R.string.connect_to_internet),Color.GREEN);
        }

        if(!isConnected){
            mView.districtSetProgress();
            new android.os.Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mView.startLoginActivity();
                }
            } , 1000);

        }
        else if(!isConnected && isLoaded){
            //offline check and  data loaded
          //  mView.showMessage(mContext.getString(R.string.offline),Color.WHITE);

        }
        else if(!isConnected && !isLoaded){
            //get offline  data using realm
            //mView.showMessage(mContext.getString(R.string.get_data_from_local),Color.WHITE);
       //     mView.updateList(mItemDbHelper.getAllItems());

        }else if(isConnected && isLoaded){
            //mView.showMessage(mContext.getString(R.string.connect_to_internet),Color.GREEN);
        }
    }

}
