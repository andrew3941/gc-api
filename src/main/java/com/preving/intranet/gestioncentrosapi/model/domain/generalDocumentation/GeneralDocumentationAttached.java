package com.preving.intranet.gestioncentrosapi.model.domain.generalDocumentation;

import javax.persistence.*;

@Entity
@Table(schema = "GESTION_CENTROS", name="DOC_GENERAL_X_ADJUNTOS")
public class GeneralDocumentationAttached {
    private int id;
    private int generalDocId;
    private String documentUrl;
    private String docNumber;
    private String documentContentType;

    public GeneralDocumentationAttached() {
    }

    public GeneralDocumentationAttached(int id, int generalDocId, String documentUrl, String docNumber, String documentContentType) {
        this.id = id;
        this.generalDocId = generalDocId;
        this.documentUrl = documentUrl;
        this.docNumber = docNumber;
        this.documentContentType = documentContentType;
    }
    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @Basic
    @Column(name = "DOC_GENERAL_ID")
    public int getGeneralDocId() { return generalDocId; }
    public void setGeneralDocId(int generalDocId) { this.generalDocId = generalDocId; }

    @Basic
    @Column(name = "DOC_URL")
    public String getDocumentUrl() { return documentUrl; }
    public void setDocumentUrl(String documentUrl) { this.documentUrl = documentUrl; }

    @Basic
    @Column(name = "DOC_NOMBRE")
    public String getDocNumber() { return docNumber; }
    public void setDocNumber(String docNumber) { this.docNumber = docNumber; }

    @Basic
    @Column(name = "DOC_CONTENT_TYPE")
    public String getDocumentContentType() { return documentContentType; }
    public void setDocumentContentType(String documentContentType) { this.documentContentType = documentContentType; }
}
