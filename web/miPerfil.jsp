<%-- 
    Document   : productoDescripcion
    Created on : 26-may-2016, 11:36:02
    Author     : Dawn_
--%>

<%@page import="Modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Perfil</title>
<link href="style.css" rel="stylesheet" type="text/css" media="screen" />
</head>
  <body>
    <div id="wrapper">
	
	<%@include file="header.jsp" %>
	<%@include file="menu.jsp" %>
        <div id="page">
            <% Usuario usuario = (Usuario) request.getAttribute("usuario");%>
            <table>
                <tr>
                <td><%@include file="menuPerfil.jsp" %></td>
                <td>
                    <table>
                        <tr><td><p>Nombre:</p></td><td><p> <%=usuario.getNombre()%></p></td></tr>
                        <tr><td><p>Apellido:</p></td><td><p> <%=usuario.getApellidos()%></p></td></tr>
                        <tr><td><p>Nick:</p></td><td><p> <%=usuario.getNick()%></p></td></tr>
                        <tr><td><p>Dirreccion:</p></td><td><p> <%=usuario.getDireccion()%></p></td></tr>
                        <tr><td><p>Codigo Postal:</p></td><td><p> <%=usuario.getCP()%></p></td></tr>
                        <tr><td><p>eMail:</p></td><td><p> <%=usuario.getMail()%></p></td></tr>
                        <tr><td><p>Ciudad:</p></td><td><p> <%=usuario.getCiudad()%></p></td></tr>
                        <tr><td><p>Provincia:</p></td><td><p> <%=usuario.getProvincia()%></p></td></tr>
                        <tr><td><p>Telefono:</p></td><td><p> <%=usuario.getTelefono()%></p></td></tr><
                        <tr><td><p>Fecha de nacimiento:</p></td><td><p>  <%=usuario.getFechaNac()%></p></td></tr>
                    </table>
                </td>
                </tr>
            </table>
        </div>
    </div>
	<!-- end #page -->
        <%@include file="footer.jsp" %>
       </body>
</html>
