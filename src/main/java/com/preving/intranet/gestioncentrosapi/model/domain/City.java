package com.preving.intranet.gestioncentrosapi.model.domain;


import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;


public class City implements Serializable {

    private int id;
    private String name;
    private Region prvCod = new Region();




}
