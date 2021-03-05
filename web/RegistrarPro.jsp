<%-- 
    Document   : RegistrarPro
    Created on : Sep 3, 2020, 1:54:50 AM
    Author     : USUARIO
--%>
<%@page import="com.conexion.Chefs"%>
<%@page import="com.conexion.Tipo_productos"%>
<%@page import="java.util.List"%>
<%@page import="com.conexion.Conexion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://xmlns.jcp.org/jsf/html"
      xmlns:ui="http://xmlns.jcp.org/jsf/facelets"
      xmlns:c="http://xmlns.jcp.org/jsp/jstl/core"
      xmlns:a="http://xmlns.jcp.org/jsf/passthrough"
      xmlns:p="http://primefaces.org/ui">
    <h:head>
        <title>Registrar Producto</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <link href="resources/css/estilos.css" rel="stylesheet" type="text/css"/>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
              rel="stylesheet"/>

    </h:head>
    
    <h:body style=" background-color: #F7F7F7;">
    <div class="nav_header" >
            <div class="nav_header_logo">
                <div>
                    <span class="material-icons icon_logo" >shopping_cart</span>
                </div>
                <div>
                    <t3 style="font-weight: 600; color: #707070">LOGO</t3>
                </div>
            </div>
            <div class="nav_header_busqueda_princ">
                <div class="nav_header_busqueda">
                    <input class="nav_header_busq_text" type="text" placeholder="Buscar.."/>
                    <span class="material-icons nav_header_busq_icon">search</span>
                </div>
                <div class="nav_header_busq_cart_cont">
                    <p class="item_num_cart">9+</p>
                    <a href="#" id="Add2cart"><i class="material-icons nav_header_busq_cart_cont_icon" style="font-size: 25px;">shopping_cart</i>
                    </a>

                    <label for="Add2cart" class="nav_header_busq_cart_cont_lbl">Mi carrito</label>
                </div>

            </div>

            <div class="nav_header_submenu">
                <div class="nav_header_busq_cart_cont_item">
                    <a href="#" id="nav_head_prim_itm_home"><i class="material-icons nav_header_busq_cart_cont_icon selected_item" style="font-size: 25px;">home</i>
                    </a>
                    <label for="nav_head_prim_itm_home" class="nav_header_busq_cart_cont_lbl selected_item">Inicio</label>
                </div>
                <div class="nav_header_busq_cart_cont_item">
                    <a href="#" id="nav_head_prim_itm_star"><i class="material-icons nav_header_busq_cart_cont_icon" style="font-size: 25px;">favorite</i>
                    </a>
                    <label for="nav_head_prim_itm_star" class="nav_header_busq_cart_cont_lbl">Favoritos</label>
                </div>
                <div class="nav_header_busq_cart_cont_item">
                    <a href="#" id="nav_head_prim_itm_star"><i class="material-icons nav_header_busq_cart_cont_icon" style="font-size: 25px;">style</i>
                    </a>
                    <label for="nav_head_prim_itm_star" class="nav_header_busq_cart_cont_lbl">Mis compras</label>
                </div>
                <div class="nav_header_busq_cart_cont_item">
                    <a href="#" id="nav_head_prim_itm_star"><i class="material-icons nav_header_busq_cart_cont_icon" style="font-size: 25px;">perm_identity</i>
                    </a>
                    <label for="nav_head_prim_itm_star" class="nav_header_busq_cart_cont_lbl">Usuario</label>
                </div>
            </div>

        </div>
        <div class="prinplbRes" >   
            <div class="prinplbRes_sec" >
                <div class="prinplbRes_tit">
                    <div class="prinplbRes_tit_ci"><span class="material-icons">new_releases</span></div>
                    <div class="prinplbRes_tit_cont">NUEVO PRODUCTO</div>
                </div>
                
                <form name="InsertarPro" action="InsertarPro" method="POST" enctype="multipart/form-data">
                <div class="contPrinFrm">
                    <div class="contPrinFrm_Sec1">
                        <div class="contPrinFrm_Sec1_in">

                            <div class="contInpT">
                                <label class="contInpT_lbl" for="txtRNombre" >Nombre</label>
                                <div class="contInpT_Sec2">
                                    <span class="contInpT_img material-icons">local_offer</span>
                                    <input type="text" class="contInpT_txt" name="txtRNombre" id="txtRNombre" a:placeholder="Ingrese un nombre" required/>
                                </div> 
                            </div>

                            <div class="contInpT">
                                <label class="contInpT_lbl" for="txtRNombre" >Tipo producto</label>
                                <div class="contInpT_Sec2">
                                    <span class="contInpT_img material-icons">fastfood</span>
                                    <select name="cbbtipopro" class="select-css">
                                         <%
                                        Conexion listaP = new Conexion();
                                        List<Tipo_productos> lista=listaP.tipoproducto();
                                        int idProvincia = 0;
                                        String nombre = "";
                                        for (int i = 0; i < lista.size(); i++) {
                                            idProvincia = lista.get(i).getId();
                                            nombre = lista.get(i).getNombre();
                                            out.println("<option value=" + idProvincia + ">" + nombre + "</option>");
                                        }
                                    %>  
                                    </select>

                                </div> 
                            </div>
                            <div class="contInpT">
                                <label class="contInpT_lbl" for="txtRNombre" >Chef</label>
                                <div class="contInpT_Sec2">
                                    <span class="contInpT_img material-icons">bookmarks</span>
                                    <select name="cbbchef" class="select-css">
                                        <%
                                        Conexion listaC = new Conexion();                                        
                                        List<Chefs> listachefs=listaC.chefs(1);
                                        int idChef = 0;
                                        String nombres = "";
                                        String apellidos="";
                                        for (int i = 0; i < listachefs.size(); i++) {
                                            idChef = listachefs.get(i).getId();
                                            nombres = listachefs.get(i).getNombre();
                                            apellidos=listachefs.get(i).getApellidos();
                                            out.println("<option value=" + idChef + ">" + nombre+" "+apellidos + "</option>");
                                        }
                                    %>  
                                    </select>
                                </div> 
                            </div>
                            <div class="contInpT">
                                <label class="contInpT_lbl" for="txtRNombre" >Precio</label>
                                <div class="contInpT_Sec2">
                                    <span class="contInpT_img material-icons">attach_money</span>
                                    <input type="number" step="any" class="contInpT_txt" name="txtPrecio" id="txtPrecio" a:placeholder="0.00" required/>
                                </div> 
                            </div>
                        </div>
                         <div id="fotos" columns="1">
                            <div class="contPrinFrm_Sec1_in">
                                <div class="contPrinFrmImgCargCon" >                          
                                    <img id="imagenPrevisualizacion" class="contPrinFrmImgCarg" style="height: 120px;" src="img/alitas.jpg" alt="" />
                                </div>
                                <div class="contInpT">
                                    <label class="contInpT_lbl" for="txtRNombre" >Foto</label>
                                    <div class="contInpT_Sec2">
                                        <span class="contInpT_img material-icons">camera_alt</span>
                                        <label class="custom-file-upload">
                                            <input type="file" name="seleccionArchivos" id="seleccionArchivos" accept="image/*" required/>
                                            <input type="hidden" id="nombre_archivo" name="nombre_archivo" value="" />
                                        </label>
                                    </div>

                                </div>
                                <div class="contInpT">
                                <label class="contInpT_lbl" for="txtRNombre" >Estado</label>
                                <div class="contInpT_Sec2">
                                    <span class="contInpT_img material-icons">bookmarks</span>
                                    <select name="cbbestado" class="select-css">
                                        <option value="SI">Disponible</option>
                                        <option value="No">No Disponible</option>
                                    </select>
                                </div> 
                            </div>
                            </div>
                         </div>
                    </div>

                    <div class="contInpT" >
                        <label class="contInpT_lbl" for="txtRNombre" >Descripción</label>
                        <div class="contInpT_Sec2" style="height: 90px;" >
                            <span class="contInpT_img material-icons" style="margin-top: -40px;">description</span>
                            <textarea name="txtdescription" class="contInpT_txt" style="height: 75px; width: 95%; margin-top: 10px;" id="txtRNombre" a:placeholder="Ingrese la descripci'on del producto" cols="30" rows="10" ></textarea>
          
                        </div>

                    </div>
                       <input type="submit" class="card_prod_dest_btnRes" style="margin: auto; margin-top: 20px;" value="GUARDAR" onclick="guardar()"/>
                </div>
              </form>
            </div>
        </div>                               
        <script>
          // Obtener referencia al input y a la imagen
            const $seleccionArchivos = document.querySelector("#seleccionArchivos"),
              $imagenPrevisualizacion = document.querySelector("#imagenPrevisualizacion"),
              $nombre_archivo = document.querySelector("#nombre_archivo");

            // Escuchar cuando cambie
            $seleccionArchivos.addEventListener("change", () => {
              // Los archivos seleccionados, pueden ser muchos o uno
              const archivos = $seleccionArchivos.files;
              // Si no hay archivos salimos de la función y quitamos la imagen
              if (!archivos || !archivos.length) {
                $imagenPrevisualizacion.src = "";
                return;
              }
              // Ahora tomamos el primer archivo, el cual vamos a previsualizar
              const primerArchivo = archivos[0];
              $nombre_archivo.value=primerArchivo.name;

              // Lo convertimos a un objeto de tipo objectURL
              const objectURL = URL.createObjectURL(primerArchivo);
              // Y a la fuente de la imagen le ponemos el objectURL
              $imagenPrevisualizacion.src = objectURL;
            });

        </script>    
    <body>
        
</html>