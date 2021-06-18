package com.asi.unicomgroupsurvey.data.models.jops;

public class Job {

    String user_id ;
    String project_id ;
    String office_id;
    String job_id;
    String count;
    String note;
    String job_name ;

    public Job() {
    }

    public Job(String user_id, String project_id, String office_id, String job_id, String count, String note, String job_name) {
        this.user_id = user_id;
        this.project_id = project_id;
        this.office_id = office_id;
        this.job_id = job_id;
        this.count = count;
        this.note = note;
        this.job_name = job_name;
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

    public String getOffice_id() {
        return office_id;
    }

    public void setOffice_id(String office_id) {
        this.office_id = office_id;
    }

    public String getJob_id() {
        return job_id;
    }

    public void setJob_id(String job_id) {
        this.job_id = job_id;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getJob_name() {
        return job_name;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }

    @Override
    public String toString() {
        return "Job{" +
                "user_id='" + user_id + '\'' +
                ", project_id='" + project_id + '\'' +
                ", office_id='" + office_id + '\'' +
                ", job_id='" + job_id + '\'' +
                ", count='" + count + '\'' +
                ", note='" + note + '\'' +
                ", job_name='" + job_name + '\'' +
                '}';
    }
}
