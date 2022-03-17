package com.preving.intranet.gestioncentrosapi.model.domain.maintenance;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.WorkCenter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "GESTION_CENTROS" , name = "MANTENIMIENTOS_X_DELEGACIONES")
public class MaintenanceByWorkCenters implements Serializable {

    private int id;
    private Maintenance maintenance = new Maintenance();
    private WorkCenter workCenter = new WorkCenter();

    public MaintenanceByWorkCenters() {
    }

    public MaintenanceByWorkCenters(int id, Maintenance maintenance, WorkCenter workCenter) {
        this.id = id;
        this.maintenance = maintenance;
        this.workCenter = workCenter;
    }

    @Id
    @Column(name = "ID", nullable = false)
    @SequenceGenerator(name = "MANTENIMIENTOS_X_DELEGACIONES_SEQ", sequenceName = "MANTENIMIENTOS_X_DELEGACIONES_SEQ", schema = "GESTION_CENTROS", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MANTENIMIENTOS_X_DELEGACIONES_SEQ")
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "MANTENIMIENTO_ID", referencedColumnName = "ID")
    public Maintenance getMaintenance() {
        return maintenance;
    }
    public void setMaintenance(Maintenance maintenance) {
        this.maintenance = maintenance;
    }

    @JsonBackReference
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "DELEGACION_ID", referencedColumnName = "ID")
    public WorkCenter getWorkCenter() { return workCenter; }
    public void setWorkCenter(WorkCenter workCenter) { this.workCenter = workCenter; }

}
