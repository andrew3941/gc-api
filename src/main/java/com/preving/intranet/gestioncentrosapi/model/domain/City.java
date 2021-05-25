package com.preving.intranet.gestioncentrosapi.model.domain;


import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "GESTION_CENTROS", name ="CITYS")
public class City implements Serializable {

    private int id;
    private String name;
    private Region prvCod = new Region();

    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    @SequenceGenerator(name = "CITYS_SEQ", sequenceName = "CITYS_SEQ", schema = "GESTION_CENTROS", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CITYS_SEQ")
    public int getId() { return id; }
    public void setId(int id) { this.id = id;  }

    @Basic
    @Column(name = "NAME", length = 100)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public City() {
    }

    public City(int id, String name, Region prvCod) {
        this.id = id;
        this.name = name;
        this.prvCod = prvCod;
    }

  
}
