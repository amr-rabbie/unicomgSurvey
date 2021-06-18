package com.asi.unicomgroupsurvey.data.models.sketch;

import android.graphics.Bitmap;

public class Sketch {

    String _ID ;
    String office_id;
    String user_id;
    String project_id;
    String sketch_image_Desc ;
    Bitmap sketch_image;
    int minimumStatus  ;

    public Sketch(String office_id, String user_id, String project_id, String sketch_image_Desc , Bitmap sketch_image) {
        this.sketch_image_Desc = sketch_image_Desc ;
        this.office_id = office_id;
        this.user_id = user_id;
        this.project_id = project_id;
        this.sketch_image = sketch_image;
        this.minimumStatus = 0 ;
    }

    public Sketch() {
        this.minimumStatus = 0 ;
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

    public String getSketch_image_Desc() {
        return sketch_image_Desc;
    }

    public void setSketch_image_Desc(String sketch_image_Desc) {
        this.sketch_image_Desc = sketch_image_Desc;
    }

    public Bitmap getSketch_image() {
        return sketch_image;
    }

    public void setSketch_image(Bitmap sketch_image) {
        this.sketch_image = sketch_image;
    }

    @Override
    public String toString() {
        return "Sketch{" +
                "office_id='" + office_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", project_id='" + project_id + '\'' +
                ", sketch_image_Desc='" + sketch_image_Desc + '\'' +
                ", sketch_image=" + sketch_image +
                '}';
    }

    public String get_ID() {
        return _ID;
    }

    public void set_ID(String _ID) {
        this._ID = _ID;
    }

    public int getMinimumStatus() {
        return minimumStatus;
    }

    public void setMinimumStatus(int minimumStatus) {
        this.minimumStatus = minimumStatus;
    }
}
