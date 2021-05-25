package com.preving.intranet.gestioncentrosapi.model.domain.workCenters;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.preving.intranet.gestioncentrosapi.model.domain.City;

import com.preving.intranet.gestioncentrosapi.model.domain.Entities;
import com.preving.intranet.gestioncentrosapi.model.domain.Province;
import com.preving.intranet.gestioncentrosapi.model.domain.User;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(schema = "GESTION_CENTROS", name = "WORKCENTERS")
public class WorkCenter implements Serializable {
    private int id;
    private String name;
    private Province province = new Province();
    private Entities entity = new Entities();
    private City city = new City();
    private String navisionCode;
    private String address;
    private String postalCode;
    private int phoneNumber;
    private String email;
    private User headPersonSearch = new User();
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Europe/Madrid")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Europe/Madrid")
    private Date endDate = null;

    public WorkCenter() {}

    public WorkCenter(int id, String name, Province province, Entities entity, City city, String navisionCode, String address, String postalCode, int phoneNumber, String email, User headPersonSearch, Date startDate, Date endDate) {
        this.id = id;
        this.name = name;
        this.province = province;
        this.entity = entity;
        this.city = city;
        this.navisionCode = navisionCode;
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.headPersonSearch = headPersonSearch;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Id
    @Column(name = "ID", nullable = false)
    @SequenceGenerator(name = "WORKCENTERS_SEQ", sequenceName = "WORKCENTERS_SEQ", schema = "GESTION_CENTROS", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WORKCENTERS_SEQ")
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    @Required
    @JoinColumn(name = "PROVINCE_ID", referencedColumnName = "ID")
    @NotNull
    public Province getProvince() {
        return province;
    }
    public void setProvince(Province province) {
        this.province = province;
    }

    @Required
    @JoinColumn(name = "ENTITIES_ID", referencedColumnName = "ID")
    @NotNull
    public Entities getEntity() {
        return entity;
    }
    public void setEntity(Entities entity) {
        this.entity = entity;
    }

    @Required
    @JoinColumn(name = "CITY_ID", referencedColumnName = "ID")
    @NotNull
    public City getCity() {
        return city;
    }
    public void setCity(City city) {
        this.city = city;
    }

    @Basic
    @Column(name = "NAVISION")
    public String getNavisionCode() {
        return navisionCode;
    }
    public void setNavisionCode(String navisionCode) {
        this.navisionCode = navisionCode;
    }

    @Basic
    @Column(name = "ADDRESS")
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "POSTAL_CODE")
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Basic
    @Column(name = "PHONE")
    public int getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    public User getHeadPersonSearch() {
        return headPersonSearch;
    }
    public void setHeadPersonSearch(User headPersonSearch) {
        this.headPersonSearch = headPersonSearch;
    }

    @Basic
    @Column(name = "START_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "END_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}



