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
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author JoseRene
 */
@Named(value = "beanReporte")
@ViewScoped
public class BeanReporte implements Serializable {

    private List<Controlador_reporte> reporte;
    private List<Controlador_reporte> listreporte;

    private PieChartModel pieModel;

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }

    private List<ControladorGraficar> gf;

    public List<ControladorGraficar> getGf() {
        return gf;
    }

    public void setGf(List<ControladorGraficar> gf) {
        this.gf = gf;
    }

    private String estado;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    private Controlador_reporte control;

    public Controlador_reporte getControl() {
        return control;
    }

    public void setControl(Controlador_reporte control) {
        this.control = control;
        global = Integer.parseInt(control.getId_establecimiento());
        creargraficapastel();
    }

    public List<Controlador_reporte> getListreporte() {
        return listreporte;
    }

    public void setListreporte(List<Controlador_reporte> listreporte) {
        this.listreporte = listreporte;
    }
    Conexion con = new Conexion();

    private int global;

    public int getGlobal() {
        return global;
    }

    public void setGlobal(int global) {
        this.global = global;
    }

    @PostConstruct
    public void init() {
        Conexion con = new Conexion();
        reporte = new ArrayList<>();
        gf = new ArrayList<>();
        con = new Conexion();
        reporte = con.reporte();
        datos();

    }

    public List<Controlador_reporte> getReporte() {
        return reporte;
    }

    public void setReporte(List<Controlador_reporte> reporte) {
        this.reporte = reporte;
    }

    public void creargraficapastel() {

        con = new Conexion();
        pieModel = new PieChartModel();
        gf = new ArrayList<>();
        gf = con.graficar(global);
        if (gf.size() == 0) {
            pieModel.set("vacio", 1);
            pieModel.setTitle("NO HAY CALIFICACIONES");
            pieModel.setLegendPosition("NO HAY CALIFICACIONES");
            pieModel.setFill(false);
            pieModel.setShowDataLabels(true);
            pieModel.setDiameter(150);
            pieModel.setShadow(false);
        } else {

            for (int i = 0; i < gf.size(); i++) {
                pieModel.set(gf.get(i).getNombre(), Integer.parseInt(gf.get(i).getPuntaje()));
            }
            pieModel.setTitle("TOP 5 MEJORES PRODUCTOS");
            pieModel.setLegendPosition("TOP 5 MEJORES PRODUCTOS");
            pieModel.setFill(false);
            pieModel.setShowDataLabels(true);
            pieModel.setDiameter(150);
            pieModel.setShadow(false);
            estado = "";
        }

    }
    
    
    public void datos()
    {
        con = new Conexion();
        pieModel = new PieChartModel();
        gf = new ArrayList<>();
        gf = con.graficar(1);
        for (int i = 0; i < gf.size(); i++) {
                pieModel.set(gf.get(i).getNombre(), Integer.parseInt(gf.get(i).getPuntaje()));
            }
            pieModel.setTitle("TOP 5 MEJORES PRODUCTOS");
            pieModel.setLegendPosition("TOP 5 MEJORES PRODUCTOS");
            pieModel.setFill(false);
            pieModel.setShowDataLabels(true);
            pieModel.setDiameter(150);
            pieModel.setShadow(false);        
    }

    public BeanReporte() {
    }

}
