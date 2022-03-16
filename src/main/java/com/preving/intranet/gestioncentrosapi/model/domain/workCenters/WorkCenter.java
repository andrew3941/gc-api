package com.preving.intranet.gestioncentrosapi.model.domain.workCenters;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.preving.intranet.gestioncentrosapi.model.domain.*;
import com.preving.intranet.gestioncentrosapi.model.domain.generalDocumentation.GeneralDocumentation;
import com.preving.intranet.gestioncentrosapi.model.domain.vendors.ProvidersByWorkCenters;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
                                @ColumnResult(name = "FECHA_BAJA", type = Date.class),
                                @ColumnResult(name = "ACTIVO", type = Integer.class),
                                @ColumnResult(name = "TIPO_ID", type = Integer.class),
                                @ColumnResult(name = "LOCALIDAD_NOMBRE", type = String.class),
                                @ColumnResult(name = "PROVINCIA_COD", type = String.class),
                                @ColumnResult(name = "PROVINCIA_NOMBRE", type = String.class),
                                @ColumnResult(name = "RESPONSABLE", type = String.class)
                        }
                )
        }

)
public class WorkCenter implements Serializable {
    private int id;
    private String name;
    private City city = new City();
    private String navisionCode;
    private DimNavision dimNavision = new DimNavision();
    private String address;
    private String postalCode;
    private String phoneNumber;
    private String email;
    private User headPerson = new User();
    private int employee;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Europe/Madrid")
    private Date startDate = new Date();
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Europe/Madrid")
    private Date endDate = null;
    private Zona zone = new Zona();
    private int active;
    private int visible;
    private WorkCenterTypes workCenterTypes = new WorkCenterTypes();
    private Float latitude;
    private Float longitude;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    private Date created = new Date();
    private User createdBy = new User();
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    private Date modified;
    private User modifiedBy = new User();
    private List<WorkCentersByEntity> workCentersByEntities = new ArrayList<>();
//    private List<Drawing> drawings = new ArrayList<>();
//    private List<Room> rooms = new ArrayList<>();
//    private List<ProvidersByWorkCenters> providers = new ArrayList<>();
//    private List<GeneralDocumentation> generalDocumentations = new ArrayList<>();


    public WorkCenter() {
    }

    public WorkCenter(int id, Integer localityId, String name, String navisionCode, String address, String postalCode,
                      String phoneNumber, String email, Date startDate, Date endDate, int active, int workCenterTypesStatusId, String localityName,
                      String prvCod, String prvName, String headPersonName)  {
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
        this.active = active;
        this.workCenterTypes.setId(workCenterTypesStatusId);
        this.getCity().setName(localityName);
        this.getCity().getProvince().setCod(prvCod);
        this.getCity().getProvince().setName(prvName);
        this.getHeadPerson().setCompleteName(headPersonName);
    }

    public WorkCenter(int id, String name, City city, String navisionCode, DimNavision dimNavision, String address, String postalCode, String phoneNumber, String email, User headPerson, int employee, Date startDate, Date endDate, Zona zone, int active, int visible, WorkCenterTypes workCenterTypes, Date created, User createdBy, Date modified, User modifiedBy, List<WorkCentersByEntity> workCentersByEntities) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.navisionCode = navisionCode;
        this.dimNavision = dimNavision;
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.headPerson = headPerson;
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.zone = zone;
        this.active = active;
        this.visible = visible;
        this.workCenterTypes = workCenterTypes;
        this.created = created;
        this.createdBy = createdBy;
        this.modified = modified;
        this.modifiedBy = modifiedBy;
        this.workCentersByEntities = workCentersByEntities;
    }

    public WorkCenter(int id, String name) {
        this.id = id;
        this.name = name;
    }

//    public WorkCenter(List<Room> rooms) {
//        this.rooms = rooms;
//    }

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "RESPONSABLE", referencedColumnName = "ID")
    public User getHeadPerson() {
        return headPerson;
    }
    public void setHeadPerson(User headPerson) {
        this.headPerson = headPerson;
    }

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

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_IN_MP2", referencedColumnName = "COD_ZONA")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public Zona getZone() { return zone; }
    public void setZone(Zona zone) { this.zone = zone; }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "LINEA_ID", referencedColumnName = "ID")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public DimNavision getDimNavision() { return dimNavision; }
    public void setDimNavision(DimNavision dimNavision) { this.dimNavision = dimNavision; }

    @Basic
    @Column(name = "ACTIVO", nullable = false)
    public int getActive() {
        return active;
    }
    public void setActive(int active) {
        this.active = active;
    }

    @Basic
    @Column(name = "VISIBLE", nullable = false)
    public int getVisible() {
        return visible;
    }
    public void setVisible(int visible) {
        this.visible = visible;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TIPO_ID", referencedColumnName = "ID")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public WorkCenterTypes getWorkCenterTypes() {
        return workCenterTypes;
    }
    public void setWorkCenterTypes(WorkCenterTypes workCenterTypes) {
        this.workCenterTypes = workCenterTypes;
    }

    @Basic
    @Column(name = "LAT")
    public Float getLatitude() { return latitude; }
    public void setLatitude(Float latitude) { this.latitude = latitude; }

    @Basic
    @Column(name = "LNG")
    public Float getLongitude() { return longitude; }
    public void setLongitude(Float longitude) { this.longitude = longitude; }

    @Basic
    @Column(name = "CREADO")
    public Date getCreated() {
        return created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CREADO_POR", referencedColumnName = "ID")
    public User getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    @Basic
    @Column(name = "MODIFICADO")
    public Date getModified() {
        return modified;
    }
    public void setModified(Date modified) {
        this.modified = modified;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MODIFICADO_POR", referencedColumnName = "ID")
    public User getModifiedBy() {
        return modifiedBy;
    }
    public void setModifiedBy(User modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "workCenter")
    public List<WorkCentersByEntity> getWorkCentersByEntities() { return workCentersByEntities; }
    public void setWorkCentersByEntities(List<WorkCentersByEntity> workCentersByEntities) { this.workCentersByEntities = workCentersByEntities; }

//    @JsonIgnore
//    @OneToMany(mappedBy = "workCenter")
//    public List<Drawing> getDrawings() {
//        return drawings;
//    }
//    public void setDrawings(List<Drawing> drawings) {
//        this.drawings = drawings;
//    }

//    @JsonIgnore
//    @JsonManagedReference
//    @OneToMany(mappedBy = "workCenter")
//    public List<Room> getRooms() {
//        return rooms;
//    }
//    public void setRooms(List<Room> rooms) { this.rooms = rooms; }

    @Transient
    public int getEmployee() { return employee;}
    public void setEmployee(int employee) { this.employee = employee; }

//    @JsonIgnore
//    @JsonManagedReference
//    @OneToMany(mappedBy = "workCenter")
//    public List<ProvidersByWorkCenters> getProviders() {
//        return providers;
//    }
//    public void setProviders(List<ProvidersByWorkCenters> providers) {
//        this.providers = providers;
//    }

//    @JsonIgnore
//    @JsonManagedReference
//    @OneToMany(mappedBy = "workCenter")
//    public List<GeneralDocumentation> getGeneralDocumentations() { return generalDocumentations; }
//    public void setGeneralDocumentations(List<GeneralDocumentation> generalDocumentations) { this.generalDocumentations = generalDocumentations; }

}



