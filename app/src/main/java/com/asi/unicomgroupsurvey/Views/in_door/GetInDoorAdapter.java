package com.asi.unicomgroupsurvey.Views.in_door;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asi.unicomgroupsurvey.R;
import com.asi.unicomgroupsurvey.data.models.in_door.InDoorImage;
import com.asi.unicomgroupsurvey.data.models.out_door.OutDoorImage;
import com.asi.unicomgroupsurvey.helper.ItemViewHolderForInDoorImage;
import com.asi.unicomgroupsurvey.helper.ItemViewHolderForOutDoorImage;
import com.asi.unicomgroupsurvey.listClickListener.ItemClickListenerForInDoorImage;
import com.asi.unicomgroupsurvey.listClickListener.ItemClickListenerForOutDoorImage;

import java.util.List;

public class GetInDoorAdapter extends RecyclerView.Adapter<ItemViewHolderForInDoorImage> {

    private Context context;
    private List<InDoorImage> items;
    private ItemClickListenerForInDoorImage mItemClickListener;


    public GetInDoorAdapter(Context context, List<InDoorImage> items, ItemClickListenerForInDoorImage itemClickListener){
        this.context = context;
        this.items = items;
        mItemClickListener = itemClickListener;
    }
    @Override
    public ItemViewHolderForInDoorImage onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_in_door_image, parent, false);
        return new ItemViewHolderForInDoorImage(view);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolderForInDoorImage holder, final int position) {
        final InDoorImage item = items.get(position);
        holder.imageViewInDoor.setImageBitmap(item.getInDoorImageBitmap());
        holder.inDoorDesc.setText(item.getIndoorImageDesc());
        holder.btnInDoorDelete.setOnClickListener(new View.OnClickListener() {
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