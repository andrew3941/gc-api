package com.preving.intranet.gestioncentrosapi.model.domain.workCenters;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.preving.intranet.gestioncentrosapi.model.domain.Department;
import com.preving.intranet.gestioncentrosapi.model.domain.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(schema = "GESTION_CENTROS" , name = "PC_DELEGACIONES_DETALLES")
public class WorkCenterDetails implements Serializable {

    private int Id;
    private WorkCenter workCenter;
    private int totalArea;
    private int jobAvailable;
    private int accesibility;
    private int parking;
    private String description;
    private int parkingPlace;
    private Department department = new Department();
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    private Date created = new Date();
    private User createdBy = new User();
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    private Date modified;
    private User modifiedBy = new User();

    public WorkCenterDetails() {}

    public WorkCenterDetails(int id, WorkCenter workCenter, int totalArea, int jobAvailable, int accesibility, int parking, String description, int parkingPlace, Department department, Date created, User createdBy, Date modified, User modifiedBy) {
        Id = id;
        this.workCenter = workCenter;
        this.totalArea = totalArea;
        this.jobAvailable = jobAvailable;
        this.accesibility = accesibility;
        this.parking = parking;
        this.description = description;
        this.parkingPlace = parkingPlace;
        this.department = department;
        this.created = created;
        this.createdBy = createdBy;
        this.modified = modified;
        this.modifiedBy = modifiedBy;
    }

    @Id
    @Column(name = "ID", nullable = false)
    @SequenceGenerator(name = "PC_DELEGACIONES_DETALLES_SEQ", sequenceName = "PC_DELEGACIONES_DETALLES_SEQ", schema = "gestion_centros", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PC_DELEGACIONES_DETALLES_SEQ")
    public int getId() { return Id; }
    public void setId(int Id) { this.Id = Id;  }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DELEGACION_ID", referencedColumnName = "ID")
    public WorkCenter getWorkCenter() {
        return workCenter;
    }

    public void setWorkCenter(WorkCenter workCenter) {
        this.workCenter = workCenter;
    }

    @Basic
    @Column(name = "SUPERFICIE")
    public int getTotalArea() { return totalArea; }
    public void setTotalArea(int totalArea) { this.totalArea = totalArea; }

    @Basic
    @Column(name = "PUESTOS_DISPONIBLES")
    public int getJobAvailable() { return jobAvailable; }
    public void setJobAvailable(int jobAvailable) { this.jobAvailable = jobAvailable; }

    @Basic
    @Column(name = "ACCESIBILIDAD")
    public int getAccesibility() { return accesibility; }
    public void setAccesibility(int accesibility) { this.accesibility = accesibility; }

    @Basic
    @Column(name = "PLAZAS_GARAJE")
    public int getParking() { return parking; }
    public void setParking(int parking) { this.parking = parking; }

    @Basic
    @Column(name = "DESCRIPCION")
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Basic
    @Column(name = "NUM_PLAZAS_GARAJE")
    public int getParkingPlace() { return parkingPlace; }
    public void setParkingPlace(int parkingPlace) { this.parkingPlace = parkingPlace;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TODOS_DPTOS", referencedColumnName = "ID")
    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }

    @Basic
    @Column(name = "CREADO")
    public Date getCreated() { return created; }

    public void setCreated(Date created) { this.created = created; }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CREADO_POR", referencedColumnName = "ID")
    public User getCreatedBy() { return createdBy; }
    public void setCreatedBy(User createdBy) { this.createdBy = createdBy; }

    @Basic
    @Column(name="MODIFICADO")
    public Date getModified() { return modified; }
    public void setModified(Date modified) { this.modified = modified; }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "MODIFICADO_POR", referencedColumnName = "ID")
    public User getModifiedBy() { return modifiedBy; }
    public void setModifiedBy(User modifiedBy) { this.modifiedBy = modifiedBy; }
}
