package com.preving.intranet.gestioncentrosapi.model.domain.generalDocumentation;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(schema = "GESTION_CENTROS", name = "TM_DOC_GENERAL_TIPOS")
public class GeneralDocumentationTypes implements Serializable {
    private int id;
    private String name;
    private String observation;
    private boolean active;

    public GeneralDocumentationTypes() {
    }

    public GeneralDocumentationTypes(int id, String name, String observation, boolean active) {
        this.id = id;
        this.name = name;
        this.observation = observation;
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
    public String getObservation() {
        return observation;
    }
    public void setObservation(String observation) {
        this.observation = observation;
    }

    @Basic
    @Column(name = "ACTIVO")
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

}
