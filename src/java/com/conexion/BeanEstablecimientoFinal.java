/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexion;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author JoseRene
 */
@Named(value = "beanEstablecimientoFinal")
@ViewScoped
public class BeanEstablecimientoFinal implements Serializable{

    private List<ControladorEstablecimientoFinal> esta;

    public List<ControladorEstablecimientoFinal> getEsta() {
        return esta;
    }

    public void setEsta(List<ControladorEstablecimientoFinal> esta) {
        this.esta = esta;
    }

    
    private String foto,nombre,categoria,descripcion,direccion;

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    
    
    @PostConstruct
    public void init()
    {
        Conexion con=new Conexion();
        Beansugerencia sg=new Beansugerencia();        
        esta=con.presentarestablecimiento(Integer.parseInt(sg.getIdestablecimientofinal()));
        
        foto=esta.get(0).getFotoestablecimiento();
        nombre=esta.get(0).getNombreestablecimiento();
        categoria=esta.get(0).getCategoriaestablecimiento();
        descripcion=esta.get(0).getDescripcion();
        direccion=esta.get(0).getDireccion();
    }
    
    
    
    public BeanEstablecimientoFinal() {
    }
    
}
