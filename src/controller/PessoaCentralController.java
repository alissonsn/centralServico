package controller;

import javax.faces.bean.ManagedBean;

import com.novell.ldap.LDAPException;

import entidades.PessoaCentral;
import modelo.PessoaCentralDAO;
import modelo.PessoaCentralDAOImpl;

@ManagedBean(name="PessoaCentralBean")
public class PessoaCentralController {
	PessoaCentral pessoaCentral = new PessoaCentral();
	PessoaCentralDAO pessoaCentralDAO = new PessoaCentralDAOImpl();

	public String logar(){
		if (pessoaCentralDAO.login(pessoaCentral) == true) {
			return "body/central.xhtml?faces-redirect=true";
		}
		return  "login.xhtml?faces-redirect=true";
	}
	
	public String isvalidate(){
		String pagina = "";
		if (pessoaCentralDAO.isValidate() == true) {
			pagina = "http://177.20.144.247:8080/centralServico/site/central/body/central.xhtml";
		}else{

			pagina = "http://177.20.144.247:8080/centralServico/site/central/login.xhtml";
		}
		return pagina;

	}
	
	

	public String logout(){
		pessoaCentralDAO.logout();
		return "http://177.20.144.247:8080/centralServico/index.xhtml?faces-redirect=true";
	}

	public PessoaCentral getPessoaCentral() {
		return pessoaCentral;
	}

	public void setPessoaCentral(PessoaCentral pessoaCentral) {
		this.pessoaCentral = pessoaCentral;
	}

	public PessoaCentralDAO getPessoaCentralDAO() {
		return pessoaCentralDAO;
	}

	public void setPessoaCentralDAO(PessoaCentralDAO pessoaCentralDAO) {
		this.pessoaCentralDAO = pessoaCentralDAO;
	}

}
