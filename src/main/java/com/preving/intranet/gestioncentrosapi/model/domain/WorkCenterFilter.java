package com.preving.intranet.gestioncentrosapi.model.domain;

public class WorkCenterFilter {

    private String workCenterName;
    private Province workCenterProvince;
    private int workCenterStatus;

    public WorkCenterFilter() {
    }

    public WorkCenterFilter(String workCenterName, Province workCenterProvince, int workCenterStatus) {
        this.workCenterName = workCenterName;
        this.workCenterProvince=workCenterProvince;
        this.workCenterStatus = workCenterStatus;
    }


    public String getWorkCenterName() {
        return workCenterName;
    }
    public void setWorkCenterName(String workCenterName) {
        this.workCenterName = workCenterName;
    }


    public Province getWorkCenterProvince() {
        return workCenterProvince;
    }
    public void setWorkCenterProvince(Province workCenterProvince) {
        this.workCenterProvince = workCenterProvince;
    }

    public int getWorkCenterStatus() {
        return workCenterStatus;
    }

    public void setWorkCenterStatus(int workCenterStatus) {
        this.workCenterStatus = workCenterStatus;
    }
}
