package com.preving.intranet.gestioncentrosapi.model.domain.workCenters;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.preving.intranet.gestioncentrosapi.model.domain.City;
import com.preving.intranet.gestioncentrosapi.model.domain.Entity;
import com.preving.intranet.gestioncentrosapi.model.domain.Province;
import com.preving.intranet.gestioncentrosapi.model.domain.User;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class WorkCenter implements Serializable {
    private int id;
    private int name;
    private Province province;
    private Entity entity;
    private City city;
    private String navisionCode;
    private String address;
    private String postalCode;
    private int phoneNumber;
    private String email;
    private User headPersonSearch;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Europe/Madrid")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Europe/Madrid")
    private Date endDate = null;

    public WorkCenter() {
    }

    public WorkCenter(int id, int name, Province province, Entity entity, City city, String navisionCode, String address, String postalCode, int phoneNumber, String email, User headPersonSearch, Date startDate, Date endDate) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getNavisionCode() {
        return navisionCode;
    }

    public void setNavisionCode(String navisionCode) {
        this.navisionCode = navisionCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getHeadPersonSearch() {
        return headPersonSearch;
    }

    public void setHeadPersonSearch(User headPersonSearch) {
        this.headPersonSearch = headPersonSearch;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}



