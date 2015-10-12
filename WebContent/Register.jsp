<%@ include file="layout/taglibs.jsp" %>


<stripes:layout-render name="/layout/bradino.jsp" title="Nuevo usuario">
    <stripes:layout-component name="contents">

<c:if test="${userLimonnAdmin.role == 'Admin'}">
<jsp:useBean id="componentManager" scope="page"
                     class="com.limonn.entities.Role"/>
</c:if>

        <stripes:errors/>

        <div class="body">
	
	
             <stripes:form action="/Register.action" focus="user.address">
             <stripes:hidden name="user.id" value="${actionBean.user.name}"></stripes:hidden>
             
              <c:choose>

             <c:when test="${userLimonnAdmin.role == 'Admin'}">

               <b>&nbsp; &nbsp;RESTRICTED AREA: &nbsp; Admin Page  </b><br><br>              

             </c:when>
             
             <c:otherwise>
            <b>Unica vez para un delivery rapido : </b><br><br><br>
             </c:otherwise>
             
               </c:choose>           

            <table class="formTable">

                  <c:if test="${userLimonnAdmin.role == 'Admin'}">
                   
                    <tr>
                    <th><stripes:label for="user.username"/>:</th>
                    <td>
                        <stripes:text name="user.username" value="${actionBean.user.username}"/> 
                        <stripes:errors field="user.username"/>
                    </td>
                </tr>
                   <tr>
                    <th><stripes:label for="user.password"/>:</th>
                    <td>
                        <stripes:password name="user.password" value="${actionBean.user.password}"/> 
                        <stripes:errors field="user.password"/>
                    </td>
                </tr>

                 <tr>
                    <th><stripes:label for="user.role"/>:</th>
                    <td>
                        <stripes:select name="user.role"> 
                         <stripes:option value=" ">Seleccione uno</stripes:option>
                         <stripes:options-collection collection="${componentManager.roleList}"/>
                           <stripes:option value="Manager">Caja</stripes:option>                           
                        </stripes:select>
                        <stripes:errors field="user.role"/>
                    </td>
                </tr>
                   
                  </c:if>
                <tr>
                    <th><stripes:label for="user.name"/>:</th>
                    <td>
                        <stripes:text name="user.name" value="${actionBean.user.name}"/> <span class="required"> * requerido</span>
                        <stripes:errors field="user.name"/>
                    </td>
                </tr>
                 <tr>
                    <th><stripes:label for="user.lastName"/>:</th>
                    <td>
                        <stripes:text name="user.lastName" value="${actionBean.user.lastName}"/> 
                        <stripes:errors field="user.lastName"/>
                    </td>
                </tr>

                 <tr>
                    <th><stripes:label for="user.address"/>:</th>
                    <td>
                        <stripes:text name="user.address" value="${actionBean.user.address}"/> <span class="required"> * </span>Completa:  calle, numero, piso, oficina
                        <stripes:errors field="user.address"/>
                    </td>
                </tr>
                <tr>
                    <th><stripes:label for="user.cellphone"/>:</th>
                    <td>
                        <stripes:text name="user.cellphone" value="${actionBean.user.cellphone}"/> <span class="required"> * </span>
                        <stripes:errors field="user.cellphone"/>
                    </td>
                </tr>                

                <tr>
                    <th><stripes:label for="user.email"/>:</th>
                    <td>
                        <stripes:text name="user.email" value="${actionBean.user.email}"/> <span class="required"> * </span>
                        <stripes:errors field="user.email"/>
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