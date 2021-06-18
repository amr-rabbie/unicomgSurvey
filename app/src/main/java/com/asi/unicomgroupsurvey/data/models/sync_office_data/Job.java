package com.asi.unicomgroupsurvey.data.models.sync_office_data;

import com.google.gson.annotations.SerializedName;

public class Job {

    @SerializedName("position_id")
    private String position_id;
    @SerializedName("count")
    private String count ;
    @SerializedName("note")
    private String note;


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

    @Override
    public String toString() {
        return "Job{" +
                "position_id='" + position_id + '\'' +
                ", count='" + count + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
