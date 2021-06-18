package com.asi.unicomgroupsurvey.Views.get_user_projects;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.asi.unicomgroupsurvey.R;
import com.asi.unicomgroupsurvey.data.models.get_user_projects.UserProjectsDetails;
import com.asi.unicomgroupsurvey.helper.ItemViewHolder;
import com.asi.unicomgroupsurvey.helper.Utilities;
import com.asi.unicomgroupsurvey.listClickListener.ItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class GetUserProjectAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private Context context;
    private List<UserProjectsDetails> items;
    private ItemClickListener mItemClickListener;

    public GetUserProjectAdapter(Context context, List<UserProjectsDetails> items, ItemClickListener itemClickListener){
        this.context = context;
        this.items = items;
        mItemClickListener = itemClickListener;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user_project, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        final UserProjectsDetails item = items.get(position);
        holder.tvName.setText(item.getProject_name());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemClickListener.onItemClick(position,item,holder.itemView);
            }
        });


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}