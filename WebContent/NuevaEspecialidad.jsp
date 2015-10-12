<%@ include file="layout/taglibs.jsp" %>

<stripes:layout-render name="/layout/bradino.jsp" title="Especialidad">
    <stripes:layout-component name="contents">

<c:if test="${userLimonnAdmin.role != 'Admin'}">
<meta http-equiv="refresh" content="0;url=${ctx}/index.jsp" />
</c:if>
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
        <stripes:errors globalErrorsOnly="true"/>

        <div class="body">
	
	
             <stripes:form action="/NuevaEspecialidad.action">
             <stripes:hidden name="especialidad.id" value="${actionBean.especialidad.id}"></stripes:hidden>
              <br>              

                     <table class="formTable">

                    <tr>
                    <th><stripes:label for="especialidad.nombre"/>:</th>
                    <td>
                        <stripes:text name="especialidad.nombre" value="${actionBean.especialidad.nombre}"/> 
                        <stripes:errors field="especialidad.nombre"/>
                    </td>
                    </tr>

                     <tr>
                    <th><stripes:label for="especialidad.posicion"/>:</th>
                    <td>
                        <stripes:text name="especialidad.posicion" value="${actionBean.especialidad.posicion}"/> 
                        <stripes:errors field="especialidad.posicion"/>
                    </td>
                    </tr>
                   
              </table>

            <div class="buttons">
                <stripes:submit name="guardar" value="Guardar"/>
            </div>
        </stripes:form>
        
            
            
           <br><br>

            <c:if test="${actionBean.especialidad!= null}">
   <b> ${actionBean.especialidad.nombre} </b><br>
            <table>
                <tr>
                   
                    <th>Id</th>
                    <th>Nombre</th>
                    <th>Precio</th>
                    <th>Editar</th>
                    
                    <c:if test="${userLimonnAdmin.role == 'Admin'}">
                    <th>Borrar</th>
                    </c:if>
                </tr>

                
                 <c:forEach items="${actionBean.especialidad.platoList}" var="plato">
                  <tr>
                          <c:choose>
                          <c:when test="${plato.id < 10}">
                          <td>&nbsp;&nbsp;${plato.id}<b>.</b></td>
                          </c:when>
                          <c:otherwise>
                          <td>${plato.id}<b>.</b></td>
                          </c:otherwise>
                          </c:choose>
                          <td>${plato.nombre}</td>
                          <td>${plato.precio}</td>
                          <td> 
                           <stripes:link href="/EditPlato.action" event="preEdit">  ${plato.nombre}
                           <stripes:param name="plato.id" value="${plato.id}"/>
                           </stripes:link>
                           </td>
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
             </c:if>





<br><br><br><br><br><br>

            </div>
                
    </stripes:layout-component>
</stripes:layout-render>