package com.preving.intranet.gestioncentrosapi.model.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(schema = "GESTION_CENTROS",name = "PC_DELEGACIONES_X_SALAS_X_TIPOS")
public class RoomByTypes {

    private int id;
    private Room room = new Room();
    private RoomTypes type = new RoomTypes();


    public RoomByTypes() {
    }

    public RoomByTypes(int id, Room room, RoomTypes type) {
        this.id = id;
        this.room = room;
        this.type = type;
    }

    @Id
    @Column(name = "ID", nullable = false)
    @SequenceGenerator(name = "PC_DELEGACIONES_X_SALAS_X_TIPOS_SEQ", sequenceName = "PC_DELEGACIONES_X_SALAS_X_TIPOS_SEQ", schema = "GESTION_CENTROS", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PC_DELEGACIONES_X_SALAS_X_TIPOS_SEQ")
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @JsonBackReference
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "DELEGACION_X_SALA_ID", referencedColumnName = "ID")
    public Room getRoom() {
        return room;
    }
    public void setRoom(Room room) {
        this.room = room;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TIPO_ID", referencedColumnName = "ID")
    public RoomTypes getType() {
        return type;
    }
    public void setType(RoomTypes type) {
        this.type = type;
    }

}
