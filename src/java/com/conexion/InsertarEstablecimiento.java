/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexion;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author USUARIO
 */
@WebServlet(name = "InsertarEstablecimiento", urlPatterns = {"/InsertarEstablecimiento"})
public class InsertarEstablecimiento extends HttpServlet {

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
         try (PrintWriter out = response.getWriter()) {
        String latitud="",longitud="";     
        String nombre=request.getParameter("txtRNombre");
        String direccion=request.getParameter("txtDireccion");
        String telefono=request.getParameter("txtTelefono");
        String whatsapp=request.getParameter("txtWhatsapp");
        String geolocalizacion=request.getParameter("latitude");
        String[] coordenadas = geolocalizacion.split(",");
        latitud=coordenadas[0];
        longitud=coordenadas[1];
        Part archivo=request.getPart("seleccionArchivos");
        String descripcion=request.getParameter("txtDescripcion");
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
        boolean bandera=con.insertar_establecimiento(1, nombre, direccion, telefono, whatsapp, descripcion, latitud, longitud, ruta);
        if(bandera){  
        out.println("<script>alert(\"Establecimiento Registrado\"); </script>");
         request.getRequestDispatcher("RegistrarRestaurante.jsp").forward(request, response);
        }else{
        out.println("<script>alert(\"Usted ya cuenta con un establecimiento registrado\"); </script>");
        request.getRequestDispatcher("RegistrarRestaurante.jsp").forward(request, response); 
                
            }
            
        }
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
