package controller;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;


import javax.faces.bean.ManagedBean;

import modelo.PessoaSSHDAO;
import modelo.PessoaSSHDAOImpl;

import com.novell.ldap.LDAPException;

import entidades.PessoaSSH;



@ManagedBean(name="PessoaSSHBean")
public class PessoaSSHController implements Serializable{
	PessoaSSH pessoaSSH = new PessoaSSH();
	ArrayList<PessoaSSH> pessoasSSH = new ArrayList<PessoaSSH>();
	PessoaSSHDAO pessoaSSHDAO = new PessoaSSHDAOImpl();

	public String logar(){
		String pagina = "";
		System.out.println("Resultado Login = "+ pessoaSSHDAO.login(pessoaSSH));
		if (pessoaSSHDAO.login(pessoaSSH) ) {
			pagina = "body/ssh.xhtml?faces-redirect=true";
		}else{
		pagina =  "login.xhtml?faces-redirect=true";
		}
		return pagina;
	}

	public String isvalidate(){
		String pagina = "";
		if (pessoaSSHDAO.isValidate() == true) {
			pagina = "http://snmp.info.ufrn.br:8080/centralServico/site/remoto/body/ssh.xhtml";
		}else{
			pagina = "http://snmp.info.ufrn.br:8080/centralServico/site/remoto/login.xhtml";
		}
		return pagina;

	}


	public String logout() throws LDAPException{
		pessoaSSHDAO.logout();
		return "http://snmp.info.ufrn.br:8080/centralServico/index.xhtml?faces-redirect=true";
	}
	
	public String criarUsuario() throws UnsupportedEncodingException, ParseException{
		PessoaSSHDAO pessoaSSHDAO = new PessoaSSHDAOImpl();
		pessoaSSHDAO.create(pessoaSSH);
		return "body/ssh.xhtml?faces-redirect=true";
	}

	public String migrarUsuario() throws UnsupportedEncodingException, ParseException{
		PessoaSSHDAO pessoaSSHDAO = new PessoaSSHDAOImpl();
		pessoaSSHDAO.migrate(pessoaSSH);
		return "body/ssh.xhtml?faces-redirect=true";
	}
	
	public ArrayList<PessoaSSH> Listarusuario() throws UnsupportedEncodingException, ParseException{
		PessoaSSHDAO pessoaSSHDAO = new PessoaSSHDAOImpl();
		pessoasSSH = pessoaSSHDAO.findAll();
		return pessoasSSH;
		//return "body/ListarUsuarios.xhtml?faces-redirect=true";
	}
	
	/*
	public String AdicionarUsuario(){
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
*/


	public PessoaSSH getPessoaSSH() {
		return pessoaSSH;
	}

	public void setPessoaSSH(PessoaSSH pessoaSSH) throws CloneNotSupportedException {
		this.pessoaSSH = pessoaSSH;
		if (this.pessoaSSH == null) {
			this.pessoaSSH = new PessoaSSH();
		}else{
			this.pessoaSSH = (PessoaSSH) pessoaSSH.clone();
		}
	}

	public ArrayList<PessoaSSH> getPessoasSSH() {
		return pessoasSSH;
	}

	public void setPessoasSSH(ArrayList<PessoaSSH> pessoasSSH) {
		this.pessoasSSH = pessoasSSH;
	}

	public PessoaSSHDAO getPessoaSSHDAO() {
		return pessoaSSHDAO;
	}

	public void setPessoaSSHDAO(PessoaSSHDAO pessoaSSHDAO) {
		this.pessoaSSHDAO = pessoaSSHDAO;
	}
}