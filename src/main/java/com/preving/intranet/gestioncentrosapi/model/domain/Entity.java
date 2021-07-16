package com.preving.intranet.gestioncentrosapi.model.domain;

import javax.persistence.*;
import java.io.Serializable;

@javax.persistence.Entity
@Table(schema = "GC2006_RELEASE", name = "TM_ENTIDADES")
public class Entity implements Serializable {

    private int id;
    private String name;


    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ACRONIMO", length = 150)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    //create a class constructor for dependency injection
    public Entity() {
    }

    public Entity(int id, String name) {
        this.id = id;
        this.name = name;
    }


}
