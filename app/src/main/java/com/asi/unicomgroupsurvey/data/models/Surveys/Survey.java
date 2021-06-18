package com.asi.unicomgroupsurvey.data.models.Surveys;

import com.google.gson.annotations.SerializedName;

public class Survey {

    @SerializedName("gov_id")
    String gov_id;
    @SerializedName("gov_name")
    String gov_name;
    @SerializedName("city_id")
    String city_id;
    @SerializedName("city_name")
    String city_name;
    @SerializedName("district_id")
    String district_id;
    @SerializedName("district_name")
    String district_name;
    @SerializedName("address")
    String address;
    @SerializedName("phone")
    String phone;
    @SerializedName("hasInternet")
    String hasInternet;
    @SerializedName("isNetwork")
    String isNetwork;
    @SerializedName("internetSeed")
    String internetSeed;
    @SerializedName("office_id")
    String office_id;
    @SerializedName("office_name")
    String office_name;
    @SerializedName("office_visit")
    String office_visit;
    @SerializedName("shiftType")
    String shiftType;
    @SerializedName("OwnerShipType")
    String OwnerShipType;
    @SerializedName("morning_shift_from")
    String morning_shift_from;
    @SerializedName("morning_shift_to")
    String morning_shift_to;
    @SerializedName("evening_shift_from")
    String evening_shift_from;
    @SerializedName("evening_shift_to")
    String evening_shift_to;
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
    @SerializedName("other_city")
    String other_city;
    @SerializedName("other_district")
    String other_district;
    @SerializedName("user_id")
    String user_id;
    @SerializedName("project_id")
    String project_id;

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

    @SerializedName("is_arabic")
    String is_arabic;
    @SerializedName("is_kurdish")
    String is_kurdish;
    @SerializedName("both")
    String both;

    public Survey() {
    }

    public Survey(String gov_id, String gov_name, String city_id, String city_name, String district_id, String district_name, String address, String phone, String hasInternet, String isNetwork, String internetSeed, String office_id, String office_name, String office_visit, String shiftType, String ownerShipType, String morning_shift_from, String morning_shift_to, String evening_shift_from, String evening_shift_to, String computer_count, String computer_notes, String printers_count, String printers_notes, String scanners_count, String scanners_notes, String other_city, String other_district, String user_id, String project_id) {
        this.gov_id = gov_id;
        this.gov_name = gov_name;
        this.city_id = city_id;
        this.city_name = city_name;
        this.district_id = district_id;
        this.district_name = district_name;
        this.address = address;
        this.phone = phone;
        this.hasInternet = hasInternet;
        this.isNetwork = isNetwork;
        this.internetSeed = internetSeed;
        this.office_id = office_id;
        this.office_name = office_name;
        this.office_visit = office_visit;
        this.shiftType = shiftType;
        OwnerShipType = ownerShipType;
        this.morning_shift_from = morning_shift_from;
        this.morning_shift_to = morning_shift_to;
        this.evening_shift_from = evening_shift_from;
        this.evening_shift_to = evening_shift_to;
        this.computer_count = computer_count;
        this.computer_notes = computer_notes;
        this.printers_count = printers_count;
        this.printers_notes = printers_notes;
        this.scanners_count = scanners_count;
        this.scanners_notes = scanners_notes;
        this.other_city = other_city;
        this.other_district = other_district;
        this.user_id = user_id;
        this.project_id = project_id;
    }

    public Survey(String gov_id, String gov_name, String city_id, String city_name, String district_id, String district_name, String address, String phone, String hasInternet, String isNetwork, String internetSeed, String office_id, String office_name, String office_visit, String shiftType, String ownerShipType, String morning_shift_from, String morning_shift_to, String evening_shift_from, String evening_shift_to, String computer_count, String computer_notes, String printers_count, String printers_notes, String scanners_count, String scanners_notes, String other_city, String other_district, String user_id, String project_id, String net_inside, String net_outside, String ins_out_notes, String others_macs, String others_macs_notes,String two_offices , String other_office_id , String other_office_name,String is_arabic,String is_kurdish ,String both) {
        this.gov_id = gov_id;
        this.gov_name = gov_name;
        this.city_id = city_id;
        this.city_name = city_name;
        this.district_id = district_id;
        this.district_name = district_name;
        this.address = address;
        this.phone = phone;
        this.hasInternet = hasInternet;
        this.isNetwork = isNetwork;
        this.internetSeed = internetSeed;
        this.office_id = office_id;
        this.office_name = office_name;
        this.office_visit = office_visit;
        this.shiftType = shiftType;
        OwnerShipType = ownerShipType;
        this.morning_shift_from = morning_shift_from;
        this.morning_shift_to = morning_shift_to;
        this.evening_shift_from = evening_shift_from;
        this.evening_shift_to = evening_shift_to;
        this.computer_count = computer_count;
        this.computer_notes = computer_notes;
        this.printers_count = printers_count;
        this.printers_notes = printers_notes;
        this.scanners_count = scanners_count;
        this.scanners_notes = scanners_notes;
        this.other_city = other_city;
        this.other_district = other_district;
        this.user_id = user_id;
        this.project_id = project_id;
        this.net_inside = net_inside;
        this.net_outside = net_outside;
        this.ins_out_notes = ins_out_notes;
        this.others_macs = others_macs;
        this.others_macs_notes = others_macs_notes;
        this.two_offices = two_offices;
        this.other_office_id = other_office_id;
        this.other_office_name = other_office_name;
        this.is_arabic = is_arabic;
        this.is_kurdish = is_kurdish;
        this.both = both;
    }

