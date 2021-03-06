package com.preving.intranet.gestioncentrosapi.model.domain.generalDocumentation;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(schema = "GESTION_CENTROS" , name = "DOC_GENERAL_x_ADJUNTOS")
public class GeneralDocByAttachment {

    private int id;

    private GeneralDocumentation generalDoc;

    private String attachedUrl;

    private String attachedName;

    private String attachedContentType;


    @Id
    @Column(name = "ID", nullable = false)
    @SequenceGenerator(name = "DOC_GENERAL_x_ADJUNTOS_SEQ", sequenceName = "DOC_GENERAL_x_ADJUNTOS_SEQ", schema = "GESTION_CENTROS", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DOC_GENERAL_x_ADJUNTOS_SEQ")
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="DOC_GENERAL_ID", referencedColumnName="id")
    public GeneralDocumentation getGeneralDoc() {
        return generalDoc;
    }
    public void setGeneralDoc(GeneralDocumentation generalDoc) {
        this.generalDoc = generalDoc;
    }

    @Basic
    @Column(name = "DOC_URL")
    public String getAttachedUrl() {
        return attachedUrl;
    }
    public void setAttachedUrl(String attachedUrl) {
        this.attachedUrl = attachedUrl;
    }

    @Basic
    @Column(name = "DOC_NOMBRE")
    public String getAttachedName() {
        return attachedName;
    }
    public void setAttachedName(String attachedName) {
        this.attachedName = attachedName;
    }

    @Basic
    @Column(name = "DOC_CONTENT_TYPE")
    public String getAttachedContentType() {
        return attachedContentType;
    }
    public void setAttachedContentType(String attachedContentType) {
        this.attachedContentType = attachedContentType;
    }
}
