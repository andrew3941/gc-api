package com.preving.intranet.gestioncentrosapi.model.domain.workers;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(schema = "RRHH", name ="TM_DEPARTAMENTOS")
public class DepartmentEmployees implements Serializable {

    private int id;
    private String name;
    private int order;
    private int active;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    private Date adrUpdated;
    private Date qdrCreated;

    public DepartmentEmployees() {
    }


    public DepartmentEmployees(int id, String name, int order, int active, Date adrUpdated, Date qdrCreated) {
        this.id = id;
        this.name = name;
        this.order = order;
        this.active = active;
        this.adrUpdated = adrUpdated;
        this.qdrCreated = qdrCreated;
    }

    @Id
    @Column(name = "ID", nullable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    @SequenceGenerator(name = "TM_DEPARTAMENTOS_SEQ", sequenceName = "TM_DEPARTAMENTOS_SEQ", schema = "RRHH", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TM_DEPARTAMENTOS_SEQ")
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

    @Basic
    @Column( name= "ADR_UPDATED")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    public Date getAdrUpdated() {return adrUpdated;}
    public void setAdrUpdated(Date adrUpdated) {this.adrUpdated = adrUpdated;}

    @Basic
    @Column( name= "QDR_CREATED")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    public Date getQdrCreated() {return qdrCreated;}
    public void setQdrCreated(Date qdrCreated) {this.qdrCreated = qdrCreated;}
}
