package com.asi.unicomgroupsurvey.Views.saved_offline_survey;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asi.unicomgroupsurvey.R;
import com.asi.unicomgroupsurvey.Views.Survey.SurveyFragment;
import com.asi.unicomgroupsurvey.Views.Survey.UpdateSurveyFragment;
import com.asi.unicomgroupsurvey.data.models.Surveys.Survey;
import com.asi.unicomgroupsurvey.data.models.rooms.RoomDetails;
import com.asi.unicomgroupsurvey.helper.Constants;
import com.asi.unicomgroupsurvey.helper.ItemViewHolderForRoom;
import com.asi.unicomgroupsurvey.helper.ItemViewHolderForSavedOfflineList;
import com.asi.unicomgroupsurvey.listClickListener.ItemClickListenerForRoom;
import com.asi.unicomgroupsurvey.listClickListener.ItemClickListenerForSavedOfflineList;

import java.util.List;

public class GetSavedOfflineSurveyAdapter extends RecyclerView.Adapter<ItemViewHolderForSavedOfflineList> {

    private Context context;
    private List<Survey> items;
    private ItemClickListenerForSavedOfflineList mItemClickListener;


    public GetSavedOfflineSurveyAdapter(Context context, List<Survey> items, ItemClickListenerForSavedOfflineList itemClickListener){
        this.context = context;
        this.items = items;
        mItemClickListener = itemClickListener;
    }
    @Override
    public ItemViewHolderForSavedOfflineList onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_show_saved_offline_survey, parent, false);
        return new ItemViewHolderForSavedOfflineList(view);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolderForSavedOfflineList holder, final int position) {
        final Survey item = items.get(position);


        holder.txtOfficeName.setText(item.getOffice_name());
        holder.imgNextOfflineSavedList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mItemClickListener.onItemClickNext(position,item,holder.itemView);

            }
        });


        holder.btnSyncOfflineData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.onItemClickSaved(position,item,holder.itemView);

            }
        });
    }



    @Override
    public int getItemCount() {
        return items.size();
    }

}