package com.asi.unicomgroupsurvey.data.models.in_door;

import android.graphics.Bitmap;

public class InDoorImage {

    private   int mimimumStatus;
    private  String inDoorImage_ID;
    private String  inDoorUserId;
    private String inDoorProjectId;

    private String inDoorOfficeId;
    private String indoorImageDesc ;
    private Bitmap inDoorImageBitmap;

    public InDoorImage() {
        this.mimimumStatus = 0  ;
    }

    public InDoorImage( String inDoorUserId,
                       String inDoorProjectId, String inDoorOfficeId, String indoorImageDesc ,Bitmap inDoorImageBitmap) {

        this.inDoorUserId = inDoorUserId;
        this.inDoorProjectId = inDoorProjectId;
        this.inDoorOfficeId = inDoorOfficeId;
        this.indoorImageDesc = indoorImageDesc;
        this.inDoorImageBitmap = inDoorImageBitmap;
        this.mimimumStatus = 0  ;
    }

    public String getInDoorImage_ID() {
        return inDoorImage_ID;
    }

    public void setInDoorImage_ID(String inDoorImage_ID) {
        this.inDoorImage_ID = inDoorImage_ID;
    }

    public String getInDoorUserId() {
        return inDoorUserId;
    }

    public void setInDoorUserId(String inDoorUserId) {
        this.inDoorUserId = inDoorUserId;
    }

    public String getInDoorProjectId() {
        return inDoorProjectId;
    }

    public void setInDoorProjectId(String inDoorProjectId) {
        this.inDoorProjectId = inDoorProjectId;
    }

    public String getInDoorOfficeId() {
        return inDoorOfficeId;
    }

    public void setInDoorOfficeId(String inDoorOfficeId) {
        this.inDoorOfficeId = inDoorOfficeId;
    }

    public String getIndoorImageDesc() {
        return indoorImageDesc;
    }

    public void setIndoorImageDesc(String indoorImageDesc) {
        this.indoorImageDesc = indoorImageDesc;
    }

    public Bitmap getInDoorImageBitmap() {
        return inDoorImageBitmap;
    }

    public void setInDoorImageBitmap(Bitmap inDoorImageBitmap) {
        this.inDoorImageBitmap = inDoorImageBitmap;
    }


    public int getMimimumStatus() {
        return mimimumStatus;
    }

    public void setMimimumStatus(int mimimumStatus) {
        this.mimimumStatus = mimimumStatus;
    }
}
