package servlet.travelbook;

import com.sun.mail.util.MailSSLSocketFactory;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CtrlEnviarCorreo", urlPatterns = {"/CtrlEnviarCorreo"})
public class CtrlEnviarCorreo extends HttpServlet {

     private String url;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, MessagingException {
        try{
            Properties props = new Properties();
            props.put("mail.transport.protocol", "smtps");
            props.put("mail.smtps.host", "smtp.gmail.com");
            props.put("mail.smtps.port", 465);
            props.put("mail.smtps.auth", "true");
            props.put("mail.smtps.quitwait", "false");
            
//          MailSSLSocketFactory socketFactory= new MailSSLSocketFactory();
//          socketFactory.setTrustAllHosts(true);
//          props.put("mail.pop3s.ssl.socketFactory", socketFactory);
//          props.setProperty("mail.smtp.ssl.trust", "smtpserver");
            
            
            Session session = Session.getDefaultInstance(props);
            session.setDebug(true);

            //Crear el mensaje
            Message mensaje = new MimeMessage(session);
            //Fijar el asunto
            mensaje.setSubject("Confirmación de registro en TravelBook");
            //Fijar el contenido del mensaje
            mensaje.setContent("<h2>Se acaba de registrar en travelBook.</h2><br><p> Por favor, haga click en la siguiente url para confirmar su registro:<a href= http://localhost:8084/TravelBookPrototipo2/CtrlConfirmarRegistro?cod="+request.getAttribute("codigo")+"&usuario="+request.getAttribute("nombreUsuario")+">Confirmación</a></p>","text/html");
            //Remitente
            Address remitente = new InternetAddress("travelbookcorreo@gmail.com");
            mensaje.setFrom(remitente);
            //Destinatario
            Address destinatario = new InternetAddress((String) request.getAttribute("email"));
            mensaje.setRecipient(Message.RecipientType.TO, destinatario);

            //Enviar mensaje
            //Transport transport = session.getTransport("smtps");
            //transport.connect ("smtp.gmail.com", 465, "travelbookcorreo@gmail.com", "travelbook2015");
            Transport transport = session.getTransport();
            transport.connect("travelbookcorreo@gmail.com", "travelbook2015");
            transport.sendMessage(mensaje, mensaje.getAllRecipients());
            transport.close();
            //Reenviamos al usuario a una pagina donde la informaremos de que recibirá un email para terminar el registro
            request.setAttribute("textoInformacion", "En breve recibirá un correo a su email para poder terminar el proceso de registro.");
            request.setAttribute("siguienteUrl", "index.jsp");
            this.url = "/informacion.jsp";
        }catch (Exception e) {

            request.setAttribute("textoError", "Se ha producido un error, por favor vuelva a intentarlo");
            this.url = "/error.jsp";
            
        // Sea cual sea el resultado del procesamiento del servlet, se redirecciona a la página que se haya especificado
        // en la variable url
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
        try {
            processRequest(request, response);
        } catch (MessagingException ex) {
            Logger.getLogger(CtrlEnviarCorreo.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (MessagingException ex) {
            Logger.getLogger(CtrlEnviarCorreo.class.getName()).log(Level.SEVERE, null, ex);
        }
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
