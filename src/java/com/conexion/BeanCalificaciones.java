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
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;

/**
 *
 * @author Suanny
 */
@Named(value = "beanCalificaciones")
@RequestScoped
public class BeanCalificaciones implements Serializable {

    private String comentario;
    private Integer ratingcalificacion;
    private Integer idUsuario;
    private Integer idProducto;

    /**
     * Creates a new instance of BeanCalificaciones
     */
    public BeanCalificaciones() {
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getRatingcalificacion() {
        return ratingcalificacion;
    }

    public void setRatingcalificacion(Integer ratingcalificacion) {
        this.ratingcalificacion = ratingcalificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Conexion getCon() {
        return con;
    }

    public void setCon(Conexion con) {
        this.con = con;
    }

    Conexion con = null;
    private HorizontalBarChartModel barModel;
    List<calificacion> listCal = new ArrayList<>();
    calificacion calificacionAnalisis = new calificacion();

    public calificacion getCalificacionAnalisis() {
        return calificacionAnalisis;
    }

    public void setCalificacionAnalisis(calificacion calificacionAnalisis) {
        this.calificacionAnalisis = calificacionAnalisis;
    }

    public HorizontalBarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(HorizontalBarChartModel barModel) {
        this.barModel = barModel;
    }

    @PostConstruct
    public void init() {
        con = new Conexion();
        listCal = con.calificacionesPorProducto(1);
        calificacionAnalisis = con.analisisCalificacion(1);
        graficar(calificacionAnalisis);
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

    public void registrarCalificacion() {
        con = new Conexion();
        con.registrarCalificacion(ratingcalificacion, comentario);
        ratingcalificacion=0;
        comentario="";
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Welcome " + ratingcalificacion + " " + comentario));
        init();

    }

    public List<calificacion> getListCal() {
        // listCal = con.calificacionesPorProducto(1);        
        return listCal;
    }

    public void setListCal(List<calificacion> listCal) {
        this.listCal = listCal;
    }

}
