

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
        <link rel="stylesheet" href="estilos.css" />
	<script type="text/javascript" src="js/evaluarFomulario.js"></script>

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
                <td valign="top" >
       
	<section>
	
		<form action="" class="contact-form" onsubmit="return validar(this);">				
			<div class="formulario">
				<div class="column">
					<label for="nombre">Nombre <span>(requerido)</span></label>
					<input type="text" name="nombre" class="form-input"  placeholder="Nombre" onblur="revisar(this);"/>
                                        
                                        <label for="apellido">Apellido <span>(requerido)</span></label>
                                        <input type="text" name="apellido" class="form-input"  placeholder="Apellido" onblur="revisar(this);"/>
                                        
                                        <label for="nick">Login <span>(requerido)</span></label>
                                        <input type="text" name="nick" class="form-input"  placeholder="Login" onblur="revisar(this);"/>
					
                                        <label for="password">Contraseña <span>(requerido)</span></label>
                                        <input type="text" name="password" class="form-input"  placeholder="contraseña" onblur="revisar(this);"/>
                                        
                                         <label for="paswordRep">Repite contraseña <span>(requerido)</span></label>
                                        <input type="text" name="paswordRep" class="form-input"  placeholder="Repite contraseña" onblur="revisar(this)"/>
					
					<label for="email">Email <span>(requerido)</span></label>
					<input type="email" name="email" class="form-input"  placeholder="ejemplo@dominio.com" onblur="revisar(this); revisaremail(this);"/>
					
                                        <label for="direccion">Direccion <span>(requerido)</span></label>
					<input type="text" name="direccion" class="form-input" placeholder="calle Olmo nº12 bajo" onblur="revisar(this)"/>
                                        
                                        <label for="codigo">Codigo Postal <span>(requerido)</span></label>
                                        <input type="text" name="codigo" class="form-input"  placeholder="34005" onblur="revisar(this);"/>
                                        
                                        <label for="edad">Edad<span>(requerido)</span></label>
                                        <input type="text" name="edad" class="form-input"  placeholder="18" onblur="revisar(this);"/>
				
				</div>
				
							
				
				<input class="form-btn" type="submit" value="Registrar" />
			</div>		
		</form>
	</section>
                  
                   </td>
                   </table>
        </div>
        </div>
        </div>

<%@include file="footer.jsp" %>

</body>
</head>
</html>
