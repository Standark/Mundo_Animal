package Servlet;

import Modelo.DetPed;
import Modelo.Pedido;
import Modelo.Producto;
import Modelo.Usuario;
import ModeloDB.DetPedDB;
import ModeloDB.PedidoDB;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author David
 */
public class RealizarPedido extends HttpServlet {

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
        try{
        HttpSession sesion = request.getSession();
        String url = null;
        
        if(sesion.getAttribute("usuario") != null){
            
            //Otenemos la fecha actual y la transformamos a formato DATE de sql      
            
            java.util.Date dates = new java.util.Date();
            
            Date date = new java.sql.Date(dates.getTime());
            
            
            
            /*Obtenemos el usuario logueado de la sesión, creamos e instertamos
              el pedido en la BD con id=0(default)*/
            Usuario user = (Usuario) sesion.getAttribute("usuario");
            
            Pedido pedido = new Pedido(0,user.getId(),(java.sql.Date)date);
            PedidoDB.insertar(pedido);
            /*Recuperamos el pedido insertado para obtener el id real
              (lo genera automaticamente la BD al insertar)*/
            pedido = PedidoDB.getPedidoPorIdCliente(user.getId());
            
            /*Obtenemos los productos y por cada uno lo introducimos en
              Detalles pedido de la BD*/
            Map<Producto, Integer> prods = (HashMap<Producto,Integer>) sesion.getAttribute("prods");
            Iterator <Entry<Producto,Integer>> it = prods.entrySet().iterator();
            Entry<Producto,Integer> entry = null;
            Producto prod = null;
            Integer cant = null;
                        
            while(it.hasNext()){
                entry =it.next();
                prod= entry.getKey();
                cant = entry.getValue();
                DetPed detalle = new DetPed(0,pedido.getId(),prod.getId(),cant,prod.getPrecio());
                DetPedDB.insertar(detalle);
            }
            url = "/exito.jsp";
            request.setAttribute("textoExito", "Tramitar su pedido");
        }else{
            url="/loginORegistro.jsp";
        }
        RequestDispatcher respuesta = getServletContext().getRequestDispatcher(url);
        respuesta.forward(request, response);
        }catch(Exception e){
            e.printStackTrace();
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
