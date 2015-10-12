<%@ include file="/layout/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head profile="http://gmpg.org/xfn/11">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />


 <title>${title}</title>
<link rel="stylesheet"
                  type="text/css"
                  href="${ctx}/layout/bradino.css"/>

<style type="text/css">

body{
	background: #fff;
	margin-top: 30px;
	margin-left: 50px;
	
	}
table th{
	background:#fff;
	}

</style>

</head>
        <body>
  <div class="body">
Pedido: ${actionBean.envio.id} &nbsp; &nbsp;  ${actionBean.envio.deliveryGuy.name} <br><br>

Total: <b>${actionBean.envio.total} $</b>

<br><br><br>

   

                 <table>
                <tr>
                   
                    <th>Id</th>
                    <th>Nombre</th>
                    <th>Direccion &nbsp; &nbsp;</th>
                    <th>Total</th>
                    <th>Unidades</th>
                    <th>&nbsp;</th>
                    
                </tr>

                
                  <c:forEach items="${actionBean.envio.listaPedidos}" var="pedido">
                  <tr>
                          
                          <td> ${pedido.id}</td>
                          <td> ${pedido.cart.user.name}</td>
                          <td> ${pedido.cart.user.address}</td>
                          <td> ${pedido.cart.total} $</td>
                          <td> ${pedido.cart.totalPlatos}</td>
                          
                          <c:if test="${pedido.deliveryComment != null}">
                          <td>
                           D:${pedido.deliveryComment} 
                          </td>
                           </c:if>
                   
					</tr>
					</c:forEach>
                   </table>

</div>

<br><br><center><stripes:link href="Envios.jsp">continuar</stripes:link></center>

<br><br>
</body>
</html>
