package com.preving.intranet.gestioncentrosapi.model.domain.vendors;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.preving.intranet.gestioncentrosapi.model.domain.City;
import com.preving.intranet.gestioncentrosapi.model.domain.User;
import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.WorkCenter;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(schema = "GESTION_CENTROS", name = "PROVEEDORES")
@SqlResultSetMapping(
        name = "ProviderMapping",
        classes = {
                @ConstructorResult(
                        targetClass = Provider.class,
                        columns = {
                                @ColumnResult(name = "ID", type = Integer.class),
                                @ColumnResult(name = "NOMBRE", type = String.class),
                                @ColumnResult(name = "CIF", type = String.class),
                                @ColumnResult(name = "DOC_NOMBRE", type = String.class),
                                @ColumnResult(name = "DOC_CONTENT_TYPE", type = String.class),
                                @ColumnResult(name = "ACTIVO", type = boolean.class),
                                @ColumnResult(name = "TIPO_ID", type = Integer.class),
                                @ColumnResult(name = "AREA_ID", type = Integer.class),
                                @ColumnResult(name = "LOCALIDAD_ID", type = Integer.class),
                                @ColumnResult(name = "LOC_NOMBRE", type = String.class),
                                @ColumnResult(name = "PRV_NOMBRE", type = String.class),
                                @ColumnResult(name = "PROVIDER_TYPE", type = String.class),
                                @ColumnResult(name = "PROVIDERAREA_TYPE", type = String.class),
                                @ColumnResult(name = "TIPO_EVALUACION_ID", type = Integer.class),
                                @ColumnResult(name = "PROVIDEREVALUATION_TYPE", type = String.class),
                                @ColumnResult(name = "FECHA_INICIO_SERVICIO", type = Date.class),

                        }
                )
        }
)

public class Provider implements Serializable {
    private int id;
    private List<WorkCenter> workCenters = new ArrayList<>();
    private String name;
    private String cif;
    private ProviderTypes providerTypes = new ProviderTypes();
    private ProviderArea providerArea = new ProviderArea();
    private ProviderEvaluationTypes evaluationTypes = new ProviderEvaluationTypes();
    private String email;
    private String address;
    private String contactPerson;
    private String telephone;
    private City city = new City();
    private String postalCode;
    private String serviceDetails;
    private String docUrl;
    private String docName;
    private String docContentType;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Europe/Madrid")
    private Date serviceStartDate = new Date();
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Europe/Madrid")
    private Date serviceEndDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    private Date created = new Date();
    private User createdBy = new User();
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    private Date modified;
    private User modifiedBy;
    private boolean active = true;

    public Provider() {
    }

    public Provider(int id, List<WorkCenter> workCenters, String name, String cif, ProviderTypes providerTypes, ProviderArea providerArea, ProviderEvaluationTypes evaluationTypes, String email, String address, String contactPerson, String telephone, City city, String postalCode, String serviceDetails, String docUrl, String docName, String docContentType, Date serviceStartDate, Date serviceEndDate, Date created, User createdBy, Date modified, User modifiedBy, boolean active) {
        this.id = id;
        this.workCenters = workCenters;
        this.name = name;
        this.cif = cif;
        this.providerTypes = providerTypes;
        this.providerArea = providerArea;
        this.evaluationTypes = evaluationTypes;
        this.email = email;
        this.address = address;
        this.contactPerson = contactPerson;
        this.telephone = telephone;
        this.city = city;
        this.postalCode = postalCode;
        this.serviceDetails = serviceDetails;
        this.docUrl = docUrl;
        this.docName = docName;
        this.docContentType = docContentType;
        this.serviceStartDate = serviceStartDate;
        this.serviceEndDate = serviceEndDate;
        this.created = created;
        this.createdBy = createdBy;
        this.modified = modified;
        this.modifiedBy = modifiedBy;
        this.active = active;
    }

