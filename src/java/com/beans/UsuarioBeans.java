/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.conexion.Conexion;
import java.io.IOException;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author USUARIO
 */
@Named(value = "usuarioBeans")
@RequestScoped
public class UsuarioBeans implements Serializable{

    private String usuario;
    private String contrasenia;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    private String mensaje;
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    public UsuarioBeans() {
    }
    
    public String autenticar(){
        Conexion con= new Conexion();
        int id;
        id=con.id(getUsuario(),getContrasenia());
        if(id!=0){
            
           return "inicio.xhtml";
        }
        else{
           setMensaje("Usuario i/o contraseña invalidos");
        }
        return "";
    }
    
}
