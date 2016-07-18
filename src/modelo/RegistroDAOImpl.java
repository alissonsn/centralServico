package modelo;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import util.SchemasLDAP;

import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPAttributeSet;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;
import com.novell.ldap.LDAPModification;
import com.novell.ldap.LDAPSearchResults;

import entidades.Registro;

/** Classe RegstroDAOImpl que implementa a Interface RegistroDAO, esta classe implementa crud da entidade Registro.
*
* @author silas
*
*/

public class RegistroDAOImpl implements RegistroDAO{

	/** Metodo que cria registro direto.
	 * @param registro, requer objeto registro para sua criação.
	 * @throws UnsupportedEncodingException 
	 * @throws LDAPException 
	 */
	@Override
	public void createRegistroDireto(Registro registro) throws UnsupportedEncodingException, LDAPException {
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = (HttpSession) req.getSession();
		String usuario = (String) session.getAttribute("usuarioDns");
		String senha  = (String) session.getAttribute("senhaDns");
		
		
		List<String> atributo = new ArrayList<String>();
		String soa = "";
		String serial  = "";
		soa = this.listarSOADireto(registro);
		System.out.println("Serial: "+ soa);
		atributo = this.listarRegistroDireto(registro);
		
		SchemasLDAP schema = new SchemasLDAP();
		LDAPAttributeSet attributesRegistroDireto = new LDAPAttributeSet();		
		serial = schema.atualizarRegistroSOA(soa);
		
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
		String serialRegistroDireto = "relativeDomainName=@,zoneName="+registro.getDominio()+",ou=dns,dc=ufrn,dc=br";
		//System.out.println("SerialDireto: "+serialRegistroDireto);
		
		//System.out.println("DN: " + baseRegistroDireto);
		//System.out.println("tamanho da lista de registros:" + atributo) ;
		//System.out.println("tamanho da lista de registros:" + atributo.size());
		
		if (atributo.size() > 0) {
			//LDAPAttribute attributesRegistroDiretoAdicionar = schema.RegistroDiretoAdicionar(registro, "aRecord");
			LDAPAttribute attributesRegistroDiretoAdicionar = schema.RegistroDiretoAdicionar(registro);
			LDAPAttribute attributoSerial = schema.adicionarSerial(serial);
			LDAPModification Change = new LDAPModification( LDAPModification.ADD, attributesRegistroDiretoAdicionar );
			LDAPModification Change2 = new LDAPModification( LDAPModification.REPLACE, attributoSerial );
			conexao.modify(baseRegistroDireto, Change);
			conexao.modify(serialRegistroDireto, Change2);
		}else{
			attributesRegistroDireto = schema.RegistroDireto(registro);
			LDAPEntry entryRegistroDireto = new LDAPEntry(baseRegistroDireto, attributesRegistroDireto);
			conexao.add(entryRegistroDireto);
		}
	}

