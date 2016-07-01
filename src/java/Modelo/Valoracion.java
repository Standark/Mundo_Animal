/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Roberto
 */
public class Valoracion {
    
    private int id;
    private int idUsuario;
    private int idProducto;
    private int valoracion;
    private String titulo;
    private String comentario;
    
    public Valoracion(int id, int idUsuario, int idProducto, int valoracion, String titulo, String comentario){
        this.id = id;
        this.idUsuario = idUsuario;
        this.idProducto = idProducto;
        this.valoracion = valoracion;
        this.titulo = titulo;
        this.comentario = comentario;
    }
    public Valoracion(){
        id = -1;
        idUsuario = -1;
        idProducto = -1;
        valoracion = 0;
        comentario = null;
    }
    
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    
    public int getIdUsuario(){
        return idUsuario;
    }
    public void setIdUsuario(int idUsuario){
        this.idUsuario = idUsuario;
    }
    
    public int getIdProducto(){
        return idProducto;
    }
    public void setIdProducto(int idProducto){
        this.idProducto = idProducto;
    }
    
    public int getValoracion(){
        return valoracion;
    }
    public void setValoracion(int valoracion){
        this.valoracion = valoracion;
    }
    
    public String getTitulo(){
        return titulo;
    }
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }
    
    public String getComentario(){
        return comentario;
    }
    public void setComentario(String comentario){
        this.comentario = comentario;
    }
}
