package com.asi.unicomgroupsurvey.data.LocalSqlite.projects;

import android.provider.BaseColumns;

public class ProjectsContract {
    public static final class ItemEntry implements BaseColumns {

        // item table and column names
        public static final String PROJECT_TABLE_NAME = "Project";

        // Since ItemEntry implements the interface "BaseColumns", it has an automatically produced
        // "_ID" column in addition to the two below

        public static final String user_id = "user_id";
        public static final String project_id = "project_id";
        public static final String project_name = "project_name";

    }
}
