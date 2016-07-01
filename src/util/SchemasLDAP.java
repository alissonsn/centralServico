package util;

import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPAttributeSet;

import entidades.Registro;

public class SchemasLDAP {

	public LDAPAttributeSet RegistroDireto(Registro registro){
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
		
		return attributesRegistroDireto;
	}
	
	public LDAPAttribute RegistroDiretoAdicionar(Registro registro){
		//Atributos para criação do do registro Direto
		String tipo = "";
		String valorTipo = "";
		if (registro.getTipo().equals("aRecord")) {
			tipo = "aRecord";
			valorTipo = registro.getIp();
		}else if (registro.getTipo().equals("cNAMERecord")) {
			tipo = "cNAMERecord";
			valorTipo = registro.getNomeMaquina()+"."+registro.getDominio();
		}else if (registro.getTipo().equals("tXTRecord")) {
			tipo = "tXTRecord";
			valorTipo = registro.getNomeMaquina()+"."+registro.getDominio();
		}else if (registro.getTipo().equals("mXRecord")) {
			tipo = "mXRecord";
			valorTipo = registro.getNomeMaquina()+"."+registro.getDominio();
		}
		
		LDAPAttribute attr= new LDAPAttribute(tipo, valorTipo );
		return attr;
	}
	
	public LDAPAttributeSet RegistroReverso(Registro registro, Object primeiroOctal, Object segundoOctal, Object terceiroOctal, Object quartoOctal){
		//Atributos para criação do do registro Direto
		String[] dominioZonaReversa = new String[2];
		dominioZonaReversa[0] = "top";
		dominioZonaReversa [1] = "dNSZone";
		LDAPAttributeSet attributesRegistroReverso = new LDAPAttributeSet();
		attributesRegistroReverso.add(new LDAPAttribute("objectClass", dominioZonaReversa));
		attributesRegistroReverso.add(new LDAPAttribute("relativeDomainName", quartoOctal.toString()));
		attributesRegistroReverso.add(new LDAPAttribute("zoneName", terceiroOctal.toString()+"."+segundoOctal.toString()+"."+
									primeiroOctal.toString()+".in-addr.arpa"));
		attributesRegistroReverso.add(new LDAPAttribute("dNSTTL", "3600"));
		attributesRegistroReverso.add(new LDAPAttribute("dNSClass", "IN"));
		attributesRegistroReverso.add(new LDAPAttribute("pTRRecord", registro.getNomeMaquina()+"."+registro.getDominio()+"."));
		
		return attributesRegistroReverso;
	}
	
	public LDAPAttribute RegistroReversoAdicionar(Registro registro, String tipo){
		//Atributos para criação do do registro Direto
		LDAPAttribute attr = new LDAPAttribute(tipo, registro.getNomeMaquina()+"."+registro.getDominio()+".");
		return attr;
	}
	
	
	
}
