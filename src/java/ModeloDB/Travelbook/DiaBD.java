
package ModeloDB.Travelbook;

import baseDatos.ConnectionPool;
import dominio.Dia;
import dominio.Diario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utilidades.TratamientoFechas;


public class DiaBD {
    
    
    public static List<Dia> getDiasDelDiario(Diario diario) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        List<Dia> resultado = new ArrayList<Dia>();
        String query = "SELECT * FROM dia WHERE idDiario = ? ORDER BY fecha";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, diario.getId());
            ResultSet res = ps.executeQuery();
            while(res.next()){
                Dia dia = new Dia(res.getInt("id"), res.getInt("idDiario"), res.getDate("fecha"), res.getString("titulo"), res.getString("descripcion"));
                resultado.add(dia);
            }
            ps.close();
            pool.freeConnection(connection);
            return resultado;
        } catch (SQLException e) {
            e.printStackTrace();
            return resultado;
        }
    }
    
    public static List<Dia> getDiasDelDiarioPorId(int idDiario) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        List<Dia> resultado = new ArrayList<Dia>();
        String query = "SELECT * FROM dia WHERE idDiario = ? ORDER BY fecha";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, idDiario);
            ResultSet res = ps.executeQuery();
            while(res.next()){
                Dia dia = new Dia(res.getInt("id"), res.getInt("idDiario"), res.getDate("fecha"), res.getString("titulo"), res.getString("descripcion"));
                resultado.add(dia);
            }
            ps.close();
            pool.freeConnection(connection);
            return resultado;
        } catch (SQLException e) {
            e.printStackTrace();
            return resultado;
        }
    }
    
    public static Dia getDia(String id){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        Dia resultado = null;
        String query = "SELECT * FROM dia WHERE id = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, id);
            ResultSet res = ps.executeQuery();
            if(res.next()){
                resultado = new Dia(res.getInt("id"), res.getInt("idDiario"), res.getDate("fecha"), res.getString("titulo"), res.getString("descripcion"));
            }
            ps.close();
            pool.freeConnection(connection);
            return resultado;
        } catch (SQLException e) {
            e.printStackTrace();
            return resultado;
        }
    }

    public static int update(Dia dia) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "UPDATE dia SET fecha = ?, titulo = ?, descripcion = ? WHERE id=?";

        try {
            ps = connection.prepareStatement(query);
            ps.setDate(1, TratamientoFechas.dateToSqlDate(dia.getFecha()));
            ps.setString(2, dia.getTitulo());
            ps.setString(3, dia.getDescripcion());
            ps.setInt(4, dia.getId());
            int res = ps.executeUpdate();

            ps.close();
            pool.freeConnection(connection);
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int insert(Dia dia) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "INSERT INTO dia (idDiario, fecha, titulo, descripcion) VALUES (?, ?, ?, ?)";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, dia.getIdDiario());
            ps.setDate(2, TratamientoFechas.dateToSqlDate(dia.getFecha()));
            ps.setString(3, dia.getTitulo());
            ps.setString(4, dia.getDescripcion());
            int res = ps.executeUpdate();
            
            

            ps.close();
            pool.freeConnection(connection);
            
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
        
        
        pool = ConnectionPool.getInstance();
        connection = pool.getConnection();
        ps = null;
        Dia resultado = null;
        int resultad = -1;
        query = "SELECT * FROM dia WHERE idDiario = ? AND fecha = ? AND titulo = ? AND descripcion = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, dia.getIdDiario());
            ps.setDate(2, TratamientoFechas.dateToSqlDate(dia.getFecha()));
            ps.setString(3, dia.getTitulo());
            ps.setString(4, dia.getDescripcion());
            ResultSet res = ps.executeQuery();
            if(res.next()){
               resultad = res.getInt("id");
            }
            ps.close();
            pool.freeConnection(connection);
            return resultad;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static boolean isDiaEnDiario(String fechaDia, int idDiario) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        boolean resultado = false;
        String query = "SELECT * FROM dia WHERE idDiario = ? AND fecha = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, idDiario);
            ps.setDate(2, TratamientoFechas.dateToSqlDate(TratamientoFechas.stringToDate(fechaDia)));
            ResultSet res = ps.executeQuery();
            if(res.next()){
                resultado = true;
            }
            ps.close();
            pool.freeConnection(connection);
            return resultado;
        } catch (SQLException e) {
            e.printStackTrace();
            return resultado;
        }
    }
}
