<%@ include file="/layout/taglibs.jsp" %>

<stripes:layout-render name="/layout/bradino.jsp" title="Deliverys">
    <stripes:layout-component name="contents">
    
<jsp:useBean id="gastronomiaManager" scope="page"
                     class="com.limonn.entityManager.GastronomiaManager"/>
<jsp:useBean id="date" class="java.util.Date"/> 

<c:if test="${userLimonnAdmin.role != 'Admin'}">
<meta http-equiv="refresh" content="0;url=${ctx}/index.jsp" />
</c:if>  

<SCRIPT Language="JavaScript">
<!--
function submitForm(id) {
	//document.getElementById('formulario').submit();
	window.location = "${ctx}/DeliverysFinancial.action?showFinancial=&user.id=" + id;
}
//-->
</SCRIPT>

     
<c:if test="${actionBean.mensaje != null}">
      <div style="color:#b72222; font-weight: bold">

   Por favor corrija los siguientes errores:</div><ol><li style="color: #b72222;">

   ${actionBean.mensaje}</li></ol>                   

     </c:if>                   
     <stripes:form id="formulario"  action="/DeliverysFinancial.action">
      <table class="formTable">
      <tr>
      <th>Delivery</th>
      <td>
       <c:forEach var="deliveryGuyList" items="${gastronomiaManager.deliveryGuyList}">
       <stripes:radio name="user.id" value="${deliveryGuyList.id}" id="${deliveryGuyList.name}"  onclick="submitForm(${deliveryGuyList.id})"/> 
       <stripes:label for="${deliveryGuyList.name}">${deliveryGuyList.name}</stripes:label>                 
       
       </c:forEach>
       
      </td>
      </tr>
     
      </table>
<c:if test="${actionBean.ejercicioFinanciero != null}">
<br><br>
Ejercicio comenzo: <fmt:formatDate value="${actionBean.ejercicioFinanciero.date}" type="date" pattern="dd/MM    . .    hh:mm"/>
<br> 
<stripes:link href="/DeliverysFinancial.action" event="closeEjercicio">cerrar
<stripes:param name="ejercicioFinanciero.id" value="${actionBean.ejercicioFinanciero.id}"/>
<stripes:param name="user.id" value="${actionBean.ejercicioFinanciero.user.id}"/>
 </stripes:link>
</c:if>
<br>
<br>

<table>
<td>
<b>delivery RECIBE </b> &nbsp; &nbsp; &nbsp; &nbsp;<stripes:submit name="addPasivo" value="guardar pasivo"></stripes:submit><br>
<table>

<tr>
<th>
cantidad $
</th>

<td>
<stripes:text name="pasivo.cantidad"></stripes:text>
</td>
</tr>

<tr>
<th valign="top">
recibe cambio
<br><br>
otro
</th>
<td>

<stripes:checkbox name="pasivo.concepto" value="recibe cambio"/>
<br><br>
<stripes:text name="pasivo.concepto"></stripes:text>
</td>
</tr>
</table>

</td>


<td>
<b>delivery ENTREGA </b>  &nbsp; &nbsp; &nbsp; &nbsp;<stripes:submit name="addActivo" value="guardar activo"></stripes:submit> <br>
<table>

<tr>
<th>
cantidad $
</th>

<td>
<stripes:text name="activo.cantidad"></stripes:text>
</td>
</tr>

<tr>
<th>
entrega efectivo
<br><br>
otro
</th>
<td>
<stripes:checkbox name="activo.concepto" value="entrega efectivo"/>
<br><br>
<stripes:text name="activo.concepto"></stripes:text>
</td>
</tr>
</table>
</td>

</table>




<c:choose>
<c:when test="${actionBean.ejercicioFinanciero.pasivos!= null || actionBean.ejercicioFinanciero.activos != null}">
<br><br>
 <b>${actionBean.ejercicioFinanciero.user.name} ,  &nbsp; balance: 
 <c:if test="${actionBean.balance>=0}">
 ${actionBean.balance}
 </c:if>
 
 <c:if test="${actionBean.balance<0}">
 <font color="red">${actionBean.balance}</font>
 </c:if>
 

 
  </b>
<br><br>
<table width="920px">
<tr>
<td valign="top">  

<b>Debe</b><br>
      <table width="460px">
     

      <c:forEach items="${actionBean.ejercicioFinanciero.pasivos}" var="pasivo">
        <tr>
        <td>
         ${pasivo.concepto} &nbsp;&nbsp;&nbsp;&nbsp; <fmt:formatDate value="${pasivo.date}" type="date" pattern="dd/MM   . .   hh:mm"/> 
       </td>
        <td>
         ${pasivo.cantidad} $
        </td>
        </tr>
      </c:forEach>
       <tr>
       <td>
         total
       </td>
        <td>
         ${actionBean.resultadoPasivos}
        </td>
        </tr>
      </table>


</td>
<td valign="top">
<b>Haber</b><br>
      <table width="460px">
       
      
      <c:forEach items="${actionBean.ejercicioFinanciero.activos}" var="activo">
        <tr>
        <td>
         ${activo.concepto}  &nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatDate value="${activo.date}" type="date" pattern="dd/MM    . .    hh:mm"/> 
         </td>
         <td>
         ${activo.cantidad} $
        </td>
        </tr>
      </c:forEach>
      <tr>
       <td>
         total
       </td>
        <td>
         ${actionBean.resultadoActivos}
        </td>
        </tr>
      </table>


</td>
</tr>
</table>

</c:when>
<c:otherwise>
<c:if test="${actionBean.ejercicioFinanciero.user.name != null}">
El balance de ${actionBean.ejercicioFinanciero.user.name} es 0.
</c:if>
</c:otherwise>

   </c:choose>  


      
     
      </stripes:form>


     


            </div>
                
    </stripes:layout-component>
</stripes:layout-render>