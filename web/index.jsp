

<%@page import="ModeloDB.ProductoDB"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Modelo.Producto"%>
<%-- 
    Document   : index
    Created on : 26-may-2016, 11:23:54
    Author     : Dawn_
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="keywords" content="" />
        <meta name="description" content="" />
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <title>Tienda</title>
        <link href="style.css" rel="stylesheet" type="text/css" media="screen" />
        <script src="js/jquery-1.5.2.min.js" type="text/javascript"></script>
        <script src="js/jcarousellite_1.0.1c5.js" type="text/javascript"></script>
        <script type="text/javascript">

            $(function () {
                $("#slidebox").jCarouselLite({
                    vertical: false,
                    hoverPause: true,
                    btnPrev: ".previous",
                    btnNext: ".next",
                    visible: 1,
                    start: 0,
                    scroll: 1,
                    circular: true,
                    auto: 500,
                    speed: 2000,
                    btnGo:
                            [".1", ".2",
                                ".3"],
                    afterEnd: function (a, to, btnGo) {
                        if (btnGo.length <= to) {
                            to = 0;
                        }
                        $(".thumbActive").removeClass("thumbActive");
                        $(btnGo[to]).addClass("thumbActive");
                    }
                });
            });
        </script>
    </head>
    <body>
        <div id="wrapper">


            <%@include file="header.jsp" %>

            <%@include file="menu.jsp" %>

            <!-- fin del  #menu -->
            <div id="page">
                <div id="page-bgtop">
                    <div> <!-- end #content -->
                        <table id="pagina" > 
                            <tr>
                                <td rowspan="5" valign="top" >
                                    <%@include file="menuLateral.jsp" %>

                                </td>
                            <tr></tr>
                            <tr></tr>

                            <td rowspan="3">
                                <div id="producto">
                                    <table id ="producto-table"   >

                                        <div id="slidebox">
                                            <div class="next"></div>
                                            <div class="previous"></div>
                                            <div class="thumbs">
                                                <a href="#" onClick="" class="1 thumbActive">1</a> 
                                                <a href="#" onClick="" class="2">2</a> 
                                                <a href="#" onClick="" class="3 ">3</a> 

                                            </div>
                                            <ul>
                                                <li><img src="images/noticia1.gif" alt="antiparasitos"/></li>
                                                <li><img src="images/noticias2.gif" alt="tapas"/></li>
                                                <li><img src="images/noticia3.gif" alt="solucion huesos"/></li>

                                            </ul>
                                        </div>
                                        <% ArrayList<Producto> productos = ProductoDB.buscarProductoUltimoComentado(); %>
                                      
                                        
                                        
                                        <% if (!productos.isEmpty()) {%>

                                            <ul>
                                                <% for (int i = 0; i < productos.size(); i++) {%>
                                                <tr>
                                                    <td><li><b><%=productos.get(i).getNombre()%></b></td>
                                                    <td><b><%=productos.get(i).getDescripcion()%></b></td>
                                                    <td>Precio unidad: <b><%=productos.get(i).getPrecio()%></b></td>
                                                    <td><b><img src="<%=productos.get(i).getImagen()%>" width="250" height="250"></b></td>
                                                    <td><b>
                                                            
                                        
                                        <form  action="Cesta" method="post">
                                            
                                            <input type="hidden" name= "producto" value="<%=String.valueOf(productos.get(i).getId())%>">
                                            <input type="hidden" name="animal" value="<%=String.valueOf(productos.get(i).getAnimal())%>">
                                            <input type="hidden" name ="categoria"value="<%=String.valueOf(productos.get(i).getCategoria())%>">
                                            <input type="hidden" name="action" value="add">
                                            <button class ="btn" type="submit" value="añadir" name="btnAñadir">Añadir al carrito</button>
                                        </form></b></li>
                                </td>
                                    <%}%>
                            </tr>
                        </ul>
                        <%} else {%>
                        <p class="textosCentrados">No hay productos disponibles de esta categoría.</p>
                        <%}%>




                                    </table>
                        </table>

                    </div>
                    </td>
                    </tr>
                    <tr>
                        <td valign="top">
                            <img src="images/paypal.png" width="155" height="85" alt=""/>

                        </td>

                    </tr>

                    </table>
                    <!-- end #sidebar -->
                    <div style="clear: both;">&nbsp;</div>
                </div>
            </div>
        </div>
        <!-- end #page -->
    </div>


    <%@include file="footer.jsp" %>

</body>
</head>
</html>
