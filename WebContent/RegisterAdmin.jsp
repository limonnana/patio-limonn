<%@ include file="layout/taglibs.jsp" %>

<stripes:layout-render name="/layout/bradino.jsp" title="Nuevo usuario">
    <stripes:layout-component name="contents">

<jsp:useBean id="componentManager" scope="page"
                     class="com.limonn.entities.Role"/>

        <stripes:errors globalErrorsOnly="true"/>

        <div class="body">
	
	
   
    <stripes:useActionBean var="actionBean" binding="/Register.action"/>
             <stripes:form action="/Register.action" focus="user.name">
            <b>&nbsp; &nbsp;RESTRICTED AREA: &nbsp; Admin Page  </b><br><br>

            <table class="formTable">
                
                   <tr>
                    <th><stripes:label for="user.username"/>:</th>
                    <td>
                        <stripes:text name="user.username"/> <span class="required"> * requerido</span>
                        <stripes:errors field="user.username"/>
                    </td>
                </tr>
                   <tr>
                    <th><stripes:label for="user.password"/>:</th>
                    <td>
                        <stripes:text name="user.password"/> <span class="required"> * </span>
                        <stripes:errors field="user.password"/>
                    </td>
                </tr>
                   <tr>
                    <th><stripes:label for="user.name"/>:</th>
                    <td>
                        <stripes:text name="user.name"/> <span class="required"> * </span>
                        <stripes:errors field="user.name"/>
                    </td>
                </tr>
                 <tr>
                    <th><stripes:label for="user.address"/>:</th>
                    <td>
                        <stripes:text name="user.address"/> Completa:  calle, numero, piso, oficina
                        <stripes:errors field="user.address"/>
                    </td>
                </tr>
                <tr>
                    <th><stripes:label for="user.cellphone"/>:</th>
                    <td>
                        <stripes:text name="user.cellphone"/> 
                        <stripes:errors field="user.cellphone"/>
                    </td>
                </tr>                

                <tr>
                    <th><stripes:label for="user.email"/>:</th>
                    <td>
                        <stripes:text name="user.email"/> 
                        <stripes:errors field="user.email"/>
                    </td>
                </tr>
                  
<tr>
                    <th><stripes:label for="user.role"/>:</th>
                    <td>
                        <stripes:select name="user.role"> <span class="required"> * </span>
                         <stripes:options-collection collection="${componentManager.roleList}" />
                                                        
                        </stripes:select>
                        <stripes:errors field="user.role"/>
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