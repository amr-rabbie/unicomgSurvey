package com.asi.unicomgroupsurvey.data.models.comments;

import com.google.gson.annotations.SerializedName;

public class DistrictModel {
    @SerializedName("district_id")
    String district_id ;
    @SerializedName("city_id")
    String city_id ;
    @SerializedName("district_name")
    String district_name ;
    @SerializedName("city")
    CityModel city ;

    public DistrictModel(String district_id, String city_id, String district_name, CityModel city) {
        this.district_id = district_id;
        this.city_id = city_id;
        this.district_name = district_name;
        this.city = city;
    }

    public DistrictModel() {
    }

    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public CityModel getCity() {
        return city;
    }

    public void setCity(CityModel city) {
        this.city = city;
    }


    @Override
    public String toString() {
        return "DistrictModel{" +
                "district_id='" + district_id + '\'' +
                ", city_id='" + city_id + '\'' +
                ", district_name='" + district_name + '\'' +
                ", city=" + city +
                '}';
    }
}
