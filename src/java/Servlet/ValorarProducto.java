/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modelo.Producto;
import ModeloDB.ProductoDB;
import Modelo.Comentario;
import ModeloDB.ComentarioDB;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Roberto
 */
//@WebServlet(name = "ValorarProducto", urlPatterns = {"/ValorarProducto"})

//public class ValorarProducto extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
 /*   private String url;
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
        String producto = request.getParameter("lugar");
        String comentario = request.getParameter("comentario");
        
        //Conseguimos todos los parametros del request, y nos quedamos solo con las categorias (para obtener la valoracion)
        Enumeration<String> nam = request.getParameterNames();
        ArrayList<String> catValoradas = new ArrayList<>();
        
        //COnseguimos la valoracion para cada categoria introducida
        ArrayList<Float> valoraciones = new ArrayList<>();
        for (int i = 0; i < catValoradas.size(); i++) {
            float val = Float.parseFloat(request.getParameter(catValoradas.get(i)));
            val /= 2.0;
            valoraciones.add(val);
        }
        
        //buscamos en la lita de visitas de la sesion, cual esta valorando y la eliminamos de la lista
        List<Producto> productos = (List<Producto>) sesion.getAttribute("lugares");
        for (int i = 0; i < productos.size(); i++) {
            Producto produc = productos.get(i);
            if (produc.getLugar().equals(lugar)) { //Es la visita que valora
                //Metemos los nuevos datos y la actualizamos en la BD
                produc.setFechaRecomendacion(new Date());
                produc.setRecomendacion(comentario);                
                ProductoDB.update(produc);
                
                //Introducimos las Valoraciones para cada categoria en la BD
                for (int j = 0; j < catValoradas.size(); j++) {
                    Comentario comentario = new Comentario(-1, new Date(), nombreUsuario, visit.getLugar(), visit.getCiudad(), visit.getPais(), Categoria.fromString(catValoradas.get(j)), valoraciones.get(j));
                    ComentarioDB.insertar(comentario);
                }

                //Eliminamos la visita valorada de la lista almacenada en la sesion.
                productos.remove(i);
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
 /*   @Override
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
 /*   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
  /*  @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}*/
