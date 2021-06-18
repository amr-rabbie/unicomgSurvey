package com.asi.unicomgroupsurvey.di;


import com.asi.unicomgroupsurvey.Views.Notes.NotesActivity;
import com.asi.unicomgroupsurvey.Views.Notes.NotesPresenter;
import com.asi.unicomgroupsurvey.Views.Notes.UpdateNotesActivity;
import com.asi.unicomgroupsurvey.Views.Sucess.SendingDataSuccessPresenter;
import com.asi.unicomgroupsurvey.Views.Sucess.SendingDataSucessActivity;
import com.asi.unicomgroupsurvey.Views.Survey.SurveyFragment;
import com.asi.unicomgroupsurvey.Views.Survey.SurveyPresenter;
import com.asi.unicomgroupsurvey.Views.Survey.UpdateSurveyActivity;
import com.asi.unicomgroupsurvey.Views.Survey.UpdateSurveyFragment;
import com.asi.unicomgroupsurvey.Views.add_rooms.AddRoomsPresenter;
import com.asi.unicomgroupsurvey.Views.add_rooms.RoomsActivity;
import com.asi.unicomgroupsurvey.Views.add_rooms.UpdateRoomsActivity;
import com.asi.unicomgroupsurvey.Views.comments.CommentsActivity;
import com.asi.unicomgroupsurvey.Views.comments.CommentsPresenter;
import com.asi.unicomgroupsurvey.Views.get_user_projects.GetUserProjectsActivity;
import com.asi.unicomgroupsurvey.Views.get_user_projects.GetUserProjectsPresenter;
import com.asi.unicomgroupsurvey.Views.in_door.InDoorActivity;
import com.asi.unicomgroupsurvey.Views.in_door.InDoorPresenter;
import com.asi.unicomgroupsurvey.Views.in_door.UpdatingInDoorActivity;
import com.asi.unicomgroupsurvey.Views.jobs.JobsActivity;

import com.asi.unicomgroupsurvey.Views.jobs.JobsPresenter;
import com.asi.unicomgroupsurvey.Views.jobs.UpdatingJobsActivity;
import com.asi.unicomgroupsurvey.Views.out_door.OutDoorActivity;
import com.asi.unicomgroupsurvey.Views.out_door.OutDoorPresenter;
import com.asi.unicomgroupsurvey.Views.out_door.UpdatingOutDoorActivity;
import com.asi.unicomgroupsurvey.Views.saved_offline_survey.GetOfflineSavedSurveyActivity;
import com.asi.unicomgroupsurvey.Views.saved_offline_survey.GetSavedOfflineSurveyPresenter;
import com.asi.unicomgroupsurvey.Views.sketch.SketchActivity;
import com.asi.unicomgroupsurvey.Views.sketch.SketchPresenter;
import com.asi.unicomgroupsurvey.Views.sketch.UpdatingSketchActivity;
import com.asi.unicomgroupsurvey.Views.splash.SplashPresenter;
import com.asi.unicomgroupsurvey.Views.splash.SplashActivity;
import com.asi.unicomgroupsurvey.Views.login.LoginActivity;
import com.asi.unicomgroupsurvey.Views.login.LoginPresenter;

import javax.inject.Singleton;
import dagger.Component;

// this class created for register who need inject
@Singleton
@Component(modules = {AppModule.class, NetworkModule.class,PresenterModule.class})
public interface AppComponent {

    //register splash activity it will need objects for injection
    void inject(SplashActivity splashActivity);
    void inject(SplashPresenter mainPresenter);
    void inject(UpdatingInDoorActivity updatingInDoorActivity);


    //login By Amr Rabie
    void inject(LoginActivity loginActivity);
    void inject(LoginPresenter loginPresenter);

    void inject(GetUserProjectsActivity getUserProjectsActivity);
    void inject(GetUserProjectsPresenter getUserProjectsPresenter);

    //survey By Amr Rabie
    void inject(SurveyFragment surveyActivity);
    //void inject(SurveyActivity surveyActivity);
    void inject(SurveyPresenter surveyPresenter);

    //notes By Amr Rabie
    void inject(NotesActivity notesActivity);
    void inject(NotesPresenter notesPresenter);


    //survey By  Mohammed Elamary
    void inject(JobsActivity jobsActivity);
    void inject(JobsPresenter jobsPresenter);
    void inject(UpdatingJobsActivity updatingJobsActivity);


    void inject(RoomsActivity roomsActivity);
    void inject(AddRoomsPresenter addRoomsPresenter);


    void inject(OutDoorActivity outDoorActivity);
    void inject(OutDoorPresenter outDoorPresenter);
    void inject(UpdatingOutDoorActivity  updatingOutDoorActivity);



    //survey By  Mohammed Elamary
    void inject(SketchActivity sketchActivity);
    void inject(SketchPresenter sketchPresenter);
    void inject(UpdatingSketchActivity updatingSketchActivity);


    //survey By  Mohammed Elamary
    void inject(CommentsActivity commentsActivity);
    void inject(CommentsPresenter commentsPresenter);

    void inject(InDoorActivity inDoorActivity);
    void inject(InDoorPresenter inDoorPresenter);

    void inject(GetSavedOfflineSurveyPresenter getSavedOfflineSurveyPresenter);

    void inject(UpdateSurveyFragment updateSurveyFragment);

    void inject(GetOfflineSavedSurveyActivity getOfflineSavedSurveyActivity);

    void inject(SendingDataSucessActivity sendingDataSucessActivity);
    void inject (SendingDataSuccessPresenter sendingDataSuccessPresenter);



    void inject(UpdateRoomsActivity updateRoomsActivity);


    void inject(UpdateNotesActivity updateNotesActivity);

    //void inject(UpdateSurveyActivity updateSurveyActivity);

}
