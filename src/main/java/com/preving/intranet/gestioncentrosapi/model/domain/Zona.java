package com.preving.intranet.gestioncentrosapi.model.domain;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "MP2", name ="ZONA")
public class Zona implements Serializable {

    private int codZona;
    private String denomination;
    private String name;
    private String telephone;
    private String email;
    private String address;
    private String codPostal;
    private String poblacion;


    @Id
    @Column(name = "COD_ZONA", nullable = false, precision = 0)
    public int getCodZona() {
        return codZona;
    }
    public void setCodZona(int codZona) {
        this.codZona = codZona;
    }

    @Basic
    @Column(name = "DENOMINACION", length = 50)
    public String getDenomination() {
        return denomination;
    }
    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    @Basic
    @Column(name = "NOMBRE", length = 250)
    public String getName() { return name;  }
    public void setName(String name) { this.name = name;  }

    @Basic
    @Column(name = "TELEFONO", length = 50)
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Basic
    @Column(name = "EMAIL", length = 250)
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "DIRECCION", length = 250)
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "COD_POSTAL", length = 10)
    public String getCodPostal() {
        return codPostal;
    }
    public void setCodPostal(String codPostal) {
        this.codPostal = codPostal;
    }

    @Basic
    @Column(name = "POBLACION", length = 50)
    public String getPoblacion() {
        return poblacion;
    }
    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }


    //create a class constructor for dependency injection
    public Zona() {
    }

    public Zona(int codZona, String name, String denomination, String telephone, String email, String address, String codPostal, String poblacion) {
        this.codZona = codZona;
        this.name = name;
        this.denomination = denomination;
        this.telephone = telephone;
        this.email = email;
        this.address = address;
        this.codPostal = codPostal;
        this.poblacion = poblacion;
    }
}
