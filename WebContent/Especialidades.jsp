<%@ include file="layout/taglibs.jsp" %>

<stripes:layout-render name="/layout/bradino.jsp" title="Especialidades">
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
               

                  <div>Especialidades &nbsp; |  &nbsp; <stripes:link href="${ctx}/NuevaEspecialidad.jsp">Nueva Especialidad</stripes:link>
                    </div>   

                 <table>
                <tr>
                   
                    
                    <th>Nombre</th>
                    <th>Posicion</th>
                    <th>Editar</th>
                     <c:if test="${userLimonnAdmin.role == 'Admin'}">
                    <th>Borrar</th>
                    </c:if>
                </tr>

                
                 <c:forEach items="${gastronomiaManager.especialidadesList}" var="especialidad">
                  <tr>
                          <td>${especialidad.nombre}</td>
                          <td>${especialidad.posicion}</td>
                         
                           <td> 
                           <stripes:link href="/EditEspecialidad.action" event="preEdit">  ${especialidad.nombre}
                           <stripes:param name="especialidad.id" value="${especialidad.id}"/>
                           </stripes:link>
                           <c:if test="${userLimonnAdmin.role == 'Admin'}">
                           <td align="center"> 
                           <stripes:link href="/DeleteEspecialidad.action" onclick="return confirmDelete('${especialidad.nombre}')" event="deleteEspecialidad">
                         <img src="${ctx}/layout/images/deleteicon1.jpg" border=0 width=15 height=15  alt="Delete" >
                           <stripes:param name="especialidad.id" value="${especialidad.id}"/>
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