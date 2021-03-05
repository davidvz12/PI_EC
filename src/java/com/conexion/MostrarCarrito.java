
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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author Cristhian
 */
@Named(value = "mostrarCarrito")
@ViewScoped
public class MostrarCarrito implements Serializable{

    /**
     * Creates a new instance of MostrarCarrito
     */
    private List<ControladorCarrito> listacarrito;
    private Producto vp;
    private ControladorCarrito conCartCl;
    private ControladorCarrito conCart;
    private ControladorCarrito conCartless;
    private String subtotal, total, center_mapa="-1.028453,-79.467503", cantidaditems;

    public String getCantidaditems() {
        return cantidaditems;
    }

    public void setCantidaditems(String cantidaditems) {
        this.cantidaditems = cantidaditems;
    }
    
    
     private MapModel emptyModel;
      
    private String title;
    
          
    private double lat;
      
    private double lng;

    public MapModel getEmptyModel() {
        return emptyModel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

   public void addMarker() {
        Marker marker = new Marker(new LatLng(lat, lng));
        emptyModel.addOverlay(marker);
        
         // System.out.println("latitud: "+ lat +" ,Lng: "+lng);
         // FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                   //     String.valueOf(getLat()),
                                    //     String.valueOf(getLng()));
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Added", "Lat:" + lat + ", Lng:" + lng));
    }
    
    
    
    
    public String getCenter_mapa() {
        return center_mapa;
    }

    public void setCenter_mapa(String center_mapa) {
        this.center_mapa = center_mapa;
    }
    private float aux=0,aux2=0, precio=0; 
    
    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
    
    public ControladorCarrito getConCartCl() {
        return conCartCl;
    }

    public void setConCartCl(ControladorCarrito conCartCl) {
        this.conCartCl = conCartCl;
        listacarrito.remove(conCartCl);
          listacarrito= new ArrayList<>();
        listacarrito=vp.getListacarrito();
        float aux3=0;
        for (int i = 0; i < listacarrito.size(); i++) {
            aux3= (Float.parseFloat(listacarrito.get(i).getPrecioP())*Float.parseFloat(listacarrito.get(i).getCantidad()))+aux3; 
        }
        setTotal(String.valueOf(aux3));
        setCantidaditems(String.valueOf(listacarrito.size()));
    }
   
    
    public ControladorCarrito getConCartless() {
        return conCartless;
    }

    public void setConCartless(ControladorCarrito conCartless) {
        this.conCartless = conCartless;
        //conCartless.setCantidad(cantidad);
        if(aux>1){
            aux= Float.parseFloat(conCartless.getCantidad())-1;
            conCartless.setCantidad(String.valueOf(Math.round(aux)));
        }
        aux2=Float.parseFloat(conCartless.getCantidad())*Float.parseFloat(conCartless.getPrecioP());
         conCart.setSubtotal(String.valueOf(aux2));

         listacarrito= new ArrayList<>();
        listacarrito=vp.getListacarrito();
        float aux3=0;
        for (int i = 0; i < listacarrito.size(); i++) {
            aux3= (Float.parseFloat(listacarrito.get(i).getPrecioP())*Float.parseFloat(listacarrito.get(i).getCantidad()))+aux3; 
        }
        setTotal(String.valueOf(aux3));
         
         
    }
    
    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }
    
    public ControladorCarrito getConCart() {
        return conCart;
    }

    public void setConCart(ControladorCarrito conCart) {
        this.conCart = conCart;
        //listacarrito.remove(conCart);
       // aux=aux+1;
       // precio= Integer.parseInt( conCart.getPrecioP());
       // setCantidad(String.valueOf(Integer.parseInt(String.valueOf(aux))));
       // setSubtotal(String.valueOf(aux*precio));
       aux= Float.parseFloat(conCart.getCantidad())+1;
        conCart.setCantidad(String.valueOf(Math.round(aux)));
        
        aux2=Float.parseFloat(conCart.getCantidad())*Float.parseFloat(conCart.getPrecioP());
         conCart.setSubtotal(String.valueOf(aux2));
        
        
         listacarrito= new ArrayList<>();
        listacarrito=vp.getListacarrito();
        float aux3=0;
        for (int i = 0; i < listacarrito.size(); i++) {
            aux3= (Float.parseFloat(listacarrito.get(i).getPrecioP())*Float.parseFloat(listacarrito.get(i).getCantidad()))+aux3; 
        }
        setTotal(String.valueOf(aux3));
         
    }
    public List<ControladorCarrito> getListacarrito() {
        return listacarrito;
    }

    public void setListacarrito(List<ControladorCarrito> listacarrito) {
        this.listacarrito = listacarrito;
    }

    public MostrarCarrito() {
    }
    
    @PostConstruct
    public void init(){
        listacarrito= new ArrayList<>();
        listacarrito=vp.getListacarrito();
        float aux3=0;
        for (int i = 0; i < listacarrito.size(); i++) {
            aux3= Float.parseFloat(listacarrito.get(i).getPrecioP())+aux3; 
        }
        setTotal(String.valueOf(aux3));
         setCantidaditems(String.valueOf(listacarrito.size()));
    }
    
    public void registrarPago()
    {
      Conexion con = new Conexion();
       listacarrito= new ArrayList<>();
        listacarrito=vp.getListacarrito();
        setCantidaditems(String.valueOf(listacarrito.size()));
      con.registrarPago(1, 1, title, total, String.valueOf(lng), String.valueOf(lat));
        for (int i = 0; i < listacarrito.size(); i++) {
            con.registrarDetallePago(listacarrito.get(i).getIdP(), listacarrito.get(i).getCantidad(), listacarrito.get(i).getPrecioP(), "");
        }
    }
}
