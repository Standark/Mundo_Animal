/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Modelo.Usuario;
import ModeloDB.UsuarioDB;
import java.io.IOException;
import java.io.PrintWriter;
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
            HttpSession session = request.getSession();
            if(session.getAttribute("id")==null){
                request.setAttribute("textoError", "La sesion no ha sido iniciada");
                this.url = "/error.jsp";
            }
            else{
                int id =(int) session.getAttribute("id");
                String nombre = (String)request.getAttribute("nombre");
                String apellido = (String)request.getAttribute("apellido");
                String nick = (String)request.getAttribute("nick");
                String password = (String)request.getAttribute("password"); 
                String direccion = (String)request.getAttribute("direccion");
                int cp = (int)request.getAttribute("cp");
                String mail = (String)request.getAttribute("mail");
                String ciudad = (String)request.getAttribute("ciudad");
                String provincia = (String)request.getAttribute("provincia");
                int telefono = (int)request.getAttribute("telefono");
                Date fechaNac = (Date)request.getAttribute("fechaNac");
                int completo = UsuarioDB.modificarUsuario(id, nombre, apellido, password, direccion, cp, mail,ciudad, provincia, telefono, fechaNac);
            }
        }catch(Exception e){
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
