package com.preving.intranet.gestioncentrosapi.model.domain;

import javax.persistence.*;

@Entity
@Table(schema = "GESTION_CENTROS", name = "REGIONS")
public class Region {

    private int id;
    private String name;

    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    @SequenceGenerator(name = "REGIONS_SEQ", sequenceName = "REGIONS_SEQ", schema = "GESTION_CENTROS", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REGIONS_SEQ")
    public int getId() { return id; }
    public void setId(int id) { this.id = id;  }

    @Basic
    @Column(name = "REGION_NAME", length = 100)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public Region() {
    }

    public Region(int id, String name) {
        this.id = id;
        this.name = name;
    }

}
