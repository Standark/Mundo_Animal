package servlet.travelbook;

import dominio.UsuarioRegistrado;
import ModeloDB.Travelbook.UsuarioRegistradoBD;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * @author Grupo 13 - Servicios y sistemas Web
 */
@WebServlet(name = "CtrlRegistrarse", urlPatterns = {"/CtrlRegistrarse"})
public class CtrlRegistrarse extends HttpServlet {

    private String url;

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            //Obtenemos la sesión actual
            HttpSession sesion = request.getSession();

            // En este caso de uso, el usuario NO tiene que haber iniciado sesion
            // por eso comprobamos que quien llegue a este servlet no este metido en sesion
            if (sesion.getAttribute("nombreUsuario") == null) {

                //Obtenemos el contenido de los cuadros de texto que nos llegan de la pagina jsp
                // se utiliza request por que esta acción tiene el alcance de una única petición
                String nombreUsuario = request.getParameter("nombreUsuario");
                String password = request.getParameter("pass");
                //Password repetida no se recupera por que ya se ha comprobado con javascript en la pagina
                //jsp que el usuario ha introducido la misma contraseña dos veces y no tiene sentido recuperarla 
                //en este servlet
                String email = request.getParameter("email");

                if(UsuarioRegistradoBD.isUsuarioRegistrado(nombreUsuario)){
                    request.setAttribute("textoError", "Ya hay un usuario registrado con ese nombre");
                    this.url = "/error.jsp";
                }
                //Generamos el codigo aleatorio que enviaremos en el email para confirmar el registro
                    String codigo = getCadenaAleatoria();
                      
                //Guardamos los datos en la BD, al insertar la función puede devolver dos valores, 0 si no ha
                //conseguido insertarlo y 1 en caso contrario
                UsuarioRegistrado nuevoUsuario = new UsuarioRegistrado(nombreUsuario,password,email, codigo, false);
                
                if (UsuarioRegistradoBD.insertar(nuevoUsuario) == 0) {
                    request.setAttribute("textoError", "No se han guardado correctamente los datos");
                    this.url = "/error.jsp";
                } else {
                    //Crear las carpetas donde se guardarán los diarios, las fotos y videos de un viaje asociado a un usuario
                    String ruta = this.getServletContext().getRealPath("/usuarios/");
                    File nuevaCarpeta = new File(ruta, nombreUsuario);
                    nuevaCarpeta.mkdir();
                    File nuevaCarpeta2 = new File(ruta+"/"+nombreUsuario, "diarios");
                    nuevaCarpeta2.mkdir();
                    //Enviamos un email de confirmacion al usuario registrado para que active la cuenta
                    request.setAttribute("nombreUsuario", nombreUsuario);
                    request.setAttribute("codigo", codigo);
                    request.setAttribute("email", email);
                    this.url = "/CtrlEnviarCorreo";
                  
                }
                // Si el usuario que llega a este servlet esta registrado, no tiene sentido que lo vuelva a hacer, por eso
                // redirigimos a su peril
            } else {
                this.url = "/miperfil.jsp";
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

    private String getCadenaAleatoria() {
        String cadenaAleatoria = "";
        long milis = new java.util.GregorianCalendar().getTimeInMillis();
        Random r = new Random(milis);
        int i = 0;
        while ( i < 10){
            char c = (char)r.nextInt(255);
            if ( (c >= '0' && c <='9') || (c >='A' && c <='Z') ){
                cadenaAleatoria += c;
                i ++;
            }
        }
        return cadenaAleatoria;
    }

}
