
<%@page import="Modelo.Usuario"%>
<link rel="stylesheet" href="style.css" />
<div id="menu">
    <ul>

    </ul>
    <div id="logeameOrRegistrame">
        <ul>
            <li><a href="loginORegistro.jsp">REGISTRO Y LOGIN</a></li>

            <%
                Usuario usuario = (Usuario) sesion.getAttribute("usuario");
                if (usuario == null) {
            %>
            <li><a>  Bienvenido </a></li>
                <%
                } else {
                %>
            <li><a href="miPerfil.jsp"> Bienvenid@ <%=usuario.getNombre()%></a></li>
                <%
                    }
                %>

        </ul>
    </div>
    <div id="buscar">
        <form method="get" action="/search" id="search">
            <input name="q" type="text" size="40" placeholder="Introduce tu búsqueda..." />
        </form>
    </div>
</div>