/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.travelbook;

import dominio.Dia;
import dominio.Diario;
import ModeloDB.Travelbook.DiaBD;
import ModeloDB.Travelbook.DiarioBD;
import ModeloDB.Travelbook.VisitasBD;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author JuanPablo
 */
@WebServlet(name = "CtrlModificarDiario", urlPatterns = {"/CtrlModificarDiario"})
public class CtrlModificarDiario extends HttpServlet {

    private String url;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            HttpSession sesion = request.getSession();
            // Si el usuario ha iniciado sesión ...
            if (sesion.getAttribute("nombreUsuario") != null) {

                //Si el usuario ha pinchado en la opcion "Mis diarios"...
                if (request.getParameter("verDiariosUsuario") != null) {
                    String nombreUsuario = request.getParameter("verDiariosUsuario");
                    ArrayList<Diario> listaDiarios = DiarioBD.buscarDiariosPublicadosPorNombre(nombreUsuario);
                    request.setAttribute("listaDiarios", listaDiarios);
                    this.url = "/diariosPublicados.jsp";
                } // Si el usuario desea modificar un diario...
                else if (request.getParameter("modificarDiarioId") != null) {
                    int idDiario = Integer.parseInt(request.getParameter("modificarDiarioId"));
                    List<Dia> listaDias = DiaBD.getDiasDelDiarioPorId(idDiario);
                    //Recuperamos el pais relativo al diario seleccionado para mostrarlo en la vista7
                    //String pais = VisitasBD.getPaisDeVisita(idDiario);
                    String tituloDiario = DiarioBD.getTituloDelDiario(idDiario);
                    request.setAttribute("tituloDiario", tituloDiario);
                    request.setAttribute("idDiario", idDiario);
                    request.setAttribute("listaDias", listaDias);
                    //request.setAttribute("paisDiario", pais);
                    this.url = "/modificarDiarioPublicado.jsp";
                } //Si el usuario desea guardar en la base de datos las modificaciones de un diario...
                else if (request.getParameter("accion") != null && request.getParameter("accion").equals("Guardar")) {
                    //Modificamos el diario con los nuevos datos introducidos por el usuario
                    int id = Integer.parseInt(request.getParameter("id"));
                    String tituloViaje = request.getParameter("tituloViaje");
                    Date fechaInicio = Date.valueOf(request.getParameter("fechaInicio"));
                    Date fechaFin = Date.valueOf(request.getParameter("fechaFin"));
                    String descripcion = request.getParameter("descripcionViaje");
                    int retorno = DiarioBD.modificarDiario(id, tituloViaje, fechaInicio, fechaFin, descripcion);
                    //Comprobamos que se ha realizado la modificación correctametne
                    if (retorno != 0) {
                        request.setAttribute("textoInformacion", "Diario modificado correctamente");
                        request.setAttribute("siguienteUrl", "miperfil.jsp");
                        this.url = "/informacion.jsp";
                    } else {
                        request.setAttribute("textoError", "No se ha podido modificar el diario seleccionado");
                        this.url = "/error.jsp";
                    }
                    //Si el usuario desea modificar los datos de un diario, redirigimos a la pagina destinada
                    //a tal proposito
                } else if (request.getParameter("editarCabeceraDiarioId") != null) {
                    int idDiario = Integer.parseInt(request.getParameter("editarCabeceraDiarioId"));
                    Diario diario = DiarioBD.getDiarioPorId(idDiario);
                    request.setAttribute("diario", diario);
                    this.url = "/editarCabecera.jsp";
                } else {
                    //Al no poder establecer desde donde viene el usuario autenticado, redirigimos a la pagina de
                    // mi perfil
                    this.url = "/miperfil.jsp";
                }

            } else {
                this.url = "/acceso.jsp";
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
