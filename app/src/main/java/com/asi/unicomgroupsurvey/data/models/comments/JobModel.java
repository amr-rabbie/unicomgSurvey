package com.asi.unicomgroupsurvey.data.models.comments;

import com.google.gson.annotations.SerializedName;

public class JobModel {

    @SerializedName("office_data_id")
    String office_data_id ;
    @SerializedName("position_id")
    String position_id ;
    @SerializedName("count")
    String count ;
    @SerializedName("note")
    String note ;
    @SerializedName("position")
    PositionModel position ;

    public JobModel(String office_data_id,
                    String position_id, String count, String note, PositionModel position) {
        this.office_data_id = office_data_id;
        this.position_id = position_id;
        this.count = count;
        this.note = note;
        this.position = position;
    }

    public String getOffice_data_id() {
        return office_data_id;
    }

    public void setOffice_data_id(String office_data_id) {
        this.office_data_id = office_data_id;
    }

    public String getPosition_id() {
        return position_id;
    }

    public void setPosition_id(String position_id) {
        this.position_id = position_id;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public PositionModel getPosition() {
        return position;
    }

    public void setPosition(PositionModel position) {
        this.position = position;
    }

    public JobModel() {
    }


    @Override
    public String toString() {
        return "JobModel{" +
                "office_data_id='" + office_data_id + '\'' +
                ", position_id='" + position_id + '\'' +
                ", count='" + count + '\'' +
                ", note='" + note + '\'' +
                ", position=" + position +
                '}';
    }
}
