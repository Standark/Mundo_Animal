package servlet.travelbook;

import dominio.Diario;
import dominioBD.DiarioBD;
import dominioBD.UsuarioRegistradoBD;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utilidades.TratamientoFechas;

/**
 * @author Grupo 13 - Sistemas y servicios Web
 */
@WebServlet(name = "CtrlCrearNuevoDiario", urlPatterns = {"/CtrlCrearNuevoDiario"})
public class CtrlCrearNuevoDiario extends HttpServlet {

    private String url;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            HttpSession sesion = request.getSession();
            // Si el usuario ha iniciado sesión ...
            if (sesion.getAttribute("nombreUsuario") != null) {
                String accion = request.getParameter("accion");
                if (accion == null) { // No se viene de formulario
                    this.url = "/miperfil.jsp";
                } else { // Caso navegacion normal
                    switch (accion) {
                        case "Volver":
                            this.url = "/miperfil.jsp";
                            break;
                        case "Crear Diario":
                            //Comprobamos si tiene algun diario en curso
                            String nombreUsuario = sesion.getAttribute("nombreUsuario").toString();
                            if(DiarioBD.getDiarioEnCursoDe(nombreUsuario)!=null){
                               request.setAttribute("textoError", "Ya tiene un diario en curso");
                               this.url = "/error.jsp"; 
                            }else{
                                //Guardar los parámetros de la vista en variables para poder tratarlas y guardarlas en la BD
                                String tituloViaje = request.getParameter("tituloViaje");
                                String fechaInicio = request.getParameter("fechaInicio");
                                String fechaFin = request.getParameter("fechaFin");
                                String resumen = request.getParameter("resumen");

                                try {

                                    Date inicio = TratamientoFechas.stringToDate(fechaInicio);
                                    Date fin = TratamientoFechas.stringToDate(fechaFin);
                                    
                                    Diario diarioNuevo = new Diario(-1, nombreUsuario, tituloViaje, inicio, fin, resumen, true);
                                    if (DiarioBD.insertar(diarioNuevo) == 0) {
                                        request.setAttribute("textoError", "No se han guardado correctamente los datos");
                                        this.url = "/error.jsp";
                                    } else {
                                        this.url = "/CtrlContinuarDiario?cont=1";
                                        //Crear las carpetas donde se guardarán las fotos y videos de un viaje asociado a un usuario
                                        String ruta = this.getServletContext().getRealPath("/usuarios/"+nombreUsuario+"/diarios/");
                                        File nuevaCarpeta = new File(ruta, tituloViaje);
                                        nuevaCarpeta.mkdir();
                                        File nuevaCarpeta2 = new File(ruta+"/"+tituloViaje, "fotos");
                                        nuevaCarpeta2.mkdir();
                                        File nuevaCarpeta3 = new File(ruta+"/"+tituloViaje, "videos");
                                        nuevaCarpeta3.mkdir();
                                    }

                                } catch (Exception e) {
                                    this.url = "/nuevoDiario.jsp";
                                    //request.setAttribute("TipoError", "No se ha introducido el la fecha en formato correcto DD-MM-AAAA");
                                }
                            }
                            
                            break;
                    }
                }
            // Si el usuario introduce la direccion de este servlet de forma manual en la barra de direcciones 
            // o llega como resultado de una "navegación hacia atras"  le redirigimos a la página correspondiente
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
