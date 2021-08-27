package com.preving.intranet.gestioncentrosapi.model.domain.vendors;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "GESTION_CENTROS", name = "TM_PROVEEDORES_TIPOS")
public class ProviderTypes implements Serializable {
    private int id;
    private String name;
    private String observations;
    private boolean active;

    public ProviderTypes(){ }

    public ProviderTypes(int id, String name, String observations, boolean active) {
        this.id = id;
        this.name = name;
        this.observations = observations;
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
    @Column(name = "OBSERVACIONES")
    public String getObservations() { return observations; }
    public void setObservations(String observations) { this.observations = observations; }

    @Basic
    @Column(name = "ACTIVO")
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

}
