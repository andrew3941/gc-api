package com.preving.intranet.gestioncentrosapi.model.domain.generalDocumentation;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.preving.intranet.gestioncentrosapi.model.domain.User;
import com.preving.intranet.gestioncentrosapi.model.domain.vendors.ExpenditurePeriod;
import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.WorkCenter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(schema = "GESTION_CENTROS", name="PC_DELEGACIONES_X_DOC_GENERAL")
public class GeneralDocumentation implements Serializable {
    private int id;
    private GeneralDocumentationTypes generalDocTypes = new GeneralDocumentationTypes();
    private String documentName;
    private int documentImport;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Europe/Madrid")
    private Date documentStartDate = new Date();
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Europe/Madrid")
    private Date documentEndDate = null;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Europe/Madrid")
    private Date documentAlarmDate = null;
    private String observations;
    private String insurerName;
    private String policeNumber;
    private String mediator;
    private String telephone;
    private String email;
    private int annualImport;
    private ExpenditurePeriod periodicity;
    private int deposit;
    private CertificateTypes certificateTypes = null;
    private TaxesTypes taxesTypes = null;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    private Date created = new Date();
    private User createdBy = new User();
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    private Date modified;
    private User modifiedBy;
    private WorkCenter workCenter = new WorkCenter();
    private Date deleted;
    private User deletedBy;
    private List<GeneralDocByAttachment> generalDocByAttachments;

    public GeneralDocumentation(){
    }


    public GeneralDocumentation(int id, GeneralDocumentationTypes generalDocTypes, String documentName, int documentImport, Date documentStartDate, Date documentEndDate, Date documentAlarmDate, String observations, String insurerName, String policeNumber, String mediator, String telephone, String email, int annualImport, ExpenditurePeriod periodicity, int deposit, CertificateTypes certificateTypes, TaxesTypes taxesTypes, Date created, User createdBy, Date modified, User modifiedBy, WorkCenter workCenter, Date deleted, User deletedBy, List<GeneralDocByAttachment> generalDocByAttachments) {
        this.id = id;
        this.generalDocTypes = generalDocTypes;
        this.documentName = documentName;
        this.documentImport = documentImport;
        this.documentStartDate = documentStartDate;
        this.documentEndDate = documentEndDate;
        this.documentAlarmDate = documentAlarmDate;
        this.observations = observations;
        this.insurerName = insurerName;
        this.policeNumber = policeNumber;
        this.mediator = mediator;
        this.telephone = telephone;
        this.email = email;
        this.annualImport = annualImport;
        this.periodicity = periodicity;
        this.deposit = deposit;
        this.certificateTypes = certificateTypes;
        this.taxesTypes = taxesTypes;
        this.created = created;
        this.createdBy = createdBy;
        this.modified = modified;
        this.modifiedBy = modifiedBy;
        this.workCenter = workCenter;
        this.deleted = deleted;
        this.deletedBy = deletedBy;
        this.generalDocByAttachments = generalDocByAttachments;
    }

