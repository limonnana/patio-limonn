<%@ include file="taglibs.jsp" %>
<jsp:useBean id="settings" scope="page"
                     class="com.limonn.entityManager.UserManager"/>
<stripes:layout-definition>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head profile="http://gmpg.org/xfn/11">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />


 <title>${title}</title>
<link rel="stylesheet"
                  type="text/css"
                  href="${ctx}/layout/bradino.css"/>
<link rel="stylesheet"
                  type="text/css"
                  href="${ctx}/layout/menu.css"/>

<script type="text/javascript">
<!--
window.onload=montre;
function montre(id) {
var d = document.getElementById(id);
	for (var i = 1; i<=10; i++) {
		if (document.getElementById('smenu'+i)) {document.getElementById('smenu'+i).style.display='none';}
	}
if (d) {d.style.display='block';}
}
//-->
</script>

 
        </head>
        <body>
          
	
	<stripes:messages/>
	
<div id="header"> 
  <div class="inside"> 
    <h2><a href="index.jsp"> 
      LIMON<font color="#33ff00">N </font>Patio Comidas       </a></h2>

    <p class="description"> 
     DELIVERY en un click   </p><br>
  </div>
</div>

<!-- [END] #header -->


<!-- inicio menu -->
<c:if test="${userLimonnAdmin.role == 'Admin'}">


<div id="menu">

      <dl>

		<dt onmouseover="montre('smenu1');"> <stripes:link href="${ctx}/Deliverys.jsp">Deliverys </stripes:link></dt>
			<dd id="smenu1">
				<ul>
					
                    
					
				</ul>

			</dd>
	</dl>

	<dl>

		<dt onmouseover="montre('smenu2');"> <stripes:link href="${ctx}/Envios.jsp">Envios </stripes:link></dt>
			<dd id="smenu2">
				<ul>
					
                    <li><a href="${ctx}/EnviosMandados.jsp">Envios (de hoy)</a></li>
					
					 
				</ul>

			</dd>
	</dl>
	
	<dl>	
		<dt onmouseover="montre('smenu3');"> <stripes:link href="${ctx}/SearchUser.action">Usuarios </stripes:link></dt>
			<dd id="smenu3">
				<ul>
					<li><stripes:link href="${ctx}/Register.jsp"> Nuevo usuario</stripes:link></li>

					
					
				</ul>
			</dd>
	</dl>
	
	<dl>	
  		
		<dt onmouseover="montre('smenu4');"><stripes:link href="${ctx}/Pedidos.jsp">Pedidos </stripes:link></dt>
			<dd id="smenu4">
				<ul>
					

				</ul>
			</dd>
	</dl>
	
	<dl>	
		<dt onmouseover="montre('smenu5');"> <stripes:link href="${ctx}/Cocina.jsp">Cocina </stripes:link></dt>
			<dd id="smenu5">
				<ul>
					
				</ul>
			</dd>
	</dl>

   
	
	
	



	
	
	
</div>

</c:if>
<!-- end menu -->

<div id="primary" class="single-post"> 
  <div class="inside"> 
        <div class="primary"> 
       
      <stripes:layout-component name="contents"/> 
      
 </div>
    </div>
    <div class="clear"></div>
  </div>
</div>
<!-- [END] #primary -->
<hr class="hide" />
<div id="secondary"> 
  <div class="inside"> 

<center>${settings.settings.storeName} &nbsp; &nbsp; &nbsp; ${settings.settings.storeAddress}&nbsp; &nbsp; &nbsp;<b> Tel: ${settings.settings.storePhone} </b></center>
</div>

</div>

<div class="footer">
		Created by <a href="http://www.limonn.com">Limonn</a>.
		Powered by <stripes:link href="http://www.stripesframework.org" style="color:gray;text-decoration:none;"> Stripes </stripes:link> 
	</div>

<div id="login">
<br><br>
<c:choose>

<c:when test="${empty userLimonnAdmin}">

<stripes:link href="/Login.jsp">Login</stripes:link>

</c:when>
<c:otherwise>
<stripes:link href="/index.jsp">Home</stripes:link> &nbsp;  |  &nbsp;
<stripes:link href="/Manager.jsp">Manager</stripes:link> &nbsp;  |  &nbsp;
<stripes:link href="/Logout.action">Logout</stripes:link> 

</c:otherwise>
</c:choose>
</div>
<br><br>
</body>
</html>

</stripes:layout-definition>  