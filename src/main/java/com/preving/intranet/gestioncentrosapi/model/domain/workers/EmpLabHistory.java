package com.preving.intranet.gestioncentrosapi.model.domain.workers;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "emp_historialab", schema = "rrhh")
public class EmpLabHistory {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Employees employee;

    @Column(name = "delegacion_id", nullable = false)
    private int delegacionId;

    @ManyToOne
    @JoinColumn(name = "area_id",nullable = false)
    private Area area;

    @Column(name = "puesto_id", nullable = false)
    private BigDecimal puestoId;

    @Column(name = "fch_entrada", nullable = false)
    private Instant fchEntrada;

    @Column(name = "fch_salida")
    private Instant fchSalida;

    @Column(name = "rl_id", nullable = false)
    private BigDecimal rlId;

    @Column(name = "insert_by", nullable = false)
    private BigDecimal insertBy;

    @Column(name = "insert_fch", nullable = false)
    private Instant insertFch;

    @Column(name = "update_by")
    private BigDecimal updateBy;

    @Column(name = "update_fch")
    private Instant updateFch;

    @Column(name = "nivel_id")
    private BigDecimal nivelId;

    @Column(name = "rol_organigrama_id")
    private BigDecimal rolOrganigramaId;

    @Column(name = "convenio_id")
    private Integer convenioId;

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

    public Integer getConvenioId() {
        return convenioId;
    }

    public void setConvenioId(Integer convenioId) {
        this.convenioId = convenioId;
    }

    public BigDecimal getRolOrganigramaId() {
        return rolOrganigramaId;
    }

    public void setRolOrganigramaId(BigDecimal rolOrganigramaId) {
        this.rolOrganigramaId = rolOrganigramaId;
    }

    public BigDecimal getNivelId() {
        return nivelId;
    }

    public void setNivelId(BigDecimal nivelId) {
        this.nivelId = nivelId;
    }

    public Instant getUpdateFch() {
        return updateFch;
    }

    public void setUpdateFch(Instant updateFch) {
        this.updateFch = updateFch;
    }

    public BigDecimal getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(BigDecimal updateBy) {
        this.updateBy = updateBy;
    }

    public Instant getInsertFch() {
        return insertFch;
    }

    public void setInsertFch(Instant insertFch) {
        this.insertFch = insertFch;
    }

    public BigDecimal getInsertBy() {
        return insertBy;
    }

    public void setInsertBy(BigDecimal insertBy) {
        this.insertBy = insertBy;
    }

    public BigDecimal getRlId() {
        return rlId;
    }

    public void setRlId(BigDecimal rlId) {
        this.rlId = rlId;
    }

    public Instant getFchSalida() {
        return fchSalida;
    }

    public void setFchSalida(Instant fchSalida) {
        this.fchSalida = fchSalida;
    }

    public Instant getFchEntrada() {
        return fchEntrada;
    }

    public void setFchEntrada(Instant fchEntrada) {
        this.fchEntrada = fchEntrada;
    }

    public BigDecimal getPuestoId() {
        return puestoId;
    }

    public void setPuestoId(BigDecimal puestoId) {
        this.puestoId = puestoId;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public int getDelegacionId() {
        return delegacionId;
    }

    public void setDelegacionId(int delegacionId) {
        this.delegacionId = delegacionId;
    }

    @JsonBackReference
    public Employees getEmpleadoId() {
        return employee;
    }

    public void setEmpleadoId(Employees employee) {
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}