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
                   <tr>
                      <td id ="oferta"  colspan="2" >
                     	<p class="titulos"> ----CAMAS PARA PERROS-----</p>
                      </td>
                    </tr>
                    <tr>
                      <td>
                         <select id="tamaño" onchange="window.location.href = this.options[this.selectedIndex].value">
                            <option selected="selected" value="">Tamaño de animal</option>
                            <option value="#">Pequeño</option>
                            <option value="#">Mediano</option>
                            <option value="#">Grande</option>
                            <option value="#">Muy grande</option>
                   		 </select>
                      </td>
                      <td>
                      <br>
                      <select id="material" onchange="window.location.href = this.options[this.selectedIndex].value">
                            <option selected="selected" value="">Material</option>
                            <option value="#">Lana</option>
                            <option value="#">Sintético</option>
                            <option value="#">Algodón</option>
                   		 </select>
                      </td>
                      <tr>
                      <td>
                           <select id="tipo" onchange="window.location.href = this.options[this.selectedIndex].value">
                            <option selected="selected" value="">Tipo</option>
                            <option value="#">Caseta</option>
                            <option value="#">Cochoneta</option>
                            
                   		 </select>
                      </td>
                      <td>
                      <br>
                      	<select id="localizacion" onchange="window.location.href = this.options[this.selectedIndex].value">
                            <option selected="selected" value="">Localización</option>
                            <option value="#">Interior</option>
                            <option value="#">Exterior</option>
                            
                   		 </select>
                      
                      </td>
                      </tr>
                      <tr>
                          <td>
                          <a href="producto-cama2.html" target="_blank">
                          <img src="images/65536_donutbett_basic_50_01_hs_5.jpg" width="300" height="300" alt=""/></a></td>
                          
                          <td><a href="producto-cama.html" target="_blank"><img src="images/55582_PLA_Wellness_Hundesofa_Divan_antik_L110_FG_DSC0314_5.jpg" width="300" height="300" 
                          alt=""/></a></td>
                         
                     </tr>
                         
                        <tr>
                          <td><h3>Sofá clásico para perros Diván Wellness</h3></td>
                          <td><h3>Tetra EX Plus </h3></td>
                         
                        </tr>
                         
                        <tr>
                          <td>
                            <p>
					Sofá ortopédico viscoelástico para perros con funda lisa de piel artificial, marrón veteado, aspecto clásico, higiénica, ideal para perros mayores y con problemas articulares.
                            </p>
							</td>
                     
                          <td><p><br>Espaciosa jaula para roedor, conejo y cobaya. Para una primera instalación básica. El pesebre de heno se coloca en el exterior. Medidas: 100 x 54 x 44 cm (LxAnxAl).</p></td>
                        </tr>
                         
                        <tr>
                          <td  id="precio2" align="center">79,99 €</p></td>
                          <td id="precio2" align="center" >70,08</td>
                
                        </tr>
                         <tr>
                          <td align="center"><button class ="btn" type="submit" >Añadir al carrito</button></td>
                          <td align="center"><button class ="btn" type="submit">Añadir al carrito</button></td>
                         
                        </tr>
                  	</tr>
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
