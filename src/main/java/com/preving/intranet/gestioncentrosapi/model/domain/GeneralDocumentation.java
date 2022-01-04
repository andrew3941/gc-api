package com.preving.intranet.gestioncentrosapi.model.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(schema = "GESTION_CENTROS", name="PC_DELEGACIONES_X_DOC_GENERAL")
public class GeneralDocumentation implements Serializable {
    private int id;
    private DocumentTypes documentTypes = new DocumentTypes();
    private String documentName;
    private String documentimport;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Europe/Madrid")
    private Date documentStartDate = new Date();
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Europe/Madrid")
    private Date documentEndDate = new  Date();
    private String observations;
    private String insurerName;
    private String policeNumber;
    private String mediator;
    private String telepnone;
    private String email;
    private int annualImport;
    private int periodicityExpenditureId;
    private int deposit;
    private Certificate certificate = new Certificate();
    private Taxes taxes = new Taxes();
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    private Date created = new Date();
    private User createdBy = new User();
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    private Date modified;
    private User modifiedBy;

    private GeneralDocumentation(){
    }

    public GeneralDocumentation(int id, DocumentTypes documentTypes, String documentName, String documentimport, Date documentStartDate, Date documentEndDate, String observations, String insurerName, String policeNumber, String mediator, String telepnone, String email, int annualImport, int periodicityExpenditureId, int deposit, Certificate certificate, Taxes taxes, Date created, User createdBy, Date modified, User modifiedBy) {
        this.id = id;
        this.documentTypes = documentTypes;
        this.documentName = documentName;
        this.documentimport = documentimport;
        this.documentStartDate = documentStartDate;
        this.documentEndDate = documentEndDate;
        this.observations = observations;
        this.insurerName = insurerName;
        this.policeNumber = policeNumber;
        this.mediator = mediator;
        this.telepnone = telepnone;
        this.email = email;
        this.annualImport = annualImport;
        this.periodicityExpenditureId = periodicityExpenditureId;
        this.deposit = deposit;
        this.certificate = certificate;
        this.taxes = taxes;
        this.created = created;
        this.createdBy = createdBy;
        this.modified = modified;
        this.modifiedBy = modifiedBy;
    }

    @Id
    @Column(name = "ID", nullable = false)
    @SequenceGenerator(name = "PC_DELEGACIONES_X_DOC_GENERAL", sequenceName = "PC_DELEGACIONES_X_DOC_GENERAL", schema = "GESTION_CENTROS", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PC_DELEGACIONES_X_DOC_GENERAL")
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TIPO_DOCUMENTO_ID", referencedColumnName = "ID")
    public DocumentTypes getDocumentTypes() { return documentTypes; }
    public void setDocumentTypes(DocumentTypes documentTypes) { this.documentTypes = documentTypes; }

    @Basic
    @Column(name = "NOMBRE")
    public String getDocumentName() { return documentName; }
    public void setDocumentName(String documentName) { this.documentName = documentName; }

    @Basic
    @Column(name = "IMPORTE")
    public String getDocumentimport() { return documentimport; }
    public void setDocumentimport(String documentimport) { this.documentimport = documentimport; }

    @Basic
    @Column(name = "FECHA_INICIO")
    public Date getDocumentStartDate() { return documentStartDate; }
    public void setDocumentStartDate(Date documentStartDate) { this.documentStartDate = documentStartDate; }

    @Basic
    @Column(name = "FECHA_IN")
    public Date getDocumentEndDate() { return documentEndDate; }
    public void setDocumentEndDate(Date documentEndDate) { this.documentEndDate = documentEndDate; }

    @Basic
    @Column(name = "OBSERVACONES")
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
    @Column(name = "TELEFONE")
    public String getTelepnone() { return telepnone; }
    public void setTelepnone(String telepnone) { this.telepnone = telepnone; }

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
    public Certificate getCertificate() { return certificate; }
    public void setCertificate(Certificate certificate) { this.certificate = certificate; }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IMPUESTO_ID", referencedColumnName = "ID")
    public Taxes getTaxes() { return taxes; }
    public void setTaxes(Taxes taxes) { this.taxes = taxes; }


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

}
