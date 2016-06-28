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
                usuario = new Usuario(rs.getInt("id"),
                        rs.getString("nomre"),
                        rs.getString("apellidos"),
                        rs.getString("nick"),
                        rs.getString("password"),
                        rs.getString("direccion"),
                        rs.getInt("cp"),
                        rs.getString("mail"),
                        rs.getString("ciudad"),
                        rs.getString("provincia"),
                        rs.getInt("telefono"),
                        rs.getDate("fechaNac"));
            }
            ps.close();
            pool.freeConnection(connection);

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return usuario;
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
                Usuario usuario = new Usuario(res.getInt("id"),
                        res.getString("nomre"),
                        res.getString("apellidos"),
                        res.getString("nick"),
                        res.getString("password"),
                        res.getString("direccion"),
                        res.getInt("cp"),
                        res.getString("mail"),
                        res.getString("ciudad"),
                        res.getString("provincia"),
                        res.getInt("telefono"),
                        res.getDate("fechaNac"));
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
    
    public static ArrayList<Usuario> buscarUsuarioPorLocalidad(String s) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ArrayList<Usuario> resultado = new ArrayList<Usuario>();
        String query = "SELECT * FROM USUARIO WHERE (CIUDAD = ?) OR (PROVINCIA = ?)";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1,s);
            ps.setString(2,s);
            ResultSet res = ps.executeQuery();
            while(res.next()){
                Usuario usuario = new Usuario(res.getInt("id"),
                        res.getString("nomre"),
                        res.getString("apellidos"),
                        res.getString("nick"),
                        res.getString("password"),
                        res.getString("direccion"),
                        res.getInt("cp"),
                        res.getString("mail"),
                        res.getString("ciudad"),
                        res.getString("provincia"),
                        res.getInt("telefono"),
                        res.getDate("fechaNac"));
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
    
    public static Usuario getUsuarioPorId(int idProducto) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        Usuario usuario = null;
        String query = "SELECT * FROM USUARIO WHERE ID= ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, idProducto);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                usuario = new Usuario(rs.getInt("id"),
                        rs.getString("nomre"),
                        rs.getString("apellidos"),
                        rs.getString("nick"),
                        rs.getString("password"),
                        rs.getString("direccion"),
                        rs.getInt("cp"),
                        rs.getString("mail"),
                        rs.getString("ciudad"),
                        rs.getString("provincia"),
                        rs.getInt("telefono"),
                        rs.getDate("fechaNac"));
            }
            ps.close();
            pool.freeConnection(connection);

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return usuario;
    }

    public static int modificarProducto(int id, 
            String nombre, 
            String apellidos,
            String nick,
            String password,
            String direccion,
            int cp,
            String mail,
            String ciudad,
            String provincia,
            int telefono, 
            Date fechaNac) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps;
        String query
                = "UPDATE PRODUCTO SET NOMBRE = ? , DESCRIPCION = ? , PRECIO = ? , IMAGEN = ? "
                + ", VALORACION = ? , ANIMAL = ? , CATEGORIA = ? WHERE id=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, nombre);
            ps.setString(2, apellidos);
            ps.setString(3, nick);
            ps.setString(4, password);
            ps.setString(5, direccion);
            ps.setInt(6, cp);
            ps.setString(7, mail);
            ps.setString(8, ciudad);
            ps.setString(9, provincia);
            ps.setInt(10, telefono);
            ps.setDate(11,fechaNac);
            ps.setInt(12, id);
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
