package com.asi.unicomgroupsurvey.helper;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.asi.unicomgroupsurvey.R;

import butterknife.BindView;
import butterknife.ButterKnife;

// create view holder for single menu single item
public class ItemViewHolderForRoom extends RecyclerView.ViewHolder {


    @BindView(R.id.text_room_name_item)
    public TextView roomNameItem;

    @BindView(R.id.text_room_num_item)
    public TextView roomnumItem;

    @BindView(R.id.text_furn_name_item)
    public TextView furnNameItem;

    @BindView(R.id.imageView_edit_room_item)
    public ImageView imageViewRoomEdit;


    @BindView(R.id.imageView_delete_room_item)
    public ImageView imageViewRoomDelete;

    @BindView(R.id.room_images_list)
    public android.support.v7.widget.RecyclerView  recyclerView ;

    @BindView(R.id.status_layout)
    public LinearLayout status_layout;

    @BindView(R.id.status_value)
    public TextView status_value;

    @BindView(R.id.ooo)
    public TextView ooo;
    @BindView(R.id.zzz)
    public TextView zzz;
    public ItemViewHolderForRoom(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


}