    public Provider(
            int id,
            String name,
            String cif,
            String docName,
            String docContentType,
            boolean providerStatus,
            Integer providerTypeId,
            Integer providerAreaId,
            Integer localitiId,
            String localityName,
            String provinceName,
            String providerType ,
            String providerAreaType,
            Integer evaluationTypeId,
            String evaluationTypeName,
            Date serviceStartDate) {
        this.id = id;
        this.name = name;
        this.cif = cif;
        this.docName = docName;
        this.docContentType = docContentType;
        this.active = providerStatus;
        this.providerTypes.setId(providerTypeId);
        this.providerArea.setId(providerAreaId);
        this.city.setId(localitiId);
        this.city.setName(localityName);
        this.city.getProvince().setName(provinceName);
        this.providerTypes.setName(providerType);
        this.providerArea.setName(providerAreaType);
        this.evaluationTypes.setId(evaluationTypeId);
        this.evaluationTypes.setName(evaluationTypeName);
        this.getProviderTypes().setId(providerTypeId);
        this.getProviderTypes().setName(providerType);
        this.getProviderArea().setId(providerAreaId);
        this.getProviderArea().setName(providerAreaType);
        this.getEvaluationTypes().setId(evaluationTypeId);
        this.getEvaluationTypes().setName(evaluationTypeName);
        this.serviceStartDate = serviceStartDate;
    }


    @Id
    @Column(name = "ID", nullable = false)
    @SequenceGenerator(name = "PROVEEDORES_SEQ", sequenceName = "PROVEEDORES_SEQ", schema = "GESTION_CENTROS", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROVEEDORES_SEQ")
    public int getId() {
        return id;
    }
    public void setId(int id) {this.id = id; }

    @Transient
    public List<WorkCenter> getWorkCenters() {
        return workCenters;
    }
    public void setWorkCenters(List<WorkCenter> workCenters) {
        this.workCenters = workCenters;
    }

    @Basic
    @Column(name = "NOMBRE")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "CIF")
    public String getCif() {
        return cif;
    }
    public void setCif(String cif) {
        this.cif = cif;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TIPO_ID", referencedColumnName = "ID")
    public ProviderTypes getProviderTypes() {
        return providerTypes;
    }
    public void setProviderTypes(ProviderTypes providerTypes) {
        this.providerTypes = providerTypes;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "AREA_ID", referencedColumnName = "ID")
    public ProviderArea getProviderArea() {
        return providerArea;
    }
    public void setProviderArea(ProviderArea providerArea) {
        this.providerArea = providerArea;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TIPO_EVALUACION_ID", referencedColumnName = "ID")
    public ProviderEvaluationTypes getEvaluationTypes() {
        return evaluationTypes;
    }
    public void setEvaluationTypes(ProviderEvaluationTypes evaluationTypes) {
        this.evaluationTypes = evaluationTypes;
    }

    @Basic
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
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
    @Column(name = "PERSONA_CONTACTO")
    public String getContactPerson() {
        return contactPerson;
    }
    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    @Basic
    @Column(name = "TELEFONO")
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
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
    @Column(name = "CODIGO_POSTAL")
    public String getPostalCode() { return postalCode;  }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }

    @Basic
    @Column(name = "DETALLES")
    public String getServiceDetails() {
        return serviceDetails;
    }
    public void setServiceDetails(String serviceDetails) {
        this.serviceDetails = serviceDetails;
    }

    @Basic
    @Column(name = "DOC_URL")
    public String getDocUrl() {
        return docUrl;
    }
    public void setDocUrl(String docUrl) {
        this.docUrl = docUrl;
    }

    @Basic
    @Column(name = "DOC_NOMBRE")
    public String getDocName() {
        return docName;
    }
    public void setDocName(String docName) {
        this.docName = docName;
    }

    @Basic
    @Column(name = "DOC_CONTENT_TYPE")
    public String getDocContentType() {
        return docContentType;
    }
    public void setDocContentType(String docContentType) {
        this.docContentType = docContentType;
    }

    @Basic
    @Column(name = "FECHA_INICIO_SERVICIO")
    public Date getServiceStartDate() {
        return serviceStartDate;
    }
    public void setServiceStartDate(Date serviceStartDate) {
        this.serviceStartDate = serviceStartDate;
    }

    @Basic
    @Column(name = "FECHA_FIN_SERVICIO")
    public Date getServiceEndDate() {
        return serviceEndDate;
    }
    public void setServiceEndDate(Date serviceEndDate) {
        this.serviceEndDate = serviceEndDate;
    }

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

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "MODIFICADO_POR", referencedColumnName = "ID")
    public User getModifiedBy() {
        return modifiedBy;
    }
    public void setModifiedBy(User modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @Basic
    @Column(name = "ACTIVO", nullable = false)
    public boolean getActive() { return active; }
    public void setActive(boolean active) { this.active = active; }


}
