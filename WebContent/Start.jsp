<%@page import="com.limonn.settings.Utils"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%

String idCookie = request.getParameter("idCookie");

if(StringUtils.isNotBlank(idCookie))
		{
if(idCookie.length()>0)
		{
	Cookie cookie = new Cookie ("usuarioLimonnId",idCookie);
	cookie.setMaxAge(2147483647);
	response.addCookie(cookie);
		}
		}
 
%>
<%@ include file="/layout/taglibs.jsp" %>



<stripes:layout-render name="/layout/bradino.jsp" title="Limonn Patio Comidas">
    <stripes:layout-component name="contents">
<jsp:useBean id="gastronomiaManager" scope="page"
                     class="com.limonn.entityManager.GastronomiaManager"/>
 
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
              
              
              hora calculada para el delivery &nbsp; 
              
             
            
               ${actionBean.deliveryHour} 
             
             <br>   
               
               
               <stripes:link href="SetTime.jsp">cambie 
               <stripes:param name="user.id" value="${actionBean.user.id}"/>
               <stripes:param name="event" value="Start"/>
               </stripes:link> + tarde


                 <br><br>
            
         

                 <div id="cpanel" id="table">
                 <c:forEach items="${gastronomiaManager.especialidadesList}" var="especialidad">
                          
                          <div class="icon"> 
                          
                         <h2>${especialidad.nombre}</h2>

                           <div class="productos">
                             <table id="tablaProducto"> 

                           <c:forEach items="${especialidad.platoList}" var="plato">
                     <tr>
                     <td>
                    <stripes:link href="/Order.action" event="order" title="  AGREGAR AL PEDIDO">
					${plato.nombre}
                     <stripes:param name="user.id" value="${actionBean.user.id}"/>
                     <stripes:param name="plato.id" value="${plato.id}"/>
                     </stripes:link>
                     </td>
                      <td>
                       <c:choose>
                      <c:when test="${plato.precio < 10}">
                     &nbsp; ${plato.precio} &nbsp;
                      </c:when> 
                      <c:otherwise>
                      ${plato.precio} &nbsp;
                      </c:otherwise>
                      </c:choose>
					  </td>
                      </tr>
                    
                     </c:forEach>
                      
                    </table>
                     </div>
                     </div>
                     
                </c:forEach>
                 

            </div>
            
</stripes:layout-component>
</stripes:layout-render>