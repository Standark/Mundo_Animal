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
    
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    
    public int getIdCliente(){
        return idCliente;
    }
    public void setIdCliente(int idCliente){
        this.idCliente = idCliente;
    }
    
    public Date getFechaAlta(){
        return fechaAlta;
    }
    public void setFechaAlta(Date fechaAlta){
        this.fechaAlta = fechaAlta;
    }
}
