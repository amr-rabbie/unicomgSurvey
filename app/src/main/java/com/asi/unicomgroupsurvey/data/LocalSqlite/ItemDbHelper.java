package com.asi.unicomgroupsurvey.data.LocalSqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.asi.unicomgroupsurvey.data.LocalSqlite.Survey.SurveyContract;
import com.asi.unicomgroupsurvey.data.LocalSqlite.cities.CityContract;
import com.asi.unicomgroupsurvey.data.LocalSqlite.districts.DistrictContract;
import com.asi.unicomgroupsurvey.data.LocalSqlite.governors.GovernorContract;
import com.asi.unicomgroupsurvey.data.LocalSqlite.in_door.InDoorContract;
import com.asi.unicomgroupsurvey.data.LocalSqlite.jobs.JobContract;
import com.asi.unicomgroupsurvey.data.LocalSqlite.notes.NotesContract;
import com.asi.unicomgroupsurvey.data.LocalSqlite.offices.OfficesContract;
import com.asi.unicomgroupsurvey.data.LocalSqlite.out_door.OutDoorContract;
import com.asi.unicomgroupsurvey.data.LocalSqlite.positions.PositionsContract;
import com.asi.unicomgroupsurvey.data.LocalSqlite.projects.ProjectsContract;
import com.asi.unicomgroupsurvey.data.LocalSqlite.rooms.RoomImagesContract;
import com.asi.unicomgroupsurvey.data.LocalSqlite.rooms.RoomsContract;
import com.asi.unicomgroupsurvey.data.LocalSqlite.sketch.SketchContract;
import com.asi.unicomgroupsurvey.data.models.Surveys.Survey;
import com.asi.unicomgroupsurvey.data.models.getCities.City;
import com.asi.unicomgroupsurvey.data.models.getDistricts.District;
import com.asi.unicomgroupsurvey.data.models.getGovernors.Governor;
import com.asi.unicomgroupsurvey.data.models.get_Notes.Notes;
import com.asi.unicomgroupsurvey.data.models.get_user_projects.UserProjectsDetails;
import com.asi.unicomgroupsurvey.data.models.in_door.InDoorImage;
import com.asi.unicomgroupsurvey.data.models.jops.Job;
import com.asi.unicomgroupsurvey.data.models.offices.OfficeResponseDetails;
import com.asi.unicomgroupsurvey.data.models.out_door.OutDoorImage;
import com.asi.unicomgroupsurvey.data.models.positions.PositionResponseDetails;
import com.asi.unicomgroupsurvey.data.models.rooms.RoomDetails;
import com.asi.unicomgroupsurvey.data.models.rooms.RoomImage;
import com.asi.unicomgroupsurvey.data.models.sketch.Sketch;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import static com.asi.unicomgroupsurvey.data.LocalSqlite.Survey.SurveyContract.ItemEntry.SURVEy_TABLE_NAME;
import static com.asi.unicomgroupsurvey.data.LocalSqlite.Survey.SurveyContract.ItemEntry.gov_id;
import static com.asi.unicomgroupsurvey.data.LocalSqlite.Survey.SurveyContract.ItemEntry.gov_name;
import static com.asi.unicomgroupsurvey.data.LocalSqlite.Survey.SurveyContract.ItemEntry.office_visit;
import static com.asi.unicomgroupsurvey.data.LocalSqlite.cities.CityContract.ItemEntry.CITY_GOVE_ID;
import static com.asi.unicomgroupsurvey.data.LocalSqlite.cities.CityContract.ItemEntry.CITY_ID;
import static com.asi.unicomgroupsurvey.data.LocalSqlite.cities.CityContract.ItemEntry.CITY_NAME;
import static com.asi.unicomgroupsurvey.data.LocalSqlite.cities.CityContract.ItemEntry.City_TABLE_NAME;
import static com.asi.unicomgroupsurvey.data.LocalSqlite.districts.DistrictContract.ItemEntry.DISTRICT_CITY_ID;
import static com.asi.unicomgroupsurvey.data.LocalSqlite.districts.DistrictContract.ItemEntry.DISTRICT_ID;
import static com.asi.unicomgroupsurvey.data.LocalSqlite.districts.DistrictContract.ItemEntry.DISTRICT_NAME;
import static com.asi.unicomgroupsurvey.data.LocalSqlite.districts.DistrictContract.ItemEntry.DISTRICT_TABLE_NAME;
import static com.asi.unicomgroupsurvey.data.LocalSqlite.governors.GovernorContract.ItemEntry.GOVERNOR_TABLE_NAME;
import static com.asi.unicomgroupsurvey.data.LocalSqlite.governors.GovernorContract.ItemEntry.GOV_ID;
import static com.asi.unicomgroupsurvey.data.LocalSqlite.governors.GovernorContract.ItemEntry.GOV_NAME;
import static com.asi.unicomgroupsurvey.data.LocalSqlite.in_door.InDoorContract.ItemEntry.INDOOR_TABLE_NAME;
import static com.asi.unicomgroupsurvey.data.LocalSqlite.jobs.JobContract.ItemEntry.JOB_ID;
import static com.asi.unicomgroupsurvey.data.LocalSqlite.jobs.JobContract.ItemEntry.JOB_TABLE_NAME;
import static com.asi.unicomgroupsurvey.data.LocalSqlite.jobs.JobContract.ItemEntry.OFFICE_ID;
import static com.asi.unicomgroupsurvey.data.LocalSqlite.jobs.JobContract.ItemEntry.PROJECT_ID;
import static com.asi.unicomgroupsurvey.data.LocalSqlite.jobs.JobContract.ItemEntry.USER_ID;
import static com.asi.unicomgroupsurvey.data.LocalSqlite.notes.NotesContract.ItemEntry.Notes_TABLE_NAME;
import static com.asi.unicomgroupsurvey.data.LocalSqlite.offices.OfficesContract.ItemEntry.OFFICE_TABLE_NAME;
import static com.asi.unicomgroupsurvey.data.LocalSqlite.offices.OfficesContract.ItemEntry.city_id;
import static com.asi.unicomgroupsurvey.data.LocalSqlite.offices.OfficesContract.ItemEntry.district_id;
import static com.asi.unicomgroupsurvey.data.LocalSqlite.offices.OfficesContract.ItemEntry.office_id;
import static com.asi.unicomgroupsurvey.data.LocalSqlite.offices.OfficesContract.ItemEntry.office_name;
import static com.asi.unicomgroupsurvey.data.LocalSqlite.out_door.OutDoorContract.ItemEntry.OUTDOOR_TABLE_NAME;
import static com.asi.unicomgroupsurvey.data.LocalSqlite.positions.PositionsContract.ItemEntry.POSITION_TABLE_NAME;
import static com.asi.unicomgroupsurvey.data.LocalSqlite.projects.ProjectsContract.ItemEntry.PROJECT_TABLE_NAME;
import static com.asi.unicomgroupsurvey.data.LocalSqlite.rooms.RoomImagesContract.ItemEntry.ROOM_IMAGE_TABLE_NAME;
import static com.asi.unicomgroupsurvey.data.LocalSqlite.rooms.RoomsContract.ItemEntry.ROOM_TABLE_NAME;
import static com.asi.unicomgroupsurvey.data.LocalSqlite.rooms.RoomsContract.ItemEntry.is_mutual;
import static com.asi.unicomgroupsurvey.data.LocalSqlite.sketch.SketchContract.ItemEntry.SKETCH_TABLE_NAME;

