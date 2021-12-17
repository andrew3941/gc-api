package com.preving.intranet.gestioncentrosapi.model.domain.vendors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.preving.intranet.gestioncentrosapi.model.domain.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(schema = "GESTION_CENTROS", name = "PROVEEDORES_DETALLES_COMUN")
public class ProvidersCommonDetails implements Serializable {
    private int id;
    private int provDelegacionId;
    private String docUrl;
    private String docName;
    private String docContentType;
    private String serviceDetails;
    private int spending;
    private int anualSpending;
    private ExpenditurePeriod expenditurePeriod = new ExpenditurePeriod();
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
    private String workCenterName;


    public ProvidersCommonDetails() {}

    public ProvidersCommonDetails(int id, int provDelegacionId, String docUrl, String docName, String docContentType, String serviceDetails, int spending, int anualSpending, ExpenditurePeriod expenditurePeriod, Date serviceStartDate, Date serviceEndDate, Date created, User createdBy, Date modified, User modifiedBy) {
        this.id = id;
        this.provDelegacionId = provDelegacionId;
        this.docUrl = docUrl;
        this.docName = docName;
        this.docContentType = docContentType;
        this.serviceDetails = serviceDetails;
        this.spending = spending;
        this.anualSpending = anualSpending;
        this.expenditurePeriod = expenditurePeriod;
        this.serviceStartDate = serviceStartDate;
        this.serviceEndDate = serviceEndDate;
        this.created = created;
        this.createdBy = createdBy;
        this.modified = modified;
        this.modifiedBy = modifiedBy;
    }

    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    @SequenceGenerator(name = "PROVEEDORES_DETALLES_COMUN_SEQ", sequenceName = "PROVEEDORES_DETALLES_COMUN_SEQ", schema = "GESTION_CENTROS", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROVEEDORES_DETALLES_COMUN_SEQ")
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @Basic
    @Column(name = "PROV_X_DELEGACION_ID")
    public int getProvDelegacionId() {
        return provDelegacionId;
    }
    public void setProvDelegacionId(int provDelegacionId) {
        this.provDelegacionId = provDelegacionId;
    }

    @Basic
    @Column(name = "DOC_URL")
    public String getDocUrl() { return docUrl; }
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
    @Column(name = "DETALLES")
    public String getServiceDetails() {
        return serviceDetails;
    }
    public void setServiceDetails(String serviceDetails) {
        this.serviceDetails = serviceDetails;
    }

    @Basic
    @Column(name = "GASTO")
    public int getSpending() {
        return spending;
    }
    public void setSpending(int spending) {
        this.spending = spending;
    }

    @Basic
    @Column(name = "GASTO_ANUAL")
    public int getAnualSpending() { return anualSpending;}
    public void setAnualSpending(int anualSpending) {
        this.anualSpending = anualSpending;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PERIODICIDAD_GASTO_ID", referencedColumnName = "ID")
    public ExpenditurePeriod getExpenditurePeriod() { return expenditurePeriod; }
    public void setExpenditurePeriod(ExpenditurePeriod expenditurePeriod) { this.expenditurePeriod = expenditurePeriod; }

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

    @Transient
    public String getWorkCenterName() {
        return workCenterName;
    }
    public void setWorkCenterName(String workCenterName) {
        this.workCenterName = workCenterName;
    }
}
