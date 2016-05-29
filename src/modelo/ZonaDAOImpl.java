package modelo;


import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPAttributeSet;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;

import entidades.Zona;

public class ZonaDAOImpl implements ZonaDAO{

	/** Metodo que cria zona direta.
	 * @param zona, requer objeto zona  para sua criação.
	 */
	@Override
	public void createZonaDireta(Zona dns) {
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = (HttpSession) req.getSession();
		String usuario = (String) session.getAttribute("usuarioDns");
		String senha  = (String) session.getAttribute("senhaDns");

		//Atributos para criação do zoneName
		String[] objectClassZonaDireta = new String[2];
	    objectClassZonaDireta [0] = "top";
	    objectClassZonaDireta [1] = "dNSZone";
	    
	    LDAPAttributeSet attributesZona = new LDAPAttributeSet();
	    attributesZona.add(new LDAPAttribute("objectClass", objectClassZonaDireta));
	    attributesZona.add(new LDAPAttribute("relativeDomainName", "@"));
	    attributesZona.add(new LDAPAttribute("zoneName", dns.getDominio()));

	    
	    
	    //Atributos para criação do galho relativeDomainName com Soa
		LDAPAttributeSet attributes = new LDAPAttributeSet();
		String dnAdmin = "uid="+ usuario+",ou=admin,ou=dns,dc=ufrn,dc=br";
		String[] dominioZonaDireta = new String[2];
		dominioZonaDireta [0] = "top";
		dominioZonaDireta [1] = "dNSZone";
	    attributes.add(new LDAPAttribute("objectClass", dominioZonaDireta));
	    attributes.add(new LDAPAttribute("relativeDomainName", "@"));
	    attributes.add(new LDAPAttribute("zoneName", dns.getDominio()));
	    attributes.add(new LDAPAttribute("dNSTTL", "86400"));
	    attributes.add(new LDAPAttribute("dNSClass", "IN"));
	    attributes.add(new LDAPAttribute("nSRecord", dns.getNomeMaquina()+"."));
	    attributes.add(new LDAPAttribute("SOARecord", dns.getNomeMaquina()+"."+dns.getDominio()+
	    								" root."+ dns.getDominio()+". 2015030201 04800 241 9200 604800"  ));

	    LDAPConnection conn = new LDAPConnection();
		try {
			conn.connect("10.3.156.9",389);
		} catch (LDAPException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			conn.bind(LDAPConnection.LDAP_V3, dnAdmin, senha.getBytes());
		} catch (LDAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String baseZonaDireta = "zoneName="+dns.getDominio()+",ou=dns,dc=ufrn,dc=br";
		String baseDominioZonaDireta = "relativeDomainName=@,zoneName="+dns.getDominio()+",ou=dns,dc=ufrn,dc=br";
		LDAPEntry entryZone = new LDAPEntry(baseZonaDireta, attributesZona);
		LDAPEntry entryDominioZone = new LDAPEntry(baseDominioZonaDireta, attributes);

		try {
			conn.add(entryZone);
			conn.add(entryDominioZone);
		} catch (LDAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/** Metodo que cria zona reversa.
	 * @param zona, requer objeto zona  para sua criação.
	 */
	@Override
	public void createZonaReversa(Zona dns) {
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = (HttpSession) req.getSession();
		String usuario = (String) session.getAttribute("usuarioDns");
		String senha  = (String) session.getAttribute("senhaDns");


		//Atributos para criação do zoneName

		//Divindo o ip por octais
		String[] octal = dns.getIp().toString().split("\\.");
		
		Object primeiroOctal = octal[0];
		Object segundoOctal = octal[1];
		Object terceiroOctal = octal[2];
		System.out.println("IP Octal:" + octal);
		System.out.println("Valor do Terceiro Octal: " + terceiroOctal);
		System.out.println("Valor do Segundo Octal: " + segundoOctal);
		System.out.println("Valor do Primeiro Octal: " + primeiroOctal);
		
		//Atributos
		String[] objectClassZonaReversa = new String[2];
	    objectClassZonaReversa [0] = "top";
	    objectClassZonaReversa [1] = "dNSZone";
	    LDAPAttributeSet attributesZona = new LDAPAttributeSet();
	    attributesZona.add(new LDAPAttribute("objectClass", objectClassZonaReversa));
	    attributesZona.add(new LDAPAttribute("relativeDomainName", "@"));
	    attributesZona.add(new LDAPAttribute("zoneName", terceiroOctal.toString()+"."+segundoOctal.toString()+"."+primeiroOctal.toString()+".in-addr.arpa"));


	    //Atributos para criação do galho relativeDomainName com Soa
		LDAPAttributeSet attributesZonaReversa = new LDAPAttributeSet();
		String dnAdmin = "uid="+ usuario+",ou=admin,ou=dns,dc=ufrn,dc=br";
		String[] dominioZonaReversa = new String[2];
		dominioZonaReversa [0] = "top";
		dominioZonaReversa [1] = "dNSZone";
	    attributesZonaReversa.add(new LDAPAttribute("objectClass", dominioZonaReversa));
	    attributesZonaReversa.add(new LDAPAttribute("relativeDomainName", "@"));
	    attributesZonaReversa.add(new LDAPAttribute("zoneName",  terceiroOctal+"."+segundoOctal+"."+primeiroOctal+".in-addr.arpa"));
	    attributesZonaReversa.add(new LDAPAttribute("dNSTTL", "3600"));
	    attributesZonaReversa.add(new LDAPAttribute("dNSClass", "IN"));
	    attributesZonaReversa.add(new LDAPAttribute("nSRecord", dns.getNomeMaquina()+"."));
	    attributesZonaReversa.add(new LDAPAttribute("SOARecord", dns.getNomeMaquina()+"."+dns.getDominio()+
	    								" root."+ dns.getDominio()+". 2015030201 04800 241 9200 604800"  ));


	    LDAPConnection conn = new LDAPConnection();
		try {
			conn.connect("10.3.156.9",389);
		} catch (LDAPException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			conn.bind(LDAPConnection.LDAP_V3, dnAdmin, senha.getBytes());
		} catch (LDAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String baseZonaReversa = "zoneName="+terceiroOctal+"."+segundoOctal+"."+primeiroOctal+".in-addr.arpa,ou=dns,dc=ufrn,dc=br";
		String baseDominioZonaReversa = "relativeDomainName=@,zoneName="+terceiroOctal+"."+segundoOctal+"."+primeiroOctal+".in-addr.arpa,ou=dns,dc=ufrn,dc=br";
		LDAPEntry entryZoneReversa = new LDAPEntry(baseZonaReversa, attributesZona);
		LDAPEntry entryDominioZoneReversa = new LDAPEntry(baseDominioZonaReversa, attributesZonaReversa);

		try {
			conn.add(entryZoneReversa);
			conn.add(entryDominioZoneReversa);
		} catch (LDAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
