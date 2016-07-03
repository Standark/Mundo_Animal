package Modelo;

import java.sql.Date;


/**
 *
 * @author Roberto
 */
public class Comentario {
    private int id;
    private int idCliente;
    private int idProducto;
    private int puntuacion;
    private String titulo;
    private String comentario;
    private Date fecha;   	
    
    public Comentario(int id, int idCliente, int idProducto, int puntuacion, String titulo, Date fecha, String comentario){
        this.id = id;
        this.idCliente = idCliente;
        this.idProducto = idProducto;
        this.puntuacion = puntuacion;
        this.titulo = titulo;
        this.comentario = comentario;
        this.fecha = fecha;
    }
    public Comentario(){
        id = -1;
        idCliente = -1;
        idProducto = -1;
        puntuacion = 0;
        titulo = null;
        comentario = null;
        fecha = null;
    }
    
    /**
     * 
     * @return identificador del comentario 
     */
    public int getId(){
        return id;
    }
    /**
     * 
     * @param id identificador del comentario
     */
    public void setId(int id){
        this.id = id;
    }
    /**
     * 
     * @return idCliente
     */
    public int getIdCliente() {
        return idCliente;
    }
    /**
     * 
     * @param idCliente identificador del cliente
     */
    public void setIdCliente(int idCliente){
        this.idCliente = idCliente;
    }
    /**
     * 
     * @return idProducto
     */
    public int getIdProducto() {
        return idProducto;
    }
    /**
     * 
     * @param idProducto identificador del producto
     */
    public void setIdProducto(int idProducto){
        this.idProducto = idProducto;
    }

    /**
     * 
     * @return puntuacion
     */
    public int getPuntuacion() {
        return puntuacion;
    }/**
     * 
     * @param puntuacion puntuacion del comntario
     */
    public void setPuntuacion(int puntuacion){
        this.puntuacion = puntuacion;
    }
    /**
     * 
     * @return titulo
     */
    public String getTitulo() {
        return titulo;
    }
    /**
     * 
     * @param titulo titulo del comentario
     */
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }
    /**
     * @return comentario
     */
    public String getComentario() {
        return comentario;
    }
    /**
     * 
     * @param comentario comentario del producto
     */
    public void setComentario(String comentario){
        this.comentario = comentario;
    }

    /**
     * @return fecha
     */
    public Date getFecha() {
        return fecha;
    }
    /**
     * 
     * @param fecha fecha de la publicacion (aaaa/mm/dd)
     */
    public void setFecha(Date fecha){
        this.fecha = fecha;
    }
}
