/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.travelbook;

import dominio.Categoria;
import dominio.Valoracion;
import dominio.Visitas;
import ModeloDB.Travelbook.ValoracionBD;
import ModeloDB.Travelbook.VisitasBD;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
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
 * @author Hector
 */
@WebServlet(name = "CtrlValorarLugares", urlPatterns = {"/CtrlValorarLugares"})
public class CtrlValorarLugares extends HttpServlet {

    private String url;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try {
            String encoding = request.getCharacterEncoding();
            if (encoding == null) {
                request.setCharacterEncoding("UTF-8");
            }
            
            HttpSession sesion = request.getSession();
            String nombreUsuario = (String) sesion.getAttribute("nombreUsuario");
            if (nombreUsuario != null) { //El usuario esta identificado
                String accion = request.getParameter("action");
                if (accion == null) {//No se ha llamado desde alguno de los botones submit
                    this.url = "/miperfil.jsp";
                } else { //Se ha pulasado algun boton submit de algun formulario
                    switch (accion) {
                        case "Siguiente":
                            
                            procesar(request, sesion, nombreUsuario);

                            this.url = "/valorarLugares.jsp"; 
                            break;

                        case "Finalizar": 
                            
                            procesar(request, sesion, nombreUsuario);
                            
                            sesion.removeAttribute("lugares");
                            this.url = "/miperfil.jsp";
                            break;

                        default:
                            this.url = "/miperfil.jsp";
                            break;
                    }
                }

            } else {
                this.url = "/acceso.jsp";
            }
        } catch (Exception e) {
            request.setAttribute("textoError", e);
            this.url = "/error.jsp";
        } finally {
            RequestDispatcher respuesta = getServletContext().getRequestDispatcher(this.url);
            respuesta.forward(request, response);
        }

    }

    
    private void procesar(HttpServletRequest request, HttpSession sesion, String nombreUsuario) {
        //Consultamos el lugar que esta valorando, y el comentario
        String lugar = request.getParameter("lugar");
        String comentario = request.getParameter("comentario");
        
        //Conseguimos todos los parametros del request, y nos quedamos solo con las categorias (para obtener la valoracion)
        Enumeration<String> nam = request.getParameterNames();
        ArrayList<String> catValoradas = new ArrayList<>();
        while (nam.hasMoreElements()) {
            String cat = nam.nextElement();
            if (Categoria.isCategoria(cat)) {
                catValoradas.add(cat);
            }
        }
        
        
        
        //COnseguimos la valoracion para cada categoria introducida
        ArrayList<Float> valoraciones = new ArrayList<>();
        for (int i = 0; i < catValoradas.size(); i++) {
            float val = Float.parseFloat(request.getParameter(catValoradas.get(i)));
            val /= 2.0;
            valoraciones.add(val);
        }
        
        //buscamos en la lita de visitas de la sesion, cual esta valorando y la eliminamos de la lista
        List<Visitas> visitas = (List<Visitas>) sesion.getAttribute("lugares");
        for (int i = 0; i < visitas.size(); i++) {
            Visitas visit = visitas.get(i);
            if (visit.getLugar().equals(lugar)) { //Es la visita que valora
                //Metemos los nuevos datos y la actualizamos en la BD
                visit.setFechaRecomendacion(new Date());
                visit.setRecomendacion(comentario);                
                VisitasBD.update(visit);
                
                //Introducimos las Valoraciones para cada categoria en la BD
                for (int j = 0; j < catValoradas.size(); j++) {
                    Valoracion valoracion = new Valoracion(-1, new Date(), nombreUsuario, visit.getLugar(), visit.getCiudad(), visit.getPais(), Categoria.fromString(catValoradas.get(j)), valoraciones.get(j));
                    ValoracionBD.insertar(valoracion);
                }

                //Eliminamos la visita valorada de la lista almacenada en la sesion.
                visitas.remove(i);
            }
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
