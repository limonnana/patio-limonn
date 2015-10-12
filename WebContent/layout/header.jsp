<%@ include file="taglibs.jsp" %>

<table width="100%" cellspacing="0" cellpadding="0" border="0" class="dr-toolbar-ext rich-toolbar">
	<tr valign="middle">
	<td class="dr-toolbar-int rich-toolbar-item">Patio  de comida &nbsp;   limonn . com</td>
	<td class="dr-toolbar-int rich-toolbar-item">
	<a href="${ctx}/Manager.jsp">Manager</a>
	</td>
	<td width="100%"/>
	<td class="dr-toolbar-int rich-toolbar-item"/>
	 <c:if test="${not empty user}">
	<td class="dr-toolbar-int rich-toolbar-item">
	
                    Bienvenido: ${user.name} ${user.lastName}
                    |
	<stripes:link  href="/Logout.action">Logout</stripes:link>
	</td>
	</c:if>
	 <c:if test="${empty user}">
	<td class="dr-toolbar-int rich-toolbar-item">
	<a href="${ctx}/Login.jsp">Login</a>
	</td>
	</c:if>
	<td class="dr-toolbar-int rich-toolbar-item"/>
	</tr>
	</table>