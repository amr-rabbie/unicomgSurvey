package com.asi.unicomgroupsurvey.data.models.comments;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CommentsResponse {
    @SerializedName("offices")
    List<Comment> comments ;
    @SerializedName("success")
    String success ;
    @SerializedName("message")
    String message ;

    public CommentsResponse(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
