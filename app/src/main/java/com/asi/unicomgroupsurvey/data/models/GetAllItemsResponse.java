package com.asi.unicomgroupsurvey.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


// this is class for mock incoming response
public class GetAllItemsResponse {

    @SerializedName("items")
    private ArrayList<Item> items;

    public ArrayList<Item> getItems() {
        return items;
    }
}
