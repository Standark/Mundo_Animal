/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Modelo.Comentario;
import Modelo.Usuario;
import ModeloDB.ComentarioDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Calendar;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Your Name <Gonzalo López Fernández>
 */
public class Comentar extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        String url;
        Usuario usuario = (Usuario) sesion.getAttribute("usuario");
        if (usuario == null) {
            url = "/error.jsp";
            request.setAttribute("textoError", "La sesion no ha sido iniciada");

        } else {
            url = "/exito.jsp";
            request.setAttribute("textoExito", "El comentario se ha guardado");
            String titulo = (String) request.getAttribute("titulo");
            String comentario = (String) request.getAttribute("comentario");
            int puntuacion = 0;
            //(int) request.getAttribute("puntuacion");
            int idCliente = (int) usuario.getId();
            Date fecha = new Date(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH);
            int idProducto = (int) sesion.getAttribute("idProducto");
            ComentarioDB.insertar(new Comentario(1, idCliente, idProducto, puntuacion, titulo, fecha, comentario));
        }
        RequestDispatcher respuesta = getServletContext().getRequestDispatcher(url);
        respuesta.forward(request, response);
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
