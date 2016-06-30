package ModeloDB.Travelbook;

import baseDatos.ConnectionPool;
import dominio.Dia;
import dominio.Diario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utilidades.TratamientoFechas;

/**
 * @author Grupo 13 - Sistemas y servicios Web
 */
public class DiarioBD {

    public static int insertar(Diario diarioNuevo) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query
                = "INSERT INTO DIARIO (nombreUsuario, titulo, fechaInicio, fechaFin, descripcion, enCurso) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, diarioNuevo.getNombreUsuario());
            ps.setString(2, diarioNuevo.getTitulo());
            ps.setDate(3, TratamientoFechas.dateToSqlDate(diarioNuevo.getFechaInicio()));
            ps.setDate(4, TratamientoFechas.dateToSqlDate(diarioNuevo.getFechaFin()));
            ps.setString(5, diarioNuevo.getDescripcion());
            ps.setBoolean(6, diarioNuevo.isEnCurso());
            int res = ps.executeUpdate();
            ps.close();
            pool.freeConnection(connection);
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static Diario getDiarioEnCursoDe(String nombreUsuario) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        Diario diarioEnCurso = null;
        String query = "SELECT * FROM diario WHERE nombreUsuario = ? AND enCurso = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, nombreUsuario);
            ps.setBoolean(2,true);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                diarioEnCurso = new Diario(rs.getInt("id"),
                        rs.getString("nombreUsuario"),
                        rs.getString("titulo"),
                        rs.getDate("fechaInicio"),
                        rs.getDate("fechaFin"),
                        rs.getString("descripcion"),
                        rs.getBoolean("enCurso"));
            }
            ps.close();
            pool.freeConnection(connection);

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return diarioEnCurso;
    }

    public static int finalizarDiarioEnCursoDe(String nombreUsuario) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps;
        String query
                = "UPDATE diario SET enCurso = ? WHERE nombreUsuario = ? AND enCurso = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setBoolean(1, false);
            ps.setString(2, nombreUsuario);
            ps.setBoolean(3, true);
            int res = ps.executeUpdate();
            ps.close();
            pool.freeConnection(connection);
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static List<Diario> buscarDiarios(String s) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        List<Diario> resultado = new ArrayList<Diario>();
        String query = "SELECT DISTINCT a.* FROM dia d, diario a, visitas v WHERE ((a.nombreUsuario = ?) OR (v.pais = ? AND v.idDia = d.id AND d.idDiario = a.id) OR (v.ciudad = ? AND v.idDia = d.id AND d.idDiario = a.id) OR (v.lugar = ? AND v.idDia = d.id AND d.idDiario = a.id)) AND a.enCurso=false ORDER BY a.fechaInicio";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1,s);
            ps.setString(2,s);
            ps.setString(3,s);
            ps.setString(4,s);
            ResultSet res = ps.executeQuery();
            while(res.next()){
                Diario diario = new Diario(res.getInt("id"), res.getString("nombreUsuario"), res.getString("titulo"), res.getDate("fechaInicio"), res.getDate("fechaFin"), res.getString("descripcion"), res.getBoolean("enCurso"));
                resultado.add(diario);
            }
            ps.close();
            pool.freeConnection(connection);
            return resultado;
        } catch (SQLException e) {
            e.printStackTrace();
            return resultado;
        }
    }
    
    public static ArrayList<Diario> buscarDiariosPublicadosPorNombre(String s) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ArrayList<Diario> resultado = new ArrayList<Diario>();
        String query = "SELECT * FROM diario WHERE nombreUsuario = ? AND enCurso=false";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1,s);
            ResultSet res = ps.executeQuery();
            while(res.next()){
                Diario diario = new Diario(res.getInt("id"), res.getString("nombreUsuario"), res.getString("titulo"), res.getDate("fechaInicio"), res.getDate("fechaFin"), res.getString("descripcion"), res.getBoolean("enCurso"));
                resultado.add(diario);
            }
            ps.close();
            pool.freeConnection(connection);
            return resultado;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return resultado;
        }
    }
    
    public static Diario getDiarioPorId(int idDiario) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        Diario diario = null;
        String query = "SELECT * FROM diario WHERE id= ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, idDiario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                diario = new Diario(rs.getInt("id"),
                        rs.getString("nombreUsuario"),
                        rs.getString("titulo"),
                        rs.getDate("fechaInicio"),
                        rs.getDate("fechaFin"),
                        rs.getString("descripcion"),
                        rs.getBoolean("enCurso"));
            }
            ps.close();
            pool.freeConnection(connection);

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return diario;
    }

    public static int modificarDiario(int id, String tituloViaje, Date fechaInicio, Date fechaFin, String descripcion) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps;
        String query
                = "UPDATE diario SET titulo = ? , fechaInicio = ? , fechaFin = ? "
                + ", descripcion = ? WHERE id=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, tituloViaje);
            ps.setDate(2, fechaInicio);
            ps.setDate(3, fechaFin);
            ps.setString(4, descripcion);
            ps.setInt(5, id);
            int res = ps.executeUpdate();
            ps.close();
            pool.freeConnection(connection);
            return res;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String getTituloDelDiario(int idDiario) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String titulo = null;
        String query = "SELECT * FROM diario WHERE id= ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, idDiario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                titulo = rs.getString("titulo");
            }
            ps.close();
            pool.freeConnection(connection);

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return titulo;
    }
}
