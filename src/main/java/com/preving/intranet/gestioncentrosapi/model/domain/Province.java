package com.preving.intranet.gestioncentrosapi.model.domain;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "VIG_SALUD", name = "PROVINCIAS")
public class Province implements Serializable {

  private int id;
  private String name;


  @Id
  @Column(name = "PRV_COD", nullable = false)
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


  public Province() {
  }

  public Province(int id, String name) {
    this.id = id;
    this.name = name;
  }

}
