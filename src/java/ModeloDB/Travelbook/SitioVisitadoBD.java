package ModeloDB.Travelbook;

import baseDatos.ConnectionPool;
import dominio.SitioVisitado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SitioVisitadoBD {

    public static int insertar(SitioVisitado sitioVisitado) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "INSERT INTO sitioVisitado (lugar, ciudad, pais) VALUES (?, ?, ?)";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1,sitioVisitado.getLugar());
            ps.setString(2, sitioVisitado.getCiudad());
            ps.setString(3, sitioVisitado.getPais());

            int res = ps.executeUpdate();

            ps.close();
            pool.freeConnection(connection);
            return res;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public static List<SitioVisitado> buscarLugares(String busqueda) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        List<SitioVisitado> lugar = new ArrayList<SitioVisitado>();
        String query = "SELECT * FROM sitioVisitado WHERE (lugar=?) OR (ciudad=?) ";
         try {
            ps = connection.prepareStatement(query);
            ps.setString(1,busqueda);
            ps.setString(2,busqueda);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SitioVisitado s = new SitioVisitado(
                    rs.getString("lugar"),  
                    rs.getString("ciudad"),  
                    rs.getString("pais")
                    );
                lugar.add(s);
            }
            ps.close();
            pool.freeConnection(connection);

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return lugar;
    }
}
