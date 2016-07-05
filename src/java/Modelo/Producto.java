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
 
    /**
     * 
     * @param id
     * @param nombre
     * @param descripcion
     * @param precio
     * @param imagen
     * @param valoracion
     * @param animal
     * @param categoria 
     */
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
    
    /**
     * 
     * @return id del producto
     */
    public int getId(){
        return id;
    }
    /**
     * 
     * @param id del producto
     */
    public void setId(int id){
        this.id = id;
    }
    
    /**
     * 
     * @return nombre del producto
     */
    public String getNombre(){
        return nombre;
    }
    /**
     * 
     * @param nombre del producto
     */
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    /**
     * 
     * @return descripcion del producto
     */
    public String getDescripcion(){
        return descripcion;
    }
    /**
     * 
     * @param descripcion del producto
     */
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    
    /**
     * 
     * @return precio del producto
     */
    public double getPrecio(){
        return precio;
    }
    /**
     * 
     * @param precio del producto
     */
    public void setPrecio(double precio){
        this.precio = precio;
    }
    
    /**
     * 
     * @return ruta de la imagen del producto
     */
    public String getImagen(){
        return imagen;
    }
    /**
     * 
     * @param imagen ruta de la imagen del producto
     */
    public void setImagen(String imagen){
        this.imagen = imagen;
    }
    
    /**
     * 
     * @return valoracion global
     */
    public int getValoracion(){
        return valoracion;
    }
    /**
     * 
     * @param valoracion global del producto
     */
    public void setValoracion(int valoracion){
        this.valoracion = valoracion;
    }
    
    /**
     * 
     * @return animal al que esta orientado el producto (ej. Perro, Gato...)
     */
    public String getAnimal(){
        return animal;
    }
    /**
     * 
     * @param animal al que esta orientado el producto (ej. Perro, Gato...)
     */
    public void setAnimal(String animal){
        this.animal = animal;
    }
    
    /**
     * 
     * @return categoria a la que pertenece el producto (ej. Comida, Cama...)
     */
    public String getCategoria(){
        return categoria;
    }
    /**
     * 
     * @param categoria a la que pertenece el producto (ej. Comida, Cama...)
     */
    public void setCategoria(String categoria){
        this.categoria = categoria;
    }
    
}
