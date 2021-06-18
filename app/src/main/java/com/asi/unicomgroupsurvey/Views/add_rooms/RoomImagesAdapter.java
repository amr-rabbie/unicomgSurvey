package com.asi.unicomgroupsurvey.Views.add_rooms;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asi.unicomgroupsurvey.R;
import com.asi.unicomgroupsurvey.data.models.rooms.RoomImage;
import com.asi.unicomgroupsurvey.helper.RoomImageViewHolder;
import com.asi.unicomgroupsurvey.listClickListener.RoomItemClick;

import java.util.List;

public class RoomImagesAdapter extends RecyclerView.Adapter<RoomImageViewHolder> {

    private Context context;
    private List<RoomImage> items;
    private RoomItemClick mItemClickListener;
    private int yy;

    public RoomImagesAdapter(Context context, List<RoomImage> items, RoomItemClick itemClickListener, int hh) {
        this.context = context;
        this.items = items;
        mItemClickListener = itemClickListener;
        this.yy = hh;
    }

    @Override
    public RoomImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.room_image_item, parent, false);
        return new RoomImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RoomImageViewHolder holder, final int position) {
        final RoomImage item = items.get(position);
        holder.imageViewInDoor.setImageBitmap(item.getImageBitmap());
        if (yy == 0) {
            holder.btnInDoorDelete.setVisibility(View.GONE);
        }
        holder.btnInDoorDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.onItemClickDelete(position, item, holder.itemView);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}