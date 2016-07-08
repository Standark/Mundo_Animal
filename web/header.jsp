<%@page import="Modelo.Usuario"%>
<link rel="stylesheet" href="style.css" />
<div id="header">
    <div id="logo">
        <h1><a href="#">Mundo Animal</a></h1>			
    </div>
    <div id ="iconos">
        <div id="perfil">
            <nav id="colorNav">
                <ul>

                    <li class="orange">
                        <a href="Cesta?action=mostrar" class="carrito"></a>
                        <ul>
                            <li><a href="Cesta?action=mostrar">Carrito</a></li>
                        </ul>
                    </li>
                    <li class="blue">
                        <a href="#" class="contacto"></a>
                        <ul>
                            <li><a href="#">Contacto Zoólogico</a></li>
                            <li><a href="contacto-tienda.html">Contacto Tienda</a></li>
                            <li><a href="#">Contacto Veterinario</a></li>
                        </ul>
                    </li>
                    <li class="red">
                        <a href="#" class="perfil"></a>
                        <ul>
                            <%
                                HttpSession sesion = request.getSession();
                                Usuario usuario0 = (Usuario) sesion.getAttribute("usuario");
                                if (usuario0 == null) {
                            %>
                            <li><a href="index.jsp">Principal</a></li>
                            <li><a href="loginORegistro.jsp">Inicar sesión</a></li>
                            <li><a href="registro.jsp">Registrarse</a></li>
                                <%
                                } else {
                                %>
                            <li><a href="index.jsp">Principal</a></li>
                            <li><a href="miPerfil.jsp">Mi perfil</a></li>
                            <li><a href="modificarPerfil.jsp">Editar perfil</a></li>
                            <li><a href="CerrarSesion">Cerrar Sesión</a></li><%
                                    }
                                %>
                        </ul>
                    </li>
                </ul>
            </nav>
        </div>

    </div>
</div>