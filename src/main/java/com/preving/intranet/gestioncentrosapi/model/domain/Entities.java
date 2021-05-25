package com.preving.intranet.gestioncentrosapi.model.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "GESTION_CENTROS", name = "ENTITIES")
public class Entities implements Serializable {

    private int id;
    private String name;


    public Entities() {
    }

    public Entities(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    @SequenceGenerator(name = "ENTITIES_SEQ", sequenceName = "ENTITIES_SEQ", schema = "GESTION_CENTROS", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ENTITIES_SEQ")
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ENTITY_NAME", length = 150)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
