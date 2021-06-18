package com.asi.unicomgroupsurvey.Views.out_door;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asi.unicomgroupsurvey.R;
import com.asi.unicomgroupsurvey.data.models.out_door.OutDoorImage;
import com.asi.unicomgroupsurvey.data.models.rooms.RoomDetails;
import com.asi.unicomgroupsurvey.helper.ItemViewHolderForOutDoorImage;
import com.asi.unicomgroupsurvey.helper.ItemViewHolderForRoom;
import com.asi.unicomgroupsurvey.listClickListener.ItemClickListenerForOutDoorImage;
import com.asi.unicomgroupsurvey.listClickListener.ItemClickListenerForRoom;

import java.util.List;

public class GetOutDoorAdapter extends RecyclerView.Adapter<ItemViewHolderForOutDoorImage> {

    private Context context;
    private List<OutDoorImage> items;
    private ItemClickListenerForOutDoorImage mItemClickListener;


    public GetOutDoorAdapter(Context context, List<OutDoorImage> items, ItemClickListenerForOutDoorImage itemClickListener){
        this.context = context;
        this.items = items;
        mItemClickListener = itemClickListener;
    }
    @Override
    public ItemViewHolderForOutDoorImage onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_out_door_image, parent, false);
        return new ItemViewHolderForOutDoorImage(view);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolderForOutDoorImage holder, final int position) {
        final OutDoorImage item = items.get(position);


        holder.imageViewOutDoor.setImageBitmap(item.getOutDoorImageBitmap());
        holder.txt_img_desc.setText(item.getOutDoorImageDesc());
        holder.btnOutDoorDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.onItemClickDelete(position,item,holder.itemView);

            }
        });


    }



    @Override
    public int getItemCount() {
        return items.size();
    }

}