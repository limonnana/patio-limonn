<%

String cookieName = "usuarioLimonnId";
Cookie cookies [] = request.getCookies();
Cookie myCookie = null;
if (cookies != null)
{
for (int i = 0; i < cookies.length; i++) 
{
if (cookies[i].getName().equals(cookieName))
{
myCookie = cookies[i];
break;
}
}
}
%>
<%@ include file="layout/taglibs.jsp" %>
<%
if(myCookie != null)
{
%>
<meta http-equiv="refresh" content="0;url=${ctx}/Start.action?id=<%=myCookie.getValue()%>" />
<%
}else
{
%>
<meta http-equiv="refresh" content="0;url=${ctx}/Start.action" />
<% } %>