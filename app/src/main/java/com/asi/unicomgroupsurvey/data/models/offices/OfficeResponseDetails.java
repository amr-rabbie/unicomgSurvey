package com.asi.unicomgroupsurvey.data.models.offices;

import com.google.gson.annotations.SerializedName;

public class OfficeResponseDetails {

    @SerializedName("office_id")
    private  String office_id ;


    @SerializedName("district_id")
    private  String district_id ;

    @SerializedName("office_name")
    private  String office_name ;

    @SerializedName("project_id")
    private  String project_id ;

    @SerializedName("city_id")
    private  String city_id ;

    @SerializedName("gov_id")
    private  String gov_id ;

    /*@SerializedName("office_visit")
    private  String office_visit ;*/

    public String getOffice_id() {
        return office_id;
    }

    public void setOffice_id(String office_id) {
        this.office_id = office_id;
    }

    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    public String getOffice_name() {
        return office_name;
    }

    public void setOffice_name(String office_name) {
        this.office_name = office_name;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
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

    /*public String getOffice_visit() {
        return office_visit;
    }

    public void setOffice_visit(String office_visit) {
        this.office_visit = office_visit;
    }*/

    //public OfficeResponseDetails(String office_id, String district_id, String office_name, String project_id, String office_visit) {
    /*public OfficeResponseDetails(String office_id, String district_id, String office_name, String project_id ) {
        this.office_id = office_id;
        this.district_id = district_id;
        this.office_name = office_name;
        this.project_id = project_id;
        //this.office_visit = office_visit;
    }*/

    public OfficeResponseDetails(String office_id, String district_id, String office_name, String project_id, String city_id, String gov_id) {
        this.office_id = office_id;
        this.district_id = district_id;
        this.office_name = office_name;
        this.project_id = project_id;
        this.city_id = city_id;
        this.gov_id = gov_id;
    }

    public OfficeResponseDetails() {
    }


}
