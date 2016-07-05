package ModeloDB;

import BasedeDatos.ConnectionPool;
import Modelo.Usuario;
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
public class UsuarioDB {
/*Inserta el usuario nuevo con los datos que este ha proporcionado*/
    public static int insertar(Usuario usuarioNuevo) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query
                = "INSERT INTO USUARIO (ID, NOMBRE, APELLIDOS, NICK, PASSWORD,"
                + " DIRECCION, CP, MAIL, CIUDAD, PROVINCIA, TELEFONO, FECHA_NAC)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?; ?, ?)";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, usuarioNuevo.getNombre());
            ps.setString(2, usuarioNuevo.getApellidos());
            ps.setString(3, usuarioNuevo.getNick());
            ps.setString(4, usuarioNuevo.getPassword());
            ps.setString(5, usuarioNuevo.getDireccion());
            ps.setInt(6, usuarioNuevo.getCP());
            ps.setString(7, usuarioNuevo.getMail());
            ps.setString(8, usuarioNuevo.getCiudad());
            ps.setString(9, usuarioNuevo.getProvincia());
            ps.setInt(10, usuarioNuevo.getTelefono());
            ps.setDate(11, usuarioNuevo.getFechaNac());

            int res = ps.executeUpdate();
            ps.close();
            pool.freeConnection(connection);
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
/*Saca los datos en relacion al nick*/
    public static Usuario getUsuarioPorNick(String nombreUsuario) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        Usuario usuario = null;
        String query = "SELECT * FROM USUARIO WHERE NICK = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, nombreUsuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                usuario = new Usuario(rs.getInt("ID"),
                        rs.getString("NOMBRE"),
                        rs.getString("APELLIDOS"),
                        rs.getString("NICK"),
                        rs.getString("PASSWORD"),
                        rs.getString("DIRECCION"),
                        rs.getInt("CP"),
                        rs.getString("MAIL"),
                        rs.getString("CIUDAD"),
                        rs.getString("PROVINCIA"),
                        rs.getInt("TELEFONO"),
                        rs.getDate("FECHA_NAC"));
            }
            
            ps.close();
            pool.freeConnection(connection);

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return usuario;
    }
    /*Busca si un usuario esta registrado*/

    public static boolean isUsuarioRegistrado(String nombreUsuario) {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT NICK FROM USUARIO WHERE NICK = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, nombreUsuario);
            rs = ps.executeQuery();
            boolean res = rs.next();
            rs.close();
            ps.close();
            pool.freeConnection(connection);
            return res;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }


    public static List<Usuario> buscarUsuario(String s) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        List<Usuario> resultado = new ArrayList<Usuario>();
        String query = "SELECT DISTINCT a.* FROM USUARIO a WHERE ((a.NOMBRE = ?) OR (a.APELLIDOS = ?) OR (a.NICK = ?))";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1,s);
            ps.setString(2,s);
            ps.setString(3,s);
            ResultSet res = ps.executeQuery();
            while(res.next()){
                Usuario usuario = new Usuario(res.getInt("ID"),
                        res.getString("NOMBRE"),
                        res.getString("APELLIDOS"),
                        res.getString("NICK"),
                        res.getString("PASSWORD"),
                        res.getString("DIRECCION"),
                        res.getInt("CP"),
                        res.getString("MAIL"),
                        res.getString("CIUDAD"),
                        res.getString("PROVINCIA"),
                        res.getInt("TELEFONO"),
                        res.getDate("FECHA_NAC"));
                resultado.add(usuario);
            }
            ps.close();
            pool.freeConnection(connection);
            return resultado;
        } catch (SQLException e) {
            e.printStackTrace();
            return resultado;
        }
    }
    
    
    
/*Comprueba que la contraseña es correcta*/
    public static boolean isClaveCorrecta(String nombreUsuario, String clave) {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM USUARIO "
                + "WHERE NICK = ? AND PASSWORD = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, nombreUsuario);
            ps.setString(2, clave);
            rs = ps.executeQuery();
            boolean res = rs.next();
            rs.close();
            ps.close();
            pool.freeConnection(connection);
            return res;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
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
