<%@ include file="/layout/taglibs.jsp" %>


   
    
     <stripes:form action="/SetUp.action" focus="user.username">
	
	
 <table border="1">
<tbody>
<tr>
<td>
<stripes:label for="user.username"/>
</td>
<td>

 <stripes:text name="user.username"  id="user.username"/>
  <stripes:errors field="user.username"/>
</td>
</tr>
<tr>
<td>
<stripes:label for="user.password"/>

</td>
<td class="value">
 <stripes:password name="user.password"  id="user.password" />
 <stripes:errors field="user.password"/>
 </td>
</tr>
<tr>
<td>

<br><br>
<div class="actionButtons">
<stripes:submit name="login" value="Guardar"/><br>
</div>        


</td>
<td class="value">



</td>
</tr>
</tbody>
</table>

            </div></div></div>
             
        
        
   </stripes:form>

            </div>
                
  
