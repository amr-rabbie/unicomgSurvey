package com.asi.unicomgroupsurvey.data.LocalSqlite.rooms;

import android.provider.BaseColumns;

public class RoomsContract {
    public static final class ItemEntry implements BaseColumns {

        // item table and column names
        public static final String ROOM_TABLE_NAME = "room";

        // Since ItemEntry implements the interface "BaseColumns", it has an automatically produced
        // "_ID" column in addition to the two below

        public static final String user_id = "user_id";
        public static final String project_id = "project_id";
        public static final String office_id = "office_id";

        public static final String room_name = "room_name";
        public static final String room_count = "room_count";
        public static final String room_furniture = "room_furniture";
        public static final String is_mutual = "is_mutual";


    }
}
