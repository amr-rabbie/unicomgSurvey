package com.asi.unicomgroupsurvey.data.models.comments;

import com.google.gson.annotations.SerializedName;

public class CityModel {
    @SerializedName("city_id")
    String city_id ;
    @SerializedName("gov_id")
    String gov_id ;
    @SerializedName("city_name")
    String city_name ;
    @SerializedName("governorate")
    GovModel governorate ;

    public CityModel(String city_id, String gov_id, String city_name, GovModel governorate) {
        this.city_id = city_id;
        this.gov_id = gov_id;
        this.city_name = city_name;
        this.governorate = governorate;
    }

    public CityModel() {
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getGov_id() {
        return gov_id;
    }

    public void setGov_id(String gov_id) {
        this.gov_id = gov_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public GovModel getGovernorate() {
        return governorate;
    }

    public void setGovernorate(GovModel governorate) {
        this.governorate = governorate;
    }

    @Override
    public String toString() {
        return "CityModel{" +
                "city_id='" + city_id + '\'' +
                ", gov_id='" + gov_id + '\'' +
                ", city_name='" + city_name + '\'' +
                ", governorate=" + governorate +
                '}';
    }
}
