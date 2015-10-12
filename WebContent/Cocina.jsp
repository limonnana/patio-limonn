<%@ include file="layout/taglibs.jsp" %>

<stripes:layout-render name="/layout/bradino.jsp" title="Cocina">
    <stripes:layout-component name="contents">

 <jsp:useBean id="gastronomiaManager" scope="page"
                     class="com.limonn.entityManager.GastronomiaManager"/>
<meta http-equiv="refresh" content="40" >
<script LANGUAGE="JavaScript">
<!--
// Nannette Thacker http://www.shiningstar.net
function confirmDelete(name)
{
var agree=confirm("Estas seguro que el :  "+name+ "  esta listo ?");
if (agree)
	return true ;
else
	return false ;
}
// -->
</script>

<c:choose>
<c:when test="${userLimonnAdmin.role == 'Admin'}">

</c:when>
<c:otherwise>
<c:if test="${userLimonnAdmin.role != 'Cocina'}">
<meta http-equiv="refresh" content="0;url=${ctx}/index.jsp" />
</c:if>
</c:otherwise>
</c:choose>

        <stripes:errors globalErrorsOnly="true"/>

        <div class="body">
  
     <c:if test="${userLimonnAdmin.role == 'Admin'}">
	<div class="user">
       ${userLimonnAdmin.name} , Posicion: ${userLimonnAdmin.role} 
    </div>
    </c:if>
               

                  <div>Cocina
                    </div>   

                 <table>
                <tr>
                   
                    
                    <th>Unidades</th>
                    <th>&nbsp; </th>
                    <th>Comentario</th>
                   
                     <c:if test="${userLimonnAdmin.role == 'Admin'}">
                    <th>Listo</th>
                    </c:if>
                </tr>
                   
                
                  <c:forEach items="${gastronomiaManager.pedidosCocinaList}" var="pedido">
                  <tr>
                          
                          <td> ${pedido.cart.totalPlatos}</td>
                          <td> 

                             <table>
                             
                              <th> Pedido numero: ${pedido.id} </th>
                              <th>&nbsp;  </th>    
                          <c:forEach items="${pedido.cart.platoCompuestoList}" var="platoCompuesto">
                           
                            <tr>
                             
                              <td>
                               ${platoCompuesto.plato.nombre}
                             </td>
                              
                          
                           <td> &nbsp; &nbsp;${platoCompuesto.cantidad.number} </td>
                           
                            
                            
                              </tr>
                         </c:forEach>
                            
                            </table>

                          </td>
                          <td>
                          ${pedido.kitchenComment}
                           
                           </td>
                           
                           <c:if test="${userLimonnAdmin.role == 'Admin'}">
                           <td align="center"> 
                           <stripes:link href="/Cocina.action" onclick="return confirmDelete('${pedido.id}')" event="listoPedido">
                         <img src="${ctx}/layout/images/deleteicon1.jpg" border=0 width=15 height=15  alt="Delete" >
                           <stripes:param name="pedido.id" value="${pedido.id}"/>
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