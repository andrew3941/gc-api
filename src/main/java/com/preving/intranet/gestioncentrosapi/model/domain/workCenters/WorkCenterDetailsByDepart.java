package com.preving.intranet.gestioncentrosapi.model.domain.workCenters;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.preving.intranet.gestioncentrosapi.model.domain.Department;

import javax.persistence.*;

@Entity
@Table(schema = "GESTION_CENTROS",name = "PC_DELEGACIONES_DETALLES_X_DPTOS")
public class WorkCenterDetailsByDepart {

    private int id;
    private WorkCenterDetails workCenterDetails = new WorkCenterDetails();
    private Department department = new Department();

    public WorkCenterDetailsByDepart() {
    }

    public WorkCenterDetailsByDepart(int id, WorkCenterDetails workCenterDetails, Department department) {
        this.id = id;
        this.workCenterDetails = workCenterDetails;
        this.department = department;
    }

    @Id
    @Column(name = "ID", nullable = false)
    @SequenceGenerator(name = "PC_DELEGACIONES_DETALLES_X_DPTOS_SEQ", sequenceName = "PC_DELEGACIONES_DETALLES_X_DPTOS_SEQ", schema = "gestion_centros", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PC_DELEGACIONES_DETALLES_X_DPTOS_SEQ")
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @JsonBackReference
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "DELEGACION_DETALLE_ID", referencedColumnName = "ID")
    public WorkCenterDetails getWorkCenterDetails() { return workCenterDetails; }
    public void setWorkCenterDetails(WorkCenterDetails workCenterDetails) { this.workCenterDetails = workCenterDetails; }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DEPARTAMENTO_ID", referencedColumnName = "ID")
    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }

}
