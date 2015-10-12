<%@ include file="/layout/taglibs.jsp" %>

<stripes:layout-render name="/layout/bradino.jsp" title="Envios">
    <stripes:layout-component name="contents">
    
     Pedido Numero: ${actionBean.envio.id} &nbsp; &nbsp;Delivery:  ${actionBean.sheliaj} <br><br>

Total: <b>${actionBean.envio.total} $</b>

<br><br><br>

   

                 <table>
                <tr>
                   
                    <th>Id</th>
                    <th>Nombre</th>
                    <th>Direccion &nbsp; &nbsp;</th>
                    <th>Total</th>
                    <th>Unidades</th>
                    <th>Borrar</th>
                    
                </tr>

                
                  <c:forEach items="${actionBean.envio.listaPedidos}" var="pedido">
                  <tr>
                          
                          <td> ${pedido.id}</td>
                          <td> ${pedido.cart.user.name}</td>
                          <td> ${pedido.cart.user.address}</td>
                          <td> ${pedido.cart.total} $</td>
                          <td> ${pedido.cart.totalPlatos}</td>
                          <td>
                         
                          <stripes:link href="/DeletePedido.action" onclick="return confirmDelete('${pedido.id}')" event="deletePedido">
                          <center>
                         <img src="${ctx}/layout/images/deleteicon1.jpg" border=0 width=15 height=15  alt="Delete" >
                           <stripes:param name="pedido.id" value="${pedido.id}"/>
                           </stripes:link>
                            </center>
                           
                           
                   </td>
					</tr>
					</c:forEach>
                   </table>

            </div>
                
    </stripes:layout-component>
</stripes:layout-render>