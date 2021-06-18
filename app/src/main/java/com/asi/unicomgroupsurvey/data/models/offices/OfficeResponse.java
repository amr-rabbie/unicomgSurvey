package com.asi.unicomgroupsurvey.data.models.offices;

import com.asi.unicomgroupsurvey.data.models.positions.PositionResponseDetails;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OfficeResponse {

    @SerializedName("offices")
    private List<OfficeResponseDetails> officeResponseDetails;
    @SerializedName("success")
    String success ;
    @SerializedName("message")
    String message ;

    public List<OfficeResponseDetails> getOfficeResponseDetails() {
        return officeResponseDetails;
    }

    public void setPositionResponseDetails(List<OfficeResponseDetails> officeResponseDetails) {
        this.officeResponseDetails = officeResponseDetails;
    }

    public void setOfficeResponseDetails(List<OfficeResponseDetails> officeResponseDetails) {
        this.officeResponseDetails = officeResponseDetails;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
