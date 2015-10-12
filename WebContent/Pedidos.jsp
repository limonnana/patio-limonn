<%@ include file="layout/taglibs.jsp" %>

<stripes:layout-render name="/layout/bradino.jsp" title="Platos">
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
  
     <c:if test="${userLimonnAdmin.role == 'Admin'}">
	<div class="user">
       ${userLimonnAdmin.name} , Posicion: ${userLimonnAdmin.role} 
    </div>
    </c:if>
               

                  <div>Pedidos 
                    </div>   

                 <table>
                <tr>
                   
                    <th>Fecha</th>
                    <th>Id</th>
                    <th>Nombre</th>
                    <th>Direccion</th>
                    <th>Total</th>
                    <th>Unidades</th>
                    <th>Comentarios</th>
                    <th>Editar</th>
                     <c:if test="${userLimonnAdmin.role == 'Admin'}">
                    <th>Borrar</th>
                    </c:if>
                </tr>

                
                  <c:forEach items="${gastronomiaManager.pedidosList}" var="pedido">
                  <tr>
                          <td> ${pedido.date}</td>
                          <td> ${pedido.id}</td>
                          <td> ${pedido.cart.user.name}</td>
                          <td> ${pedido.cart.user.address}</td>
                          <td> ${pedido.cart.total}</td>
                          <td> ${pedido.cart.totalPlatos}</td>
                          <td>
                          <c:if test="${pedido.deliveryComment != null}">
                          D:${pedido.deliveryComment} 
                           
                           </c:if>

                           <c:if test="${pedido.kitchenComment != null}">
                           &nbsp; C: ${pedido.kitchenComment}
                           </c:if>
                           </td>
                           <td>
                           <stripes:link href="/EditPedido.action" event="preEdit"> pedido 
                           <stripes:param name="pedido.id" value="${pedido.id}"/>
                           </stripes:link>
                           ${pedido.id}
                           </td>
                           <c:if test="${userLimonnAdmin.role == 'Admin'}">
                           <td align="center"> 
                           <stripes:link href="/DeletePedido.action" onclick="return confirmDelete('${pedido.id}')" event="deletePedido">
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