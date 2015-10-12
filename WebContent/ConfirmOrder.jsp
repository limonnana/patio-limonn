<%@ include file="/layout/taglibs.jsp" %>

<stripes:layout-render name="/layout/bradino.jsp" title="Patio comidas">
    <stripes:layout-component name="contents">


   <stripes:form action="/RegisterCartOn.action">
        <c:if test="">
      <div class="errors">
       ${actionBean.mensaje}
       </div>
      </c:if>
	<table id="tablaInvisible">
	<tr>
	<td>
	<c:choose>
    <c:when test="${actionBean.cartInSession.user.name != null}">
	<h2>Para entregar a:</h2> 
	  
	    
	 ${actionBean.cartInSession.user.name},  ${actionBean.cartInSession.user.address} <br>
	     ${actionBean.cartInSession.user.cellphone}
    </c:when>
      <c:otherwise>
      

      <b>Unica vez para un delivery rapido : </b><br><br>
      <table id="tablaInvisible">
       <tr>
                    <th><stripes:label for="user.name"/>:</th>
                    <td>
                        <stripes:text name="user.name" /> <span class="required"> * requerido</span>
                        <stripes:errors field="user.name"/>
                    </td>
                </tr>
                 <tr>
                    <th><stripes:label for="user.lastName"/>:</th>
                    <td>
                        <stripes:text name="user.lastName" /> 
                        <stripes:errors field="user.lastName"/>
                    </td>
                </tr>

                 <tr>
                    <th><stripes:label for="user.address"/>:</th>
                    <td>
                        <stripes:text name="user.address" /> <span class="required"> * </span>Completa:  calle, numero, piso, oficina
                        <stripes:errors field="user.address"/>
                    </td>
                </tr>
                <tr>
                    <th><stripes:label for="user.cellphone"/>:</th>
                    <td>
                        <stripes:text name="user.cellphone" /> <span class="required"> * </span>
                        <stripes:errors field="user.cellphone"/>
                    </td>
                </tr>                

                
            </table>
         
      </c:otherwise>

	</c:choose>
    
	</td>
	<td>&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;</td>
	<td valign="top">
	<b>Hora para el delivery &nbsp;&nbsp; ${actionBean.deliveryHour} 
             
	</b><br>
	<stripes:link href="SetTime.jsp">cambie 
               <stripes:param name="user.id" value="${actionBean.cartInSession.user.id}"/>
               <stripes:param name="event" value="ProcessOrder.action"></stripes:param>
               </stripes:link> + tarde
	</td>      
	</tr>
	</table>
	</div>

               
               
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
                      
                      
                      
					  </td>
                      
                    
                    
                     <c:choose>
                      <c:when test="${platoCompuesto.cantidad.toPay < 10}">
                    <td> &nbsp; ${platoCompuesto.cantidad.toPay} &nbsp;$</td>
                      </c:when> 
                      <c:otherwise>
                     <td> ${platoCompuesto.cantidad.toPay} &nbsp;$ </td>
                      </c:otherwise>
                      </c:choose>
                      
                      </tr>
                     
                     </c:forEach>
                       <c:if test="${actionBean.cartInSession.totalPlatos > 1}">  
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
                      <c:when test="${actionBean.cartInSession.totalPlatos > 1}">
                    <td>
                      <br><br>
                     &nbsp; ${actionBean.cartInSession.totalPlatos} </td>
                      </c:when> 
                      <c:otherwise>
                     <td> &nbsp;  </td>
                      </c:otherwise>
                      </c:choose>
                     <td>
                      <br><br>
                   &nbsp;${actionBean.cartInSession.total} &nbsp;$ </td>
                     </tr>
                       </c:if> 
                      </table>



               <br><br>
         
         <div class="buttons">
          &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;
          
          &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
          
           <stripes:submit name="registerCartOn" value="Realizar pedido"/>

           </div>

       <br><br><br><br><br>
          
    <table id="tablaInvisible">
       <tr>
                    <th><stripes:label for="kitchenComment"/>:</th>
                    <td>
                        <stripes:text name="kitchenComment" /> 
                        
                    </td>
                </tr>
                 <tr>
                    <th><stripes:label for="deliveryComment"/>:</th>
                    <td>
                        <stripes:text name="deliveryComment" /> 
                        
                    </td>
                </tr>
           </table>

      
           </stripes:form>
                     
           
                     <br><br>  <br><br>  <br><br> <br><br>
                     </div>
                     </div>
                     
               
                 

            </div>

</stripes:layout-component>
</stripes:layout-render>
