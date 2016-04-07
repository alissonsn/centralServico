package controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;


import modelo.PessoaDnsDAO;
import modelo.PessoaDnsDAOImpl;

import com.novell.ldap.LDAPException;

import entidades.PessoaDns;



@ManagedBean(name="PessoaDnsBean")
public class PessoaDnsController implements Serializable{
	PessoaDns pessoaDns = new PessoaDns();
	PessoaDnsDAO pessoaDnsDAO = new PessoaDnsDAOImpl();

	public String logar(){
		if (pessoaDnsDAO.login(pessoaDns) == true) {
			return "body/dns.xhtml?faces-redirect=true";
		}
		return "login.xhtml?faces-redirect=true";
	}

	public String isvalidate(){
		String pagina = "";
		if (pessoaDnsDAO.isValidate() == true) {
			pagina = "http://snmp.info.ufrn.br:8080/centralServico/site/dns/body/dns.xhtml";
		}else{

			pagina = "http://snmp.info.ufrn.br:8080/centralServico/site/dns/login.xhtml";
		}
		return pagina;
	}

	public String logout() throws LDAPException{
		pessoaDnsDAO.logout();
		return "http://snmp.info.ufrn.br:8080/centralServico/index.xhtml?faces-redirect=true";
	}

	public PessoaDns getPessoaDns() {
		return pessoaDns;
	}

	public void setPessoaDns(PessoaDns pessoaDns) {
		this.pessoaDns = pessoaDns;
	}

	public PessoaDnsDAO getPessoaDnsDAO() {
		return pessoaDnsDAO;
	}

	public void setPessoaDnsDAO(PessoaDnsDAO pessoaDnsDAO) {
		this.pessoaDnsDAO = pessoaDnsDAO;
	}
}
