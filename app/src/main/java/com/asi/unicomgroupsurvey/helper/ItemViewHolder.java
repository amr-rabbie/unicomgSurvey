package com.asi.unicomgroupsurvey.helper;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.asi.unicomgroupsurvey.R;

import butterknife.BindView;
import butterknife.ButterKnife;

// create view holder for single menu single item
public class ItemViewHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.text_project_name)
    public TextView tvName;

    @BindView(R.id.linear_next)
    public LinearLayout linearLayout;




    public ItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


}