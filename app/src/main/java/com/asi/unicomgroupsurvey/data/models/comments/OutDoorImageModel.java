package com.asi.unicomgroupsurvey.data.models.comments;

import com.google.gson.annotations.SerializedName;

public class OutDoorImageModel {

    @SerializedName("site_image_id")
    String site_image_id ;
    @SerializedName("office_data_id")
    String office_data_id ;
    @SerializedName("site_image_type")
    String site_image_type ;
    @SerializedName("image")
    String image ;

    //////
    @SerializedName("img_desc")
    String img_desc;
    //////

    public OutDoorImageModel(String site_image_id,
                             String office_data_id, String site_image_type, String image) {
        this.site_image_id = site_image_id;
        this.office_data_id = office_data_id;
        this.site_image_type = site_image_type;
        this.image = image;
    }

    public OutDoorImageModel() {
    }

    public String getSite_image_id() {
        return site_image_id;
    }

    public void setSite_image_id(String site_image_id) {
        this.site_image_id = site_image_id;
    }

    public String getOffice_data_id() {
        return office_data_id;
    }

    public void setOffice_data_id(String office_data_id) {
        this.office_data_id = office_data_id;
    }

    public String getSite_image_type() {
        return site_image_type;
    }

    public void setSite_image_type(String site_image_type) {
        this.site_image_type = site_image_type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    /////
    public String getImg_desc() {
        return img_desc;
    }

    public void setImg_desc(String img_desc) {
        this.img_desc = img_desc;
    }
    //////


    @Override
    public String toString() {
        return "OutDoorImageModel{" +
                "site_image_id='" + site_image_id + '\'' +
                ", office_data_id='" + office_data_id + '\'' +
                ", site_image_type='" + site_image_type + '\'' +
                ", image='" + image + '\'' +
                ", img_desc='" + img_desc + '\'' +
                '}';
    }
}
