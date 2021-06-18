package com.asi.unicomgroupsurvey.data.LocalSqlite.cities;

import android.provider.BaseColumns;

import com.google.gson.annotations.SerializedName;

public class CityContract {
    public static final class ItemEntry implements BaseColumns {

        // item table and column names
        public static final String City_TABLE_NAME = "city";

        // Since ItemEntry implements the interface "BaseColumns", it has an automatically produced
        // "_ID" column in addition to the two below

        public static final String CITY_ID = "city_id";
        public static final String CITY_GOVE_ID = "gov_id";
        public static final String CITY_NAME = "city_name";
    }
}
