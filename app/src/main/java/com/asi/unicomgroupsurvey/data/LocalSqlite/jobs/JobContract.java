package com.asi.unicomgroupsurvey.data.LocalSqlite.jobs;

import android.provider.BaseColumns;

public class JobContract {
    public static final class ItemEntry implements BaseColumns {

        // item table and column names
        public static final String JOB_TABLE_NAME = "job";

        // Since ItemEntry implements the interface "BaseColumns", it has an automatically produced
        // "_ID" column in addition to the two below
        public static final String JOB_ID = "job_id";
        public static final String USER_ID = "user_id";
        public static final String PROJECT_ID = "project_id";
        public static final String OFFICE_ID = "office_id";
        public static final String COUNT = "count";
        public static final String NOTE = "note";
        public static final String JOB_NAME = "job_name";
    }
}
