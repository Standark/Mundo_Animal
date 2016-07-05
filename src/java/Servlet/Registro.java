/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Modelo.Usuario;
import ModeloDB.UsuarioDB;
import ModeloDB.UsuarioDB;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Dawn_
 */
@WebServlet(name = "Registro", urlPatterns = {"/Registro"})

public class Registro extends HttpServlet {

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
            // En este caso de uso, el usuario NO tiene que haber iniciado sesion
            // por eso comprobamos que quien llegue a este servlet no este metido en sesion
            if (request.getParameter("nombre") != null) {
                //Obtenemos el contenido de los cuadros de texto que nos llegan de la pagina jsp
                // se utiliza request por que esta acción tiene el alcance de una única petición
                String nombre = request.getParameter("nombre");
                String apellidos = request.getParameter("apellido");
                String nick = request.getParameter("nick");
                String password = request.getParameter("password");
                String direccion = request.getParameter("direccion");
                int cp = Integer.parseInt(request.getParameter("codigo"));
                String mail = request.getParameter("email");
                String ciudad = request.getParameter("ciudad");
                String provincia = request.getParameter("provincia");
                int telefono = Integer.parseInt(request.getParameter("telefono"));
                String dateInString = request.getParameter("nacimiento");

                SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");
                Date fechaNac = null;
                try {
                    fechaNac = (Date) formatter.parse(dateInString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                //Password repetida no se recupera por que ya se ha comprobado con javascript en la pagina
                //jsp que el usuario ha introducido la misma contraseña dos veces y no tiene sentido recuperarla 
                //en este servlet
                System.out.println("Comprobamos el nick...");
                if (UsuarioDB.isUsuarioRegistrado(nick)) {
                    request.setAttribute("textoError", "Ya hay un usuario registrado con ese nick.");
                    this.url = "/error.jsp";
                    System.out.println("Ya hay usuario con ese nick.");
                } else {
                    System.out.println("No hay usuario con ese nick.");
                }

                //Guardamos los datos en la BD, al insertar la función puede devolver dos valores, 0 si no ha
                //conseguido insertarlo y 1 en caso contrario
                Usuario nuevoUsuario = new Usuario(nombre, apellidos, nick, password, direccion, cp, mail, ciudad, provincia, telefono, fechaNac);
                if (UsuarioDB.insertar(nuevoUsuario) == 0) {
                    request.setAttribute("textoError", "No se han guardado correctamente los datos.");
                    this.url = "/error.jsp";
                } else {

                    try (PrintWriter out = response.getWriter()) {
                        /* TODO output your page here. You may use following sample code. */
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Registro realizado</title>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h1>Te has registrado correctamente " + request.getContextPath() + "</h1>");
                        out.println("</body>");
                        out.println("</html>");
                    }

                }
                // Si el usuario que llega a este servlet esta registrado, no tiene sentido que lo vuelva a hacer, por eso
                // redirigimos a su peril
            } else {
                this.url = "/perfil.jsp";
            }

            //En caso de algun error no previsto , redireccionamos a una pagina destinada a este fin
        } catch (Exception e) {

            request.setAttribute("textoError", "Inesperado, contacte con el administrador.");
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
