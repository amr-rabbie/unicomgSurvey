package com.asi.unicomgroupsurvey.Views.saved_offline_survey;

import com.asi.unicomgroupsurvey.baseClass.BaseView;
import com.asi.unicomgroupsurvey.data.models.Surveys.Survey;
import com.asi.unicomgroupsurvey.data.models.get_Notes.Notes;
import com.asi.unicomgroupsurvey.data.models.in_door.InDoorImage;
import com.asi.unicomgroupsurvey.data.models.jops.Job;
import com.asi.unicomgroupsurvey.data.models.out_door.OutDoorImage;
import com.asi.unicomgroupsurvey.data.models.rooms.RoomDetails;
import com.asi.unicomgroupsurvey.data.models.sketch.Sketch;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;


//class created for register function for main view
public interface GetOfflineSavedSurveyView extends BaseView {

    void showMessage(String message);
    void showLoading();
    void hideLoading();

    File getSketchImage();
    void showOfflineSavedSurvey(List<Survey> surveys );

    void showOfflineSavedSurveyAllData(Survey survey ,List<Job> jobs , List<RoomDetails> rooms ,List<Sketch> sketches , List<OutDoorImage> outDoorImages , List<InDoorImage> inDoorImages , List<Notes> notes );

    void reSendTheDataAnotherTry();
}
