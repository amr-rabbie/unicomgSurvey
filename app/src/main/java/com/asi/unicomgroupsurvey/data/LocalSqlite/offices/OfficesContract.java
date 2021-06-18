package com.asi.unicomgroupsurvey.data.LocalSqlite.offices;

import android.provider.BaseColumns;

public class OfficesContract {
    public static final class ItemEntry implements BaseColumns {

        // item table and column names
        public static final String OFFICE_TABLE_NAME = "office";

        // Since ItemEntry implements the interface "BaseColumns", it has an automatically produced
        // "_ID" column in addition to the two below

        public static final String office_id = "office_id";
        public static final String district_id = "district_id";
        public static final String office_name = "office_name";
        public static final String project_id = "project_id";
        public static final String office_visit = "office_visit";
        public static final String city_id = "city_id";
        public static final String gov_id = "gov_id";



    }
}
