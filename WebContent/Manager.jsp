<%@ include file="layout/taglibs.jsp" %>
<c:if test="${userLimonnAdmin.role != 'Admin'}">
<meta http-equiv="refresh" content="0;url=${ctx}/index.jsp" />
</c:if>
<stripes:layout-render name="/layout/bradino.jsp" title="Panel de control">
    <stripes:layout-component name="contents">

        <stripes:errors globalErrorsOnly="true"/>

        <div class="body">
	&nbsp; Panel de control </div>
	
               <table>
                 <th>Usuarios</th>
                 <th>Pedidos</th>
                 <th>Cocina</th>
                 <th>Envios</th>
                 <th>Especialidades</th>
                 <th>Platos</th>
                 <th>Configuraciones</th>
                 <tr>
                 <td>
                 <stripes:link href="${ctx}/SearchUser.action">Usuarios </stripes:link>
                 <br>
                 <stripes:link href="${ctx}/Register.jsp"> Nuevo usuario</stripes:link>
                 </td>
                 <td>
                 <stripes:link href="${ctx}/Pedidos.jsp">Pedidos </stripes:link>
                 </td>
                  <td>
                 <stripes:link href="${ctx}/Cocina.jsp">Cocina </stripes:link>
                 </td>
                  <td>
                 <stripes:link href="${ctx}/Envios.jsp">Envios </stripes:link>
                 </td>
                  <td>
                   
                   <stripes:link href="${ctx}/Especialidades.jsp">Especialidades</stripes:link>
                   <br>
                  <stripes:link href="${ctx}/NuevaEspecialidad.jsp">Nueva especialidad</stripes:link>
                  </td>
                   <td>
                   <stripes:link href="${ctx}/Platos.jsp">Platos</stripes:link>
<br>
                  <stripes:link href="${ctx}/NuevoPlato.jsp">Nuevo plato</stripes:link>
                   </td>
                   <td>
                   <stripes:link href="${ctx}/Settings.action" event="getSettingsFromDb">Configuraciones</stripes:link>
                   </td>
                   </tr>
                  </table>
            
           

            <br><br><br><br><br><br><br><br>

            </div>
                
    </stripes:layout-component>
</stripes:layout-render>