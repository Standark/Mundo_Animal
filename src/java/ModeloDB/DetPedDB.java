package ModeloDB;

import BasedeDatos.ConnectionPool;
import Modelo.DetPed;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



/**
 * @author Roberto
 */
public class DetPedDB {

    public static int insertar(DetPed detPedNuevo) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query
                = "INSERT INTO DETPED (ID_PEDIDO, ID_PRODUCTO, CANTIDAD, PRECIO)"
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

   
    public static ArrayList<DetPed> buscarDetPedPorPedido(int idProducto) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ArrayList<DetPed> resultado = new ArrayList<DetPed>();
        String query = "SELECT * FROM DETPED WHERE ID_PEDIDO = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1,idProducto);
            ResultSet res = ps.executeQuery();
            while(res.next()){
                DetPed producto = new DetPed(res.getInt("ID"),
                        res.getInt("ID_PEDIDO"),
                        res.getInt("IDPRODUCTO"),
                        res.getInt("CANTIDAD"),
                        res.getDouble("PRECIO"));
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
        String query = "SELECT * FROM DET_PED WHERE ID= ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, idDetPed);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                detPed = new DetPed(rs.getInt("ID"),
                        rs.getInt("ID_PEDIDO"),
                        rs.getInt("ID_PRODUCTO"),
                        rs.getInt("CANTIDAD"),
                        rs.getDouble("PRECIO"));
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

    public static ArrayList<DetPed> buscarDetPedPorCliente(int idCliente) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ArrayList<DetPed> resultado = new ArrayList<DetPed>();
        String query = "SELECT * FROM PEDIDO WHERE ID_CLIENTE = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1,idCliente);
            ResultSet res = ps.executeQuery();
            while(res.next()){
                resultado.add(DetPedDB.getDetPedPorId(res.getInt("ID_PEDIDO")));
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
