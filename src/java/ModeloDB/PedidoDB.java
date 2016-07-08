package ModeloDB;

import BasedeDatos.ConnectionPool;
import Modelo.Pedido;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Roberto
 */
public class PedidoDB {

    public static int insertar(Pedido pedidoNuevo) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query
                    = "INSERT INTO PEDIDO (ID_CLIENTE, FECHA_ALTA) VALUES (?, ?)";

        try {
            ps = connection.prepareStatement(query);
                ps.setInt(1, pedidoNuevo.getIdCliente());
            ps.setDate(2, pedidoNuevo.getFechaAlta());
            
            int res = ps.executeUpdate();
            ps.close();
            pool.freeConnection(connection);
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static Pedido getPedidoPorIdCliente(int idCliente) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        Pedido pedido = null;
        String query = "SELECT * FROM PEDIDO WHERE ID_CLIENTE = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, idCliente);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                pedido = new Pedido(rs.getInt("ID"),
                        rs.getInt("ID_CLIENTE"),
                        rs.getDate("FECHA_ALTA"));
            }
            ps.close();
            pool.freeConnection(connection);

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return pedido;
    }

    /*public static List<Producto> buscarProducto(String s) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        List<Producto> resultado = new ArrayList<Producto>();
        String query = "SELECT DISTINCT a.* FROM PRODUCTO a WHERE ((a.NOMBRE = ?) OR (a.ANIMAL = ?) OR (a.CATEGORIA = ?))";

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
    }
    
    public static ArrayList<Producto> buscarProductosPorValoracion(int i) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ArrayList<Producto> resultado = new ArrayList<Producto>();
        String query = "SELECT * FROM PRODUCTOS WHERE VALORACION = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1,i);
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
    }
    
    public static Producto getProductoPorId(int idProducto) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        Producto producto = null;
        String query = "SELECT * FROM PRODUCTO WHERE ID= ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, idProducto);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                producto = new Producto(rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("diario"),
                        rs.getDouble("precio"),
                        rs.getString("imagen"),
                        rs.getInt("valoracion"),
                        rs.getString("animal"),
                        rs.getString("categoria"));
            }
            ps.close();
            pool.freeConnection(connection);

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return producto;
    }*/

    public static int modificarProducto(int id,
            int idCliente,
            Date fechaAlta) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps;
        String query
                = "UPDATE PEDIDO SET ID_CLIENTE = ? , FECHA_ALTA = ?  WHERE id=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, idCliente);
            ps.setDate(2, fechaAlta);
            ps.setInt(3, id);
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
