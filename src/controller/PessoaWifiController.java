package controller;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import com.novell.ldap.LDAPException;

import modelo.PessoaWifiDAO;
import modelo.PessoaWifiDAOImpl;
import entidades.PessoaWifi;


@ManagedBean(name="PessoaWifiBean")
public class PessoaWifiController implements Serializable{
	PessoaWifi pessoaWifi = new PessoaWifi();
	ArrayList<PessoaWifi> pessoasWifi = new ArrayList<PessoaWifi>();
	PessoaWifiDAO pessoaWifiDAO = new PessoaWifiDAOImpl();

	public String logar(){
		if (pessoaWifiDAO.login(pessoaWifi) == true) {
			return "body/wifi.xhtml?faces-redirect=true";
		}
		return "login.xhtml?faces-redirect=true";
	}

	public boolean dataHoje(PessoaWifi pessoaWifi){
		Date hoje = new Date();
		if (pessoaWifiDAO.verificavencimento(hoje, pessoaWifi)) {
			System.out.println(pessoaWifiDAO.verificavencimento(hoje, pessoaWifi));
			//System.out.println(pessoaWifi.getValidade());
			return true;
		}
		System.out.println(pessoaWifiDAO.verificavencimento(hoje, pessoaWifi));
		return false;
	}

	public String logout() throws LDAPException{
		pessoaWifiDAO.logout();
		return "http://snmp.info.ufrn.br:8080/centralServico/index.xhtml?faces-redirect=true";
	}

	public String AdicionarUsuario() throws UnsupportedEncodingException{
			PessoaWifiDAO pessoaWifiDAO = new PessoaWifiDAOImpl();
			pessoaWifiDAO.create(pessoaWifi);
			return "wifi.xhtml?faces-redirect=true";
	}

	public ArrayList<PessoaWifi> Listarusuario() throws UnsupportedEncodingException, ParseException{
		PessoaWifiDAO pessoaWifiDAO = new PessoaWifiDAOImpl();
		pessoasWifi = pessoaWifiDAO.findAll();
		return pessoasWifi;
		//return "body/ListarUsuarios.xhtml?faces-redirect=true";
	}

	public PessoaWifi getPessoaWifi() {
		return pessoaWifi;
	}

	public void setPessoaWifi(PessoaWifi pessoaWifi) throws CloneNotSupportedException {
		this.pessoaWifi = pessoaWifi;
		if (this.pessoaWifi == null) {
			this.pessoaWifi = new PessoaWifi();
		}else{
			this.pessoaWifi = (PessoaWifi) pessoaWifi.clone();
		}
	}

	public List<PessoaWifi> getPessoasWifi() {
		return pessoasWifi;
	}

	public void setPessoasWifi(ArrayList<PessoaWifi> pessoasWifi) {
		this.pessoasWifi = pessoasWifi;
	}

	public PessoaWifiDAO getPessoaWifiDAO() {
		return pessoaWifiDAO;
	}

	public void setPessoaWifiDAO(PessoaWifiDAO pessoaWifiDAO) {
		this.pessoaWifiDAO = pessoaWifiDAO;
	}

}
