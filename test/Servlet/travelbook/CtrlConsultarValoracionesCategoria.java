/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.travelbook;

import dominio.MediaLugar;
import ModeloDB.Travelbook.ValoracionBD;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sandra_PC
 */
@WebServlet(name = "CtrlConsultarValoracionesCategoria", urlPatterns = {"/CtrlConsultarValoracionesCategoria"})
public class CtrlConsultarValoracionesCategoria extends HttpServlet {

    private String url;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String encoding = request.getCharacterEncoding();
        if (encoding==null) {
            request.setCharacterEncoding("UTF-8");
        }
        try {
                String accion = request.getParameter("accion");
                if (accion == null) { // No se viene de formulario
                    this.url = "/miperfil.jsp";
                } else { // Caso navegacion normal
                    switch (accion) {
                        case "Buscar":
                            List<MediaLugar> ranking = ValoracionBD.obtenerRanking();
                            request.setAttribute("ranking", ranking);
                            //Buscamos las ciudades con mejor puntuación media en la categoría seleccionada
                            String busqueda = request.getParameter("categoria");
                            List<MediaLugar> ciudades = ValoracionBD.buscarValoracionesCategoria(busqueda);
                            request.setAttribute("ciudades", ciudades);
                            request.setAttribute("categoria", busqueda);
                            this.url = "/valoraciones.jsp";
                            break;
                    }
                }
        } catch (Exception e) {
            //En caso de algun error , redireccionamos a una pagina destinada a este fin
            request.setAttribute("textoError", e);
            this.url = "/error.jsp";
        } finally {
            RequestDispatcher respuesta = getServletContext().getRequestDispatcher(this.url);
            respuesta.forward(request, response);
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
