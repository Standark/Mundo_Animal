<%-- 
    Document   : productoDescripcion
    Created on : 26-may-2016, 11:36:02
    Author     : Dawn_
--%>

<%@page import="Modelo.Producto"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="keywords" content="" />
        <meta name="description" content="" />
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <title>Cátalogo</title>
        <link href="style.css" rel="stylesheet" type="text/css" media="screen" />
    </head>
    <body>
        <div id="wrapper">

            <%@include file="header.jsp" %>
            <%@include file="menu.jsp" %>

            <div id="page">
                <div id="page-bgtop">
                    <div> <!-- end #content -->
                        <table id="pagina" > 
                            <tr>
                                <td valign="top" >
                                    <%@include file="menuLateral.jsp" %>

                                </td>
                                <td rowspan="3"  width= "4000 px">
                                    <div id="producto">
                                        <table id ="producto-flitros" >

                                            <% String animal = (String) request.getAttribute("animal");%>

                                            <% ArrayList<Producto> listProd = (ArrayList<Producto>) request.getAttribute("listProd");%>
                                            
                                            <h3>Lista de productos para el animal:&nbsp;<b><%=animal%></b></h3>
                                             
                                            <% if (!listProd.isEmpty()) {%>

                                            <ul>
                                                <% for (int i = 0; i < listProd.size(); i++) {%>
                                                <li>Nombre producto: <b><%=listProd.get(i).getNombre()%></b> ----- Descripción: <b><%=listProd.get(i).getDescripcion()%></b> ----- Precio: <b><%=listProd.get(i).getPrecio()%></b><b>Imagen:<img src="<%=listProd.get(i).getImagen()%>" width="250" height="250"></b><b><button class ="btn" type="submit" >Añadir al carrito</button></b></li>
                                                    <%}%>
                                            </ul>
                                            <%} else {%>
                                            <p class="textosCentrados">No hay productos disponibles de esta categoría.</p>
                                            <%}%>

                                           
                                          

                                        </table>
                                        <tr>

                                            <td  style="width: 200px;height :100%">
                                                <img src="images/paypal.png" width="155" height="85" alt=""/>

                                            </td>

                                        </tr>
                        </table>
                    </div>
                </div>
                <!-- end #sidebar -->
                <div style="clear: both;">
                    <br>
                    <br>
                </div>
            </div>
        </div>


        <%@include file="footer.jsp" %>

    </body>

</html>
