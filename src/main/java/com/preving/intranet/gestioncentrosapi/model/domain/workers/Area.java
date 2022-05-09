package com.preving.intranet.gestioncentrosapi.model.domain.workers;

import com.preving.intranet.gestioncentrosapi.model.domain.Department;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "TM_AREAS", schema = "RRHH")
public class Area {
    @Id
    @Column(name = "ID", nullable = false)
    private int id;

    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String name;

    @Column(name = "ORDEN", nullable = false)
    private int order;

    @Column(name = "ACTIVO", nullable = false)
    private int active;

    @ManyToOne
    @JoinColumn(name = "DEPARTAMENTO_ID",nullable = false)
    private Department department = new Department();

    @Column(name = "REDMINE_PROJECT", length = 200)
    private String redmineProject;

    @Column(name = "ADR_UPDATED")
    private Date adrUpdated;

    @Column(name = "QDR_CREATED")
    private Date qdrCreated;

    public Area() {
    }


    public Area(int id, String name, int order, int active, Department department, String redmineProject, Date adrUpdated, Date qdrCreated) {
        this.id = id;
        this.name = name;
        this.order = order;
        this.active = active;
        this.department = department;
        this.redmineProject = redmineProject;
        this.adrUpdated = adrUpdated;
        this.qdrCreated = qdrCreated;
    }

    public Date getQdrCreated() {return qdrCreated;}
    public void setQdrCreated(Date qdrCreated) {this.qdrCreated = qdrCreated;}

    public Date getAdrUpdated() {return adrUpdated;}
    public void setAdrUpdated(Date adrUpdated) {this.adrUpdated = adrUpdated;}

    public String getRedmineProject() {return redmineProject;}
    public void setRedmineProject(String redmineProject) {this.redmineProject = redmineProject;}

    public Department getDepartment() {return department;}
    public void setDepartment(Department department) {this.department = department;}

    public int getActive() {return active;}
    public void setActive(int active) {this.active = active;}

    public int getOrder() {return order;}
    public void setOrder(int order) {this.order = order;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
}