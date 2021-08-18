package com.preving.intranet.gestioncentrosapi.model.domain;

import javax.persistence.Entity;
import javax.persistence.*;


@Entity
@Table(schema = "GESTION_CENTROS", name = "TM_PC_DELEGACIONES_SALAS_TIPOS")
public class RoomTypes {
    private int id;
    private String name;
    private String observations;
    private boolean active;

    public RoomTypes() {
    }

    public RoomTypes(int id, String name, String observations, boolean active) {
        this.id = id;
        this.name = name;
        this.observations = observations;
        this.active = active;
    }

    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "DENOMINACION")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "OBSERVACIONES")
    public String getObservations() {
        return observations;
    }
    public void setObservations(String observations) {
        this.observations = observations;
    }

    @Basic
    @Column(name = "ACTIVO")
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
}
