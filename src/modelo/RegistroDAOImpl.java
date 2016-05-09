package modelo;


import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPAttributeSet;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;
import com.novell.ldap.LDAPModification;
import com.novell.ldap.LDAPSearchResults;

import entidades.PessoaDns;
import entidades.PessoaWifi;
import entidades.Registro;


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
			conexao.connect("10.3.156.9",389);
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
		attributesRegistroReverso.add(new LDAPAttribute("pTRRecord", registro.getNomeMaquina()+"."+registro.getDominio()+"."));

		String dnAdmin = "uid="+ usuario+",ou=admin,ou=dns,dc=ufrn,dc=br";
		LDAPConnection conexao = new LDAPConnection();

		try {
			conexao.connect("10.3.156.9",389);
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
	public void delete(Registro registro) throws UnsupportedEncodingException, LDAPException {
		List<String> atributo = new ArrayList<String>();
		List<String> attr = new ArrayList<String>();
		atributo = this.listarRegistroReverso(registro);

		//Atributos para criação do do registro Reverso
	    //Divindo o ip por octais
	  	String[] octal = registro.getIp().toString().split("\\.");
	  	String primeiroOctal = octal[0];
	  	String segundoOctal = octal[1];
	  	String terceiroOctal = octal[2];
	  	String quartoOctal = octal[3];

		String baseDireta = "relativeDomainName="+registro.getNomeMaquina()+",zoneName="+registro.getDominio()+ ",ou=dns,dc=ufrn,dc=br";
		String baseReversa = "relativeDomainName="+quartoOctal+",zoneName="+terceiroOctal+"."+segundoOctal+"."+primeiroOctal+".in-addr.arpa,ou=dns,dc=ufrn,dc=br";
		String dnAdmin = "cn=admin,dc=ufrn,dc=br";
		String password = "gob0l1nux";



		LDAPConnection lc = new LDAPConnection();
		lc.connect("10.3.156.9", 389 );
		lc.bind( LDAPConnection.LDAP_V3, dnAdmin,  password.getBytes("UTF8"));

		System.out.println(registro.getNomeMaquina()+"."+registro.getDominio()+".");
		if (atributo.size() > 1) {
			LDAPAttribute attribute = new LDAPAttribute("pTRRecord", registro.getNomeMaquina()+"."+registro.getDominio()+".");
	        LDAPModification modificao = new LDAPModification(1, attribute);


	        lc.modify(baseReversa, modificao);
			lc.delete(baseDireta);

		}else{
		lc.delete(baseReversa);
		lc.delete(baseDireta);
		}

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

	@Override
	public List<String> listarRegistroReverso(Registro registro) throws UnsupportedEncodingException {
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = (HttpSession) req.getSession();
		String usuario = (String) session.getAttribute("usuarioDns");
		String senha = (String) session.getAttribute("senhaDns");
		ArrayList<String> atributo = new ArrayList<String>();
		int contador = 0;


		//String dnAdmin = "uid="+ usuario+",ou=admin,ou=dns,dc=ufrn,dc=br";
		String dnAdmin = "cn=admin,dc=ufrn,dc=br";
		String password = "gob0l1nux";
		String searchBase = "ou=dns,dc=ufrn,dc=br", searchFilter = "(pTRRecord="+ registro.getNomeMaquina()+"."+registro.getDominio()+"."+")";
		int searchScope = LDAPConnection.SCOPE_SUB;
		String[] atributos = {"pTRRecord"};

		LDAPConnection lc = new LDAPConnection();
		try {
			lc.connect("10.3.156.9", 389 );
			lc.bind( LDAPConnection.LDAP_V3, dnAdmin,  password.getBytes("UTF8"));
			LDAPSearchResults searchResults = lc.search(searchBase, searchScope, searchFilter, atributos, false);

			while (searchResults.hasMore() ) {
				LDAPEntry nextEntry = null;
				try {
					nextEntry = searchResults.next();
				} catch(LDAPException e) {
					System.out.println("Error: " + e);
					continue;
				}
				LDAPAttributeSet attributeSet = nextEntry.getAttributeSet();
				Iterator allAttributes = attributeSet.iterator();
				while ( allAttributes.hasNext() ) {
					LDAPAttribute attribute = (LDAPAttribute)allAttributes.next();
					String attributeName = attribute.getName();
					System.out.println("attributeName "+attributeName);
					Enumeration allValues = attribute.getStringValues();
					if ( allValues != null ) {
						while ( allValues.hasMoreElements() ) {
							atributo.add(allValues.nextElement().toString());

							/*
							for (int i = 0; i < atributo.size(); i++) {
								System.out.println(atributo.get(i));
							}
							*/
							//String value = (String)allValues.nextElement();
							//System.out.println("value "+value);
							//System.out.println(attributeName + ":  " + value);
						}
					}
				}
			}
		} catch( LDAPException e ) {
			System.out.println("Error " + e.toString() );
		}

		return atributo;
	}


}
