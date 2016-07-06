package ModeloDB;

import BasedeDatos.ConnectionPool;
import Modelo.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Roberto
 */
public class ProductoDB {

    public static int insertar(Producto productoNuevo) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query
                = "INSERT INTO PRODUCTOS (ID, NOMBRE, DESCRIPCION, PRECIO, IMAGEN, VALORACION, ANIMAL, CATEGORIA) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, productoNuevo.getNombre());
            ps.setString(2, productoNuevo.getDescripcion());
            ps.setDouble(3, productoNuevo.getPrecio());
            ps.setString(4, productoNuevo.getImagen());
            ps.setInt(5, productoNuevo.getValoracion());
            ps.setString(6, productoNuevo.getAnimal());
            ps.setString(7, productoNuevo.getCategoria());
            int res = ps.executeUpdate();
            ps.close();
            pool.freeConnection(connection);
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static Producto getProductoPorNombre(String nombreProducto) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        Producto producto = null;
        String query = "SELECT * FROM PRODUCTOS WHERE NOMBRE = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, nombreProducto);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                producto = new Producto(rs.getInt("ID"),
                        rs.getString("NOMBRE"),
                        rs.getString("DESCRIPCION"),
                        rs.getDouble("PRECIO"),
                        rs.getString("IMAGEN"),
                        rs.getInt("VALORACION"),
                        rs.getString("ANIMAL"),
                        rs.getString("CATEGORIA"));
            }
            ps.close();
            pool.freeConnection(connection);

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return producto;
    }

    public static List<Producto> buscarProducto(String animal) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        List<Producto> resultado = new ArrayList<Producto>();
        String query = "SELECT DISTINCT * FROM PRODUCTOS WHERE ANIMAL = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, animal);
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                Producto producto = new Producto(res.getInt("ID"),
                        res.getString("NOMBRE"),
                        res.getString("DESCRIPCION"),
                        res.getDouble("PRECIO"),
                        res.getString("IMAGEN"),
                        res.getInt("VALORACION"),
                        res.getString("ANIMAL"),
                        res.getString("CATEGORIA"));
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

    public static List<Producto> buscarProducto(String animal, String categoria) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        List<Producto> resultado = new ArrayList<Producto>();
        String query = "SELECT DISTINCT * FROM PRODUCTOS WHERE ANIMAL = ? AND CATEGORIA =  ?"; 
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, animal);
            ps.setString(2, categoria);
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                Producto producto = new Producto(res.getInt("ID"),
                        res.getString("NOMBRE"),
                        res.getString("DESCRIPCION"),
                        res.getDouble("PRECIO"),
                        res.getString("IMAGEN"),
                        res.getInt("VALORACION"),
                        res.getString("ANIMAL"),
                        res.getString("CATEGORIA"));
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
            ps.setInt(1, i);
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                Producto producto = new Producto(res.getInt("ID"),
                        res.getString("NOMBRE"),
                        res.getString("DESCRIPCION"),
                        res.getDouble("PRECIO"),
                        res.getString("IMAGEN"),
                        res.getInt("VALORACION"),
                        res.getString("ANIMAL"),
                        res.getString("CATEGORIA"));
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
        String query = "SELECT * FROM PRODUCTOS WHERE ID = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, idProducto);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                producto = new Producto(rs.getInt("ID"),
                        rs.getString("NOMBRE"),
                        rs.getString("DESCRIPCION"),
                        rs.getDouble("PRECIO"),
                        rs.getString("IMAGEN"),
                        rs.getInt("VALORACION"),
                        rs.getString("ANIMAL"),
                        rs.getString("CATEGORIA"));
            }
            ps.close();
            pool.freeConnection(connection);

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return producto;
    }

    public static int modificarProducto(int id,
            String nombre,
            String descripcion,
            double precio,
            String imagen,
            int valoracion,
            String animal,
            String categoria) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps;
        String query
                = "UPDATE PRODUCTOS SET NOMBRE = ? , DESCRIPCION = ? , PRECIO = ? , IMAGEN = ? "
                + ", VALORACION = ? , ANIMAL = ? , CATEGORIA = ? WHERE ID=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, nombre);
            ps.setString(2, descripcion);
            ps.setDouble(3, precio);
            ps.setString(4, imagen);
            ps.setInt(5, valoracion);
            ps.setString(6, animal);
            ps.setString(7, categoria);
            ps.setInt(8, id);
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
    public static Producto getNombre(String action) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
