package com.asi.unicomgroupsurvey.Views.comments;

import com.asi.unicomgroupsurvey.baseClass.BaseView;
import com.asi.unicomgroupsurvey.data.models.comments.Comment;
import com.asi.unicomgroupsurvey.data.models.jops.Job;
import com.asi.unicomgroupsurvey.data.models.positions.PositionResponseDetails;

import java.util.ArrayList;
import java.util.List;

public interface CommentsView extends BaseView {
    void showProgress();
    void hideProgress();
    void showMessage(String message, int mColor);
    void getComments(List<Comment> comments);

}
