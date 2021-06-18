package com.asi.unicomgroupsurvey.Views.comments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.asi.unicomgroupsurvey.R;
import com.asi.unicomgroupsurvey.data.LocalSqlite.ItemDbHelper;
import com.asi.unicomgroupsurvey.data.models.comments.Comment;
import com.asi.unicomgroupsurvey.data.models.get_user_projects.UserProjectsDetails;
import com.asi.unicomgroupsurvey.helper.Constants;

import java.util.Collections;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private LayoutInflater inflater;
    public List<Comment> data = Collections.emptyList();
    Comment current;

    private CommentItemClick lOnClickListener;
    public CommentAdapter(CommentItemClick listener) {
        lOnClickListener = listener;
    }
    public void setCommentData(List<Comment> recipesIn, Context context) {
        data = recipesIn;
        mContext = context;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.comment_item;

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, parent, false);
        MyHolder viewHolder = new MyHolder(view);

        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        MyHolder myHolder = (MyHolder) holder;
        current = data.get(position);
        myHolder.project_name.setText(current.getOffice().getOffice_name());
        myHolder.comment_id.setText(current.getFeedback_message());
        myHolder.ggg.setText(current.getOffice().getProject().getProject_name());
        myHolder.commentEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lOnClickListener.EditBtn(v, position);
            }
        });
    }
    @Override
    public int getItemCount() {
        return data.size();
    }
    class MyHolder extends RecyclerView.ViewHolder {

        TextView project_name ,comment_id ;
        ImageView commentEdit ;
        TextView ggg;

        public MyHolder(View itemView) {
            super(itemView);
            project_name = (TextView) itemView.findViewById(R.id.project_name);
            comment_id = (TextView) itemView.findViewById(R.id.comment_id);
            ggg = itemView.findViewById(R.id.g);
            commentEdit = (ImageView) itemView.findViewById(R.id.commentEdit);
        }
    }
    public void clear() {
        final int size = data.size();
        data.clear();
        notifyItemRangeRemoved(0, size);
    }

//    public String getProjectName(String project_id) {
//        ItemDbHelper itemDbHelper = new ItemDbHelper(mContext);
//        List<UserProjectsDetails> gg = itemDbHelper.getUserProjects(Constants.getuserId(mContext));
//
//        for (UserProjectsDetails f : gg) {
//            if (f.getId().equals(project_id)) {
//                return f.getProject_name();
//            }
//
//        }
//        return "";
//    }

}