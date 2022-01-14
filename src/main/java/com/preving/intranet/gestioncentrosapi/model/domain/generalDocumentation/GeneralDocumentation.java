package com.preving.intranet.gestioncentrosapi.model.domain.generalDocumentation;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.preving.intranet.gestioncentrosapi.model.domain.User;
import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.WorkCenter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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
    private Date documentEndDate = new  Date();
    private String observations;
    private String insurerName;
    private String policeNumber;
    private String mediator;
    private String telephone;
    private String email;
    private int annualImport;
    private int periodicityExpenditureId;
    private int deposit;
    private CertificateTypes certificateTypes = new CertificateTypes();
    private TaxesTypes taxesTypes = new TaxesTypes();
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    private Date created = new Date();
    private User createdBy = new User();
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    private Date modified;
    private User modifiedBy;
    private WorkCenter workCenter = new WorkCenter();
    private Date deleted;
    private User deletedBy;


    private GeneralDocumentation(){
    }

    public GeneralDocumentation(int id, GeneralDocumentationTypes generalDocTypes, String documentName, int documentImport, Date documentStartDate, Date documentEndDate, String observations, String insurerName, String policeNumber, String mediator, String telephone, String email, int annualImport, int periodicityExpenditureId, int deposit, CertificateTypes certificateTypes, TaxesTypes taxesTypes, Date created, User createdBy, Date modified, User modifiedBy, WorkCenter workCenter, Date deleted, User deletedBy) {
        this.id = id;
        this.generalDocTypes = generalDocTypes;
        this.documentName = documentName;
        this.documentImport = documentImport;
        this.documentStartDate = documentStartDate;
        this.documentEndDate = documentEndDate;
        this.observations = observations;
        this.insurerName = insurerName;
        this.policeNumber = policeNumber;
        this.mediator = mediator;
        this.telephone = telephone;
        this.email = email;
        this.annualImport = annualImport;
        this.periodicityExpenditureId = periodicityExpenditureId;
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
    public int getdocumentImport() { return documentImport; }
    public void setdocumentImport(int documentImport) { this.documentImport = documentImport; }

    @Basic
    @Column(name = "FECHA_INICIO")
    public Date getDocumentStartDate() { return documentStartDate; }
    public void setDocumentStartDate(Date documentStartDate) { this.documentStartDate = documentStartDate; }

    @Basic
    @Column(name = "FECHA_FIN")
    public Date getDocumentEndDate() { return documentEndDate; }
    public void setDocumentEndDate(Date documentEndDate) { this.documentEndDate = documentEndDate; }

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
    public String gettelephone() { return telephone; }
    public void settelephone(String telephone) { this.telephone = telephone; }

    @Basic
    @Column(name = "EMAIL")
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Basic
    @Column(name = "IMPORTE_ANUAL")
    public int getAnnualImport() { return annualImport; }
    public void setAnnualImport(int annualImport) { this.annualImport = annualImport; }

    @Basic
    @Column(name = "PERIODICIDAD_GASTO_ID")
    public int getPeriodicityExpenditureId() { return periodicityExpenditureId; }
    public void setPeriodicityExpenditureId(int periodicityExpenditureId) { this.periodicityExpenditureId = periodicityExpenditureId; }

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

    @JsonBackReference
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
}


