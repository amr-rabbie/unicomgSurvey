package com.asi.unicomgroupsurvey.data.LocalSqlite.governors;

import android.provider.BaseColumns;

public class GovernorContract {
    public static final class ItemEntry implements BaseColumns {

        // item table and column names
        public static final String GOVERNOR_TABLE_NAME = "governor";

        // Since ItemEntry implements the interface "BaseColumns", it has an automatically produced
        // "_ID" column in addition to the two below

        public static final String GOV_ID = "gov_id";
        public static final String GOV_NAME = "gov_name";
    }
}
