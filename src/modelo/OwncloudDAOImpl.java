package modelo;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPAttributeSet;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;
import com.novell.ldap.LDAPSearchResults;

import entidades.Owncloud;




public class OwncloudDAOImpl implements IOwncloud{

	@Override
	public boolean login(Owncloud owncloud) {
		LDAPConnection conn = new LDAPConnection();
		boolean estado = false;
		try {
			conn.connect("10.3.156.9",389);
			String baseAdmin  = "uid="+ owncloud.getUid()+ ",ou=admin,ou=owncloud,dc=ufrn,dc=br";
			String senha = owncloud.getUserPassword();
			conn.bind(LDAPConnection.LDAP_V3, baseAdmin, senha.getBytes());
			if (conn.isBound()) {
				FacesContext fc = FacesContext.getCurrentInstance();
				HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
				session.setAttribute("usuarioOwncloud", owncloud.getUid());
				session.setAttribute("senhaOwncloud", owncloud.getUserPassword());
				session.setAttribute("ldapOwncloud", conn);
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
	public void logout() throws LDAPException {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		LDAPConnection conexao = (LDAPConnection) session.getAttribute("ldapOwncloud");
		session.removeAttribute("usuarioOwncloud");
		session.removeAttribute("senhaOwncloud");
		conexao.disconnect();
		session.invalidate();
		
	}

	@Override
	public void create(Owncloud owncloud) {
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = (HttpSession) req.getSession();
		String usuario = (String) session.getAttribute("usuarioOwncloud");
		String senha = (String) session.getAttribute("senhaOwncloud");
		LDAPAttributeSet attributes = new LDAPAttributeSet();


		String dnAdmin = "uid="+ usuario+",ou=admin,ou=owncloud,dc=ufrn,dc=br";

		String[] objectClass = new String[4];
	    objectClass[0] = "top";
	    objectClass[1] = "person";
	    objectClass[2] = "inetOrgPerson";
	    objectClass[3] = "organizationalPerson";

	    attributes.add(new LDAPAttribute("objectClass", objectClass));

	    
	    attributes.add(new LDAPAttribute("cn", owncloud.getUid()));
	    attributes.add(new LDAPAttribute("sn", owncloud.getUid()));
	    attributes.add(new LDAPAttribute("uid", owncloud.getUid()));
	    attributes.add(new LDAPAttribute("userPassword", owncloud.getUserPassword()));
	    
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
		String base = "uid="+owncloud.getUid()+",ou=owncloud,dc=ufrn,dc=br";
		LDAPEntry entry = new LDAPEntry(base, attributes);
		try {
			conn.add(entry);
		} catch (LDAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Owncloud listAbobora(Owncloud owncloud)
			throws UnsupportedEncodingException, ParseException {
		String loginDN = "cn=admin,dc=ufrn,dc=br";
		String password = "gob0l1nux";
		String searchBase = "ou=pessoa,dc=ufrn,dc=br", searchFilter = "(uid="+owncloud.getUid()+")";
		int searchScope = LDAPConnection.SCOPE_ONE;
		//String[] atributos = {"userPassword", "cn", "uid", "mail", "gidNumber"};
		String[] atributos = {"userPassword", "uid"};
		LDAPConnection lc = new LDAPConnection();
		try {
			lc.connect("10.3.226.192", 389 );
			lc.bind( LDAPConnection.LDAP_V3, loginDN,  password.getBytes("UTF8"));
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

				owncloud.setUid(attributeuid.getStringValue());
				owncloud.setUserPassword(attributeuserPassword.getStringValue());

			}
		} catch( LDAPException e ) {
			System.out.println("Error " + e.toString() );
		}
		return owncloud;
	}

	@Override
	public ArrayList<Owncloud> findAll() throws UnsupportedEncodingException,
			ParseException {
		
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = (HttpSession) req.getSession();
		String usuario = (String) session.getAttribute("usuarioOwncloud");
		String senha = (String) session.getAttribute("senhaOwncloud");


		String dnAdmin = "uid="+ usuario+",ou=admin,ou=owncloud,dc=ufrn,dc=br";
		
		ArrayList<Owncloud> pessoa = new ArrayList<Owncloud>();
		String searchBase = "ou=owncloud,dc=ufrn,dc=br", searchFilter = "(uid=*)";
		int searchScope = LDAPConnection.SCOPE_ONE;
		String[] atributos = {"uid", "modifiersName", "modifyTimestamp"};
		LDAPConnection lc = new LDAPConnection();
		try {
			lc.connect("10.3.156.9", 389 );
			lc.bind( LDAPConnection.LDAP_V3, dnAdmin,  senha.getBytes("UTF8"));
			LDAPSearchResults searchResults = lc.search(searchBase, searchScope, searchFilter, atributos, false);
			while (searchResults.hasMore() ) {
				Owncloud owncloud = new Owncloud();
				LDAPEntry nextEntry = null;
				try {
					nextEntry = searchResults.next();
				} catch(LDAPException e) {
					System.out.println("Error: " + e);
					continue;
				}

				LDAPAttribute attributeuid = nextEntry.getAttribute("uid");
				LDAPAttribute attributemodifiersName = nextEntry.getAttribute("modifiersName");
				LDAPAttribute attributemodifyTimestamp = nextEntry.getAttribute("modifyTimestamp");

				DateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss'Z'");
				Date date = (Date) formatter.parse(attributemodifyTimestamp.getStringValue());
				
				owncloud.setUid(attributeuid.getStringValue());
				owncloud.setModificador(attributemodifiersName.getStringValue());
				owncloud.setUltimaModificacao(date);
				
				pessoa.add(owncloud);
			}
		} catch( LDAPException e ) {
			//PessoaSSH pessoaSSH = new PessoaSSH();
			//pessoaSSH.setMensagem(e.toString());
			System.out.println("Error " + e.toString() );
			
		}



		return pessoa;
	}

	@Override
	public Owncloud poruid(String uid) throws UnsupportedEncodingException,
			ParseException {
	
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = (HttpSession) req.getSession();
		String usuario = (String) session.getAttribute("usuarioOwncloud");
		String senha = (String) session.getAttribute("senhaOwncloud");
		//LDAPAttributeSet attributes = new LDAPAttributeSet();

		Owncloud owncloud = new Owncloud();
		String dnAdmin = "uid="+ usuario+",ou=admin,ou=owncloud,dc=ufrn,dc=br";
		
		
		String searchBase = "ou=owncloud,dc=ufrn,dc=br", searchFilter = "(uid="+uid+")";
		int searchScope = LDAPConnection.SCOPE_ONE;
		String[] atributos = {"uid", "modifiersName", "modifyTimestamp"};
		LDAPConnection lc = new LDAPConnection();
		try {
			lc.connect("10.3.156.9", 389 );
			lc.bind( LDAPConnection.LDAP_V3, dnAdmin,  senha.getBytes("UTF8"));
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
				LDAPAttribute attributemodifiersName = nextEntry.getAttribute("modifiersName");
				LDAPAttribute attributemodifyTimestamp = nextEntry.getAttribute("modifyTimestamp");

				DateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss'Z'");
				Date date = (Date) formatter.parse(attributemodifyTimestamp.getStringValue());

				
				owncloud.setUid(attributeuid.getStringValue());
				owncloud.setModificador(attributemodifiersName.getStringValue());
				owncloud.setUltimaModificacao(date);
				
		
			}
		} catch( LDAPException e ) {
			//PessoaSSH pessoaSSH = new PessoaSSH();
			//pessoaSSH.setMensagem(e.toString());
			System.out.println("Error " + e.toString() );
			
		}

		return owncloud;	
	}
		

	@Override
	public void migrate(Owncloud owncloud) throws UnsupportedEncodingException,
			ParseException {
	
		
		Owncloud pessoaAbobora = listAbobora(owncloud);

		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = (HttpSession) req.getSession();
		String usuario = (String) session.getAttribute("usuarioOwncloud");
		String senha = (String) session.getAttribute("senhaOwncloud");
		LDAPAttributeSet attributes = new LDAPAttributeSet();

		String dnAdmin = "uid="+ usuario+",ou=admin,ou=owncloud,dc=ufrn,dc=br";

		String[] objectClass = new String[4];
	    objectClass[0] = "top";
	    objectClass[1] = "person";
	    objectClass[2] = "inetOrgPerson";
	    objectClass[3] = "organizationalPerson";

	    attributes.add(new LDAPAttribute("objectClass", objectClass));

	    
	    attributes.add(new LDAPAttribute("cn", pessoaAbobora.getUid()));
	    attributes.add(new LDAPAttribute("sn", pessoaAbobora.getUid()));
	    attributes.add(new LDAPAttribute("uid", pessoaAbobora.getUid()));
	    attributes.add(new LDAPAttribute("userPassword", pessoaAbobora.getUserPassword()));
	    
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
		String base = "uid="+pessoaAbobora.getUid()+",ou=owncloud,dc=ufrn,dc=br";
		LDAPEntry entry = new LDAPEntry(base, attributes);
		try {
			conn.add(entry);

		} catch (LDAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}	
}