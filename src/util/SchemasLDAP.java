package util;

import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPAttributeSet;

import entidades.Nslcd;
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
			valorTipo = registro.getApelido()+"."+registro.getDominio();
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
	
	public LDAPAttributeSet nslcDebian(Nslcd nslcd, String uidNumber, String flagAdmin){
		String gidNumber = "";
		if (flagAdmin == "SIM") {
			gidNumber = "27";
		}else{
			gidNumber = "500";
		}
		
		//Atributos para criação do do registro Direto
		String[] objectClassNslcdDebian = new String[4];
		objectClassNslcdDebian [0] = "top";
		objectClassNslcdDebian [1] = "account";
		objectClassNslcdDebian [2] = "posixAccount";
		objectClassNslcdDebian [1] = "shadowAccount";
		LDAPAttributeSet attributesNslcDebian = new LDAPAttributeSet();
		attributesNslcDebian.add(new LDAPAttribute("objectClass", objectClassNslcdDebian));
		attributesNslcDebian.add(new LDAPAttribute("cn", nslcd.getUid()));
		attributesNslcDebian.add(new LDAPAttribute("uid", nslcd.getUid()));
		attributesNslcDebian.add(new LDAPAttribute("uidNumber", uidNumber));
		attributesNslcDebian.add(new LDAPAttribute("gidNumber", gidNumber));
		attributesNslcDebian.add(new LDAPAttribute("homeDirectory", "/home/"+nslcd.getUid()));
		attributesNslcDebian.add(new LDAPAttribute("loginShell", "/bin/bash"));
		attributesNslcDebian.add(new LDAPAttribute("ou", nslcd.getServidor()));
		attributesNslcDebian.add(new LDAPAttribute("userPassword", nslcd.getSenha()));
		
		return attributesNslcDebian;
	}
	
	public LDAPAttributeSet nslcCentos(Nslcd nslcd, String uidNumber, String flagAdmin){
		String gidNumber = "";
		if (flagAdmin == "SIM") {
			gidNumber = "10";
		}else{
			gidNumber = "500";
		}
		
		//Atributos para criação do do registro Direto
		String[] objectClassNslcdCentos = new String[4];
		objectClassNslcdCentos [0] = "top";
		objectClassNslcdCentos [1] = "account";
		objectClassNslcdCentos [2] = "posixAccount";
		objectClassNslcdCentos [1] = "shadowAccount";
		LDAPAttributeSet attributesNslcCentos = new LDAPAttributeSet();
		attributesNslcCentos.add(new LDAPAttribute("objectClass", objectClassNslcdCentos));
		attributesNslcCentos.add(new LDAPAttribute("cn", nslcd.getUid()));
		attributesNslcCentos.add(new LDAPAttribute("uid", nslcd.getUid()));
		attributesNslcCentos.add(new LDAPAttribute("uidNumber", uidNumber));
		attributesNslcCentos.add(new LDAPAttribute("gidNumber", gidNumber));
		attributesNslcCentos.add(new LDAPAttribute("homeDirectory", "/home/"+nslcd.getUid()));
		attributesNslcCentos.add(new LDAPAttribute("loginShell", "/bin/bash"));
		attributesNslcCentos.add(new LDAPAttribute("ou", nslcd.getServidor()));
		attributesNslcCentos.add(new LDAPAttribute("userPassword", nslcd.getSenha()));
		
		return attributesNslcCentos;
	}
	
}
