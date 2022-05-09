package com.preving.intranet.gestioncentrosapi.model.domain.workers;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.DataInput;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "emp_contacto", schema = "rrhh")
public class EmpContacto  implements Serializable {
    @Id
    @OneToOne
    @JoinColumn(name = "empleado_id",nullable = false)
    private Employees employee = new Employees();

    @Column(name = "tfno_personal1", length = 9)
    private String tfnoPersonal1;

    @Column(name = "tfno_personal2", length = 9)
    private String tfnoPersonal2;

    @Column(name = "tfno_empresa1", length = 9)
    private String tfnoEmpresa1;

    @Column(name = "tfno_empresa2", length = 4)
    private String tfnoEmpresa2;

    @Column(name = "saldo", precision = 5, scale = 2)
    private BigDecimal saldo;

    @Column(name = "tarifa_plana")
    private Integer tarifaPlana;

    @Column(name = "email_personal", length = 100)
    private String emailPersonal;

    @Column(name = "email_empresa", length = 100)
    private String emailEmpresa;

    @Column(name = "adr_updated")
    private Date adrUpdated;

    @Column(name = "qdr_created")
    private Date qdrCreated;



    public EmpContacto() {
    }

    public EmpContacto(Employees employee, String tfnoPersonal1, String tfnoPersonal2, String tfnoEmpresa1, String tfnoEmpresa2, BigDecimal saldo, Integer tarifaPlana, String emailPersonal, String emailEmpresa, Date adrUpdated, Date qdrCreated) {
        this.employee = employee;
        this.tfnoPersonal1 = tfnoPersonal1;
        this.tfnoPersonal2 = tfnoPersonal2;
        this.tfnoEmpresa1 = tfnoEmpresa1;
        this.tfnoEmpresa2 = tfnoEmpresa2;
        this.saldo = saldo;
        this.tarifaPlana = tarifaPlana;
        this.emailPersonal = emailPersonal;
        this.emailEmpresa = emailEmpresa;
        this.adrUpdated = adrUpdated;
        this.qdrCreated = qdrCreated;
    }

    public Date getQdrCreated() {
        return qdrCreated;
    }

    public void setQdrCreated(Date qdrCreated) {
        this.qdrCreated = qdrCreated;
    }

    public Date getAdrUpdated() {
        return adrUpdated;
    }

    public void setAdrUpdated(Date adrUpdated) {
        this.adrUpdated = adrUpdated;
    }

    public String getEmailEmpresa() {
        return emailEmpresa;
    }

    public void setEmailEmpresa(String emailEmpresa) {
        this.emailEmpresa = emailEmpresa;
    }

    public String getEmailPersonal() {
        return emailPersonal;
    }

    public void setEmailPersonal(String emailPersonal) {
        this.emailPersonal = emailPersonal;
    }

    public Integer getTarifaPlana() {
        return tarifaPlana;
    }

    public void setTarifaPlana(Integer tarifaPlana) {
        this.tarifaPlana = tarifaPlana;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public String getTfnoEmpresa2() {
        return tfnoEmpresa2;
    }

    public void setTfnoEmpresa2(String tfnoEmpresa2) {
        this.tfnoEmpresa2 = tfnoEmpresa2;
    }

    public String getTfnoEmpresa1() {
        return tfnoEmpresa1;
    }

    public void setTfnoEmpresa1(String tfnoEmpresa1) {
        this.tfnoEmpresa1 = tfnoEmpresa1;
    }

    public String getTfnoPersonal2() {
        return tfnoPersonal2;
    }

    public void setTfnoPersonal2(String tfnoPersonal2) {
        this.tfnoPersonal2 = tfnoPersonal2;
    }

    public String getTfnoPersonal1() {
        return tfnoPersonal1;
    }

    public void setTfnoPersonal1(String tfnoPersonal1) {
        this.tfnoPersonal1 = tfnoPersonal1;
    }

    @JsonIgnore
    public Employees getEmployee() {
        return employee;
    }

    public void setEmployee(Employees employee) {
            this.employee = employee;
    }
}