// for functions
public class ItemDbHelper  extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "SurveyDB.db";
    private static final int VERSION = 1;
    public ItemDbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }
    @Override

    public void onCreate(SQLiteDatabase db) {

        // Create items table (careful to follow SQL formatting rules)
        final String CREATE_GOVERNOR_TABLE = "CREATE TABLE "  + GOVERNOR_TABLE_NAME + " (" +
                GovernorContract.ItemEntry._ID                + " INTEGER PRIMARY KEY, " +
                GOV_ID + " TEXT , " +
                GOV_NAME + " TEXT );" ;

        db.execSQL(CREATE_GOVERNOR_TABLE);

        final String CREATE_CITY_TABLE = "CREATE TABLE " + City_TABLE_NAME + " (" +
                CityContract.ItemEntry._ID + " INTEGER PRIMARY KEY, " +
                CITY_ID + " TEXT , " +
                CITY_GOVE_ID + " TEXT , " +
                CITY_NAME + " TEXT );";

        db.execSQL(CREATE_CITY_TABLE);


        final String CREATE_DISTRICT_TABLE = "CREATE TABLE " + DISTRICT_TABLE_NAME + " (" +
                DistrictContract.ItemEntry._ID + " INTEGER PRIMARY KEY, " +
                DISTRICT_ID + " TEXT , " +
                DISTRICT_NAME + " TEXT , " +
                DISTRICT_CITY_ID + " TEXT );";

        db.execSQL(CREATE_DISTRICT_TABLE);



        // TODO Position Logic
        final String CREATE_POSITION_TABLE = "CREATE TABLE " + POSITION_TABLE_NAME + " (" +
                PositionsContract.ItemEntry._ID + " INTEGER PRIMARY KEY, " +
                PositionsContract.ItemEntry.position_id + " TEXT , " +
                PositionsContract.ItemEntry.project_id + " TEXT , " +
                PositionsContract.ItemEntry.position_name + " TEXT );";

        db.execSQL(CREATE_POSITION_TABLE);


        // TODO Office Logic
        final String CREATE_OFFICE_TABLE = "CREATE TABLE " + OFFICE_TABLE_NAME + " (" +
                OfficesContract.ItemEntry._ID + " INTEGER PRIMARY KEY, " +
                office_id + " TEXT , " +
                district_id + " TEXT , " +
                office_name + " TEXT ," +
                office_visit + " TEXT ," +
                city_id + " TEXT ," +
                gov_id + " TEXT ," +
                OfficesContract.ItemEntry.project_id + " TEXT );";

        db.execSQL(CREATE_OFFICE_TABLE);


        // TODO Project Logic

        final String CREATE_PROJECT_TABLE = "CREATE TABLE " + PROJECT_TABLE_NAME + " (" +
                ProjectsContract.ItemEntry._ID + " INTEGER PRIMARY KEY, " +
                ProjectsContract.ItemEntry.user_id + " TEXT , " +
                ProjectsContract.ItemEntry.project_id + " TEXT , " +
                ProjectsContract.ItemEntry.project_name + " TEXT );";

        db.execSQL(CREATE_PROJECT_TABLE);

        final String CREATE_JOB_TABLE = "CREATE TABLE " + JOB_TABLE_NAME + " (" +
                JobContract.ItemEntry._ID + " INTEGER PRIMARY KEY, " +
                JobContract.ItemEntry.JOB_ID + " TEXT , " +
                JobContract.ItemEntry.JOB_NAME + " TEXT , " +
                JobContract.ItemEntry.COUNT + " TEXT , " +
                JobContract.ItemEntry.USER_ID + " TEXT , " +
                PROJECT_ID + " TEXT , " +
                JobContract.ItemEntry.OFFICE_ID + " TEXT , " +
                JobContract.ItemEntry.NOTE + " TEXT );";
        db.execSQL(CREATE_JOB_TABLE);


        //TODO Room Table
        final String CREATE_ROOM_TABLE = "CREATE TABLE " + ROOM_TABLE_NAME + " (" +
                RoomsContract.ItemEntry._ID + " INTEGER PRIMARY KEY, " +
                RoomsContract.ItemEntry.user_id + " TEXT , " +
                RoomsContract.ItemEntry.project_id + " TEXT , " +
                RoomsContract.ItemEntry.office_id + " TEXT , " +
                RoomsContract.ItemEntry.room_name + " TEXT , " +
                RoomsContract.ItemEntry.room_count + " TEXT , " +
                RoomsContract.ItemEntry.is_mutual + " TEXT , " +
                RoomsContract.ItemEntry.room_furniture + " TEXT );";

        db.execSQL(CREATE_ROOM_TABLE);

        //Survey Table By Amr Rabie

        // Create items table (careful to follow SQL formatting rules)
        final String CREATE_SURVEY_TABLE = "CREATE TABLE "  + SURVEy_TABLE_NAME + " (" +
                SurveyContract.ItemEntry._ID                + " INTEGER PRIMARY KEY, " +
                gov_id + " TEXT , " +
                gov_name + " TEXT , " +
                SurveyContract.ItemEntry.city_id + " TEXT , " +
                SurveyContract.ItemEntry.city_name + "  TEXT , " +
                SurveyContract.ItemEntry.district_id + "  TEXT , " +
                SurveyContract.ItemEntry.district_name + " TEXT , " +
                SurveyContract.ItemEntry.address + " TEXT , " +
                SurveyContract.ItemEntry.phone + " TEXT , " +
                SurveyContract.ItemEntry.hasInternet +  " TEXT , " +
                SurveyContract.ItemEntry.isNetwork + " TEXT  , " +
                SurveyContract.ItemEntry.internetSeed + " TEXT  , " +
                SurveyContract.ItemEntry.office_id + " TEXT , " +
                SurveyContract.ItemEntry.office_name + " TEXT , " +
                office_visit + " TEXT  , " +
                SurveyContract.ItemEntry.shiftType + " TEXT  , " +
                SurveyContract.ItemEntry.OwnerShipType + " TEXT  , " +
                SurveyContract.ItemEntry.morning_shift_from + " TEXT  , " +
                SurveyContract.ItemEntry.morning_shift_to + " TEXT  , " +
                SurveyContract.ItemEntry.evening_shift_from + " TEXT  , " +
                SurveyContract.ItemEntry.evening_shift_to + " TEXT  , " +
                SurveyContract.ItemEntry.computer_count + " TEXT , " +
                SurveyContract.ItemEntry.computer_notes + " TEXT  , " +
                SurveyContract.ItemEntry.printers_count + " TEXT , " +
                SurveyContract.ItemEntry.printers_notes + " TEXT  , " +
                SurveyContract.ItemEntry.scanners_count + " TEXT , " +
                SurveyContract.ItemEntry.scanners_notes + " TEXT  , " +
                SurveyContract.ItemEntry.other_city +  " TEXT  , " +
                SurveyContract.ItemEntry.other_district + " TEXT  , " +
                SurveyContract.ItemEntry.user_id + " TEXT , " +

                SurveyContract.ItemEntry.net_inside + " TEXT  , " +
                SurveyContract.ItemEntry.net_outside + " TEXT  , " +
                SurveyContract.ItemEntry.ins_out_notes +  " TEXT  , " +
                SurveyContract.ItemEntry.others_macs + " TEXT  , " +
                SurveyContract.ItemEntry.others_macs_notes + " TEXT , " +
                SurveyContract.ItemEntry.two_offices +  " TEXT  , " +
                SurveyContract.ItemEntry.other_office_id + " TEXT  , " +
                SurveyContract.ItemEntry.other_office_name + " TEXT , " +

                SurveyContract.ItemEntry.is_arabic +  " TEXT  , " +
                SurveyContract.ItemEntry.is_kurdish + " TEXT  , " +
                SurveyContract.ItemEntry.both + " TEXT , " +

                SurveyContract.ItemEntry.project_id + " TEXT );";

        db.execSQL(CREATE_SURVEY_TABLE);


        //Notes Table By Amr Rabie

        // Create items table (careful to follow SQL formatting rules)
        final String CREATE_NOTES_TABLE = "CREATE TABLE "  + Notes_TABLE_NAME + " (" +
                NotesContract.ItemEntry._ID                + " INTEGER PRIMARY KEY, " +
                NotesContract.ItemEntry.notes +  " TEXT  , " +
                NotesContract.ItemEntry.location + " TEXT  , " +
                NotesContract.ItemEntry.office_id + " TEXT  , " +
                NotesContract.ItemEntry.user_id + " TEXT  , " +
                NotesContract.ItemEntry.visit_date + " TEXT  , " +

                NotesContract.ItemEntry.electricty +  " TEXT  , " +
                NotesContract.ItemEntry.water + " TEXT  , " +
                NotesContract.ItemEntry.bill_end + " TEXT  , " +
                NotesContract.ItemEntry.colunms + " TEXT  , " +
                NotesContract.ItemEntry.doors + " TEXT  , " +
                NotesContract.ItemEntry.secondary + " TEXT  , " +
                NotesContract.ItemEntry.camersnet + " TEXT  , " +

                NotesContract.ItemEntry.project_id + " TEXT  );";

        db.execSQL(CREATE_NOTES_TABLE);


        final String CREATE_OUT_DOOR_TABLE = "CREATE TABLE " + OUTDOOR_TABLE_NAME + " (" +
                OutDoorContract.ItemEntry._ID + " INTEGER PRIMARY KEY, " +
                OutDoorContract.ItemEntry.user_id + " TEXT  , " +
                OutDoorContract.ItemEntry.project_id + " TEXT  , " +
                OutDoorContract.ItemEntry.office_id + " TEXT  , " +
                OutDoorContract.ItemEntry.minimum_status + "  INTEGER DEFAULT 0  , " +
                OutDoorContract.ItemEntry.out_door_image_desc + " TEXT  , " +
                OutDoorContract.ItemEntry.out_door_image + " BLOB);";

        db.execSQL(CREATE_OUT_DOOR_TABLE);

        final String CREATE_IN_DOOR_TABLE = "CREATE TABLE " + INDOOR_TABLE_NAME + " (" +
                InDoorContract.ItemEntry._ID + " INTEGER PRIMARY KEY, " +
                InDoorContract.ItemEntry.user_id + " TEXT , " +
                InDoorContract.ItemEntry.project_id + " TEXT , " +
                InDoorContract.ItemEntry.office_id + " TEXT , " +
                InDoorContract.ItemEntry.minimum_status + " INTEGER DEFAULT 0 , " +
                InDoorContract.ItemEntry.indoor_door_image_desc + " TEXT , " +
                InDoorContract.ItemEntry.indoor_door_image + " BLOB);";

        db.execSQL(CREATE_IN_DOOR_TABLE);

        //TODO Room Table
        final String CREATE_SKETCH_TABLE = "CREATE TABLE " + SKETCH_TABLE_NAME + " (" +
                SketchContract.ItemEntry._ID + " INTEGER PRIMARY KEY, " +
                SketchContract.ItemEntry.user_id + " TEXT , " +
                SketchContract.ItemEntry.office_id + " TEXT , " +
                SketchContract.ItemEntry.project_id + " TEXT , " +
                SketchContract.ItemEntry.minimum_status + " INTEGER DEFAULT 0 , " +
                SketchContract.ItemEntry.sketch_image_desc + " TEXT DEFAULT '' , " +
                SketchContract.ItemEntry.sketch_image + " BLOB );";

        db.execSQL(CREATE_SKETCH_TABLE);


        final String CREATE_ROOM_IMAGE_TABLE = "CREATE TABLE " + ROOM_IMAGE_TABLE_NAME + " (" +
                RoomImagesContract.ItemEntry._ID + " INTEGER PRIMARY KEY, " +
                RoomImagesContract.ItemEntry.user_id + " TEXT , " +
                RoomImagesContract.ItemEntry.project_id + " TEXT , " +
                RoomImagesContract.ItemEntry.office_id + " TEXT , " +
                RoomImagesContract.ItemEntry.room_id + " TEXT , " +
                RoomImagesContract.ItemEntry.minimum_status + " INTEGER DEFAULT 0 , " +
                RoomImagesContract.ItemEntry.image_bitmap + " BLOB);";

        db.execSQL(CREATE_ROOM_IMAGE_TABLE);
    }
    // get survey table data amr rabie

    public List<Survey>  getSurveys() {
        List<Survey>  surveyss = new ArrayList<>();
        // Get access to underlying database (read-only for query)
        final SQLiteDatabase db = this.getReadableDatabase();
        Cursor retCursor;
        retCursor =  db.query(SURVEy_TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);


        if(retCursor!=null){
            while (retCursor.moveToNext()) {
                Survey  survey = new Survey();
                // governor.setId(retCursor.getInt(retCursor.getColumnIndex(ItemContract.ItemEntry._ID)));
                survey.setGov_id( retCursor.getString(retCursor.getColumnIndex("gov_id")));
                survey.setGov_name( retCursor.getString(retCursor.getColumnIndex("gov_name")));
                survey.setCity_id( retCursor.getString(retCursor.getColumnIndex("city_id")));
                survey.setCity_name( retCursor.getString(retCursor.getColumnIndex("city_name")));
                survey.setDistrict_id( retCursor.getString(retCursor.getColumnIndex("district_id")));
                survey.setDistrict_name( retCursor.getString(retCursor.getColumnIndex("district_name")));
                survey.setAddress( retCursor.getString(retCursor.getColumnIndex("address")));
                survey.setPhone( retCursor.getString(retCursor.getColumnIndex("phone")));
                survey.setHasInternet( retCursor.getString(retCursor.getColumnIndex("hasInternet")));
                survey.setIsNetwork( retCursor.getString(retCursor.getColumnIndex("isNetwork")));
                survey.setInternetSeed( retCursor.getString(retCursor.getColumnIndex("internetSeed")));
                survey.setOffice_id( retCursor.getString(retCursor.getColumnIndex("office_id")));
                survey.setOffice_name( retCursor.getString(retCursor.getColumnIndex("office_name")));
                survey.setOffice_visit( retCursor.getString(retCursor.getColumnIndex("office_visit")));
                survey.setShiftType( retCursor.getString(retCursor.getColumnIndex("shiftType")));
                survey.setOwnerShipType( retCursor.getString(retCursor.getColumnIndex("OwnerShipType")));
                survey.setMorning_shift_from( retCursor.getString(retCursor.getColumnIndex("morning_shift_from")));
                survey.setMorning_shift_to( retCursor.getString(retCursor.getColumnIndex("morning_shift_to")));
                survey.setEvening_shift_from( retCursor.getString(retCursor.getColumnIndex("evening_shift_from")));
                survey.setEvening_shift_to( retCursor.getString(retCursor.getColumnIndex("evening_shift_to")));
                survey.setComputer_count( retCursor.getString(retCursor.getColumnIndex("computer_count")));
                survey.setComputer_notes( retCursor.getString(retCursor.getColumnIndex("computer_notes")));
                survey.setPrinters_count( retCursor.getString(retCursor.getColumnIndex("printers_count")));
                survey.setPrinters_notes( retCursor.getString(retCursor.getColumnIndex("printers_notes")));
                survey.setScanners_count( retCursor.getString(retCursor.getColumnIndex("scanners_count")));
                survey.setScanners_notes( retCursor.getString(retCursor.getColumnIndex("scanners_notes")));
                survey.setOther_city( retCursor.getString(retCursor.getColumnIndex("other_city")));
                survey.setOther_district( retCursor.getString(retCursor.getColumnIndex("other_district")));
                survey.setUser_id( retCursor.getString(retCursor.getColumnIndex("user_id")));
                survey.setProject_id( retCursor.getString(retCursor.getColumnIndex("project_id")));

                survey.setNet_inside( retCursor.getString(retCursor.getColumnIndex("net_inside")));
                survey.setNet_outside( retCursor.getString(retCursor.getColumnIndex("net_outside")));
                survey.setIns_out_notes( retCursor.getString(retCursor.getColumnIndex("ins_out_notes")));
                survey.setOthers_macs( retCursor.getString(retCursor.getColumnIndex("others_macs")));
                survey.setOthers_macs_notes( retCursor.getString(retCursor.getColumnIndex("others_macs_notes")));
                survey.setTwo_offices( retCursor.getString(retCursor.getColumnIndex("two_offices")));
                survey.setOther_office_id( retCursor.getString(retCursor.getColumnIndex("other_office_id")));
                survey.setOther_office_name( retCursor.getString(retCursor.getColumnIndex("other_office_name")));

                survey.setIs_arabic( retCursor.getString(retCursor.getColumnIndex("is_arabic")));
                survey.setIs_kurdish( retCursor.getString(retCursor.getColumnIndex("is_kurdish")));
                survey.setBoth( retCursor.getString(retCursor.getColumnIndex("both")));

                surveyss.add(survey);
            }
        }

        return  surveyss;
    }
    public Survey  getOneSurveyForUserForLastScreenLogic(String userid, String projectId , String officeId) {
        Survey  surveyss = new Survey();
        final SQLiteDatabase db = this.getReadableDatabase();
        Cursor retCursor;
        retCursor =  db.query(SURVEy_TABLE_NAME,
                null,
                SurveyContract.ItemEntry.user_id +" = "+ ""+userid +" and " +SurveyContract.ItemEntry.project_id + " = "+projectId +" and " +SurveyContract.ItemEntry.office_id +" = "+""+officeId ,
                null,
                null,
                null,
                null);
        if(retCursor!=null){
            while (retCursor.moveToNext()) {
                Survey  survey = new Survey();
                // governor.setId(retCursor.getInt(retCursor.getColumnIndex(ItemContract.ItemEntry._ID)));
                survey.setGov_id( retCursor.getString(retCursor.getColumnIndex("gov_id")));
                survey.setGov_id( retCursor.getString(retCursor.getColumnIndex("gov_id")));
                survey.setGov_name( retCursor.getString(retCursor.getColumnIndex("gov_name")));
                survey.setCity_id( retCursor.getString(retCursor.getColumnIndex("city_id")));
                survey.setCity_name( retCursor.getString(retCursor.getColumnIndex("city_name")));
                survey.setDistrict_id( retCursor.getString(retCursor.getColumnIndex("district_id")));
                survey.setDistrict_name( retCursor.getString(retCursor.getColumnIndex("district_name")));
                survey.setAddress( retCursor.getString(retCursor.getColumnIndex("address")));
                survey.setPhone( retCursor.getString(retCursor.getColumnIndex("phone")));
                survey.setHasInternet( retCursor.getString(retCursor.getColumnIndex("hasInternet")));
                survey.setIsNetwork( retCursor.getString(retCursor.getColumnIndex("isNetwork")));
                survey.setInternetSeed( retCursor.getString(retCursor.getColumnIndex("internetSeed")));
                survey.setOffice_id( retCursor.getString(retCursor.getColumnIndex("office_id")));
                survey.setOffice_name( retCursor.getString(retCursor.getColumnIndex("office_name")));
                survey.setOffice_visit( retCursor.getString(retCursor.getColumnIndex("office_visit")));
                survey.setShiftType( retCursor.getString(retCursor.getColumnIndex("shiftType")));
                survey.setOwnerShipType( retCursor.getString(retCursor.getColumnIndex("OwnerShipType")));
                survey.setMorning_shift_from( retCursor.getString(retCursor.getColumnIndex("morning_shift_from")));
                survey.setMorning_shift_to( retCursor.getString(retCursor.getColumnIndex("morning_shift_to")));
                survey.setEvening_shift_from( retCursor.getString(retCursor.getColumnIndex("evening_shift_from")));
                survey.setEvening_shift_to( retCursor.getString(retCursor.getColumnIndex("evening_shift_to")));
                survey.setComputer_count( retCursor.getString(retCursor.getColumnIndex("computer_count")));
                survey.setComputer_notes( retCursor.getString(retCursor.getColumnIndex("computer_notes")));
                survey.setPrinters_count( retCursor.getString(retCursor.getColumnIndex("printers_count")));
                survey.setPrinters_notes( retCursor.getString(retCursor.getColumnIndex("printers_notes")));
                survey.setScanners_count( retCursor.getString(retCursor.getColumnIndex("scanners_count")));
                survey.setScanners_notes( retCursor.getString(retCursor.getColumnIndex("scanners_notes")));
                survey.setOther_city( retCursor.getString(retCursor.getColumnIndex("other_city")));
                survey.setOther_district( retCursor.getString(retCursor.getColumnIndex("other_district")));
                survey.setUser_id( retCursor.getString(retCursor.getColumnIndex("user_id")));
                survey.setProject_id( retCursor.getString(retCursor.getColumnIndex("project_id")));

                survey.setNet_inside( retCursor.getString(retCursor.getColumnIndex("net_inside")));
                survey.setNet_outside( retCursor.getString(retCursor.getColumnIndex("net_outside")));
                survey.setIns_out_notes( retCursor.getString(retCursor.getColumnIndex("ins_out_notes")));
                survey.setOthers_macs( retCursor.getString(retCursor.getColumnIndex("others_macs")));
                survey.setOthers_macs_notes( retCursor.getString(retCursor.getColumnIndex("others_macs_notes")));
                survey.setTwo_offices( retCursor.getString(retCursor.getColumnIndex("two_offices")));
                survey.setOther_office_id( retCursor.getString(retCursor.getColumnIndex("other_office_id")));
                survey.setOther_office_name( retCursor.getString(retCursor.getColumnIndex("other_office_name")));

                survey.setIs_arabic( retCursor.getString(retCursor.getColumnIndex("is_arabic")));
                survey.setIs_kurdish( retCursor.getString(retCursor.getColumnIndex("is_kurdish")));
                survey.setBoth( retCursor.getString(retCursor.getColumnIndex("both")));


                surveyss = survey ;

            }
        }


        return  surveyss;
    }

    public List<Survey>  getSurveyForUser(String userid, String projectId) {


        List<Survey>  surveyss = new ArrayList<>();
        // Get access to underlying database (read-only for query)
        final SQLiteDatabase db = this.getReadableDatabase();

        Cursor retCursor;
        retCursor =  db.query(SURVEy_TABLE_NAME,
                null,
                SurveyContract.ItemEntry.user_id +" = "+ ""+userid +" and "
                        +SurveyContract.ItemEntry.project_id + " = "+projectId ,
                null,
                null,
                null,
                null);


        if(retCursor!=null){
            while (retCursor.moveToNext()) {
                Survey  survey = new Survey();
                // governor.setId(retCursor.getInt(retCursor.getColumnIndex(ItemContract.ItemEntry._ID)));
                survey.setGov_id( retCursor.getString(retCursor.getColumnIndex("gov_id")));
                survey.setGov_name( retCursor.getString(retCursor.getColumnIndex("gov_name")));
                survey.setCity_id( retCursor.getString(retCursor.getColumnIndex("city_id")));
                survey.setCity_name( retCursor.getString(retCursor.getColumnIndex("city_name")));
                survey.setDistrict_id( retCursor.getString(retCursor.getColumnIndex("district_id")));
                survey.setDistrict_name( retCursor.getString(retCursor.getColumnIndex("district_name")));
                survey.setAddress( retCursor.getString(retCursor.getColumnIndex("address")));
                survey.setPhone( retCursor.getString(retCursor.getColumnIndex("phone")));
                survey.setHasInternet( retCursor.getString(retCursor.getColumnIndex("hasInternet")));
                survey.setIsNetwork( retCursor.getString(retCursor.getColumnIndex("isNetwork")));
                survey.setInternetSeed( retCursor.getString(retCursor.getColumnIndex("internetSeed")));
                survey.setOffice_id( retCursor.getString(retCursor.getColumnIndex("office_id")));
                survey.setOffice_name( retCursor.getString(retCursor.getColumnIndex("office_name")));
                survey.setOffice_visit( retCursor.getString(retCursor.getColumnIndex("office_visit")));
                survey.setShiftType( retCursor.getString(retCursor.getColumnIndex("shiftType")));
                survey.setOwnerShipType( retCursor.getString(retCursor.getColumnIndex("OwnerShipType")));
                survey.setMorning_shift_from( retCursor.getString(retCursor.getColumnIndex("morning_shift_from")));
                survey.setMorning_shift_to( retCursor.getString(retCursor.getColumnIndex("morning_shift_to")));
                survey.setEvening_shift_from( retCursor.getString(retCursor.getColumnIndex("evening_shift_from")));
                survey.setEvening_shift_to( retCursor.getString(retCursor.getColumnIndex("evening_shift_to")));
                survey.setComputer_count( retCursor.getString(retCursor.getColumnIndex("computer_count")));
                survey.setComputer_notes( retCursor.getString(retCursor.getColumnIndex("computer_notes")));
                survey.setPrinters_count( retCursor.getString(retCursor.getColumnIndex("printers_count")));
                survey.setPrinters_notes( retCursor.getString(retCursor.getColumnIndex("printers_notes")));
                survey.setScanners_count( retCursor.getString(retCursor.getColumnIndex("scanners_count")));
                survey.setScanners_notes( retCursor.getString(retCursor.getColumnIndex("scanners_notes")));
                survey.setOther_city( retCursor.getString(retCursor.getColumnIndex("other_city")));
                survey.setOther_district( retCursor.getString(retCursor.getColumnIndex("other_district")));
                survey.setUser_id( retCursor.getString(retCursor.getColumnIndex("user_id")));
                survey.setProject_id( retCursor.getString(retCursor.getColumnIndex("project_id")));

                survey.setNet_inside( retCursor.getString(retCursor.getColumnIndex("net_inside")));
                survey.setNet_outside( retCursor.getString(retCursor.getColumnIndex("net_outside")));
                survey.setIns_out_notes( retCursor.getString(retCursor.getColumnIndex("ins_out_notes")));
                survey.setOthers_macs( retCursor.getString(retCursor.getColumnIndex("others_macs")));
                survey.setOthers_macs_notes( retCursor.getString(retCursor.getColumnIndex("others_macs_notes")));
                survey.setTwo_offices( retCursor.getString(retCursor.getColumnIndex("two_offices")));
                survey.setOther_office_id( retCursor.getString(retCursor.getColumnIndex("other_office_id")));
                survey.setOther_office_name( retCursor.getString(retCursor.getColumnIndex("other_office_name")));


                survey.setIs_arabic( retCursor.getString(retCursor.getColumnIndex("is_arabic")));
                survey.setIs_kurdish( retCursor.getString(retCursor.getColumnIndex("is_kurdish")));
                survey.setBoth( retCursor.getString(retCursor.getColumnIndex("both")));
                surveyss.add(survey);
            }
        }

        return  surveyss;
    }
    public List<Survey>  getSurveysbyUseridOfficeidProjectid(String userid,String officeid , String projectid) {
        String [] args={officeid};
        List<Survey>  surveyss = new ArrayList<>();
        // Get access to underlying database (read-only for query)
        final SQLiteDatabase db = this.getReadableDatabase();

        Cursor retCursor;
        retCursor =  db.query(SURVEy_TABLE_NAME,
                null,
                SurveyContract.ItemEntry.project_id + " = " + ""+projectid +" and "+
                        SurveyContract.ItemEntry.office_id + " = "  +""+officeid +" and "+SurveyContract.ItemEntry.user_id +" = "+ ""+userid,
                null,
                null,
                null,
                null);


        if(retCursor!=null){
            while (retCursor.moveToNext()) {
                Survey  survey = new Survey();
                // governor.setId(retCursor.getInt(retCursor.getColumnIndex(ItemContract.ItemEntry._ID)));
                survey.setGov_id( retCursor.getString(retCursor.getColumnIndex("gov_id")));
                survey.setGov_name( retCursor.getString(retCursor.getColumnIndex("gov_name")));
                survey.setCity_id( retCursor.getString(retCursor.getColumnIndex("city_id")));
                survey.setCity_name( retCursor.getString(retCursor.getColumnIndex("city_name")));
                survey.setDistrict_id( retCursor.getString(retCursor.getColumnIndex("district_id")));
                survey.setDistrict_name( retCursor.getString(retCursor.getColumnIndex("district_name")));
                survey.setAddress( retCursor.getString(retCursor.getColumnIndex("address")));
                survey.setPhone( retCursor.getString(retCursor.getColumnIndex("phone")));
                survey.setHasInternet( retCursor.getString(retCursor.getColumnIndex("hasInternet")));
                survey.setIsNetwork( retCursor.getString(retCursor.getColumnIndex("isNetwork")));
                survey.setInternetSeed( retCursor.getString(retCursor.getColumnIndex("internetSeed")));
                survey.setOffice_id( retCursor.getString(retCursor.getColumnIndex("office_id")));
                survey.setOffice_name( retCursor.getString(retCursor.getColumnIndex("office_name")));
                survey.setOffice_visit( retCursor.getString(retCursor.getColumnIndex("office_visit")));
                survey.setShiftType( retCursor.getString(retCursor.getColumnIndex("shiftType")));
                survey.setOwnerShipType( retCursor.getString(retCursor.getColumnIndex("OwnerShipType")));
                survey.setMorning_shift_from( retCursor.getString(retCursor.getColumnIndex("morning_shift_from")));
                survey.setMorning_shift_to( retCursor.getString(retCursor.getColumnIndex("morning_shift_to")));
                survey.setEvening_shift_from( retCursor.getString(retCursor.getColumnIndex("evening_shift_from")));
                survey.setEvening_shift_to( retCursor.getString(retCursor.getColumnIndex("evening_shift_to")));
                survey.setComputer_count( retCursor.getString(retCursor.getColumnIndex("computer_count")));
                survey.setComputer_notes( retCursor.getString(retCursor.getColumnIndex("computer_notes")));
                survey.setPrinters_count( retCursor.getString(retCursor.getColumnIndex("printers_count")));
                survey.setPrinters_notes( retCursor.getString(retCursor.getColumnIndex("printers_notes")));
                survey.setScanners_count( retCursor.getString(retCursor.getColumnIndex("scanners_count")));
                survey.setScanners_notes( retCursor.getString(retCursor.getColumnIndex("scanners_notes")));
                survey.setOther_city( retCursor.getString(retCursor.getColumnIndex("other_city")));
                survey.setOther_district( retCursor.getString(retCursor.getColumnIndex("other_district")));
                survey.setUser_id( retCursor.getString(retCursor.getColumnIndex("user_id")));
                survey.setProject_id( retCursor.getString(retCursor.getColumnIndex("project_id")));


                survey.setNet_inside( retCursor.getString(retCursor.getColumnIndex("net_inside")));
                survey.setNet_outside( retCursor.getString(retCursor.getColumnIndex("net_outside")));
                survey.setIns_out_notes( retCursor.getString(retCursor.getColumnIndex("ins_out_notes")));
                survey.setOthers_macs( retCursor.getString(retCursor.getColumnIndex("others_macs")));
                survey.setOthers_macs_notes( retCursor.getString(retCursor.getColumnIndex("others_macs_notes")));
                survey.setTwo_offices( retCursor.getString(retCursor.getColumnIndex("two_offices")));
                survey.setOther_office_id( retCursor.getString(retCursor.getColumnIndex("other_office_id")));
                survey.setOther_office_name( retCursor.getString(retCursor.getColumnIndex("other_office_name")));


                survey.setIs_arabic( retCursor.getString(retCursor.getColumnIndex("is_arabic")));
                survey.setIs_kurdish( retCursor.getString(retCursor.getColumnIndex("is_kurdish")));
                survey.setBoth( retCursor.getString(retCursor.getColumnIndex("both")));

                surveyss.add(survey);
            }
        }

        return  surveyss;
    }
    // TODO Update Room Table
    public boolean updateRoom(String roomName, String roomCount, String roomFurniture, String roomId, String is_mutual_value)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();

        ContentValues args = new ContentValues();
        args.put(RoomsContract.ItemEntry.room_name, roomName);
        args.put(RoomsContract.ItemEntry.room_count, roomCount);
        args.put(RoomsContract.ItemEntry.room_furniture, roomFurniture);
        args.put(RoomsContract.ItemEntry.is_mutual, is_mutual_value);

        return db.update(ROOM_TABLE_NAME, args, RoomsContract.ItemEntry._ID + "=" + roomId, null) > 0;
    }


    //insert Suvey Table on sqlite

    public Long addSurvey(String gov_id,String gov_name ,String city_id ,String city_name , String district_id ,String district_name
    ,String address , String phone , String hasInternet , String isNetwork , String internetSeed , String office_id , String office_name
    ,String office_visit , String shiftType , String OwnerShipType , String morning_shift_from , String morning_shift_to , String evening_shift_from
                          ,String evening_shift_to , String computer_count , String computer_notes , String printers_count , String printers_notes
                          ,String scanners_count , String scanners_notes , String other_city , String other_district ,String user_id ,String project_id

    ) {
        long rowInserted ;
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();

                values.put(SurveyContract.ItemEntry.gov_id, gov_id);
                values.put(SurveyContract.ItemEntry.gov_name, gov_name);
                values.put(SurveyContract.ItemEntry.city_id, city_id);
                values.put(SurveyContract.ItemEntry.city_name, city_name);
                values.put(SurveyContract.ItemEntry.district_id, district_id);
                values.put(SurveyContract.ItemEntry.district_name, district_name);
            values.put(SurveyContract.ItemEntry.address, address);
            values.put(SurveyContract.ItemEntry.phone, phone);
            values.put(SurveyContract.ItemEntry.hasInternet, hasInternet);
            values.put(SurveyContract.ItemEntry.isNetwork, isNetwork);
            values.put(SurveyContract.ItemEntry.internetSeed, internetSeed);
            values.put(SurveyContract.ItemEntry.office_id, office_id);

            values.put(SurveyContract.ItemEntry.office_name, office_name);
            values.put(SurveyContract.ItemEntry.office_visit, office_visit);
            values.put(SurveyContract.ItemEntry.shiftType, shiftType);
            values.put(SurveyContract.ItemEntry.OwnerShipType, OwnerShipType);
            values.put(SurveyContract.ItemEntry.morning_shift_from, morning_shift_from);
            values.put(SurveyContract.ItemEntry.morning_shift_to, morning_shift_to);
            values.put(SurveyContract.ItemEntry.evening_shift_from, evening_shift_from);
            values.put(SurveyContract.ItemEntry.evening_shift_to, evening_shift_to);
            values.put(SurveyContract.ItemEntry.computer_count, computer_count);
            values.put(SurveyContract.ItemEntry.computer_notes, computer_notes);
            values.put(SurveyContract.ItemEntry.printers_count, printers_count);
            values.put(SurveyContract.ItemEntry.printers_notes, printers_notes);

            values.put(SurveyContract.ItemEntry.scanners_count, scanners_count);
            values.put(SurveyContract.ItemEntry.scanners_notes, scanners_notes);
            values.put(SurveyContract.ItemEntry.other_city, other_city);
            values.put(SurveyContract.ItemEntry.other_district, other_district);
            values.put(SurveyContract.ItemEntry.user_id, user_id);
            values.put(SurveyContract.ItemEntry.project_id, project_id);
            values.put(SurveyContract.ItemEntry.printers_count, printers_count);
            values.put(SurveyContract.ItemEntry.printers_notes, printers_notes);
            values.put(SurveyContract.ItemEntry.project_id, project_id);
            values.put(SurveyContract.ItemEntry.printers_count, printers_count);
            values.put(SurveyContract.ItemEntry.printers_notes, printers_notes);


             rowInserted  =     db.insert(SURVEy_TABLE_NAME, null, values);

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        return rowInserted ;
    }

    public Long addSurvey2(String gov_id,String gov_name ,String city_id ,String city_name , String district_id ,String district_name
            ,String address , String phone , String hasInternet , String isNetwork , String internetSeed , String office_id , String office_name
            ,String office_visit , String shiftType , String OwnerShipType , String morning_shift_from , String morning_shift_to , String evening_shift_from
            ,String evening_shift_to , String computer_count , String computer_notes , String printers_count , String printers_notes
            ,String scanners_count , String scanners_notes , String other_city , String other_district ,String user_id ,String project_id
            , String net_inside,String net_outside ,String ins_out_notes ,String others_macs ,String others_macs_notes
            , String two_offices ,String other_office_id ,String other_office_name  , String is_arabic1 ,String is_kurdish1 ,String both1
    ) {
        long rowInserted ;
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();

            values.put(SurveyContract.ItemEntry.gov_id, gov_id);
            values.put(SurveyContract.ItemEntry.gov_name, gov_name);
            values.put(SurveyContract.ItemEntry.city_id, city_id);
            values.put(SurveyContract.ItemEntry.city_name, city_name);
            values.put(SurveyContract.ItemEntry.district_id, district_id);
            values.put(SurveyContract.ItemEntry.district_name, district_name);
            values.put(SurveyContract.ItemEntry.address, address);
            values.put(SurveyContract.ItemEntry.phone, phone);
            values.put(SurveyContract.ItemEntry.hasInternet, hasInternet);
            values.put(SurveyContract.ItemEntry.isNetwork, isNetwork);
            values.put(SurveyContract.ItemEntry.internetSeed, internetSeed);
            values.put(SurveyContract.ItemEntry.office_id, office_id);

            values.put(SurveyContract.ItemEntry.office_name, office_name);
            values.put(SurveyContract.ItemEntry.office_visit, office_visit);
            values.put(SurveyContract.ItemEntry.shiftType, shiftType);
            values.put(SurveyContract.ItemEntry.OwnerShipType, OwnerShipType);
            values.put(SurveyContract.ItemEntry.morning_shift_from, morning_shift_from);
            values.put(SurveyContract.ItemEntry.morning_shift_to, morning_shift_to);
            values.put(SurveyContract.ItemEntry.evening_shift_from, evening_shift_from);
            values.put(SurveyContract.ItemEntry.evening_shift_to, evening_shift_to);
            values.put(SurveyContract.ItemEntry.computer_count, computer_count);
            values.put(SurveyContract.ItemEntry.computer_notes, computer_notes);
            values.put(SurveyContract.ItemEntry.printers_count, printers_count);
            values.put(SurveyContract.ItemEntry.printers_notes, printers_notes);

            values.put(SurveyContract.ItemEntry.scanners_count, scanners_count);
            values.put(SurveyContract.ItemEntry.scanners_notes, scanners_notes);
            values.put(SurveyContract.ItemEntry.other_city, other_city);
            values.put(SurveyContract.ItemEntry.other_district, other_district);
            values.put(SurveyContract.ItemEntry.user_id, user_id);
            values.put(SurveyContract.ItemEntry.project_id, project_id);
            values.put(SurveyContract.ItemEntry.printers_count, printers_count);
            values.put(SurveyContract.ItemEntry.printers_notes, printers_notes);

            values.put(SurveyContract.ItemEntry.net_inside, net_inside);
            values.put(SurveyContract.ItemEntry.net_outside, net_outside);
            values.put(SurveyContract.ItemEntry.ins_out_notes, ins_out_notes);
            values.put(SurveyContract.ItemEntry.others_macs, others_macs);
            values.put(SurveyContract.ItemEntry.others_macs_notes, others_macs_notes);
            values.put(SurveyContract.ItemEntry.two_offices, two_offices);
            values.put(SurveyContract.ItemEntry.other_office_id, other_office_id);
            values.put(SurveyContract.ItemEntry.other_office_name, other_office_name);

            values.put(SurveyContract.ItemEntry.is_arabic, is_arabic1);
            values.put(SurveyContract.ItemEntry.is_kurdish, is_kurdish1);
            values.put(SurveyContract.ItemEntry.both, both1);


            rowInserted  =     db.insert(SURVEy_TABLE_NAME, null, values);

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        return rowInserted ;
    }


    public boolean updateSurvey(String updatedOfficeId ,String gov_id,String gov_name ,String city_id ,String city_name , String district_id ,String district_name
            ,String address , String phone , String hasInternet , String isNetwork , String internetSeed , String office_id , String office_name
            ,String office_visit , String shiftType , String OwnerShipType , String morning_shift_from , String morning_shift_to , String evening_shift_from
            ,String evening_shift_to , String computer_count , String computer_notes , String printers_count , String printers_notes
            ,String scanners_count , String scanners_notes , String other_city , String other_district ,String user_id ,String project_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SurveyContract.ItemEntry.gov_id, gov_id);
        values.put(SurveyContract.ItemEntry.gov_name, gov_name);
        values.put(SurveyContract.ItemEntry.city_id, city_id);
        values.put(SurveyContract.ItemEntry.city_name, city_name);
        values.put(SurveyContract.ItemEntry.district_id, district_id);
        values.put(SurveyContract.ItemEntry.district_name, district_name);
        values.put(SurveyContract.ItemEntry.address, address);
        values.put(SurveyContract.ItemEntry.phone, phone);
        values.put(SurveyContract.ItemEntry.hasInternet, hasInternet);
        values.put(SurveyContract.ItemEntry.isNetwork, isNetwork);
        values.put(SurveyContract.ItemEntry.internetSeed, internetSeed);
        values.put(SurveyContract.ItemEntry.office_id, office_id);

        values.put(SurveyContract.ItemEntry.office_name, office_name);
        values.put(SurveyContract.ItemEntry.office_visit, office_visit);
        values.put(SurveyContract.ItemEntry.shiftType, shiftType);
        values.put(SurveyContract.ItemEntry.OwnerShipType, OwnerShipType);
        values.put(SurveyContract.ItemEntry.morning_shift_from, morning_shift_from);
        values.put(SurveyContract.ItemEntry.morning_shift_to, morning_shift_to);
        values.put(SurveyContract.ItemEntry.evening_shift_from, evening_shift_from);
        values.put(SurveyContract.ItemEntry.evening_shift_to, evening_shift_to);
        values.put(SurveyContract.ItemEntry.computer_count, computer_count);
        values.put(SurveyContract.ItemEntry.computer_notes, computer_notes);
        values.put(SurveyContract.ItemEntry.printers_count, printers_count);
        values.put(SurveyContract.ItemEntry.printers_notes, printers_notes);

        values.put(SurveyContract.ItemEntry.scanners_count, scanners_count);
        values.put(SurveyContract.ItemEntry.scanners_notes, scanners_notes);
        values.put(SurveyContract.ItemEntry.other_city, other_city);
        values.put(SurveyContract.ItemEntry.other_district, other_district);
        values.put(SurveyContract.ItemEntry.user_id, user_id);
        values.put(SurveyContract.ItemEntry.project_id, project_id);
        values.put(SurveyContract.ItemEntry.printers_count, printers_count);
        values.put(SurveyContract.ItemEntry.printers_notes, printers_notes);


        return db.update(SURVEy_TABLE_NAME, values, SurveyContract.ItemEntry.user_id + " = " + user_id + " and "+SurveyContract.ItemEntry.office_id+" = "+updatedOfficeId, null) > 0;
    }

    public boolean updateSurvey2(String updatedOfficeId ,String gov_id,String gov_name ,String city_id ,String city_name , String district_id ,String district_name
            ,String address , String phone , String hasInternet , String isNetwork , String internetSeed , String office_id , String office_name
            ,String office_visit , String shiftType , String OwnerShipType , String morning_shift_from , String morning_shift_to , String evening_shift_from
            ,String evening_shift_to , String computer_count , String computer_notes , String printers_count , String printers_notes
            ,String scanners_count , String scanners_notes , String other_city , String other_district ,String user_id ,String project_id
            , String net_inside,String net_outside ,String ins_out_notes ,String others_macs ,String others_macs_notes
            , String two_offices ,String other_office_id ,String other_office_name , String is_arabic1 ,String is_kurdish1 ,String both1

    )
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SurveyContract.ItemEntry.gov_id, gov_id);
        values.put(SurveyContract.ItemEntry.gov_name, gov_name);
        values.put(SurveyContract.ItemEntry.city_id, city_id);
        values.put(SurveyContract.ItemEntry.city_name, city_name);
        values.put(SurveyContract.ItemEntry.district_id, district_id);
        values.put(SurveyContract.ItemEntry.district_name, district_name);
        values.put(SurveyContract.ItemEntry.address, address);
        values.put(SurveyContract.ItemEntry.phone, phone);
        values.put(SurveyContract.ItemEntry.hasInternet, hasInternet);
        values.put(SurveyContract.ItemEntry.isNetwork, isNetwork);
        values.put(SurveyContract.ItemEntry.internetSeed, internetSeed);
        values.put(SurveyContract.ItemEntry.office_id, office_id);

        values.put(SurveyContract.ItemEntry.office_name, office_name);
        values.put(SurveyContract.ItemEntry.office_visit, office_visit);
        values.put(SurveyContract.ItemEntry.shiftType, shiftType);
        values.put(SurveyContract.ItemEntry.OwnerShipType, OwnerShipType);
        values.put(SurveyContract.ItemEntry.morning_shift_from, morning_shift_from);
        values.put(SurveyContract.ItemEntry.morning_shift_to, morning_shift_to);
        values.put(SurveyContract.ItemEntry.evening_shift_from, evening_shift_from);
        values.put(SurveyContract.ItemEntry.evening_shift_to, evening_shift_to);
        values.put(SurveyContract.ItemEntry.computer_count, computer_count);
        values.put(SurveyContract.ItemEntry.computer_notes, computer_notes);
        values.put(SurveyContract.ItemEntry.printers_count, printers_count);
        values.put(SurveyContract.ItemEntry.printers_notes, printers_notes);

        values.put(SurveyContract.ItemEntry.scanners_count, scanners_count);
        values.put(SurveyContract.ItemEntry.scanners_notes, scanners_notes);
        values.put(SurveyContract.ItemEntry.other_city, other_city);
        values.put(SurveyContract.ItemEntry.other_district, other_district);
        values.put(SurveyContract.ItemEntry.user_id, user_id);
        values.put(SurveyContract.ItemEntry.project_id, project_id);
        values.put(SurveyContract.ItemEntry.printers_count, printers_count);
        values.put(SurveyContract.ItemEntry.printers_notes, printers_notes);

        values.put(SurveyContract.ItemEntry.net_inside, net_inside);
        values.put(SurveyContract.ItemEntry.net_outside, net_outside);
        values.put(SurveyContract.ItemEntry.ins_out_notes, ins_out_notes);
        values.put(SurveyContract.ItemEntry.others_macs, others_macs);
        values.put(SurveyContract.ItemEntry.others_macs_notes, others_macs_notes);
        values.put(SurveyContract.ItemEntry.two_offices, two_offices);
        values.put(SurveyContract.ItemEntry.other_office_id, other_office_id);
        values.put(SurveyContract.ItemEntry.other_office_name, other_office_name);


        values.put(SurveyContract.ItemEntry.is_arabic, is_arabic1);
        values.put(SurveyContract.ItemEntry.is_kurdish, is_kurdish1);
        values.put(SurveyContract.ItemEntry.both, both1);

        return db.update(SURVEy_TABLE_NAME, values, SurveyContract.ItemEntry.user_id + " = " + user_id + " and "+SurveyContract.ItemEntry.office_id+" = "+updatedOfficeId, null) > 0;
    }


    //insert Notes Table on sqlite amr rabie

    public void addNotes( String office_id , String notes
            ,String location ,String user_id ,String project_id , String visit_date
    ) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(NotesContract.ItemEntry.office_id, office_id);
            values.put(NotesContract.ItemEntry.notes, notes);
            values.put(NotesContract.ItemEntry.location, location);
            values.put(NotesContract.ItemEntry.user_id, user_id);
            values.put(NotesContract.ItemEntry.project_id, project_id);
            values.put(NotesContract.ItemEntry.visit_date, visit_date);
         long asd =    db.insert(Notes_TABLE_NAME, null, values);
         Log.e("kkkkk" , asd+"");
            db.setTransactionSuccessful();


        } finally {
            db.endTransaction();
        }
    }

    public void addNotes2( String office_id , String notes
            ,String location ,String user_id ,String project_id , String visit_date
            , String electricty , String water, String bill_end , String colunms
            ,String doors , String secondary ,String camersnet
    ) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(NotesContract.ItemEntry.office_id, office_id);
            values.put(NotesContract.ItemEntry.notes, notes);
            values.put(NotesContract.ItemEntry.location, location);
            values.put(NotesContract.ItemEntry.user_id, user_id);
            values.put(NotesContract.ItemEntry.project_id, project_id);
            values.put(NotesContract.ItemEntry.visit_date, visit_date);

            values.put(NotesContract.ItemEntry.electricty, electricty);
            values.put(NotesContract.ItemEntry.water, water);
            values.put(NotesContract.ItemEntry.bill_end, bill_end);
            values.put(NotesContract.ItemEntry.colunms, colunms);
            values.put(NotesContract.ItemEntry.doors, doors);
            values.put(NotesContract.ItemEntry.secondary, secondary);
            values.put(NotesContract.ItemEntry.camersnet, camersnet);

            long asd =    db.insert(Notes_TABLE_NAME, null, values);
            Log.e("kkkkk" , asd+"");
            db.setTransactionSuccessful();


        } finally {
            db.endTransaction();
        }
    }

    //get Notes  amr rabie

    public List<Notes>  getNotes() {


        List<Notes>  notes = new ArrayList<>();
        // Get access to underlying database (read-only for query)
        final SQLiteDatabase db = this.getReadableDatabase();

        Cursor retCursor;
        retCursor =  db.query(Notes_TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);


        if(retCursor!=null){
            while (retCursor.moveToNext()) {
                Notes  note = new Notes();
                // governor.setId(retCursor.getInt(retCursor.getColumnIndex(ItemContract.ItemEntry._ID)));
                note.setVisit_date( retCursor.getString(retCursor.getColumnIndex("visit_date")));
                note.setNotes( retCursor.getString(retCursor.getColumnIndex("notes")));
                note.setLocation( retCursor.getString(retCursor.getColumnIndex("location")));
                note.setUser_id( retCursor.getString(retCursor.getColumnIndex("user_id")));
                note.setOffice_id( retCursor.getString(retCursor.getColumnIndex("office_id")));
                note.setProject_id( retCursor.getString(retCursor.getColumnIndex("project_id")));

                note.setElectricty( retCursor.getString(retCursor.getColumnIndex("electricty")));
                note.setWater( retCursor.getString(retCursor.getColumnIndex("water")));
                note.setBill_end( retCursor.getString(retCursor.getColumnIndex("bill_end")));
                note.setColunms( retCursor.getString(retCursor.getColumnIndex("colunms")));
                note.setDoors( retCursor.getString(retCursor.getColumnIndex("doors")));
                note.setSecondary( retCursor.getString(retCursor.getColumnIndex("secondary")));
                note.setCamersnet( retCursor.getString(retCursor.getColumnIndex("camersnet")));

                notes.add(note);
            }
        }
        
        return  notes;
    }


    public Notes  getNotesForUpdate(String userId , String projectId , String officeId) {


        // Get access to underlying database (read-only for query)
        final SQLiteDatabase db = this.getReadableDatabase();

        Cursor retCursor;
        retCursor =  db.query(Notes_TABLE_NAME,
                null,
                NotesContract.ItemEntry.user_id +" = "+ ""+userId +" and " +NotesContract.ItemEntry.project_id + " = " +projectId+" and " +NotesContract.ItemEntry.office_id + " = " +officeId,
                null,
                null,
                null,
                null);


        Notes  note = new Notes();
        if(retCursor!=null){
            while (retCursor.moveToNext()) {
                  note = new Notes();
                // governor.setId(retCursor.getInt(retCursor.getColumnIndex(ItemContract.ItemEntry._ID)));

                note.setVisit_date( retCursor.getString(retCursor.getColumnIndex("visit_date")));
                note.setNotes( retCursor.getString(retCursor.getColumnIndex("notes")));
                note.setLocation( retCursor.getString(retCursor.getColumnIndex("location")));
                note.setUser_id( retCursor.getString(retCursor.getColumnIndex("user_id")));
                note.setOffice_id( retCursor.getString(retCursor.getColumnIndex("office_id")));
                note.setProject_id( retCursor.getString(retCursor.getColumnIndex("project_id")));

                note.setElectricty( retCursor.getString(retCursor.getColumnIndex("electricty")));
                note.setWater( retCursor.getString(retCursor.getColumnIndex("water")));
                note.setBill_end( retCursor.getString(retCursor.getColumnIndex("bill_end")));
                note.setColunms( retCursor.getString(retCursor.getColumnIndex("colunms")));
                note.setDoors( retCursor.getString(retCursor.getColumnIndex("doors")));
                note.setSecondary( retCursor.getString(retCursor.getColumnIndex("secondary")));
                note.setCamersnet( retCursor.getString(retCursor.getColumnIndex("camersnet")));
            }
        }
        
        return  note;
    }

    //get Notes  amr rabie

    public List<Notes>  getNotesByUseridProjectIdOfficeid(String userid,String officeid , String projectid) {

        String [] args={officeid};
        String[] columns={NotesContract.ItemEntry.notes,NotesContract.ItemEntry.location};
        List<Notes>  notes = new ArrayList<>();
        // Get access to underlying database (read-only for query)
        final SQLiteDatabase db = this.getReadableDatabase();

        Cursor retCursor;
        retCursor =  db.query(Notes_TABLE_NAME,
                null,
                NotesContract.ItemEntry.project_id + " = " + ""+projectid +" and "+
                        NotesContract.ItemEntry.office_id + " = "  +""+officeid +" and "+NotesContract.ItemEntry.user_id +" = "+ ""+userid,
                null,
                null,
                null,
                null);


        if(retCursor!=null){
            while (retCursor.moveToNext()) {
                Notes  note = new Notes();
                // governor.setId(retCursor.getInt(retCursor.getColumnIndex(ItemContract.ItemEntry._ID)));
                note.setNotes( retCursor.getString(retCursor.getColumnIndex("notes")));
                note.setVisit_date( retCursor.getString(retCursor.getColumnIndex("visit_date")));
                note.setLocation( retCursor.getString(retCursor.getColumnIndex("location")));
                note.setUser_id( retCursor.getString(retCursor.getColumnIndex("user_id")));
                note.setOffice_id( retCursor.getString(retCursor.getColumnIndex("office_id")));
                note.setProject_id( retCursor.getString(retCursor.getColumnIndex("project_id")));

                note.setElectricty( retCursor.getString(retCursor.getColumnIndex("electricty")));
                note.setWater( retCursor.getString(retCursor.getColumnIndex("water")));
                note.setBill_end( retCursor.getString(retCursor.getColumnIndex("bill_end")));
                note.setColunms( retCursor.getString(retCursor.getColumnIndex("colunms")));
                note.setDoors( retCursor.getString(retCursor.getColumnIndex("doors")));
                note.setSecondary( retCursor.getString(retCursor.getColumnIndex("secondary")));
                note.setCamersnet( retCursor.getString(retCursor.getColumnIndex("camersnet")));

                notes.add(note);
            }
        }
        return  notes;
    }



    public List<Notes>  getNotesByUserid(String userid , String officeId) {

        List<Notes>  notes = new ArrayList<>();
        // Get access to underlying database (read-only for query)
        final SQLiteDatabase db = this.getReadableDatabase();

        Cursor retCursor;
        retCursor =  db.query(Notes_TABLE_NAME,
                null,
                NotesContract.ItemEntry.user_id +" = "+ ""+userid +" and " +NotesContract.ItemEntry.office_id + " = " +officeId,
                null,
                null,
                null,
                null);


        if(retCursor!=null){
            while (retCursor.moveToNext()) {
                Notes  note = new Notes();
                // governor.setId(retCursor.getInt(retCursor.getColumnIndex(ItemContract.ItemEntry._ID)));
                note.setNotes( retCursor.getString(retCursor.getColumnIndex("notes")));
                note.setVisit_date(retCursor.getString(retCursor.getColumnIndex("visit_date")));
                note.setLocation( retCursor.getString(retCursor.getColumnIndex("location")));
                note.setUser_id( retCursor.getString(retCursor.getColumnIndex("user_id")));
                note.setOffice_id( retCursor.getString(retCursor.getColumnIndex("office_id")));
                note.setProject_id( retCursor.getString(retCursor.getColumnIndex("project_id")));

                note.setElectricty( retCursor.getString(retCursor.getColumnIndex("electricty")));
                note.setWater( retCursor.getString(retCursor.getColumnIndex("water")));
                note.setBill_end( retCursor.getString(retCursor.getColumnIndex("bill_end")));
                note.setColunms( retCursor.getString(retCursor.getColumnIndex("colunms")));
                note.setDoors( retCursor.getString(retCursor.getColumnIndex("doors")));
                note.setSecondary( retCursor.getString(retCursor.getColumnIndex("secondary")));
                note.setCamersnet( retCursor.getString(retCursor.getColumnIndex("camersnet")));

                notes.add(note);
            }
        }
        
        return  notes;
    }


    //get govs amr rabie

    public List<Governor>  getGovs() {

        String[] columns={GOV_ID , GOV_NAME};
        List<Governor>  governors = new ArrayList<>();
        // Get access to underlying database (read-only for query)
        final SQLiteDatabase db = this.getReadableDatabase();

        Cursor retCursor;
        retCursor =  db.query(GOVERNOR_TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                null);


        if(retCursor!=null){
            while (retCursor.moveToNext()) {
                Governor  governor = new Governor();
                // governor.setId(retCursor.getInt(retCursor.getColumnIndex(ItemContract.ItemEntry._ID)));
                governor.setGov_id( retCursor.getString(retCursor.getColumnIndex(GOV_ID)));
                governor.setGov_name( retCursor.getString(retCursor.getColumnIndex(GOV_NAME)));
                governors.add(governor);
            }
        }
        
        return  governors;
    }

    //get cities by gov id amr rabie

    public List<City>  getCitiesByGovId(String govid) {

        String[] columns={CITY_ID , CITY_GOVE_ID ,CITY_NAME};
        String[] args={govid};
        List<City>  cities = new ArrayList<>();
        // Get access to underlying database (read-only for query)
        final SQLiteDatabase db = this.getReadableDatabase();

        Cursor retCursor;
        retCursor =  db.query(City_TABLE_NAME,
                columns,
                CITY_GOVE_ID + "=?"  ,
                args,
                null,
                null,
                null);


        if(retCursor!=null){
            while (retCursor.moveToNext()) {
                City  city = new City();
                // governor.setId(retCursor.getInt(retCursor.getColumnIndex(ItemContract.ItemEntry._ID)));
                city.setCity_id( retCursor.getString(retCursor.getColumnIndex(CITY_ID)));
                city.setCity_name( retCursor.getString(retCursor.getColumnIndex(CITY_NAME)));
                city.setGov_id( retCursor.getString(retCursor.getColumnIndex(CITY_GOVE_ID)));
                cities.add(city);
            }
        }
        
        return  cities;
    }


    //get districts by city id amr rabie

    public List<District>  getDistrictsbycityid(String cityid) {
        String[] columns={DISTRICT_ID , DISTRICT_CITY_ID ,DISTRICT_NAME};
        String[] args={cityid};
        List<District>   districts = new ArrayList<>();
        // Get access to underlying database (read-only for query)
        final SQLiteDatabase db = this.getReadableDatabase();

        Cursor retCursor;
        retCursor =  db.query(DISTRICT_TABLE_NAME,
                columns,
                DISTRICT_CITY_ID + "=?",
                args,
                null,
                null,
                null);


        if(retCursor!=null){
            while (retCursor.moveToNext()) {
                District   district = new District();
                // governor.setId(retCursor.getInt(retCursor.getColumnIndex(ItemContract.ItemEntry._ID)));
                district.setCity_id( retCursor.getString(retCursor.getColumnIndex(DISTRICT_CITY_ID)));
                district.setDistrict_id( retCursor.getString(retCursor.getColumnIndex(DISTRICT_ID)));
                district.setDistrict_name( retCursor.getString(retCursor.getColumnIndex(DISTRICT_NAME)));
                districts.add(district);
            }
        }
        
        return  districts;
    }


    //get offices by district id and project id amr rabie

    public List<OfficeResponseDetails>  getOfficesbydistidprojid(String distid,String projid) {
        //String[] columns={office_id , district_id ,office_name };
        String[] args={distid};
        String officevisit="0";
        List<OfficeResponseDetails>  officeResponseDetails = new ArrayList<>();
        // Get access to underlying database (read-only for query)
        final SQLiteDatabase db = this.getReadableDatabase();

        Cursor retCursor;
        retCursor =  db.query(OFFICE_TABLE_NAME,
                null,
                OfficesContract.ItemEntry.project_id + " = " + ""+projid +" and "+
                        OfficesContract.ItemEntry.district_id + " = "  +""+distid +" and "+
                        OfficesContract.ItemEntry.office_visit + " = "  +""+officevisit ,
                null,
                null,
                null,
                null);


        if(retCursor!=null){
            while (retCursor.moveToNext()) {
                OfficeResponseDetails  officeResponseDetails1 = new OfficeResponseDetails();
                officeResponseDetails1.setOffice_id( retCursor.getString(retCursor.getColumnIndex(office_id)));

                officeResponseDetails1.setDistrict_id( retCursor.getString(retCursor.getColumnIndex(district_id)));

                officeResponseDetails1.setOffice_name( retCursor.getString(retCursor.getColumnIndex(office_name)));

                officeResponseDetails1.setProject_id( retCursor.getString(retCursor.getColumnIndex(OfficesContract.ItemEntry.project_id)));

                officeResponseDetails.add(officeResponseDetails1);
            }
        }
        
        return  officeResponseDetails;
    }

    public List<OfficeResponseDetails>  getOfficesbyprojid(String projid , String govid) {
        //String[] columns={office_id , district_id ,office_name };
        //String[] args={distid};
        List<OfficeResponseDetails>  officeResponseDetails = new ArrayList<>();
        String officevisit="0";
        // Get access to underlying database (read-only for query)
        final SQLiteDatabase db = this.getReadableDatabase();

        Cursor retCursor;
        retCursor =  db.query(OFFICE_TABLE_NAME,
                null,
                OfficesContract.ItemEntry.project_id + " = " + ""+projid
                        + " and " +
                        OfficesContract.ItemEntry.gov_id + " = "  +""+govid
                        + " and " +
                        OfficesContract.ItemEntry.office_visit + " = "  +""+officevisit,
                null,
                null,
                null,
                null);


        if(retCursor!=null){
            while (retCursor.moveToNext()) {
                OfficeResponseDetails  officeResponseDetails1 = new OfficeResponseDetails();
                officeResponseDetails1.setOffice_id( retCursor.getString(retCursor.getColumnIndex(office_id)));

                officeResponseDetails1.setDistrict_id( retCursor.getString(retCursor.getColumnIndex(district_id)));

                officeResponseDetails1.setOffice_name( retCursor.getString(retCursor.getColumnIndex(office_name)));

                officeResponseDetails1.setProject_id( retCursor.getString(retCursor.getColumnIndex(OfficesContract.ItemEntry.project_id)));

                officeResponseDetails.add(officeResponseDetails1);
            }
        }
        
        return  officeResponseDetails;
    }


    public Long addRooms(RoomDetails roomDetails) {
        Long returnFinalResult ;
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
                ContentValues values = new ContentValues();
                values.put(RoomsContract.ItemEntry.user_id, roomDetails.getUser_id());
                values.put(RoomsContract.ItemEntry.project_id, roomDetails.getProject_id());
                values.put(RoomsContract.ItemEntry.office_id, roomDetails.getOffice_id());
                values.put(RoomsContract.ItemEntry.room_name, roomDetails.getRoomName());
                values.put(RoomsContract.ItemEntry.room_count, roomDetails.getRoomCount());
                values.put(RoomsContract.ItemEntry.room_furniture, roomDetails.getRoomFurniture());
                values.put(is_mutual, roomDetails.getIs_mutual());
                 returnFinalResult =  db.insert(ROOM_TABLE_NAME, null, values);

            db.setTransactionSuccessful();

        } finally {
            db.endTransaction();
        }

        for (RoomImage roomImage : roomDetails.getRoomImages()) {
            roomImage.setRoomID(getLastId());
            addRoomImage(roomImage);
        }


        return returnFinalResult;
    }

    public String getLastId() {
        String g = "";
        final SQLiteDatabase db = this.getReadableDatabase();
        Cursor retCursor;
        retCursor = db.rawQuery("select * from " + ROOM_TABLE_NAME + " ORDER BY " + RoomImagesContract.ItemEntry._ID + " DESC LIMIT 1", null);
        if (retCursor != null) {
            while (retCursor.moveToNext()) {
                g = retCursor.getString(retCursor.getColumnIndex(RoomsContract.ItemEntry._ID));

            }
        }
        return g;

    }
    public boolean deleteRoom(String roomId)
    {
        final SQLiteDatabase db = this.getReadableDatabase();
        boolean asd = db.delete(ROOM_TABLE_NAME, RoomsContract.ItemEntry._ID + "=" + roomId, null) > 0;
        if (asd) {
            deleteRoomImage2(roomId);
        }
        return asd;
    }

    // TODO Delete Logic after upload data ======================================================================================================//

    public boolean deleteUploadedSurvey(String userId , String projectId , String officeId)
    {
        final SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(SURVEy_TABLE_NAME, SurveyContract.ItemEntry.user_id + "=" + userId + " and " + SurveyContract.ItemEntry.project_id + " = "+projectId + " and "+SurveyContract.ItemEntry.office_id + " = "+officeId, null) > 0;
    }

    public boolean deleteUploadedJobs(String userId , String projectId , String officeId)
    {
        final SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(JOB_TABLE_NAME, JobContract.ItemEntry.USER_ID + "=" + userId + " and " + JobContract.ItemEntry.PROJECT_ID + " = "+projectId + " and "+JobContract.ItemEntry.OFFICE_ID + " = "+officeId, null) > 0;
    }

    public boolean deleteUploadedRooms(String userId , String projectId , String officeId)
    {
        final SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(ROOM_TABLE_NAME, RoomsContract.ItemEntry.user_id + "=" + userId + " and " +
                RoomsContract.ItemEntry.project_id + " = "+projectId + " and "+
                RoomsContract.ItemEntry.office_id + " = "+officeId, null) > 0;
    }

    public boolean deleteUploadedRoomImages(String userId , String projectId , String officeId)
    {
        final SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(ROOM_IMAGE_TABLE_NAME, RoomImagesContract.ItemEntry.user_id + "=" + userId + " and " + RoomImagesContract.ItemEntry.project_id + " = "+projectId + " and "+RoomImagesContract.ItemEntry.office_id + " = "+officeId, null) > 0;
    }

    public boolean deleteUploadedSketch(String userId , String projectId , String officeId)
    {
        final SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(SKETCH_TABLE_NAME, SketchContract.ItemEntry.user_id + "=" + userId + " and " + SketchContract.ItemEntry.project_id + " = " + projectId + " and " + SketchContract.ItemEntry.office_id + " = " + officeId, null) > 0;
    }

    public boolean deleteUploadedOutDoors(String userId , String projectId , String officeId)
    {
        final SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(OUTDOOR_TABLE_NAME, OutDoorContract.ItemEntry.user_id + "=" + userId + " and " + OutDoorContract.ItemEntry.project_id + " = "+projectId + " and "+OutDoorContract.ItemEntry.office_id + " = "+officeId, null) > 0;
    }

    public boolean deleteUploadedInDoors(String userId , String projectId , String officeId)
    {
        final SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(INDOOR_TABLE_NAME, InDoorContract.ItemEntry.user_id + "=" + userId + " and " + InDoorContract.ItemEntry.project_id + " = "+projectId + " and "+InDoorContract.ItemEntry.office_id + " = "+officeId, null) > 0;
    }

    public boolean deleteUploadedNotes(String userId , String projectId , String officeId)
    {
        final SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(Notes_TABLE_NAME, NotesContract.ItemEntry.user_id + "=" + userId + " and " + NotesContract.ItemEntry.project_id + " = "+projectId + " and "+NotesContract.ItemEntry.office_id + " = "+officeId, null) > 0;
    }

    //======================================================================================================//
    public List<RoomDetails> getUserRooms(String projectId, String officeId, String userId) throws SQLException {
        List<RoomDetails> roomDetails = new ArrayList<>();

        final SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCursor = db.query(true, ROOM_TABLE_NAME, new String[] {RoomsContract.ItemEntry._ID,RoomsContract.ItemEntry.project_id ,
                RoomsContract.ItemEntry.office_id ,RoomsContract.ItemEntry.user_id,RoomsContract.ItemEntry.room_name ,  RoomsContract.ItemEntry.room_count
                ,RoomsContract.ItemEntry.room_furniture , is_mutual
        }, RoomsContract.ItemEntry.project_id + " = " + ""+projectId +" and "+
                RoomsContract.ItemEntry.office_id + " = "  +""+officeId +" and "+RoomsContract.ItemEntry.user_id +" = "+ ""+userId, null, null, null, null, null);
        if(mCursor!=null){
            while (mCursor.moveToNext()) {
                RoomDetails roomDetails1 = new RoomDetails();
                String IDID = mCursor.getString(mCursor.getColumnIndex(RoomsContract.ItemEntry._ID));
                roomDetails1.set_ID(IDID);

                roomDetails1.setRoomFurniture( mCursor.getString(mCursor.getColumnIndex(RoomsContract.ItemEntry.room_furniture)));
                roomDetails1.setRoomCount( mCursor.getString(mCursor.getColumnIndex(RoomsContract.ItemEntry.room_count)));
                roomDetails1.setRoomName( mCursor.getString(mCursor.getColumnIndex(RoomsContract.ItemEntry.room_name)));
                roomDetails1.setOffice_id( mCursor.getString(mCursor.getColumnIndex(RoomsContract.ItemEntry.office_id)));
                roomDetails1.setProject_id( mCursor.getString(mCursor.getColumnIndex(RoomsContract.ItemEntry.project_id)));
                roomDetails1.setUser_id( mCursor.getString(mCursor.getColumnIndex(RoomsContract.ItemEntry.user_id)));
                roomDetails1.setRoomImages(getRoomImages(projectId, officeId, userId, IDID));
                roomDetails1.setIs_mutual( mCursor.getString(mCursor.getColumnIndex(is_mutual)));
                roomDetails.add(roomDetails1);
            }
        }

        return roomDetails;
    }

    public List<RoomDetails> getUserRoomsByUserId(String userId , String officeId) throws SQLException {
        List<RoomDetails> roomDetails = new ArrayList<>();

        final SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCursor = db.query(true, ROOM_TABLE_NAME, new String[] {RoomsContract.ItemEntry._ID,RoomsContract.ItemEntry.project_id ,
                RoomsContract.ItemEntry.office_id ,RoomsContract.ItemEntry.user_id,RoomsContract.ItemEntry.room_name ,  RoomsContract.ItemEntry.room_count , is_mutual
                ,RoomsContract.ItemEntry.room_furniture
        }, RoomsContract.ItemEntry.user_id + " = " + userId + " and " +RoomsContract.ItemEntry.office_id+ " = " +officeId, null, null, null, null, null);
        if(mCursor!=null){
            while (mCursor.moveToNext()) {
                RoomDetails roomDetails1 = new RoomDetails();
                String pro_id = mCursor.getString(mCursor.getColumnIndex(RoomsContract.ItemEntry.project_id));
                String IDID = mCursor.getString(mCursor.getColumnIndex(RoomsContract.ItemEntry._ID));
                roomDetails1.set_ID(IDID);
                roomDetails1.setRoomFurniture( mCursor.getString(mCursor.getColumnIndex(RoomsContract.ItemEntry.room_furniture)));
                roomDetails1.setRoomCount( mCursor.getString(mCursor.getColumnIndex(RoomsContract.ItemEntry.room_count)));
                roomDetails1.setRoomName( mCursor.getString(mCursor.getColumnIndex(RoomsContract.ItemEntry.room_name)));
                roomDetails1.setOffice_id( mCursor.getString(mCursor.getColumnIndex(RoomsContract.ItemEntry.office_id)));
                roomDetails1.setProject_id(pro_id);
                roomDetails1.setUser_id( mCursor.getString(mCursor.getColumnIndex(RoomsContract.ItemEntry.user_id)));
                roomDetails1.setIs_mutual( mCursor.getString(mCursor.getColumnIndex(is_mutual)));
                roomDetails1.setRoomImages(getRoomImages(pro_id, officeId, userId, IDID));
                roomDetails.add(roomDetails1);
            }
        }

        return roomDetails;
    }


    public List<RoomDetails> getUserRoomsByUserIdSending(String userId , String officeId) throws SQLException {
        List<RoomDetails> roomDetails = new ArrayList<>();

        final SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCursor = db.query(true, ROOM_TABLE_NAME, new String[] {RoomsContract.ItemEntry._ID,RoomsContract.ItemEntry.project_id ,
                RoomsContract.ItemEntry.office_id ,RoomsContract.ItemEntry.user_id,RoomsContract.ItemEntry.room_name ,  RoomsContract.ItemEntry.room_count , is_mutual
                ,RoomsContract.ItemEntry.room_furniture
        }, RoomsContract.ItemEntry.user_id + " = " + userId + " and " +RoomsContract.ItemEntry.office_id+ " = " +officeId, null, null, null, null, null);
        if(mCursor!=null){
            while (mCursor.moveToNext()) {
                RoomDetails roomDetails1 = new RoomDetails();
                String pro_id = mCursor.getString(mCursor.getColumnIndex(RoomsContract.ItemEntry.project_id));
                String IDID = mCursor.getString(mCursor.getColumnIndex(RoomsContract.ItemEntry._ID));
                roomDetails1.set_ID(IDID);
                roomDetails1.setRoomFurniture( mCursor.getString(mCursor.getColumnIndex(RoomsContract.ItemEntry.room_furniture)));
                roomDetails1.setRoomCount( mCursor.getString(mCursor.getColumnIndex(RoomsContract.ItemEntry.room_count)));
                roomDetails1.setRoomName( mCursor.getString(mCursor.getColumnIndex(RoomsContract.ItemEntry.room_name)));
                roomDetails1.setOffice_id( mCursor.getString(mCursor.getColumnIndex(RoomsContract.ItemEntry.office_id)));
                roomDetails1.setProject_id(pro_id);
                roomDetails1.setUser_id( mCursor.getString(mCursor.getColumnIndex(RoomsContract.ItemEntry.user_id)));
                roomDetails1.setIs_mutual( mCursor.getString(mCursor.getColumnIndex(is_mutual)));
                roomDetails1.setRoomImages(getRoomImagesSending(pro_id, officeId, userId, IDID));
                roomDetails.add(roomDetails1);
            }
        }

        return roomDetails;
    }




    //TODO Out Door Image
    public Long addOutDoorImage(OutDoorImage outDoorImage) {
        Long returnFinalResult;
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            byte[] imgByte = getBitmapAsByteArray(outDoorImage.getOutDoorImageBitmap());

            ContentValues values = new ContentValues();
            values.put(OutDoorContract.ItemEntry.user_id, outDoorImage.getOutDoorUserId());
            values.put(OutDoorContract.ItemEntry.project_id, outDoorImage.getOutDoorProjectId());
            values.put(OutDoorContract.ItemEntry.office_id, outDoorImage.getOutDoorOfficeId());
            values.put(OutDoorContract.ItemEntry.minimum_status, outDoorImage.getMimimumStatus());
            values.put(OutDoorContract.ItemEntry.out_door_image_desc, outDoorImage.getOutDoorImageDesc());
            values.put(OutDoorContract.ItemEntry.out_door_image, imgByte);

            returnFinalResult = db.insert(OUTDOOR_TABLE_NAME, null, values);

            db.setTransactionSuccessful();

        } finally {
            db.endTransaction();
        }

        return returnFinalResult;
    }


    public Long addInDoorImage(InDoorImage inDoorImage) {
        Long returnFinalResult;
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            byte[] imgByte = getBitmapAsByteArray(inDoorImage.getInDoorImageBitmap());

            ContentValues values = new ContentValues();
            values.put(InDoorContract.ItemEntry.user_id, inDoorImage.getInDoorUserId());
            values.put(InDoorContract.ItemEntry.minimum_status, inDoorImage.getMimimumStatus());
            values.put(InDoorContract.ItemEntry.project_id, inDoorImage.getInDoorProjectId());
            values.put(InDoorContract.ItemEntry.office_id, inDoorImage.getInDoorOfficeId());
            values.put(InDoorContract.ItemEntry.indoor_door_image_desc, inDoorImage.getIndoorImageDesc());
            values.put(InDoorContract.ItemEntry.indoor_door_image, imgByte);

            returnFinalResult = db.insert(INDOOR_TABLE_NAME, null, values);

            db.setTransactionSuccessful();

        } finally {
            db.endTransaction();
        }

        return returnFinalResult;
    }


    public void addRoomImage(RoomImage roomImage) {
        Long returnFinalResult;
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            byte[] imgByte = getBitmapAsByteArray(roomImage.getImageBitmap());
            ContentValues values = new ContentValues();
            values.put(RoomImagesContract.ItemEntry.user_id, roomImage.getUser_id());
            values.put(RoomImagesContract.ItemEntry.project_id, roomImage.getProject_id());
            values.put(RoomImagesContract.ItemEntry.office_id, roomImage.getOffice_id());
            values.put(RoomImagesContract.ItemEntry.room_id, roomImage.getRoomID());
            values.put(RoomImagesContract.ItemEntry.minimum_status, roomImage.getMinimumStatus());
            values.put(RoomImagesContract.ItemEntry.image_bitmap, imgByte);
            returnFinalResult = db.insert(ROOM_IMAGE_TABLE_NAME, null, values);
            db.setTransactionSuccessful();

        } catch (SQLException ex) {
            Log.e("SQLException", ex.getLocalizedMessage());
        } finally {
            db.endTransaction();

        }


    }

    public List<RoomImage> getRoomImages(String projectId, String officeId, String userId, String roomId) throws SQLException {
        List<RoomImage> roomImages = new ArrayList<>();

        final SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCursor = db.query(true, ROOM_IMAGE_TABLE_NAME
                , new String[]{RoomImagesContract.ItemEntry._ID,
                        RoomImagesContract.ItemEntry.user_id,
                        RoomImagesContract.ItemEntry.project_id,
                        RoomImagesContract.ItemEntry.office_id,
                        RoomImagesContract.ItemEntry.room_id,
                        RoomImagesContract.ItemEntry.image_bitmap},

                RoomImagesContract.ItemEntry.project_id + " = " + "" + projectId + " and " +
                        RoomImagesContract.ItemEntry.room_id + " = " + "" + roomId + " and " +
                        RoomImagesContract.ItemEntry.office_id + " = " + "" +
                        officeId + " and " + RoomImagesContract.ItemEntry.user_id + " = " + "" + userId,
                null, null, null, null, null);
        if (mCursor != null) {
            while (mCursor.moveToNext()) {
                RoomImage roomImage = new RoomImage();
                roomImage.setImageID(mCursor.getString(mCursor.getColumnIndex(RoomImagesContract.ItemEntry._ID)));
                roomImage.setProject_id(mCursor.getString(mCursor.getColumnIndex(RoomImagesContract.ItemEntry.project_id)));
                roomImage.setOffice_id(mCursor.getString(mCursor.getColumnIndex(RoomImagesContract.ItemEntry.office_id)));
                roomImage.setUser_id(mCursor.getString(mCursor.getColumnIndex(RoomImagesContract.ItemEntry.user_id)));
                roomImage.setRoomID(mCursor.getString(mCursor.getColumnIndex(RoomImagesContract.ItemEntry.room_id)));
                int index = mCursor.getColumnIndexOrThrow(RoomImagesContract.ItemEntry.image_bitmap);
                byte[] imgByte = mCursor.getBlob(index);

                Bitmap bitmap = BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length );

                roomImage.setImageBitmap(bitmap);
                roomImages.add(roomImage);
            }
        }

        return roomImages;
    }



    public List<RoomImage> getRoomImagesSending(String projectId, String officeId, String userId, String roomId) throws SQLException {
        List<RoomImage> roomImages = new ArrayList<>();

        final SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCursor = db.query(true, ROOM_IMAGE_TABLE_NAME
                , new String[]{RoomImagesContract.ItemEntry._ID,
                        RoomImagesContract.ItemEntry.user_id,
                        RoomImagesContract.ItemEntry.project_id,
                        RoomImagesContract.ItemEntry.office_id,
                        RoomImagesContract.ItemEntry.room_id,
                        RoomImagesContract.ItemEntry.minimum_status,
                        RoomImagesContract.ItemEntry.image_bitmap},

                RoomImagesContract.ItemEntry.project_id + " = " + "" + projectId + " and " +
                        RoomImagesContract.ItemEntry.room_id + " = " + "" + roomId + " and " +
                        RoomImagesContract.ItemEntry.office_id + " = " + "" +
                        officeId + " and " + RoomImagesContract.ItemEntry.user_id + " = " + "" + userId,
                null, null, null, null, null);
        if (mCursor != null) {
            while (mCursor.moveToNext()) {
                RoomImage roomImage = new RoomImage();
                String minimumStatus = mCursor.getString(mCursor.getColumnIndex(RoomImagesContract.ItemEntry.minimum_status));
                roomImage.setImageID(mCursor.getString(mCursor.getColumnIndex(RoomImagesContract.ItemEntry._ID)));
                roomImage.setProject_id(mCursor.getString(mCursor.getColumnIndex(RoomImagesContract.ItemEntry.project_id)));
                roomImage.setOffice_id(mCursor.getString(mCursor.getColumnIndex(RoomImagesContract.ItemEntry.office_id)));
                roomImage.setUser_id(mCursor.getString(mCursor.getColumnIndex(RoomImagesContract.ItemEntry.user_id)));
                roomImage.setRoomID(mCursor.getString(mCursor.getColumnIndex(RoomImagesContract.ItemEntry.room_id)));
                roomImage.setMinimumStatus(Integer.parseInt(mCursor.getString(mCursor.getColumnIndex(RoomImagesContract.ItemEntry.minimum_status))));
                int index = mCursor.getColumnIndexOrThrow(RoomImagesContract.ItemEntry.image_bitmap);
                byte[] imgByte = mCursor.getBlob(index);

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 4;
                Bitmap bitmap;
                if (Integer.parseInt(minimumStatus) == 0) {
                    bitmap = BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length, options);
                } else {
                    bitmap = BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);
                }

                roomImage.setImageBitmap(bitmap);
                roomImages.add(roomImage);
            }
        }

        return roomImages;
    }


    public boolean deleteRoomImage(String roomImageId) {
        final SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(ROOM_IMAGE_TABLE_NAME, RoomImagesContract.ItemEntry._ID + "=" + roomImageId, null) > 0;
    }

    public boolean deleteRoomImage2(String roomId) {
        final SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(ROOM_IMAGE_TABLE_NAME, RoomImagesContract.ItemEntry.room_id + "=" + roomId, null) > 0;
    }

    public boolean deleteListOfRoomImages(List<RoomImage> roomImages) {
        boolean o = false;
        for (RoomImage room : roomImages) {
            final SQLiteDatabase db = this.getReadableDatabase();
            o = db.delete(ROOM_IMAGE_TABLE_NAME, RoomImagesContract.ItemEntry._ID + "=" + room.getImageID(), null) > 0;
        }
        return o;
    }


    public byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        return outputStream.toByteArray();
    }


    public List<InDoorImage> getInDoorOfficeByUserId(String userId , String officeId) throws SQLException {
        List<InDoorImage> inDoorImages = new ArrayList<>();

        final SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCursor = db.query(true, INDOOR_TABLE_NAME
                , new String[] {InDoorContract.ItemEntry._ID,InDoorContract.ItemEntry.project_id ,
                        InDoorContract.ItemEntry.office_id, InDoorContract.ItemEntry.indoor_door_image_desc, InDoorContract.ItemEntry.indoor_door_image
                }, InDoorContract.ItemEntry.user_id +" = "+ ""+userId + " and " + InDoorContract.ItemEntry.office_id + " = "+officeId, null, null, null, null, null);
        if(mCursor!=null){
            while (mCursor.moveToNext()) {
                InDoorImage inDoorImage1 = new InDoorImage();
                inDoorImage1.setInDoorImage_ID( mCursor.getString(mCursor.getColumnIndex(InDoorContract.ItemEntry._ID)));
                inDoorImage1.setInDoorProjectId( mCursor.getString(mCursor.getColumnIndex(InDoorContract.ItemEntry.project_id)));
                inDoorImage1.setInDoorOfficeId( mCursor.getString(mCursor.getColumnIndex(InDoorContract.ItemEntry.office_id)));
                inDoorImage1.setIndoorImageDesc(mCursor.getString(mCursor.getColumnIndex(InDoorContract.ItemEntry.indoor_door_image_desc)));
                int index = mCursor.getColumnIndexOrThrow(InDoorContract.ItemEntry.indoor_door_image);
                byte[] imgByte = mCursor.getBlob(index);


                Bitmap bitmap = BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length );

                inDoorImage1.setInDoorImageBitmap(bitmap);

                inDoorImages.add(inDoorImage1);
            }
        }

        return inDoorImages;
    }
    public List<InDoorImage> getInDoorOfficeByUserIdSending(String userId , String officeId) throws SQLException {
        List<InDoorImage> inDoorImages = new ArrayList<>();

        final SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCursor = db.query(true, INDOOR_TABLE_NAME
                , new String[] {InDoorContract.ItemEntry._ID,InDoorContract.ItemEntry.project_id ,InDoorContract.ItemEntry.minimum_status ,
                        InDoorContract.ItemEntry.office_id, InDoorContract.ItemEntry.indoor_door_image_desc, InDoorContract.ItemEntry.indoor_door_image
                }, InDoorContract.ItemEntry.user_id +" = "+ ""+userId + " and " + InDoorContract.ItemEntry.office_id + " = "+officeId, null, null, null, null, null);
        if(mCursor!=null){
            while (mCursor.moveToNext()) {
                InDoorImage inDoorImage1 = new InDoorImage();
                String minimumStatus = mCursor.getString(mCursor.getColumnIndex(InDoorContract.ItemEntry.minimum_status));
                inDoorImage1.setInDoorImage_ID( mCursor.getString(mCursor.getColumnIndex(InDoorContract.ItemEntry._ID)));
                inDoorImage1.setInDoorProjectId( mCursor.getString(mCursor.getColumnIndex(InDoorContract.ItemEntry.project_id)));
                inDoorImage1.setInDoorOfficeId( mCursor.getString(mCursor.getColumnIndex(InDoorContract.ItemEntry.office_id)));
                inDoorImage1.setIndoorImageDesc(mCursor.getString(mCursor.getColumnIndex(InDoorContract.ItemEntry.indoor_door_image_desc)));
                int index = mCursor.getColumnIndexOrThrow(InDoorContract.ItemEntry.indoor_door_image);
                byte[] imgByte = mCursor.getBlob(index);

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 4;
                Bitmap bitmap;
                if (Integer.parseInt(minimumStatus) == 0) {
                    bitmap = BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length, options);
                } else {
                    bitmap = BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);
                }


                inDoorImage1.setInDoorImageBitmap(bitmap);

                inDoorImages.add(inDoorImage1);
            }
        }

        return inDoorImages;
    }
    public List<InDoorImage> getInDoorOffice(String projectId , String officeId , String userId) throws SQLException {
        List<InDoorImage> inDoorImages = new ArrayList<>();

        final SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCursor = db.query(true, INDOOR_TABLE_NAME
                , new String[] {InDoorContract.ItemEntry._ID,InDoorContract.ItemEntry.project_id ,
                        InDoorContract.ItemEntry.office_id, InDoorContract.ItemEntry.indoor_door_image_desc, InDoorContract.ItemEntry.indoor_door_image
                }, InDoorContract.ItemEntry.project_id + " = " + "" + projectId + " and " +
                        InDoorContract.ItemEntry.office_id + " = "  +""+officeId +" and "+InDoorContract.ItemEntry.user_id +" = "+ ""+userId, null, null, null, null, null);
        if(mCursor!=null){
            while (mCursor.moveToNext()) {
                InDoorImage inDoorImage1 = new InDoorImage();
                inDoorImage1.setInDoorImage_ID( mCursor.getString(mCursor.getColumnIndex(InDoorContract.ItemEntry._ID)));
                inDoorImage1.setInDoorProjectId( mCursor.getString(mCursor.getColumnIndex(InDoorContract.ItemEntry.project_id)));
                inDoorImage1.setInDoorOfficeId( mCursor.getString(mCursor.getColumnIndex(InDoorContract.ItemEntry.office_id)));
                inDoorImage1.setIndoorImageDesc(mCursor.getString(mCursor.getColumnIndex(InDoorContract.ItemEntry.indoor_door_image_desc)));
                int index = mCursor.getColumnIndexOrThrow(InDoorContract.ItemEntry.indoor_door_image);
                byte[] imgByte = mCursor.getBlob(index);

                Bitmap bitmap = BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length );

                inDoorImage1.setInDoorImageBitmap(bitmap);

                inDoorImages.add(inDoorImage1);
            }
        }

        return inDoorImages;
    }

    public List<InDoorImage> getInDoorOfficeSending(String projectId , String officeId , String userId) throws SQLException {
        List<InDoorImage> inDoorImages = new ArrayList<>();

        final SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCursor = db.query(true, INDOOR_TABLE_NAME
                , new String[] {InDoorContract.ItemEntry._ID,InDoorContract.ItemEntry.project_id ,InDoorContract.ItemEntry.minimum_status ,
                        InDoorContract.ItemEntry.office_id, InDoorContract.ItemEntry.indoor_door_image_desc, InDoorContract.ItemEntry.indoor_door_image
                }, InDoorContract.ItemEntry.project_id + " = " + "" + projectId + " and " +
                        InDoorContract.ItemEntry.office_id + " = "  +""+officeId +" and "+InDoorContract.ItemEntry.user_id +" = "+ ""+userId, null, null, null, null, null);
        if(mCursor!=null){
            while (mCursor.moveToNext()) {
                InDoorImage inDoorImage1 = new InDoorImage();
                inDoorImage1.setInDoorImage_ID( mCursor.getString(mCursor.getColumnIndex(InDoorContract.ItemEntry._ID)));
                inDoorImage1.setInDoorProjectId( mCursor.getString(mCursor.getColumnIndex(InDoorContract.ItemEntry.project_id)));
                inDoorImage1.setInDoorOfficeId( mCursor.getString(mCursor.getColumnIndex(InDoorContract.ItemEntry.office_id)));
                inDoorImage1.setIndoorImageDesc(mCursor.getString(mCursor.getColumnIndex(InDoorContract.ItemEntry.indoor_door_image_desc)));
                int index = mCursor.getColumnIndexOrThrow(InDoorContract.ItemEntry.indoor_door_image);
                byte[] imgByte = mCursor.getBlob(index);
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 4;
                Bitmap bitmap = BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length , options);

                inDoorImage1.setInDoorImageBitmap(bitmap);

                inDoorImages.add(inDoorImage1);
            }
        }

        return inDoorImages;
    }

    public List<OutDoorImage> getOutDoorOfficeByUserId(String userId , String officeId) throws SQLException {
        List<OutDoorImage> outDoorImages = new ArrayList<>();

        final SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCursor = db.query(true, OUTDOOR_TABLE_NAME, new String[] {OutDoorContract.ItemEntry._ID,OutDoorContract.ItemEntry.project_id ,
                OutDoorContract.ItemEntry.office_id, OutDoorContract.ItemEntry.out_door_image_desc, OutDoorContract.ItemEntry.out_door_image
        }, OutDoorContract.ItemEntry.user_id +" = "+ ""+userId + " and "+OutDoorContract.ItemEntry.office_id + " = " +officeId,
                null, null, null, null, null);
        if(mCursor!=null){
            while (mCursor.moveToNext()) {
                OutDoorImage outDoorImage1 = new OutDoorImage();
                outDoorImage1.setOutDoorImage_ID( mCursor.getString(mCursor.getColumnIndex(OutDoorContract.ItemEntry._ID)));
                outDoorImage1.setOutDoorProjectId( mCursor.getString(mCursor.getColumnIndex(OutDoorContract.ItemEntry.project_id)));
                outDoorImage1.setOutDoorOfficeId( mCursor.getString(mCursor.getColumnIndex(OutDoorContract.ItemEntry.office_id)));
                outDoorImage1.setOutDoorImageDesc(mCursor.getString(mCursor.getColumnIndex(OutDoorContract.ItemEntry.out_door_image_desc)));
                int index = mCursor.getColumnIndexOrThrow(OutDoorContract.ItemEntry.out_door_image);
                byte[] imgByte = mCursor.getBlob(index);

                Bitmap bitmap = BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length );

                outDoorImage1.setOutDoorImageBitmap(bitmap);

                outDoorImages.add(outDoorImage1);
            }
        }

        return outDoorImages;
    }

    public List<OutDoorImage> getOutDoorOfficeByUserIdSending(String userId , String officeId) throws SQLException {
        List<OutDoorImage> outDoorImages = new ArrayList<>();

        final SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCursor = db.query(true, OUTDOOR_TABLE_NAME, new String[] {OutDoorContract.ItemEntry._ID,OutDoorContract.ItemEntry.project_id , OutDoorContract.ItemEntry.minimum_status ,
                        OutDoorContract.ItemEntry.office_id, OutDoorContract.ItemEntry.out_door_image_desc, OutDoorContract.ItemEntry.out_door_image
                }, OutDoorContract.ItemEntry.user_id +" = "+ ""+userId + " and "+OutDoorContract.ItemEntry.office_id + " = " +officeId,
                null, null, null, null, null);
        if(mCursor!=null){
            while (mCursor.moveToNext()) {
                OutDoorImage outDoorImage1 = new OutDoorImage();
                String minimumStatus = mCursor.getString(mCursor.getColumnIndex(OutDoorContract.ItemEntry.minimum_status));
                outDoorImage1.setOutDoorImage_ID( mCursor.getString(mCursor.getColumnIndex(OutDoorContract.ItemEntry._ID)));
                outDoorImage1.setOutDoorProjectId( mCursor.getString(mCursor.getColumnIndex(OutDoorContract.ItemEntry.project_id)));
                outDoorImage1.setOutDoorOfficeId( mCursor.getString(mCursor.getColumnIndex(OutDoorContract.ItemEntry.office_id)));
                outDoorImage1.setOutDoorImageDesc(mCursor.getString(mCursor.getColumnIndex(OutDoorContract.ItemEntry.out_door_image_desc)));
                int index = mCursor.getColumnIndexOrThrow(OutDoorContract.ItemEntry.out_door_image);
                byte[] imgByte = mCursor.getBlob(index);
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 4;
                Bitmap bitmap;
                if (Integer.parseInt(minimumStatus) == 0) {
                    bitmap = BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length, options);
                } else {
                    bitmap = BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);
                }


                outDoorImage1.setOutDoorImageBitmap(bitmap);

                outDoorImages.add(outDoorImage1);
            }
        }

        return outDoorImages;
    }

    public List<OutDoorImage> getOutDoorOffice(String projectId , String officeId , String userId) throws SQLException {
        List<OutDoorImage> outDoorImages = new ArrayList<>();

        final SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCursor = db.query(true, OUTDOOR_TABLE_NAME, new String[] {OutDoorContract.ItemEntry._ID,OutDoorContract.ItemEntry.project_id ,
                OutDoorContract.ItemEntry.office_id, OutDoorContract.ItemEntry.out_door_image_desc, OutDoorContract.ItemEntry.out_door_image
        }, OutDoorContract.ItemEntry.project_id + " = " + ""+projectId +" and "+
                OutDoorContract.ItemEntry.office_id + " = "  +""+officeId +" and "+OutDoorContract.ItemEntry.user_id +" = "+ ""+userId, null, null, null, null, null);
        if(mCursor!=null){
            while (mCursor.moveToNext()) {
                OutDoorImage outDoorImage1 = new OutDoorImage();
                outDoorImage1.setOutDoorImage_ID( mCursor.getString(mCursor.getColumnIndex(OutDoorContract.ItemEntry._ID)));
                outDoorImage1.setOutDoorProjectId( mCursor.getString(mCursor.getColumnIndex(OutDoorContract.ItemEntry.project_id)));
                outDoorImage1.setOutDoorOfficeId( mCursor.getString(mCursor.getColumnIndex(OutDoorContract.ItemEntry.office_id)));
                outDoorImage1.setOutDoorImageDesc(mCursor.getString(mCursor.getColumnIndex(OutDoorContract.ItemEntry.out_door_image_desc)));
                int index = mCursor.getColumnIndexOrThrow(OutDoorContract.ItemEntry.out_door_image);
                byte[] imgByte = mCursor.getBlob(index);

                Bitmap bitmap = BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length ) ;

                outDoorImage1.setOutDoorImageBitmap(bitmap);

                outDoorImages.add(outDoorImage1);
            }
        }

        return outDoorImages;
    }

    public List<OutDoorImage> getOutDoorOfficeSending(String projectId , String officeId , String userId) throws SQLException {
        List<OutDoorImage> outDoorImages = new ArrayList<>();

        final SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCursor = db.query(true, OUTDOOR_TABLE_NAME, new String[] {OutDoorContract.ItemEntry._ID,OutDoorContract.ItemEntry.project_id , OutDoorContract.ItemEntry.minimum_status ,
                OutDoorContract.ItemEntry.office_id, OutDoorContract.ItemEntry.out_door_image_desc, OutDoorContract.ItemEntry.out_door_image
        }, OutDoorContract.ItemEntry.project_id + " = " + ""+projectId +" and "+
                OutDoorContract.ItemEntry.office_id + " = "  +""+officeId +" and "+OutDoorContract.ItemEntry.user_id +" = "+ ""+userId, null, null, null, null, null);
        if(mCursor!=null){
            while (mCursor.moveToNext()) {
                OutDoorImage outDoorImage1 = new OutDoorImage();
                outDoorImage1.setOutDoorImage_ID( mCursor.getString(mCursor.getColumnIndex(OutDoorContract.ItemEntry._ID)));
                outDoorImage1.setOutDoorProjectId( mCursor.getString(mCursor.getColumnIndex(OutDoorContract.ItemEntry.project_id)));
                outDoorImage1.setOutDoorOfficeId( mCursor.getString(mCursor.getColumnIndex(OutDoorContract.ItemEntry.office_id)));
                outDoorImage1.setOutDoorImageDesc(mCursor.getString(mCursor.getColumnIndex(OutDoorContract.ItemEntry.out_door_image_desc)));
                int index = mCursor.getColumnIndexOrThrow(OutDoorContract.ItemEntry.out_door_image);
                byte[] imgByte = mCursor.getBlob(index);
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 4;
                Bitmap bitmap = BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length , options) ;

                outDoorImage1.setOutDoorImageBitmap(bitmap);

                outDoorImages.add(outDoorImage1);
            }
        }


        return outDoorImages;
    }

    public List<Sketch> getSketchOffice(String projectId , String officeId , String userId) throws SQLException {
        List<Sketch> sketches = new ArrayList<>();

        final SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCursor = db.query(true, SKETCH_TABLE_NAME, new String[]{SketchContract.ItemEntry._ID, SketchContract.ItemEntry.user_id, SketchContract.ItemEntry.project_id,
                SketchContract.ItemEntry.office_id, SketchContract.ItemEntry.sketch_image_desc, SketchContract.ItemEntry.sketch_image
        }, SketchContract.ItemEntry.project_id + " = " + "" + projectId + " and " +
                SketchContract.ItemEntry.office_id + " = " + "" + officeId + " and " + SketchContract.ItemEntry.user_id + " = " + "" + userId, null, null, null, null, null);
        if(mCursor!=null){
            while (mCursor.moveToNext()) {
                Sketch sketch1 = new Sketch();
                sketch1.set_ID(mCursor.getString(mCursor.getColumnIndex(SketchContract.ItemEntry._ID)));
                sketch1.setUser_id(mCursor.getString(mCursor.getColumnIndex(SketchContract.ItemEntry.user_id)));
                sketch1.setProject_id(mCursor.getString(mCursor.getColumnIndex(SketchContract.ItemEntry.project_id)));
                sketch1.setOffice_id(mCursor.getString(mCursor.getColumnIndex(SketchContract.ItemEntry.office_id)));
                sketch1.setSketch_image_Desc(mCursor.getString(mCursor.getColumnIndex(SketchContract.ItemEntry.sketch_image_desc)));
                int index = mCursor.getColumnIndexOrThrow(SketchContract.ItemEntry.sketch_image);
                byte[] imgByte = mCursor.getBlob(index);



                Bitmap bitmap = BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length );

                sketch1.setSketch_image(bitmap);

                sketches.add(sketch1);
            }
        }

        return sketches;
    }


    public List<Sketch> getSketchOfficeSending(String projectId , String officeId , String userId) throws SQLException {
        List<Sketch> sketches = new ArrayList<>();

        final SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCursor = db.query(true, SKETCH_TABLE_NAME, new String[]{SketchContract.ItemEntry._ID, SketchContract.ItemEntry.user_id, SketchContract.ItemEntry.project_id,SketchContract.ItemEntry.minimum_status,
                SketchContract.ItemEntry.office_id, SketchContract.ItemEntry.sketch_image_desc, SketchContract.ItemEntry.sketch_image
        }, SketchContract.ItemEntry.project_id + " = " + "" + projectId + " and " +
                SketchContract.ItemEntry.office_id + " = " + "" +
                officeId + " and " + SketchContract.ItemEntry.user_id + " = " + "" +
                userId, null, null, null, null, null);
        if(mCursor!=null){
            while (mCursor.moveToNext()) {
                Sketch sketch1 = new Sketch();
                sketch1.set_ID(mCursor.getString(mCursor.getColumnIndex(SketchContract.ItemEntry._ID)));
                sketch1.setUser_id(mCursor.getString(mCursor.getColumnIndex(SketchContract.ItemEntry.user_id)));
                sketch1.setProject_id(mCursor.getString(mCursor.getColumnIndex(SketchContract.ItemEntry.project_id)));
                sketch1.setOffice_id(mCursor.getString(mCursor.getColumnIndex(SketchContract.ItemEntry.office_id)));
                sketch1.setSketch_image_Desc(mCursor.getString(mCursor.getColumnIndex(SketchContract.ItemEntry.sketch_image_desc)));
                int index = mCursor.getColumnIndexOrThrow(SketchContract.ItemEntry.sketch_image);
                byte[] imgByte = mCursor.getBlob(index);

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 4;

                Bitmap bitmap = BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length , options);

                sketch1.setSketch_image(bitmap);

                sketches.add(sketch1);
            }
        }

        return sketches;
    }

    public List<Sketch> getSketchOfficeByUserId(String userId , String officeId) throws SQLException {
        List<Sketch> sketches = new ArrayList<>();

        final SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCursor = db.query(true, SKETCH_TABLE_NAME, new String[]{SketchContract.ItemEntry._ID, SketchContract.ItemEntry.user_id, SketchContract.ItemEntry.project_id,
                SketchContract.ItemEntry.office_id, SketchContract.ItemEntry.sketch_image_desc, SketchContract.ItemEntry.sketch_image
        }, SketchContract.ItemEntry.user_id + " = " + "" + userId + " and " + SketchContract.ItemEntry.office_id + " = " + officeId, null, null, null, null, null);
        if(mCursor!=null){
            while (mCursor.moveToNext()) {
                Sketch sketch1 = new Sketch();
                sketch1.set_ID(mCursor.getString(mCursor.getColumnIndex(SketchContract.ItemEntry._ID)));
                sketch1.setUser_id(mCursor.getString(mCursor.getColumnIndex(SketchContract.ItemEntry.user_id)));
                sketch1.setProject_id(mCursor.getString(mCursor.getColumnIndex(SketchContract.ItemEntry.project_id)));
                sketch1.setOffice_id(mCursor.getString(mCursor.getColumnIndex(SketchContract.ItemEntry.office_id)));
                sketch1.setSketch_image_Desc(mCursor.getString(mCursor.getColumnIndex(SketchContract.ItemEntry.sketch_image_desc)));

                int index = mCursor.getColumnIndexOrThrow(SketchContract.ItemEntry.sketch_image);
                byte[] imgByte = mCursor.getBlob(index);



                Bitmap bitmap = BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length );

                sketch1.setSketch_image(bitmap);

                sketches.add(sketch1);
            }
        }

        return sketches;
    }

    public List<Sketch> getSketchOfficeByUserIdSending(String userId , String officeId) throws SQLException {
        List<Sketch> sketches = new ArrayList<>();

        final SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCursor = db.query(true, SKETCH_TABLE_NAME, new String[]{SketchContract.ItemEntry._ID, SketchContract.ItemEntry.user_id,
                SketchContract.ItemEntry.project_id, SketchContract.ItemEntry.minimum_status,
                SketchContract.ItemEntry.office_id, SketchContract.ItemEntry.sketch_image_desc, SketchContract.ItemEntry.sketch_image
        }, SketchContract.ItemEntry.user_id + " = " + "" + userId + " and " + SketchContract.ItemEntry.office_id + " = " + officeId, null, null, null, null, null);
        if(mCursor!=null){
            while (mCursor.moveToNext()) {
                Sketch sketch1 = new Sketch();
                String minimumStatus = mCursor.getString(mCursor.getColumnIndex(SketchContract.ItemEntry.minimum_status));
                sketch1.set_ID(mCursor.getString(mCursor.getColumnIndex(SketchContract.ItemEntry._ID)));
                sketch1.setUser_id(mCursor.getString(mCursor.getColumnIndex(SketchContract.ItemEntry.user_id)));
                sketch1.setProject_id(mCursor.getString(mCursor.getColumnIndex(SketchContract.ItemEntry.project_id)));
                sketch1.setOffice_id(mCursor.getString(mCursor.getColumnIndex(SketchContract.ItemEntry.office_id)));
                sketch1.setSketch_image_Desc(mCursor.getString(mCursor.getColumnIndex(SketchContract.ItemEntry.sketch_image_desc)));

                int index = mCursor.getColumnIndexOrThrow(SketchContract.ItemEntry.sketch_image);
                byte[] imgByte = mCursor.getBlob(index);

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 4;
                Bitmap bitmap;
                if (Integer.parseInt(minimumStatus) == 0) {
                    bitmap = BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length, options);
                } else {
                    bitmap = BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);
                }


                sketch1.setSketch_image(bitmap);

                sketches.add(sketch1);
            }
        }

        return sketches;
    }



    public boolean deleteInDoorImage(String inDoorImage)
    {
        final SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(INDOOR_TABLE_NAME, InDoorContract.ItemEntry._ID + "=" + inDoorImage, null) > 0;
    }

    public boolean deleteOutDoorImage(String outDoorImage)
    {
        final SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(OUTDOOR_TABLE_NAME, OutDoorContract.ItemEntry._ID + "=" + outDoorImage, null) > 0;
    }


    //=======================================================================================//
    public void addProjects(List<UserProjectsDetails> userProjectsDetails , String userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            for (UserProjectsDetails userProjectsDetails1 : userProjectsDetails) {
                values.put(ProjectsContract.ItemEntry.user_id, userId);
                values.put(ProjectsContract.ItemEntry.project_name, userProjectsDetails1.getProject_name());
                values.put(ProjectsContract.ItemEntry.project_id, userProjectsDetails1.getId());


                db.insert(PROJECT_TABLE_NAME, null, values);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }


    public List<UserProjectsDetails>  getProjects() {
        List<UserProjectsDetails>  userProjectsDetails = new ArrayList<>();
        // Get access to underlying database (read-only for query)
        final SQLiteDatabase db = this.getReadableDatabase();

        Cursor retCursor;
        retCursor =  db.query(PROJECT_TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);


        if(retCursor!=null){
            while (retCursor.moveToNext()) {
                UserProjectsDetails  userProjectsDetails1 = new UserProjectsDetails();
                userProjectsDetails1.setId( retCursor.getString(retCursor.getColumnIndex(ProjectsContract.ItemEntry.project_id)));
                userProjectsDetails1.setProject_name( retCursor.getString(retCursor.getColumnIndex(ProjectsContract.ItemEntry.project_name)));

                userProjectsDetails.add(userProjectsDetails1);
            }
        }
        
        return  userProjectsDetails;
    }



//    public Sketch getSketch(String project_id, String office_id, String user_id) {
//        Sketch sketch = null;
//        final SQLiteDatabase db = this.getReadableDatabase();
//        Cursor mCursor = db.query(true, SKETCH_TABLE_NAME
//                ,null
//                , SketchContract.ItemEntry.PROJECT_ID + " = " + project_id + " and " +
//                        SketchContract.ItemEntry.OFFICE_ID + " = " + office_id + " and " +
//                        SketchContract.ItemEntry.USER_ID + " = " + user_id
//                , null, null, null, null, null);
//        if (mCursor != null) {
//            while (mCursor.moveToNext()) {
//                sketch = new Sketch();
//                sketch.setProject_id(mCursor.getString(mCursor.getColumnIndex(SketchContract.ItemEntry.PROJECT_ID)));
//                sketch.setOffice_id(mCursor.getString(mCursor.getColumnIndex(SketchContract.ItemEntry.OFFICE_ID)));
//                sketch.setUser_id(mCursor.getString(mCursor.getColumnIndex(SketchContract.ItemEntry.USER_ID)));
//
//                int index = mCursor.getColumnIndexOrThrow(SketchContract.ItemEntry.SKETCH_IMAGE);
//                byte[] imgByte = mCursor.getBlob(index);
//                Bitmap bitmap = BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);
//
//                sketch.setSketch_image(bitmap);
//
//            }
//        }
//        return sketch;
//    }

    public void addPositions(List<PositionResponseDetails> positionResponseDetails) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(PositionsContract.ItemEntry.position_id, "0");
            values.put(PositionsContract.ItemEntry.project_id, "0");
            values.put(PositionsContract.ItemEntry.position_name, "اختر الوظيفة");
            db.insert(POSITION_TABLE_NAME, null, values);
            for (PositionResponseDetails positionResponseDetails1 : positionResponseDetails) {
                values.put(PositionsContract.ItemEntry.position_id, positionResponseDetails1.getPosition_id());
                values.put(PositionsContract.ItemEntry.project_id, positionResponseDetails1.getProject_id());
                values.put(PositionsContract.ItemEntry.position_name, positionResponseDetails1.getPosition_name());


                db.insert(POSITION_TABLE_NAME, null, values);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public void addPosition(PositionResponseDetails positionResponseDetails) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
                values.put(PositionsContract.ItemEntry.position_id, positionResponseDetails.getPosition_id());
                values.put(PositionsContract.ItemEntry.project_id, positionResponseDetails.getProject_id());
                values.put(PositionsContract.ItemEntry.position_name, positionResponseDetails.getPosition_name());
                db.insert(POSITION_TABLE_NAME, null, values);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public List<PositionResponseDetails>  getPositions() {
        List<PositionResponseDetails>  positionResponses = new ArrayList<>();
        // Get access to underlying database (read-only for query)
        final SQLiteDatabase db = this.getReadableDatabase();
        Cursor retCursor;
        retCursor =  db.query(POSITION_TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);
        if(retCursor!=null){
            while (retCursor.moveToNext()) {
                PositionResponseDetails  positionResponseDetails = new PositionResponseDetails();
                positionResponseDetails.setPosition_id( retCursor.getString(retCursor.getColumnIndex(PositionsContract.ItemEntry.position_id)));
                positionResponseDetails.setProject_id( retCursor.getString(retCursor.getColumnIndex(PositionsContract.ItemEntry.project_id)));
                positionResponseDetails.setPosition_name( retCursor.getString(retCursor.getColumnIndex(PositionsContract.ItemEntry.position_name)));
                positionResponses.add(positionResponseDetails);
            }
        }
        
        return  positionResponses;
    }


    public void addOffices(List<OfficeResponseDetails> officeResponseDetails) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            /*values.put(office_id, 0);
            values.put(district_id, 0);
            values.put(office_name, "اختر المكتب");
            values.put(OfficesContract.ItemEntry.project_id, 0);
            values.put(OfficesContract.ItemEntry.office_visit, "0");*/

            //db.insert(OFFICE_TABLE_NAME, null, values);
            for (OfficeResponseDetails officeResponseDetails1 : officeResponseDetails) {
                values.put(office_id, officeResponseDetails1.getOffice_id());
                values.put(district_id, officeResponseDetails1.getDistrict_id());
                values.put(office_name, officeResponseDetails1.getOffice_name());
                values.put(OfficesContract.ItemEntry.project_id, officeResponseDetails1.getProject_id());
                values.put(OfficesContract.ItemEntry.office_visit, "0");
                values.put(OfficesContract.ItemEntry.city_id, officeResponseDetails1.getCity_id());
                values.put(OfficesContract.ItemEntry.gov_id, officeResponseDetails1.getGov_id());

                db.insert(OFFICE_TABLE_NAME, null, values);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }




    public List<OfficeResponseDetails>  getOffices() {
        List<OfficeResponseDetails>  officeResponseDetails = new ArrayList<>();
        // Get access to underlying database (read-only for query)
        String officevisit="0";
        final SQLiteDatabase db = this.getReadableDatabase();

        Cursor retCursor;
        retCursor =  db.query(OFFICE_TABLE_NAME,

                        null,
                OfficesContract.ItemEntry.office_visit + " = "  +""+officevisit,
                null,
                null,
                null,
                null);


        if(retCursor!=null){
            while (retCursor.moveToNext()) {
                OfficeResponseDetails  officeResponseDetails1 = new OfficeResponseDetails();
                officeResponseDetails1.setOffice_id( retCursor.getString(retCursor.getColumnIndex(office_id)));

                officeResponseDetails1.setDistrict_id( retCursor.getString(retCursor.getColumnIndex(district_id)));

                officeResponseDetails1.setOffice_name( retCursor.getString(retCursor.getColumnIndex(office_name)));

                officeResponseDetails1.setProject_id( retCursor.getString(retCursor.getColumnIndex(OfficesContract.ItemEntry.project_id)));

                officeResponseDetails.add(officeResponseDetails1);
            }
        }
        
        return  officeResponseDetails;
    }

    public List<OfficeResponseDetails>  getOffices2(String officeid) {
        List<OfficeResponseDetails>  officeResponseDetails = new ArrayList<>();
        // Get access to underlying database (read-only for query)
        String officevisit="0";
        final SQLiteDatabase db = this.getReadableDatabase();

        Cursor retCursor;
        retCursor =  db.query(OFFICE_TABLE_NAME,

                null,
                OfficesContract.ItemEntry.office_id + " = "  +""+officeid,
                null,
                null,
                null,
                null);


        if(retCursor!=null){
            while (retCursor.moveToNext()) {
                OfficeResponseDetails  officeResponseDetails1 = new OfficeResponseDetails();
                officeResponseDetails1.setOffice_id( retCursor.getString(retCursor.getColumnIndex(office_id)));

                officeResponseDetails1.setDistrict_id( retCursor.getString(retCursor.getColumnIndex(district_id)));

                officeResponseDetails1.setOffice_name( retCursor.getString(retCursor.getColumnIndex(office_name)));

                officeResponseDetails1.setProject_id( retCursor.getString(retCursor.getColumnIndex(OfficesContract.ItemEntry.project_id)));

                officeResponseDetails.add(officeResponseDetails1);
            }
        }
        
        return  officeResponseDetails;
    }


    // TODO End Office Logic
    public void addGovernors(ArrayList<Governor> governors) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(GOV_ID, 0);
            values.put(GOV_NAME, "اختر المحافظة");
            db.insert(GOVERNOR_TABLE_NAME, null, values);
            for (Governor governor : governors) {
                values.put(GOV_ID, governor.getGov_id());
                values.put(GOV_NAME, governor.getGov_name());
                db.insert(GOVERNOR_TABLE_NAME, null, values);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }



     public void addCities(ArrayList<City> cities) {
         SQLiteDatabase db = this.getWritableDatabase();
         db.beginTransaction();
         try {
             ContentValues values = new ContentValues();
             values.put(CITY_ID, 0);
             values.put(CITY_GOVE_ID, 0);
             values.put(CITY_NAME, "اختر المدينة");
             //db.insert(City_TABLE_NAME, null, values);
             for (City city : cities) {
                 values.put(CITY_ID, city.getCity_id());
                 values.put(CITY_GOVE_ID, city.getGov_id());
                 values.put(CITY_NAME, city.getCity_name());
                 db.insert(City_TABLE_NAME, null, values);
             }
             values.put(CITY_ID, 1101);
             values.put(CITY_GOVE_ID, 1101);
             values.put(CITY_NAME, "اخرى");
             db.insert(City_TABLE_NAME, null, values);

             db.setTransactionSuccessful();
         } finally {
             db.endTransaction();
         }
     }



     public void addDistricts(ArrayList<District> districts) {
         SQLiteDatabase db = this.getWritableDatabase();
         db.beginTransaction();
         try {
             ContentValues values = new ContentValues();
             values.put(DISTRICT_ID, 0);
             values.put(DISTRICT_CITY_ID, 0);
             values.put(DISTRICT_NAME, "اختر الحى");
             //db.insert(DISTRICT_TABLE_NAME, null, values);
             for (District  district : districts) {
                 values.put(DISTRICT_ID, district.getDistrict_id());
                 values.put(DISTRICT_CITY_ID, district.getCity_id());
                 values.put(DISTRICT_NAME, district.getDistrict_name());
                 db.insert(DISTRICT_TABLE_NAME, null, values);
             }
             values.put(DISTRICT_ID, 1101);
             values.put(DISTRICT_CITY_ID, 1101);
             values.put(DISTRICT_NAME, "اخرى");
             db.insert(DISTRICT_TABLE_NAME, null, values);
             db.setTransactionSuccessful();
         } finally {
             db.endTransaction();
         }
     }


    public ArrayList<City> getCities() {
         ArrayList<City>  cities = new ArrayList<>();
         // Get access to underlying database (read-only for query)
         final SQLiteDatabase db = this.getReadableDatabase();

         Cursor retCursor;
         retCursor =  db.query(City_TABLE_NAME,
                 null,
                 null,
                 null,
                 null,
                 null,
                 null);


         if(retCursor!=null){
             while (retCursor.moveToNext()) {
                 City  city = new City();
                 // governor.setId(retCursor.getInt(retCursor.getColumnIndex(ItemContract.ItemEntry._ID)));
                 city.setGov_id( retCursor.getString(retCursor.getColumnIndex(CITY_ID)));
                 city.setCity_name( retCursor.getString(retCursor.getColumnIndex(CITY_NAME)));
                 city.setGov_id( retCursor.getString(retCursor.getColumnIndex(CITY_GOVE_ID)));
                 cities.add(city);
             }
         }
         
         return  cities;
     }

     public ArrayList<District>  getDistricts() {
         ArrayList<District>   districts = new ArrayList<>();
         // Get access to underlying database (read-only for query)
         final SQLiteDatabase db = this.getReadableDatabase();

         Cursor retCursor;
         retCursor =  db.query(DISTRICT_TABLE_NAME,
                 null,
                 null,
                 null,
                 null,
                 null,
                 null);


         if(retCursor!=null){
             while (retCursor.moveToNext()) {
                 District   district = new District();
                 // governor.setId(retCursor.getInt(retCursor.getColumnIndex(ItemContract.ItemEntry._ID)));
                 district.setCity_id( retCursor.getString(retCursor.getColumnIndex(DISTRICT_CITY_ID)));
                 district.setDistrict_id( retCursor.getString(retCursor.getColumnIndex(DISTRICT_ID)));
                 district.setDistrict_name( retCursor.getString(retCursor.getColumnIndex(DISTRICT_NAME)));
                 districts.add(district);
             }
         }
         
         return  districts;
     }


    public List<UserProjectsDetails> getUserProjects(String userId) throws SQLException {
        List<UserProjectsDetails> userProjectsDetails = new ArrayList<>();

        final SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCursor = db.query(true, PROJECT_TABLE_NAME,
                new String[] {ProjectsContract.ItemEntry.project_id  ,ProjectsContract.ItemEntry.project_name},
                ProjectsContract.ItemEntry.user_id + " = " + ""+userId, null, null, null, null, null);

        if(mCursor!=null){
            while (mCursor.moveToNext()) {
                UserProjectsDetails userProjectsDetails1 = new UserProjectsDetails();
                userProjectsDetails1.setId( mCursor.getString(mCursor.getColumnIndex(ProjectsContract.ItemEntry.project_id)));
                userProjectsDetails1.setProject_name( mCursor.getString(mCursor.getColumnIndex(ProjectsContract.ItemEntry.project_name)));
                userProjectsDetails.add(userProjectsDetails1);
            }
        }

        return userProjectsDetails;
    }

    public ArrayList<Governor> getGovernors() {
        ArrayList<Governor> governors = new ArrayList<>();
        // Get access to underlying database (read-only for query)
        final SQLiteDatabase db = this.getReadableDatabase();

        Cursor retCursor;
        retCursor = db.query(GOVERNOR_TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);


        if (retCursor != null) {
            while (retCursor.moveToNext()) {
                Governor governor = new Governor();
                governor.setGov_id(retCursor.getString(retCursor.getColumnIndex(GovernorContract.ItemEntry.GOV_ID)));
                governor.setGov_name(retCursor.getString(retCursor.getColumnIndex(GovernorContract.ItemEntry.GOV_NAME)));
                governors.add(governor);
            }
        }
        
        return governors;
    }


    /**
     * This method discards the old table of data and calls onCreate to recreate a new one.
     * This only occurs when the version number for this database (DATABASE_VERSION) is incremented.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + GOVERNOR_TABLE_NAME);
        onCreate(db);
    }

    public void deleteSavedGovernors()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from  " + GOVERNOR_TABLE_NAME);
        
    }

     public void deleteSavedCities()
     {
         SQLiteDatabase db = this.getWritableDatabase();
         db.execSQL("delete from  " + City_TABLE_NAME);
         
     }

     public void deleteSavedDistricts()
     {

         SQLiteDatabase db = this.getWritableDatabase();
         db.execSQL("delete from  " + DISTRICT_TABLE_NAME);
         
     }

      // TODO position Table
      public void deleteSavedPositionTable()
      {
          SQLiteDatabase db = this.getWritableDatabase();
          db.execSQL("delete from  " + POSITION_TABLE_NAME);
          
      }

    public void deleteSavedOfficesTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from  " + OFFICE_TABLE_NAME);
        
    }

    public void deleteSavedProjectsTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from  " + PROJECT_TABLE_NAME);
        
    }


    // By Mohammed Elamary
    public ArrayList<PositionResponseDetails> getCertainPosition(String projectId) throws SQLException {
        ArrayList<PositionResponseDetails> positions = new ArrayList<>();

        final SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCursor = db.query(true, POSITION_TABLE_NAME,
                new String[]{PositionsContract.ItemEntry.project_id,
                        PositionsContract.ItemEntry.position_id,
                        PositionsContract.ItemEntry.position_name},
                PositionsContract.ItemEntry.project_id + "=" + projectId + " or " + PositionsContract.ItemEntry.project_id + "=0",
                null, null, null, null, null);
        if (mCursor != null) {
            while (mCursor.moveToNext()) {
                PositionResponseDetails positionResponseDetails = new PositionResponseDetails();
                positionResponseDetails.setPosition_id(
                        mCursor.getString(mCursor.getColumnIndex(PositionsContract.ItemEntry.position_id)));
                positionResponseDetails.setPosition_name(
                        mCursor.getString(mCursor.getColumnIndex(PositionsContract.ItemEntry.position_name)));
                positionResponseDetails.setProject_id(
                        mCursor.getString(mCursor.getColumnIndex(PositionsContract.ItemEntry.project_id)));

                positions.add(positionResponseDetails);
            }
        }

        return positions;
    }

    // By Mohammed Elamary
    public void addJob(Job job) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();

        try {
            ContentValues values = new ContentValues();
            values.put(JobContract.ItemEntry.JOB_ID, job.getJob_id());
            values.put(JobContract.ItemEntry.JOB_NAME, job.getJob_name());
            values.put(JobContract.ItemEntry.COUNT, job.getCount());
            values.put(JobContract.ItemEntry.NOTE, job.getNote());
            values.put(JobContract.ItemEntry.OFFICE_ID, job.getOffice_id());
            values.put(JobContract.ItemEntry.PROJECT_ID, job.getProject_id());
            values.put(JobContract.ItemEntry.USER_ID, job.getUser_id());
            db.insert(JOB_TABLE_NAME, null, values);

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    //By Mohammed Elamary
    public ArrayList<Job> getJobs(String project_id , String office_id , String user_id) {
        ArrayList<Job> jobs = new ArrayList<>();
        final SQLiteDatabase db = this.getReadableDatabase();

        Cursor retCursor;
        retCursor = db.query(JOB_TABLE_NAME,
                null,
                        PROJECT_ID + "=" + project_id + " and " + OFFICE_ID + "=" + office_id
                        + " and " + USER_ID + "=" + user_id,
                null,
                null,
                null,
                null);


        if (retCursor != null) {
            while (retCursor.moveToNext()) {
                Job job = new Job();
                // governor.setId(retCursor.getInt(retCursor.getColumnIndex(ItemContract.ItemEntry._ID)));
                job.setJob_id(retCursor.getString(retCursor.getColumnIndex(JobContract.ItemEntry.JOB_ID)));
                job.setJob_name(retCursor.getString(retCursor.getColumnIndex(JobContract.ItemEntry.JOB_NAME)));
                job.setCount(retCursor.getString(retCursor.getColumnIndex(JobContract.ItemEntry.COUNT)));
                job.setNote(retCursor.getString(retCursor.getColumnIndex(JobContract.ItemEntry.NOTE)));
                job.setProject_id(retCursor.getString(retCursor.getColumnIndex(PROJECT_ID)));
                job.setUser_id(retCursor.getString(retCursor.getColumnIndex(JobContract.ItemEntry.USER_ID)));
                job.setOffice_id(retCursor.getString(retCursor.getColumnIndex(JobContract.ItemEntry.OFFICE_ID)));
                jobs.add(job);
            }
        }
        
        return jobs;
    }

    public ArrayList<Job> getJobsByUserId(String user_id , String officeId) {
        ArrayList<Job> jobs = new ArrayList<>();
        final SQLiteDatabase db = this.getReadableDatabase();

        Cursor retCursor;
        retCursor = db.query(JOB_TABLE_NAME,
                null,
                USER_ID + " = " + user_id + " and " +OFFICE_ID + " = " + officeId,
                null,
                null,
                null,
                null);


        if (retCursor != null) {
            while (retCursor.moveToNext()) {
                Job job = new Job();
                // governor.setId(retCursor.getInt(retCursor.getColumnIndex(ItemContract.ItemEntry._ID)));
                job.setJob_id(retCursor.getString(retCursor.getColumnIndex(JobContract.ItemEntry.JOB_ID)));
                job.setJob_name(retCursor.getString(retCursor.getColumnIndex(JobContract.ItemEntry.JOB_NAME)));
                job.setCount(retCursor.getString(retCursor.getColumnIndex(JobContract.ItemEntry.COUNT)));
                job.setNote(retCursor.getString(retCursor.getColumnIndex(JobContract.ItemEntry.NOTE)));
                job.setProject_id(retCursor.getString(retCursor.getColumnIndex(PROJECT_ID)));
                job.setUser_id(retCursor.getString(retCursor.getColumnIndex(JobContract.ItemEntry.USER_ID)));
                job.setOffice_id(retCursor.getString(retCursor.getColumnIndex(JobContract.ItemEntry.OFFICE_ID)));
                jobs.add(job);
            }
        }
        
        return jobs;
    }

    //By Mohammed Elamary
    public boolean updateJob(String jobId , Job job) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();

        args.put(JobContract.ItemEntry.JOB_ID, job.getJob_id());

        args.put(JobContract.ItemEntry.JOB_NAME, job.getJob_name());

        args.put(JobContract.ItemEntry.NOTE, job.getNote());

        args.put(JobContract.ItemEntry.COUNT, job.getCount());

        args.put(PROJECT_ID, job.getProject_id());

        args.put(JobContract.ItemEntry.USER_ID, job.getUser_id());

        args.put(JobContract.ItemEntry.OFFICE_ID, job.getOffice_id());

        return db.update(JOB_TABLE_NAME, args, JOB_ID + " = " + jobId + " and " +
                PROJECT_ID + " = " + job.getProject_id() + " and " + OFFICE_ID + " = " + job.getOffice_id()
                + " and " + USER_ID + " = " + job.getUser_id(), null) > 0;

    }
    public boolean updateNotesForUpdate(String userId , String projectId , String officeId , String notes , String visit_date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();

        args.put(NotesContract.ItemEntry.notes, notes);

        return db.update(Notes_TABLE_NAME, args, NotesContract.ItemEntry.user_id + " = " + userId  + " and " +
                NotesContract.ItemEntry.visit_date  + " = " + visit_date + " and " +
                NotesContract.ItemEntry.project_id  + " = " + projectId + " and " + NotesContract.ItemEntry.office_id  + " = " + officeId, null) > 0;

    }

    public boolean updateNotesForUpdate2(String userId , String projectId , String officeId , String notes , String visit_date
            , String electricty , String water, String bill_end , String colunms
            ,String doors , String secondary ,String camersnet) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();

        args.put(NotesContract.ItemEntry.notes, notes);
        args.put(NotesContract.ItemEntry.electricty, electricty);
        args.put(NotesContract.ItemEntry.water, water);
        args.put(NotesContract.ItemEntry.bill_end, bill_end);
        args.put(NotesContract.ItemEntry.colunms, colunms);
        args.put(NotesContract.ItemEntry.doors, doors);
        args.put(NotesContract.ItemEntry.secondary, secondary);
        args.put(NotesContract.ItemEntry.camersnet, camersnet);

        return db.update(Notes_TABLE_NAME, args, NotesContract.ItemEntry.user_id + " = " + userId  + " and " +
                NotesContract.ItemEntry.visit_date  + " = " + visit_date + " and " +
                NotesContract.ItemEntry.project_id  + " = " + projectId + " and " + NotesContract.ItemEntry.office_id  + " = " + officeId, null) > 0;

    }



    //by amr rabie

    public boolean updateOfficeVisit(String officeid , OfficeResponseDetails office ) {

         boolean returnResult ;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();

        args.put(OfficesContract.ItemEntry.office_visit, "1");


     returnResult =   db.update(OFFICE_TABLE_NAME, args, OFFICE_ID + " = " + officeid , null) > 0 ;
        return returnResult ;

    }


    public boolean deleteSurvey(Survey survey) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(SURVEy_TABLE_NAME,
                PROJECT_ID + " = " + survey.getProject_id() + " and " + OFFICE_ID + " = " + survey.getOffice_id()
                + " and " + USER_ID + " = " + survey.getUser_id(), null) > 0;
    }


    // By Mohammed Elamary
    public boolean deleteJob(Job job) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(JOB_TABLE_NAME, JOB_ID + " = " + job.getJob_id() + " and " +
                PROJECT_ID + " = " + job.getProject_id() + " and " + OFFICE_ID + " = " + job.getOffice_id()
                + " and " + USER_ID + " = " + job.getUser_id(), null) > 0;
    }
    //By Mohammed Elamary
    public boolean getACertainJob(String jobID, String officeID , String projectId ,String userId) {
        final SQLiteDatabase db = this.getReadableDatabase();
        Cursor retCursor;
        retCursor = db.query(JOB_TABLE_NAME,
                null,
                JobContract.ItemEntry.JOB_ID + " = " + jobID + " and "+JobContract.ItemEntry.OFFICE_ID + " = " + officeID+ " and "+JobContract.ItemEntry.PROJECT_ID + " = " + projectId+ " and "+JobContract.ItemEntry.USER_ID + " = " + userId ,
                null,
                null,
                null,
                null);

        if (retCursor.getCount() > 0) {
            
            return true;
        } else {
            
            return false;
        }
    }
    // By Mohammed Elamary
    public void addSketch(Sketch sketch) {
        SQLiteDatabase db = this.getWritableDatabase();
        byte[] imgByte = getBitmapAsByteArray(sketch.getSketch_image());
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(SketchContract.ItemEntry.office_id, sketch.getOffice_id());
            values.put(SketchContract.ItemEntry.project_id, sketch.getProject_id());
            values.put(SketchContract.ItemEntry.user_id, sketch.getUser_id());
            values.put(SketchContract.ItemEntry.minimum_status, sketch.getMinimumStatus());
            values.put(SketchContract.ItemEntry.sketch_image_desc, sketch.getSketch_image_Desc());
            values.put(SketchContract.ItemEntry.sketch_image, imgByte);
            db.insert(SKETCH_TABLE_NAME, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
        } finally {
            db.endTransaction();
        }
    }
    // By Mohammed Elamary
    public boolean deletSketch(String office_id, String user_id, String project_id, String sketchId) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(SKETCH_TABLE_NAME, SketchContract.ItemEntry.office_id +
                " = " + office_id + " and " +
                SketchContract.ItemEntry.user_id + " = " + user_id + " and " +
                SketchContract.ItemEntry._ID + " = " + sketchId + " and " +
                SketchContract.ItemEntry.project_id + " = " + project_id, null) > 0;
    }


//
//    public boolean isOfficeMutualWithOtherOffices(String projectId, String officeId, String userId) {
//        List<Survey> surveys = new ArrayList<>() ;
//        Survey  surveyss = new Survey();
//        final SQLiteDatabase db = this.getReadableDatabase();
//        Cursor retCursor;
//        boolean status=false;
//        retCursor =  db.query(SURVEy_TABLE_NAME,
//                null,
//                SurveyContract.ItemEntry.user_id +" = "+ ""+userId +" and " +SurveyContract.ItemEntry.project_id + " = "+projectId +" and " +SurveyContract.ItemEntry.office_id +" = "+""+officeId ,
//                null,
//                null,
//                null,
//                null);
//        if(retCursor!=null){
//            while (retCursor.moveToNext()) {
//                Survey  survey = new Survey();
//                survey.setProject_id( retCursor.getString(retCursor.getColumnIndex("project_id")));
//                survey.setTwo_offices(retCursor.getString(retCursor.getColumnIndex("two_offices")));
//                surveyss = survey ;
//
//                surveys.add(surveyss) ;
//
//
//
//            }
//        }
//
//
//
//
//        if (surveys.get(surveys.size()).getTwo_offices().equals("1")){
//            return  true;
//        }
//        else  {
//            return  false;
//        }
//
//
//
//
//
//    }

    public boolean  gettwoofficesstatus( String projectId , String officeId , String userid) {
        Survey  surveyss = new Survey();
        final SQLiteDatabase db = this.getReadableDatabase();
        Cursor retCursor;
        retCursor =  db.query(SURVEy_TABLE_NAME,
                null,
                SurveyContract.ItemEntry.user_id +" = "+ ""+userid +" and " +SurveyContract.ItemEntry.project_id + " = "+projectId +" and " +SurveyContract.ItemEntry.office_id +" = "+""+officeId ,
                null,
                null,
                null,
                null);
        if(retCursor!=null){
            while (retCursor.moveToNext()) {
                Survey  survey = new Survey();
                // governor.setId(retCursor.getInt(retCursor.getColumnIndex(ItemContract.ItemEntry._ID)));

                survey.setTwo_offices( retCursor.getString(retCursor.getColumnIndex("two_offices")));


                surveyss = survey ;

            }
        }


        String two_offices = surveyss.getTwo_offices();

        if(two_offices.equals("1")){
            return true;
        }else{
            return false;
        }
    }






    // By Mohammed Elamary
  /*  public ArrayList<PositionResponseDetails> getPositionsForUpdate(List<String> addedJobs) throws SQLException {
        ArrayList<PositionResponseDetails> positions = new ArrayList<>();

        final SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCursor = db.query(true, POSITION_TABLE_NAME,
                new String[]{PositionsContract.ItemEntry.project_id,
                        PositionsContract.ItemEntry.position_id,
                        PositionsContract.ItemEntry.position_name},
                PositionsContract.ItemEntry.project_id + "=" + projectId,
                null, null, null, null, null);
        if (mCursor != null) {
            while (mCursor.moveToNext()) {
                PositionResponseDetails positionResponseDetails = new PositionResponseDetails();
                positionResponseDetails.setPosition_id(
                        mCursor.getString(mCursor.getColumnIndex(PositionsContract.ItemEntry.position_id)));
                positionResponseDetails.setPosition_name(
                        mCursor.getString(mCursor.getColumnIndex(PositionsContract.ItemEntry.position_name)));
                positionResponseDetails.setProject_id(
                        mCursor.getString(mCursor.getColumnIndex(PositionsContract.ItemEntry.project_id)));
                positions.add(positionResponseDetails);
            }
        }

        return positions;
    }*/


    public   String  getSecondOfficeName( String projectId , String officeId , String userid) {
        Survey  surveyss = new Survey();
        final SQLiteDatabase db = this.getReadableDatabase();
        Cursor retCursor;
        retCursor =  db.query(SURVEy_TABLE_NAME,
                null,
                SurveyContract.ItemEntry.user_id +" = "+ ""+userid +" and " +SurveyContract.ItemEntry.project_id + " = "+projectId +" and " +SurveyContract.ItemEntry.office_id +" = "+""+officeId ,
                null,
                null,
                null,
                null);
        if(retCursor!=null){
            while (retCursor.moveToNext()) {
                Survey  survey = new Survey();
                // governor.setId(retCursor.getInt(retCursor.getColumnIndex(ItemContract.ItemEntry._ID)));

                survey.setOther_office_name( retCursor.getString(retCursor.getColumnIndex("other_office_name")));


                surveyss = survey ;

            }
        }


        String other_office_name = surveyss.getOther_office_name();

        return  other_office_name;
    }
}