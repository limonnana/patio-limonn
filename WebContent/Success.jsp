<%@ include file="/layout/taglibs.jsp" %>
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

<stripes:layout-render name="/layout/bradino.jsp" title="Pedido exitoso">
    <stripes:layout-component name="contents">
    
    Felicitaciones !!   su pedido fue procesado exitosamente y va en camino a la hora estipulada<br><br>
      <c:if test="${actionBean.mensaje != null}">
             Pedido <b> numero: ${actionBean.mensaje}, estipulado para las &nbsp; ${actionBean.deliveryHour}</b><br><br><br><br>
            
            </c:if>
            </div>
                
    </stripes:layout-component>
</stripes:layout-render>