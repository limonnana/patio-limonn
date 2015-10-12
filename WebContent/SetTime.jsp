<%@ include file="/layout/taglibs.jsp" %>

<stripes:layout-render name="/layout/bradino.jsp" title="Cambie hora delivery">
    <stripes:layout-component name="contents">
    
      <stripes:form action="/SetTime.action">
       <stripes:hidden name="user.id" value="${user.id}"></stripes:hidden>
       <stripes:hidden name="event" ></stripes:hidden>
      <table width="300px">
 
                    <tr>
                    <th width="150px"><stripes:label for="deliveryTime"/>&nbsp;&nbsp;&nbsp;:</th>
                    <td width="150px">
                        <stripes:select  name="deliveryHour">
                        <stripes:option>11:45</stripes:option>
                        <stripes:option>12:00</stripes:option>
                        <stripes:option>12:15</stripes:option>
                        <stripes:option>12:30</stripes:option>
                        <stripes:option>12:45</stripes:option>
                        <stripes:option value="13:00">1:00</stripes:option>
                        <stripes:option value="13:15">1:15</stripes:option>
                        <stripes:option value="13:30">1:30</stripes:option>
                        <stripes:option value="13:45">1:45</stripes:option>
                        <stripes:option value="14:00">2:00</stripes:option>
                        
                        <stripes:option value="14:15">2:15</stripes:option>
                        <stripes:option value="14:30">2:30</stripes:option>
                        <stripes:option value="14:45">2:45</stripes:option>
                        <stripes:option value="15:00">3:00</stripes:option>
                        <stripes:option value="15:15">3:15</stripes:option>
                        <stripes:option value="15:30">3:30</stripes:option>
                        </stripes:select> 
                       
                    </td>
                </tr>
                  
      
      </table>
      <br><br>
      <stripes:submit name="setTime">Cambiar</stripes:submit>
      </stripes:form>

            </div>
                
    </stripes:layout-component>
</stripes:layout-render>