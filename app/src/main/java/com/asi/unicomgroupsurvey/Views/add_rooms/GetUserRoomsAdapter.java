package com.asi.unicomgroupsurvey.Views.add_rooms;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asi.unicomgroupsurvey.R;
import com.asi.unicomgroupsurvey.data.models.rooms.RoomDetails;
import com.asi.unicomgroupsurvey.helper.Constants;
import com.asi.unicomgroupsurvey.helper.ItemViewHolderForRoom;
import com.asi.unicomgroupsurvey.listClickListener.ItemClickListenerForRoom;

import java.util.List;

public class GetUserRoomsAdapter extends RecyclerView.Adapter<ItemViewHolderForRoom>  {

    private Context context;
    private List<RoomDetails> items;
    private ItemClickListenerForRoom mItemClickListener;
private int bb ;
    AddRoomsPresenter addRoomsPresenter1;

    public GetUserRoomsAdapter(AddRoomsPresenter addRoomsPresenter, Context context, List<RoomDetails> items, ItemClickListenerForRoom itemClickListener, int i) {
        this.context = context;
        this.items = items;
        mItemClickListener = itemClickListener;
        this.bb = i ;
        addRoomsPresenter1 = addRoomsPresenter;
    }
    @Override
    public ItemViewHolderForRoom onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_room, parent, false);
        return new ItemViewHolderForRoom(view);
    }


    @Override
    public void onBindViewHolder(final ItemViewHolderForRoom holder, final int position) {
        final RoomDetails item = items.get(position);


        holder.roomnumItem.setText(item.getRoomCount());
        holder.furnNameItem.setText(item.getRoomFurniture());
        holder.roomNameItem.setText(item.getRoomName());
        ///
        if (item.getIs_mutual().equals("1")) {
            holder.status_layout.setVisibility(View.VISIBLE);
            holder.status_value.setText(addRoomsPresenter1.getOfficeName(Constants.getProjectId(context), Constants.getOfficeId(context), Constants.getuserId(context)));
        }
        //
        GridLayoutManager linearLayoutManager = new GridLayoutManager(context,4);
        holder.recyclerView.setLayoutManager(linearLayoutManager);

        if(item.getRoomImages().size() > 0){
            holder.recyclerView.setVisibility(View.VISIBLE);

            RoomImagesAdapter getInDoorAdapter = new RoomImagesAdapter(context , item.getRoomImages(),new RoomsActivity() , bb);
            holder.recyclerView.setAdapter(getInDoorAdapter);
        }
        else
        {
            holder.recyclerView.setVisibility(View.GONE);

        }

        if(item.getRoomCount() != null)
        if (item.getRoomCount().equals("")) {
            holder.ooo.setVisibility(View.GONE);
        }
        if(item.getRoomFurniture() != null)
            if (item.getRoomFurniture().equals("")) {
            holder.zzz.setVisibility(View.GONE);

        }
            holder.imageViewRoomEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mItemClickListener.onItemClick(position,item,holder.itemView);

                }
            });


        holder.imageViewRoomDelete.setOnClickListener(new View.OnClickListener() {
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