package com.asi.unicomgroupsurvey.data.models.get_Notes;

import com.google.gson.annotations.SerializedName;

public class Notes {

    @SerializedName("office_id")
    String office_id;
    @SerializedName("user_id")
    String user_id;
    @SerializedName("project_id")
    String project_id;
    @SerializedName("notes")
    String notes;
    @SerializedName("location")
    String location;

    @SerializedName("electricty")
    String electricty;
    @SerializedName("water")
    String water;
    @SerializedName("bill_end")
    String bill_end;
    @SerializedName("colunms")
    String colunms;
    @SerializedName("doors")
    String doors;
    @SerializedName("secondary")
    String secondary;
    @SerializedName("camersnet")
    String camersnet;

    public String getVisit_date() {
        return visit_date;
    }

    public void setVisit_date(String visit_date) {
        this.visit_date = visit_date;
    }

    @SerializedName("visit_date")

    String visit_date;

    public Notes() {
    }

    public Notes(String office_id, String user_id, String project_id, String notes, String location) {
        this.office_id = office_id;
        this.user_id = user_id;
        this.project_id = project_id;
        this.notes = notes;
        this.location = location;
    }

    public Notes(String office_id, String user_id, String project_id, String notes, String location, String electricty, String water, String bill_end, String colunms, String doors, String secondary, String camersnet) {
        this.office_id = office_id;
        this.user_id = user_id;
        this.project_id = project_id;
        this.notes = notes;
        this.location = location;
        this.electricty = electricty;
        this.water = water;
        this.bill_end = bill_end;
        this.colunms = colunms;
        this.doors = doors;
        this.secondary = secondary;
        this.camersnet = camersnet;
    }

    public String getOffice_id() {
        return office_id;
    }

    public void setOffice_id(String office_id) {
        this.office_id = office_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getElectricty() {
        return electricty;
    }

    public void setElectricty(String electricty) {
        this.electricty = electricty;
    }

    public String getWater() {
        return water;
    }

    public void setWater(String water) {
        this.water = water;
    }

    public String getBill_end() {
        return bill_end;
    }

    public void setBill_end(String bill_end) {
        this.bill_end = bill_end;
    }

    public String getColunms() {
        return colunms;
    }

    public void setColunms(String colunms) {
        this.colunms = colunms;
    }

    public String getDoors() {
        return doors;
    }

    public void setDoors(String doors) {
        this.doors = doors;
    }

    public String getSecondary() {
        return secondary;
    }

    public void setSecondary(String secondary) {
        this.secondary = secondary;
    }

    public String getCamersnet() {
        return camersnet;
    }

    public void setCamersnet(String camersnet) {
        this.camersnet = camersnet;
    }

    @Override
    public String toString() {
        return "Notes{" +
                "office_id='" + office_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", project_id='" + project_id + '\'' +
                ", notes='" + notes + '\'' +
                ", location='" + location + '\'' +
                ", electricty='" + electricty + '\'' +
                ", water='" + water + '\'' +
                ", bill_end='" + bill_end + '\'' +
                ", colunms='" + colunms + '\'' +
                ", doors='" + doors + '\'' +
                ", secondary='" + secondary + '\'' +
                ", camersnet='" + camersnet + '\'' +
                ", visit_date='" + visit_date + '\'' +
                '}';
    }
}
