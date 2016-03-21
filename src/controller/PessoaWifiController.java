package controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.novell.ldap.LDAPException;

import modelo.PessoaWifiDAO;
import modelo.PessoaWifiDAOImpl;
import entidades.PessoaWifi;

@SessionScoped
@ManagedBean(name="PessoaWifiBean")
public class PessoaWifiController implements Serializable{
	PessoaWifi pessoaWifi = new PessoaWifi();
	PessoaWifiDAO pessoaWifiDAO = new PessoaWifiDAOImpl();

	public String logar(){
		if (pessoaWifiDAO.login(pessoaWifi) == true) {
			return "body/wifi.xhtml?faces-redirect=true";
		}
		return "login.xhtml?faces-redirect=true";
	}

	public String isvalidate(){
		String pagina = "";
		if (pessoaWifiDAO.isValidate() == true) {
			pagina = "http://localhost:8080/centralServico/site/wifi/body/wifi.xhtml";
		}else{

			pagina = "http://localhost:8080/centralServico/site/wifi/login.xhtml";
		}
		return pagina;

	}


	public String logout() throws LDAPException{
		pessoaWifiDAO.logout();
		return "http://localhost:8080/centralServicoJSF/index.xhtml?faces-redirect=true";
	}

	public String AdicionarUsuario(){
			PessoaWifiDAO pessoaWifiDAO = new PessoaWifiDAOImpl();
			pessoaWifiDAO.create(pessoaWifi);
			return "wifi.xhtml?faces-redirect=true";
	}

	public PessoaWifi getPessoaWifi() {
		return pessoaWifi;
	}

	public void setPessoaWifi(PessoaWifi pessoaWifi) {
		this.pessoaWifi = pessoaWifi;
	}

	public PessoaWifiDAO getPessoaWifiDAO() {
		return pessoaWifiDAO;
	}

	public void setPessoaWifiDAO(PessoaWifiDAO pessoaWifiDAO) {
		this.pessoaWifiDAO = pessoaWifiDAO;
	}

}
