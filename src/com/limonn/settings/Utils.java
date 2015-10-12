package com.limonn.settings;


import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;


import com.limonn.entities.Cart;
import com.limonn.entities.Plato;
import com.limonn.entities.Settings;
import com.limonn.services.Services;




public class Utils {
	
	private static ActionBeanContext context;
    
	public static ActionBeanContext getContext()
	{
		return context;
	}

	
	
   
	public static String toUpperFirstCaracter(String palabra)
	{
		    Character u = palabra.charAt(0);
	    	String toUpper = u.toString().toUpperCase();
	    	String  subUsername = palabra.substring(1);
	    	String  nameUpper = toUpper+subUsername;
	        return nameUpper;
	}
	
	
	public static List<Plato> removeDeleted(List<Plato> platoList)
	{
		 List<Plato> listaDePlatos = new ArrayList<Plato>();
		 for(Plato p : platoList)
		 {
			 if(p.getStatus()!= -1)
			 {
				 listaDePlatos.add(p);
			 }
		 }
		 return listaDePlatos;
	}
	
	public static String deliveryHour()
	{
		Integer minutosToAdd = Services.getSettings().getMinutesToAddCurrentTime();
		Integer minutosTardaDelivery = Services.getSettings().getMinutosTardaDelivery();
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.MINUTE, minutosToAdd + minutosTardaDelivery);
		
		Date deliveryHour = calendar.getTime();
		
		Format formatterMinutos = new SimpleDateFormat("m");

		String resultMinutos = formatterMinutos.format(deliveryHour);
		if(resultMinutos.length()<2)
		{
			resultMinutos = "0"+ resultMinutos;
		}
		
		Format formatterHora = new SimpleDateFormat("h");
		
		String resultHora = formatterHora.format(deliveryHour);

		return resultHora + " : " + resultMinutos;
		//return deliveryHour;
	}
	
	public static Date getTimeNow()
	{
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.MINUTE, 180);
		Date deliveryHour = calendar.getTime();
		return deliveryHour;
	}
	
	public static String getDeliveryTimeFromSession(Cart cartInSession)
	{
		
		 String hora = null;
		 String minutos = null;
		 
		 if(cartInSession != null)
		 {
				  Date date = cartInSession.getDate();
				  hora = String.valueOf(date.getHours());
				  minutos = String.valueOf(date.getMinutes());
				  return hora +" : " + minutos;
		 }
		 return null;
	}
	
	public static String getDeliveryTimeFromCart(Cart cartInSession)
	{
		
		 String hora = null;
		 String minutos = null;
		 
		 if(cartInSession != null)
		 {
				  Date date = cartInSession.getDate();
				  hora = String.valueOf(date.getHours());
				  minutos = String.valueOf(date.getMinutes());
				  if(minutos.equals("0"))
				  {
					  minutos = "00";
				  }
				  return hora +" : " + minutos;
		 }
		 return null;
	}
	
	public static String getStringTimeNow(Date now)
	{
	
		DateFormat dateFormat = new SimpleDateFormat ("dd/MM    -   hh:mm");

		String datetime = dateFormat.format(now); 
		
		return datetime;
	}
	
	public static Integer getMinutesCorrectHour()
	{
		Settings settings = Services.getSettings();
		return settings.getMinutesToAddCurrentTime();
	}
	
	public static int compararDia(Date dia)
	{
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.MINUTE, 180);
		int fechaDeHoy = calendar.get(Calendar.DAY_OF_MONTH );
		
		String diaAComparar = dia.toString();
		
		
		String diaACompararSub = diaAComparar.substring(8, 10);
		System.out.println(diaACompararSub);
		int diaACompararInt = Integer.parseInt(diaACompararSub);
		int resultado = diaACompararInt - fechaDeHoy;
		
		return resultado ;
	}
	
	public static boolean compararDiaTiempoDelivery(Date date, int minutosPlatoReady)
	{
		Date today =  Utils.getTimeNow();
		if(date.getYear()== today.getYear()&& date.getMonth()==today.getMonth()&& date.getDay()== today.getDay()){
			System.out.print("son iguales en año y mes y dia");
			int diferenciaMinutos = today.getMinutes()- date.getMinutes() ;
			int diferenciaHoras = date.getHours()- today.getHours();
			System.out.print(today.getHours()+ " : " + today.getMinutes());
			System.out.print("DiferenciaHoras: " + diferenciaHoras + " diferenciaMinutos " + diferenciaMinutos);
			System.out.print("el comparador: " + today.compareTo(date)+ " \n");
			if(today.compareTo(date)>0)
			{
				return true;
			}
			
			
			if(diferenciaHoras == 0 && diferenciaMinutos < minutosPlatoReady)
			{
			   return true;
			}
			if(diferenciaHoras == 1 && diferenciaMinutos >  minutosPlatoReady)
			{
				return true;
			}
			
			
		}
		return false;
	}
	
	public static boolean mail(String message, String subject, String emailToRecipient)
	{
		

		String host = "limonn.com";
    	 try
		{
			
		Properties p = new Properties();
        //Properties p = System.getProperties();
        
		p.put("mail.transport.protocol", "smtp");  
        p.put ( "mail.smtp.host", host);
		

		Session ms = Session.getDefaultInstance(p, null);
		

		System.out.println("pass #1");
	    MimeMessage m = new MimeMessage(ms);
	    InternetAddress fromAddr = new InternetAddress("info@limonn.com");
		fromAddr.setPersonal("Limonn Patio Comidas");
		InternetAddress toAddr = new InternetAddress(emailToRecipient);
	//	toAddr.setPersonal());
		m.setFrom(fromAddr);
		m.addRecipient(Message.RecipientType.TO, toAddr);
		m.addHeader("User-Agent", "Limonn/0.0");
	    m.setText(message);
		m.setSubject(subject);
		m.addRecipient(Message.RecipientType.TO, toAddr);
		m.addHeader("User-Agent", "Limonn/0.0");
		
		try
		{
		Transport transport = ms.getTransport();
		System.out.println("pass #2");
		transport.connect();

		System.out.println("Conexion successful");

		Transport.send(m);
		System.out.println("pass #3");
		transport.close();
		}
		catch (MessagingException nspe)
		{
		System.err.println("ERROR..No SMTP mail provider! \n"+nspe);
		}
		
		
	
		
	} catch (Exception e)
	{
		System.out.println(e.getMessage() );
		System.out.println(" failed , reason is: " + e);
		return false;
	}
	return true;
	}
	
	


        
}
