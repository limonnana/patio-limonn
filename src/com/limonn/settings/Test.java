package com.limonn.settings;

import java.util.Date;
import com.limonn.entities.*;
import com.limonn.services.Services;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		Date pedido = Utils.getTimeNow();
//		pedido.setDate(17);
//		
//		pedido.setHours(15);
//		pedido.setMinutes(20);
//		
//		int compararDia = Utils.compararDia(pedido);
//		//System.out.println(compararDia);
//		System.out.print(Utils.compararDiaTiempoDelivery(pedido, 30));
		
		

	}
	
	static Session getSesion()
	{
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		return session;
	}

}
