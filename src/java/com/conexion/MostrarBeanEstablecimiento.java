/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexion;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Cristhian
 */
@Named(value = "mostrarBeanEstablecimiento")
@ViewScoped
public class MostrarBeanEstablecimiento implements Serializable{

    
    
    
    
    
    @PostConstruct
    public void init()
    {
        Conexion con=new Conexion();
        Beansugerencia sug=new Beansugerencia();
        int n=Integer.parseInt(sug.getIdestablecimientofinal());        
        con.ProductoPorEst(n);
        
    }
    
    
    
    
    public MostrarBeanEstablecimiento() {
    }
    
}
