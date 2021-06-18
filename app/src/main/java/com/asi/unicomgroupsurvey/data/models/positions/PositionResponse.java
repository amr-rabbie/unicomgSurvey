package com.asi.unicomgroupsurvey.data.models.positions;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PositionResponse {

    @SerializedName("positions")
    private List<PositionResponseDetails> positionResponseDetails;

    public List<PositionResponseDetails> getPositionResponseDetails() {
        return positionResponseDetails;
    }

    public void setPositionResponseDetails(List<PositionResponseDetails> positionResponseDetails) {
        this.positionResponseDetails = positionResponseDetails;
    }
}
