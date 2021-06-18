package com.asi.unicomgroupsurvey.Views.jobs;

import android.view.View;

public interface JobItemClick {

    void deleteJob(View v, int position);
    void updateJob(View v, int position);
}
