package servlet.travelbook;

import ModeloDB.Travelbook.UsuarioRegistradoBD;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CtrlConfirmarRegistro", urlPatterns = {"/CtrlConfirmarRegistro"})
public class CtrlConfirmarRegistro extends HttpServlet {

        
    private String url;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            //Comprobar que el usuario y el codigo es el correcto. Si es así poner activado a true y crear la sesión del usuario
            //redireccionándolo a miperfil.jsp
                String codigo = request.getParameter("cod");
                String usuario = request.getParameter("usuario");
                if (UsuarioRegistradoBD.comprobarRegistro(codigo, usuario) == false) {
                    request.setAttribute("textoError", "El código de activación no coincide con el enviado por el usuario.");
                    this.url = "/error.jsp";
                } else {
                     //Si el codigo es el correcto, redirigimos a la siguiente página lógica, en este caso
                    // para que no tenga que volver a loguearse, iniciamos la sesión del usuario y le activamos la cuenta
                    UsuarioRegistradoBD.activarCuenta(usuario);
                    HttpSession sesion = request.getSession();
                    sesion.setAttribute("nombreUsuario", usuario);
                    this.url = "/miperfil.jsp";
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