	/** Metodo que cria registro reverso.
	 * @param registro, requer objeto registro para sua criação.
	 * @throws UnsupportedEncodingException 
	 * @throws LDAPException 
	 */
	@Override
	public void createRegistroReverso(Registro registro) throws UnsupportedEncodingException, LDAPException {
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = (HttpSession) req.getSession();
		String usuario = (String) session.getAttribute("usuarioDns");
		String senha  = (String) session.getAttribute("senhaDns");
		List<String> atributo = new ArrayList<String>();
		
		//Atributos para criação do do registro Reverso
	    //Divindo o ip por octais
	  	String[] octal = registro.getIp().toString().split("\\.");
	  	Object primeiroOctal = octal[0];
	  	Object segundoOctal = octal[1];
	  	Object terceiroOctal = octal[2];
	  	Object quartoOctal = octal[3];	  	
	  	String zoneName = terceiroOctal+"."+segundoOctal+"."+primeiroOctal+".in-addr.arpa";
	  	String relativeDomainName = quartoOctal.toString();
	  	
	  	atributo = this.listarRegistroReverso(registro, relativeDomainName, zoneName);
	  	
		SchemasLDAP schema = new SchemasLDAP();
		LDAPAttributeSet attributesRegistroReverso = new LDAPAttributeSet();		
		
		String soa = "";
		String serial  = "";
		soa = this.listarSOAReverso(registro, zoneName);
		serial = schema.atualizarRegistroSOA(soa);
		System.out.println("Serial: "+serial);
		
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
		String baseRegistroReverso = "relativeDomainName="+relativeDomainName+",zoneName="+ zoneName +",ou=dns,dc=ufrn,dc=br";
		String serialRegistroReverso = "relativeDomainName=@,zoneName="+zoneName +",ou=dns,dc=ufrn,dc=br";
		System.out.println("SerialReverso: "+serialRegistroReverso);
		
		
		//System.out.println("DN: " + baseRegistroReverso);
		//System.out.println("tamanho da lista de registros:" + atributo) ;
		//System.out.println("tamanho da lista de registros:" + atributo.size()) ;
		
		if (atributo.size() > 0) {
			LDAPAttribute attributesRegistroReversoAdicionar = schema.RegistroReversoAdicionar(registro, "pTRRecord");
			LDAPAttribute attributoSerial = schema.adicionarSerial(serial);
			LDAPModification Change = new LDAPModification( LDAPModification.ADD, attributesRegistroReversoAdicionar );
			LDAPModification Change2 = new LDAPModification( LDAPModification.REPLACE, attributoSerial );
			conexao.modify(baseRegistroReverso, Change);
			conexao.modify(serialRegistroReverso, Change2);
		}else{
			attributesRegistroReverso = schema.RegistroReverso(registro, primeiroOctal, segundoOctal, terceiroOctal, quartoOctal);
			LDAPEntry entryRegistroReverso = new LDAPEntry(baseRegistroReverso, attributesRegistroReverso);
			conexao.add(entryRegistroReverso);
		}
	}
	/** Metodo que remove registro.
	 * @param registro, requer objeto registro para sua remoção.
	 */
	@Override
	public void delete(Registro registro) throws UnsupportedEncodingException, LDAPException {
		//Atributos para criação do do registro Reverso
	    //Divindo o ip por octais
	  	String[] octal = registro.getIp().toString().split("\\.");
	  	String primeiroOctal = octal[0];
	  	String segundoOctal = octal[1];
	  	String terceiroOctal = octal[2];
	  	String quartoOctal = octal[3];

	  	List<String> atributo = new ArrayList<String>();
		//List<String> attr = new ArrayList<String>();
		String relativeDomainName = registro.getNomeMaquina();
		String zoneName = registro.getDominio(); 
		
		atributo = this.listarRegistroReverso(registro, relativeDomainName, zoneName);
	  	
		String baseDireta = "relativeDomainName="+relativeDomainName+",zoneName="+zoneName+ ",ou=dns,dc=ufrn,dc=br";
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

	/** Metodo que consulta registro especifico por seu dominio. 
	 * @return Registro, Contendo registro cadastrado.
	 */
	@Override
	public Registro find(String dominio) {
		// TODO Auto-generated method stub
		return null;
	}

	
	/** Metodo que consulta todas registro cadastrado. 
	 * @return List<Registro>, Contendo todos os registros cadastrados.
	 */
	@Override
	public List<Registro> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/** Metodo que consulta todos registros reversos cadastrados. 
	 * @return List<String'>, Contendo todos os registros reversos cadastrados.
	 */
	@Override
	public List<String> listarRegistroReverso(Registro registro, String relativeDomainName, String zoneName) throws UnsupportedEncodingException {
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = (HttpSession) req.getSession();
		String usuario = (String) session.getAttribute("usuarioDns");
		String senha = (String) session.getAttribute("senhaDns");
		ArrayList<String> atributo = new ArrayList<String>();


		//String dnAdmin = "uid="+ usuario+",ou=admin,ou=dns,dc=ufrn,dc=br";
		String dnAdmin = "cn=admin,dc=ufrn,dc=br";
		String password = "gob0l1nux";
		String searchBase = "zoneName="+zoneName+ ",ou=dns,dc=ufrn,dc=br", searchFilter = "(relativeDomainName="+relativeDomainName+")";
		int searchScope = LDAPConnection.SCOPE_SUB;
		String[] atributos = {"relativeDomainName"};

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
					//System.out.println("Error: " + e);
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
						}
					}
				}
			}
		} catch( LDAPException e ) {
			//System.out.println("Error " + e.toString() );
		}
		return atributo;
	}

	/** Metodo que consulta todos registros reversos cadastrados.
	 * @return List<String'>, Contendo todos os registros reversos cadastrados.
	 */
	@Override
	public List<String> listarRegistroDireto(Registro registro) throws UnsupportedEncodingException {
		ArrayList<String> atributo = new ArrayList<String>();

		//String dnAdmin = "uid="+ usuario+",ou=admin,ou=dns,dc=ufrn,dc=br";
		String dnAdmin = "cn=admin,dc=ufrn,dc=br";
		String password = "gob0l1nux";
		String searchBase = "relativeDomainName="+registro.getNomeMaquina()+",zoneName="+registro.getDominio()+ ",ou=dns,dc=ufrn,dc=br", searchFilter = "(aRecord=*)";
		int searchScope = LDAPConnection.SCOPE_SUB;
		String[] atributos = {"aRecord"};

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
					//System.out.println("Error: " + e);
					continue;
				}
				LDAPAttributeSet attributeSet = nextEntry.getAttributeSet();
				Iterator allAttributes = attributeSet.iterator();
				while ( allAttributes.hasNext() ) {
					LDAPAttribute attribute = (LDAPAttribute)allAttributes.next();
					String attributeName = attribute.getName();
					Enumeration allValues = attribute.getStringValues();
					if ( allValues != null ) {
						while ( allValues.hasMoreElements() ) {
							atributo.add(allValues.nextElement().toString());
						}
					}
				}
			}
		} catch( LDAPException e ) {
			//System.out.println("Error " + e.toString() );
		}

		return atributo;
	}

	@Override
	public String listarSOADireto(Registro registro) throws UnsupportedEncodingException {
		String atributo = "";

		//String dnAdmin = "uid="+ usuario+",ou=admin,ou=dns,dc=ufrn,dc=br";
		String dnAdmin = "cn=admin,dc=ufrn,dc=br";
		String password = "gob0l1nux";
		String searchBase = "relativeDomainName=@,zoneName="+registro.getDominio()+ ",ou=dns,dc=ufrn,dc=br", searchFilter = "(objectClass=top)";
		int searchScope = LDAPConnection.SCOPE_SUB;
		String[] atributos = {"sOARecord"};

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
					//System.out.println("Error: " + e);
					continue;
				}
				LDAPAttributeSet attributeSet = nextEntry.getAttributeSet();
				Iterator allAttributes = attributeSet.iterator();
				while ( allAttributes.hasNext() ) {
					LDAPAttribute attribute = (LDAPAttribute)allAttributes.next();
					String attributeName = attribute.getName();
					Enumeration allValues = attribute.getStringValues();
					if ( allValues != null ) {
						while ( allValues.hasMoreElements() ) {
							atributo = allValues.nextElement().toString();
						}
					}
				}
			}
		} catch( LDAPException e ) {
			//System.out.println("Error " + e.toString() );
		}

		return atributo;
	}

	@Override
	public String listarSOAReverso(Registro registro, String zoneName) throws UnsupportedEncodingException {
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = (HttpSession) req.getSession();
		String usuario = (String) session.getAttribute("usuarioDns");
		String senha = (String) session.getAttribute("senhaDns");
		String atributo = "";


		//String dnAdmin = "uid="+ usuario+",ou=admin,ou=dns,dc=ufrn,dc=br";
		String dnAdmin = "cn=admin,dc=ufrn,dc=br";
		String password = "gob0l1nux";
		String searchBase = "relativeDomainName=@,zoneName="+zoneName+ ",ou=dns,dc=ufrn,dc=br", searchFilter = "(objectClass=top)";
		int searchScope = LDAPConnection.SCOPE_SUB;
		String[] atributos = {"sOARecord"};

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
					//System.out.println("Error: " + e);
					continue;
				}
				LDAPAttributeSet attributeSet = nextEntry.getAttributeSet();
				Iterator allAttributes = attributeSet.iterator();
				while ( allAttributes.hasNext() ) {
					LDAPAttribute attribute = (LDAPAttribute)allAttributes.next();
					String attributeName = attribute.getName();
					//System.out.println("attributeName "+attributeName);
					Enumeration allValues = attribute.getStringValues();
					if ( allValues != null ) {
						while ( allValues.hasMoreElements() ) {
							atributo = allValues.nextElement().toString();
						}
					}
				}
			}
		} catch( LDAPException e ) {
			//System.out.println("Error " + e.toString() );
		}
		return atributo;
	}
}