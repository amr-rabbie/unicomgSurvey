package com.asi.unicomgroupsurvey.Views.Sucess;

import com.asi.unicomgroupsurvey.baseClass.BaseView;
import com.asi.unicomgroupsurvey.data.models.Surveys.Survey;
import com.asi.unicomgroupsurvey.data.models.get_Notes.Notes;
import com.asi.unicomgroupsurvey.data.models.in_door.InDoorImage;
import com.asi.unicomgroupsurvey.data.models.jops.Job;
import com.asi.unicomgroupsurvey.data.models.out_door.OutDoorImage;
import com.asi.unicomgroupsurvey.data.models.rooms.RoomDetails;
import com.asi.unicomgroupsurvey.data.models.sketch.Sketch;

import java.util.List;


//class created for register function for main view
public interface SendingDataSuccessView extends BaseView {

    void showMessage(String message);

    void showProgress();
    void hideProgress();
    void showOfflineSavedSurvey(Survey surveys);
    void showOfflineSavedSurveyAllData(Survey survey, List<Job> jobs, List<RoomDetails> rooms, List<Sketch> sketches, List<OutDoorImage> outDoorImages, List<InDoorImage> inDoorImages, List<Notes> notes);

    void reSendTheDataAnotherTry();
}
