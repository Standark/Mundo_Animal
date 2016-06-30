/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.travelbook;

import dominio.Opiniones;
import ModeloDB.Travelbook.ValoracionBD;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "CtrlMostrarValoracionesLugar", urlPatterns = {"/CtrlMostrarValoracionesLugar"})
public class CtrlMostrarValoracionesLugar extends HttpServlet {
    
    private String url;
      protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String encoding = request.getCharacterEncoding();
        if (encoding==null) {
                request.setCharacterEncoding("UTF-8");
            }
        try {
                String lugar = request.getParameter("nombreLugar");
                String media = request.getParameter("mediaLugar");
                //Obtiene todas las opiniones y valoraciones por categoría de todos los usuarios
                //que han comentado el lugar seleccionado
                List <Opiniones> opinion = ValoracionBD.buscarOpiniones(lugar);
                request.setAttribute("opiniones", opinion);
                request.setAttribute("media", media);
                request.setAttribute("lugar", lugar);
                this.url = "/verValoracion.jsp";
                
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
