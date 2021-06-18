package com.asi.unicomgroupsurvey.data.models.getGovernors;

import com.google.gson.annotations.SerializedName;

public class Governor {
    @SerializedName("gov_id")
    String gov_id;
    @SerializedName("gov_name")
    String gov_name;

    public Governor(String gov_id, String gov_name) {
        this.gov_id = gov_id;
        this.gov_name = gov_name;
    }

    public Governor() {

    }

    public String getGov_id() {
        return gov_id;
    }

    public void setGov_id(String gov_id) {
        this.gov_id = gov_id;
    }

    public String getGov_name() {
        return gov_name;
    }

    public void setGov_name(String gov_name) {
        this.gov_name = gov_name;
    }

    @Override
    public String toString() {
        return "Governor{" +
                "gov_id='" + gov_id + '\'' +
                ", gov_name='" + gov_name + '\'' +
                '}';
    }
}
