package com.preving.intranet.gestioncentrosapi.model.domain.workCenters;

import com.preving.intranet.gestioncentrosapi.model.domain.Department;

import javax.persistence.*;
import java.io.Serializable;

public class WorkCenterDetails implements Serializable {

    private int Id;
    private int totalArea;
    private int jobAvailable;
    private int accesibility;
    private int parking;
    private String description;
    private int parkingPlace;
    private Department department = new Department();

    public WorkCenterDetails() {}

    public WorkCenterDetails(int Id, int totalArea, int jobAvailable, int accesibility, int parking, String description, int parkingPlace, Department department) {
        this.Id = Id;
        this.totalArea = totalArea;
        this.jobAvailable = jobAvailable;
        this.accesibility = accesibility;
        this.parking = parking;
        this.description = description;
        this.parkingPlace = parkingPlace;
        this.department = department;
    }
    @Id
    @Column(name = "ID", nullable = false)
    @SequenceGenerator(name = "PC_DELEGACIONES_SQ", sequenceName = "PC_DELEGACIONES_SQ", schema = "GC2006_RELEASE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PC_DELEGACIONES_SQ")
    public int getWorkCenterId() {
        return Id;
    }

    public void setWorkCenterId(int Id) {
        this.Id = Id;
    }

    @Basic
    @Column(name = "TOTAL_AREA")
    public int getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(int totalArea) {
        this.totalArea = totalArea;
    }

    @Basic
    @Column(name = "JOB_AVAILABLE")
    public int getJobAvailable() {
        return jobAvailable;
    }

    public void setJobAvailable(int jobAvailable) {
        this.jobAvailable = jobAvailable;
    }

    @Basic
    @Column(name = "ACCESIBILITY")
    public int getAccesibility() {
        return accesibility;
    }

    public void setAccesibility(int accesibility) {
        this.accesibility = accesibility;
    }

    @Basic
    @Column(name = "PARKING")
    public int getParking() {
        return parking;
    }

    public void setParking(int parking) {
        this.parking = parking;
    }

    @Basic
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "PARKING_PLACE")
    public int getParkingPlace() {
        return parkingPlace;
    }

    public void setParkingPlace(int parkingPlace) {
        this.parkingPlace = parkingPlace;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DEPARTMENT", referencedColumnName = "ID")
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
