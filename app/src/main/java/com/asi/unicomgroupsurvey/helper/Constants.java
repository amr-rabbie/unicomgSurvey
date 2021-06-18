package com.asi.unicomgroupsurvey.helper;


import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

//this class created for holding  all constant for all application
public class Constants {
    public static final String ITEM_KEY = "item";
    public static final String SHARED_VIEW_IMAGE_KEY = "image" ;
    public static final String MyPREFERENCES = "PREF" ;


    public static final String SHARED_PREFERENCES_PROJECT = "PREF_PROJECT" ;

    public static final String SHARED_PREFERENCES_PROJECT_NAME = "PREF_PROJECT_NAME" ;


    public static final String SHARED_PREFERENCES_UPDATE_OFFICE_ID = "office_id_preference" ;






    //login amr rabie

    public  static  void saveLoginData(String userId, String uname ,String token ,Context context )
    {
        SharedPreferences.Editor editor = context.getSharedPreferences(MyPREFERENCES,MODE_PRIVATE).edit();
        editor.putString("userId", userId);
        editor.putString("uname", uname);
        editor.putString("token", token);
        editor.apply();

    }

    public  static  void saveUnPw(String un, String pw ,Context context )
    {
        SharedPreferences.Editor editor = context.getSharedPreferences(MyPREFERENCES,MODE_PRIVATE).edit();
        editor.putString("un", un);
        editor.putString("pw", pw);
        editor.apply();
    }

    public  static  String getUn( Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        return prefs.getString("un", "");
    }

    public  static  String getPw( Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        return prefs.getString("pw", "");
    }

    public  static  void savelanglat(String lang, String lat ,Context context )
    {
        SharedPreferences.Editor editor = context.getSharedPreferences(MyPREFERENCES,MODE_PRIVATE).edit();
        editor.putString("lang", lang);
        editor.putString("lat", lat);
        editor.apply();
    }

    public  static  String getlang( Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        return prefs.getString("lang", "");
    }

    public  static  String getlat( Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        return prefs.getString("lat", "");
    }

    public  static  String getuserId( Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        return prefs.getString("userId", "");
    }

    public  static  String getuserName( Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        return prefs.getString("uname", "");
    }

    public  static  String getuserToken( Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        return prefs.getString("token", "");
    }

    public  static  void saveProjectId(String projectId , Context context )
    {
        SharedPreferences.Editor editor = context.getSharedPreferences(SHARED_PREFERENCES_PROJECT,MODE_PRIVATE).edit();
        editor.putString("project_id", projectId);
        editor.apply();

    }

    public  static  void saveProjectName(String projectName , Context context )
    {
        SharedPreferences.Editor editor = context.getSharedPreferences(SHARED_PREFERENCES_PROJECT_NAME,MODE_PRIVATE).edit();
        editor.putString("project_name", projectName);
        editor.apply();

    }

    public  static  String getProjectName( Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences(SHARED_PREFERENCES_PROJECT_NAME, MODE_PRIVATE);
        return prefs.getString("project_name", "");
    }

    public  static  String getProjectId( Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences(SHARED_PREFERENCES_PROJECT, MODE_PRIVATE);
        return prefs.getString("project_id", "");
    }

    //amr rabie

    public  static  void saveOFFiceid(String officeid,Context context )
    {
        SharedPreferences.Editor editor = context.getSharedPreferences(MyPREFERENCES,MODE_PRIVATE).edit();
        editor.putString("officeid", officeid);
        editor.apply();

    }

    public  static  void saveUpdatedOfficeId(String officeId,Context context )
    {
        SharedPreferences.Editor editor = context.getSharedPreferences(SHARED_PREFERENCES_UPDATE_OFFICE_ID,MODE_PRIVATE).edit();
        editor.putString("office_id", officeId);
        editor.apply();

    }

    public  static  String getUpdatedOfficeId( Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences(SHARED_PREFERENCES_UPDATE_OFFICE_ID, MODE_PRIVATE);
        return prefs.getString("office_id", "");
    }

    public  static  void saveVisitOfficeId(String officeId,Context context )
    {
        SharedPreferences.Editor editor = context.getSharedPreferences(SHARED_PREFERENCES_UPDATE_OFFICE_ID,MODE_PRIVATE).edit();
        editor.putString("office_visit_id", officeId);
        editor.apply();

    }

    public  static  String getVisitOfficeId( Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences(SHARED_PREFERENCES_UPDATE_OFFICE_ID, MODE_PRIVATE);
        return prefs.getString("office_visit_id", "");
    }


    public  static  String getOfficeId( Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        return prefs.getString("officeid", "");
    }

    public  static  void savenotes(String notes , String loc,Context context )
    {
        SharedPreferences.Editor editor = context.getSharedPreferences(SHARED_PREFERENCES_UPDATE_OFFICE_ID,MODE_PRIVATE).edit();
        editor.putString("notes", notes);
        editor.putString("loc", loc);
        editor.apply();

    }

    public  static  String getnotesstring( Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences(SHARED_PREFERENCES_UPDATE_OFFICE_ID, MODE_PRIVATE);
        return prefs.getString("notes", "");
    }

    public  static  String getlocstring( Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences(SHARED_PREFERENCES_UPDATE_OFFICE_ID, MODE_PRIVATE);
        return prefs.getString("loc", "");
    }





}
