package com.preving.intranet.gestioncentrosapi.model.domain;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "GESTIONCENTROS", name = "PROVINCE")
public class Province implements Serializable {
  private int id;
  private String name;

  @Id
  @Column(name = "PRV_COD", nullable = false, precision = 0)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }

  @Basic
  @Column(name = "PRV_NAME", length = 100, nullable = false)
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
