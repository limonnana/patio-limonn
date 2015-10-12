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



<stripes:layout-render name="/layout/bradino.jsp" title="Patio comidas">
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

  </div>
  
  Felicitaciones ahora puede hacer sus pedidos de comida en un click !! 
  
  <br><br>
  Su cuenta a sido activada y el sistema reconoce automaticamente su nombre y direccion.
  <br><br>
  <stripes:link href="${ctx}/index.jsp">Compruebelo</stripes:link>
  <br><br><br><br>
  
            
</stripes:layout-component>
</stripes:layout-render>