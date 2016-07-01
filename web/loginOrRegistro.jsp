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
<title>Producto</title>
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
                <td rowspan="3">
                 <div id="producto">
                
            <table > 
             <tr>
                <td colspan="2">
                <br><br>
                </td>
                
            </tr>
            <tr>
                <td  width="400px" align="left">
					<h2> Nuevos Clientes</h2>
					<br>
					<p>Al crear una cuenta con nuestra tienda podrás moverte más rápidamente por el
						proceso de pago,guardar múltiples direcciones de envío, ver y seguir el rastro de 
						los pedidos de tu cuenta y más.
					</p><br>
				
				
				
						 				 
				
				<button class="btn" type="submit" id="registrar"><a href="/regitrar.jsp">Registrarse</button>
                	
				
                </td>
                <td  class="arriba" width="400px" align="left">  
                <h2>Clientes Registrados</h2>
                <br>
                <p>Si tienes una cuenta con nosotros, por favor logueate.</p>
				
				<table align="center"> 
					<td><br></td>
					<tr>
						<td>
							Nick:
						</td>
						<td>
							<form action="AccederAlSitio" method="post">
							<input type="text" id="nick" name="nick" value="" />
							
						</td>
					</tr>
					<td><br></td>
					<tr>
						<td>
							Contraseña:
						</td>
						<td>
						
							<input type="password" id="contrasenya1" name="contrasenya1" value="" />
							</form>
						</td>
					</tr>
					<td><br></td>
					<td><br></td>
				</table>
                 
                 <button class="btn" type="submit" id="iniciar" align="center">Iniciar</button>
                </td>              
            </tr>
         
            
               <tr>
                <td colspan="2">
                <br>
                <br>
                </td>
                
            </tr>
          
        </table>
        </table>
                 <tr>
                 
                    <td  style="width: 200px;height :100%">
                        <img src="images/paypal.png" width="155" height="85" alt=""/>
                    
                    </td>
               
                </tr>
              </table>
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
