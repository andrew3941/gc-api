package com.preving.intranet.gestioncentrosapi.model.domain.workCenters;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.preving.intranet.gestioncentrosapi.model.domain.Entity;
import com.preving.intranet.gestioncentrosapi.model.domain.Province;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class WorkCenter implements Serializable {
    private int id;
    private int name;
    private List<Province> provinces;
    // private Province province
    private List<Entity> mainEntities;
    // private Entity entity
    private String locality;
    // Private Locality locality
    private String navisionCode;
    private String address;
    private String postalCode;
    private int phoneNumber;
    private String email;
    private String headpersonSearch;
    // Private User headPerson
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Europe/Madrid")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Europe/Madrid")
    private Date endDate = null;

    public WorkCenter() {
    }

    public WorkCenter(int id, int name, String locality, String navisionCode, String address, String postalCode, int phoneNumber, String email, String headpersonSearch, Date startDate, Date endDate) {
        this.id = id;
        this.name = name;
        this.locality = locality;
        this.navisionCode = navisionCode;
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.headpersonSearch = headpersonSearch;
        this.startDate = startDate;
        this.endDate = endDate;
    }


}



