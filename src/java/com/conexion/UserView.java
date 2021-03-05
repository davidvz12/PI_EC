/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexion;

import java.util.List;
import javafx.scene.chart.PieChart;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author Suanny
 */
@Named
@RequestScoped
public class UserView {

    public HorizontalBarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(HorizontalBarChartModel barModel) {
        this.barModel = barModel;
    }

    public Conexion getCon() {
        return con;
    }

    public void setCon(Conexion con) {
        this.con = con;
    }
    
    private HorizontalBarChartModel barModel;
   
     
    private String firstname;
    private String lastname;
    Conexion con=new Conexion();

 
    public void listar()
    {
        List<calificacion> list;
        list=con.calificacionesPorProducto(1);
        
        
        calificacion calificacionAnalisis=new calificacion();
        calificacionAnalisis=con.analisisCalificacion(1);
        graficar(calificacionAnalisis);
    }
    
    public String getFirstname() {
        return firstname;
    }
 
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
 
    public String getLastname() {
        return lastname;
    }
 
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
 
    public void save() {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Welcome " + firstname + " " + lastname));
    }

    private void graficar(calificacion cal) {
 /*  barModel = new HorizontalBarChartModel();
    
    for(calificacion cal:list)
    {
        ChartSeries serie=new BarChartSeries();
        serie.setLabel(cal.getOpinion());
        serie.set(cal.getOpinion(), cal.getCalificacion());
        barModel.addSeries(serie);
    }
    barModel.setTitle("Calificaciones");
    barModel.setLegendPosition("ne");
    barModel.setAnimate(true);
    */
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
  
/*barModel = new HorizontalBarChartModel();
 ChartSeries boys = new ChartSeries();
        boys.setLabel("Boys");
        boys.set("2004", 50);
        boys.set("2005", 96);
        boys.set("2006", 44);
        boys.set("2007", 55);
        boys.set("2008", 25);
 
        ChartSeries girls = new ChartSeries();
        girls.setLabel("Girls");
        girls.set("2004", 52);
        girls.set("2005", 60);
        girls.set("2006", 82);
        girls.set("2007", 35);
        girls.set("2008", 120);
 
        barModel.addSeries(boys);
        barModel.addSeries(girls);
 
        barModel.setTitle("Horizontal and Stacked");
        barModel.setLegendPosition("e");
        barModel.setStacked(true);
 
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Births");
        xAxis.setMin(0);
        xAxis.setMax(200);
 
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Gender");
  */ 
    }
}
