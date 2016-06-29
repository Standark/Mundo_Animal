/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.travelbook;

import dominio.Dia;
import dominio.Diario;
import dominio.Foto;
import dominio.PuntuacionCategoria;
import dominio.VerDiario;
import dominio.Video;
import dominio.Visitas;
import dominioBD.DiaBD;
import dominioBD.DiarioBD;
import dominioBD.FotoBD;
import dominioBD.ValoracionBD;
import dominioBD.VideoBD;
import dominioBD.VisitasBD;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author JuanPablo
 */
@WebServlet(name = "CtrlVerDiarioPublicado", urlPatterns = {"/CtrlVerDiarioPublicado"})
public class CtrlVerDiarioPublicado extends HttpServlet {

    private String url;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession sesion = request.getSession();
            //Si el usuario ha pinchado en la opcion "Mis diarios"...
            if (request.getParameter("verDiarioId") != null) {
                //Para facilitar la modularización de la información que enviaremos a la vista, crearemos
                //un dtd con los atributos necesarios
                ArrayList<VerDiario> listaLugaresPuntuados = new ArrayList<VerDiario>();

                //Comenzamos la recuperación de datos asociados al dia seleccionado por el usuario
                int idDiario = Integer.parseInt(request.getParameter("verDiarioId"));
                //Recuperamos el diario con el objetivo de obtener el autor
                Diario diario = DiarioBD.getDiarioPorId(idDiario);
                List<Dia> listaDias = DiaBD.getDiasDelDiarioPorId(idDiario);
                
                //Para poder mostrar al inicio el primer día, recuperamos el contenido
                //del primer día del viaje
                if (!listaDias.isEmpty()) {
                    Dia d = listaDias.get(0);

                    int idDiaInicial = d.getId();
                    Foto foto = FotoBD.getFotoDelDia(idDiaInicial);
                    Video video = VideoBD.getVideoDelDia(idDiaInicial);
                    //Obtenemos la lista de lugares visitados por el usuario el dia seleccionado
                    List<Visitas> listaVisitas = VisitasBD.getVisitasDelDia(String.valueOf(idDiaInicial));
                    //Recuperamos las puntuaciones asociadas a cada uno de los sitios visitados el dia deseado
                    for (Visitas v : listaVisitas) {
                        VerDiario vd = new VerDiario();
                        vd.setLugar(v.getLugar());
                        vd.setRecomendacionDia(v.getRecomendacion());
                        vd.setFechaRecomendacion(v.getFechaRecomendacion());
                        ArrayList<PuntuacionCategoria> listaValoraciones = ValoracionBD.obtenerValoracionesDia(v.getLugar(), v.getCiudad(),
                                v.getPais(), diario.getNombreUsuario(), idDiaInicial);
                        vd.añadirValoracionesLugar(listaValoraciones);
                        listaLugaresPuntuados.add(vd);
                    }
                    //Devolvemos los datos a la vista
                    request.setAttribute("listaDias", listaDias);
                    request.setAttribute("dia", d);
                    request.setAttribute("foto", foto);
                    request.setAttribute("video", video);
                    request.setAttribute("listaLugaresPuntuados", listaLugaresPuntuados);
                    request.setAttribute("idDiario", diario.getId());
                    this.url = "/verDiarioPublicado.jsp";
                    //Si el diario seleccionado aun no tiene dias...
                } else {
                    request.setAttribute("textoInformacion", "El diario no tiene dias registrados");
                    request.setAttribute("siguienteUrl", "index.jsp");
                    this.url = "/informacion.jsp";
                }

                //Si el usuario ha seleccionado un dia en concreto del diario...
            } else if (request.getParameter("verDiaId") != null) {

                //Para facilitar la modularización de la información que enviaremos a la vista, crearemos
                //un dtd con los atributos necesarios
                ArrayList<VerDiario> listaLugaresPuntuados = new ArrayList<VerDiario>();

                //Comenzamos la recuperación de datos asociados al dia seleccionado por el usuario
                int idDiario = Integer.parseInt(request.getParameter("idDiario"));
                //Recuperamos el diario con el objetivo de obtener el autor
                Diario diario = DiarioBD.getDiarioPorId(idDiario);
                List<Dia> listaDias = DiaBD.getDiasDelDiarioPorId(idDiario);
                //Para poder mostrar al inicio el primer día, recuperamos el contenido
                //del primer día del viaje
                Dia d = DiaBD.getDia(request.getParameter("verDiaId"));
                int idDia = d.getId();
                Foto foto = FotoBD.getFotoDelDia(idDia);
                Video video = VideoBD.getVideoDelDia(idDia);
                //Obtenemos la lista de lugares visitados por el usuario el dia seleccionado
                List<Visitas> listaVisitas = VisitasBD.getVisitasDelDia(String.valueOf(idDia));
                //Recuperamos las puntuaciones asociadas a cada uno de los sitios visitados el dia deseado
                for (Visitas v : listaVisitas) {
                    VerDiario vd = new VerDiario();
                    vd.setLugar(v.getLugar());
                    vd.setRecomendacionDia(v.getRecomendacion());
                    vd.setFechaRecomendacion(v.getFechaRecomendacion());
                    ArrayList<PuntuacionCategoria> listaValoraciones = ValoracionBD.obtenerValoracionesDia(v.getLugar(), v.getCiudad(),
                            v.getPais(), diario.getNombreUsuario(), idDia);
                    vd.añadirValoracionesLugar(listaValoraciones);
                    listaLugaresPuntuados.add(vd);
                }
                //Devolvemos los datos a la vista
                request.setAttribute("listaDias", listaDias);
                request.setAttribute("dia", d);
                request.setAttribute("foto", foto);
                request.setAttribute("video", video);
                request.setAttribute("listaLugaresPuntuados", listaLugaresPuntuados);
                request.setAttribute("nombreUsuario", diario.getNombreUsuario());
                request.setAttribute("idDiario", diario.getId());
                this.url = "/verDiarioPublicado.jsp";
            } else {
                this.url = "/index.jsp";
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
