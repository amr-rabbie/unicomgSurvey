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
public class ItemViewHolderForInDoorImage extends RecyclerView.ViewHolder {


    @BindView(R.id.image_in_door_item)
    public ImageView imageViewInDoor;

    @BindView(R.id.button_in_door_delete_image)
    public Button btnInDoorDelete;

    @BindView(R.id.txt_img_desc)
    public TextView inDoorDesc;


    public ItemViewHolderForInDoorImage(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


}