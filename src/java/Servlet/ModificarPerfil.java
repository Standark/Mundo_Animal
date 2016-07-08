/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Modelo.Usuario;
import ModeloDB.UsuarioDB;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Roberto
 */
public class ModificarPerfil extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private String url;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession sesion = request.getSession();
            Usuario usuario = (Usuario) sesion.getAttribute("usuario");
            if (usuario.getNombre() == null) {
                request.setAttribute("textoError", "La sesion no ha sido iniciada");
                this.url = "/error.jsp";
            } else {
                int id = usuario.getId();
                String nombre = usuario.getNombre();
                String apellido = usuario.getApellidos();
                String nick = usuario.getNick();
                String password = usuario.getPassword();
                String direccion = usuario.getDireccion();
                int cp = usuario.getCP();
                String mail = usuario.getMail();
                String ciudad = usuario.getCiudad();
                String provincia = usuario.getProvincia();
                int telefono = usuario.getTelefono();
                Date fechaNac = usuario.getFechaNac();
                UsuarioDB.modificarUsuario(id, nombre, apellido, password, direccion, cp, mail, ciudad, provincia, telefono, fechaNac);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
