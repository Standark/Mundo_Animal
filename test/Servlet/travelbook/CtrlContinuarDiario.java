/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.travelbook;

import dominio.Dia;
import dominio.Diario;
import dominio.Foto;
import dominio.SitioVisitado;
import dominio.Valoracion;
import dominio.Video;
import dominio.Visitas;
import ModeloDB.Travelbook.DiaBD;
import ModeloDB.Travelbook.DiarioBD;
import ModeloDB.Travelbook.FotoBD;
import ModeloDB.Travelbook.SitioVisitadoBD;
import ModeloDB.Travelbook.ValoracionBD;
import ModeloDB.Travelbook.VideoBD;
import ModeloDB.Travelbook.VisitasBD;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import utilidades.TratamientoFechas;

/**
 *
 * @author Hector
 */
@WebServlet(name = "CtrlContinuarDiario", urlPatterns = {"/CtrlContinuarDiario"})
@MultipartConfig
public class CtrlContinuarDiario extends HttpServlet {

    private String url;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            String encoding = request.getCharacterEncoding();
            if (encoding == null) {
                request.setCharacterEncoding("UTF-8");
            }

            HttpSession sesion = request.getSession();
            String nombreUsuario = (String) sesion.getAttribute("nombreUsuario");
            if (nombreUsuario != null) { //El usuario esta identificado

                String accion = request.getParameter("action");

                if (accion == null) {//No se ha llamado desde alguno de los botones submit
                    if (request.getParameter("cont") != null && request.getParameter("cont").equals("1")) { //Quiere continuar un diario, cargamos los datos necesarios para mostrarlos
                        Diario diarioEnCurso = DiarioBD.getDiarioEnCursoDe(nombreUsuario);
                        request.setAttribute("diarioEnCurso", diarioEnCurso);
                        if (diarioEnCurso != null) {
                            List<Dia> dias = DiaBD.getDiasDelDiario(diarioEnCurso);
                            request.setAttribute("dias", dias);
                        }
                        this.url = "/continuarDiario.jsp";

                    } else if (request.getParameter("idDia") != null) {//Esta pidiendo editar un dia que ya está en la base de datos
                        //Enviar dia
                        String idDia = (String) request.getParameter("idDia");
                        Dia dia = DiaBD.getDia(idDia);
                        request.setAttribute("dia", dia);

                        //Enviar visitas
                        List<Visitas> listado = VisitasBD.getVisitasDelDia(idDia);
                        request.setAttribute("visitas", listado);

                        //enviar foto
                        Foto foto = FotoBD.getFotoDelDia(Integer.parseInt(idDia));
                        String urlFoto = "";
                        if (foto != null) {
                            urlFoto = "usuarios" + File.separator + foto.getUrl();
                        }
                        request.setAttribute("urlFoto", urlFoto);

                        //enviar video
                        Video video = VideoBD.getVideoDelDia(Integer.parseInt(idDia));
                        String urlVideo = "";
                        if (video != null) {
                            urlVideo = video.getUrl();
                        }
                        request.setAttribute("urlVideo", urlVideo);

                        this.url = "/editarDia.jsp";

                    } else { //Habrá metido la direccion del servlet a mano, se le manda a la pagina del perfil.
                        this.url = "/miperfil.jsp";
                    }

                } else { //Se ha pulasado algun boton submit de algun formulario
                    switch (accion) {
                        case "Finalizar viaje":
                            int res = DiarioBD.finalizarDiarioEnCursoDe(nombreUsuario);
                            this.url = "/confirmacionFinViaje.jsp"; //Pagina que muestra que se finalizo correctamente
                            if (res != 1) {
                                request.setAttribute("textoError", "Ha habido un problema al finalizar el diario");
                                this.url = "/error.jsp";
                            }
                            break;

                        case "Guardar dia": //Puede ser los datos de un nuevo dia, o la edicion de uno ya existente
                            String tituloDia = request.getParameter("tituloDia");
                            String fechaDia = request.getParameter("fechaDia");
                            String pais[] = request.getParameterValues("pais");
                            String ciudad[] = request.getParameterValues("ciudad");
                            String lugar[] = request.getParameterValues("lugar");
                            String descripcion = request.getParameter("descripcion");

                            //Obtenenos el diario donde se metera el dia
                            Diario d;
                            if (request.getParameter("idDiario") != null) {
                                int idDiario = Integer.parseInt(request.getParameter("idDiario"));
                                d = DiarioBD.getDiarioPorId(idDiario);
                            } else {
                                d = DiarioBD.getDiarioEnCursoDe(nombreUsuario);
                            }

                            //Comprobamos que la fecha este dentro del rango del diario
                            boolean fechasValidas = TratamientoFechas.estaEntreIncluidas(fechaDia, d.getFechaInicio(), d.getFechaFin());
                            if (fechasValidas) { //Está entre el inicio y el fin del diario

                                int idDia; //id del dia (Solo si ya esta almacenado en la BD)
                                try {
                                    //Comprobamos si el usuario esta introduciendo un nuevo dia o se trata de un dia ya empezado previamente
                                    idDia = Integer.parseInt(request.getParameter("id"));
                                } catch (Exception e) {
                                    //Se trata de la insercción de un nuevo día
                                    idDia = -1;
                                }

                                if (request.getParameter("id") == null) {//Es un nuevo dia                                

                                    //Insertamos los datos del nuevo dia en la BD
                                    Dia dia = new Dia(-1, d.getId(), TratamientoFechas.stringToDate(fechaDia), tituloDia, descripcion);
                                    idDia = DiaBD.insert(dia);

                                    //Para todos los lugares visitados que ha introducido...
                                    for (int i = 0; i < pais.length; i++) {
                                        //Introducimos el lugar que visita en la BD, por si no estubiera ya.
                                        SitioVisitado sitioVisitado = new SitioVisitado(lugar[i], ciudad[i], pais[i]);
                                        SitioVisitadoBD.insertar(sitioVisitado); //Si ya existe el lugar, dará error y no lo meterá.(Lo ignoramos)

                                        //Metemos la nueva visita al lugar que ya estara en la BD
                                        Visitas visita = new Visitas(idDia, lugar[i], ciudad[i], pais[i], null, null);
                                        VisitasBD.insertar(visita);
                                    }
                                } else { //Está modificando un dia ya creado en el diario

                                    //Actualizamos los datos en la BD del dia
                                    Dia dia = new Dia(idDia, d.getId(), TratamientoFechas.stringToDate(fechaDia), tituloDia, descripcion);
                                    DiaBD.update(dia);

                                    //Recuperamos las visitas que tenia antes el dia
                                    List<Visitas> listado = VisitasBD.getVisitasDelDia(Integer.toString(idDia));

                                    //Recuperamos los valores que tenian antes, para saber si han cambiado y actualizarlas
                                    String paish[] = request.getParameterValues("paish");
                                    String ciudadh[] = request.getParameterValues("ciudadh");
                                    String lugarh[] = request.getParameterValues("lugarh");

                                    for (int i = 0; i < pais.length; i++) {

                                        if (paish[i].isEmpty() && ciudadh[i].isEmpty() && lugarh[i].isEmpty()) {
                                                //Es un nuevo lugar visitado, por lo tanto solo hay que meterle nuevo

                                            //Metemos el lugar por si no estaba
                                            SitioVisitado sitioVisitado = new SitioVisitado(lugar[i], ciudad[i], pais[i]);
                                            SitioVisitadoBD.insertar(sitioVisitado);

                                            //metemos la neuva visita
                                            Visitas v = new Visitas(idDia, lugar[i], ciudad[i], pais[i], null, null);
                                            VisitasBD.insertar(v);

                                        } else if (!pais[i].equals(paish[i]) || !ciudad[i].equals(ciudadh[i]) || !lugar[i].equals(lugarh[i])) {
                                                //El usuaario ha cambiado alguno de los campos, hay que actualizarlo (eliminando la anterior y poniendo una nueva)
                                            //se elimina tambien la valoracion que tuviera dicha visita.

                                            //Eliminamos visita con los datos antiguos
                                            Visitas visitaBorrar = new Visitas(idDia, lugarh[i], ciudadh[i], paish[i], null, null);
                                            VisitasBD.borrar(visitaBorrar);

                                            //Eliminamos valoracion de la visita antigua
                                            Valoracion valoracion = new Valoracion(-1, null, nombreUsuario, lugarh[i], ciudadh[i], paish[i], null, 0);
                                            ValoracionBD.borrarValoracionesDeVisita(valoracion);

                                            //Metemos el nuevo lugar por si no estubiera
                                            SitioVisitado sitioVisitado = new SitioVisitado(lugar[i], ciudad[i], pais[i]);
                                            SitioVisitadoBD.insertar(sitioVisitado);

                                            //Metemos la nueva visita a dicho lugar
                                            Visitas v = new Visitas(idDia, lugar[i], ciudad[i], pais[i], null, null);
                                            VisitasBD.insertar(v);

                                        }
                                    }

                                    //Eliminar las visitas que el usuario ha eliminado
                                    for (int i = 0; i < listado.size(); i++) {
                                        Visitas v = listado.get(i);
                                        boolean esta = false;
                                        for (int j = 0; j < lugarh.length; j++) {
                                            if (v.getLugar().equals(lugarh[j]) && v.getCiudad().equals(ciudadh[j]) && v.getPais().equals(paish[j])) {
                                                esta = true; //Entonces no la ha eliminado (como mucho la ha cambiado, y ya se actualiza antes)
                                            }
                                        }
                                        if (!esta) {
                                            VisitasBD.borrar(v);
                                            Valoracion valoracion = new Valoracion(-1, null, nombreUsuario, v.getLugar(), v.getCiudad(), v.getPais(), null, 0);
                                            ValoracionBD.borrarValoracionesDeVisita(valoracion);
                                        }
                                    }
                                }

                                //FOTO Subimos o modificamos la foto (Si la hubiera añadido)                                    
                                Part filePart = request.getPart("file");
                                String fileName = getFileName(filePart);
                                if (fileName != null && !fileName.isEmpty()) {//EL usuario ha añadido una foto
                                    String path = this.getServletContext().getRealPath("/usuarios/");
                                    String titulo = d.getTitulo();
                                    String route = File.separator + nombreUsuario + File.separator + "diarios" + File.separator + titulo + File.separator + "fotos";
                                    path += route;

                                    InputStream fileContent = filePart.getInputStream();
                                    OutputStream outFile = null;
                                    try {
                                        outFile = new FileOutputStream(new File(path + File.separator + fileName));
                                        int read = 0;
                                        byte[] bytes = new byte[1024];
                                        while ((read = fileContent.read(bytes)) != -1) {
                                            outFile.write(bytes, 0, read);
                                        }

                                        //Añadimos la informacion de la foto a la Base de datos
                                        String direccion = route + File.separator + fileName;
                                        Foto foto = new Foto(direccion, idDia, "");
                                        int cambios = FotoBD.update(foto);
                                        if (cambios == 0) { //No habia una foto anterior, por lo que no existe la entrada en la BD
                                            FotoBD.insert(foto);
                                        }
                                    } catch (FileNotFoundException fne) {
                                        System.out.println(fne);
                                    } finally {
                                        if (outFile != null) {
                                            outFile.close();
                                        }
                                        if (fileContent != null) {
                                            fileContent.close();
                                        }
                                    }
                                }

                                //VIDEO, si el usuario ha añadido algo al textarea lo guardamos como URL (SE DEBERIA DE VALIDAR QUE ES CORRECTA!!!)
                                String videoUrl = request.getParameter("videoUrl");
                                if (videoUrl != null && !videoUrl.isEmpty()) {//Ha metido algo en el campo
                                    Video video = new Video(videoUrl, idDia, "");
                                    int cambios = VideoBD.update(video);
                                    if (cambios == 0) {
                                        VideoBD.insert(video);
                                    }
                                }

                                List<Visitas> visitas = VisitasBD.getVisitasSinValorarDelDia(nombreUsuario, idDia);
                                if (visitas.isEmpty()) {
                                    Diario diarioEnCurso = DiarioBD.getDiarioEnCursoDe(nombreUsuario);
                                    request.setAttribute("diarioEnCurso", diarioEnCurso);
                                    if (diarioEnCurso != null) {
                                        List<Dia> dias = DiaBD.getDiasDelDiario(diarioEnCurso);
                                        request.setAttribute("dias", dias);
                                    }
                                    this.url = "/continuarDiario.jsp";
                                } else {
                                    sesion.setAttribute("lugares", visitas);
                                    this.url = "/valorarLugares.jsp";
                                }

                            } else {
                                request.setAttribute("textoError", "La fecha introducida no esta dentro de las fechas del diario");
                                this.url = "/error.jsp";
                            }
                            break;

                        case "Agregar dia":
                            this.url = "/agregarNuevoDia.jsp";
                            break;

                        case "Actualizar Diario":
                            int idD = Integer.parseInt(request.getParameter("idDiario"));
                            String tituloViaje = request.getParameter("tituloViaje");
                            Date fechaI = TratamientoFechas.stringToDate(request.getParameter("fechaInicio"));
                            Date fechaF = TratamientoFechas.stringToDate(request.getParameter("fechaFin"));
                            String descrip = request.getParameter("descripcion");

                            if (fechaI.before(fechaF)) {
                                DiarioBD.modificarDiario(idD, tituloViaje, TratamientoFechas.dateToSqlDate(fechaI), TratamientoFechas.dateToSqlDate(fechaF), descrip);
                                Diario diarioEnCurso = DiarioBD.getDiarioEnCursoDe(nombreUsuario);
                                request.setAttribute("diarioEnCurso", diarioEnCurso);
                                if (diarioEnCurso != null) {
                                    List<Dia> dias = DiaBD.getDiasDelDiario(diarioEnCurso);
                                    request.setAttribute("dias", dias);
                                }
                                this.url = "/continuarDiario.jsp";
                            } else {
                                request.setAttribute("textoError", "La fecha de inicio del diario tiene que ser anterior a la de fin del diario.");
                                this.url = "/error.jsp";
                            }
                            break;

                        default:
                            this.url = "/miperfil.jsp";
                            break;
                    }
                }

            } else {
                this.url = "/acceso.jsp";
            }
        } catch (Exception e) {
            request.setAttribute("textoError", e);
            this.url = "/error.jsp";
        } finally {
            RequestDispatcher respuesta = getServletContext().getRequestDispatcher(this.url);
            respuesta.forward(request, response);
        }
    }

    private static String getFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1);
            }
        }
        return null;
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
