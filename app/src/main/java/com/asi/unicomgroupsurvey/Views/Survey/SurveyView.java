package com.asi.unicomgroupsurvey.Views.Survey;

import com.asi.unicomgroupsurvey.baseClass.BaseView;
import com.asi.unicomgroupsurvey.data.models.Item;

import java.util.ArrayList;

public interface SurveyView extends BaseView {

    void showLoading();

    void hideLoading();

    void showMessage(String message, int mColor);
    void showMessageForSave(String message);

    void updateList(ArrayList<Item> items);
}
