package com.asi.unicomgroupsurvey.Views.Notes;

import com.asi.unicomgroupsurvey.baseClass.BaseView;
import com.asi.unicomgroupsurvey.data.models.Item;

import java.util.ArrayList;

public interface NotesView  extends BaseView {
    void showLoading();

    void hideLoading();

    void showMessage(String message, int mColor);

    void updateList(ArrayList<Item> items);

    void openProjectsActivity();

    //void getLogin(String email,String pw);
}