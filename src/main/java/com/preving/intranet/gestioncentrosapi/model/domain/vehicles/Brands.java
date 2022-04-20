package com.preving.intranet.gestioncentrosapi.model.domain.vehicles;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(schema = "SAC", name ="VE_MARCAS")
public class Brands implements Serializable {
    private int id;
    private String name;
    private int active;
    private Date adrUpdated;
    private Date qdrCreated;

    public Brands() {
    }

    public Brands(int id, String name, int active, Date adrUpdated, Date qdrCreated) {
        this.id = id;
        this.name = name;
        this.active = active;
        this.adrUpdated = adrUpdated;
        this.qdrCreated = qdrCreated;
    }

    @Id
    @Column(name = "ID", nullable = false)
    @SequenceGenerator(name = "VE_MARCAS_SEQ", sequenceName = "VE_MARCAS_SEQ", schema = "SAC", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VE_MARCAS_SEQ")
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    @Basic
    @Column(name = "NOMBRE")
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    @Basic
    @Column(name = "ACTIVO")
    public int getActive() {return active;}
    public void setActive(int active) {this.active = active;}

    @Basic
    @Column(name = "ADR_UPDATED")
    @JsonFormat(pattern = "dd-mm-yyyy", timezone = "Europe/Madrid")
    public Date getAdrUpdated() {return adrUpdated;}
    public void setAdrUpdated(Date adrUpdated) {this.adrUpdated = adrUpdated;}

    @Basic
    @Column(name = "QDR_CREATED")
    @JsonFormat(pattern = "dd-mm-yyyy", timezone = "Europe/Madrid")
    public Date getQdrCreated() {return qdrCreated;}
    public void setQdrCreated(Date qdrCreated) {this.qdrCreated = qdrCreated;}

}
