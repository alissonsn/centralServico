package controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import modelo.ZonaDAO;
import modelo.ZonaDAOImpl;

import entidades.Zona;

@SessionScoped
@ManagedBean(name="ZonaBean")
public class ZonaController implements Serializable{
	Zona zona = new Zona();
	ZonaDAO zonaDAO = new ZonaDAOImpl();

	public String createZonaDireta(){
		ZonaDAO zonaDAO = new ZonaDAOImpl();
		zonaDAO.createZonaDireta(zona);
			return "dns.xhtml?faces-redirect=true";
	}

	public String createZonaReversa(){
		ZonaDAO zonaDAO = new ZonaDAOImpl();
		zonaDAO.createZonaReversa(zona);
			return "dns.xhtml?faces-redirect=true";
	}

	public Zona getZona() {
		return zona;
	}

	public void setZona(Zona zona) {
		this.zona = zona;
	}

	public ZonaDAO getZonaDAO() {
		return zonaDAO;
	}

	public void setZonaDAO(ZonaDAO zonaDAO) {
		this.zonaDAO = zonaDAO;
	}
}