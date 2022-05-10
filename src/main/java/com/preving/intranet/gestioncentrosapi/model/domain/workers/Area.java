package com.preving.intranet.gestioncentrosapi.model.domain.workers;

import com.preving.intranet.gestioncentrosapi.model.domain.Department;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "tm_areas", schema = "rrhh")
public class Area {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "orden", nullable = false)
    private BigDecimal orden;

    @Column(name = "activo", nullable = false)
    private Integer activo;

    @ManyToOne
    @JoinColumn(name = "departamento_id",nullable = false)
    private Department department;

    @Column(name = "redmine_project", length = 200)
    private String redmineProject;

    @Column(name = "adr_updated")
    private Instant adrUpdated;

    @Column(name = "qdr_created")
    private Instant qdrCreated;

    public Instant getQdrCreated() {
        return qdrCreated;
    }

    public void setQdrCreated(Instant qdrCreated) {
        this.qdrCreated = qdrCreated;
    }

    public Instant getAdrUpdated() {
        return adrUpdated;
    }

    public void setAdrUpdated(Instant adrUpdated) {
        this.adrUpdated = adrUpdated;
    }

    public String getRedmineProject() {
        return redmineProject;
    }

    public void setRedmineProject(String redmineProject) {
        this.redmineProject = redmineProject;
    }

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

    public BigDecimal getOrden() {
        return orden;
    }

    public void setOrden(BigDecimal orden) {
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
