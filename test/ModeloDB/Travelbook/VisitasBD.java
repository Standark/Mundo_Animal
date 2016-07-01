package ModeloDB.Travelbook;

import baseDatos.ConnectionPool;
import dominio.Dia;
import dominio.Visitas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utilidades.TratamientoFechas;

public class VisitasBD {

    public static List<Visitas> getVisitasDelDia(String idDia) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        List<Visitas> resultado = new ArrayList<Visitas>();
        String query = "SELECT * FROM visitas WHERE idDia = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, idDia);
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                Visitas visita = new Visitas(res.getInt("idDia"), res.getString("lugar"), res.getString("ciudad"), res.getString("pais"), res.getDate("fechaRecomendacion"), res.getString("recomendacion"));
                resultado.add(visita);
            }
            ps.close();
            pool.freeConnection(connection);
            return resultado;
        } catch (SQLException e) {
            e.printStackTrace();
            return resultado;
        }
    }

    public static Visitas getVisitasDelDia(int idDia, String lugar, String ciudad, String pais) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        Visitas resultado = null;
        String query = "SELECT * FROM visitas WHERE idDia = ? AND lugar = ? AND ciudad = ? AND pais = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, idDia);
            ps.setString(2, lugar);
            ps.setString(3, ciudad);
            ps.setString(4, pais);
            ResultSet res = ps.executeQuery();
            if (res.next()) {
                resultado = new Visitas(res.getInt("idDia"), res.getString("lugar"), res.getString("ciudad"), res.getString("pais"), res.getDate("fechaRecomendacion"), res.getString("recomendacion"));
            }
            ps.close();
            pool.freeConnection(connection);
            return resultado;
        } catch (SQLException e) {
            e.printStackTrace();
            return resultado;
        }
    }

    public static int insertar(Visitas visita) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "INSERT INTO visitas (idDia, lugar, ciudad, pais, fechaRecomendacion, recomendacion) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            ps = connection.prepareStatement(query);

            ps.setInt(1, visita.getIdDia());
            ps.setString(2, visita.getLugar());
            ps.setString(3, visita.getCiudad());
            ps.setString(4, visita.getPais());
            ps.setDate(5, TratamientoFechas.dateToSqlDate(visita.getFechaRecomendacion()));
            ps.setString(6, visita.getRecomendacion());

            int res = ps.executeUpdate();

            ps.close();
            pool.freeConnection(connection);
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int borrar(Visitas v) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "DELETE FROM visitas WHERE idDia = ? AND lugar = ? AND ciudad = ? AND pais = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, v.getIdDia());
            ps.setString(2, v.getLugar());
            ps.setString(3, v.getCiudad());
            ps.setString(4, v.getPais());
            int res = ps.executeUpdate();

            ps.close();
            pool.freeConnection(connection);
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String getPaisDeVisita(int idDiario){
        String pais = null;
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "SELECT DISTINCT vi.pais FROM visitas vi, dia di, diario d WHERE vi.idDia = di.id AND di.idDiario = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, idDiario);
            ResultSet res = ps.executeQuery();
            if (res.next()) {
                pais = res.getString("pais");
            }
            ps.close();
            pool.freeConnection(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pais;
    }

    public static List<Visitas> getVisitasSinValorarDelDia(String nombreUsuario, int idDia) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps;
        List<Visitas> vis = new ArrayList<>();
        String query = "SELECT DISTINCT vi.* FROM visitas vi, dia di, diario d WHERE vi.fechaRecomendacion IS NULL AND vi.idDia = ? AND vi.idDia = di.id AND di.idDiario = d.id AND d.nombreUsuario = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, idDia);
            ps.setString(2, nombreUsuario);
            ResultSet res = ps.executeQuery();
            while(res.next()) {
                vis.add(new Visitas(res.getInt("idDia"), res.getString("lugar"), res.getString("ciudad"), res.getString("pais"), res.getDate("fechaRecomendacion"), res.getString("recomendacion")));
            }
            ps.close();
            pool.freeConnection(connection);
            return vis;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vis;
    }

    public static int update(Visitas visita) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "UPDATE visitas SET fechaRecomendacion = ?, recomendacion = ? WHERE idDia=? AND lugar = ? AND ciudad = ? AND pais = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setDate(1, TratamientoFechas.dateToSqlDate(visita.getFechaRecomendacion()));
            ps.setString(2, visita.getRecomendacion());
            ps.setInt(3, visita.getIdDia());
            ps.setString(4, visita.getLugar());
            ps.setString(5, visita.getCiudad());
            ps.setString(6, visita.getPais());
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
