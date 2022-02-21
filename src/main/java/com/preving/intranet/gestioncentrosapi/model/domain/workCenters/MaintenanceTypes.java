package com.preving.intranet.gestioncentrosapi.model.domain.workCenters;

import java.io.Serializable;

public class MaintenanceTypes implements Serializable {
    private int id;
    private String name;

    public MaintenanceTypes() {
    }

    public MaintenanceTypes(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
