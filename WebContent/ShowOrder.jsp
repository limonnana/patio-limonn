<%@ include file="/layout/taglibs.jsp" %>


<stripes:layout-render name="/layout/bradino.jsp" title="Patio comidas">
    <stripes:layout-component name="contents">

 
<c:if test="${actionBean.user.name != null}">   
 <div class="user">    
 ${actionBean.user.name},  ${actionBean.user.address} <br>
     ${actionBean.user.cellphone} 
<br>

<stripes:link href="/EditUser.action" event="preEdit">  actualizar
<stripes:param name="user.id" value="${actionBean.user.id}"/>
</stripes:link>      
</div>

</c:if>

               <br><br>
               
               <!--    
                   &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; <h2>Estado actual </h2>
                -->   
                    <table>

                     <th>
                      Plato
                     </th> 
                     <th align="center">
                      precio
                     </th>
                      <th>
                      cantidad

                     </th> 
                       
					
                     <th>
                       Total
                     </th>                   
                
                 <c:forEach items="${actionBean.cartInSession.platoCompuestoList}" var="platoCompuesto">
                       <tr>
                        <td>   
                         
                    ${platoCompuesto.plato.nombre}
                     
                     </td>
                     <c:choose>
                      <c:when test="${platoCompuesto.plato.precio < 10}">
                    <td>&nbsp;&nbsp;${platoCompuesto.plato.precio} &nbsp;$</td>
                      </c:when> 
                      <c:otherwise>
                     <td>${platoCompuesto.plato.precio} &nbsp;$</td>
                      </c:otherwise>
                      </c:choose>
                       <td>
                     <c:choose>
                     <c:when test="${platoCompuesto.cantidad.number < 10}">
                    &nbsp;&nbsp;${platoCompuesto.cantidad.number} 
                      </c:when> 
                      <c:otherwise>
                     ${platoCompuesto.cantidad.number} 
                      </c:otherwise>
                      </c:choose>
                      
                      
                      &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
                      + 
                      <stripes:link href="/Order.action" event="add">
                      <stripes:param name="plato.id" value="${platoCompuesto.plato.id}"/>
                      <stripes:param name="user.id" value="${actionBean.user.id}"/>
                       agregar
                        
                        </stripes:link>
                       &nbsp;&nbsp; - 
                      <stripes:link href="/Order.action" event="rest">
                      <stripes:param name="plato.id" value="${platoCompuesto.plato.id}"/>
                      <stripes:param name="user.id" value="${actionBean.user.id}"/>
                     sacar
                      </stripes:link> 
					  </td>
                      
                    
                        
                     <c:choose>
                      <c:when test="${platoCompuesto.cantidad.toPay < 10}">
                    <td> &nbsp; ${platoCompuesto.cantidad.toPay} &nbsp;$ </td>
                      </c:when> 
                      <c:otherwise>
                     <td> ${platoCompuesto.cantidad.toPay} &nbsp;$ </td>
                      </c:otherwise>
                      </c:choose>
                      
                      </tr>
                     
                     </c:forEach>
                     
                      <c:if test="${actionBean.cartInSession.totalPlatos>1}">
                      <tr>
                      <td>
                      <br><br>
                      Total
                      </td>
                      <td>
                      <br><br>
                     &nbsp;
                     
                      </td>
                      <c:choose>
                      <c:when test="${actionBean.cartInSession.totalPlatos < 10}">
                    <td>
                      <br><br>
                     &nbsp; ${actionBean.cartInSession.totalPlatos} </td>
                      </c:when> 
                      <c:otherwise>
                     <td> ${actionBean.cartInSession.totalPlatos}  </td>
                      </c:otherwise>
                      </c:choose>
                     <td>
                      <br><br>
                   &nbsp;${actionBean.cartInSession.total} &nbsp;$ </td>
                     </tr>
                        </c:if>
                      </table>
                   
                     <br><br>
                     &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;
                     <stripes:link href="/index.jsp">Agregar otro plato </stripes:link>
                      &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                     <stripes:link href="/ProcessOrder.action">Confirmar pedido</stripes:link>  
                     <br><br>  <br><br>  <br><br> <br><br>
                     </div>
                     </div>
                     
               
                 

            </div>

</stripes:layout-component>
</stripes:layout-render>
