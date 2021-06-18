package com.asi.unicomgroupsurvey.Views.jobs;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.asi.unicomgroupsurvey.R;
import com.asi.unicomgroupsurvey.data.models.jops.Job;

import java.util.Collections;
import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private LayoutInflater inflater;
    public List<Job> data = Collections.emptyList();
    Job current;

    private JobItemClick lOnClickListener;
    public JobAdapter(JobItemClick listener) {
        lOnClickListener = listener;
    }
    public void setJobsData(List<Job> recipesIn, Context context) {
        data = recipesIn;
        mContext = context;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.job_item;

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, parent, false);
        MyHolder viewHolder = new MyHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        MyHolder myHolder = (MyHolder) holder;
        current = data.get(position);
        myHolder.jobTitle.setText(current.getJob_name());
        myHolder.jobCount.setText(  current.getCount());
        myHolder.jobNotes.setText(current.getNote());
        if(current.getNote() != null)
        if(current.getNote().equals("")){
            myHolder.yyy.setVisibility(View.GONE);
        }
        myHolder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lOnClickListener.deleteJob(v, position);
            }
        });

        myHolder.updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lOnClickListener.updateJob(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        ImageView deleteBtn, updateBtn;
        TextView jobTitle, jobCount, jobNotes , yyy;

        public MyHolder(View itemView) {
            super(itemView);
            deleteBtn = (ImageView) itemView.findViewById(R.id.delete_job_btn);
            updateBtn = (ImageView) itemView.findViewById(R.id.update_job_btn);

            jobTitle = (TextView) itemView.findViewById(R.id.job_title);
            jobCount = (TextView) itemView.findViewById(R.id.job_count);
            jobNotes = (TextView) itemView.findViewById(R.id.job_notes);
            yyy = itemView.findViewById(R.id.yyy);
        }
    }

    public void clear() {
        final int size = data.size();
        data.clear();
        notifyItemRangeRemoved(0, size);
    }
}