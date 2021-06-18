package com.asi.unicomgroupsurvey.Views.Notes;

import android.content.Context;
import android.widget.Toast;

import com.asi.unicomgroupsurvey.Views.login.LoginView;
import com.asi.unicomgroupsurvey.baseClass.BasePresenter;
import com.asi.unicomgroupsurvey.data.ApisClient.ApiInterface;
import com.asi.unicomgroupsurvey.data.LocalSqlite.ItemDbHelper;
import com.asi.unicomgroupsurvey.data.models.GetAllItemsResponse;
import com.asi.unicomgroupsurvey.data.models.Login.Info;
import com.asi.unicomgroupsurvey.data.models.Login.Login;
import com.asi.unicomgroupsurvey.data.models.Surveys.Survey;
import com.asi.unicomgroupsurvey.data.models.get_Notes.Notes;
import com.asi.unicomgroupsurvey.di.DaggerApplication;
import com.asi.unicomgroupsurvey.helper.Constants;
import com.asi.unicomgroupsurvey.helper.Utilities;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NotesPresenter  implements BasePresenter<NotesView> {
    NotesView mView;
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
    public void onAttach(NotesView view) {
        mView = view;
        mView.onAttache();
    }



    @Override
    public void onDetach() {
        mView = null;
    }

    //create Constructor to get reference of api interface object
    public NotesPresenter(Context context){
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




    void getLogin(String email,String pw){
        if(!Utilities.checkConnection(mContext)){
            checkConnection(false);

            return;
        }

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
                        Toast.makeText(mContext, "غير مسجل , حاول مرة اخرى", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(Login login) {
                        Info info = login.getInfo();
                        String userId = info.getUserId().toString();
                        String name = info.getName();
                        String token = info.getToken();

                        Constants.saveLoginData(userId,name,token,mContext);

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

    public void saveNotesoffline(String office_id , String notes
            ,String location ,String user_id ,String project_id , String visit_date
            , String electricty , String water, String bill_end , String colunms
            ,String doors , String secondary ,String camersnet)
    {
        mItemDbHelper.addNotes2(office_id,notes,location,user_id ,project_id , visit_date,electricty,water,bill_end,colunms,doors,secondary,camersnet);
    }

    public List<Notes> getdata(){
        return mItemDbHelper.getNotes();
    }

    public Notes getNotesForUpdate(){
        //Toast.makeText(mContext,"User Id: " +Constants.getuserId(mContext) + "\n" + "Project Id: " + Constants.getProjectId( mContext) + "\n" + "Office Id: " + Constants.getOfficeId(mContext) ,Toast.LENGTH_LONG).show();
        return mItemDbHelper.getNotesForUpdate(Constants.getuserId(mContext) , Constants.getProjectId( mContext) , Constants.getOfficeId(mContext));
    }

    public Notes getNotesForUpdate2(){
        //Toast.makeText(mContext,"User Id: " +Constants.getuserId(mContext) + "\n" + "Project Id: " + Constants.getProjectId( mContext) + "\n" + "Office Id: " + Constants.getOfficeId(mContext) ,Toast.LENGTH_LONG).show();
        return mItemDbHelper.getNotesForUpdate(Constants.getuserId(mContext) , Constants.getProjectId( mContext) , Constants.getOfficeId(mContext));
    }

    public void updateNotes(String notes , String visit_date  , String electricty , String water, String bill_end , String colunms
            ,String doors , String secondary ,String camersnet){
        mItemDbHelper.updateNotesForUpdate2(Constants.getuserId(mContext) , Constants.getProjectId( mContext) , Constants.getOfficeId(mContext) , notes , visit_date ,electricty ,water ,bill_end ,colunms ,doors ,secondary ,camersnet);
    }

    public List<Notes> getdataByUseridOfficeidProjectid(String userid,String officeid , String projectid){
        return mItemDbHelper.getNotesByUseridProjectIdOfficeid(userid ,officeid ,projectid);
    }








}
