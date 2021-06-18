package com.asi.unicomgroupsurvey.helper;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.asi.unicomgroupsurvey.R;

import butterknife.BindView;
import butterknife.ButterKnife;

// create view holder for single menu single item
public class ItemViewHolderForSavedOfflineList extends RecyclerView.ViewHolder {


    @BindView(R.id.text_office_name_saved_offline_list)
    public TextView txtOfficeName;

    @BindView(R.id.image_next_get_data_saved_of_survey)
    public ImageView imgNextOfflineSavedList;


    @BindView(R.id.button_sync_saved_data)
    public Button btnSyncOfflineData;



    public ItemViewHolderForSavedOfflineList(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


}