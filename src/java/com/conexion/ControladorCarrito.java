
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexion;

/**
 *
 * @author Cristhian
 */
public class ControladorCarrito {
     private String nombreP, imagenP, precioP,idP, restauranteP, cantidad, subtotal;

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getNombreP() {
        return nombreP;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public void setNombreP(String nombreP) {
        this.nombreP = nombreP;
    }

    public String getImagenP() {
        return imagenP;
    }

    public void setImagenP(String imagenP) {
        this.imagenP = imagenP;
    }

    public String getPrecioP() {
        return precioP;
    }

    public void setPrecioP(String precioP) {
        this.precioP = precioP;
    }

    public String getIdP() {
        return idP;
    }

    public void setIdP(String idP) {
        this.idP = idP;
    }

    public String getRestauranteP() {
        return restauranteP;
    }

    public void setRestauranteP(String restauranteP) {
        this.restauranteP = restauranteP;
    }
}
