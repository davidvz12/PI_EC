/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexion;

/**
 *
 * @author USUARIO
 */
public class Usuario {
    double plato;
    double calificacion;
    double categoria;
    double establecimiento;
    Usuario(double plato, double categoria,double establecimiento ,double calificacion) {
        this.plato = plato;
        this.calificacion = calificacion;
        this.categoria=categoria;
        this.establecimiento=establecimiento;
    }

    public double getCategoria() {
        return categoria;
    }

    public void setCategoria(double categoria) {
        this.categoria = categoria;
    }

    public double getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(double establecimiento) {
        this.establecimiento = establecimiento;
    }

    public double getPlato() {
        return plato;
    }
    
    public void setPlato(double plato) {
        this.plato = plato;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }
    
}
