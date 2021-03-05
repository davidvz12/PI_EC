/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexion;

import com.conexion.Conexion;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author USUARIO
 */
@MultipartConfig
@WebServlet(name = "InsertarPro", urlPatterns = {"/InsertarPro"})
public class InsertarPro extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        //Codigo para programar mi pasión
         try (PrintWriter out = response.getWriter()) {
       
        String nombre=request.getParameter("txtRNombre");
        String tipopro=request.getParameter("cbbtipopro");
        String chef=request.getParameter("cbbchef");
        String estado=request.getParameter("cbbestado");
        String precio=request.getParameter("txtPrecio");
        Part archivo=request.getPart("seleccionArchivos");
        String descripcion=request.getParameter("txtdescription");
        String nomarch=request.getParameter("nombre_archivo");
        InputStream entrada=archivo.getInputStream();
        String ruta2="img/"+nomarch;
        String ruta="C:/Users/Cristhian/Documents/NetBeansProjects/Pi8/web/img/"+nomarch;
        File f= new File(ruta);
        FileOutputStream output= new FileOutputStream(f);
        int dato=entrada.read();
        while(dato!=-1){
            output.write(dato);
            dato=entrada.read(); 
        }
        output.close();
        entrada.close();
        Conexion con= new Conexion();
        int producto=con.producto(nombre);
        if(producto!=0){
             con.insertar_productof(producto,2, descripcion,ruta2,Integer.parseInt(tipopro) ,Double.valueOf(precio) , Integer.parseInt(chef) ,estado);
             out.println("<script>alert(\"Producto Registrado\"); </script>");
             request.getRequestDispatcher("RegistrarPro.jsp").forward(request, response);
        }else{
            
            boolean ban=con.insertar_producto(nombre);
            if(ban){
                producto=con.producto(nombre);
                if(producto!=0){
                    boolean ban2=con.insertar_productof(producto,2, descripcion,ruta2,Integer.parseInt(tipopro) ,Double.valueOf(precio) , Integer.parseInt(chef) ,estado);
                    if(ban2){
                         out.println("<script>alert(\"Producto Registrado\"); </script>");
                         request.getRequestDispatcher("RegistrarPro.jsp").forward(request, response);
                    }else{
                        out.println("<script>alert(\"Error no se pudo registrar el producto\"); </script>");
                        request.getRequestDispatcher("RegistrarPro.jsp").forward(request, response);
                    }
                }
                
            }
            
        }
        
        
        
        
       }
        
        
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
