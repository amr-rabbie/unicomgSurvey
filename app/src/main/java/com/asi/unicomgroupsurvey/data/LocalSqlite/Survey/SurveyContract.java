package com.asi.unicomgroupsurvey.data.LocalSqlite.Survey;

import android.provider.BaseColumns;

public class SurveyContract {
    public static final class ItemEntry implements BaseColumns {

        // item table and column names
        public static final String SURVEy_TABLE_NAME = "survey";

        // Since ItemEntry implements the interface "BaseColumns", it has an automatically produced
        // "_ID" column in addition to the two below

        public static final String gov_id = "gov_id";
        public static final String gov_name = "gov_name";
        public static final String city_id = "city_id";
        public static final String city_name = "city_name";
        public static final String district_id = "district_id";
        public static final String district_name = "district_name";
        public static final String address = "address";
        public static final String phone = "phone";
        public static final String hasInternet = "hasInternet";
        public static final String isNetwork = "isNetwork";
        public static final String internetSeed = "internetSeed";
        public static final String office_id = "office_id";
        public static final String office_name = "office_name";
        public static final String office_visit = "office_visit";
        public static final String shiftType = "shiftType";
        public static final String OwnerShipType = "OwnerShipType";
        public static final String morning_shift_from = "morning_shift_from";
        public static final String morning_shift_to = "morning_shift_to";
        public static final String evening_shift_from = "evening_shift_from";
        public static final String evening_shift_to = "evening_shift_to";
        public static final String computer_count = "computer_count";
        public static final String computer_notes = "computer_notes";
        public static final String printers_count = "printers_count";
        public static final String printers_notes = "printers_notes";
        public static final String scanners_count = "scanners_count";
        public static final String scanners_notes = "scanners_notes";
        public static final String other_city = "other_city";
        public static final String other_district = "other_district";
        public static final String user_id = "user_id";
        public static final String project_id = "project_id";
        //public static final String shiftType = "shiftType";
        //public static final String OwnerShipType = "OwnerShipType";

        public static final String net_inside = "net_inside";
        public static final String net_outside = "net_outside";
        public static final String ins_out_notes = "ins_out_notes";
        public static final String others_macs = "others_macs";
        public static final String others_macs_notes = "others_macs_notes";

        public static final String two_offices = "two_offices";
        public static final String other_office_id = "other_office_id";
        public static final String other_office_name = "other_office_name";

        public static final String is_arabic = "is_arabic";
        public static final String is_kurdish = "is_kurdish";
        public static final String both = "both";


    }

}
