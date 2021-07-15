package com.preving.intranet.gestioncentrosapi.model.domain;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Table(schema = "VIG_SALUD", name = "PROVINCIAS")
public class Province implements Serializable {

    private String cod;
    private String name;

    //create a class constructor for dependency injection
    public Province() {
    }

    public Province(String cod, String name) {
        this.cod = cod;
        this.name = name;
    }

    //create the get and set methods with the definition gonna have in the data base
    @Id
    @Column(name = "PRV_COD", nullable = false, precision = 0)
    public String getCod() { return cod; }
    public void setCod(String cod) { this.cod = cod; }

    @Basic
    @Column(name = "PRV_NOMBRE", length = 100, nullable = false)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
