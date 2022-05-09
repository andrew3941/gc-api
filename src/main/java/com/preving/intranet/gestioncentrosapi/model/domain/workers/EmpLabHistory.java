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
    private int id;

    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Employees employee = new Employees();

    @Column(name = "delegacion_id", nullable = false)
    private int delegacionId;

    @ManyToOne
    @JoinColumn(name = "area_id",nullable = false)
    private Area area ;

    @Column(name = "puesto_id", nullable = false)
    private BigDecimal puestoId;

    @Column(name = "fch_entrada", nullable = false)
    private Date fchEntrada;

    @Column(name = "fch_salida")
    private Date fchSalida;

    @Column(name = "rl_id", nullable = false)
    private int rlId;

    @Column(name = "insert_by", nullable = false)
    private int insertBy;

    @Column(name = "insert_fch", nullable = false)
    private Date insertFch;

    @Column(name = "update_by")
    private int updateBy;

    @Column(name = "update_fch")
    private Date updateFch;

    @Column(name = "nivel_id")
    private BigDecimal nivelId;

    @Column(name = "rol_organigrama_id")
    private BigDecimal rolOrganigramaId;


//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "rol_organigrama_id", referencedColumnName = "ID")
//    private RolesEmployees rolesEmployees;

    @Column(name = "convenio_id")
    private int convenioId;

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

    public BigDecimal getRolOrganigramaId() {
        return rolOrganigramaId;
    }

    public void setRolOrganigramaId(BigDecimal rolOrganigramaId) {
        this.rolOrganigramaId = rolOrganigramaId;
    }


//    public RolesEmployees getRolesEmployees() {return rolesEmployees;}
//    public void setRolesEmployees(RolesEmployees rolesEmployees) {this.rolesEmployees = rolesEmployees;
//    }

    public BigDecimal getNivelId() {return nivelId;}
    public void setNivelId(BigDecimal nivelId) {this.nivelId = nivelId;}

    public Date getUpdateFch() {return updateFch;}
    public void setUpdateFch(Date updateFch) {this.updateFch = updateFch;}

    public int getUpdateBy() {return updateBy;}
    public void setUpdateBy(int updateBy) {this.updateBy = updateBy;}

    public Date getInsertFch() {return insertFch;}
    public void setInsertFch(Date insertFch) {this.insertFch = insertFch;}

    public int getInsertBy() {
        return insertBy;
    }

    public void setInsertBy(int insertBy) {
        this.insertBy = insertBy;
    }

    public int getRlId() {
        return rlId;
    }

    public void setRlId(int rlId) {
        this.rlId = rlId;
    }

    public Date getFchSalida() {
        return fchSalida;
    }

    public void setFchSalida(Date fchSalida) {
        this.fchSalida = fchSalida;
    }

    public Date getFchEntrada() {
        return fchEntrada;
    }

    public void setFchEntrada(Date fchEntrada) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}