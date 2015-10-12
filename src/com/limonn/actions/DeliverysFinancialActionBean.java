package com.limonn.actions;

import org.apache.commons.lang.StringUtils;

import com.limonn.entities.Activo;
import com.limonn.entities.EjercicioFinanciero;
import com.limonn.entities.Pasivo;
import com.limonn.entities.User;
import com.limonn.entityManager.FinancialManager;
import com.limonn.services.FinancialServices;
import com.limonn.settings.LimonnActionBean;
import com.limonn.settings.Utils;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;


@UrlBinding("/DeliverysFinancial.action")
public class DeliverysFinancialActionBean extends LimonnActionBean{
	
	
	private User user;
	private EjercicioFinanciero ejercicioFinanciero;
	private String mensaje;
	private String resultadoActivos, resultadoPasivos;
	private Double balance;
	private Activo activo;
	private Pasivo pasivo;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	public String getResultadoActivos() {
		return resultadoActivos;
	}

	public void setResultadoActivos(String resultadoActivos) {
		this.resultadoActivos = resultadoActivos;
	}

	public String getResultadoPasivos() {
		return resultadoPasivos;
	}

	public void setResultadoPasivos(String resultadoPasivos) {
		this.resultadoPasivos = resultadoPasivos;
	}

	public EjercicioFinanciero getEjercicioFinanciero() {
		return ejercicioFinanciero;
	}

	public void setEjercicioFinanciero(EjercicioFinanciero ejercicioFinanciero) {
		this.ejercicioFinanciero = ejercicioFinanciero;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
   public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

   public Activo getActivo() {
		return activo;
	}

	public void setActivo(Activo activo) {
		this.activo = activo;
	}

	public Pasivo getPasivo() {
		return pasivo;
	}

	public void setPasivo(Pasivo pasivo) {
		this.pasivo = pasivo;
	}

		@DefaultHandler
		public Resolution showFinancial()
		{
		    this.ejercicioFinanciero = FinancialServices.getEjercicioFinanciero(this.user);
		    if(ejercicioFinanciero != null)
		    {
		    this.balance = FinancialManager.calcularBalance(ejercicioFinanciero.getActivos(), ejercicioFinanciero.getPasivos());
		    this.resultadoActivos = FinancialManager.calcularResultadoActivos(ejercicioFinanciero.getActivos()).toString();
		    this.resultadoPasivos = FinancialManager.calcularResultadoPasivos(ejercicioFinanciero.getPasivos()).toString();
		    this.activo = null;
		    this.pasivo = null;
		    } 
		    return new ForwardResolution("/Deliverys.jsp");
		}
		
		public Resolution addActivo()
		{
			if(this.user == null)
			{
				 this.mensaje = "No eligio el delivery";
				 return new ForwardResolution("/Deliverys.jsp");
			}
			if(this.activo.getCantidad()== 0.0)
			{
				 this.mensaje = "No lleno la cantidad";
				 return new ForwardResolution("/Deliverys.jsp");
			}
			if(StringUtils.isBlank(this.activo.getConcepto()))
					{
				       this.activo.setConcepto("entrega efectivo ");
					}
			this.activo.setUser(this.user);
			this.activo.setDate(Utils.getTimeNow());
			FinancialServices.saveActivo(this.activo);
			
			 return showFinancial();
		}
        
		public Resolution addPasivo()
		{
			if(this.user == null)
			{
				this.mensaje = "No eligio el delivery";
				 return new ForwardResolution("/Deliverys.jsp");
			}
			if(this.pasivo.getCantidad()== 0.0)
			{
				 this.mensaje = "No lleno la cantidad";
				 return new ForwardResolution("/Deliverys.jsp");
			}
			if(StringUtils.isBlank(this.pasivo.getConcepto()))
			{
		       this.pasivo.setConcepto("recibe cambio");
			}
			this.pasivo.setUser(this.user);
			this.pasivo.setDate(Utils.getTimeNow());
			FinancialServices.savePasivo(this.pasivo);
			
			 return showFinancial();
		}
		
		public Resolution closeEjercicio()
		{
			FinancialServices.closeEjercicio(this.ejercicioFinanciero);
			return showFinancial();
		}

}
