/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;

/**
 *
 * @author JoseRene
 */
@Named(value = "producto")
@ViewScoped
public class Producto implements Serializable {

    private static String auxTipo;
    private static String auxPrecio;
    private String cantidaditems = "0";

    public String getCantidaditems() {
        return cantidaditems;
    }

    public void setCantidaditems(String cantidaditems) {
        this.cantidaditems = cantidaditems;
    }

    public static String getAuxPrecio() {
        return auxPrecio;
    }

    public static void setAuxPrecio(String auxPrecio) {
        Producto.auxPrecio = auxPrecio;
    }

    public static String getAuxTipo() {
        return auxTipo;
    }

    public static void setAuxTipo(String auxTipo) {
        Producto.auxTipo = auxTipo;
    }

    List<Producto_Final> imagenes = new ArrayList<>();
    List<Producto_Final> productosEst = new ArrayList<>();

    
    public List<Producto_Final> getProductosEst() {
        Conexion con = new Conexion();
        
        Beansugerencia sg=new Beansugerencia();
        
       return con.ProductoPorEst(Integer.parseInt(sg.getIdestablecimientofinal()));
    }

    public void setProductosEst(List<Producto_Final> productosEst) {
        this.productosEst = productosEst;
    }

    private String tipo_producto, precio;

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
        auxPrecio = this.precio;
        if (precio.equals("asc") && auxTipo != null) {
            Conexion con = new Conexion();
            imagenes = con.filtroTipoComidaPrecioMenorMayor(Integer.parseInt(auxTipo));
        } else if (precio.equals("desc") && auxTipo != null) {
            Conexion con = new Conexion();
            imagenes = con.filtroTipoComidaPrecioMayorMenor(Integer.parseInt(auxTipo));
        }

        if (precio.equals("asc") && auxTipo == null) {
            Conexion con = new Conexion();
            imagenes = con.flitroSoloPrecioMenorMayor();
        } else if (precio.equals("desc") && auxTipo == null) {
            Conexion con = new Conexion();
            imagenes = con.flitroSoloPrecioMayorMenor();
        }
    }

    public String getTipo_producto() {
        return tipo_producto;
    }

    public void setTipo_producto(String tipo_producto) {
        this.tipo_producto = tipo_producto;
        auxTipo = this.tipo_producto;

        if (auxPrecio == null) {
            Conexion con = new Conexion();
            imagenes = con.filtroTipoComida(Integer.parseInt(tipo_producto));
        }
        if (auxPrecio != null) {
            if (auxPrecio.equals("asc")) {
                Conexion con = new Conexion();
                imagenes = con.filtroTipoComidaPrecioMenorMayor(Integer.parseInt(auxTipo));
            } else if (auxPrecio.equals("desc")) {
                Conexion con = new Conexion();
                imagenes = con.filtroTipoComidaPrecioMayorMenor(Integer.parseInt(auxTipo));
            }
        }

    }

    private String producto, nombrestaurante, parrafo, foto, dinero;

    public String getDinero() {
        return dinero;
    }

    public void setDinero(String dinero) {
        this.dinero = dinero;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getNombrestaurante() {
        return nombrestaurante;
    }

    public void setNombrestaurante(String nombrestaurante) {
        this.nombrestaurante = nombrestaurante;
    }

    public String getParrafo() {
        return parrafo;
    }

    public void setParrafo(String parrafo) {
        this.parrafo = parrafo;
    }

    
    
    
    public String reidrigir()
    {        
        return "Producto";
    }
    
    
    private Producto_Final mostrarAux;

    public Producto_Final getMostrarAux() {
        return mostrarAux;
    }

    public void setMostrarAux(Producto_Final mostrarAux) {
        this.mostrarAux = mostrarAux;
        
        Beansugerencia sug = new Beansugerencia();

        sug.setNombrefinalin(mostrarAux.getProducto());
        sug.setTipo_produto_final(mostrarAux.getTipo());
      
    }
    
    
    
    
    private Producto_Final mostrar;

    public Producto_Final getMostrar() {
        return mostrar;
    }

    public void setMostrar(Producto_Final mostrar) {
        this.mostrar = mostrar;
        this.idproducto = mostrar.getIdproducto();
        this.dinero = mostrar.getDinero();
        this.producto = mostrar.getProducto();
        this.nombrestaurante = mostrar.getNombre();
        this.tipo_producto = mostrar.getTipo();
        this.parrafo = mostrar.getDescripcion();
        this.foto = mostrar.getFoto();
    }

    public List<Producto_Final> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<Producto_Final> imagenes) {
        this.imagenes = imagenes;
    }

    private String idproducto;

    public String getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(String idproducto) {
        this.idproducto = idproducto;
    }

    @PostConstruct
    public void init() {

        String obtener = "";
        Conexion con = new Conexion();

        Beansugerencia sug = new Beansugerencia();

        obtener = sug.getNombrefinalin();

        imagenes = con.fotosProdcuto(Integer.parseInt(sug.getIdestablecimientofinal()), Integer.parseInt(sug.getTipo_produto_final()));

        for (int i = 0; i < imagenes.size(); i++) {

            if (obtener.equals(imagenes.get(i).getProducto())) {
                idproducto = imagenes.get(i).getIdproducto();
                dinero = imagenes.get(i).getDinero();
                producto = imagenes.get(i).getProducto();
                nombrestaurante = imagenes.get(i).getNombre();
                tipo_producto = imagenes.get(i).getTipo();
                parrafo = imagenes.get(i).getDescripcion();
                foto = imagenes.get(i).getFoto();
                setCantidaditems("0");
            }
        }
    }

    public Producto() {
    }

    private ControladorCarrito contCarrito;
    private List<ControladorCarrito> listac = new ArrayList<>();

    public List<ControladorCarrito> getListac() {
        return listac;
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

    private static List<ControladorCarrito> listacarrito = new ArrayList<>();

    public static List<ControladorCarrito> getListacarrito() {
        return listacarrito;
    }

    public static void setListacarrito(List<ControladorCarrito> listacarrito) {
        Producto.listacarrito = listacarrito;
    }

    public void agregarCarrito() {

        contCarrito = new ControladorCarrito();
        contCarrito.setIdP(idproducto);
        contCarrito.setImagenP(foto);
        contCarrito.setNombreP(producto);
        contCarrito.setPrecioP(dinero);
        contCarrito.setRestauranteP(nombrestaurante);
        contCarrito.setCantidad("1");
        contCarrito.setSubtotal(dinero);
        listac.add(contCarrito);
        listacarrito = listac;
        //Conexion con = new Conexion();
        //listap=con.productosPerfil(10, 1);
        setCantidaditems(String.valueOf(listacarrito.size()));
    }

}
