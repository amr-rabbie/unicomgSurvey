package com.asi.unicomgroupsurvey.data.models.out_door;

import android.graphics.Bitmap;

public class OutDoorImage {

    private  String outDoorImage_ID;
    private String outDoorUserId;
    private String outDoorProjectId;
    private String outDoorOfficeId;
    private Bitmap outDoorImageBitmap;
    private String outDoorImageDesc;
    private int mimimumStatus ;
    public OutDoorImage() {
        this.mimimumStatus = 0  ;
    }

    public OutDoorImage( String outDoorUserId, String outDoorProjectId, String outDoorOfficeId,  String outDoorImageDesc, Bitmap outDoorImageBitmap) {

        this.outDoorUserId = outDoorUserId;
        this.outDoorProjectId = outDoorProjectId;
        this.outDoorOfficeId = outDoorOfficeId;
        this.outDoorImageDesc = outDoorImageDesc ;
        this.outDoorImageBitmap = outDoorImageBitmap;
        this.mimimumStatus = 0  ;
    }

    public String getOutDoorImage_ID() {
        return outDoorImage_ID;
    }

    public void setOutDoorImage_ID(String outDoorImage_ID) {
        this.outDoorImage_ID = outDoorImage_ID;
    }

    public String getOutDoorUserId() {
        return outDoorUserId;
    }

    public void setOutDoorUserId(String outDoorUserId) {
        this.outDoorUserId = outDoorUserId;
    }

    public String getOutDoorProjectId() {
        return outDoorProjectId;
    }

    public void setOutDoorProjectId(String outDoorProjectId) {
        this.outDoorProjectId = outDoorProjectId;
    }

    public String getOutDoorOfficeId() {
        return outDoorOfficeId;
    }

    public void setOutDoorOfficeId(String outDoorOfficeId) {
        this.outDoorOfficeId = outDoorOfficeId;
    }

    public Bitmap getOutDoorImageBitmap() {
        return outDoorImageBitmap;
    }

    public void setOutDoorImageBitmap(Bitmap outDoorImageBitmap) {
        this.outDoorImageBitmap = outDoorImageBitmap;
    }

    public String getOutDoorImageDesc() {
        return outDoorImageDesc;
    }

    public void setOutDoorImageDesc(String outDoorImageDesc) {
        this.outDoorImageDesc = outDoorImageDesc;
    }


    public int getMimimumStatus() {
        return mimimumStatus;
    }

    public void setMimimumStatus(int mimimumStatus) {
        this.mimimumStatus = mimimumStatus;
    }
}
