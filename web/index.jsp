

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

                            $(function() {
                                $("#slidebox").jCarouselLite({
                                    vertical: false,
                                    hoverPause:true,
                                    btnPrev: ".previous",
                                    btnNext: ".next",
                                    visible: 1,
                                    start: 0,
                                    scroll: 1,
                                    circular: true,
                                    auto:500,
                                    speed:2000,				
                                    btnGo:
                                        [".1", ".2",
                                        ".3"],

                                    afterEnd: function(a, to, btnGo) {
                                            if(btnGo.length <= to){
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
                <td valign="top" >
                   <%@include file="menuLateral.jsp" %>
                
                </td>
                
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
                      
                    <tr>
                        
                        <br>
                        <br>
                            <td class="titulos" colspan="4">Las ofertas de la semana:
                     	<br>
                        <br>
                       </td>
                       <tr>
                          <td><a href="producto-jaula-roedor.html" target="_blank">  
                          
                          <img src="images/42810_2_1.jpg" width="250" height="200" alt=""/></a></td>
                          <td><a href="producto-filtro.html" target="_blank"> <img src="images/teil_2_1.jpg" width="250" height="200" alt=""/></a></td>
                          <td><a href="producto-jaula-roedor2.html" target="_blank"><img src="images/49818_PLA_Savic_Kleintierkaefig_Caesar_3_4.jpg" width="250" height="200" alt=""/></a></td>
                     </tr>
                         
                        <tr>
                          <td><h3>Jaula Skyline Maxi XXL</h3></td>
                          <td><h3>Tetra EX Plus Filtro exterior</h3></td>
                          <td><h3>Jaula para conejos Piggy Basic</h3></td>
                        </tr>
                         
                        <tr>
                          <td>
                            <p>
							 De gran capacidad, para conejos y cobayas. Una sólida construcción y accesorios resistentes harán de esta jaula un                            hogar duradero para tu roedor. 
                            Medidas: 119 x 59 x 47 (L x An x Al).
                            </p>
							</td>
                          <td><p>
                          <br>
                          Filtro exterior potente en 3 tamaños distintos para acuarios entre 60 y 500 l, equipado con masas filtrantes. Consume un 35% menos que la versión anterior de estos filtros</p></td>
                          <td><p><br>Espaciosa jaula para roedor, conejo y cobaya. Para una primera instalación básica. El pesebre de heno se coloca en el exterior. Medidas: 100 x 54 x 44 cm (LxAnxAl).</p></td>
                        </tr>
                         
                        <tr>
                          <td  id="precio2" align="center">50,59 €</p></td>
                          <td id="precio2" align="center" >70,08 €</td>
                          <td id="precio2" align="center">39,52 €</td>
                        </tr>
                         <tr>
                          <td align="center"><button class ="btn" type="submit" >Añadir al carrito</button></td>
                          <td align="center"><button class ="btn" type="submit">Añadir al carrito</button></td>
                          <td align="center"><button class ="btn" type="submit">Añadir al carrito</button></td>
                        </tr>
                  	</tr>
                    
                    
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
