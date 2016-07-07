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
                <% Usuario usuario = (Usuario) sesion.getAttribute("usuario");%>
                <div class="column">
                    <label for="nombre">Nombre <span>(requerido)</span></label>
                    <input type="text" name="nombre" class="form-input"  placeholder="Nombre" onblur="revisar(this);"/>

                    <label for="apellido">Apellido <span>(requerido)</span></label>
                    <input type="text" name="apellido" class="form-input"  placeholder="Apellido" onblur="revisar(this);"/>

                    <label for="password">Contraseña <span>(requerido)</span></label>
                    <input type="password" name="password" class="form-input"  placeholder="contraseña" onblur="revisar(this);"/>

                    <label for="paswordRep">Repite contraseña <span>(requerido)</span></label>
                    <input type="password" name="paswordRep" class="form-input"  placeholder="Repite contraseña" onblur="revisar(this)"/>

                    <label for="nacimiento">Fecha de nacimiento<span>(requerido)</span></label>
                    <input type="text" name="nacimiento" class="form-input"  placeholder="01/01/2001" onblur="revisar(this); revisarFecha(this);"/>

                    <label for="telefono">Teléfono: <span>(requerido)</span></label>
                    <input type="text" name="telefono" class="form-input" placeholder="983000000" onblur="revisar(this)"/>

                    <label for="email">Email <span>(requerido)</span></label>
                    <input type="email" name="email" class="form-input"  placeholder="ejemplo@dominio.com" onblur="revisar(this); revisarEmail(this);"/>

                    <label for="direccion">Direccion <span>(requerido)</span></label>
                    <input type="text" name="direccion" class="form-input" placeholder="calle Olmo nº12 bajo" onblur="revisar(this)"/>

                    <label for="ciudad">Ciudad <span>(requerido)</span></label>
                    <input type="text" name="ciudad" class="form-input" placeholder="Madrid" onblur="revisar(this)"/>

                    <label for="provincia">Provincia <span>(requerido)</span></label>
                    <input type="text" name="provincia" class="form-input" placeholder="Madrid" onblur="revisar(this)"/>

                    <label for="codigo">Codigo Postal <span>(requerido)</span></label>
                    <input type="text" name="codigo" class="form-input"  placeholder="28080" onblur="revisar(this);"/>

                </div>

            </div>
        </div>
        <!-- end #page -->
        <%@include file="footer.jsp" %>
    </body>
</html>
