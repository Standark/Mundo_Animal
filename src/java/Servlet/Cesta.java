/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Modelo.Producto;
import ModeloDB.ProductoDB;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
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
public class Cesta extends HttpServlet {

    //private Map<Producto, Integer> prods = new HashMap<Producto, Integer>();
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
        String action = request.getParameter("action");

        if (action.equals("mostrar")) {
            mostrarCarrito(request, response);
        } else if (action.equals("add")) {
            addProduct(request, response);

        }
    }

    private void mostrarCarrito(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/cesta.jsp";

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    private void addProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String categoria = request.getParameter("categoria");
        String animal = request.getParameter("animal");
        String url;
        String url1 = "ObtenerProductos?animal=" + animal;

        if (!categoria.equals("null")) {
            url = url1 + "&categoria=" + categoria;
        } else {
            url = url1;
        }

        HttpSession sesion = request.getSession();
        Map<Producto, Integer> prods;
        Producto prod = ProductoDB.getProductoPorId(Integer.parseInt(request.getParameter("producto")));

        if (sesion.getAttribute("prods") == null) {
            prods = new HashMap<>();
        } else {
            prods = (HashMap<Producto, Integer>) sesion.getAttribute("prods");
        }

        Iterator<Entry<Producto, Integer>> it = prods.entrySet().iterator();
        Entry<Producto, Integer> entry = null;
        Boolean flag = true;
        while (it.hasNext()) {
            entry = it.next();
            if (entry.getKey().getId() == prod.getId()) {
                prods.put(entry.getKey(), entry.getValue() + 1);
                flag = false;
            }
        }
        if (flag) {
            prods.put(prod, 1);
        }
        sesion.setAttribute("prods", prods);
        response.sendRedirect(url);
        //RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        //dispatcher.forward(request, response);
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
