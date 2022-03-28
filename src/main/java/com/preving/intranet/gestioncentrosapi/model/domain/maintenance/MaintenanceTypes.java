package com.preving.intranet.gestioncentrosapi.model.domain.maintenance;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "GESTION_CENTROS", name = "TM_MANTENIMIENTOS_TIPOS")
public class MaintenanceTypes implements Serializable {
    private int id;
    private String denomination;
    private String concept;
    private boolean active;


    public MaintenanceTypes() {
    }

    public MaintenanceTypes(int id, String denomination, String concept, boolean active) {
        this.id = id;
        this.denomination = denomination;
        this.concept = concept;
        this.active = active;
    }

    public static boolean hasRole(String gcManagerRolName) {
        return false;
    }


    @Id
    @Column(name = "ID", nullable = false)
    @SequenceGenerator(name = "MANTENIMIENTOS_X_ADJUNTOS_SEQ", sequenceName = "MANTENIMIENTOS_X_ADJUNTOS_SEQ", schema = "GESTION_CENTROS", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MANTENIMIENTOS_X_ADJUNTOS_SEQ")
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "DENOMINACION")
    public String getDenomination() {
        return denomination;
    }
    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    @Basic
    @Column(name = "OBSERVACIONES")
    public String getObservations() {
        return concept;
    }
    public void setObservations(String observations) {
        this.concept = observations;
    }

    @Basic
    @Column(name = "ACTIVO")
    public boolean isActive() {return active;}
    public void setActive(boolean active) {this.active = active;}
}
