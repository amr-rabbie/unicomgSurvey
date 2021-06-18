package com.asi.unicomgroupsurvey.Adapters;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.asi.unicomgroupsurvey.R;
import com.asi.unicomgroupsurvey.data.models.getDistricts.District;


import java.util.List;

public class DistricSpinnerAdapter extends ArrayAdapter<String>{

    private final LayoutInflater mInflater;
    private final Context mContext;
    private final List<District> items;
    private final int mResource;

    public DistricSpinnerAdapter(@NonNull Context context, @LayoutRes int resource,
                                 @NonNull List objects) {
        super(context, resource, 0, objects);

        mContext = context;
        mInflater = LayoutInflater.from(context);
        mResource = resource;
        items = objects;
    }
    @Override
    public View getDropDownView(int position, @Nullable View convertView,
                                @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    @Override
    public @NonNull View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    private View createItemView(int position, View convertView, ViewGroup parent){
        final View view = mInflater.inflate(mResource, parent, false);
        TextView tvTitle = view.findViewById(R.id.tvTitle);
        if (items.size() !=0)
        {
            District offerData = items.get(position);
            tvTitle.setText(offerData.getDistrict_name());
        }


        return view;
    }
}
