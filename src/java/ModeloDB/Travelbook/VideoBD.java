/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloDB.Travelbook;

import baseDatos.ConnectionPool;
import dominio.Video;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Hector
 */
public class VideoBD {
    public static boolean insert(Video video) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "INSERT INTO video (url, idDia, nombre) VALUES (?, ?, ?)";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, video.getUrl());
            ps.setInt(2, video.getIdDia());
            ps.setString(3, video.getNombre());
            int res = ps.executeUpdate();
            ps.close();
            pool.freeConnection(connection);
            return res==1;
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
        return false;
    }
    
    public static Video getVideoDelDia(int idDia){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps;
        Video video = null;
        String query = "SELECT * FROM video WHERE idDia = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, idDia);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                video = new Video(rs.getString("url"), rs.getInt("idDia"), rs.getString("nombre"));
            }
            ps.close();
            pool.freeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
        return video;
    }

    public static int update(Video video) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "UPDATE video SET url = ? WHERE idDia=?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, video.getUrl());
            ps.setInt(2, video.getIdDia());
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
