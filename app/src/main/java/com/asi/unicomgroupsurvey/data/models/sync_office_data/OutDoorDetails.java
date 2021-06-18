package com.asi.unicomgroupsurvey.data.models.sync_office_data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by belal on 1/17/19.
 */

public class OutDoorDetails {

    @SerializedName("office_id")
    private String office_id;

    @SerializedName("image")
    private String image ;
    @SerializedName("img_desc")
    private String img_desc;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImg_desc() {
        return img_desc;
    }

    public void setImg_desc(String img_desc) {
        this.img_desc = img_desc;
    }

    public String getOffice_id() {
        return office_id;
    }

    public void setOffice_id(String office_id) {
        this.office_id = office_id;
    }

    @Override
    public String toString() {
        return "OutDoorDetails{" +
                "office_id='" + office_id + '\'' +
                ", image='" + image + '\'' +
                ", img_desc='" + img_desc + '\'' +
                '}';
    }
}
