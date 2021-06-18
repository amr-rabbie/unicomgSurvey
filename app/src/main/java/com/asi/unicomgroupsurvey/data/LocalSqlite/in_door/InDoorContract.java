package com.asi.unicomgroupsurvey.data.LocalSqlite.in_door;

import android.provider.BaseColumns;

public class InDoorContract {
    public static final class ItemEntry implements BaseColumns {

        // item table and column names
        public static final String INDOOR_TABLE_NAME = "inDoor";

        public static final String office_id = "office_id";
        public static final String project_id = "project_id";
        public static final String user_id = "user_id";
        public static final String indoor_door_image = "in_door_image";
        public static final String minimum_status = "minimum_status";
        public static final String indoor_door_image_desc = "indoor_door_image_desc";


    }
}
