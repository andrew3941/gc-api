package com.preving.intranet.gestioncentrosapi.model.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class EmailRaw implements Serializable {
    private String idioma = "es";    // Idioma. Por defecto castellano
    private int entidad = 1;     // Identificador de la Entidad. Por defecto Preving
    private int tipo = 1101;
    private int evento;                 // Identificador del Tipo de Evento
    private int targetTipo;
    private int targetId;
    private boolean interna = true;
    private int usuarioId;

    private String[] to;            // Obligatorio según la plantilla
    private String from;
    private String[] cc;
    private String[] bcc;

    private Map<String, Object> datos = new HashMap<>();

    public EmailRaw() {
    }

    public EmailRaw(String[] to, String from, String[] cc, String[] bcc,Map<String, Object> datos) {
        this.to = to;
        this.from = from;
        this.cc = cc;
        this.bcc = bcc;
        this.datos  = datos;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public int getEntidad() {
        return entidad;
    }

    public void setEntidad(int entidad) {
        this.entidad = entidad;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getEvento() {
        return evento;
    }

    public void setEvento(int evento) {
        this.evento = evento;
    }

    public int getTargetTipo() {
        return targetTipo;
    }

    public void setTargetTipo(int targetTipo) {
        this.targetTipo = targetTipo;
    }

    public int getTargetId() {
        return targetId;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }

    public boolean isInterna() {
        return interna;
    }

    public void setInterna(boolean interna) {
        this.interna = interna;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String[] getTo() {
        return to;
    }

    public void setTo(String[] to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String[] getCc() {
        return cc;
    }

    public void setCc(String[] cc) {
        this.cc = cc;
    }

    public String[] getBcc() {
        return bcc;
    }

    public void setBcc(String[] bcc) {
        this.bcc = bcc;
    }
}
