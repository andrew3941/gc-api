package com.preving.intranet.gestioncentrosapi.model.domain;


import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "VIG_SALUD", name ="LOCALIDADES")
public class City implements Serializable {

    private int id;
    private String name;
    private Province prvCod = new Province();


    @Id
    @Column(name = "LOC_ID", nullable = false, precision = 0)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() { return id; }
    public void setId(int id) { this.id = id;  }

    @Basic
    @Column(name = "LOC_NOMBRE", length = 100)
    public String getName() { return name;  }
    public void setName(String name) { this.name = name;  }

    //create the relationship between PROVINCIAS and LOCALIDADES tables join the columns of each tables
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "LOC_PRV_COD", referencedColumnName = "PRV_COD", nullable = false)
    public Province getprvCod() {  return prvCod; }
    public void setprvCod(Province prvCod) { this.prvCod = prvCod;  }

    //create a class constructor for dependency injection
    public City() {
    }


    public City(int id, String name, Province prvCod) {
        this.id = id;
        this.name = name;
        this.prvCod = prvCod;
    }

    public City(int id, int prvCod, String name) {
        this.id = id;
        this.prvCod.setId(prvCod);
        this.name = name;
    }
  
}
