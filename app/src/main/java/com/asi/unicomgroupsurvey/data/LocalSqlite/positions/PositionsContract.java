package com.asi.unicomgroupsurvey.data.LocalSqlite.positions;

import android.provider.BaseColumns;

public class PositionsContract {
    public static final class ItemEntry implements BaseColumns {

        // item table and column names
        public static final String POSITION_TABLE_NAME = "position";

        // Since ItemEntry implements the interface "BaseColumns", it has an automatically produced
        // "_ID" column in addition to the two below

        public static final String position_id = "position_id";
        public static final String project_id = "project_id";
        public static final String position_name = "position_name";

    }
}
