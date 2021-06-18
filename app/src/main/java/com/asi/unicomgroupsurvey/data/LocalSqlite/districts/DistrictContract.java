package com.asi.unicomgroupsurvey.data.LocalSqlite.districts;

import android.provider.BaseColumns;

public class DistrictContract {
    public static final class ItemEntry implements BaseColumns {

        // item table and column names
        public static final String DISTRICT_TABLE_NAME = "district";

        // Since ItemEntry implements the interface "BaseColumns", it has an automatically produced
        // "_ID" column in addition to the two below


        public static final String DISTRICT_ID = "district_id";
        public static final String DISTRICT_CITY_ID = "city_id";
        public static final String DISTRICT_NAME = "district_name";
    }
}
