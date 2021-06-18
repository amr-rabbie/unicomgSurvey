package com.asi.unicomgroupsurvey.helper;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.asi.unicomgroupsurvey.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RoomImageViewHolder  extends RecyclerView.ViewHolder {


    public ImageView imageViewInDoor;

    public Button btnInDoorDelete;

    public RoomImageViewHolder(View itemView) {
        super(itemView);
        imageViewInDoor = itemView.findViewById(R.id.image_room_item) ;
        btnInDoorDelete = itemView.findViewById(R.id.button_room_delete_image) ;
    }


}
