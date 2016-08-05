package util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import jcifs.util.Hexdump;
import jcifs.util.MD4;
import sun.misc.BASE64Encoder;

import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPAttributeSet;

import entidades.Nslcd;
import entidades.PessoaWifi;
import entidades.Registro;

public class SchemasLDAP {
	
	
	@SuppressWarnings("unused")
	public String encryptLdapPassword(String algorithm, String _password) {
	        String sEncrypted = _password;
	        if ((_password != null) && (_password.length() > 0)) {
	            boolean bMD5 = algorithm.equalsIgnoreCase("MD5");
	            boolean bSHA = algorithm.equalsIgnoreCase("SHA")
	                    || algorithm.equalsIgnoreCase("SHA1")
	                    || algorithm.equalsIgnoreCase("SHA-1");
	            if (bSHA || bMD5) {
	                String sAlgorithm = "MD5";
	                if (bSHA) {
	                    sAlgorithm = "SHA";
	                }
	                try {
	                    MessageDigest md = MessageDigest.getInstance(sAlgorithm);
	                    md.update(_password.getBytes("UTF-8"));
	                    sEncrypted = "{" + sAlgorithm + "}" + (new BASE64Encoder()).encode(md.digest());
	                } catch (Exception e) {
	                    sEncrypted = null;
	                }
	            }
	        }
	        return sEncrypted;
	    }
	
	
	public LDAPAttributeSet AdicionarPessoaWifi(PessoaWifi pessoaWifi){
		LDAPAttributeSet attributes = new LDAPAttributeSet();

		
		String[] objectClass = new String[8];
	    objectClass[0] = "top";
	    objectClass[1] = "inetOrgPerson";
	    objectClass[2] = "organizationalPerson";
	    objectClass[3] = "person";
	    objectClass[4] = "radiusprofile";
	    objectClass[5] = "sambaSamAccount";
	    objectClass[6] = "schacPersonalCharacteristics";
	    objectClass[7] = "brPerson";
	    //objectClass[8] = "pwdPolicy";


	    attributes.add(new LDAPAttribute("objectClass", objectClass));

	    SimpleDateFormat formatoData = new SimpleDateFormat("ddMMyyyy");
	    String dataFormatada = formatoData.format(pessoaWifi.getNascimento());

	    
	    attributes.add(new LDAPAttribute("cn", pessoaWifi.getUid()));
	    attributes.add(new LDAPAttribute("dialupAccess", "access_attr"));
	    attributes.add(new LDAPAttribute("sambaSID", "S-1-5-21-4190300969-615862220-67323155-1000"));
	    attributes.add(new LDAPAttribute("sn", pessoaWifi.getUid()));
	    attributes.add(new LDAPAttribute("uid", pessoaWifi.getUid()));
	    attributes.add(new LDAPAttribute("mail", pessoaWifi.getEmail()));
	    attributes.add(new LDAPAttribute("brPersonCPF", pessoaWifi.getCPF()));
	    attributes.add(new LDAPAttribute("schacDateofBirth", dataFormatada));
	    attributes.add(new LDAPAttribute("userPassword", this.encryptLdapPassword("SHA", pessoaWifi.getSenha())));
	    try {
			attributes.add(new LDAPAttribute("sambaNTPassword",  this.SambaNTPassword(pessoaWifi.getSenha())));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    //attributes.add(new LDAPAttribute("pwdAttribute", "userPassword"));
	    //attributes.add(new LDAPAttribute("pwdPolicySubentry", pessoaWifi.getValidade()));
		return attributes;

		
	}
	
	
	/** Metodo que gera senha sambaNTPassword apartir de uma senha em texto plano.
	 *  @param password, senha em texto plano.
	 * @return String, retorn senha criptografada.
	 */
	private String SambaNTPassword(String password) throws UnsupportedEncodingException {
        MD4 md4 = new MD4();
        byte[] bpass = password.getBytes("UnicodeLittleUnmarked");
        md4.engineUpdate(bpass, 0, bpass.length);
        byte[] hashbytes = md4.engineDigest();
        String ntHash = Hexdump.toHexString(hashbytes, 0, hashbytes.length * 2);
        return ntHash;
    }
	
	
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
	
	public LDAPAttribute adicionarAtributo(Nslcd nslcd){
		LDAPAttribute attr = new LDAPAttribute("ou", nslcd.getServidor());
		return attr;
	}
	
	public LDAPAttribute adicionarSerial(String serial){
		LDAPAttribute attr = new LDAPAttribute("sOARecord", serial);
		return attr;
	}
	
	public ArrayList<String> Nslcd(String sistemaOperacional, Nslcd nslcd){
		ArrayList<String> listaAtributos = new ArrayList<String>();
		String searchBase = "";
		String searchFilter = "";
		
		if (nslcd.getServidor() == null && nslcd.getUid() == null) {
			searchBase = "ou="+sistemaOperacional+",ou=nslcd,dc=ufrn,dc=br";
			searchFilter =  "(&(ou=*)(uid=*))";
			System.out.println("Todos nulos");
		}else if (nslcd.getServidor() != null && nslcd.getUid() == null) {
			searchBase = "ou="+sistemaOperacional+",ou=nslcd,dc=ufrn,dc=br";
			searchFilter =  "(&(ou="+nslcd.getServidor()+")(uid=*))";
			System.out.println("Uid nulo");
		}else if (nslcd.getServidor() == null && nslcd.getUid() != null) {
			searchBase = "ou="+sistemaOperacional+",ou=nslcd,dc=ufrn,dc=br";
			searchFilter = "(&(ou=*)(uid="+nslcd.getUid()+"))";
			System.out.println("Servidor nulo");
		}else if (nslcd.getServidor() != null && nslcd.getUid() != null){
			searchBase = "ou="+sistemaOperacional+",ou=nslcd,dc=ufrn,dc=br";
			searchFilter = "(&(ou="+nslcd.getServidor()+")(uid="+nslcd.getUid()+"))";
			System.out.println("Nenhum nulo");
			
		}
		listaAtributos.add(searchBase);
		listaAtributos.add(searchFilter);
		
		
		System.out.println("Base de pesquisa: " +searchBase);
		System.out.println("Sistema operacional: " + sistemaOperacional);
		System.out.println("Filtro: " + searchFilter);
		
		
		return listaAtributos;
	}
	
	
	public String atualizarRegistroSOA(String SOA){
		String[] soa = SOA.split(" ");
		//System.out.println("Serial completo: "+ SOA);
		
		Date data = new Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String data2 = sdf.format(data);
		int serial = Integer.parseInt((String) soa[2].subSequence(8, 10));
		String dataSOA = (String) soa[2].subSequence(0, 8);
		String serialAlterado = "";
		/*System.out.println("Soa 0 "+ soa[0]);
		System.out.println("Soa 1 "+ soa[1]);
		System.out.println("Soa 2 "+ soa[2]);
		System.out.println("Soa 3 "+ soa[3]);
		System.out.println("Soa 4 "+ soa[4]);
		System.out.println("Soa 5 "+ soa[5]);
		System.out.println("Soa 6 "+ soa[6]);*/
		
		if (dataSOA.equals(data2)) {
			if (serial < 10) {
				serial = serial + 1;
				//serialAlterado =   dataSOA+"0"+serial;
				serialAlterado =  soa[0]+" "+soa[1]+" "+dataSOA+"0"+serial+" "+soa[3]+" "+soa[4]+" "+soa[5]+" "+soa[6];
			}else{
				serial = serial + 1;
				serialAlterado = soa[0]+" "+soa[1]+" "+dataSOA+serial+" "+soa[3]+" "+soa[4]+" "+soa[5]+" "+soa[6];
			}
		}else{
				serialAlterado =  soa[0]+" "+soa[1]+" "+data2+"01 "+soa[3]+" "+soa[4]+" "+soa[5]+" "+soa[6];
		}
		//System.out.println("Serial completo: "+ serialAlterado);
		return serialAlterado;
	}
	
	
	public LDAPAttributeSet nslcDebian(Nslcd nslcd, String uidNumber, String flagAdmin){
		System.out.println("Entrou no schema do Debian");
		String gidNumber = "";
		if (flagAdmin == "Sim") {
			gidNumber = "27";
		}else{
			gidNumber = "500";
		}
		
		//Atributos para criação do do registro Direto
		String[] objectClassNslcdDebian = new String[4];
		objectClassNslcdDebian [0] = "top";
		objectClassNslcdDebian [1] = "account";
		objectClassNslcdDebian [2] = "posixAccount";
		objectClassNslcdDebian [3] = "shadowAccount";
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
		System.out.println("Entrou no schema do CENTOS");
		System.out.println("Hostname: " + nslcd.getServidor());
		System.out.println("Admin = "+ flagAdmin);
		String gidNumber = "";
		if (flagAdmin.equals("Sim")) {
			gidNumber = "10";
		}else{
			gidNumber = "500";
		}
		
		//Atributos para criação do do registro Direto
		String[] objectClassNslcdCentos = new String[4];
		objectClassNslcdCentos [0] = "top";
		objectClassNslcdCentos [1] = "account";
		objectClassNslcdCentos [2] = "posixAccount";
		objectClassNslcdCentos [3] = "shadowAccount";
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