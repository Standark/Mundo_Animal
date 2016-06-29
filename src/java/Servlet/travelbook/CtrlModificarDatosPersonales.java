package servlet.travelbook;

import dominio.UsuarioRegistrado;
import dominioBD.UsuarioRegistradoBD;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Grupo 13 - Sistemas y servicios Web
 */
@WebServlet(name = "CtrlModificarDatosPersonales", urlPatterns = {"/CtrlModificarDatosPersonales"})
public class CtrlModificarDatosPersonales extends HttpServlet {
    private String url;


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            
            String encoding = request.getCharacterEncoding();
            if (encoding == null) {
                request.setCharacterEncoding("UTF-8");
            }
            //Obtenemos la sesión actual
            HttpSession sesion = request.getSession();
            String nombreUsuario = (String) sesion.getAttribute("nombreUsuario");

            // Si el usuario ha iniciado sesión ...
            if (nombreUsuario != null) {
                String accion = request.getParameter("action");
                if (accion == null) {//No se ha llamado desde alguno de los botones submit
                    if (request.getParameter("cont") != null && request.getParameter("cont").equals("1")) { //Quiere modificar el usuario, cargamos los datos necesarios para mostrarlos
                        UsuarioRegistrado usuarioActual = UsuarioRegistradoBD.datosUsuarioRegistrado(nombreUsuario);
                        request.setAttribute("usuarioActual", usuarioActual);
                        
                        this.url = "/modificarDatosPersonales.jsp";
                    } else { //Habrá metido la direccion del servlet a mano, se le manda a la pagina del perfil.
                        this.url = "/miperfil.jsp";
                    }
                }else if(accion.equals("Modificar Datos")){
                    String nombre = request.getParameter("nombreUsuario");
                    String pass = request.getParameter("pass");
                    String email = request.getParameter("email");
                    int retorno = UsuarioRegistradoBD.update(nombre, pass, email, nombreUsuario);
                    if(retorno != 0){
                        request.setAttribute("textoInformacion", "Se ha modificado correctamente el usuario");
                        request.setAttribute("siguienteUrl","miperfil.jsp");
                        sesion.setAttribute("nombreUsuario", nombre);

                        this.url = "/informacion.jsp";
                    }else{
                        request.setAttribute("textoInformacion", "No se ha modificado el usuario");
                        request.setAttribute("siguienteUrl","miperfil.jsp");

                        this.url = "/informacion.jsp";
                    }
                }

            } else {
                this.url = "/acceso.jsp";
            }
            //En caso de algun error no previsto , redireccionamos a una pagina destinada a este fin
        } catch (Exception e) {

            request.setAttribute("textoError", "Se ha producido un error, por favor vuelva a intentarlo");
            this.url = "/error.jsp";

            // Sea cual sea el resultado del procesamiento del servlet, se redirecciona a la página que se haya especificado
            // en la varibale url
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