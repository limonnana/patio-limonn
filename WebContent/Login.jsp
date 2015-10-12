<%@ include file="/layout/taglibs.jsp" %>

<stripes:layout-render name="/layout/bradino.jsp" title="Autenticarse">
    <stripes:layout-component name="contents">
    
  <div class="errors">   ${actionBean.usernameNoExiste} ${actionBean.passwordIncorrecto}</div>

     <stripes:form action="/Login.action" focus="user.username">
	
	
 <table border="1">
<tbody>
<tr>
<td>
<stripes:label for="user.username"/>
</td>
<td>

 <stripes:text name="user.username"  id="user.username" value="limonn"/>
  <stripes:errors field="user.username"/>
</td>
</tr>
<tr>
<td>
<stripes:label for="user.password"/>

</td>
<td class="value">
 <stripes:password name="user.password"  id="user.password" value="argentina"/>
 <stripes:errors field="user.password"/>
 </td>
</tr>
<tr>
<td>

<br><br>
<div class="actionButtons">
<stripes:submit name="login" value="Ingresar"/><br>
</div>        
<stripes:link href="Forgot.jsp" class="forgot">olvide la clave</stripes:link>

</td>
<td class="value">



</td>
</tr>
</tbody>
</table>

            </div></div></div>
             
        
        
   </stripes:form>

            </div>
                
    </stripes:layout-component>
</stripes:layout-render>