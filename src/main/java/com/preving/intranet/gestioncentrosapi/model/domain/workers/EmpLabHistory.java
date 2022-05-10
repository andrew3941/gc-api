package com.preving.intranet.gestioncentrosapi.model.domain.workers;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

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

    @ManyToOne
    @JoinColumn(name = "puesto_id")
    private Puesto puesto;

    @Column(name = "fch_entrada", nullable = false)
    private Instant fchEntrada;

    @Column(name = "fch_salida")
    private Instant fchSalida;

    @Column(name = "rl_id", nullable = false)
    private Integer rlId;

    @Column(name = "insert_by", nullable = false)
    private Integer insertBy;

    @Column(name = "insert_fch", nullable = false)
    private Instant insertFch;

    @Column(name = "update_by")
    private Integer updateBy;

    @Column(name = "update_fch")
    private Instant updateFch;

    @Column(name = "nivel_id")
    private Integer nivelId;

    @Column(name = "rol_organigrama_id")
    private Integer rolOrganigramaId;

    @Column(name = "convenio_id")
    private Integer convenioId;

    @Column(name = "adr_updated")
    private Date adrUpdated;

    @Column(name = "qdr_created")
    private Date qdrCreated;

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

    public Integer getConvenioId() {
        return convenioId;
    }

    public void setConvenioId(Integer convenioId) {
        this.convenioId = convenioId;
    }

    public Integer getRolOrganigramaId() {
        return rolOrganigramaId;
    }

    public void setRolOrganigramaId(Integer rolOrganigramaId) {
        this.rolOrganigramaId = rolOrganigramaId;
    }

    public Integer getNivelId() {
        return nivelId;
    }

    public void setNivelId(Integer nivelId) {
        this.nivelId = nivelId;
    }

    public Instant getUpdateFch() {
        return updateFch;
    }

    public void setUpdateFch(Instant updateFch) {
        this.updateFch = updateFch;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public Instant getInsertFch() {
        return insertFch;
    }

    public void setInsertFch(Instant insertFch) {
        this.insertFch = insertFch;
    }

    public Integer getInsertBy() {
        return insertBy;
    }

    public void setInsertBy(Integer insertBy) {
        this.insertBy = insertBy;
    }

    public Integer getRlId() {
        return rlId;
    }

    public void setRlId(Integer rlId) {
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

    @JsonIgnore
    public Employees getEmployee() {
        return employee;
    }

    public void setEmployee(Employees employee) {
        this.employee = employee;
    }

    public Puesto getPuesto() {
        return puesto;
    }

    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
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