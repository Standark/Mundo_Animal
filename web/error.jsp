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
                  
                   <h1>Se ha producido el siguiente errrrrrror: <%= request.getAttribute("textoError") %></h1>
                   
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
