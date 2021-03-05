/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Cristhian
 */
@Named(value = "vistaProducto")
@ViewScoped
public class VistaProducto implements Serializable{

    /**
     * Creates a new instance of VistaProducto
     */
    private List<ControladorProducto> listap ;
    private List<ControladorCarrito> listac =new ArrayList<>();
    private ControladorProducto controlador;
    private ControladorCarrito contCarrito;
    private static List<ControladorCarrito> listacarrito =new ArrayList<>();
    public List<ControladorCarrito> getListac() {
        return listac;
    }

    public static List<ControladorCarrito> getListacarrito() {
        return listacarrito;
    }

    public static void setListacarrito(List<ControladorCarrito> listacarrito) {
        VistaProducto.listacarrito = listacarrito;
    }

    public void setListac(List<ControladorCarrito> listac) {
        this.listac = listac;
    }
       
    public ControladorCarrito getContCarrito() {
        return contCarrito;
    }

    public void setContCarrito(ControladorCarrito contCarrito) {
        this.contCarrito = contCarrito;
    }
    public List<ControladorProducto> getListap() {
        return listap;
    }

    public void setListap(List<ControladorProducto> listap) {
        this.listap = listap;
    }

    public ControladorProducto getControlador() {
        return controlador;
    }

    public void setControlador(ControladorProducto controlador) {
        this.controlador = controlador;
    }
    @PostConstruct
    public void init(){
        listap=new ArrayList<>();
        Conexion con = new Conexion();
        for (int i = 0; i < listap.size(); i++) {
            listap.remove(i);
        }
        listap=con.productosPerfil(1, 2);
        //listap=con.productosPerfil(10, 1);
    }
    
    public void agregarCarrito(){
        contCarrito= new ControladorCarrito();
        contCarrito.setIdP(listap.get(0).getIdP());
        contCarrito.setImagenP(listap.get(0).getImagenP());
        contCarrito.setNombreP(listap.get(0).getNombreP());
        contCarrito.setPrecioP(listap.get(0).getPrecioP());
        contCarrito.setRestauranteP(listap.get(0).getRestauranteP());
        contCarrito.setCantidad("1");
        contCarrito.setSubtotal(listap.get(0).getPrecioP());
        listac.add(contCarrito);
        listacarrito= listac;
        Conexion con = new Conexion();
        listap=con.productosPerfil(10, 1);
    }
    public VistaProducto() {
    }
    
}
