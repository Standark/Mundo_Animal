package ModeloDB;

import BasedeDatos.ConnectionPool;
import Modelo.DetPed;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Roberto
 */
public class DetPedDB {

    public static int insertar(DetPed detPedNuevo) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query
                = "INSERT INTO DETPED (ID, ID_PEDIDO, ID_PRODUCTO, CANTIDAD, PRECIO)"
                + " VALUES (?, ?, ?, ?)";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, detPedNuevo.getIdPedido());
            ps.setInt(2, detPedNuevo.getIdProducto());
            ps.setInt(3, detPedNuevo.getCantidad());
            ps.setDouble(4, detPedNuevo.getPrecio());
            int res = ps.executeUpdate();
            ps.close();
            pool.freeConnection(connection);
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

/*    public static DetPed getProductoPorNombre(String nombreProducto) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        DetPed detPed = null;
        String query = "SELECT * FROM DETPED WHERE NOMBRE = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, nombreProducto);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                detPed = new DetPed(rs.getInt("id"),
                        rs.getInt("id_precio"),
                        rs.getInt("id_producto"),
                        rs.getInt("cantidad"),
                        rs.getDouble("precio"));
            }
            ps.close();
            pool.freeConnection(connection);

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return detPed;
    }

    public static List<DetPed> buscarProducto(String s) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        List<DetPed> resultado = new ArrayList<DetPed>();
        String query = "SELECT DISTINCT a.* FROM DETPED a WHERE ((a.ID_PEDIDO = ?)"
                + " OR (a.ANIMAL = ?) OR (a.CATEGORIA = ?))";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1,s);
            ps.setString(2,s);
            ps.setString(3,s);
            ResultSet res = ps.executeQuery();
            while(res.next()){
                Producto producto = new Producto(res.getInt("id"),
                        res.getString("nombre"),
                        res.getString("descripcion"),
                        res.getDouble("precio"),
                        res.getString("imagen"),
                        res.getInt("valoracion"),
                        res.getString("animal"),
                        res.getString("categoria"));
                resultado.add(producto);
            }
            ps.close();
            pool.freeConnection(connection);
            return resultado;
        } catch (SQLException e) {
            e.printStackTrace();
            return resultado;
        }
    }*/
    
    public static ArrayList<DetPed> buscarDetPedPorPedido(int i) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ArrayList<DetPed> resultado = new ArrayList<DetPed>();
        String query = "SELECT * FROM DETPED WHERE ID_PEDIDO = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1,i);
            ResultSet res = ps.executeQuery();
            while(res.next()){
                DetPed producto = new DetPed(res.getInt("id"),
                        res.getInt("id_pedido"),
                        res.getInt("id_producto"),
                        res.getInt("cantidad"),
                        res.getDouble("precio"));
                resultado.add(producto);
            }
            ps.close();
            pool.freeConnection(connection);
            return resultado;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return resultado;
        }
    }
    
    public static DetPed getDetPedPorId(int idDetPed) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        DetPed detPed = null;
        String query = "SELECT * FROM DETPED WHERE ID= ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, idDetPed);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                detPed = new DetPed(rs.getInt("id"),
                        rs.getInt("id_pedido"),
                        rs.getInt("id_producto"),
                        rs.getInt("cantidad"),
                        rs.getDouble("precio"));
            }
            ps.close();
            pool.freeConnection(connection);

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return detPed;
    }

    public static int modificarDetPed(int id,
            int idPedido,
            int idProducto,
            int cantidad,
            double precio) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps;
        String query
                = "UPDATE DETPED SET ID_PED = ? , ID_PRODUCTO = ? , CANTIDAD = ? , PRECIO = ? "
                + "WHERE ID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, idPedido);
            ps.setInt(2, idProducto);
            ps.setInt(3, cantidad);
            ps.setDouble(4, precio);
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
/* es posible que haya que borrarlo
    public static String getNombredelProducto(int idProducto) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String titulo = null;
        String query = "SELECT * FROM PRODUCTO WHERE ID = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, idProducto);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                titulo = rs.getString("nombre");
            }
            ps.close();
            pool.freeConnection(connection);

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return titulo;
    }*/
}
