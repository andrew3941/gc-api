package com.preving.intranet.gestioncentrosapi.model.domain.workCenters;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.preving.intranet.gestioncentrosapi.model.domain.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(schema = "GESTION_CENTROS" , name = "PC_DELEGACIONES_DETALLES")
public class WorkCenterDetails implements Serializable {

    private int id;
    private WorkCenter workCenter;
    private Double totalArea;
    private Integer jobAvailable;
    private Boolean accesibility;
    private boolean parking;
    private Integer parkingPlace;
    private String description;
    private Boolean allDepartment;
    private Integer communityAmount;
    private boolean stealingAlarm;
    private boolean fireAlarm;
    private Integer umParkingPlaces;
    private String administrator;
    private String email;
    private Integer phone;
    private List<WorkCenterDetailsByDepart> departments;
    private String cadastralRef;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    private Date created = new Date();
    private User createdBy = new User();
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    private Date modified;
    private User modifiedBy;

    public WorkCenterDetails() {}

    public WorkCenterDetails(int id, WorkCenter workCenter, Double totalArea, Integer jobAvailable, Boolean accesibility,
                             boolean parking, int parkingPlace, String description, Boolean allDepartment, int communityAmount, boolean stealingAlarm, boolean fireAlarm, int umParkingPlaces,
                             String administrator, String email, int phone,
                             List<WorkCenterDetailsByDepart> departments, String cadastralRef, String latitude, String longitude,
                             Date created, User createdBy, Date modified, User modifiedBy) {
        this.id = id;
        this.workCenter = workCenter;
        this.totalArea = totalArea;
        this.jobAvailable = jobAvailable;
        this.accesibility = accesibility;
        this.parking = parking;
        this.parkingPlace = parkingPlace;
        this.description = description;
        this.allDepartment = allDepartment;
        this.departments = departments;
        this.communityAmount = communityAmount;
        this.stealingAlarm = stealingAlarm;
        this.fireAlarm = fireAlarm;
        this.umParkingPlaces = umParkingPlaces;
        this.administrator = administrator;
        this.email = email;
        this.phone = phone;
        this.cadastralRef = cadastralRef;
        this.created = created;
        this.createdBy = createdBy;
        this.modified = modified;
        this.modifiedBy = modifiedBy;
    }

    @Id
    @Column(name = "ID", nullable = false)
    @SequenceGenerator(name = "PC_DELEGACIONES_DETALLES_SEQ", sequenceName = "PC_DELEGACIONES_DETALLES_SEQ", schema = "gestion_centros", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PC_DELEGACIONES_DETALLES_SEQ")
    public int getId() { return id; }
    public void setId(int id) { this.id = id;  }

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "DELEGACION_ID", referencedColumnName = "ID")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public WorkCenter getWorkCenter() {
        return workCenter;
    }
    public void setWorkCenter(WorkCenter workCenter) {
        this.workCenter = workCenter;
    }

    @Basic
    @Column(name = "SUPERFICIE")
    public Double getTotalArea() { return totalArea; }
    public void setTotalArea(Double totalArea) { this.totalArea = totalArea; }

    @Basic
    @Column(name = "PUESTOS_DISPONIBLES")
    public Integer getJobAvailable() { return jobAvailable; }
    public void setJobAvailable(Integer jobAvailable) { this.jobAvailable = jobAvailable; }

    @Basic
    @Column(name = "ACCESIBILIDAD")
    public Boolean isAccesibility() {
        return accesibility;
    }
    public void setAccesibility(Boolean accesibility) {
        this.accesibility = accesibility;
    }

    @Basic
    @Column(name = "PLAZAS_GARAJE")
    public boolean isParking() {
        return parking;
    }
    public void setParking(boolean parking) {
        this.parking = parking;
    }

    @Basic
    @Column(name = "NUM_PLAZAS_GARAJE_VEHICULOS")
    public Integer getParkingPlace() { return parkingPlace; }
    public void setParkingPlace(Integer parkingPlace) { this.parkingPlace = parkingPlace;
    }

    @Basic
    @Column(name = "DESCRIPCION")
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Basic
    @Column(name = "TODOS_DPTOS")
    public Boolean isAllDepartment() {
        return allDepartment;
    }
    public void setAllDepartment(Boolean allDepartment) {
        this.allDepartment = allDepartment;
    }

    @Basic
    @Column(name = "GASTO_COMUNIDAD")
    public Integer getCommunityAmount() { return communityAmount; }
    public void setCommunityAmount(Integer communityAmount) { this.communityAmount = communityAmount; }

    @Basic
    @Column(name = "ALARMA_ROBO")
    public boolean isStealingAlarm() {
        return stealingAlarm;
    }
    public void setStealingAlarm(boolean stealingAlarm) {
        this.stealingAlarm = stealingAlarm;
    }

    @Basic
    @Column(name = "ALARMA_INCENDIO")
    public boolean isFireAlarm() {
        return fireAlarm;
    }
    public void setFireAlarm(boolean fireAlarm) {
        this.fireAlarm = fireAlarm;
    }

    @Basic
    @Column(name = "NUM_PLAZAS_GARAJE_UM")
    public Integer getUmParkingPlaces() {return umParkingPlaces;}
    public void setUmParkingPlaces(Integer umParkingPlaces) {this.umParkingPlaces = umParkingPlaces;}

    @Basic
    @Column(name = "ADMINISTRADOR_COMUNIDAD")
    public String getAdministrator() {return administrator;}
    public void setAdministrator(String administrator) {this.administrator = administrator;}

    @Basic
    @Column(name = "EMAIL")
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    @Basic
    @Column(name = "TELEFONO")
    public Integer getPhone() {return phone;}
    public void setPhone(Integer phone) {this.phone = phone;}

    @JsonManagedReference
    @OneToMany(mappedBy = "workCenterDetails")
    public List<WorkCenterDetailsByDepart> getDepartments() { return departments; }
    public void setDepartments(List<WorkCenterDetailsByDepart> departments) { this.departments = departments; }

    @Basic
    @Column(name = "REF_CATASTRAL")
    public String getCadastralRef() { return cadastralRef; }
    public void setCadastralRef(String cadastralRef) { this.cadastralRef = cadastralRef; }

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
