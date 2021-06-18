package com.asi.unicomgroupsurvey.data.models.getGovernors;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetGovernorsResponse {
    @SerializedName("governorates")
    ArrayList<Governor> governors ;

    public GetGovernorsResponse(ArrayList<Governor> governors) {
        this.governors = governors;
    }

    public ArrayList<Governor> getGovernors() {
        return governors;
    }

    public void setGovernors(ArrayList<Governor> governors) {
        this.governors = governors;
    }

    @Override
    public String toString() {
        return "GetGovernorsResponse{" +
                "governors=" + governors +
                '}';
    }
}
