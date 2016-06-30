package ModeloDB.Travelbook;

import baseDatos.ConnectionPool;
import dominio.UsuarioRegistrado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Grupo 13 - Sistemas y servicios Web
 */
public class UsuarioRegistradoBD {

    public static boolean isUsuarioRegistrado(String nombreUsuario) {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT nombreUsuario FROM USUARIOREGISTRADO "
                + "WHERE nombreUsuario = ?";

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

    public static boolean isClaveCorrecta(String nombreUsuario, String clave) {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM USUARIOREGISTRADO "
                + "WHERE nombreUsuario = ? AND password = ?";

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

    public static int insertar(UsuarioRegistrado nuevoUsuario) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        //Se crea la sentencia SQL de insercción a mano
        String query = "INSERT INTO usuarioRegistrado (nombreUsuario, password, email, codigoActivacion, activado) VALUES (?, ?, ?, ?, ?)";

        try {
            //Para que la insercción sea mas eficiente se utiliza una "insercción preparada", es decir , una vez
            //construida la sentencia sql, se agregan los parametros
            ps = connection.prepareStatement(query);
            ps.setString(1, nuevoUsuario.getNombreUsuario());
            ps.setString(2, nuevoUsuario.getPassword());
            ps.setString(3, nuevoUsuario.getEmail());
            ps.setString(4, nuevoUsuario.getCodigoActivacion());
            ps.setBoolean(5, nuevoUsuario.isActivado());
            int res = ps.executeUpdate();
            ps.close();
            pool.freeConnection(connection);
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static boolean comprobarRegistro(String codigo, String usuario) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM usuarioRegistrado where nombreUsuario=? and codigoActivacion=?";

        try {
            //Para que la insercción sea mas eficiente se utiliza una "insercción preparada", es decir , una vez
            //construida la sentencia sql, se agregan los parametros
            ps = connection.prepareStatement(query);
            ps.setString(1, usuario);
            ps.setString(2, codigo);
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

    public static void activarCuenta(String usuario) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "UPDATE usuarioRegistrado SET  activado= ? WHERE nombreUsuario=?";

        try {
            ps = connection.prepareStatement(query);
            ps.setBoolean(1, true);
            ps.setString(2, usuario);
            int res = ps.executeUpdate();
            ps.close();
            pool.freeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean isActivado(String nombreUsuario) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT activado FROM USUARIOREGISTRADO "
                + "WHERE nombreUsuario = ? and activado=true";

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

    public static UsuarioRegistrado datosUsuarioRegistrado(String nombreUsuario) {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        UsuarioRegistrado usuario = null;
        String query = "SELECT * FROM USUARIOREGISTRADO "
                + "WHERE nombreUsuario = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, nombreUsuario);
            //ps.setBoolean(2, true);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                usuario = new UsuarioRegistrado(rs.getString("nombreUsuario"), rs.getString("password"), rs.getString("email"), rs.getString("codigoActivacion"), rs.getBoolean("activado"));
            }
            ps.close();
            pool.freeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    public static int update(String nombreUsuario, String pass, String email, String antiguoNombre) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "UPDATE USUARIOREGISTRADO SET nombreUsuario = ?, password = ?, email = ? WHERE nombreUsuario = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, nombreUsuario);
            ps.setString(2, pass);
            ps.setString(3, email);
            ps.setString(4, antiguoNombre);
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
