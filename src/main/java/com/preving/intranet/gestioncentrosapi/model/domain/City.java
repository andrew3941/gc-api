package com.preving.intranet.gestioncentrosapi.model.domain;


import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "VIG_SALUD", name ="LOCALIDADES")
public class City implements Serializable {

    private int id;
    private String name;
    private Province province = new Province();

    @Id
    @Column(name = "ID", nullable = false, precision = 0)
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

    // TODO getter and setter province
    // Relation with the Province table (many to one)


    public City() {
    }

    public City(int id, String name, Province province) {
        this.id = id;
        this.name = name;
        this.province = province;
    }

  
}
