package com.preving.intranet.gestioncentrosapi.model.domain.workCenters;

import com.preving.intranet.gestioncentrosapi.model.domain.Department;

import javax.persistence.*;

@Entity
@Table(schema = "gestion_centros",name = "pc_delegaciones_x_dptos")
public class CenterDetailsDepart {

    private int id;
    private WorkCenter delegation_id = new WorkCenter();
    private Department department_id = new Department();

    public CenterDetailsDepart() {
    }

    public CenterDetailsDepart(int id, WorkCenter delegation_id, Department department_id) {
        this.id = id;
        this.delegation_id = delegation_id;
        this.department_id = department_id;
    }


    @Id
    @Column(name = "ID", nullable = false)
    @SequenceGenerator(name = "PC_DELEGACIONES_DPTOS_SEQ", sequenceName = "PC_DELEGACIONES_DPTOS_SEQ", schema = "gestion_centros", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PC_DELEGACIONES_DPTOS_SEQ")
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "delegacion_id", referencedColumnName = "ID")
    public WorkCenter getDelegation_id() {
        return delegation_id;
    }
    public void setDelegation_id(WorkCenter delegation_id) {
        this.delegation_id = delegation_id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "departamento_id", referencedColumnName = "ID")
    public Department getDepartment_id() {
        return department_id;
    }
    public void setDepartment_id(Department department_id) {
        this.department_id = department_id;
    }

}
