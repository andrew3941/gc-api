package com.preving.intranet.gestioncentrosapi.model.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.Pattern;

@Entity
@Table(schema = "GESTION_CENTROS", name = "PLANOS_X_ADJUNTOS")
public class DrawingsByAttachment {

    private int id;

    private Drawing drawing;

    private String attachedUrl;

    private String attachedName;

    private String attachedContentType;

    @Id
    @Column(name = "ID", nullable = false)
    @SequenceGenerator(name = "PLANOS_X_ADJUNTOS_SEQ", sequenceName = "PLANOS_X_ADJUNTOS_SEQ", schema = "GESTION_CENTROS", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PLANOS_X_ADJUNTOS_SEQ")
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="PLANO_ID", referencedColumnName="id")
    public Drawing getDrawing() { return drawing; }
    public void setDrawing(Drawing drawing) { this.drawing = drawing; }


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
    @Pattern(regexp="^(application/msword|application/vnd.openxmlformats-officedocument.wordprocessingml.document|application/vnd.oasis.opendocument.text|application/vnd.ms-excel|application/vnd.openxmlformats-officedocument.spreadsheetml.sheet|application/vnd.oasis.opendocument.spreadsheet|image/jpeg|image/png|application/x-zip-compressed|application/pdf|message/rfc822)$")
    @Column(name = "DOC_CONTENT_TYPE")
    public String getAttachedContentType() { return attachedContentType; }
    public void setAttachedContentType(String attachedContentType) { this.attachedContentType = attachedContentType; }
}
