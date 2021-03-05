/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexion;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;

/**
 *
 * @author Suanny
 */
@Named(value = "beanAnalisisCalificaciones")
@RequestScoped
public class BeanAnalisisCalificaciones {
 
    
    private HorizontalBarChartModel barModel;
    calificacion calificacionAnalisis=new calificacion();
    
    /**
     * Creates a new instance of BeanAnalisisCalificaciones
     */
    
    @PostConstruct
    public void init() {
        Conexion con = new Conexion();
        calificacionAnalisis=con.analisisCalificacion(1);
        graficar(calificacionAnalisis);
        
    }

    public HorizontalBarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(HorizontalBarChartModel barModel) {
        this.barModel = barModel;
    }

    
    public BeanAnalisisCalificaciones() {
    }

    public calificacion getCalificacionAnalisis() {
        return calificacionAnalisis;
    }

    public void setCalificacionAnalisis(calificacion calificacionAnalisis) {
        this.calificacionAnalisis = calificacionAnalisis;
    }

    private void graficar(calificacion cal) {
    barModel = new HorizontalBarChartModel();
 
        ChartSeries califcs = new ChartSeries();
        califcs.setLabel("Estrellas");
        califcs.set("1", cal.getPorc1());
        califcs.set("2", cal.getPorc2());
        califcs.set("3", cal.getPorc3());
        califcs.set("4", cal.getPorc4());
        califcs.set("5", cal.getPorc5());
        
        
        barModel.addSeries(califcs);
        barModel.setSeriesColors("ff9933, 77933c");
        barModel.setAnimate(true);
        barModel.setBarPadding(-20);
        barModel.setStacked(true);
 
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setMin(0);
        xAxis.setMax(100);
    }


    
    
}
