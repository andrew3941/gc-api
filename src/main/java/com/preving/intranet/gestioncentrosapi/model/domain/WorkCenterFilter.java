package com.preving.intranet.gestioncentrosapi.model.domain;

import java.util.ArrayList;
import java.util.List;

public class WorkCenterFilter {

    private String workCenterName;
    private Province workCenterProvince;
    private int workCenterStatus;
    private int workCenterTypes;
    private boolean allEntitiesSelected;
    private List<Entity> workCenterEntities = new ArrayList<>();

    public WorkCenterFilter() {
    }

    public WorkCenterFilter(String workCenterName, Province workCenterProvince, int workCenterStatus, int workCenterTypes, boolean allEntitiesSelected, List<Entity> workCenterEntities) {
        this.workCenterName = workCenterName;
        this.workCenterProvince = workCenterProvince;
        this.workCenterStatus = workCenterStatus;
        this.workCenterTypes = workCenterTypes;
        this.allEntitiesSelected = allEntitiesSelected;
        this.workCenterEntities = workCenterEntities;
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

    public int getWorkCenterTypes() { return workCenterTypes;  }
    public void setWorkCenterTypes(int workCenterTypes) { this.workCenterTypes = workCenterTypes; }

    public boolean isAllEntitiesSelected() {
        return allEntitiesSelected;
    }
    public void setAllEntitiesSelected(boolean allEntitiesSelected) {
        this.allEntitiesSelected = allEntitiesSelected;
    }

    public List<Entity> getWorkCenterEntities() { return workCenterEntities; }
    public void setWorkCenterEntities(List<Entity> workCenterEntities) { this.workCenterEntities = workCenterEntities; }

}
