package com.preving.intranet.gestioncentrosapi.model.domain;


import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Table(schema = "RRHH", name ="TM_DIM_NAVISION")
public class DimNavision implements Serializable {

    private int id;
    private String type;
    private String cod;
    private String name;
    private int active;
    private Integer order;
    private String mccLnMf;
    private String provinceCod;

    //create a class constructor for dependency injection
    public DimNavision() {
    }

    @Id
    @Column(name = "ID", nullable = false)
    @SequenceGenerator(name = "TM_DIM_NAVISION_SQ", sequenceName = "TM_DIM_NAVISION_SQ", schema = "RRHH", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TM_DIM_NAVISION_SQ")
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "TIPO", length = 5)
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "COD", length = 15)
    public String getCod() {
        return cod;
    }
    public void setCod(String cod) {
        this.cod = cod;
    }

    @Basic
    @Column(name = "NOMBRE", length = 100)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "ACTIVO")
    public int getActive() {
        return active;
    }
    public void setActive(int active) {
        this.active = active;
    }

    @Basic
    @Column(name = "ORDEN")
    public Integer getOrder() {
        return order;
    }
    public void setOrder(Integer order) {
        this.order = order;
    }

    @Basic
    @Column(name = "MCC_LN_MF", length = 5)
    public String getMccLnMf() { return mccLnMf; }
    public void setMccLnMf(String mccLnMf) { this.mccLnMf = mccLnMf; }

    @Basic
    @Column(name = "PROVINCIA_COD", length = 3)
    public String getProvinceCod() {
        return provinceCod;
    }
    public void setProvinceCod(String provinceCod) {
        this.provinceCod = provinceCod;
    }

    public DimNavision(int id, String type, String cod, String name, int active, Integer order, String mccLnMf, String provinceCod) {
        this.id = id;
        this.type = type;
        this.cod = cod;
        this.name = name;
        this.active = active;
        this.order = order;
        this.mccLnMf = mccLnMf;
        this.provinceCod = provinceCod;
    }

}
