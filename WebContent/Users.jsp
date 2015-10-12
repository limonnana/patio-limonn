<%@ include file="/layout/taglibs.jsp" %>

<c:if test="${userLimonnAdmin.role != 'Admin'}">
<meta http-equiv="refresh" content="0;url=${ctx}/index.jsp" />
</c:if>

<stripes:layout-render name="/layout/bradino.jsp" title="Usuarios">
    <stripes:layout-component name="contents">
   
<script LANGUAGE="JavaScript">
<!--
// Nannette Thacker http://www.shiningstar.net
function confirmDelete(name)
{
var agree=confirm("Estas seguro de querer borrar el usuario : "+name+"?");
if (agree)
	return true ;
else
	return false ;
}
// -->
</script>
        <stripes:errors globalErrorsOnly="true"/>

        <div class="body">
    <c:if test="${userLimonnAdmin.role == 'Admin'}">
	<div class="user">
       ${userLimonnAdmin.name} , Posicion: ${userLimonnAdmin.role} 
    </div>
    </c:if>
	<div> <stripes:link href="/SearchUser.action">Usuarios </stripes:link>&nbsp; |  &nbsp; <stripes:link href="${ctx}/Register.jsp"> Nuevo usuario</stripes:link></div>
	

    <div id="live-search"> 
    <div class="inside"> 
    <div id="search"> 
    <stripes:form action="/SearchUser.action" focus="user.phone">
        
       Telefono: <stripes:text name="user.phone"  />
        
        <stripes:image name="phone" src="/layout/images/search.gif" alt="Search:" style="width:14px;height:13px;margin-top: 2px;
	position: relative;top: 0.25em;border:0px;" />

       &nbsp; &nbsp; Nombre: <stripes:text name="user.name"  />
        
        <stripes:image name="name" src="/layout/images/search.gif" alt="Search:" style="width:14px;height:13px;margin-top: 2px;
	position: relative;top: 0.25em;border:0px;" />

      &nbsp; &nbsp; Apellido: <stripes:text name="user.lastName"  />
        
        <stripes:image name="lastName" src="/layout/images/search.gif" alt="Search:" style="width:14px;height:13px;margin-top: 2px;
	position: relative;top: 0.25em;border:0px;" />

      &nbsp; &nbsp;  Direccion: <stripes:text name="user.address"  />
        
        <stripes:image name="address" src="/layout/images/search.gif" alt="Search:" style="width:14px;height:13px;margin-top: 2px;
	position: relative;top: 0.25em;border:0px;" />

    </stripes:form>
     
    </div>
     
  </div>
	
</div>

    


            <table>
                <tr>
                   
                    
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>Direccion</th>
                    <th>HACER PEDIDO</th>
                    <th>Telefono</th>
                    <th>Email</th>
                    <th>Editar</th>
                    <c:if test="${userLimonnAdmin.role == 'Admin'}">
                    <th>Borrar</th>
                    </c:if>
                    <th>Rol</th>
                </tr>

                
                 <c:forEach items="${actionBean.userList}" var="person">
                  <tr>
                         
                          <td>${person.name }</td>
                          <td>${person.lastName }</td>
                          <td>${person.address}</td>
                           <td> <stripes:link href="Start.action?id=${person.id}"> >> </stripes:link></td>
                          <td>${person.cellphone}</td>
                           <td>${person.email}</td>
                           <td> 
                           <stripes:link href="/EditUser.action" event="preEdit">  ${person.name}
                           <stripes:param name="user.id" value="${person.id}"/>
                           </stripes:link>
                           <c:if test="${userLimonnAdmin.role == 'Admin'}">
                           <td align="center"> 
                           <stripes:link href="/DeleteUser.action" onclick="return confirmDelete('${person.name}')" event="deleteUser">
                         <img src="${ctx}/layout/images/deleteicon1.jpg" border=0 width=15 height=15  alt="Delete" >
                           <stripes:param name="user.id" value="${person.id}"/>
                           </stripes:link>
                           </td>
                           </c:if>
                           <td>${person.role}
                           
                           </td>
                </tr>
                </c:forEach>
                 
                   
            </table>
        <br><br><br><br><br><br><br><br><br><br>
         
           

            </div>


                
    </stripes:layout-component>
</stripes:layout-render>