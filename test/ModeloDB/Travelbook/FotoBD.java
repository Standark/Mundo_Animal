/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloDB.Travelbook;

import BasedeDatos.ConnectionPool;
import dominio.Foto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utilidades.TratamientoFechas;

/**
 *
 * @author Hector
 */
public class FotoBD {
    
    
    public static boolean insert(Foto foto) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "INSERT INTO foto (url, idDia, nombre) VALUES (?, ?, ?)";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, foto.getUrl());
            ps.setInt(2, foto.getIdDia());
            ps.setString(3, foto.getNombre());
            int res = ps.executeUpdate();
            ps.close();
            pool.freeConnection(connection);
            return res==1;
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
        return false;
    }
    
    public static Foto getFotoDelDia(int idDia){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps;
        Foto foto = null;
        String query = "SELECT * FROM foto WHERE idDia = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, idDia);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                foto = new Foto(rs.getString("url"), rs.getInt("idDia"), rs.getString("nombre"));
            }
            ps.close();
            pool.freeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
        return foto;
    }

    public static int update(Foto foto) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "UPDATE foto SET url = ? WHERE idDia=?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, foto.getUrl());
            ps.setInt(2, foto.getIdDia());
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
