package com.limonn.entities;

import java.util.ArrayList;
import java.util.List;


public class Role {
	
	private  List<String> roleList = new ArrayList<String>();
	
	public List<String> getRoleList()
	{
		roleList.add("Cliente");
		roleList.add("Delivery");
		roleList.add("Empaquetador");
		roleList.add("Cocina");
		//roleList.add("Caja"); fue cambiado en la jsp para que sea manager caja
		roleList.add("Admin");
		
		return roleList;
	}

}
