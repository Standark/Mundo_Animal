/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloDB.Travelbook;

import baseDatos.ConnectionPool;
import dominio.Categoria;
import dominio.MediaLugar;
import dominio.Opiniones;
import dominio.PuntuacionCategoria;
import dominio.Valoracion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utilidades.TratamientoFechas;

/**
 *
 * @author Grupo 13 - Sistemas y servicios Web
 */
public class ValoracionBD {

    /**
     *
     * @param busqueda
     * @return el lugar y la media global de ese lugar, donde lugar debe ser
     * igual a la busqueda introducida por el usuario
     */
    public static List<MediaLugar> buscarValoraciones(String busqueda) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        List<MediaLugar> valoracionLugar = new ArrayList<MediaLugar>();
        String query = "SELECT Distinct v.lugar, avg(v.PUNTUACION) AS media FROM valoracion v WHERE (v.lugar=?) OR (v.ciudad=?) group by (v.lugar) order by media DESC ";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, busqueda);
            ps.setString(2, busqueda);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MediaLugar m = new MediaLugar(
                        rs.getString("lugar"),
                        rs.getDouble(2)
                );
                valoracionLugar.add(m);
            }
            ps.close();
            pool.freeConnection(connection);

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return valoracionLugar;
    }

    /**
     *
     * @param ciudad
     * @return los lugares y la media global de ellos de la ciudad y la categoría seleccionada por el usuario
     */
    public static List<MediaLugar> buscarValoracionesLugarCategoria(String ciudad, String categoria) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        List<MediaLugar> valoracionLugar = new ArrayList<MediaLugar>();
        String query = "SELECT Distinct v.lugar, avg(v.PUNTUACION) AS media FROM valoracion v WHERE (v.ciudad=?) and (v.categoria=?) group by (v.lugar) order by media DESC ";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, ciudad);
            ps.setString(2, categoria);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MediaLugar m = new MediaLugar(
                        rs.getString("lugar"),
                        rs.getDouble(2)
                );
                valoracionLugar.add(m);
            }
            ps.close();
            pool.freeConnection(connection);

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return valoracionLugar;
    }
    
    /**
     *
     * @param lugar seleccionado por el usuario para conocer las opiniones y
     * puntuaciones escritas por otros usuarios
     * @return todas las opiniones de un determinado lugar, con su recomendacion
     * y su puntuacion por categoria.
     */
    public static List<Opiniones> buscarOpiniones(String lugar) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        List<Opiniones> opinion = new ArrayList<Opiniones>();
        String query = "SELECT Distinct v.FECHAVALORACION, v.NOMBREUSUARIO, v.LUGAR, v.CIUDAD, v.PAIS, vi.RECOMENDACION\n"
                + " from valoracion v, visitas vi, USUARIOREGISTRADO u, DIARIO d, DIA di \n"
                + " where v.LUGAR=? and vi.LUGAR=? and v.NOMBREUSUARIO=u.nombreUsuario and u.NOMBREUSUARIO=d.NOMBREUSUARIO and d.ID=di.IDDIARIO and di.ID=vi.IDDIA";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, lugar);
            ps.setString(2, lugar);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Opiniones o = new Opiniones();
                List<PuntuacionCategoria> puntCat = new ArrayList<PuntuacionCategoria>();
                o.setNombreUsuario(rs.getString("nombreUsuario"));
                o.setFechaValoracion(rs.getDate("fechaValoracion"));
                //new PuntuacionCategoria(Categoria.fromString(rs.getString("categoria")), rs.getFloat("puntuacion")),
                o.setRecomendacion(rs.getString("recomendacion"));
                o.setLugar(rs.getString("lugar"));
                o.setCiudad(rs.getString("ciudad"));
                o.setPais(rs.getString("pais"));
                String query2 = "SELECT v.categoria, v.puntuacion\n"
                        + " from valoracion v \n"
                        + " where v.NOMBREUSUARIO=? and v.FECHAVALORACION=? and v.LUGAR=? and v.CIUDAD=? and v.PAIS=?";
                try {
                    ps2 = connection.prepareStatement(query2);
                    ps2.setString(1, rs.getString("nombreUsuario"));
                    ps2.setDate(2, rs.getDate("fechaValoracion"));
                    ps2.setString(3, rs.getString("lugar"));
                    ps2.setString(4, rs.getString("ciudad"));
                    ps2.setString(5, rs.getString("pais"));
                    ResultSet rs2 = ps2.executeQuery();
                    while (rs2.next()) {
                        //Crear la lista PuntuacionCategoria
                        PuntuacionCategoria pc = new PuntuacionCategoria(Categoria.fromString(rs2.getString("categoria")), rs2.getFloat("puntuacion"));
                        puntCat.add(pc);
                    }
                    o.setCategoria(puntCat);
                    ps2.close();
                    //pool.freeConnection(connection);
                } catch (SQLException e) {
                    e.printStackTrace();

                }
                opinion.add(o);
            }
            ps.close();
            pool.freeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return opinion;
    }

    public static int borrarValoracionesDeVisita(Valoracion valoracion) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "DELETE FROM valoracion WHERE nombreUsuario = ? AND lugar = ? AND ciudad = ? AND pais = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, valoracion.getNombreUsuario());
            ps.setString(2, valoracion.getLugar());
            ps.setString(3, valoracion.getCiudad());
            ps.setString(4, valoracion.getPais());
            int res = ps.executeUpdate();

            ps.close();
            pool.freeConnection(connection);
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static List<MediaLugar> obtenerRanking() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        List<MediaLugar> ranking = new ArrayList<MediaLugar>();
        String query = "SELECT Distinct v.ciudad, avg(v.PUNTUACION) AS media FROM valoracion v group by (v.ciudad) "
                + "order by media DESC ";
        try {
            ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MediaLugar m = new MediaLugar(
                        rs.getString("ciudad"),
                        rs.getDouble(2)
                );
                ranking.add(m);
            }
            ps.close();
            pool.freeConnection(connection);

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return ranking;
    }

    public static ArrayList<PuntuacionCategoria> obtenerValoracionesDia(String lugar, String ciudad, String pais, String nombreUsuario, int idDia) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ArrayList<PuntuacionCategoria> listaValoraciones = new ArrayList<PuntuacionCategoria>();
        String query = "SELECT Distinct val.* FROM valoracion val,usuarioRegistrado usu,diario diar,dia d WHERE val.lugar = ?"
                + " AND val.ciudad = ? AND val.pais = ? and val.nombreUsuario = ? AND val.nombreUsuario = usu.nombreUsuario AND"
                + " usu.nombreUsuario = diar.nombreUsuario AND diar.id = d.idDiario AND d.id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, lugar);
            ps.setString(2, ciudad);
            ps.setString(3, pais);
            ps.setString(4, nombreUsuario);
            ps.setInt(5, idDia);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PuntuacionCategoria pc = new PuntuacionCategoria(
                        Categoria.fromString(rs.getString("categoria")),
                        rs.getFloat("puntuacion")
                );
                listaValoraciones.add(pc);
            }
            ps.close();
            pool.freeConnection(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaValoraciones;
    }

    public static List<MediaLugar> buscarValoracionesCategoria(String busqueda) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        List<MediaLugar> valoracionCiudad = new ArrayList<MediaLugar>();
        String query = "SELECT Distinct v.ciudad, avg(v.PUNTUACION) AS media FROM valoracion v WHERE v.CATEGORIA=? group by (v.ciudad) order by media DESC ";
         try {
            ps = connection.prepareStatement(query);
            ps.setString(1,busqueda);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MediaLugar m = new MediaLugar(  
                    rs.getString("ciudad"),  
                    rs.getDouble(2)
                    );
                valoracionCiudad.add(m);
            }
            ps.close();
            pool.freeConnection(connection);

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return valoracionCiudad;
    }

    public static int insertar(Valoracion valoracion) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps;


        String query = "INSERT INTO valoracion (fechaValoracion, nombreUsuario, lugar, ciudad, pais, categoria, puntuacion) VALUES (?, ?, ?, ? , ?, ?, ?)";

        try {
            ps = connection.prepareStatement(query);

            ps.setDate(1, TratamientoFechas.dateToSqlDate(valoracion.getFechaValoracion()));
            ps.setString(2, valoracion.getNombreUsuario());
            ps.setString(3, valoracion.getLugar());
            ps.setString(4, valoracion.getCiudad());
            ps.setString(5, valoracion.getPais());
            ps.setString(6, valoracion.getCategoria().getText());
            ps.setDouble(7, valoracion.getPuntuacion());

            int res = ps.executeUpdate();

            ps.close();
            pool.freeConnection(connection);
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
