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
    
    /**
     * 
     * @param id
     * @param idPedido
     * @param idProducto
     * @param cantidad
     * @param precio 
     */
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
    
    /**
     * 
     * @return id del detalle del pedido
     */
    public int getId(){
        return id;
    }
    /**
     * 
     * @param id del detalle del pedido
     */
    public void setId(int id){
        this.id = id;
    }
    
    /**
     * 
     * @return id del pedido 
     */
    public int getIdPedido(){
        return idPedido;
    }
    /**
     * 
     * @param idPedido id del pedido 
     */
    public void setIdPedido(int idPedido){
        this.idPedido = idPedido;
    }
    
    /**
     * 
     * @return id del producto
     */
    public int getIdProducto(){
        return idProducto;
    }
    /**
     * 
     * @param idProducto id del producto
     */
    public void setIdProducto(int idProducto){
        this.idProducto = idProducto;
    }
    
    /**
     * 
     * @return cantidad del producto a comprar
     */
    public int getCantidad(){
        return cantidad;
    }
    /**
     * 
     * @param cantidad del producto a comprar
     */
    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }
    
    /**
     * 
     * @return precio que se tiene que pagar (precio del producto*cantidad)
     */
    public double getPrecio(){
        return precio;
    }
    /**
     * 
     * @param precio que se tiene que pagar (precio del producto*cantidad)
     */
    public void setPrecio(int precio){
        this.precio = precio;
    }
}
