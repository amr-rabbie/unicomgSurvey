package com.asi.unicomgroupsurvey.data.LocalSqlite.sketch;

import android.provider.BaseColumns;

public class SketchContract {
    public static final class ItemEntry implements BaseColumns {
        public static final String SKETCH_TABLE_NAME = "sketch";

        public static final String office_id     = "office_id";
        public static final String user_id       = "user_id";
        public static final String project_id    = "project_id";
        public static final String sketch_image_desc  = "sketch_image_desc" ;
        public static final String sketch_image  = "sketch_image" ;
        public static final String minimum_status  = "minimum_status" ;


    }
}
