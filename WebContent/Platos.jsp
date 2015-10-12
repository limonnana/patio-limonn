<%@ include file="layout/taglibs.jsp" %>

<stripes:layout-render name="/layout/bradino.jsp" title="Platos">
    <stripes:layout-component name="contents">

 <jsp:useBean id="gastronomiaManager" scope="page"
                     class="com.limonn.entityManager.GastronomiaManager"/>
<script LANGUAGE="JavaScript">
<!--
// Nannette Thacker http://www.shiningstar.net
function confirmDelete(name)
{
var agree=confirm("Estas seguro de querer borrar:  "+name+"?");
if (agree)
	return true ;
else
	return false ;
}
// -->
</script>

<c:if test="${userLimonnAdmin.role != 'Admin'}">
<meta http-equiv="refresh" content="0;url=${ctx}/index.jsp" />
</c:if>

        <stripes:errors globalErrorsOnly="true"/>

        <div class="body">
  
     <c:if test="${userLimonnAdmin.role == 'Admin'}">
	<div class="user">
       ${userLimonnAdmin.name} , Posicion: ${userLimonnAdmin.role} 
    </div>
    </c:if>
               

                  <div>Platos &nbsp; |  &nbsp; <stripes:link href="${ctx}/NuevoPlato.jsp">Nuevo plato</stripes:link>
                    </div>   

                 <table>
                <tr>
                   
                    
                    <th>Nombre</th>
                    <th>Precio</th>
                    <th>Editar</th>
                     <c:if test="${userLimonnAdmin.role == 'Admin'}">
                    <th>Borrar</th>
                    </c:if>
                </tr>

                
                 <c:forEach items="${gastronomiaManager.platosList}" var="plato">
                  <tr>
                          <td>${plato.nombre}</td>
                        <c:choose>
                      <c:when test="${plato.precio < 10}">
                    <td> &nbsp; ${plato.precio} &nbsp;</td>
                      </c:when> 
                      <c:otherwise>
                     <td> ${plato.precio} &nbsp; </td>
                      </c:otherwise>
                      </c:choose>
                         
                           <td> 
                           <stripes:link href="/EditPlato.action" event="preEdit">  ${plato.nombre}
                           <stripes:param name="plato.id" value="${plato.id}"/>
                           </stripes:link>
                           <c:if test="${userLimonnAdmin.role == 'Admin'}">
                           <td align="center"> 
                           <stripes:link href="/DeletePlato.action" onclick="return confirmDelete('${plato.nombre}')" event="deletePlato">
                         <img src="${ctx}/layout/images/deleteicon1.jpg" border=0 width=15 height=15  alt="Delete" >
                           <stripes:param name="plato.id" value="${plato.id}"/>
                           </stripes:link>
                           </td>
                           </c:if>
                </tr>
                </c:forEach>
                 
                   
            </table>

         
            </div>
        
        
            
            
           <br><br><br><br><br><br><br><br>

            </div>
                
    </stripes:layout-component>
</stripes:layout-render>