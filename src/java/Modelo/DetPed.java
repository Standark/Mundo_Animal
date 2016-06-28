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
public class DetPed {
    private int id;
    private int idPedido;
    private int idProducto;
    private int cantidad;
    private double precio;
    
    public DetPed(int id, int idPedido, int idProducto, int cantidad, double precio){
        this.id = id;
        this.idPedido = idPedido;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precio = precio;
    }
    
    public DetPed(){
        id = -2;
        idPedido = -2;
        idProducto = -2;
        cantidad = 0;
        precio = 0;
    }
    
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    
    public int getIdPedido(){
        return idPedido;
    }
    public void setIdPedido(){
        this.idPedido = idPedido;
    }
    public int getIdProducto(){
        return idProducto;
    }
    public void setIdProducto(int idProducto){
        this.idProducto = idProducto;
    }
    
    public int getCantidad(){
        return cantidad;
    }
    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }
    
    public double getPrecio(){
        return precio;
    }
    public void setPrecio(int precio){
        this.precio = precio;
    }
}
