<%-- 
    Document   : productoDescripcion
    Created on : 26-may-2016, 11:36:02
    Author     : Dawn_
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Valorar Producto</title>
<link href="style.css" rel="stylesheet" type="text/css" media="screen" />
</head>
  <body>
    <div id="wrapper">
	
	<%@include file="header.jsp" %>
	<%@include file="menu.jsp" %>
    <div id="page">
		<div id="page-bgtop">
			<div> <!-- end #content -->
              
              
             <form method="post" action="cgi-bin/script.pl">
             <br>
             <br>
                <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;¡Cuéntanos tu experiencia con este producto!
                <br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Por favor, ten en cuenta que todas las valoraciones están sujetas a moderación para evitar lenguaje inapropiado y protección de datos.</p>
                <% Usuario usuario = (Usuario) sesion.getAttribute("usuario");
                request.setAttribute("idCliente",usuario.getId());%>
                <table id="valoracion" width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                <td>&nbsp;Tu valoración:</td>
                <td> 
                <div class="ec-stars-wrapper">
                                <a href="#" data-value="1" title="Votar con 1 estrellas" name="puntuacion">&#9733;</a>
                                <a href="#" data-value="2" title="Votar con 2 estrellas" name="puntuacion">&#9733;</a>
                                <a href="#" data-value="3" title="Votar con 3 estrellas" name="puntuacion">&#9733;</a>
                                <a href="#" data-value="4" title="Votar con 4 estrellas" name="puntuacion">&#9733;</a>
                                <a href="#" data-value="5" title="Votar con 5 estrellas" name="puntuacion">&#9733;</a>
                            </div>
                </td>
                </tr>
                <tr>
                    <td>&nbsp;Titulo de la valoración: </td>
                    <td>
                    <input type="text" name="titulo">
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;Comentarios: </td>
                    <td>
                    <textarea rows="3" name="comentario"></textarea>
                    </td>
                    </tr>
                    <%--<td colspan="2">
                    <input type="checkbox" name="pasa" value="publicarNombre">Mostrar nombre en la página
                    </td>--%>
                    <tr>
                    <td colspan="2">
                    <input type="checkbox" name="pasa" value="aceptoTerminos">Acepto los términos de uso
                    </td>
                    </tr>
                     
                    <td colspan="2" align="center">
                    <button class="btn" type="submit" value="Comentario">Guardar</button>
                    </td>
                
                
                
                 </table>
                 </form>
                 
                 
                    <td  style="width: 200px;height :100%">&nbsp;
            
              </div>
				<!-- end #sidebar -->
			  <div style="clear: both;">
              <br>
              <br>
              </div>
		  </div>
  </div>
  </div>


        <%@include file="footer.jsp" %>
       </body>
</html>
