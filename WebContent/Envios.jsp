<%@ include file="/layout/taglibs.jsp" %>

<stripes:layout-render name="/layout/bradino.jsp" title="Envios">
    <stripes:layout-component name="contents">
    
<jsp:useBean id="gastronomiaManager" scope="page"
                     class="com.limonn.entityManager.GastronomiaManager"/>
<c:if test="${userLimonnAdmin.role != 'Admin'}">
<meta http-equiv="refresh" content="0;url=${ctx}/index.jsp" />
</c:if>  



     
<c:if test="${actionBean.mensaje != null}">
      <div style="color:#b72222; font-weight: bold">

   Por favor corrija los siguientes errores:</div><ol><li style="color: #b72222;">

   ${actionBean.mensaje}</li></ol>                   

     </c:if>    <p style="color: #000;font-weight:bold;">Pedidos  para enviar</p>               
     <stripes:form action="/Envios.action">
      <table class="formTable">
      <tr>
      <th>Delivery</th>
      <td>
       <c:forEach var="deliveryGuyList" items="${gastronomiaManager.deliveryGuyList}">
       <stripes:radio name="user.id" value="${deliveryGuyList.id}" id="${deliveryGuyList.name}"/> 
       <stripes:label for="${deliveryGuyList.name}">${deliveryGuyList.name}</stripes:label>                 
       &nbsp;  &nbsp;  &nbsp;
       </c:forEach>
       <stripes:radio name="user.id" value="0" id="Otro"/> 
       <stripes:label for="Otro">Otro</stripes:label> 
      </td>
      </tr>
      <tr>
      
      <th>Pedidos listos</th>
      
      
      <td>
      <br>
        <c:forEach items="${gastronomiaManager.pedidosNoEnviados}" var="pedido" varStatus="loop"> 
                         <stripes:checkbox name="pedido"  value="${pedido.id}"/> &nbsp; ${pedido.id}  -  ${pedido.cart.user.address}<br><br>
            </c:forEach>
       <stripes:errors field="pedido"/>
      </td>
      
      </tr>
      </table>
      
      <stripes:submit name="enviar">Enviar</stripes:submit>
      </stripes:form>
            </div>
                
    </stripes:layout-component>
</stripes:layout-render>