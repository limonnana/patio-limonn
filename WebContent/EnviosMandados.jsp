<%@ include file="layout/taglibs.jsp" %>

<stripes:layout-render name="/layout/bradino.jsp" title="Envios">
    <stripes:layout-component name="contents">

 <jsp:useBean id="gastronomiaManager" scope="page"
                     class="com.limonn.entityManager.GastronomiaManager"/>
<script LANGUAGE="JavaScript">
<!--
// Nannette Thacker http://www.shiningstar.net
function confirmDelete(name)
{
var agree=confirm("Estas seguro de querer borrar:  "+name+"?");
if (agree)
	return true ;
else
	return false ;
}
// -->
</script>

<c:if test="${userLimonnAdmin.role != 'Admin'}">
<meta http-equiv="refresh" content="0;url=${ctx}/index.jsp" />
</c:if>

        <stripes:errors globalErrorsOnly="true"/>

        <div class="body">
  
     
               

                  <div>Envios 
                    </div>  

  TODO Seleccionar / filtrar envios por fecha 

                 <table>
                <tr>
                   
                    <th>Fecha</th>
                    <th>Id</th>
                    <th>Delivery</th>
                    
                    <th>Total</th>
                   
                    <th>Editar</th>
                    
                     <c:if test="${userLimonnAdmin.role == 'Admin'}">
                    <th>Borrar</th>
                    </c:if>
                </tr>

                
                  <c:forEach items="${gastronomiaManager.enviosList}" var="envio">
                  <tr>
                          <td> ${envio.date}</td>
                          <td> ${envio.id}</td>
                          <td> ${envio.deliveryGuy.name}</td>
                          <td> ${envio.total}</td>
                          
                          <td> 
                          
                           <stripes:link href="/Envios.action" event="preEdit"> envio 
                           <stripes:param name="envio.id" value="${envio.id}"/>
                           </stripes:link>
                           ${envio.id}

                           </td>
                          
                           <c:if test="${userLimonnAdmin.role == 'Admin'}">
                           <td align="center"> 
                           <stripes:link href="/Deleteenvio.action" onclick="return confirmDelete('${envio.id}')" event="deleteenvio">
                         <img src="${ctx}/layout/images/deleteicon1.jpg" border=0 width=15 height=15  alt="Delete" >
                           <stripes:param name="envio.id" value="${envio.id}"/>
                           </stripes:link>
                           </td>
                           </c:if>
                </tr>
                </c:forEach>
                 
                   
            </table>

         
            </div>
        
        
            
            
           <br><br><br><br><br><br><br><br>

            </div>
                
    </stripes:layout-component>
</stripes:layout-render>