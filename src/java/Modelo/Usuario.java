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
    private String mail;
    ; 
    private String ciudad;
    private String provincia;
    private int telefono;
    private Date fechaNac;

    /**
     *
     * @param id
     * @param nombre
     * @param apellidos
     * @param nick
     * @param password
     * @param direccion
     * @param cp
     * @param mail
     * @param ciudad
     * @param provincia
     * @param telefono
     * @param fechaNac
     */
    public Usuario(String nombre, String apellidos, String nick, String password, String direccion, int cp, String mail, String ciudad, String provincia, int telefono, Date fechaNac) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nick = nick;
        this.password = password;
        this.direccion = direccion;
        this.cp = cp;
        this.mail = mail;
        this.ciudad = ciudad;
        this.provincia = provincia;
        this.telefono = telefono;
        this.fechaNac = fechaNac;
    }

    public Usuario(int id, String nombre, String apellidos, String nick, String password, String direccion, int cp, String mail, String ciudad, String provincia, int telefono, Date fechaNac) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nick = nick;
        this.password = password;
        this.direccion = direccion;
        this.cp = cp;
        this.mail = mail;
        this.ciudad = ciudad;
        this.provincia = provincia;
        this.telefono = telefono;
        this.fechaNac = fechaNac;
    }

    public Usuario() {
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

    /**
     *
     * @return id del usuario
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id del usuario
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return nombre del usuario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre del usuario
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * @return apellidos del usuario
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     *
     * @param apellidos del usuario
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     *
     * @return nick del usuario
     */
    public String getNick() {
        return nick;
    }

    /**
     *
     * @param nick del usuario
     */
    public void setNick(String nick) {
        this.nick = nick;
    }

    /**
     *
     * @return password del usuario
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password del usuario
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return direccion del usuario
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     *
     * @param direccion del usuario
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     *
     * @return codigo postal del usuario
     */
    public int getCP() {
        return cp;
    }

    /**
     *
     * @param cp del usuario
     */
    public void setCP(int cp) {
        this.cp = cp;
    }

    /**
     *
     * @return mail del usuario
     */
    public String getMail() {
        return mail;
    }

    /**
     *
     * @param mail del usuario
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     *
     * @return ciudad del usuario
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     *
     * @param ciudad del usuario
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     *
     * @return provincia del usuario
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     *
     * @param provincia del usuario
     */
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    /**
     *
     * @return telefono del usuario
     */
    public int getTelefono() {
        return telefono;
    }

    /**
     *
     * @param telefono del usuario
     */
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    /**
     *
     * @return fecha de nacimiento del usuario
     */
    public Date getFechaNac() {
        return fechaNac;
    }

    /**
     *
     * @param fechaNac del usuario
     */
    public void setFechaNacimiento(Date fechaNac) {
        this.fechaNac = fechaNac;
    }
}
