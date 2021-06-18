package com.asi.unicomgroupsurvey.Views.splash;

import com.asi.unicomgroupsurvey.baseClass.BaseView;
import com.asi.unicomgroupsurvey.data.models.getCities.City;
import com.asi.unicomgroupsurvey.data.models.getDistricts.District;
import com.asi.unicomgroupsurvey.data.models.getGovernors.Governor;

import java.util.ArrayList;


//class created for register function for main view
public interface SplashView extends BaseView {
    void govSetProgress();
    void citySetProgress();
    void districtSetProgress();
    void startLoginActivity();
    void showMessage(String message, int mColor);
    void cashCitiesTable(ArrayList<City> cities);
    void cashGovernorTable(ArrayList<Governor> governors);
    void cashDistrictsTable (ArrayList<District> districts);

}
