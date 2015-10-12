package com.limonn.entityManager;

import java.util.List;
import com.limonn.entities.Activo;
import com.limonn.entities.Pasivo;


public class FinancialManager {
	
public static Double calcularBalance(List<Activo> activos, List<Pasivo> pasivos) {
		
		Double totalActivos = 0.0;
		Double totalPasivos = 0.0;
		Double balance = 0.0;
		
		for(Activo a : activos)
		{
			if(a.getStatus()!= -1)
			{
			totalActivos = totalActivos + a.getCantidad();
			}
		}
		for(Pasivo p : pasivos)
		{
			if(p.getStatus()!= -1)
			{
			totalPasivos = totalPasivos + p.getCantidad();
			}
		}
		
		balance = totalActivos - totalPasivos;
		return balance;
	}

	public static Double calcularResultadoActivos(List<Activo> activos)
	{
		Double totalActivos = 0.0;
		
		for(Activo a : activos)
		{
			if(a.getStatus()!= -1)
			{
			totalActivos = totalActivos + a.getCantidad();
			}
		}
		
		return totalActivos;
	}
	
	public static Double calcularResultadoPasivos(List<Pasivo> pasivos)
	{
       Double totalPasivos = 0.0;
		
		for(Pasivo p : pasivos)
		{
			if(p.getStatus() != -1)
			{
			totalPasivos = totalPasivos + p.getCantidad();
			}
		}
		
		return totalPasivos;
	}
	

}
