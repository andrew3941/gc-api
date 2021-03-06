package com.preving.intranet.gestioncentrosapi.model.domain;

import javax.persistence.*;
import javax.persistence.Entity;
import java.time.Instant;


@Entity
@Table(schema = "RRHH", name = "TM_DEPARTAMENTOS")
public class Department {
    private int id;
    private String name;
    private int order;
    private int active;

    @Column(name = "adr_updated")
    private Instant adrUpdated;

    @Column(name = "qdr_created")
    private Instant qdrCreated;

    public Instant getQdrCreated() {
        return qdrCreated;
    }

    public void setQdrCreated(Instant qdrCreated) {
        this.qdrCreated = qdrCreated;
    }

    public Instant getAdrUpdated() {
        return adrUpdated;
    }

    public void setAdrUpdated(Instant adrUpdated) {
        this.adrUpdated = adrUpdated;
    }

    public Department() {
    }

    public Department(int id, String name, int order, int active) {
        this.id = id;
        this.name = name;
        this.order = order;
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
    @Column(name = "NOMBRE")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "ORDEN")
    public int getOrder() {
        return order;
    }
    public void setOrder(int order) {
        this.order = order;
    }

    @Basic
    @Column(name = "ACTIVO")
    public int getActive() {
        return active;
    }
    public void setActive(int active) {
        this.active = active;
    }
}
