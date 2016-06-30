package servlet.travelbook;

import dominio.Diario;
import dominio.UsuarioRegistrado;
import ModeloDB.Travelbook.DiarioBD;
import ModeloDB.Travelbook.UsuarioRegistradoBD;
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
@WebServlet(name = "CtrlBuscarDiario", urlPatterns = {"/CtrlBuscarDiario"})
public class CtrlBuscarDiario extends HttpServlet {

    private String url;

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

        try {
            //Obtenemos la sesión actual
            HttpSession sesion = request.getSession();

            // En este caso de uso, el usuario tiene que haber iniciado sesion
            // por eso comprobamos que quien llegue a este servlet este metido en sesion
            String accion = request.getParameter("accion");
            switch (accion) {
                case "Buscar":
                    //Guarda los parametros de la vista en variables para poder tratarlas y meterlas en la BD
                    String s = request.getParameter("s");
                    List<Diario> resultado = DiarioBD.buscarDiarios(s);
                    request.setAttribute("listado", resultado);
                    this.url = "/diarios.jsp";
                    break;
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
