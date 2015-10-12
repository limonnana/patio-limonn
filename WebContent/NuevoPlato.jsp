<%@ include file="layout/taglibs.jsp" %>

<stripes:layout-render name="/layout/bradino.jsp" title="Nuevo plato">
    <stripes:layout-component name="contents">
<jsp:useBean id="gastronomiaManager" scope="page"
                     class="com.limonn.entityManager.GastronomiaManager" />

<c:if test="${userLimonnAdmin.role != 'Admin'}">
<meta http-equiv="refresh" content="0;url=${ctx}/index.jsp" />
</c:if>

        <stripes:errors globalErrorsOnly="true"/>

        <div class="body">
	
	
             <stripes:form action="/NuevoPlato.action" focus="plato.nombre">
             <stripes:hidden name="plato.id" value="${actionBean.plato.id}"></stripes:hidden>
               <b> &nbsp; &nbsp; Platos  </b><br><br>              

                     <table class="formTable">

                    <c:if test="${actionBean.plato.id == 0 or actionBean.plato.id == null}">
                   <tr>
                    <th><stripes:label for="especialidad.nombre"/>:</th>
                    <td>
                        <stripes:select name="especialidad.id"> 
                         <stripes:option value=" ">Seleccione una</stripes:option>
                         <stripes:options-collection collection="${gastronomiaManager.especialidadesList}" label="nombre" value="id" />
                                                      
                        </stripes:select>
                        <stripes:errors field="especialidad.nombre"/>
                    </td>
                </tr>
                </c:if> 
                    <tr>
                    <th><stripes:label for="plato.nombre"/>:</th>
                    <td>
                        <stripes:text name="plato.nombre" value="${actionBean.plato.nombre}"/> 
                        <stripes:errors field="plato.nombre"/>
                    </td>
                    </tr>

 <tr>
                    <th><stripes:label for="plato.precio"/>:</th>
                    <td>
                        <stripes:text name="plato.precio" value="${actionBean.plato.precio}"/> 
                        <stripes:errors field="plato.precio"/>
                    </td>
                    </tr>
                   
              </table>

            <div class="buttons">
                <stripes:submit name="guardar" value="Guardar"/>
            </div>
        </stripes:form>
        
            
            
           <br><br><br><br><br><br><br><br>

            </div>
                
    </stripes:layout-component>
</stripes:layout-render>