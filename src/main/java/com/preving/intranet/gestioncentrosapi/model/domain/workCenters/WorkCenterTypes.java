package com.preving.intranet.gestioncentrosapi.model.domain.workCenters;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "GESTION_CENTROS", name = "TM_DELEGACIONES_TIPOS")
public class WorkCenterTypes implements Serializable {
    private int id;
    private String name;
    private boolean active;

    public WorkCenterTypes(){}

    public WorkCenterTypes(int id, String name, boolean active) {
        this.id = id;
        this.name = name;
        this.active = active;
    }

    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @Basic
    @Column(name = "DENOMINACION")
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Basic
    @Column(name = "ACTIVO")
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

}
