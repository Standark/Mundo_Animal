<%-- 
    Document   : productoDescripcion
    Created on : 26-may-2016, 11:36:02
    Author     : Dawn_
--%>

<%@page import="Modelo.Producto"%>
<%@page import="java.util.ArrayList"%>
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
                <table>
                    <tr>
                        <td><%@include file="menuLateral.jsp" %></td>
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
                                <tr><td><p>Telefono:</p></td><td><p> <%=usuario.getTelefono()%></p></td></tr>
                                <tr><td><p>Fecha de nacimiento:</p></td><td><p>  <%=usuario.getFechaNac()%></p></td></tr>
                            </table>
                        </td>
                    </tr>
                    <% ArrayList<Producto> lista = (ArrayList<Producto>) sesion.getAttribute("listaComprados");%>
                    <tr>
                        <td><h3>Lista de productos que has comprado:&nbsp;</h3></td>
                    </tr> 
                    <% if (!lista.isEmpty()) {%>

                    <ul>
                        <% for (int i = 0; i < lista.size(); i++) {%>
                        <tr>
                            <td><li><b><%=lista.get(i).getNombre()%></b></td>
                        <td><b><%=lista.get(i).getDescripcion()%></b></td>
                        <td>Precio unidad: <b><%=lista.get(i).getPrecio()%></b></td>
                        <td><b><img src="<%=lista.get(i).getImagen()%>" width="250" height="250"></b></td>
                        <td><b>

                                <%--<a href="Cesta?action=add&producto=<%=String.valueOf(listProd.get(i).getId())%>">Añadir al carrito</a>--%>
                                <form  action="Cesta" method="post">

                                    <input type="hidden" name= "producto" value="<%=String.valueOf(lista.get(i).getId())%>">
                                    <input type="hidden" name="animal" value="<%=String.valueOf(lista.get(i).getAnimal())%>">
                                    <input type="hidden" name ="categoria"value="<%=String.valueOf(lista.get(i).getCategoria())%>">
                                    <input type="hidden" name="action" value="add">
                                    <button class ="btn" type="submit" value="añadir" name="btnAñadir">Añadir de nuevo al carrito</button>
                                            <input  class ="btn" type="button" value="Comentar Producto" onClick="location.href = 'valoracion.jsp'"<%
                                        sesion.setAttribute("idProducto", lista.get(i).getId()); %>>
                                </form>
                            </b></li>
                        </td>
                        <%}%>
                        </tr>
                    </ul>
                    <%} else {%>
                    <p class="textosCentrados">No hay productos disponibles de esta categoría.</p>
                    <%}%>
                </table>

            </div>
        </div>
        <!-- end #page -->
        <%@include file="footer.jsp" %>
    </body>
</html>
