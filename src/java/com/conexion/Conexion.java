/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Conexion {

    public Connection connection;
    private String message;
    private Statement statement;
    ResultSet resultSet;

    public Conexion() {
        System.out.println("HOLAAAAA");
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            //connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyectofinanciero", "root", "");
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dbFoodi", "postgres", "123");
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            //connection = DriverManager.getConnection(url, username, password);
            
            message = "ok";
        } catch (Exception ex) {
            message = ex.getMessage();
        }
    }

    public String obtenerFotoEstablecimiento(int id) {
        String foto = "";
        try {
            ResultSet rs = statement.executeQuery("select foto from establecimiento where id_establecimiento=1");
            while (rs.next()) {
                foto = rs.getString("foto");
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return foto;
    }

    public String descripcionEstablecimiento(int id) {
        String desc = "";
        try {
            ResultSet rs = statement.executeQuery("select descripcion from establecimiento where id_establecimiento=1");
            while (rs.next()) {
                desc = rs.getString("descripcion");
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return desc;
    }

    public void registrarCalificacionE(int calificacion, String comentario) {
        String desc = "";
        try {
            //select insertarcomentarioE (5,'Excelente',1,1);	   
            ResultSet rs = statement.executeQuery("select insertarcomentarioE(" + calificacion + ",'" + comentario + "',1,1);");
            rs.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public calificacion analisisCalificacionE(int i) {
        List<calificacion> listCalificaciones = new ArrayList();

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dbFoodi", "postgres", "123");

            statement = null;
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            int n = 0;
            ResultSet rs2 = statement.executeQuery("select u.nombre_usuario,c.calificacion,c.opinion,c.fecha from calificacion c, usuario u where \n"
                    + "c.id_usuario=u.id_usuario and id_establecimiento=" + i + "");
            while (rs2.next()) {
                calificacion c = new calificacion();
                c.setNombreUsuario(rs2.getString("nombre_usuario"));
                c.setFecha(rs2.getString("fecha"));
                c.setOpinion(rs2.getString("opinion"));
                c.setCalificacion(rs2.getInt("calificacion"));
                listCalificaciones.add(c);
                n++;
            }
            rs2.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            String Exc = e.toString();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        calificacion ac = new calificacion();
        float prom = 0, por1 = 0, por2 = 0, por3 = 0, por4 = 0, por5 = 0;
        float suma = 0, cantidad = 0;
        int promedioint = 0, cant1 = 0, cant2 = 0, cant3 = 0, cant4 = 0, cant5 = 0;
        cantidad = listCalificaciones.size();

        for (int j = 0; j < cantidad; j++) {
            suma = suma + listCalificaciones.get(j).calificacion;
            switch (listCalificaciones.get(j).calificacion) {
                case 1:
                    cant1 = cant1 + 1;
                    break;
                case 2:
                    cant2 = cant2 + 1;
                    break;
                case 3:
                    cant3 = cant3 + 1;
                    break;
                case 4:
                    cant4 = cant4 + 1;
                    break;
                case 5:
                    cant5 = cant5 + 1;
                    break;
            }
        }
        prom = suma / cantidad;
        promedioint = (int) prom;
        DecimalFormat formato1 = new DecimalFormat("#.00");
        String promedio = formato1.format(prom);
        por1 = ((cant1 * 100) / cantidad);
        por2 = ((cant2 * 100) / cantidad);
        por3 = ((cant3 * 100) / cantidad);
        por4 = ((cant4 * 100) / cantidad);
        por5 = ((cant5 * 100) / cantidad);

        ac.setPorc1(por1);
        ac.setPorc2(por2);
        ac.setPorc3(por3);
        ac.setPorc4(por4);
        ac.setPorc5(por5);

        int j = listCalificaciones.size();
        ac.setNumOpiniones(j);
        ac.setPromedio(promedio);
        ac.setPromedioInt(promedioint);

        return ac;
    }

    public List<calificacion> calificacionesPorEstablecimiento(int i) {
        List<calificacion> listCalificaciones = new ArrayList();
        int n = 0;
        try {
            ResultSet rs = statement.executeQuery("select u.nombre_usuario,c.calificacion,c.opinion,c.fecha from calificacion c, usuario u where \n"
                    + "c.id_usuario=u.id_usuario and id_establecimiento=" + i + "");
            while (rs.next()) {
                calificacion c = new calificacion();
                c.setNombreUsuario(rs.getString("nombre_usuario"));
                c.setFecha(rs.getString("fecha"));
                c.setOpinion(rs.getString("opinion"));
                c.setCalificacion(rs.getInt("calificacion"));
                listCalificaciones.add(c);
                n++;
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (Exception e) {

            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return listCalificaciones;
    }

    public int calificacionEstablecimiento(int id) {
        int desc = 0;
        try {
            ResultSet rs = statement.executeQuery("select calificacion from establecimiento where id_establecimiento=1");
            while (rs.next()) {
                desc = Integer.valueOf(rs.getString("calificacion"));
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return desc;
    }

    public List<Producto_Final> ProductoPorEst(int idestablecimiento) {

        String fotof = "";
        String desc = "", nomb = "";
        List<Producto_Final> fotos = new ArrayList();
        int n = 0;
        try {
            ResultSet rs = statement.executeQuery("select p.descripcion as descripcion, p.foto as foto, e.nombre as nombre from producto_final p inner join establecimiento e on p.id_establecimiento=e.id_establecimiento and e.id_establecimiento=" + idestablecimiento + "");
            while (rs.next()) {
                Producto_Final c = new Producto_Final();
                fotof = rs.getString("foto");
                desc = rs.getString("descripcion");
                nomb = rs.getString("nombre");
                c.setDescripcion(desc);
                c.setFoto(fotof);
                c.setNombre(nomb);
                fotos.add(n, c);
                n++;
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return fotos;
    }

    public Integer existeusuario(String usuario) {
        int desc = 0;
        try {
            ResultSet rs = statement.executeQuery("select id_usuario from usuario where nombre_usuario='" + usuario + "'");
            while (rs.next()) {
                desc = Integer.parseInt(rs.getString("id_usuario"));
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return desc;
    }

    public Integer maximopersona() {
        int desc = 0;
        try {
            ResultSet rs = statement.executeQuery("select max(id_persona)as id from persona");
            while (rs.next()) {
                desc = Integer.parseInt(rs.getString("id"));
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return desc;
    }
    
    
     public List<ControladorEstablecimientoFinal> presentarestablecimiento(int id) {
        ResultSet rs = null;
        String fotoestablecimiento, categoriaestablecimiento, direccion, descripcion, tipoproducto, nombreestablecimiento, idestablecimiento, nombreproducto, fotoproducto;
        List<ControladorEstablecimientoFinal> fotos = new ArrayList();
        int n = 0;
        try {
            rs = statement.executeQuery("select es.foto as festablecimiento,cgor.nombre as catestablecimiento,es.direccion,es.descripcion as destablecimiento,\n"
                    + "tc.id_tipo_producto as tipo,es.nombre as establecimiento,pf.id_establecimiento,pt.nombre,pf.foto\n"
                    + "                    from producto_final pf \n"
                    + "                    inner join producto pt on pt.id_producto=pf.id_producto inner join \n"
                    + "                    establecimiento es on es.id_establecimiento=pf.id_establecimiento\n"
                    + "                    inner join tipo_producto tc on tc.id_tipo_producto=pf.id_tipo_producto\n"
                    + "					inner join categoria_establecimiento cs on cs.id_establecimiento=es.id_establecimiento\n"
                    + "					inner join categoria cgor on cgor.id_categoria=cs.id_categoria\n"
                    + "                  	where pf.id_establecimiento=" + id + "");
            while (rs.next()) {
                ControladorEstablecimientoFinal c = new ControladorEstablecimientoFinal();
                fotoestablecimiento = rs.getString("festablecimiento");
                categoriaestablecimiento = rs.getString("catestablecimiento");
                direccion = rs.getString("direccion");
                descripcion = rs.getString("destablecimiento");
                tipoproducto = rs.getString("tipo");
                
                nombreestablecimiento = rs.getString("establecimiento");
                idestablecimiento = rs.getString("id_establecimiento");
                nombreproducto = rs.getString("nombre");
                fotoproducto = rs.getString("foto");
                               
                c.setCategoriaestablecimiento(categoriaestablecimiento);
                c.setFotoestablecimiento(fotoestablecimiento);
                c.setDescripcion(descripcion);
                c.setDireccion(direccion);
                c.setTipoproducto(tipoproducto);
                c.setNombreestablecimiento(nombreestablecimiento);
                c.setIdestablecimiento(idestablecimiento);
                c.setNombreproducto(nombreproducto);
                c.setFotoproducto(fotoproducto);
                fotos.add(n, c);
                n++;
            }

            rs.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return fotos;
    }
    
    
    
    
    

    public Integer maximoUsuario() {
        int desc = 0;
        try {
            ResultSet rs = statement.executeQuery("select max(id_usuario) as id from usuario");
            while (rs.next()) {
                desc = Integer.parseInt(rs.getString("id"));
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return desc;
    }

    public String nombreEstablecimiento(int id) {
        String desc = "";
        try {
            ResultSet rs = statement.executeQuery("select nombre from establecimiento where id_establecimiento=1");
            while (rs.next()) {
                desc = rs.getString("nombre");
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return desc;
    }

    public String tipoEstablecimiento(int id) {
        String desc = "";
        try {
            ResultSet rs = statement.executeQuery("select nombre from categoria c inner join categoria_establecimiento e on c.id_categoria=e.id_categoria where id_establecimiento=1");
            while (rs.next()) {
                desc = rs.getString("nombre");
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return desc;
    }

    public String direccionEstablecimiento(int id) {
        String desc = "";
        try {
            ResultSet rs = statement.executeQuery("select direccion from establecimiento where id_establecimiento=1");
            while (rs.next()) {
                desc = rs.getString("direccion");
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return desc;
    }

    public void registrarPersona(int id, String nombre, String apellido, String telefono, String correo, String ciudad, String sector, String calle, String referencia) {
        String desc = "";
        try {
            ResultSet rs = statement.executeQuery("insert into persona values(" + id + ",'" + nombre + "','" + apellido + "','" + telefono + "','" + correo + "','" + ciudad + "','" + sector + "','" + calle + "','" + referencia + "')");
            rs.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public void registrarPago(int id_usuario, int id_establecimiento, String direccion, String valor_cancelar, String longitud, String latitud) {
        String desc = "";
        try {
            //insert into pedido (id_usuario,id_establecimiento,fecha,direccion,valor_cancelar,longitud,latitud)
//values(1,1,current_date,'Esmeraldas','2.50','-1.028453','-79.467503');
            ResultSet rs = statement.executeQuery("insert into pedido (id_usuario,id_establecimiento,fecha,direccion,valor_cancelar,longitud,latitud) values(1,1,current_date,'Quevedo','" + valor_cancelar + "','" + longitud + "','" + latitud + "');");
            rs.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public void registrarDetallePago(String idProducto, String cantidad, String precio, String observacion) {
        String desc = "";
        try {
            ResultSet rs = statement.executeQuery("insert into detalle_pedido (id_pedido,id_producto_final,cantidad,precio_unitario,observacion) values((SELECT MAX(id_pedido) FROM pedido)," + idProducto + "," + cantidad + ",'" + precio + "','" + observacion + "');");
            rs.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public void registrarUsuario(int usuario, int id, String nombre, String clave) {
        String desc = "";
        try {
            ResultSet rs = statement.executeQuery("insert into usuario values (" + usuario + ",'" + nombre + "','" + hash.sha1(clave) + "','C'," + id + ")");
            rs.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public void registrarCalificacion(int calificacion, String comentario) {
        String desc = "";
        try {
            ResultSet rs = statement.executeQuery("select insertarcomentario(" + calificacion + ",'" + comentario + "',1,1);");
            rs.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public List<CatalagoImagenes> fotosCatalogo(int i) {
        String fotof = "";
        String desc = "", nomb = "";
        List<CatalagoImagenes> fotos = new ArrayList();
        int n = 0;
        try {
            ResultSet rs = statement.executeQuery("select p.foto as foto, a.nombre as restaurante, t.nombre as nombre from producto_final p \n"
                    + "	inner join producto t on t.id_producto=p.id_producto\n"
                    + "	inner join establecimiento a on a.id_establecimiento= p.id_establecimiento where p.id_establecimiento=1");
            while (rs.next()) {
                CatalagoImagenes c = new CatalagoImagenes();
                fotof = rs.getString("foto");
                desc = rs.getString("restaurante");
                nomb = rs.getString("nombre");
                c.setFoto(fotof);
                c.setDescripcion(desc);
                c.setNombre(nomb);
                fotos.add(n, c);
                n++;
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return fotos;
    }

    public List<Producto_Final> fotosProdcuto() {
        String fotof = "";
        String desc = "", nomb = "";
        List<Producto_Final> fotos = new ArrayList();
        int n = 0;
        try {
            ResultSet rs = statement.executeQuery("select p.descripcion as descripcion, p.foto as foto, e.nombre as nombre from producto_final p inner join establecimiento e on p.id_establecimiento=e.id_establecimiento");
            while (rs.next()) {
                Producto_Final c = new Producto_Final();
                fotof = rs.getString("foto");
                desc = rs.getString("descripcion");
                nomb = rs.getString("nombre");
                c.setDescripcion(desc);
                c.setFoto(fotof);
                c.setNombre(nomb);
                fotos.add(n, c);
                n++;
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return fotos;
    }

    public List<Producto_Final> filtroTipoComida(int i) {
        String fotof = "";
        String desc = "", nomb = "";
        List<Producto_Final> fotos = new ArrayList();
        int n = 0;
        try {
            ResultSet rs = statement.executeQuery("select p.descripcion as descripcion, p.foto as foto, e.nombre as nombre from producto_final p inner join establecimiento e \n"
                    + "on p.id_establecimiento=e.id_establecimiento where p.id_tipo_producto=" + i + "");
            while (rs.next()) {
                Producto_Final c = new Producto_Final();
                fotof = rs.getString("foto");
                desc = rs.getString("descripcion");
                nomb = rs.getString("nombre");
                c.setDescripcion(desc);
                c.setFoto(fotof);
                c.setNombre(nomb);
                fotos.add(n, c);
                n++;
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return fotos;
    }

    public List<calificacion> calificacionesPorProducto(int i) {
        List<calificacion> listCalificaciones = new ArrayList();
        int n = 0;
        try {
            ResultSet rs = statement.executeQuery("select u.nombre_usuario,c.calificacion,c.opinion,c.fecha from calificacion c, usuario u where \n"
                    + "c.id_usuario=u.id_usuario and id_producto_final=" + i + "");
            while (rs.next()) {
                calificacion c = new calificacion();
                c.setNombreUsuario(rs.getString("nombre_usuario"));
                c.setFecha(rs.getString("fecha"));
                c.setOpinion(rs.getString("opinion"));
                c.setCalificacion(rs.getInt("calificacion"));
                listCalificaciones.add(c);
                n++;
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return listCalificaciones;
    }

    public calificacion analisisCalificacion(int i) {
        List<calificacion> listCalificaciones = new ArrayList();

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dbFoodi", "postgres", "123");

            statement = null;
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            int n = 0;
            ResultSet rs2 = statement.executeQuery("select u.nombre_usuario,c.calificacion,c.opinion,c.fecha from calificacion c, usuario u where \n"
                    + "c.id_usuario=u.id_usuario and id_producto_final=" + i + "");
            while (rs2.next()) {
                calificacion c = new calificacion();
                c.setNombreUsuario(rs2.getString("nombre_usuario"));
                c.setFecha(rs2.getString("fecha"));
                c.setOpinion(rs2.getString("opinion"));
                c.setCalificacion(rs2.getInt("calificacion"));
                listCalificaciones.add(c);
                n++;
            }
            rs2.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            String Exc = e.toString();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        calificacion ac = new calificacion();
        float prom = 0, por1 = 0, por2 = 0, por3 = 0, por4 = 0, por5 = 0;
        float suma = 0, cantidad = 0;
        int promedioint = 0, cant1 = 0, cant2 = 0, cant3 = 0, cant4 = 0, cant5 = 0;
        cantidad = listCalificaciones.size();

        for (int j = 0; j < cantidad; j++) {
            suma = suma + listCalificaciones.get(j).calificacion;
            switch (listCalificaciones.get(j).calificacion) {
                case 1:
                    cant1 = cant1 + 1;
                    break;
                case 2:
                    cant2 = cant2 + 1;
                    break;
                case 3:
                    cant3 = cant3 + 1;
                    break;
                case 4:
                    cant4 = cant4 + 1;
                    break;
                case 5:
                    cant5 = cant5 + 1;
                    break;
            }
        }
        prom = suma / cantidad;
        promedioint = (int) prom;
        DecimalFormat formato1 = new DecimalFormat("#.00");
        String promedio = formato1.format(prom);
        por1 = ((cant1 * 100) / cantidad);
        por2 = ((cant2 * 100) / cantidad);
        por3 = ((cant3 * 100) / cantidad);
        por4 = ((cant4 * 100) / cantidad);
        por5 = ((cant5 * 100) / cantidad);

        ac.setPorc1(por1);
        ac.setPorc2(por2);
        ac.setPorc3(por3);
        ac.setPorc4(por4);
        ac.setPorc5(por5);

        int j = listCalificaciones.size();
        ac.setNumOpiniones(j);
        ac.setPromedio(promedio);
        ac.setPromedioInt(promedioint);

        return ac;
    }

    // public List<Producto_Final> filtroTipoComidaPrecioMayorMenor(int i) 
    //{
    public List<ControladorProducto> productosPerfil(int i, int res) {
        List<ControladorProducto> listCalificaciones = new ArrayList();

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dbFoodi", "postgres", "123");
            //connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dbFoodi", "postgres", "123");
            statement = null;
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            int n = 0;
            ResultSet rs2 = statement.executeQuery("select p.id_producto as producid,a.foto,p.nombre as producto,m.nombre as establecimiento,  a.descripcion, a.precio from producto_final a inner join \n"
                    + "producto p on p.id_producto = a.id_producto inner join establecimiento m \n"
                    + "on m.id_establecimiento = a.id_establecimiento where p.id_producto=" + i + " and m.id_establecimiento=" + res + "");
            while (rs2.next()) {
                ControladorProducto c = new ControladorProducto();
                c.setNombreP(rs2.getString("producto"));
                c.setIdP(rs2.getString("producid"));
                c.setDescripcionP(rs2.getString("descripcion"));
                c.setImagenP(rs2.getString("foto"));
                c.setRestauranteP(rs2.getString("establecimiento"));
                c.setPrecioP(rs2.getString("precio"));
                listCalificaciones.add(c);
                n++;
            }
            rs2.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            String Exc = e.toString();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return listCalificaciones;
    }

    public List<Producto_Final> filtroTipoComidaPrecioMayorMenor(int i) {
        String fotof = "";
        String desc = "", nomb = "";
        List<Producto_Final> fotos = new ArrayList();
        int n = 0;
        try {
            ResultSet rs = statement.executeQuery("select p.descripcion as descripcion, p.foto as foto, e.nombre as nombre from producto_final p inner join establecimiento e \n"
                    + "on p.id_establecimiento=e.id_establecimiento where p.id_tipo_producto=" + i + ""
                    + "order by p.precio desc");
            while (rs.next()) {
                Producto_Final c = new Producto_Final();
                fotof = rs.getString("foto");
                desc = rs.getString("descripcion");
                nomb = rs.getString("nombre");
                c.setDescripcion(desc);
                c.setFoto(fotof);
                c.setNombre(nomb);
                fotos.add(n, c);
                n++;
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return fotos;
    }

    public List<Producto_Final> filtroTipoComidaPrecioMenorMayor(int i) {
        String fotof = "";
        String desc = "", nomb = "";
        List<Producto_Final> fotos = new ArrayList();
        int n = 0;
        try {
            ResultSet rs = statement.executeQuery("select p.descripcion as descripcion, p.foto as foto, e.nombre as nombre from producto_final p inner join establecimiento e \n"
                    + "on p.id_establecimiento=e.id_establecimiento where p.id_tipo_producto=" + i + "\n"
                    + "order by p.precio asc");
            while (rs.next()) {
                Producto_Final c = new Producto_Final();
                fotof = rs.getString("foto");
                desc = rs.getString("descripcion");
                nomb = rs.getString("nombre");
                c.setDescripcion(desc);
                c.setFoto(fotof);
                c.setNombre(nomb);
                fotos.add(n, c);
                n++;
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return fotos;
    }

    public List<Producto_Final> flitroSoloPrecioMenorMayor() {
        String fotof = "";
        String desc = "", nomb = "";
        List<Producto_Final> fotos = new ArrayList();
        int n = 0;
        try {
            ResultSet rs = statement.executeQuery("select p.descripcion as descripcion, p.foto as foto, e.nombre as nombre from producto_final p inner join establecimiento e \n"
                    + "on p.id_establecimiento=e.id_establecimiento order by p.precio desc");
            while (rs.next()) {
                Producto_Final c = new Producto_Final();
                fotof = rs.getString("foto");
                desc = rs.getString("descripcion");
                nomb = rs.getString("nombre");
                c.setDescripcion(desc);
                c.setFoto(fotof);
                c.setNombre(nomb);
                fotos.add(n, c);
                n++;
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return fotos;
    }

    public List<Producto_Final> flitroSoloPrecioMayorMenor() {
        String fotof = "";
        String desc = "", nomb = "";
        List<Producto_Final> fotos = new ArrayList();
        int n = 0;
        try {
            ResultSet rs = statement.executeQuery("select p.descripcion as descripcion, p.foto as foto, e.nombre as nombre from producto_final p inner join establecimiento e \n"
                    + "on p.id_establecimiento=e.id_establecimiento order by p.precio asc");
            while (rs.next()) {
                Producto_Final c = new Producto_Final();
                fotof = rs.getString("foto");
                desc = rs.getString("descripcion");
                nomb = rs.getString("nombre");
                c.setDescripcion(desc);
                c.setFoto(fotof);
                c.setNombre(nomb);
                fotos.add(n, c);
                n++;
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return fotos;
    }

    public Integer id(String usu, String ct) {
        int desc = 0;
        try {
            ResultSet rs = statement.executeQuery("select id_usuario from usuario where nombre_usuario='" + usu + "' and clave='" + ct + "'");
            while (rs.next()) {
                desc = Integer.parseInt(rs.getString("id_usuario"));
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return desc;
    }

    public List<Tipo_productos> tipoproducto() {
        List<Tipo_productos> productos = new ArrayList();
        String nomb = "";
        int n = 0;
        try {
            ResultSet rs = statement.executeQuery("select id_tipo_producto, nombre from tipo_producto");
            while (rs.next()) {
                Tipo_productos pro = new Tipo_productos();
                n = rs.getInt("id_tipo_producto");
                nomb = rs.getString("nombre");
                pro.setId(n);
                pro.setNombre(nomb);
                productos.add(pro);
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return productos;
    }

    public List<Chefs> chefs(int establecimiento) {
        List<Chefs> chef = new ArrayList();
        String nomb = "", apelli = "";
        int n = 0;
        try {
            ResultSet rs = statement.executeQuery("select chef.id_chef,persona.nombres,persona.apellidos from chef,persona where chef.id_persona=persona.id_persona and id_establecimiento=" + establecimiento + "");
            while (rs.next()) {
                Chefs pro = new Chefs();
                n = rs.getInt("id_chef");
                nomb = rs.getString("nombres");
                apelli = rs.getString("apellidos");
                pro.setId(n);
                pro.setNombre(nomb);
                pro.setApellidos(apelli);
                chef.add(pro);
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return chef;
    }

    public int producto(String nombre) {
        int id = 0;
        try {
            ResultSet rs = statement.executeQuery("select id_producto from producto where nombre='" + nombre + "'");
            while (rs.next()) {

                id = rs.getInt("id_producto");
            }
            rs.close();
            statement.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return id;
    }

    public boolean insertar_productof(int producto, int establecimiento, String descripcion, String foto, int tipo_producto, double precio, int chef, String disponible) {
        boolean bandera = true;
        try {
            statement = connection.createStatement();
            statement.executeQuery("insert into producto_final(id_producto,id_establecimiento,descripcion,foto,id_tipo_producto,precio,id_chef,disponible) values(" + producto + "," + establecimiento + ",'" + descripcion + "','" + foto + "'," + tipo_producto + "," + precio + "," + chef + ",'" + disponible + "')");
            statement.close();
            connection.close();
        } catch (SQLException e) {

        }
        return bandera;
    }

    public boolean insertar_producto(String nombre) {
        boolean bandera = true;
        try {
            statement = connection.createStatement();
            statement.executeQuery("insert into producto(nombre)values('" + nombre + "')");
            statement.close();
        } catch (SQLException e) {
        }
        return bandera;
    }

    public List<Controlador_reporte> reporte() {
        String fotof = "";
        String dir = "", nomb = "", idestablecimiento = "", idusuario = "", telefono = "", descr = "";
        List<Controlador_reporte> fotos = new ArrayList();
        int n = 0;
        try {
            ResultSet rs = statement.executeQuery("select id_establecimiento, id_usuario, nombre, direccion, telefono,descripcion, foto from establecimiento");
            while (rs.next()) {
                Controlador_reporte c = new Controlador_reporte();
                fotof = rs.getString("foto");
                dir = rs.getString("direccion");
                nomb = rs.getString("nombre");
                idestablecimiento = rs.getString("id_establecimiento");
                idusuario = rs.getString("id_usuario");
                telefono = rs.getString("telefono");
                descr = rs.getString("descripcion");

                c.setDescripcion(descr);
                c.setDireccion(dir);
                c.setFoto(fotof);
                c.setId_establecimiento(idestablecimiento);
                c.setId_usuario(idusuario);
                c.setNombre(nomb);
                c.setTelefono(telefono);
                fotos.add(n, c);
                n++;
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return fotos;
    }

    public List<ControladorGraficar> graficar(int establecimiento) {
        String nombre = "";
        String id_establecimiento = "", puntaje = "";
        List<ControladorGraficar> fotos = new ArrayList();
        int n = 0;
        try {
            ResultSet rs = statement.executeQuery("select  pt.nombre,pf.id_establecimiento,sum(cl.calificacion) as Puntaje from producto_final pf \n"
                    + "inner join calificacion cl on pf.id_producto_final=cl.id_producto_final\n"
                    + "inner join producto pt on pt.id_producto=pf.id_producto\n"
                    + "where pf.id_establecimiento=" + establecimiento + "\n"
                    + "group by pt.nombre,pf.id_establecimiento\n"
                    + "order by Puntaje desc\n"
                    + "LIMIT 5");
            while (rs.next()) {
                ControladorGraficar c = new ControladorGraficar();
                nombre = rs.getString("nombre");
                id_establecimiento = rs.getString("id_establecimiento");
                puntaje = rs.getString("Puntaje");

                c.setId_establecimiento(id_establecimiento);
                c.setNombre(nombre);
                c.setPuntaje(puntaje);
                fotos.add(n, c);
                n++;
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return fotos;
    }

    public List<ControladorGraficarEstablecimiento> graficarEstablecimiento(int establecimiento) {
        String nombre = "";
        String id_establecimiento = "", puntaje = "";
        List<ControladorGraficarEstablecimiento> fotos = new ArrayList();
        int n = 0;
        try {
            ResultSet rs = statement.executeQuery("select  pt.nombre,pf.id_establecimiento,sum(cl.calificacion) as Puntaje from producto_final pf \n"
                    + "inner join calificacion cl on pf.id_producto_final=cl.id_producto_final\n"
                    + "inner join producto pt on pt.id_producto=pf.id_producto\n"
                    + "where pf.id_establecimiento=" + establecimiento + "\n"
                    + "group by pt.nombre,pf.id_establecimiento\n"
                    + "order by Puntaje desc\n"
                    + "LIMIT 5");
            while (rs.next()) {
                ControladorGraficarEstablecimiento c = new ControladorGraficarEstablecimiento();
                nombre = rs.getString("nombre");
                id_establecimiento = rs.getString("id_establecimiento");
                puntaje = rs.getString("Puntaje");

                c.setId_establecimiento(id_establecimiento);
                c.setNombre(nombre);
                c.setPuntaje(puntaje);
                fotos.add(n, c);
                n++;
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return fotos;
    }

    public List<Producto_Final> fotosProdcuto(int id, int tipo) {
        String fotof = "";
        String desc = "", nomb = "";
        String tipoo = "", producto = "", dinero = "";
        String idproducto = "";
        List<Producto_Final> fotos = new ArrayList();
        int n = 0;
        try {
            ResultSet rs = statement.executeQuery("select p.id_producto_final, p.precio, p.id_tipo_producto,pr.nombre as producto,p.descripcion as descripcion, p.foto as foto, e.nombre as nombre from producto_final p\n"
                    + "inner join establecimiento e on p.id_establecimiento=e.id_establecimiento inner join tipo_producto tp\n"
                    + "on tp.id_tipo_producto=p.id_tipo_producto inner join producto pr on pr.id_producto=p.id_producto\n"
                    + "where p.id_establecimiento=" + id + " and p.id_tipo_producto=" + tipo + "");
            while (rs.next()) {
                Producto_Final c = new Producto_Final();
                fotof = rs.getString("foto");
                desc = rs.getString("descripcion");
                nomb = rs.getString("nombre");
                producto = rs.getString("producto");
                tipoo = rs.getString("id_tipo_producto");
                dinero = rs.getString("precio");
                idproducto = rs.getString("id_producto_final");
                c.setDescripcion(desc);
                c.setProducto(producto);
                c.setFoto(fotof);
                c.setNombre(nomb);
                c.setTipo(tipoo);
                c.setDinero(dinero);
                c.setIdproducto(idproducto);
                fotos.add(n, c);
                n++;
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return fotos;
    }

    public List<Controladorsugerencia> sugerir() {
        String fotof = "";
        String nomb = "", idestablecimiento = "", nota = "", descr = "", categoria = "", direccion = "";
        List<Controladorsugerencia> fotos = new ArrayList();
        int n = 0;
        try {
            ResultSet rs = statement.executeQuery("select max(es.calificacion) as nota, es.foto,cat.nombre as categoria,es.direccion,es.id_establecimiento, es.nombre, es.descripcion\n"
                    + "from establecimiento es inner join categoria_establecimiento ct on ct.id_establecimiento=es.id_establecimiento\n"
                    + "inner join categoria cat on cat.id_categoria=ct.id_categoria\n"
                    + "group by es.id_establecimiento,cat.nombre\n"
                    + "order by nota desc\n"
                    + "LIMIT 10");
            while (rs.next()) {
                Controladorsugerencia c = new Controladorsugerencia();
                fotof = rs.getString("foto");
                nomb = rs.getString("nombre");
                idestablecimiento = rs.getString("id_establecimiento");
                descr = rs.getString("descripcion");
                categoria = rs.getString("categoria");
                direccion = rs.getString("direccion");
                c.setDireccion(direccion);
                c.setDescripcion(direccion);
                c.setFoto(fotof);
                c.setIdestablecimiento(idestablecimiento);
                c.setNombre(nomb);
                c.setCategoria(categoria);
                fotos.add(n, c);
                n++;
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return fotos;
    }

    public List<Controladorcategorias> sugerircategoria() {
        String fotof = "", nombreestablecimiento = "", tipoproducto = "", idestablecimiento = "", nombreproducto = "", nota = "";
        List<Controladorcategorias> fotos = new ArrayList();
        int n = 0;
        try {
            ResultSet rs = statement.executeQuery("select tc.id_tipo_producto as tipo,es.nombre as establecimiento,pf.id_establecimiento,pt.nombre,pf.foto,sum(cl.calificacion) as nota\n"
                    + "from producto_final pf \n"
                    + "inner join producto pt on pt.id_producto=pf.id_producto inner join \n"
                    + "establecimiento es on es.id_establecimiento=pf.id_establecimiento\n"
                    + "inner join calificacion cl on cl.id_producto_final=pf.id_producto_final\n"
                    + "inner join tipo_producto tc on tc.id_tipo_producto=pf.id_tipo_producto\n"
                    + "group by establecimiento,pf.id_establecimiento,pt.nombre,pf.foto,tc.id_tipo_producto\n"
                    + "order by nota desc\n"
                    + "LIMIT 10");
            while (rs.next()) {
                Controladorcategorias c = new Controladorcategorias();
                fotof = rs.getString("foto");
                nombreestablecimiento = rs.getString("establecimiento");
                tipoproducto = rs.getString("tipo");
                idestablecimiento = rs.getString("id_establecimiento");
                nombreproducto = rs.getString("nombre");
                nota = rs.getString("nota");

                c.setEstablecimiento(nombreestablecimiento);
                c.setId_establecimiento(idestablecimiento);
                c.setFoto(fotof);
                c.setNota(nota);
                c.setTipo(tipoproducto);
                c.setNombre(nombreproducto);
                fotos.add(n, c);
                n++;
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return fotos;
    }

    public List<ControldorPopulares> recomendaciones(int usuario) {
        ArrayList<Usuario> entrenamiento = new ArrayList<>();
        ArrayList<Usuario> prueba = DatosPrueba();
        boolean bandera = false;
        int n = 0;
        List<ControldorPopulares> products = new ArrayList<>();
        ControldorPopulares pros = new ControldorPopulares();
        try {
            ResultSet rs = statement.executeQuery("select producto_final.id_producto_final,categoria.id_categoria,establecimiento.id_establecimiento,calificacion.calificacion from calificacion,producto_final,tipo_producto,categoria,establecimiento  where calificacion.id_producto_final=producto_final.id_producto_final  and producto_final.id_establecimiento=establecimiento.id_establecimiento and producto_final.id_tipo_producto=tipo_producto.id_tipo_producto and tipo_producto.id_categoria=categoria.id_categoria and calificacion.id_usuario=" + usuario + "");
            while (rs.next()) {
                Usuario pro = new Usuario(rs.getInt("id_producto_final"), rs.getInt("id_categoria"), rs.getInt("id_establecimiento"), rs.getInt("calificacion"));
                entrenamiento.add(pro);
                bandera = true;
            }
            rs.close();

            //Si tiene historial aparecen estas recomendaciones
            int k = 0;
            if (bandera) {
                Recomendacion recon = new Recomendacion();
                double[] resultado = recon.recomendacion(entrenamiento, prueba);
                for (int i = 0; i < resultado.length; i++) {
                    pros = new ControldorPopulares();
                    pros.setProducto_final((int) resultado[i]);
                    products.add(k, pros);
                    k++;
                    //System.out.println("plato : "+String.valueOf()+"  establecimiento : "+String.valueOf(recomen[j][1]));
                }
            } //en caso que no tenga historial mostrar populares
            else {
                double[] resultado = populares();
                for (int i = 0; i < resultado.length; i++) {
                    pros = new ControldorPopulares();
                    pros.setProducto_final((int) resultado[i]);
                    products.add(k, pros);
                    k++;
                    //System.out.println("plato : "+String.valueOf()+"  establecimiento : "+String.valueOf(recomen[j][1]));
                }
            }
            statement.close();
            connection.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return products;
    }

    public ArrayList<Usuario> DatosPrueba() {
        ArrayList<Usuario> prueba = new ArrayList<>();
        int n = 0;
        try {
            ResultSet rs = statement.executeQuery("select producto_final.id_producto_final,categoria.id_categoria,establecimiento.id_establecimiento, 0 as calificacion from producto_final,producto,tipo_producto,categoria,establecimiento where producto_final.id_producto=producto.id_producto and producto_final.id_tipo_producto=tipo_producto.id_tipo_producto and tipo_producto.id_categoria=categoria.id_categoria and producto_final.id_establecimiento=establecimiento.id_establecimiento");
            while (rs.next()) {
                Usuario pro = new Usuario(rs.getInt("id_producto_final"), rs.getInt("id_categoria"), rs.getInt("id_establecimiento"), rs.getInt("calificacion"));
                prueba.add(pro);
            }
            rs.close();
            //  statement.close();
            // connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return prueba;
    }

    public double[] populares() {
        double[] popular;
        double[] resultado = new double[0];
        ArrayList<Usuario> populares = new ArrayList<>();
        int n = 0;
        try {
            ResultSet rs = statement.executeQuery("select producto_final.id_producto_final,categoria.id_categoria,establecimiento.id_establecimiento,round(AVG(calificacion.calificacion)) as calificacion "
                    + "from calificacion,producto_final,tipo_producto,categoria,establecimiento  "
                    + "where calificacion.id_producto_final=producto_final.id_producto_final  and "
                    + "producto_final.id_establecimiento=establecimiento.id_establecimiento and "
                    + "producto_final.id_tipo_producto=tipo_producto.id_tipo_producto and "
                    + "tipo_producto.id_categoria=categoria.id_categoria and calificacion.calificacion >= 4 "
                    + "group by producto_final.id_producto_final,categoria.id_categoria,establecimiento.id_establecimiento");
            while (rs.next()) {
                Usuario pro = new Usuario(rs.getInt("id_producto_final"), rs.getInt("id_categoria"), rs.getInt("id_establecimiento"), rs.getInt("calificacion"));
                populares.add(pro);
            }
            popular = new double[populares.size()];
            for (int i = 0; i < populares.size(); i++) {
                popular[i] = Double.valueOf(populares.get(i).getEstablecimiento());
                // popular[i][0]=Double.valueOf(populares.get(i).getPlato());
            }
            rs.close();
            statement.close();
            connection.close();
            return popular;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return resultado;
    }

    public List<ControladorPopu> sugerirPopulares(List<ControldorPopulares> control) {
        String fotof = "";
        ResultSet rs = null;
        String nombestablecimiento = "", idestablecimiento = "", nombreproducto = "", categoria = "";
        List<ControladorPopu> fotos = new ArrayList();
        int n = 0;
        try {

            for (int i = 0; i < control.size(); i++) {

                rs = statement.executeQuery("select tc.id_tipo_producto as tipo,es.nombre as establecimiento,pf.id_establecimiento,pt.nombre,pf.foto\n"
                        + "from producto_final pf \n"
                        + "inner join producto pt on pt.id_producto=pf.id_producto inner join \n"
                        + "establecimiento es on es.id_establecimiento=pf.id_establecimiento\n"
                        + "inner join tipo_producto tc on tc.id_tipo_producto=pf.id_tipo_producto where pf.id_producto_final=" + control.get(i).getProducto_final() + "");
                while (rs.next()) {
                    ControladorPopu c = new ControladorPopu();
                    fotof = rs.getString("foto");
                    nombestablecimiento = rs.getString("establecimiento");
                    idestablecimiento = rs.getString("id_establecimiento");
                    nombreproducto = rs.getString("nombre");
                    categoria = rs.getString("tipo");
                    c.setFoto(fotof);
                    c.setEstablecimiento(nombestablecimiento);
                    c.setIdestablecimiento(idestablecimiento);
                    c.setNombre(nombreproducto);
                    c.setTipo(categoria);
                    fotos.add(n, c);
                    n++;
                }
            }

            rs.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return fotos;
    }

    public List<ControladorUltimos> sugerirUltimos() {
        String fotof = "";
        ResultSet rs = null;
        String nombestablecimiento = "", idestablecimiento = "", nombreproducto = "", categoria = "";
        List<ControladorUltimos> fotos = new ArrayList();
        int n = 0;
        try {
            rs = statement.executeQuery("select tc.id_tipo_producto as tipo,es.nombre as establecimiento,pf.id_establecimiento,pt.nombre,pf.foto\n"
                    + "from producto_final pf \n"
                    + "inner join producto pt on pt.id_producto=pf.id_producto inner join \n"
                    + "establecimiento es on es.id_establecimiento=pf.id_establecimiento\n"
                    + "inner join tipo_producto tc on tc.id_tipo_producto=pf.id_tipo_producto\n"
                    + "order by pf.id_producto_final desc\n"
                    + "Limit 20");
            while (rs.next()) {
                ControladorUltimos c = new ControladorUltimos();
                fotof = rs.getString("foto");
                nombestablecimiento = rs.getString("establecimiento");
                idestablecimiento = rs.getString("id_establecimiento");
                nombreproducto = rs.getString("nombre");
                categoria = rs.getString("tipo");
                c.setFoto(fotof);
                c.setEstablecimiento(nombestablecimiento);
                c.setIdestablecimiento(idestablecimiento);
                c.setNombre(nombreproducto);
                c.setTipo(categoria);
                fotos.add(n, c);
                n++;
            }

            rs.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return fotos;
    }

    public boolean insertar_establecimiento(int usuario, String nombre, String direccion, String telefono, String whatsapp, String descripcion, String latitud, String longitud, String foto) {
        boolean bandera = true;
        boolean verificacion = false;
        try {
            if (validarestablecimiento(usuario)) {
                statement = connection.createStatement();
                statement.executeQuery("insert into establecimiento(id_usuario,nombre,direccion,telefono,whatsapp,descripcion,latitud,longitud,verificado,foto) values(" + usuario + ",'" + nombre + "','" + direccion + "','" + telefono + "','" + whatsapp + "','" + descripcion + "','" + latitud + "','" + longitud + "'," + verificacion + ",'" + foto + "')");
                statement.close();
                connection.close();
            } else {

                bandera = false;
            }
        } catch (SQLException e) {
        }
        return bandera;
    }

    public boolean validarestablecimiento(int usuario) {
        boolean bandera = true;
        try {
            ResultSet rs = statement.executeQuery("select * from establecimiento where id_usuario=" + usuario + "");
            while (rs.next()) {
                bandera = false;
            }
            rs.close();
            // statement.close();
            // connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return bandera;
    }

}