    public String getGov_id() {
        return gov_id;
    }

    public void setGov_id(String gov_id) {
        this.gov_id = gov_id;
    }

    public String getGov_name() {
        return gov_name;
    }

    public void setGov_name(String gov_name) {
        this.gov_name = gov_name;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHasInternet() {
        return hasInternet;
    }

    public void setHasInternet(String hasInternet) {
        this.hasInternet = hasInternet;
    }

    public String getIsNetwork() {
        return isNetwork;
    }

    public void setIsNetwork(String isNetwork) {
        this.isNetwork = isNetwork;
    }

    public String getInternetSeed() {
        return internetSeed;
    }

    public void setInternetSeed(String internetSeed) {
        this.internetSeed = internetSeed;
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

    public String getOffice_visit() {
        return office_visit;
    }

    public void setOffice_visit(String office_visit) {
        this.office_visit = office_visit;
    }

    public String getShiftType() {
        return shiftType;
    }

    public void setShiftType(String shiftType) {
        this.shiftType = shiftType;
    }

    public String getOwnerShipType() {
        return OwnerShipType;
    }

    public void setOwnerShipType(String ownerShipType) {
        OwnerShipType = ownerShipType;
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


    @Override
    public String toString() {
        return "Survey{" +
                "gov_id='" + gov_id + '\'' +
                ", gov_name='" + gov_name + '\'' +
                ", city_id='" + city_id + '\'' +
                ", city_name='" + city_name + '\'' +
                ", district_id='" + district_id + '\'' +
                ", district_name='" + district_name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", hasInternet='" + hasInternet + '\'' +
                ", isNetwork='" + isNetwork + '\'' +
                ", internetSeed='" + internetSeed + '\'' +
                ", office_id='" + office_id + '\'' +
                ", office_name='" + office_name + '\'' +
                ", office_visit='" + office_visit + '\'' +
                ", shiftType='" + shiftType + '\'' +
                ", OwnerShipType='" + OwnerShipType + '\'' +
                ", morning_shift_from='" + morning_shift_from + '\'' +
                ", morning_shift_to='" + morning_shift_to + '\'' +
                ", evening_shift_from='" + evening_shift_from + '\'' +
                ", evening_shift_to='" + evening_shift_to + '\'' +
                ", computer_count='" + computer_count + '\'' +
                ", computer_notes='" + computer_notes + '\'' +
                ", printers_count='" + printers_count + '\'' +
                ", printers_notes='" + printers_notes + '\'' +
                ", scanners_count='" + scanners_count + '\'' +
                ", scanners_notes='" + scanners_notes + '\'' +
                ", other_city='" + other_city + '\'' +
                ", other_district='" + other_district + '\'' +
                ", user_id='" + user_id + '\'' +
                ", project_id='" + project_id + '\'' +
                ", net_inside='" + net_inside + '\'' +
                ", net_outside='" + net_outside + '\'' +
                ", ins_out_notes='" + ins_out_notes + '\'' +
                ", others_macs='" + others_macs + '\'' +
                ", others_macs_notes='" + others_macs_notes + '\'' +
                ", two_offices='" + two_offices + '\'' +
                ", other_office_id='" + other_office_id + '\'' +
                ", other_office_name='" + other_office_name + '\'' +
                ", is_arabic='" + is_arabic + '\'' +
                ", is_kurdish='" + is_kurdish + '\'' +
                ", both='" + both + '\'' +
                '}';
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
}
