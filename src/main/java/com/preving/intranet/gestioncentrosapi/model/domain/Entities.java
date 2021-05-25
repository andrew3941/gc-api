package com.preving.intranet.gestioncentrosapi.model.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "GESTIONCENTROS", name = "ENTITY")
public class Entities implements Serializable {

    private int id;
    private String name;

    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ENTITYNAME", length = 150)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public Entities() {
    }

    public Entities(int id, String name) {
        this.id = id;
        this.name = name;
    }

}
