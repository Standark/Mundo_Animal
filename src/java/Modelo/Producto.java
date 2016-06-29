 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
 
import java.io.Serializable;

public class Producto implements Serializable {
    private int id;
    private String nombre;
    private String descripcion;
    private double precio;
    private String imagen;
    private int valoracion;
    private String animal;
    private String categoria;
 
    public Producto (int id, String nombre, String descripcion, double precio, String imagen, int valoracion, String animal, String categoria){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
        this.valoracion = valoracion;
        this.animal = animal;
        this.categoria = categoria;
    }
    
    public Producto (){
        id = -2;
        nombre = null;
        descripcion = null;
        precio = 0.0;
        imagen = null;
        valoracion = 0;
        animal = null;
        categoria = null;
    }
    
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public String getDescripcion(){
        return descripcion;
    }
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    
    public double getPrecio(){
        return precio;
    }
    public void setPrecio(double precio){
        this.precio = precio;
    }
    
    public String getImagen(){
        return imagen;
    }
    public void setImagen(String imagen){
        this.imagen = imagen;
    }
    
    public int getValoracion(){
        return valoracion;
    }
    public void setValoracion(int valoracion){
        this.valoracion = valoracion;
    }
    
    public String getAnimal(){
        return animal;
    }
    public void setAnimal(String animal){
        this.animal = animal;
    }
    
    public String getCategoria(){
        return categoria;
    }
    public void setCategoria(String categoria){
        this.categoria = categoria;
    }
    
}
