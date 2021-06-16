package com.preving.intranet.gestioncentrosapi.model.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "VIG_SALUD", name = "PROVINCIAS")
public class Province implements Serializable {

    private int id;
    private String name;

    //create the get and set methods with the definition gonna have in the data base
    @Id
    @Column(name = "PRV_COD", nullable = false, precision = 0)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "PRV_NOMBRE", length = 100, nullable = false)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    //create a class constructor for dependency injection
    public Province() {
    }

    public Province(int id, String name) {
        this.id = id;
        this.name = name;
    }

}
