package Modelo;

import java.sql.Date;

/**
 *
 */
public class Usuario {

    private int id; 
    private String nombre;
    private String apellidos;
    private String nick;
    private String password;
    private String direccion;
    private int cp;
    private String mail;; 
    private String ciudad;
    private String provincia;
    private int telefono;
    private Date fechaNac;



    public Usuario(int id, String nombre, String apellidos, String nick, String password, String direccion, int cp, String mail, String ciudad, String provincia, int telefono, Date fechaNac) {
        this.id = id;
        this.nombre=nombre;
        this.apellidos = apellidos;
        this.nick = nick;
        this.password = password;
        this.direccion = direccion;
        this.cp=cp;
        this.mail = mail;
        this.ciudad = ciudad;
        this.provincia = provincia;
        this.telefono = telefono;
        this.fechaNac = fechaNac;
    } 
    public Usuario(){
        id = -2;
        nombre = null;
        apellidos = null;
        nick = null;
        password = null;
        direccion = null;
        cp = -2;
        mail = null;
        ciudad = null;
        provincia = null;
        telefono = -2;
        fechaNac = null;
        
    }
    
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNick() {
        return nick;
    }
    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getDireccion(){
        return direccion;
    }
    public void setDireccion(String direccion){
        this.direccion = direccion;
    }

    public int getCP() {
        return cp;
    }
    public void setCP(int cp) {
        this.cp = cp;
    }

    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCiudad() {
        return ciudad;
    }
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    
    public String getProvincia(){
        return provincia;
    }
    public void setProvincia(String provincia){
        this.provincia = provincia;
    }

    public int getTelefono() {
        return telefono;
    }
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNacimiento(Date fechaNac) {
        this.fechaNac = fechaNac;
    }
}