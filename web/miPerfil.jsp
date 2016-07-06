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
            <% Usuario usuario = (Usuario) request.getAttribute("usur");%>
            <table>
                <tr>
                <td><%@include file="menuPerfil.jsp" %></td>
                <td><p>Nombre:<%=usuario.getNombre()%></p></td>
                <td><p>Nombre:<%=usuario.getApellidos()%></p></td>
                <td><p>Nombre:<%=usuario.getNick()%></p></td>
                <td><p>Nombre:<%=usuario.getDireccion()%></p></td>
                <td><p>Nombre:<%=usuario.getCP()%></p></td>
                <td><p>Nombre:<%=usuario.getMail()%></p></td>
                <td><p>Nombre:<%=usuario.getCiudad()%></p></td>
                <td><p>Nombre:<%=usuario.getProvincia()%></p></td>
                <td><p>Nombre:<%=usuario.getTelefono()%></p></td>
                <td><p>Nombre:<%=usuario.getFechaNac()%></p></td>
                </tr>
            </table>
        </div>
	<!-- end #page -->
        <%@include file="footer.jsp" %>
       </body>
</html>
