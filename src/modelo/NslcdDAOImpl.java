package modelo;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.smartcardio.ATR;

import util.SchemasLDAP;
import util.Utilitaria;

import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPAttributeSet;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;
import com.novell.ldap.LDAPModification;
import com.novell.ldap.LDAPSearchResults;

import entidades.Nslcd;
import entidades.PessoaSSH;
import entidades.Registro;


/** Classe PessoaSSHDAOImpl que implementa a Interface das PessoasSSHDAO, esta classe implementa os metodos da classe PessoaSSH.
*
* @author silas
* @see   Utilitaria 
*/

public class NslcdDAOImpl implements NslcdDAO{

	@Override
	public boolean login(Nslcd nslcd) {
		LDAPConnection conn = new LDAPConnection();
		boolean estado = false;
		try {
			conn.connect("10.3.156.9",389);
			String baseAdmin  = "uid="+ nslcd.getUid()+ ",ou=admin,ou=nslcd,dc=ufrn,dc=br";
			conn.bind(LDAPConnection.LDAP_V3, baseAdmin, nslcd.getSenha().getBytes());
			if (conn.isBound()) {
				FacesContext fc = FacesContext.getCurrentInstance();
				HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
				session.setAttribute("usuarioNslcd", nslcd.getUid());
				session.setAttribute("senhaNslcd", nslcd.getSenha());
				session.setAttribute("ldapNslcd", conn);
				estado = true;
			}else{
				estado = false;
			}

		} catch (LDAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return estado;

	}

	@Override
	public void logout() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		LDAPConnection conexao = (LDAPConnection) session.getAttribute("ldapNslcd");
		session.removeAttribute("usuarioNslcd");
		session.removeAttribute("senhaNslcd");
		try {
			conexao.disconnect();
		} catch (LDAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.invalidate();
		
	}

	@Override
	public Nslcd listAbobora(Nslcd nslcd) {
			String loginDN = "cn=admin,dc=ufrn,dc=br";
			String password = "gob0l1nux";
			String searchBase = "ou=pessoa,dc=ufrn,dc=br", searchFilter = "(uid="+nslcd.getUid()+")";
			int searchScope = LDAPConnection.SCOPE_ONE;
			//String[] atributos = {"userPassword", "cn", "uid", "mail", "gidNumber"};
			String[] atributos = {"userPassword", "uid"};
			LDAPConnection lc = new LDAPConnection();
			try {
				lc.connect("10.3.226.192", 389 );
				try {
					lc.bind( LDAPConnection.LDAP_V3, loginDN,  password.getBytes("UTF8"));
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				LDAPSearchResults searchResults = lc.search(searchBase, searchScope, searchFilter, atributos, false);
				while (searchResults.hasMore() ) {
					LDAPEntry nextEntry = null;
					try {
						nextEntry = searchResults.next();
					} catch(LDAPException e) {
						System.out.println("Error: " + e);
						continue;
					}

					LDAPAttribute attributeuid = nextEntry.getAttribute("uid");
					LDAPAttribute attributeuserPassword = nextEntry.getAttribute("userPassword");

					nslcd.setUid(attributeuid.getStringValue());
					nslcd.setSenha(attributeuserPassword.getStringValue());

				}
			} catch( LDAPException e ) {
				System.out.println("Error " + e.toString() );
			}



			return nslcd;
		}

	@Override
	public ArrayList<Nslcd> findAll(String sistemaOperacional) {
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = (HttpSession) req.getSession();
		String usuario = (String) session.getAttribute("usuarioNslcd");
		String senha = (String) session.getAttribute("senhaNslcd");
		//LDAPAttributeSet attributes = new LDAPAttributeSet();

		
		String dnAdmin = "uid="+ usuario+",ou=admin,ou=nslcd,dc=ufrn,dc=br";
		
		ArrayList<Nslcd> pessoa = new ArrayList<Nslcd>();
		ArrayList<String> gruposOUS = new ArrayList<String>();
		//String searchBase = "ou="+sistemaOperacional+"ou=nslcd,dc=ufrn,dc=br", searchFilter = "(uid=*)";
		String searchBase = "ou="+sistemaOperacional+",ou=nslcd,dc=ufrn,dc=br", searchFilter = "(uid=*)";
		System.out.println("Base de pesquisa: " +searchBase);
		System.out.println("Sistema operacional: " + sistemaOperacional);
		
		int searchScope = LDAPConnection.SCOPE_ONE;
		String[] atributos = {"uid", "modifiersName", "modifyTimestamp", "ou"};
		LDAPConnection lc = new LDAPConnection();
		try {
			lc.connect("10.3.156.9", 389 );
			try {
				lc.bind( LDAPConnection.LDAP_V3, dnAdmin,  senha.getBytes("UTF8"));
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			LDAPSearchResults searchResults = lc.search(searchBase, searchScope, searchFilter, atributos, false);
			while (searchResults.hasMore() ) {
				Nslcd nslcd = new Nslcd();
				LDAPEntry nextEntry = null;
				try {
					nextEntry = searchResults.next();
				} catch(LDAPException e) {
					System.out.println("Error: " + e);
					continue;
				}
				
				System.out.println("entradas: "+ nextEntry.toString());
				
				int numero = nextEntry.getAttribute("ou").size();
				System.out.println("Quantidade de atributos: "+ numero);
				
				LDAPAttribute attributeuid = nextEntry.getAttribute("uid");
				LDAPAttribute attributemodifiersName = nextEntry.getAttribute("modifiersName");
				LDAPAttribute attributemodifyTimestamp = nextEntry.getAttribute("modifyTimestamp");
				LDAPAttribute attributeOU = nextEntry.getAttribute("ou");

				DateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss'Z'");
				Date date = null;
				try {
					date = (Date) formatter.parse(attributemodifyTimestamp.getStringValue());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}



				
				nslcd.setUid(attributeuid.getStringValue());
				nslcd.setModificador(attributemodifiersName.getStringValue());
				nslcd.setUltimaModificacao(date);
				gruposOUS.add(attributeOU.getStringValue());
				nslcd.setListaServidores(gruposOUS);
				
				while(attributeOU.size() >= 1){
					System.out.println("Grupos: "+ attributeOU.getStringValue());
					System.out.println("Grupos: "+ attributeOU.getStringValues().hasMoreElements());
					System.out.println("Grupos: "+ attributeOU.getStringValues().nextElement().toString());
					System.out.println("Tamanho Grupos: "+ gruposOUS.size());
					attributeOU.removeValue(attributeOU.getStringValue());
				}
				
				
					
				
				
				
				//nslcd.setServidor(attributeOU.getStringValue());
				
				pessoa.add(nslcd);
			}
		} catch( LDAPException e ) {
			PessoaSSH pessoaSSH = new PessoaSSH();
			pessoaSSH.setMensagem(e.toString());
			System.out.println("Error " + e.toString() );
			
		}



		return pessoa;
	}

	@Override
	public Nslcd poruid(String uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void migrate(Nslcd nslcd, String sistemaOperacional, String flagAdmin) throws UnsupportedEncodingException, LDAPException {
		Nslcd pessoaAbobora = listAbobora(nslcd);

		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = (HttpSession) req.getSession();
		String usuario = (String) session.getAttribute("usuarioNslcd");
		//String senha = (String) session.getAttribute("senhaNslcd");
		LDAPAttributeSet attributes = new LDAPAttributeSet();
		
		
		List<String> atributo = new ArrayList<String>();
		atributo = this.procurarUsuario(nslcd, sistemaOperacional);
		
		SchemasLDAP schema = new SchemasLDAP();
		Utilitaria util = new Utilitaria();

		//String dnAdmin = "uid="+ usuario+",ou=admin,ou=nslcd,dc=ufrn,dc=br";
		String dnAdmin = "cn=admin,dc=ufrn,dc=br";
		String senha = "gob0l1nux";
		
		String uidNumber = util.lerUidNumber();
			    
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
		String base = "";
		
		if (sistemaOperacional.equals("Debian")) {
			base = "uid="+pessoaAbobora.getUid()+",ou=debian,ou=nslcd,dc=ufrn,dc=br";
			if (atributo.size() < 1) {
				attributes = schema.nslcDebian(pessoaAbobora, uidNumber, flagAdmin);
				LDAPEntry entry = new LDAPEntry(base, attributes);
				conn.add(entry);
			}else{
				LDAPAttribute attributosLDAP = schema.adicionarAtributo(nslcd);
				LDAPModification singleChange = new LDAPModification( LDAPModification.ADD, attributosLDAP);
				conn.modify(base, singleChange);
				}
		}else{
			base = "uid="+pessoaAbobora.getUid()+",ou=centos,ou=nslcd,dc=ufrn,dc=br";
			if (atributo.size() < 1) {
				attributes = schema.nslcCentos(pessoaAbobora, uidNumber, flagAdmin);
				LDAPEntry entry = new LDAPEntry(base, attributes);
				conn.add(entry);
			}else{
				LDAPAttribute attributosLDAP = schema.adicionarAtributo(nslcd);
				LDAPModification singleChange = new LDAPModification( LDAPModification.ADD, attributosLDAP);
				conn.modify(base, singleChange);
				}
		}

	}
	
	@Override
	public List<String> procurarUsuario(Nslcd nslcd, String sistemaOperacional) throws UnsupportedEncodingException {
		ArrayList<String> atributo = new ArrayList<String>();

		//String dnAdmin = "uid="+ usuario+",ou=admin,ou=dns,dc=ufrn,dc=br";
		String dnAdmin = "cn=admin,dc=ufrn,dc=br";
		String password = "gob0l1nux";
		String searchBase = "ou="+sistemaOperacional+",ou=nslcd,dc=ufrn,dc=br", searchFilter = "(uid="+nslcd.getUid()+")";
		int searchScope = LDAPConnection.SCOPE_SUB;
		String[] atributos = {"uid"};

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
	
	
}