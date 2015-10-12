<%@ include file="taglibs.jsp" %>


<stripes:layout-definition>
    <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
    <html>
        <head>     
     <title>${title}</title>
            <link rel="stylesheet"
                  type="text/css"
                  href="${ctx}/layout/css.css"/>
 <stripes:layout-component name="html-head"/>
        </head>
        <body>
           <stripes:layout-component name="header">
            <jsp:include page="/layout/header.jsp"/>
                </stripes:layout-component>
	
	<stripes:messages/>
	<stripes:layout-component name="contents"/>   
	          
		

	<div class="footer">
		Created by <a href="http://www.limonn.com">Limonn</a>.
		Powered by <stripes:link href="http://mc4j.org/confluence/display/stripes/Home" style="color:gray;text-decoration:none;"> Stripes </stripes:link> 
	</div>

</body>
</html>
</stripes:layout-definition>       
        
        
        
        
        
        
  
          
            
           