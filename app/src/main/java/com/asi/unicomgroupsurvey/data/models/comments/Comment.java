package com.asi.unicomgroupsurvey.data.models.comments;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Comment {

    @SerializedName("office_data_id")
    String office_data_id ;
    @SerializedName("user_id")
    String user_id;
    @SerializedName("office_id")
    String office_id;
    @SerializedName("office")
    Office office;
    @SerializedName("city_name")
    String other_city;
    @SerializedName("district_name")
    String other_district;
    @SerializedName("address")
    String address;
    @SerializedName("visit_date")
    String visit_date;
    @SerializedName("phone")
    String phone;
    @SerializedName("latitude")
    String latitude;
    @SerializedName("longitude")
    String longitude;
    @SerializedName("is_network")
    String is_network;
    @SerializedName("is_internet")
    String is_internet;
    @SerializedName("internet_speed")
    String internet_speed;
    @SerializedName("shift_type")
    String shift_type;
    @SerializedName("morning_shift_from")
    String morning_shift_from;
    @SerializedName("morning_shift_to")
    String morning_shift_to;
    @SerializedName("evening_shift_from")
    String evening_shift_from;
    @SerializedName("evening_shift_to")
    String evening_shift_to;
    @SerializedName("ownership_type")
    String ownership_type;
    @SerializedName("computer_count")
    String computer_count;
    @SerializedName("computer_notes")
    String computer_notes;
    @SerializedName("printers_count")
    String printers_count;
    @SerializedName("printers_notes")
    String printers_notes;
    @SerializedName("scanners_count")
    String scanners_count;
    @SerializedName("scanners_notes")
    String scanners_notes;
    @SerializedName("general_notes")
    String general_notes;
    @SerializedName("has_feedback")
    String has_feedback;
    @SerializedName("feedback_message")
    String feedback_message;
///
    @SerializedName("net_inside")
    String net_inside;
    @SerializedName("net_outside")
    String net_outside;
    @SerializedName("ins_out_notes")
    String ins_out_notes;
    @SerializedName("others_macs")
    String others_macs;
    @SerializedName("others_macs_notes")
    String others_macs_notes;
    @SerializedName("two_offices")
    String two_offices;
    @SerializedName("other_office_id")
    String other_office_id;
    @SerializedName("other_office_name")
    String other_office_name;
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
    ///////
//    @SerializedName("sketching")
//    String sketching;

    @SerializedName("is_arabic")
    String is_arabic;
    @SerializedName("is_kurdish")
    String is_kurdish;
    @SerializedName("both")
    String both;

    @SerializedName("sketches")
    List<SketchModel> sketchModels;

    @SerializedName("rooms")
    List<RoomModel> rooms;
    @SerializedName("positions")
    List<JobModel> jobs;
    @SerializedName("out_door_images")
    List<OutDoorImageModel> outDoorImages;
    @SerializedName("in_door_images")
    List<InDoorImageModel> inDoorImages;

    public Comment() {
    }

    public String getOffice_data_id() {
        return office_data_id;
    }

    public void setOffice_data_id(String office_data_id) {
        this.office_data_id = office_data_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getOffice_id() {
        return office_id;
    }

    public void setOffice_id(String office_id) {
        this.office_id = office_id;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public String getOther_city() {
        return other_city;
    }

    public void setOther_city(String other_city) {
        this.other_city = other_city;
    }

    public String getOther_district() {
        return other_district;
    }

    public void setOther_district(String other_district) {
        this.other_district = other_district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getVisit_date() {
        return visit_date;
    }

    public void setVisit_date(String visit_date) {
        this.visit_date = visit_date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getIs_network() {
        return is_network;
    }

    public void setIs_network(String is_network) {
        this.is_network = is_network;
    }

    public String getIs_internet() {
        return is_internet;
    }

    public void setIs_internet(String is_internet) {
        this.is_internet = is_internet;
    }

    public String getInternet_speed() {
        return internet_speed;
    }

    public void setInternet_speed(String internet_speed) {
        this.internet_speed = internet_speed;
    }

    public String getShift_type() {
        return shift_type;
    }

    public void setShift_type(String shift_type) {
        this.shift_type = shift_type;
    }

    public String getMorning_shift_from() {
        return morning_shift_from;
    }

    public void setMorning_shift_from(String morning_shift_from) {
        this.morning_shift_from = morning_shift_from;
    }

    public String getMorning_shift_to() {
        return morning_shift_to;
    }

    public void setMorning_shift_to(String morning_shift_to) {
        this.morning_shift_to = morning_shift_to;
    }

    public String getEvening_shift_from() {
        return evening_shift_from;
    }

    public void setEvening_shift_from(String evening_shift_from) {
        this.evening_shift_from = evening_shift_from;
    }

    public String getEvening_shift_to() {
        return evening_shift_to;
    }

    public void setEvening_shift_to(String evening_shift_to) {
        this.evening_shift_to = evening_shift_to;
    }

    public String getOwnership_type() {
        return ownership_type;
    }

    public void setOwnership_type(String ownership_type) {
        this.ownership_type = ownership_type;
    }

    public String getComputer_count() {
        return computer_count;
    }

    public void setComputer_count(String computer_count) {
        this.computer_count = computer_count;
    }

    public String getComputer_notes() {
        return computer_notes;
    }

    public void setComputer_notes(String computer_notes) {
        this.computer_notes = computer_notes;
    }

    public String getPrinters_count() {
        return printers_count;
    }

    public void setPrinters_count(String printers_count) {
        this.printers_count = printers_count;
    }

    public String getPrinters_notes() {
        return printers_notes;
    }

    public void setPrinters_notes(String printers_notes) {
        this.printers_notes = printers_notes;
    }

    public String getScanners_count() {
        return scanners_count;
    }

    public void setScanners_count(String scanners_count) {
        this.scanners_count = scanners_count;
    }

    public String getScanners_notes() {
        return scanners_notes;
    }

    public void setScanners_notes(String scanners_notes) {
        this.scanners_notes = scanners_notes;
    }

//    public String getSketching() {
//        return sketching;
//    }
//
//    public void setSketching(String sketching) {
//        this.sketching = sketching;
//    }

    public String getGeneral_notes() {
        return general_notes;
    }

    public void setGeneral_notes(String general_notes) {
        this.general_notes = general_notes;
    }

    public String getHas_feedback() {
        return has_feedback;
    }

    public void setHas_feedback(String has_feedback) {
        this.has_feedback = has_feedback;
    }

    public String getFeedback_message() {
        return feedback_message;
    }

    public void setFeedback_message(String feedback_message) {
        this.feedback_message = feedback_message;
    }

    public List<RoomModel> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomModel> rooms) {
        this.rooms = rooms;
    }

    public List<JobModel> getJobs() {
        return jobs;
    }

    public void setJobs(List<JobModel> jobs) {
        this.jobs = jobs;
    }

    public List<OutDoorImageModel> getOutDoorImages() {
        return outDoorImages;
    }

    public void setOutDoorImages(List<OutDoorImageModel> outDoorImages) {
        this.outDoorImages = outDoorImages;
    }

    public List<InDoorImageModel> getInDoorImages() {
        return inDoorImages;
    }

    public void setInDoorImages(List<InDoorImageModel> inDoorImages) {
        this.inDoorImages = inDoorImages;
    }



    //////

    public String getNet_inside() {
        return net_inside;
    }

    public void setNet_inside(String net_inside) {
        this.net_inside = net_inside;
    }

    public String getNet_outside() {
        return net_outside;
    }

    public void setNet_outside(String net_outside) {
        this.net_outside = net_outside;
    }

    public String getIns_out_notes() {
        return ins_out_notes;
    }

    public void setIns_out_notes(String ins_out_notes) {
        this.ins_out_notes = ins_out_notes;
    }

    public String getOthers_macs() {
        return others_macs;
    }

    public void setOthers_macs(String others_macs) {
        this.others_macs = others_macs;
    }

    public String getOthers_macs_notes() {
        return others_macs_notes;
    }

    public void setOthers_macs_notes(String others_macs_notes) {
        this.others_macs_notes = others_macs_notes;
    }

    public String getTwo_offices() {
        return two_offices;
    }

    public void setTwo_offices(String two_offices) {
        this.two_offices = two_offices;
    }

    public String getOther_office_id() {
        return other_office_id;
    }

    public void setOther_office_id(String other_office_id) {
        this.other_office_id = other_office_id;
    }

    public String getOther_office_name() {
        return other_office_name;
    }

    public void setOther_office_name(String other_office_name) {
        this.other_office_name = other_office_name;
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

    public List<SketchModel> getSketchModels() {
        return sketchModels;
    }

    public void setSketchModels(List<SketchModel> sketchModels) {
        this.sketchModels = sketchModels;
    }

    public String getIs_arabic() {
        return is_arabic;
    }

    public void setIs_arabic(String is_arabic) {
        this.is_arabic = is_arabic;
    }

    public String getIs_kurdish() {
        return is_kurdish;
    }

    public void setIs_kurdish(String is_kurdish) {
        this.is_kurdish = is_kurdish;
    }

    public String getBoth() {
        return both;
    }

    public void setBoth(String both) {
        this.both = both;
    }



    @Override
    public String toString() {
        return "Comment{" +

                ", office_data_id='" + office_data_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", office_id='" + office_id + '\'' +
                ", office=" + office +
                ", other_city='" + other_city + '\'' +
                ", other_district='" + other_district + '\'' +
                ", address='" + address + '\'' +
                ", visit_date='" + visit_date + '\'' +
                ", phone='" + phone + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", is_network='" + is_network + '\'' +
                ", is_internet='" + is_internet + '\'' +
                ", internet_speed='" + internet_speed + '\'' +
                ", shift_type='" + shift_type + '\'' +
                ", morning_shift_from='" + morning_shift_from + '\'' +
                ", morning_shift_to='" + morning_shift_to + '\'' +
                ", evening_shift_from='" + evening_shift_from + '\'' +
                ", evening_shift_to='" + evening_shift_to + '\'' +
                ", ownership_type='" + ownership_type + '\'' +
                ", computer_count='" + computer_count + '\'' +
                ", computer_notes='" + computer_notes + '\'' +
                ", printers_count='" + printers_count + '\'' +
                ", printers_notes='" + printers_notes + '\'' +
                ", scanners_count='" + scanners_count + '\'' +
                ", scanners_notes='" + scanners_notes + '\'' +
                ", general_notes='" + general_notes + '\'' +
                ", has_feedback='" + has_feedback + '\'' +
                ", feedback_message='" + feedback_message + '\'' +
                ", net_inside='" + net_inside + '\'' +
                ", net_outside='" + net_outside + '\'' +
                ", ins_out_notes='" + ins_out_notes + '\'' +
                ", others_macs='" + others_macs + '\'' +
                ", others_macs_notes='" + others_macs_notes + '\'' +
                ", two_offices='" + two_offices + '\'' +
                ", other_office_id='" + other_office_id + '\'' +
                ", other_office_name='" + other_office_name + '\'' +
                ", electricty='" + electricty + '\'' +
                ", water='" + water + '\'' +
                ", bill_end='" + bill_end + '\'' +
                ", colunms='" + colunms + '\'' +
                ", doors='" + doors + '\'' +
                ", secondary='" + secondary + '\'' +
                ", camersnet='" + camersnet + '\'' +
                ", is_arabic='" + is_arabic + '\'' +
                ", is_kurdish='" + is_kurdish + '\'' +
                ", both='" + both + '\'' +
                ", sketchModels=" + sketchModels +
                ", rooms=" + rooms +
                ", jobs=" + jobs +
                ", outDoorImages=" + outDoorImages +
                ", inDoorImages=" + inDoorImages +
                '}';
    }
}
