package controller;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;

import com.novell.ldap.LDAPException;

import entidades.Nslcd;
import entidades.PessoaSSH;
import modelo.NslcdDAO;
import modelo.NslcdDAOImpl;
import modelo.PessoaSSHDAO;
import modelo.PessoaSSHDAOImpl;



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

	public String migrarUsuario() throws UnsupportedEncodingException, ParseException{
		NslcdDAO nslcdDAO = new NslcdDAOImpl();
		nslcdDAO.migrate(nslcd, sistemaOperacional, isFlagAdmin);
		return "body/nslcd.xhtml?faces-redirect=true";
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