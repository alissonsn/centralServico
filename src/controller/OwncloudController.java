package controller;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;

import modelo.IOwncloud;
import modelo.OwncloudDAOImpl;
import com.novell.ldap.LDAPException;
import entidades.Owncloud;



@ManagedBean(name="OwncloudBean")
public class OwncloudController implements Serializable{
	Owncloud owncloud = new Owncloud();
	ArrayList<Owncloud> listaOwncloud = new ArrayList<Owncloud>();
	IOwncloud iOwncloud =  new OwncloudDAOImpl();

	public String logar(){
		String pagina = "";
		if (iOwncloud.login(owncloud) ) {
			pagina = "body/owncloud.xhtml?faces-redirect=true";
		}else{
		pagina =  "login.xhtml?faces-redirect=true";
		}
		return pagina;
	}

	public String logout() throws LDAPException{
		iOwncloud.logout();
		return "http://snmp.info.ufrn.br:8080/centralServico/index.xhtml?faces-redirect=true";
	}
	
	public String criarUsuario() throws UnsupportedEncodingException, ParseException{
		IOwncloud iOwncloud =  new OwncloudDAOImpl();
		iOwncloud.create(owncloud);
		return "body/owncloud.xhtml?faces-redirect=true";
	}

	public String migrarUsuario() throws UnsupportedEncodingException, ParseException{
		IOwncloud iOwncloud =  new OwncloudDAOImpl();
		iOwncloud.migrate(owncloud);
		return "body/owncloud.xhtml?faces-redirect=true";
	}
	
	public ArrayList<Owncloud> Listarusuario() throws UnsupportedEncodingException, ParseException{
		IOwncloud iOwncloud =  new OwncloudDAOImpl();
		listaOwncloud = iOwncloud.findAll();
		return listaOwncloud;
	}

	
	public Owncloud getOwncloud() {
		return owncloud;
	}

	public void setOwncloud(Owncloud owncloud) throws CloneNotSupportedException {
		this.owncloud = owncloud;
		if (this.owncloud == null) {
			this.owncloud = new Owncloud();
		}else{
			this.owncloud = (Owncloud) owncloud.clone();
		}
	}

	public ArrayList<Owncloud> getListaOwncloud() {
		return listaOwncloud;
	}

	public void setListaOwncloud(ArrayList<Owncloud> listaOwncloud) {
		this.listaOwncloud = listaOwncloud;
	}

	public IOwncloud getiOwncloud() {
		return iOwncloud;
	}

	public void setiOwncloud(IOwncloud iOwncloud) {
		this.iOwncloud = iOwncloud;
	}
}