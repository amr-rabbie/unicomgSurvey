package com.asi.unicomgroupsurvey.data.LocalSqlite.notes;

import android.provider.BaseColumns;

public class NotesContract {

    public static final class ItemEntry implements BaseColumns {

        // item table and column names
        public static final String Notes_TABLE_NAME = "note";

        // Since ItemEntry implements the interface "BaseColumns", it has an automatically produced
        // "_ID" column in addition to the two below

        public static final String notes = "notes";
        public static final String location = "location";
        public static final String office_id = "office_id";
        public static final String user_id = "user_id";
        public static final String project_id = "project_id";
        public static final String visit_date = "visit_date";

        public static final String electricty = "electricty";
        public static final String water = "water";
        public static final String bill_end = "bill_end";
        public static final String colunms = "colunms";
        public static final String doors = "doors";
        public static final String secondary = "secondary";
        public static final String camersnet = "camersnet";

    }
}
