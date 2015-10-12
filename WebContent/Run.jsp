<%@page import="com.limonn.entities.*"%>
<%@page import="com.limonn.services.Services"%>
<%@page import="org.hibernate.Session" %>
<%@page import="org.hibernate.SessionFactory"%>
<%@page import="org.hibernate.cfg.Configuration"%>
<%@page import="java.util.List"%>

<%



//Sueldo sueldo = new Sueldo(1000);

//Services.saveObject(sueldo);

//User u = new User();

//u.setId(4);
//u.setName("Raul");
//u.setUsername("limonn");
//u.setRole("Admin");
//u.setSueldo(sueldo);
//Services.updateUser(u);

List  usuarios = Services.dameUsuarios(1000);
//out.prin//t(u.getSueldo().getSalario());

out.print("");

		
		
		
		
		
		
		%>