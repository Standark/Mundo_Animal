<%-- 
    Document   : productoDescripcion
    Created on : 26-may-2016, 11:36:02
    Author     : Dawn_
--%>

<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="Modelo.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Cesta</title>
<link href="style.css" rel="stylesheet" type="text/css" media="screen" />
</head>
  <body>
    <div id="wrapper">
	
	<%@include file="header.jsp" %>
	<%@include file="menu.jsp" %>
	
<div id="page">
		<div id="page-bgtop">
			<div> <!-- end #content -->
              <table id="pagina">
				</table>
             	<table class="cesta" >
                <tr>
                <td colspan="4">
                	<p class="titulos"  >MI CESTA</p>
                    <hr size="2px" width="90%" noshade="noshade" align="center" />
                </td>
                </tr>
                <tr>
                <td>
                	<p class="subtitulos">Producto</p>
                    
                </td>
                <td>
                	<p class="subtitulos">PVP</p>
                    
                </td>
                <td>
                	<p class="subtitulos">Cantidad</p>
                    
                </td>
                <td>
                	<p class="subtitulos">Precio</p>
                    
                </td>
                </tr>
                <tr>
                        <%HttpSession sesion = request.getSession();
                        Map<Producto, Integer> prods = (HashMap<Producto,Integer>) sesion.getAttribute("prods");
                        Iterator <Entry<Producto,Integer>> it = prods.entrySet().iterator();
                        Entry<Producto,Integer> entry = null;
                        Producto prod = null;
                        Integer cant = null;
                        while(it.hasNext()){
                            entry =it.next();
                            prod= entry.getKey();
                            cant = entry.getValue();
                           %>
                           <td><%=prod.getNombre()%></td>
                           <td><%=prod.getPrecio()%></td>
                           <td><%=cant%></td>
                           <td><%double precio = cant*prod.getPrecio();%><%=precio%></td>
                        <%}%>
                        
                        
                   
                </tr> 
                <td colspan="4" >
                	<br>
                    <br>
                    
                
                	<hr size="2px" width="90%" noshade="noshade" align="center" />
                </td>
               
                
                <tr>
                	<td colspan="2">
                    <br>
                    <input type="text" name="cupon"  size="40" value="¿Tienes algún cupón ?" />
                    <br>
                    <br>
                    
                    <p>Si tienes algún cupón de descuento introdúcelo aquí</p>
                    
                    </td>
                    <td>
                    <button class="boton-seguir" type="submit" >Seguir comprando</button>
                    
                    </td>
                    <td>
                    <button class="boton-comprar" type="submit" >Realizar pedido</button>
                    
                    </td>
    
              </table>
              </div>
		  </div>
		</div>
  </div>	

<%@include file="footer.jsp" %>

</body>

</html>
