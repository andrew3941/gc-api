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
}



