/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author JoseRene
 */
@Named(value = "catalogo")
@ViewScoped
public class Catalogo implements Serializable {

    List<CatalagoImagenes> imagenes = new ArrayList<>();
    private String imagen, descripcion, nombre, tipo, direccion;
    private int calificacion;

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }
    
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @PostConstruct
    public void init() {
        Conexion con = new Conexion();
        imagen = con.obtenerFotoEstablecimiento(1);
        con = new Conexion();
        imagenes = con.fotosCatalogo(1);
        con = new Conexion();
        descripcion = con.descripcionEstablecimiento(1);
        con = new Conexion();
        nombre = con.nombreEstablecimiento(1);
        con = new Conexion();
        tipo = con.tipoEstablecimiento(1);
        con = new Conexion();
        direccion = con.direccionEstablecimiento(1);
        con=new Conexion();
        calificacion=con.calificacionEstablecimiento(1);

    }

    public List<CatalagoImagenes> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<CatalagoImagenes> imagenes) {
        this.imagenes = imagenes;
    }

    public Catalogo() {
    }
}
