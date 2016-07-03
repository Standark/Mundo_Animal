package ModeloDB;

import BasedeDatos.ConnectionPool;
import Modelo.Comentario;
import Modelo.Usuario;
import Modelo.Producto;
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
public class ComentarioDB {

    public static int insertar(Comentario comentarioNuevo) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query
                = "INSERT INTO COENTARIO (ID, ID_CLIENTE, ID_PRODUCTO, PUNTUACION, TITULO, FECHA, COMENTARIO) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, comentarioNuevo.getIdCliente());
            ps.setInt(2, comentarioNuevo.getIdProducto());
            ps.setInt(3, comentarioNuevo.getPuntuacion());
            ps.setString(4, comentarioNuevo.getTitulo());
            ps.setDate(5, comentarioNuevo.getFecha());
            ps.setString(6, comentarioNuevo.getComentario());
            int res = ps.executeUpdate();
            ps.close();
            pool.freeConnection(connection);
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static List<Comentario> getComentarioPorUsuario(Usuario usuariobusc) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        List<Comentario> resultado = new ArrayList<Comentario>();
        String query = "SELECT DISTINCT a.* FROM COMENTARIO a WHERE a.ID_CLIENTE = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1,usuariobusc.getId());
            ResultSet res = ps.executeQuery();
            while(res.next()){
                Comentario comentario = new Comentario(res.getInt("ID"),
                        res.getInt("ID_CLIENTE"),
                        res.getInt("ID_PRODUCTO"),
                        res.getInt("VALORACIOM"),
                        res.getString("TITULO"),
                        res.getDate("FECHA"),
                        res.getString("COMENTARIO"));
                resultado.add(comentario);
            }
            ps.close();
            pool.freeConnection(connection);
            return resultado;
        } catch (SQLException e) {
            e.printStackTrace();
            return resultado;
        }
    }
    
    public static List<Comentario> getComentarioPorProducto(Producto productobusc) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        List<Comentario> resultado = new ArrayList<Comentario>();
        String query = "SELECT DISTINCT a.* FROM COMENTARIO a WHERE a.ID_PRODUCTO = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1,productobusc.getId());
            ResultSet res = ps.executeQuery();
            while(res.next()){
                Comentario comentario = new Comentario(res.getInt("ID"),
                        res.getInt("ID_CLIENTE"),
                        res.getInt("ID_PRODUCTO"),
                        res.getInt("VALORACIOM"),
                        res.getString("TITULO"),
                        res.getDate("FECHA"),
                        res.getString("COMENTARIO"));
                resultado.add(comentario);
            }
            ps.close();
            pool.freeConnection(connection);
            return resultado;
        } catch (SQLException e) {
            e.printStackTrace();
            return resultado;
        }
    }
    
    public static Comentario getComentarioPorId(int idComentario) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        Comentario comentario = null;
        String query = "SELECT * FROM COMENTARIO WHERE ID= ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, idComentario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                comentario = new Comentario(rs.getInt("id"),
                        rs.getInt("ID_CLIENTE"),
                        rs.getInt("ID_PRODUCTO"),
                        rs.getInt("PUNTUACION"),
                        rs.getString("TITULO"),
                        rs.getDate("FECHA"),
                        rs.getString("COMENTARIO"));
            }
            ps.close();
            pool.freeConnection(connection);

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return comentario;
    }

    public static int modificarComentario(int id,
            int idCliente,
            int idProducto,
            int puntuacion,
            String titulo,
            Date fecha,
            String comentario) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps;
        String query
                = "UPDATE COMENTARIO SET ID_CLIENTE = ? , ID_PRODUCTO = ? , TITULO = ? , FECHA = ? "
                + ", COMENTARIO = ? WHERE ID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, idCliente);
            ps.setInt(2, idProducto);
            ps.setInt(3, puntuacion);
            ps.setString(4, titulo);
            ps.setDate(5, fecha);
            ps.setString(6, comentario);
            ps.setInt(7, id);
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
