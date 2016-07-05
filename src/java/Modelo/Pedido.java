/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Date;



/**
 *
 * @author Dawn_
 */
public class Pedido {
    private int id;
    private int idCliente;
    private Date fechaAlta;
    
    /**
     * 
     * @param id
     * @param idCliente
     * @param fechaAlta 
     */
    public Pedido (int id, int idCliente, Date fechaAlta){
        this.id = id;
        this.idCliente = idCliente;
        this.fechaAlta = fechaAlta;
    }
    public Pedido(){
        id = -2;
        idCliente = -2;
        fechaAlta = null;
    }
    
    /**
     * 
     * @return id del pedido
     */
    public int getId(){
        return id;
    }
    /**
     * 
     * @param id del pedido
     */
    public void setId(int id){
        this.id = id;
    }
    
    /**
     * 
     * @return id del cliente
     */
    public int getIdCliente(){
        return idCliente;
    }
    /**
     * 
     * @param idCliente id del cliente
     */
    public void setIdCliente(int idCliente){
        this.idCliente = idCliente;
    }
    
    /**
     * 
     * @return fecha en la que se realiza el pedio
     */
    public Date getFechaAlta(){
        return fechaAlta;
    }
    /**
     * 
     * @param fechaAlta fecha en la que se realiza el pedido
     */
    public void setFechaAlta(Date fechaAlta){
        this.fechaAlta = fechaAlta;
    }
}
