package com.preving.intranet.gestioncentrosapi.model.domain.workCenters;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.preving.intranet.gestioncentrosapi.model.domain.City;
import com.preving.intranet.gestioncentrosapi.model.domain.User;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(schema = "GC2006_RELEASE", name = "PC_DELEGACIONES")
@SqlResultSetMapping(
        name = "WorkCenterMapping",
        classes = {
                @ConstructorResult(
                        targetClass = WorkCenter.class,
                        columns = {
                                @ColumnResult(name = "ID", type = Integer.class),
                                @ColumnResult(name = "LOCALIDAD_ID", type = Integer.class),
                                @ColumnResult(name = "NOMBRE", type = String.class),
                                @ColumnResult(name = "COD_IN_NAV", type = String.class),
                                @ColumnResult(name = "DIRECCION", type = String.class),
                                @ColumnResult(name = "C_POSTAL", type = String.class),
                                @ColumnResult(name = "TFNO", type = String.class),
                                @ColumnResult(name = "MAIL", type = String.class),
                                @ColumnResult(name = "FECHA_ALTA", type = Date.class),
                                @ColumnResult(name = "FECHA_BAJA", type = Date.class)
                        }
                )
        }

)
public class WorkCenter implements Serializable {
    private int id;
    private String name;
    private City city = new City();
    private String navisionCode;
    private String address;
    private String postalCode;
    private String phoneNumber;
    private String email;
//    private User headPersonSearch = new User();
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Europe/Madrid")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Europe/Madrid")
    private Date endDate = null;

    public WorkCenter() {}

    public WorkCenter(int id, Integer localityId, String name, String navisionCode, String address, String postalCode, String phoneNumber, String email, Date startDate, Date endDate) {
        this.id = id;
        this.city.setId(localityId) ;
        this.name = name;
        this.navisionCode = navisionCode;
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public WorkCenter(int id, String name, City city, String navisionCode, String address, String postalCode, String phoneNumber, String email, Date startDate, Date endDate) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.navisionCode = navisionCode;
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Id
    @Column(name = "ID", nullable = false)
    @SequenceGenerator(name = "PC_DELEGACIONES_SQ", sequenceName = "PC_DELEGACIONES_SQ", schema = "GC2006_RELEASE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PC_DELEGACIONES_SQ")
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NOMBRE")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "LOCALIDAD_ID", referencedColumnName = "LOC_ID")
    public City getCity() {
        return city;
    }
    public void setCity(City city) {
        this.city = city;
    }

    @Basic
    @Column(name = "COD_IN_NAV")
    public String getNavisionCode() {
        return navisionCode;
    }
    public void setNavisionCode(String navisionCode) {
        this.navisionCode = navisionCode;
    }

    @Basic
    @Column(name = "DIRECCION")
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "C_POSTAL")
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Basic
    @Column(name = "TFNO")
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "MAIL")
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


    // TODO the column name is "Responsable"
   // @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
//    public User getHeadPersonSearch() {
//        return headPersonSearch;
//    }
//    public void setHeadPersonSearch(User headPersonSearch) {
//        this.headPersonSearch = headPersonSearch;
//    }

    @Basic
    @Column(name = "FECHA_ALTA")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "FECHA_BAJA")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}



