/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexion;

/**
 *
 * @author Suanny
 */
public class calificacion {
    String nombreUsuario;
    Integer calificacion;
    String opinion;
    String fecha;;
    String promedio;
    
    int promedioInt;
    int numOpiniones;
    float porc1;
    float porc2;
    float porc3;
    float porc4;
    float porc5;

    public int getPromedioInt() {
        return promedioInt;
    }

    public void setPromedioInt(int promedioInt) {
        this.promedioInt = promedioInt;
    }

    public String getPromedio() {
        return promedio;
    }

    public void setPromedio(String promedio) {
        this.promedio = promedio;
    }

    public int getNumOpiniones() {
        return numOpiniones;
    }

    public void setNumOpiniones(int numOpiniones) {
        this.numOpiniones = numOpiniones;
    }

    public float getPorc1() {
        return porc1;
    }

    public void setPorc1(float porc1) {
        this.porc1 = porc1;
    }

    public float getPorc2() {
        return porc2;
    }

    public void setPorc2(float porc2) {
        this.porc2 = porc2;
    }

    public float getPorc3() {
        return porc3;
    }

    public void setPorc3(float porc3) {
        this.porc3 = porc3;
    }

    public float getPorc4() {
        return porc4;
    }

    public void setPorc4(float porc4) {
        this.porc4 = porc4;
    }

    public float getPorc5() {
        return porc5;
    }

    public void setPorc5(float porc5) {
        this.porc5 = porc5;
    }
    
    
    
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    void setNumOpiniones(float suma) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
