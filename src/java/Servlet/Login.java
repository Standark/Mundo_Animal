/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Modelo.DetPed;
import Modelo.Pedido;
import Modelo.Producto;
import Modelo.Usuario;
import ModeloDB.DetPedDB;
import ModeloDB.PedidoDB;
import ModeloDB.ProductoDB;
import ModeloDB.UsuarioDB;
import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet(name = "Login", urlPatterns = {"/Login"})

public class Login extends HttpServlet {

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
        this.url = "/index.jsp";
        try {
            HttpSession sesion = request.getSession();
            Usuario usuario = (Usuario) sesion.getAttribute("usuario");
            if (usuario == null) {
                // Recuperamos del formulario de acceso.html el usuario y la contraseña introducidas por el usuario
                String nombreUsuario = request.getParameter("nick");
                String clave = request.getParameter("password");

                //En primer lugar comprobamos si el usuario registrado existe en la base de datos
                if (!UsuarioDB.isUsuarioRegistrado(nombreUsuario)) {
                    //Si el usuario no existe en la base de datos , redireccioamos a la pagina de error
                    request.setAttribute("textoError", "No existe el usuario en la base de datos");
                    this.url = "/error.jsp";
                } else if (!UsuarioDB.isClaveCorrecta(nombreUsuario, clave)) {
                    //Si el usuario no ha introducido de forma correcta su clave , redireccionamos a la pagina de error
                    request.setAttribute("textoError", "La clave introducida no es correcta, por favor vuelva a introducirla");
                    this.url = "/error.jsp";
                } else {
                    //Una vez se ha comprobado que el usuario existe y que ha introducido su contraseña de forma correcta se inicia la sesión
                    Usuario nuevoUsuario = UsuarioDB.getUsuarioPorNick(nombreUsuario);
                    this.url = "/miPerfil.jsp";
                    request.setAttribute("usuario", nuevoUsuario);
                    sesion.setAttribute("usuario", nuevoUsuario);

                    ArrayList<DetPed> listaPedidos = DetPedDB.buscarDetPedPorCliente(nuevoUsuario.getId());
                    System.out.println(listaPedidos);
                    ArrayList<Producto> listaProductos = new ArrayList<>();
                    for (int i = 0; i < listaPedidos.size(); i++) {
                        listaProductos.add(ProductoDB.getProductoPorId(listaPedidos.get(i).getIdProducto()));
                    }
                    sesion.setAttribute("listaComprados", listaProductos);
                }
                // Si el usuario introduce la direccion de este servlet de forma manual en la barra de direcciones 
                // o llega como resultado de una "navegación hacia atras"  le redirigimos a la página correspondiente
            } else {
                request.setAttribute("textoError", "Ya estas logueado con otra cuenta.");
                this.url = "/error.jsp";
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
