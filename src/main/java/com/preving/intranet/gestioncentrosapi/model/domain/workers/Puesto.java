package com.preving.intranet.gestioncentrosapi.model.domain.workers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.preving.intranet.gestioncentrosapi.model.domain.Department;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tm_puestos", schema = "rrhh")
public class Puesto {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "orden", nullable = false)
    private Integer orden;

    @Column(name = "activo", nullable = false)
    private Integer activo;

    @ManyToOne
    @JoinColumn(name = "departamento_id")
    private Department department;

    @Column(name = "adr_updated")
    private LocalDateTime adrUpdated;

    @Column(name = "qdr_created")
    private LocalDateTime qdrCreated;

    public LocalDateTime getQdrCreated() {
        return qdrCreated;
    }

    public void setQdrCreated(LocalDateTime qdrCreated) {
        this.qdrCreated = qdrCreated;
    }

    public LocalDateTime getAdrUpdated() {
        return adrUpdated;
    }

    public void setAdrUpdated(LocalDateTime adrUpdated) {
        this.adrUpdated = adrUpdated;
    }

    @JsonIgnore
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}