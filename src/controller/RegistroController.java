package controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import modelo.PessoaWifiDAO;
import modelo.PessoaWifiDAOImpl;
import modelo.RegistroDAO;
import modelo.RegistroDAOImpl;
import modelo.ZonaDAO;
import modelo.ZonaDAOImpl;

import com.novell.ldap.LDAPException;

import entidades.PessoaWifi;
import entidades.Registro;
import entidades.Zona;

@SessionScoped
@ManagedBean(name="RegistroBean")
public class RegistroController implements Serializable{
	Registro registro = new Registro();
	RegistroDAO registroDAO = new RegistroDAOImpl();

	public String createRegistro(){
		RegistroDAO registroDAO = new RegistroDAOImpl();
		registroDAO.createRegistroDireto(registro);
		registroDAO.createRegistroReverso(registro);
			return "dns.xhtml?faces-redirect=true";
	}

	public Registro getRegistro() {
		return registro;
	}

	public void setRegistro(Registro registro) {
		this.registro = registro;
	}

	public RegistroDAO getRegistroDAO() {
		return registroDAO;
	}

	public void setRegistroDAO(RegistroDAO registroDAO) {
		this.registroDAO = registroDAO;
	}

	
}