    @Id
    @Column(name = "ID", nullable = false)
    @SequenceGenerator(name = "PC_DELEGACIONES_X_DOC_GENERAL_SEQ", sequenceName = "PC_DELEGACIONES_X_DOC_GENERAL_SEQ", schema = "GESTION_CENTROS", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PC_DELEGACIONES_X_DOC_GENERAL_SEQ")
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TIPO_DOCUMENTO_ID", referencedColumnName = "ID")
    public GeneralDocumentationTypes getGeneralDocTypes() { return generalDocTypes; }
    public void setGeneralDocTypes(GeneralDocumentationTypes generalDocTypes) { this.generalDocTypes = generalDocTypes; }

    @Basic
    @Column(name = "NOMBRE")
    public String getDocumentName() { return documentName; }
    public void setDocumentName(String documentName) { this.documentName = documentName; }

    @Basic
    @Column(name = "IMPORTE")
    public int getDocumentImport() {
        return documentImport;
    }
    public void setDocumentImport(int documentImport) {
        this.documentImport = documentImport;
    }

    @Basic
    @Column(name = "FECHA_INICIO")
    public Date getDocumentStartDate() { return documentStartDate; }
    public void setDocumentStartDate(Date documentStartDate) { this.documentStartDate = documentStartDate; }

    @Basic
    @Column(name = "FECHA_FIN")
    public Date getDocumentEndDate() { return documentEndDate; }
    public void setDocumentEndDate(Date documentEndDate) { this.documentEndDate = documentEndDate; }

    @Basic
    @Column(name = "FECHA_PREAVISO")
    public Date getDocumentAlarmDate() {
        return documentAlarmDate;
    }
    public void setDocumentAlarmDate(Date documentAlarmDate) {
        this.documentAlarmDate = documentAlarmDate;
    }

    @Basic
    @Column(name = "OBSERVACIONES")
    public String getObservations() { return observations; }
    public void setObservations(String observations) { this.observations = observations; }

    @Basic
    @Column(name = "NOMBRE_ASEGURADORA")
    public String getInsurerName() { return insurerName; }
    public void setInsurerName(String insurerName) { this.insurerName = insurerName; }

    @Basic
    @Column(name = "NUMERO_POLIZA")
    public String getPoliceNumber() { return policeNumber; }
    public void setPoliceNumber(String policeNumber) { this.policeNumber = policeNumber; }

    @Basic
    @Column(name = "MEDIDADOR")
    public String getMediator() { return mediator; }
    public void setMediator(String mediator) { this.mediator = mediator; }

    @Basic
    @Column(name = "TELEFONO")
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Basic
    @Column(name = "EMAIL")
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Basic
    @Column(name = "IMPORTE_ANUAL")
    public int getAnnualImport() { return annualImport; }
    public void setAnnualImport(int annualImport) { this.annualImport = annualImport; }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PERIODICIDAD_GASTO_ID", referencedColumnName = "ID")
    public ExpenditurePeriod getPeriodicity() {
        return periodicity;
    }
    public void setPeriodicity(ExpenditurePeriod periodicity) {
        this.periodicity = periodicity;
    }

    @Basic
    @Column(name = "DEPOSITO")
    public int getDeposit() { return deposit; }
    public void setDeposit(int deposit) { this.deposit = deposit; }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CERTIFICADO_ID", referencedColumnName = "ID")
    public CertificateTypes getCertificateTypes() { return certificateTypes; }
    public void setCertificateTypes(CertificateTypes certificateTypes) { this.certificateTypes = certificateTypes; }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IMPUESTO_ID", referencedColumnName = "ID")
    public TaxesTypes getTaxesTypes() { return taxesTypes; }
    public void setTaxesTypes(TaxesTypes taxesTypes) { this.taxesTypes = taxesTypes; }


    @Basic
    @Column(name = "CREADO")
    public Date getCreated() { return created; }
    public void setCreated(Date created) { this.created = created; }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CREADO_POR", referencedColumnName = "ID")
    public User getCreatedBy() { return createdBy; }
    public void setCreatedBy(User createdBy) { this.createdBy = createdBy; }

    @Basic
    @Column(name = "MODIFICADO")
    public Date getModified() { return modified; }
    public void setModified(Date modified) { this.modified = modified; }

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "MODIFICADO_POR", referencedColumnName = "ID")
    public User getModifiedBy() { return modifiedBy; }
    public void setModifiedBy(User modifiedBy) { this.modifiedBy = modifiedBy; }

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DELEGACION_ID", referencedColumnName = "ID")
    public WorkCenter getWorkCenter() { return workCenter; }
    public void setWorkCenter(WorkCenter workCenter) { this.workCenter = workCenter; }

    @Basic
    @Column(name = "BORRADO")
    public Date getDeleted() { return deleted; }
    public void setDeleted(Date deleted) { this.deleted = deleted; }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "BORRADO_POR", referencedColumnName = "ID")
    public User getDeletedBy() { return deletedBy; }
    public void setDeletedBy(User deletedBy) { this.deletedBy = deletedBy; }

    // multiple files belong to general document
    @JsonManagedReference
    @OneToMany(mappedBy = "generalDoc",  fetch = FetchType.LAZY, cascade = { CascadeType.MERGE})
    public List<GeneralDocByAttachment> getGeneralDocByAttachments() {
        return generalDocByAttachments;
    }
    public void setGeneralDocByAttachments(List<GeneralDocByAttachment> generalDocByAttachments) {
        this.generalDocByAttachments = generalDocByAttachments;
    }
}


