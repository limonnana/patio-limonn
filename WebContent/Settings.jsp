<%@ include file="/layout/taglibs.jsp" %>
<c:if test="${userLimonnAdmin.role != 'Admin'}">
<meta http-equiv="refresh" content="0;url=${ctx}/index.jsp" />
</c:if>
<stripes:layout-render name="/layout/bradino.jsp" title="Configuraciones">
    <stripes:layout-component name="contents">
    
      <stripes:form action="/Settings.action" >
             <stripes:hidden name="settings.id"></stripes:hidden>
            <table class="formTable">

                 
                <tr>
                    <th><stripes:label for="settings.storeName"/>:</th>
                    <td>
                        <stripes:text name="settings.storeName" value="${actionBean.settings.storeName}"/> <span class="required"> * requerido</span>
                        <stripes:errors field="settings.storeName"/>
                    </td>
                </tr>
                
                <tr>
                    <th><stripes:label for="settings.storeAddress"/>:</th>
                    <td>
                        <stripes:text name="settings.storeAddress" value="${actionBean.settings.storeAddress}"/> 
                        <stripes:errors field="settings.storeAddress"/>
                    </td>
                </tr>
                 <tr>
                    <th><stripes:label for="settings.storePhone"/>:</th>
                    <td>
                        <stripes:text name="settings.storePhone" value="${actionBean.settings.storePhone}"/> <span class="required"> * requerido</span>
                        <stripes:errors field="settings.storePhone"/>
                    </td>
                </tr>
                 <tr>
                    <th><stripes:label for="settings.rutaDominio"/>:</th>
                    <td>
                        <stripes:text name="settings.rutaDominio" value="${actionBean.settings.rutaDominio}"/> <span class="required"> * requerido ej: limonn.com</span>
                        <stripes:errors field="settings.rutaDominio"/>
                    </td>
                </tr>
                
                <tr>
                    <th><stripes:label for="settings.minutesToAddCurrentTime"/>:</th>
                    <td>
                        <stripes:text name="settings.minutesToAddCurrentTime" value="${actionBean.settings.minutesToAddCurrentTime}"/> <span class="required"> * requerido ej: 180</span>
                        <stripes:errors field="settings.minutesToAddCurrentTime"/>
                    </td>
                </tr>
                
                <tr>
                    <th><stripes:label for="settings.minutosTardaDelivery"/>:</th>
                    <td>
                        <stripes:text name="settings.minutosTardaDelivery" value="${actionBean.settings.minutosTardaDelivery}"/> <span class="required"> * requerido ej: 23</span>
                        <stripes:errors field="settings.minutosTardaDelivery"/>
                    </td>
                </tr>
                 
                
            </table>

            <div class="buttons">
                <stripes:submit name="guardar"  value="Guardar"/>
            </div>
        </stripes:form>
        

            </div>
                
    </stripes:layout-component>
</stripes:layout-render>