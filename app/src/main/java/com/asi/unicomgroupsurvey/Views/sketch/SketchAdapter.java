package com.asi.unicomgroupsurvey.Views.sketch;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asi.unicomgroupsurvey.R;
import com.asi.unicomgroupsurvey.data.models.in_door.InDoorImage;
import com.asi.unicomgroupsurvey.data.models.sketch.Sketch;
import com.asi.unicomgroupsurvey.helper.ItemViewHolderForInDoorImage;
import com.asi.unicomgroupsurvey.listClickListener.ItemClickListenerForInDoorImage;
import com.asi.unicomgroupsurvey.listClickListener.ItemClickListenerForSketch;

import java.util.List;

public class SketchAdapter extends RecyclerView.Adapter<ItemViewHolderForInDoorImage> {

    private Context context;
    private List<Sketch> items;
    private ItemClickListenerForSketch mItemClickListener;


    public SketchAdapter(Context context, List<Sketch> items, ItemClickListenerForSketch itemClickListener){
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
        final Sketch item = items.get(position);
        holder.imageViewInDoor.setImageBitmap(item.getSketch_image());
        holder.inDoorDesc.setText(item.getSketch_image_Desc());
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