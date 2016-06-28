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
                <td rowspan="3" valign="top">
                 <div id="producto">
                   <table id ="producto-flitros" >
         			 <tr>
                      	<td>
                       	 <img src="images/65536_donutbett_basic_50_01_hs_5.jpg" width="300" height="300" alt=""/>
                           
                        </td>
                       <td><br>
                        	
							<p class="titulos">Sofá clásico para perros Diván Wellness</p>

                            <p>
								Sofá clásico para perros Diván Wellness
								<br>
                                <br>
								Sofá ortopédico viscoelástico para perros con funda lisa de piel artificial, marrón veteado, aspecto clásico, higiénica, ideal para perros mayores y con problemas articulares.
       
                            </p>
                            <h3 id="precio">79,99 €</h3>
                            <br>
                            
                            <div class="ec-stars-wrapper">
                                <a href="#" data-value="1" title="Votar con 1 estrellas">&#9733;</a>
                                <a href="#" data-value="2" title="Votar con 2 estrellas">&#9733;</a>
                                <a href="#" data-value="3" title="Votar con 3 estrellas">&#9733;</a>
                                <a href="#" data-value="4" title="Votar con 4 estrellas">&#9733;</a>
                                <a href="#" data-value="5" title="Votar con 5 estrellas">&#9733;</a>
                            </div>
                            <noscript>Necesitas tener habilitado javascript para poder votar</noscript>
                             <a href="realizar-valoracion.html" id="Valorar">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Realizar valoración</a>
								<br>
                                <br>
                             <button class="btn" type="submit" >Añadir al carrito</button>
                            
    
                        </td>
                     </tr>
                     
                     <tr>
                   		  <td colspan="2">
                               <section class="tabs">
                                <input id="tab-1" type="radio" name="radio-set" class="tab-selector-1" checked="checked" />
                                <label for="tab-1" class="tab-label-1">Descripción </label>
                                
                                <input id="tab-2" type="radio" name="radio-set" class="tab-selector-2" />
                                <label for="tab-2" class="tab-label-2">Comentarios</label>
                                
                   
                                <div class="clear-shadow"></div>
                                            
                                <div class="content">
                                    <div class="content-1">
                                        <p>
                                        El sofá clásico para perros Diván Wellness otorga a tu perro el confort ideal para dormir. El colchón ortopédico está compuesto por un tercio de tejido viscoelástico y se adapta óptimamente a las formas anatómicas del cuerpo, como los hombros y las caderas. Esto significa que la mascota podrá descargar todo el peso y la fuerza al tumbarse. De ese modo, el grueso colchón le protegerá del perjudicial frío del piso y del suelo radiante.
                                        <br>
                                        <br>
Por ello, el sofá clásico para perros Diván Wellness es ideal para la prevención o cuidado de las enfermedades articulares, en especial las que afectan a los perros mayores. Además, el acceso al sofá es especialmente cómodo para perros mayores y con problemas articulares debido a su entrada elevada.

<br>
<br>
El brazo alto, cosido a dos lados de la cama, favorece la comodidad del perro debido a su forma estable. En él podrá apoyar cómodamente la columna y la cabeza. La superficie de apoyo lisa, hecha de piel artificial marrón, es especialmente flexible y tiene un aspecto antiguo gracias a su estructura veteada. El tipo de piel es inodora de modo natural y ofrece protección optima frente a ácaros y pulgas.
                                        
                                        </p>
                                    </div>
                                    <div class="content-2">
                                        <p>
                                        <h1 id="titulo">Valoraciones más recientes</h1>
                                              <br>                                
                                      		 
									<hr align="left" noshade="noshade" size="1" width="80%" />
                                          
                                        <p>
                                        <h2 id="fecha">15/05/2016 Juan</h2>
                                        <a href="#" data-value="1">&#9733;</a>
                                        <a href="#" data-value="2">&#9733;</a>
                                        <a href="#" data-value="3">&#9733;</a>
                                        <a href="#" data-value="4" >&#9733;</a>
                                        <a href="#" data-value="5">&#9733;</a>
                                        <br>
                                             
                                            	<h3 id="titulo2"> A mi perro le encanta</h3>
                                                <br>
											<p>En cuanto lo hemos colocado se ha tumbado y se ha echado la siesta. Parece cómodo, tiene un lado de aspecto polar pensado para el frío y el lado de cuadros de tela normal, de aspecto resistente. Ha sido un acierto.
                                             </p>
                                                <br>
                                              <hr align="left" noshade="noshade" size="0.5" width="50%" />
                                                                                      
                                             <h2 id="fecha">10/05/2016 Maria</h2>
                                                 <a href="#" data-value="1">&#9733;</a>
                                                <a href="#" data-value="2">&#9733;</a>
                                                <a href="#" data-value="3">&#9733;</a>
                                                <a href="#" data-value="4" >&#9733;</a>
                                                <a href="#" data-value="5">&#9733;</a>
                                             <br>
                                            	<h3 id="titulo2"> Lo adora</h3>
                                                <br>
											<p>En cuanto lo hemos colocado se ha tumbado y se ha echado la siesta. Parece cómodo, tiene un lado de aspecto polar pensado para el frío y el lado de cuadros de tela normal, de aspecto resistente. Ha sido un acierto.
                                            <br>
                                             </p>
                                             
                                             <hr align="left" noshade="noshade" size="0.5" width="50%" />
                                             <br>
                                             <br>
                                             <button class="btn" type="submit" >Valora este producto</button>      
                                    </div>
                                   
                                </div>
                            </section>
                            
                           
                          </td>
                     </tr>
                       

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
	<!-- end #page -->
        <%@include file="footer.jsp" %>
       </body>
</html>
