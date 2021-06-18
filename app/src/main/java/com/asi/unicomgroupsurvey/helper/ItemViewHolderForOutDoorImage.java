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
public class ItemViewHolderForOutDoorImage extends RecyclerView.ViewHolder {


    @BindView(R.id.image_out_door_item)
    public ImageView imageViewOutDoor;

    @BindView(R.id.button_out_door_delete_image)
    public Button btnOutDoorDelete;


    @BindView(R.id.txt_img_desc)
    public TextView txt_img_desc;




    public ItemViewHolderForOutDoorImage(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


}