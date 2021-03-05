/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexion;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
/**
 *
 * @author JoseRene
 */
@Named(value = "ingresarLogin")
@SessionScoped
public class IngresarLogin implements Serializable {

    
    private String usu,contra;
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getUsu() {
        return usu;
    }

    public void setUsu(String usu) {
        this.usu = usu;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }
    
    
    private static int idusuarioFinal; 

    public static int getIdusuarioFinal() {
        return idusuarioFinal;
    }

    public static void setIdusuarioFinal(int idusuarioFinal) {
        IngresarLogin.idusuarioFinal = idusuarioFinal;
    }
    
    public String conectar()
    {
    Conexion con= new Conexion();
        int idusu;
        
        //idusu=con.id(usu,hash.sha1(contra));
        idusu=con.id(usu,contra);   
        idusuarioFinal=idusu;
        if(idusu!=0){
           this.id=idusu;
           return "inicioSt.xhtml";
        }
        else{
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario i/o contraseña invalidos ", null);
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        }
         return "";
    }
    public String pb(){
         return "faces/RegistrarCliente.xhtml";
    }
    
   
    
    
    public IngresarLogin() {
    }
    
}
