package Modelo;

import java.util.Date;


/**
 *
 * @author josefrancisco
 */
public class Comentario {
    private int idCliente;
    private int idProducto;
    private int idComentario;
    private int puntuacion;
    private String titulo;
    private String comentario;
    private Date fecha;   	
    
    public Comentario(int idCliente, int idProducto,int idComentario ,int puntuacion ,String titulo, String comentario, Date fecha){
        this.idCliente = idCliente;
        this.idProducto = idProducto;
        this.idComentario = idComentario;
        this.puntuacion = puntuacion;
        this.titulo = titulo;
        this.comentario = comentario;
        this.fecha = fecha;
    }

    /**
     * @return the idCliente
     */
    public int getIdCliente() {
        return idCliente;
    }
    /**
        * @return the idProducto
        */
       public int getidProductor() {
           return idProducto;
       }
    /**
     * @return the idComentario
     */
    public int getidComentario() {
        return idComentario;
    }

    /**
     * @return the puntuacion
     */
    public int getpuntuacion() {
        return puntuacion;
    }
  /**
     * @return the puntuacion
     */
    public String gettitulo() {
        return titulo;
    }
    /**
     * @return the comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }
}
