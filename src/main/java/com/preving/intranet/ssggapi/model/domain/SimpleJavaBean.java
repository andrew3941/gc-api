package com.preving.intranet.ssggapi.model.domain;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Administrador
 * Date: 30-mar-2009
 * Time: 10:50:47
 * To change this template use File | Settings | File Templates.
 */
public class SimpleJavaBean implements Serializable, Cloneable {

    protected int id;
    protected String codigo;
    protected String nombre;
    protected boolean activo;

    public SimpleJavaBean() {
    }

    public SimpleJavaBean(int id) {
        this.id = id;
    }

    public SimpleJavaBean(String codigo) {
        this.codigo = codigo;
    }

    public SimpleJavaBean(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public SimpleJavaBean(int id, String nombre, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.activo = activo;
    }

    public SimpleJavaBean(int id, String codigo, String nombre) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public SimpleJavaBean(int id, String codigo, String nombre, boolean activo) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.activo = activo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Object clone(){
        SimpleJavaBean obj;
        try{
            obj=(SimpleJavaBean)super.clone();
        }catch(CloneNotSupportedException ex){
            obj = new SimpleJavaBean();
        }
        return obj;
    }
}
