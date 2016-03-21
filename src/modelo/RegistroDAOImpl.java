package modelo;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPAttributeSet;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;

import entidades.Registro;
import entidades.Zona;

public class RegistroDAOImpl implements RegistroDAO{

	@Override
	public void createRegistroDireto(Registro registro) {
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = (HttpSession) req.getSession();
		String usuario = (String) session.getAttribute("usuarioDns");
		String senha  = (String) session.getAttribute("senhaDns");
		
		//Atributos para criação do do registro Direto
		String[] objectClassRegistroDireto = new String[2];
	    objectClassRegistroDireto [0] = "top";
	    objectClassRegistroDireto [1] = "dNSZone";
	    LDAPAttributeSet attributesRegistroDireto = new LDAPAttributeSet();
	    attributesRegistroDireto.add(new LDAPAttribute("objectClass", objectClassRegistroDireto));
	    attributesRegistroDireto.add(new LDAPAttribute("relativeDomainName", registro.getNomeMaquina()));
	    attributesRegistroDireto.add(new LDAPAttribute("zoneName", registro.getDominio()));
	    attributesRegistroDireto.add(new LDAPAttribute("dNSTTL", "604800"));
	    attributesRegistroDireto.add(new LDAPAttribute("dNSClass", "IN"));
	    attributesRegistroDireto.add(new LDAPAttribute("aRecord", registro.getIp()));
	    
	    
	    
		
	    
		String dnAdmin = "uid="+ usuario+",ou=admin,ou=dns,dc=ufrn,dc=br";
		LDAPConnection conexao = new LDAPConnection();
		
		try {
			conexao.connect("10.3.226.126",389);
		} catch (LDAPException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			conexao.bind(LDAPConnection.LDAP_V3, dnAdmin, senha.getBytes());
		} catch (LDAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String baseRegistroDireto = "relativeDomainName="+registro.getNomeMaquina()+",zoneName="+registro.getDominio()+",ou=dns,dc=ufrn,dc=br";
		
		LDAPEntry entryRegistroDireto= new LDAPEntry(baseRegistroDireto, attributesRegistroDireto);
		try {
			conexao.add(entryRegistroDireto);
		} catch (LDAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void createRegistroReverso(Registro registro) {
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = (HttpSession) req.getSession();
		String usuario = (String) session.getAttribute("usuarioDns");
		String senha  = (String) session.getAttribute("senhaDns");
		
		//Atributos para criação do do registro Reverso
	    //Divindo o ip por octais
	  	String[] octal = registro.getIp().toString().split("\\.");
	  	Object primeiroOctal = octal[0];
	  	Object segundoOctal = octal[1];
	  	Object terceiroOctal = octal[2];
	  	Object quartoOctal = octal[3];
		
		String[] dominioZonaDireta = new String[2];
		dominioZonaDireta [0] = "top";
		dominioZonaDireta [1] = "dNSZone";
		LDAPAttributeSet attributesRegistroReverso = new LDAPAttributeSet();
		attributesRegistroReverso.add(new LDAPAttribute("objectClass", dominioZonaDireta));
		attributesRegistroReverso.add(new LDAPAttribute("relativeDomainName", quartoOctal.toString()));
		attributesRegistroReverso.add(new LDAPAttribute("zoneName", terceiroOctal.toString()+"."+segundoOctal.toString()+"."+
									primeiroOctal.toString()+".in-addr.arpa"));
		attributesRegistroReverso.add(new LDAPAttribute("dNSTTL", "3600"));
		attributesRegistroReverso.add(new LDAPAttribute("dNSClass", "IN"));
		attributesRegistroReverso.add(new LDAPAttribute("pTRRecord", registro.getNomeMaquina()+"."+registro.getDominio()));
		
		String dnAdmin = "uid="+ usuario+",ou=admin,ou=dns,dc=ufrn,dc=br";
		LDAPConnection conexao = new LDAPConnection();
		
		try {
			conexao.connect("10.3.226.126",389);
		} catch (LDAPException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			conexao.bind(LDAPConnection.LDAP_V3, dnAdmin, senha.getBytes());
		} catch (LDAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String baseRegistroReverso = "relativeDomainName="+quartoOctal+ ",zoneName="+terceiroOctal+"."+segundoOctal+"."+primeiroOctal+".in-addr.arpa,ou=dns,dc=ufrn,dc=br";
		
		LDAPEntry entryRegistroReverso = new LDAPEntry(baseRegistroReverso, attributesRegistroReverso);

		try {
			conexao.add(entryRegistroReverso);
		} catch (LDAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	@Override
	public void update(Registro registro) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String ip) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Registro find(String dominio) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Registro> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
