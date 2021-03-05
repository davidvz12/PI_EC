/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author JoseRene
 */
@Named(value = "beansugerencia")
@ViewScoped
public class Beansugerencia implements Serializable {

    
    private static String idestablecimientofinal,tipo_produto_final;

    public static String getIdestablecimientofinal() {
        return idestablecimientofinal;
    }

    public static void setIdestablecimientofinal(String idestablecimientofinal) {
        Beansugerencia.idestablecimientofinal = idestablecimientofinal;
    }

    public static String getTipo_produto_final() {
        return tipo_produto_final;
    }

    public static void setTipo_produto_final(String tipo_produto_final) {
        Beansugerencia.tipo_produto_final = tipo_produto_final;
    }
    
    private String nombre, descripcion, foto, categoria, direccion;

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    Conexion con;
    private List<Controladorsugerencia> sugerencia;

    private List<Controladorcategorias> sugerircategorias;
    
    
    private List<ControldorPopulares> sugerenciapopular;
    
    
    private List<ControladorPopu> sugerienciaspopularesfinales;
    
    
    
    private List<ControladorUltimos> sugerirultimos;
    
    private ControladorUltimos ultimosugerir;

    public ControladorUltimos getUltimosugerir() {
        return ultimosugerir;
    }
    
    private static String nombrefinalin;

    public static String getNombrefinalin() {
        return nombrefinalin;
    }

    public static void setNombrefinalin(String nombrefinalin) {
        Beansugerencia.nombrefinalin = nombrefinalin;
    }

    public void setUltimosugerir(ControladorUltimos ultimosugerir) {
        this.ultimosugerir = ultimosugerir;
        
        idestablecimientofinal=ultimosugerir.getIdestablecimiento();
        tipo_produto_final=ultimosugerir.getTipo();
        
        nombrefinalin=ultimosugerir.getNombre();
        
    }
    

    public List<ControladorUltimos> getSugerirultimos() {
        return sugerirultimos;
    }

    public void setSugerirultimos(List<ControladorUltimos> sugerirultimos) {
        this.sugerirultimos = sugerirultimos;
    }
    
    private ControladorPopu popufinal;

    public ControladorPopu getPopufinal() {
        return popufinal;
    }

    public void setPopufinal(ControladorPopu popufinal) {
        this.popufinal = popufinal;
        
        idestablecimientofinal=popufinal.getIdestablecimiento();
        tipo_produto_final=popufinal.getTipo();
        
        nombrefinalin=popufinal.getNombre();
        
        
    }

    public List<ControladorPopu> getSugerienciaspopularesfinales() {
        return sugerienciaspopularesfinales;
    }

    public void setSugerienciaspopularesfinales(List<ControladorPopu> sugerienciaspopularesfinales) {
        this.sugerienciaspopularesfinales = sugerienciaspopularesfinales;
    }
    

    public List<ControldorPopulares> getSugerenciapopular() {
        return sugerenciapopular;
    }

    public void setSugerenciapopular(List<ControldorPopulares> sugerenciapopular) {
        this.sugerenciapopular = sugerenciapopular;
    }
    

    private Controladorcategorias controlc;

    public Controladorcategorias getControlc() {
        return controlc;
    }

    public void setControlc(Controladorcategorias controlc) {
        this.controlc = controlc;
        
        
        idestablecimientofinal=controlc.getId_establecimiento();
        tipo_produto_final=controlc.getTipo();
        nombrefinalin=controlc.getNombre();
        
    }
    
    public String cambiar()
    {
        return "Producto";
    }
    
    
    
    public List<Controladorcategorias> getSugerircategorias() {
        return sugerircategorias;
    }

    public void setSugerircategorias(List<Controladorcategorias> sugerircategorias) {
        this.sugerircategorias = sugerircategorias;
    }

    public List<Controladorsugerencia> getSugerencia() {
        return sugerencia;
    }

    public void setSugerencia(List<Controladorsugerencia> sugerencia) {
        this.sugerencia = sugerencia;
    }

    Random r = new Random();

    @PostConstruct
    public void init() {
        con = new Conexion();
        int valorDado;
        sugerencia = new ArrayList<>();
        sugerencia = con.sugerir();

        if (sugerencia.size() <= 8) {
            valorDado = r.nextInt(sugerencia.size() - 1);
        } else {
            valorDado = r.nextInt(9);
        }

        nombre = sugerencia.get(valorDado).getNombre();
        foto = sugerencia.get(valorDado).getFoto();
        descripcion = sugerencia.get(valorDado).getDescripcion();
        categoria = sugerencia.get(valorDado).getCategoria();
        direccion = sugerencia.get(valorDado).getDireccion();

        con=new Conexion();
        sugerircategorias=new ArrayList<>();
        sugerircategorias=con.sugerircategoria();
        
        
        
        
        con=new Conexion();
        sugerenciapopular=con.recomendaciones(1);
        
        
        con=new Conexion();
        sugerienciaspopularesfinales=new ArrayList<>();
        sugerienciaspopularesfinales=con.sugerirPopulares(sugerenciapopular);
        
        
        con=new Conexion();
        sugerirultimos=new ArrayList<>();
        sugerirultimos=con.sugerirUltimos();
    }

    public Beansugerencia() {
    }

}
