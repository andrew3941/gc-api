package com.preving.intranet.gestioncentrosapi.model.domain.workCenters;

import com.preving.intranet.gestioncentrosapi.model.domain.Department;

import javax.persistence.*;

@Entity
@Table(schema = "GESTION_CENTROS",name = "PC_DELEGACIONES_X_DPTOS")
public class CenterDetailsDepart {

    private int id;
    private WorkCenter workCenter = new WorkCenter();
    private Department department = new Department();

    public CenterDetailsDepart() {
    }

    public CenterDetailsDepart(int id, WorkCenter workCenter, Department department) {
        this.id = id;
        this.workCenter = workCenter;
        this.department = department;
    }

    @Id
    @Column(name = "ID", nullable = false)
    @SequenceGenerator(name = "PC_DELEGACIONES_X_DPTOS_SEQ", sequenceName = "PC_DELEGACIONES_X_DPTOS_SEQ", schema = "gestion_centros", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PC_DELEGACIONES_X_DPTOS_SEQ")
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DELEGACION_ID", referencedColumnName = "ID")
    public WorkCenter getWorkCenter() { return workCenter; }
    public void setWorkCenter(WorkCenter workCenter) { this.workCenter = workCenter; }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DEPARTAMENTO_ID", referencedColumnName = "ID")
    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }

}
