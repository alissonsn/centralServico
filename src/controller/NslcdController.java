package controller;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ValueChangeEvent;

import com.novell.ldap.LDAPException;

import entidades.Nslcd;
import modelo.NslcdDAO;
import modelo.NslcdDAOImpl;


@ManagedBean(name="NslcdBean")
public class NslcdController implements Serializable{
	Nslcd nslcd = new Nslcd();
	String sistemaOperacional = "";
	String isFlagAdmin = "";
	ArrayList<Nslcd> listaNslcd = new ArrayList<Nslcd>();
	NslcdDAO nslcdDAO = new NslcdDAOImpl();

	public String logar(){
		String pagina = "";
		if (nslcdDAO.login(nslcd) ) {
			pagina = "body/nslcd.xhtml?faces-redirect=true";
		}else{
			pagina =  "login.xhtml?faces-redirect=true";
		}
		return pagina;
	}

	public String logout() throws LDAPException{
		nslcdDAO.logout();
		return "http://snmp.info.ufrn.br:8080/centralServico/index.xhtml?faces-redirect=true";
	}

	public String adicionarUsuario() throws UnsupportedEncodingException, ParseException, LDAPException{
		NslcdDAO nslcdDAO = new NslcdDAOImpl();
		nslcdDAO.add(nslcd, sistemaOperacional, isFlagAdmin);
		return "body/nslcd.xhtml?faces-redirect=true";
	}
	
	public String migrarUsuario() throws UnsupportedEncodingException, ParseException, LDAPException{
		NslcdDAO nslcdDAO = new NslcdDAOImpl();
		nslcdDAO.migrate(nslcd, sistemaOperacional, isFlagAdmin);
		return "body/nslcd.xhtml?faces-redirect=true";
	}

	public String deletaUsuario() throws UnsupportedEncodingException, ParseException, LDAPException{
		NslcdDAO nslcdDAO = new NslcdDAOImpl();
		nslcdDAO.delete(sistemaOperacional, nslcd);
		return "body/nslcd.xhtml?faces-redirect=true";
	}
	
	
	public List<Nslcd> listarusuarios(){
		NslcdDAO InslcdDAO = new NslcdDAOImpl();
		if (this.getSistemaOperacional() == null) {
			listaNslcd = null;
		}else{
			listaNslcd = InslcdDAO.findAll(this.getSistemaOperacional(), nslcd);
		}
		
		
		return listaNslcd;
	}
	
	public String lerSistemaOperacional(ValueChangeEvent evento){
		String codigoSistemaOperacional = evento.getNewValue().toString();
		System.out.println("Codigo do Sistema Operacional :" + codigoSistemaOperacional);
		if (codigoSistemaOperacional == null) {
			codigoSistemaOperacional = "*";
			this.setSistemaOperacional(codigoSistemaOperacional);
		}else{
			this.setSistemaOperacional(codigoSistemaOperacional);
		}
		
		return codigoSistemaOperacional;
	}
	
	public String lerServidor(ValueChangeEvent evento){
		String codigoServidor = evento.getNewValue().toString();
		System.out.println("Codigo do servidor :" + codigoServidor);
		
		if (codigoServidor == null) {
			this.nslcd.setServidor("*");
			codigoServidor = "*";
		}else{
			this.nslcd.setServidor(codigoServidor);
		}
		return codigoServidor;
		//return codigoSistemaOperacional;
	}
	
	public String lerUsuario(ValueChangeEvent evento){
		String codigoUsuario = evento.getNewValue().toString();
		System.out.println("Codigo do usuario :" + codigoUsuario);
		if (codigoUsuario == null) {
			this.nslcd.setUid("*");
			codigoUsuario = "*";
		}else{
			this.nslcd.setUid(codigoUsuario);
		}
		return codigoUsuario;
	}
	
	
	public void listarusuario(){
		this.nslcd.setServidor(this.nslcd.getServidor());
		this.setSistemaOperacional(sistemaOperacional);
		this.nslcd.setUid(this.nslcd.getUid());
	}
	
	
	public Nslcd getNslcd() {
		return nslcd;
	}

	public void setNslcd(Nslcd nslcd) {
		this.nslcd = nslcd;
		if (this.nslcd == null) {
			this.nslcd = new Nslcd();
		}else{
			try {
				this.nslcd = (Nslcd) nslcd.clone();
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public String getSistemaOperacional() {
		return sistemaOperacional;
	}

	public void setSistemaOperacional(String sistemaOperacional) {
		this.sistemaOperacional = sistemaOperacional;
	}

	public String getIsFlagAdmin() {
		return isFlagAdmin;
	}

	public void setIsFlagAdmin(String isFlagAdmin) {
		this.isFlagAdmin = isFlagAdmin;
	}

	public ArrayList<Nslcd> getListaNslcd() {
		return listaNslcd;
	}

	public void setListaNslcd(ArrayList<Nslcd> listaNslcd) {
		this.listaNslcd = listaNslcd;
	}

	public NslcdDAO getNslcdDAO() {
		return nslcdDAO;
	}

	public void setNslcdDAO(NslcdDAO nslcdDAO) {
		this.nslcdDAO = nslcdDAO;
	}
}