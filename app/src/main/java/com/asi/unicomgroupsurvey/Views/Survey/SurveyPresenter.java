package com.asi.unicomgroupsurvey.Views.Survey;

import android.content.Context;

import com.asi.unicomgroupsurvey.Views.login.LoginView;
import com.asi.unicomgroupsurvey.baseClass.BasePresenter;
import com.asi.unicomgroupsurvey.data.ApisClient.ApiInterface;
import com.asi.unicomgroupsurvey.data.LocalSqlite.ItemDbHelper;
import com.asi.unicomgroupsurvey.data.models.GetAllItemsResponse;
import com.asi.unicomgroupsurvey.data.models.Surveys.Survey;
import com.asi.unicomgroupsurvey.data.models.getCities.City;
import com.asi.unicomgroupsurvey.data.models.getDistricts.District;
import com.asi.unicomgroupsurvey.data.models.getGovernors.Governor;
import com.asi.unicomgroupsurvey.data.models.offices.OfficeResponseDetails;
import com.asi.unicomgroupsurvey.di.DaggerApplication;
import com.asi.unicomgroupsurvey.helper.Utilities;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SurveyPresenter implements BasePresenter<SurveyView> {
    SurveyView mView;
    boolean isLoaded = false;


    //inject api interface object

    @Inject
    ApiInterface mApiInterface;
    @Inject
    Context mContext;


    // create sqllit reference
    @Inject
    ItemDbHelper mItemDbHelper;

    //create Constructor to get reference of api interface object
    public SurveyPresenter(Context context){
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
                        //mItemDbHelper.deleteSavedData();
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

    @Override
    public void onAttach(SurveyView view) {
        mView = view;
        mView.onAttache();
    }

    @Override
    public void onDetach() {
        mView = null;
    }

    public void saveSurveyoffline(String gov_id,String gov_name ,String city_id ,String city_name , String district_id ,String district_name
            ,String address , String phone , String hasInternet , String isNetwork , String internetSeed , String office_id , String office_name
            ,String office_visit , String shiftType , String OwnerShipType , String morning_shift_from , String morning_shift_to , String evening_shift_from
            ,String evening_shift_to , String computer_count , String computer_notes , String printers_count , String printers_notes
            ,String scanners_count , String scanners_notes , String other_city , String other_district ,String user_id ,String project_id)
    {
        long rowInserted =   mItemDbHelper.addSurvey(gov_id,gov_name,city_id,city_name,district_id,district_name,address,phone,hasInternet,isNetwork,internetSeed,office_id,office_name,
                office_visit,shiftType,OwnerShipType,morning_shift_from,morning_shift_to,evening_shift_from,evening_shift_to,computer_count,computer_notes,printers_count,
                printers_notes,scanners_count,scanners_notes,other_city,other_district,user_id,project_id);



    }

    public void saveSurveyoffline2(String gov_id,String gov_name ,String city_id ,String city_name , String district_id ,String district_name
            ,String address , String phone , String hasInternet , String isNetwork , String internetSeed , String office_id , String office_name
            ,String office_visit , String shiftType , String OwnerShipType , String morning_shift_from , String morning_shift_to , String evening_shift_from
            ,String evening_shift_to , String computer_count , String computer_notes , String printers_count , String printers_notes
            ,String scanners_count , String scanners_notes , String other_city , String other_district ,String user_id ,String project_id
            , String net_inside,String net_outside ,String ins_out_notes ,String others_macs ,String others_macs_notes
            , String two_offices ,String other_office_id ,String other_office_name
            , String is_arabic ,String is_kurdish ,String both
    )
    {
        long rowInserted =   mItemDbHelper.addSurvey2(gov_id,gov_name,city_id,city_name,district_id,district_name,address,phone,hasInternet,isNetwork,internetSeed,office_id,office_name,
                office_visit,shiftType,OwnerShipType,morning_shift_from,morning_shift_to,evening_shift_from,evening_shift_to,computer_count,computer_notes,printers_count,
                printers_notes,scanners_count,scanners_notes,other_city,other_district,user_id,project_id,net_inside,net_outside,ins_out_notes,others_macs,others_macs_notes,two_offices,other_office_id,other_office_name
                ,is_arabic , is_kurdish , both
        );



    }

    public void updateSurveyoffline(String updatedOfficeId ,String gov_id,String gov_name ,String city_id ,String city_name , String district_id ,String district_name
            ,String address , String phone , String hasInternet , String isNetwork , String internetSeed , String office_id , String office_name
            ,String office_visit , String shiftType , String OwnerShipType , String morning_shift_from , String morning_shift_to , String evening_shift_from
            ,String evening_shift_to , String computer_count , String computer_notes , String printers_count , String printers_notes
            ,String scanners_count , String scanners_notes , String other_city , String other_district ,String user_id ,String project_id)
    {
        boolean rowInserted =   mItemDbHelper.updateSurvey(updatedOfficeId , gov_id, gov_name , city_id , city_name ,  district_id , district_name
                , address ,  phone ,  hasInternet ,  isNetwork ,  internetSeed ,  office_id ,  office_name
                , office_visit ,  shiftType ,  OwnerShipType ,  morning_shift_from ,  morning_shift_to ,  evening_shift_from
                , evening_shift_to ,  computer_count ,  computer_notes ,  printers_count ,  printers_notes
                , scanners_count ,  scanners_notes ,  other_city ,  other_district , user_id , project_id);



    }

    public void updateSurveyOffline(String updatedOfficeId , String gov_id,String gov_name ,String city_id ,String city_name , String district_id ,String district_name
            ,String address , String phone , String hasInternet , String isNetwork , String internetSeed , String office_id , String office_name
            ,String office_visit , String shiftType , String OwnerShipType , String morning_shift_from , String morning_shift_to , String evening_shift_from
            ,String evening_shift_to , String computer_count , String computer_notes , String printers_count , String printers_notes
            ,String scanners_count , String scanners_notes , String other_city , String other_district ,String user_id ,String project_id)
    {
        mItemDbHelper.updateSurvey(updatedOfficeId ,gov_id,gov_name,city_id,city_name,district_id,district_name,address,phone,hasInternet,isNetwork,internetSeed,office_id,office_name,
                office_visit,shiftType,OwnerShipType,morning_shift_from,morning_shift_to,evening_shift_from,evening_shift_to,computer_count,computer_notes,printers_count,
                printers_notes,scanners_count,scanners_notes,other_city,other_district,user_id,project_id);

    }

    public void updateSurveyOffline2(String updatedOfficeId , String gov_id,String gov_name ,String city_id ,String city_name , String district_id ,String district_name
            ,String address , String phone , String hasInternet , String isNetwork , String internetSeed , String office_id , String office_name
            ,String office_visit , String shiftType , String OwnerShipType , String morning_shift_from , String morning_shift_to , String evening_shift_from
            ,String evening_shift_to , String computer_count , String computer_notes , String printers_count , String printers_notes
            ,String scanners_count , String scanners_notes , String other_city , String other_district ,String user_id ,String project_id
            , String net_inside,String net_outside ,String ins_out_notes ,String others_macs ,String others_macs_notes
            , String two_offices ,String other_office_id ,String other_office_name
            , String is_arabic ,String is_kurdish ,String both
    )
    {
        mItemDbHelper.updateSurvey2(updatedOfficeId ,gov_id,gov_name,city_id,city_name,district_id,district_name,address,phone,hasInternet,isNetwork,internetSeed,office_id,office_name,
                office_visit,shiftType,OwnerShipType,morning_shift_from,morning_shift_to,evening_shift_from,evening_shift_to,computer_count,computer_notes,printers_count,
                printers_notes,scanners_count,scanners_notes,other_city,other_district,user_id,project_id,net_inside,net_outside,ins_out_notes,others_macs,others_macs_notes,two_offices,other_office_id,other_office_name
                ,is_arabic , is_kurdish , both
        );

    }




    public List<Governor> getGovsoffline(){

        return mItemDbHelper.getGovs();
    }

    public List<City> getCitiesBygovid(String govid){
        return mItemDbHelper.getCitiesByGovId(govid);
    }

    public List<City> getCities(){
        return mItemDbHelper.getCities();
    }

    public List<District> getDistrictsBycityid(String cityid){
        return mItemDbHelper.getDistrictsbycityid(cityid);
    }

    public List<District> getDistricts(){
        return mItemDbHelper.getDistricts();
    }


    public List<OfficeResponseDetails> getOfficessBydistidprojid(String distid,String projid){
        return mItemDbHelper.getOfficesbydistidprojid(distid,projid);
    }

    public List<OfficeResponseDetails> getOfficess(){
        return mItemDbHelper.getOffices();
    }

    public List<OfficeResponseDetails> getOfficessByprojid(String projid , String govid){
        return mItemDbHelper.getOfficesbyprojid(projid,govid);
    }

    public List<Survey> getdata(){
        return mItemDbHelper.getSurveys();
    }

    public List<Survey> getdatabyUseridOfficeidProjectid(String userid , String officeid , String projectid){
        return mItemDbHelper.getSurveysbyUseridOfficeidProjectid(userid,officeid,projectid);
    }

    public boolean deleteSurvy(Survey survey){
        return  mItemDbHelper.deleteSurvey(survey);
    }


}
