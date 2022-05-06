package com.preving.intranet.gestioncentrosapi.model.domain.workers;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.preving.intranet.gestioncentrosapi.model.domain.Department;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(schema = "RRHH", name ="TM_ROLES_ORGANIGRAMA")
public class RolesEmployees implements Serializable {

    private int id;
    private String name;
    private int order;
    private int active;
    private Department department = new Department();
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    private Date adrUpdated;
    private Date qdrCreated;

    public RolesEmployees() {
    }

    public RolesEmployees(int id, String name, int order, int active, Department department, Date adrUpdated, Date qdrCreated) {
        this.id = id;
        this.name = name;
        this.order = order;
        this.active = active;
        this.department = department;
        this.adrUpdated = adrUpdated;
        this.qdrCreated = qdrCreated;
    }

    @Id
    @Column(name = "ID", nullable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    @SequenceGenerator(name = "TM_ROLES_ORGANIGRAMA", sequenceName = "TM_ROLES_ORGANIGRAMA", schema = "RRHH", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TM_ROLES_ORGANIGRAMA")
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    @Basic
    @Column( name= "NOMBRE")
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    @Basic
    @Column( name= "ORDEN")
    public int getOrder() {return order;}
    public void setOrder(int order) {this.order = order;}

    @Basic
    @Column( name= "ACTIVO")
    public int getActive() {return active;}
    public void setActive(int active) {this.active = active;}

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DEPARTAMENTO_ID", referencedColumnName = "ID")
    @NotFound(action = NotFoundAction.IGNORE)
    public Department getDepartment() {return department;}
    public void setDepartment(Department department) {
        this.department = department;}

    @Basic
    @Column( name= "ADR_UPDATED")
    public Date getAdrUpdated() {return adrUpdated;}
    public void setAdrUpdated(Date adrUpdated) {this.adrUpdated = adrUpdated;}

    @Basic
    @Column( name= "QDR_CREATED")
    public Date getQdrCreated() {return qdrCreated;}
    public void setQdrCreated(Date qdrCreated) {this.qdrCreated = qdrCreated;}

}
