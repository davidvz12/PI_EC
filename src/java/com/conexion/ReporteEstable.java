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
@Named(value = "reporteEstable")
@ViewScoped
public class ReporteEstable implements Serializable {

    private List<ControladorGraficarEstablecimiento> listreporte;
    
    Conexion con;

    public List<ControladorGraficarEstablecimiento> getListreporte() {
        return listreporte;
    }

    public void setListreporte(List<ControladorGraficarEstablecimiento> listreporte) {
        this.listreporte = listreporte;
    }

    private PieChartModel pieModel;

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }

    @PostConstruct
    public void init()
    {
        con=new Conexion();
        pieModel=new PieChartModel();        
        creargraficapastel();
    }
    
    public void creargraficapastel() {

        con = new Conexion();
        pieModel = new PieChartModel();
        listreporte = new ArrayList<>();
        listreporte = con.graficarEstablecimiento(1);
        if (listreporte.size() == 0) {
            pieModel.set("vacio", 1);
            pieModel.setTitle("NO HAY CALIFICACIONES");
            pieModel.setLegendPosition("NO HAY CALIFICACIONES");
            pieModel.setFill(false);
            pieModel.setShowDataLabels(true);
            pieModel.setDiameter(150);
            pieModel.setShadow(false);
        } else {

            for (int i = 0; i < listreporte.size(); i++) {
                pieModel.set(listreporte.get(i).getNombre(), Integer.parseInt(listreporte.get(i).getPuntaje()));
            }
            pieModel.setTitle("TOP 5 MEJORES PRODUCTOS");
            pieModel.setLegendPosition("TOP 5 MEJORES PRODUCTOS");
            pieModel.setFill(false);
            pieModel.setShowDataLabels(true);
            pieModel.setDiameter(150);
            pieModel.setShadow(false);
        }

    }
    
    
    public ReporteEstable() {
    }

}
