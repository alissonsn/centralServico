package controller;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import com.novell.ldap.LDAPException;

import modelo.RegistroDAO;
import modelo.RegistroDAOImpl;
import entidades.Registro;


@SessionScoped
@ManagedBean(name="RegistroBean")
public class RegistroController implements Serializable{
	Registro registro = new Registro();
	RegistroDAO registroDAO = new RegistroDAOImpl();

	public String createRegistro() throws UnsupportedEncodingException, LDAPException{
		RegistroDAO registroDAO = new RegistroDAOImpl();
		if (registro.getTipo().equals("aRecord")) {
			registroDAO.createRegistroDireto(registro);
			registroDAO.createRegistroReverso(registro);	
		}else{
			registroDAO.createRegistroDireto(registro);
		}
		
			return "dns.xhtml?faces-redirect=true";
	}

	public String RemoverRegistro() throws UnsupportedEncodingException, LDAPException{
		RegistroDAO registroDAO = new RegistroDAOImpl();
		registroDAO.delete(registro);
		return "registro.xhtml?faces-redirect=true";
	}

	public String lerRegistro(ValueChangeEvent evento){
		String tipoRegistro = evento.getNewValue().toString();
		System.out.println("Codigo do Registro: "+ tipoRegistro);
		return tipoRegistro;

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