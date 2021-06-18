package com.asi.unicomgroupsurvey.data.models.getDistricts;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetDistrictsResponse {
    @SerializedName("districts")
    ArrayList<District>districts ;

    public GetDistrictsResponse(ArrayList<District> districts) {
        this.districts = districts;
    }

    public ArrayList<District> getDistricts() {
        return districts;
    }

    public void setDistricts(ArrayList<District> districts) {
        this.districts = districts;
    }

    @Override
    public String toString() {
        return "GetDistrictsResponse{" +
                "districts=" + districts +
                '}';
    }
}
