package controller;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.novell.ldap.LDAPException;

import entidades.PessoaDns;
import modelo.PessoaDnsDAO;
import modelo.PessoaDnsDAOImpl;
import modelo.PessoaWifiDAO;
import modelo.PessoaWifiDAOImpl;



@ManagedBean(name="PessoaDnsBean")
public class PessoaDnsController implements Serializable{
	PessoaDns pessoaDns = new PessoaDns();
	PessoaDnsDAO pessoaDnsDAO = new PessoaDnsDAOImpl();
	ArrayList<PessoaDns> pessoas = new ArrayList<PessoaDns>();

	public String logar(){
		if (pessoaDnsDAO.login(pessoaDns) == true) {
			return "body/dns.xhtml?faces-redirect=true";
		}
		return "login.xhtml?faces-redirect=true";
	}

	public String AdicionarUsuario() throws UnsupportedEncodingException{
		PessoaDnsDAO pessoaDNSDAO = new PessoaDnsDAOImpl();
		pessoaDNSDAO.create(pessoaDns);
		return "dns.xhtml?faces-redirect=true";
}

	public String migrarUsuario() throws UnsupportedEncodingException, ParseException{
		PessoaDnsDAO pessoaDNSDAO = new  PessoaDnsDAOImpl();
		pessoaDNSDAO.migrate(pessoaDns);
		return "body/dns.xhtml?faces-redirect=true";
	}

	public ArrayList<PessoaDns> Listarusuario() throws UnsupportedEncodingException, ParseException{
		PessoaDnsDAO pessoaDNSDAO = new PessoaDnsDAOImpl();
		pessoas = pessoaDNSDAO.findAll();
		return pessoas;
		//return "body/ListarUsuarios.xhtml?faces-redirect=true";
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
