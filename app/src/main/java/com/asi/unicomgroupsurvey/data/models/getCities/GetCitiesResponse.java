package com.asi.unicomgroupsurvey.data.models.getCities;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetCitiesResponse {
    @SerializedName("cities")
    ArrayList<City> cities ;

    public GetCitiesResponse(ArrayList<City> cities) {
        this.cities = cities;
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public void setCities(ArrayList<City> cities) {
        this.cities = cities;
    }

}
