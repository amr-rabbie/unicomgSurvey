package com.asi.unicomgroupsurvey.data.models.comments;

import com.asi.unicomgroupsurvey.data.models.getDistricts.District;
import com.google.gson.annotations.SerializedName;

public class Office {


    @SerializedName("office_id")
    String office_id;
    @SerializedName("office_name")
    String office_name;
    @SerializedName("district_id")
    String district_id;
    @SerializedName("project_id")
    String project_id;
    @SerializedName("district")
    DistrictModel district;
    @SerializedName("project")
    Project project ;

    public Office(String office_id, String office_name, String district_id, String project_id, DistrictModel district) {
        this.office_id = office_id;
        this.office_name = office_name;
        this.district_id = district_id;
        this.project_id = project_id;
        this.district = district;
    }

    public Office() {
    }

    public String getOffice_id() {
        return office_id;
    }

    public void setOffice_id(String office_id) {
        this.office_id = office_id;
    }

    public String getOffice_name() {
        return office_name;
    }

    public void setOffice_name(String office_name) {
        this.office_name = office_name;
    }

    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public DistrictModel getDistrict() {
        return district;
    }

    public void setDistrict(DistrictModel district) {
        this.district = district;
    }

    @Override
    public String toString() {
        return "Office{" +
                "office_id='" + office_id + '\'' +
                ", office_name='" + office_name + '\'' +
                ", district_id='" + district_id + '\'' +
                ", project_id='" + project_id + '\'' +
                ", district=" + district +
                '}';
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
