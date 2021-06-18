package com.asi.unicomgroupsurvey.di;


import android.content.Context;

import com.asi.unicomgroupsurvey.Views.Notes.NotesPresenter;
import com.asi.unicomgroupsurvey.Views.Sucess.SendingDataSuccessPresenter;
import com.asi.unicomgroupsurvey.Views.Survey.SurveyPresenter;
import com.asi.unicomgroupsurvey.Views.Survey.UpdateSurveyActivity;
import com.asi.unicomgroupsurvey.Views.add_rooms.AddRoomsPresenter;
import com.asi.unicomgroupsurvey.Views.comments.CommentsPresenter;
import com.asi.unicomgroupsurvey.Views.get_user_projects.GetUserProjectsPresenter;
import com.asi.unicomgroupsurvey.Views.Survey.SurveyPresenter;
import com.asi.unicomgroupsurvey.Views.in_door.InDoorPresenter;
import com.asi.unicomgroupsurvey.Views.jobs.JobsPresenter;
import com.asi.unicomgroupsurvey.Views.login.LoginPresenter;
import com.asi.unicomgroupsurvey.Views.out_door.OutDoorPresenter;
import com.asi.unicomgroupsurvey.Views.saved_offline_survey.GetSavedOfflineSurveyPresenter;
import com.asi.unicomgroupsurvey.Views.sketch.SketchPresenter;
import com.asi.unicomgroupsurvey.Views.splash.SplashPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


//this class created to put  providers for Presenters

@Module
public class PresenterModule {



    @Provides
    @Singleton
    SplashPresenter provideMainPresenter(Context context) {
        return new SplashPresenter(context);
    }

    //login by amr rabie
    @Provides
    @Singleton
    LoginPresenter provideLoginPresenter(Context context) {
        return new LoginPresenter(context);
    }

    //survey by amr rabie
    @Provides
    @Singleton
    SurveyPresenter provideSurveyPresenter(Context context) {
        return new SurveyPresenter(context);
    }

    //notes by amr rabie
    @Provides
    @Singleton
    NotesPresenter provideNotesPresenter(Context context) {
        return new NotesPresenter(context);
    }



    @Provides
    @Singleton
    GetUserProjectsPresenter provideGetUserProjectsPresenter(Context context) {
        return new GetUserProjectsPresenter(context);
    }



    @Provides
    @Singleton
    AddRoomsPresenter provideAddRoomsPresenter(Context context) {
        return new AddRoomsPresenter(context);
    }


    @Provides
    @Singleton
    JobsPresenter provideJobsPresenter(Context context) {
        return new JobsPresenter(context);
    }


    @Provides
    @Singleton
    OutDoorPresenter provideOutDoorPresenter(Context context) {
        return new OutDoorPresenter(context);
    }


    @Provides
    @Singleton
    SketchPresenter provideSketchPresenter(Context context) {
        return new SketchPresenter(context);
    }


    @Provides
    @Singleton
    CommentsPresenter provideCommentsPresenter(Context context) {
        return new CommentsPresenter(context);
    }


    @Provides
    @Singleton
    InDoorPresenter provideInDoorPresenter(Context context) {
        return new InDoorPresenter(context);
    }


    @Provides
    @Singleton
    GetSavedOfflineSurveyPresenter provideOfflineSavedPresenter(Context context) {
        return new GetSavedOfflineSurveyPresenter(context);
    }



    @Provides
    @Singleton
    SendingDataSuccessPresenter provideSendingDataSuccessPresenter(Context context) {
        return new SendingDataSuccessPresenter(context);
    }

   





}

