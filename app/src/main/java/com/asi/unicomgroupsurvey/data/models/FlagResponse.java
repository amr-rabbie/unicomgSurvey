package com.asi.unicomgroupsurvey.data.models;

import com.google.gson.annotations.SerializedName;

public class FlagResponse {
    @SerializedName("message")
    String message ;
    @SerializedName("success")
    String success ;

    public FlagResponse(String message, String success) {
        this.message = message;
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
