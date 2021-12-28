package com.preving.intranet.certificate;


import javax.persistence.*;

@Entity
@Table(schema = "GESTION_CENTROS", name="TM_CERTIFICADOS")
public class Certificate {
    private int id;
    private String denomination;
    private String observations;
    private boolean active;

    public Certificate() {
    }

    public Certificate(int id, String denomination, String observations, boolean active) {
        this.id = id;
        this.denomination = denomination;
        this.observations = observations;
        this.active = active;
    }

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @Basic
    @Column(name = "DENOMINACION")
    public String getDenomination() { return denomination; }
    public void setDenomination(String denomination) { this.denomination = denomination; }

    @Basic
    @Column(name = "OBSERVACIONES")
    public String getObservations() { return observations; }
    public void setObservations(String observations) { this.observations = observations; }

    @Basic
    @Column(name = "ACTIVO")
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
