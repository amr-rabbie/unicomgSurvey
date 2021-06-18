package com.asi.unicomgroupsurvey.data.LocalSqlite.out_door;

import android.provider.BaseColumns;

public class OutDoorContract {
    public static final class ItemEntry implements BaseColumns {

        // item table and column names
        public static final String OUTDOOR_TABLE_NAME = "outDoor";

        // Since ItemEntry implements the interface "BaseColumns", it has an automatically produced
        // "_ID" column in addition to the two below

        public static final String office_id = "office_id";
        public static final String project_id = "project_id";
        public static final String user_id = "user_id";
        public static final String out_door_image = "out_door_image";
        public static final String out_door_image_desc = "out_door_image_desc";
        public static final String minimum_status = "minimum_status";


    }
}
