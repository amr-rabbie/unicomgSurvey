package com.asi.unicomgroupsurvey.Views.login;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.asi.unicomgroupsurvey.baseClass.BasePresenter;
import com.asi.unicomgroupsurvey.data.ApisClient.ApiInterface;
import com.asi.unicomgroupsurvey.data.LocalSqlite.ItemDbHelper;
import com.asi.unicomgroupsurvey.data.models.GetAllItemsResponse;
import com.asi.unicomgroupsurvey.data.models.Login.Info;
import com.asi.unicomgroupsurvey.data.models.Login.Login;
import com.asi.unicomgroupsurvey.data.models.getCities.City;
import com.asi.unicomgroupsurvey.data.models.getCities.GetCitiesResponse;
import com.asi.unicomgroupsurvey.data.models.getDistricts.District;
import com.asi.unicomgroupsurvey.data.models.getDistricts.GetDistrictsResponse;
import com.asi.unicomgroupsurvey.data.models.getGovernors.GetGovernorsResponse;
import com.asi.unicomgroupsurvey.data.models.getGovernors.Governor;
import com.asi.unicomgroupsurvey.di.DaggerApplication;
import com.asi.unicomgroupsurvey.helper.Constants;
import com.asi.unicomgroupsurvey.helper.Utilities;

import java.util.ArrayList;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginPresenter  implements BasePresenter<LoginView> {
    LoginView mView;
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
    public void onAttach(LoginView view) {
        mView = view;
        mView.onAttache();
    }



    @Override
    public void onDetach() {
        mView = null;
    }

    //create Constructor to get reference of api interface object
    public LoginPresenter(Context context){
        ((DaggerApplication)context).getAppComponent().inject(this);
    }

    //this function created to load items from specific endpoint
    public void loadItems() {
        if(!Utilities.checkConnection(mContext)){
            checkConnection(false);
            return;
        }

        mView.showLoading();
        mApiInterface.getAllItems()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetAllItemsResponse>() {
                    @Override
                    public final void onCompleted() {

                        mView.hideLoading();
                    }

                    @Override
                    public final void onError(Throwable e) {
                        mView.hideLoading();
                    }

                    @Override
                    public final void onNext(GetAllItemsResponse response) {
                        isLoaded = true;
                        mView.updateList(response.getItems());
                  //      mItemDbHelper.deleteSavedData();
                     }
                });
    }




    //store Realm data


    void checkConnection(boolean isConnected) {
        //check internet and  data not loaded
        if(isConnected  && !isLoaded){
            loadItems();
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




    public  void getLogin(final String email, final String pw , final ProgressBar pbar , final LinearLayout ll, final Dialog dialog, final ProgressBar probar){



        if(!Utilities.checkConnection(mContext)){
            checkConnection(false);

            mView.showMessage("من فضلك قم بالاتصال بالانترنت",0);
            return;
        }





        //ProgressBar probar = (ProgressBar) dialog.findViewById(R.id.pbar);


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms

                //pbar.setVisibility(View.VISIBLE);
                //ll.setVisibility(View.GONE);
                dialog.show();


       //mView.showLoading();
        mApiInterface.getLogin(email,pw)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Login>() {
                    @Override
                    public final void onCompleted() {

                        //mView.hideLoading();
                    }

                    @Override
                    public final void onError(Throwable e) {
                       // mView.hideLoading();
                        //Toast.makeText(mContext, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(mContext, "البريد الالكترونى او كلمة السر خطا", Toast.LENGTH_SHORT).show();
                        probar.setVisibility(View.GONE);
                        dialog.dismiss();
                    }

                    @Override
                    public void onNext(Login login) {
                        Info info = login.getInfo();
                        String userId = info.getUserId().toString();
                        String name = info.getName();
                        String token = info.getToken();
                        Constants.saveLoginData(userId,name,token,mContext);
                        Constants.saveUnPw(email,pw,mContext);
                        Toast.makeText(mContext,  "مرحبا بك "   + name, Toast.LENGTH_SHORT).show();
                        mView.openProjectsActivity();
                    }


                    /*
                    @Override
                    public final void onNext(GetAllItemsResponse response) {
                        isLoaded = true;
                        mView.updateList(response.getItems());
                        mItemDbHelper.deleteSavedData();
                        SaveLocalData(response.getItems());
                    }
                    */

                });

            }
        }, 1000);

        //pbar.setVisibility(View.GONE);
        //ll.setVisibility(View.VISIBLE);
        dialog.dismiss();
    }


///////////////////////////////
//this function created to load items from specific endpoint

public void loadGovernor() {
    if(!Utilities.checkConnection(mContext)){

        checkConnection(false);

        return;
    }


    mApiInterface.getGovernors()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<GetGovernorsResponse>() {
                @Override
                public final void onCompleted() {
                    loadCities();

                }
                @Override
                public final void onError(Throwable e) {

                }
                @Override
                public final void onNext(GetGovernorsResponse response) {
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
                        loadDistricts();

                    }

                    @Override
                    public final void onError(Throwable e) {
                        // mView.hideLoading();
                    }

                    @Override
                    public final void onNext(GetCitiesResponse response) {
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
                        //     Toast.makeText(mContext, "تم تحميل البيانات ", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public final void onError(Throwable e) {
                        //   mView.hideLoading();
                    }

                    @Override
                    public final void onNext(GetDistrictsResponse response) {
                        isLoaded = true;
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

    ///////////////////////////////////////////


}
