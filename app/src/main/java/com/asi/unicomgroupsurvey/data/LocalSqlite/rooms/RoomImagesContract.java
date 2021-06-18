package com.asi.unicomgroupsurvey.data.LocalSqlite.rooms;

import android.graphics.Bitmap;
import android.provider.BaseColumns;

public class RoomImagesContract {

    public static final class ItemEntry implements BaseColumns {

        // item table and column names
        public static final String ROOM_IMAGE_TABLE_NAME = "room_image";
        public static final String user_id = "user_id";
        public static final String project_id = "project_id";
        public static final String office_id = "office_id";
        public static final String room_id = "room_id";
        public static final String image_bitmap = "image_bitmap";
        public static final String minimum_status = "minimum_status";
    }
}
