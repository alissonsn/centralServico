package controller;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.novell.ldap.LDAPException;

import modelo.RegistroDAO;
import modelo.RegistroDAOImpl;
import entidades.Registro;


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


	public String RemoverRegistro() throws UnsupportedEncodingException, LDAPException{
		RegistroDAO registroDAO = new RegistroDAOImpl();
		registroDAO.delete(registro);
		return "registro.xhtml?faces-redirect=true";
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