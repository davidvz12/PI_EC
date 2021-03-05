/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.conexion.Conexion;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author JoseRene
 */
@Named(value = "registrarUsuario")
@RequestScoped
public class RegistrarUsuario implements Serializable{

    
    private String nombre_usuario,clave,claveDos,tipo;

    public String getClaveDos() {
        return claveDos;
    }

    public void setClaveDos(String claveDos) {
        this.claveDos = claveDos;
    }
    
    private static String nombre_usuario1,clave1,tipo1;

    public static String getNombre_usuario1() {
        return nombre_usuario1;
    }

    public static void setNombre_usuario1(String nombre_usuario1) {
        RegistrarUsuario.nombre_usuario1 = nombre_usuario1;
    }

    public static String getClave1() {
        return clave1;
    }

    public static void setClave1(String clave1) {
        RegistrarUsuario.clave1 = clave1;
    }

    public static String getTipo1() {
        return tipo1;
    }

    public static void setTipo1(String tipo1) {
        RegistrarUsuario.tipo1 = tipo1;
    }
    
    private String nombre,apellido,telefono,correo,ciudad,sector,calle_principal,referencia;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getCalle_principal() {
        return calle_principal;
    }

    public void setCalle_principal(String calle_principal) {
        this.calle_principal = calle_principal;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
    

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
    public void guardar()
    {
       Conexion con=new Conexion();
       int n=con.maximopersona();
       con=new Conexion();
       con.registrarPersona(n+1, nombre, apellido, telefono, correo, ciudad, sector, clave, referencia);
       nombre="";
       apellido="";
       telefono="";
       correo="";
       ciudad="";
       sector="";
       clave="";
       referencia="";
               
       con=new Conexion();
       int max=con.maximoUsuario();
       con=new Conexion();
       con.registrarUsuario(max+1,n+1, nombre_usuario1, clave1);
       
    }
    
    public String conectar(){
        nombre_usuario1=nombre_usuario;
        clave1=clave;
        tipo1=tipo;
        int cambiar=0;
        
         Conexion con=new Conexion();        
        int exite=con.existeusuario(nombre_usuario1);
        
        if(exite>=1)
        {
            String n="No se puede registrar al usuario";
            cambiar=1;
        }
        if(!clave.equals(claveDos))
        {
           cambiar=1;
        }

        if(cambiar==1)
        {
             return "#";
        }        
         return "faces/RegistrarCliente.xhtml";       
    }
    
    
    
    public RegistrarUsuario() {
    }
    
}
