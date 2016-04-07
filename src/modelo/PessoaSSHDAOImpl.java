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

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPAttributeSet;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;
import com.novell.ldap.LDAPSearchResults;

import entidades.PessoaSSH;
import entidades.PessoaWifi;

public class PessoaSSHDAOImpl implements PessoaSSHDAO{

	@Override
	public boolean login(PessoaSSH pessoaSSH) {
		LDAPConnection conn = new LDAPConnection();
		boolean estado = false;
		try {
			conn.connect("10.3.226.126",389);
			String baseAdmin  = "uid="+ pessoaSSH.getUid()+ ",ou=admin,ou=ssh,dc=ufrn,dc=br";
			conn.bind(LDAPConnection.LDAP_V3, baseAdmin, pessoaSSH.getSenha().getBytes());
			if (conn.isBound()) {
				FacesContext fc = FacesContext.getCurrentInstance();
				HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
				session.setAttribute("usuarioSSH", pessoaSSH.getUid());
				session.setAttribute("senhaSSH", pessoaSSH.getSenha());
				session.setAttribute("ldapSSH", conn);
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
		LDAPConnection conexao = (LDAPConnection) session.getAttribute("ldapSSH");
		session.removeAttribute("usuarioSSH");
		session.removeAttribute("senhaSSH");
		conexao.disconnect();
		session.invalidate();

	}

	@Override
	public boolean isValidate() {
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().
									getExternalContext().getRequest();
		HttpSession session = (HttpSession) req.getSession();

		String usuario = (String) session.getAttribute("usuarioSSH");
		String senha = (String) session.getAttribute("senhaSSH");
		boolean pagina = false;
		if (usuario != null && senha != null) {
			pagina = true;
		}else{
			pagina = false;
		}
		return pagina;
	}

	@Override
	public void create(PessoaSSH pessoaSSH) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(PessoaSSH pessoaSSH) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String uid) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<PessoaSSH> findAll() throws UnsupportedEncodingException,
			ParseException {
		
		ArrayList<PessoaSSH> pessoa = new ArrayList<PessoaSSH>();
		String searchBase = "ou=bolsistas,ou=redes,ou=sinfo,dc=ufrn,dc=br", 
								searchFilter = "(uid=*)";
		int searchScope = LDAPConnection.SCOPE_ONE;
		String[] atributos = {"uid", "modifiersName", "modifyTimestamp"};
		LDAPConnection lc = new LDAPConnection();
		try {
			lc.connect("10.3.226.126", 389 );
			//lc.bind( LDAPConnection.LDAP_V3, loginDN,  password.getBytes("UTF8"));
			LDAPSearchResults searchResults = lc.search(searchBase, searchScope, searchFilter, atributos, false);
			while (searchResults.hasMore() ) {
				PessoaSSH pessoaSSH = new PessoaSSH();
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



				
				pessoaSSH.setUid(attributeuid.getStringValue());
				pessoaSSH.setModificador(attributemodifiersName.getStringValue());
				pessoaSSH.setUltimaModificacao(date);
				
				pessoa.add(pessoaSSH);
			}
		} catch( LDAPException e ) {
			PessoaSSH pessoaSSH = new PessoaSSH();
			pessoaSSH.setMensagem(e.toString());
			System.out.println("Error " + e.toString() );
			
		}



		return pessoa;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void migrate(PessoaSSH pessoaSSH) throws UnsupportedEncodingException, ParseException {
		PessoaSSH pessoaAbobora = listAbobora(pessoaSSH);

		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = (HttpSession) req.getSession();
		String usuario = (String) session.getAttribute("usuarioSSH");
		String senha = (String) session.getAttribute("senhaSSH");
		LDAPAttributeSet attributes = new LDAPAttributeSet();



		String[] objectClass = new String[4];
	    objectClass[0] = "top";
	    objectClass[1] = "posixAccount";
	    objectClass[2] = "shadowAccount";
	    objectClass[3] = "account";

	    attributes.add(new LDAPAttribute("objectClass", objectClass));


	    attributes.add(new LDAPAttribute("cn", pessoaAbobora.getCn()));
	    attributes.add(new LDAPAttribute("gidNumber", "10"));
	    attributes.add(new LDAPAttribute("uidNumber", "20000"));
	    attributes.add(new LDAPAttribute("uid", pessoaAbobora.getUid()));
	    //attributes.add(new LDAPAttribute("mail", pessoaAbobora.getEmail()));
	    attributes.add(new LDAPAttribute("homeDirectory", "/bin/"+pessoaAbobora.getUid()));
	    attributes.add(new LDAPAttribute("gecos", pessoaAbobora.getUid()+",,,"));
	    attributes.add(new LDAPAttribute("userPassword", pessoaAbobora.getUserPassword()));

	    LDAPConnection conn = new LDAPConnection();
		try {
			conn.connect("10.3.226.126",389);
		} catch (LDAPException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			conn.bind(LDAPConnection.LDAP_V3, "cn=admin,dc=ufrn,dc=br", "gob0l1nux");
		} catch (LDAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String base = "uid="+pessoaAbobora.getUid()+",ou=bolsistas,ou=redes,ou=sinfo,dc=ufrn,dc=br";
		LDAPEntry entry = new LDAPEntry(base, attributes);
		try {
			conn.add(entry);

		} catch (LDAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



	@Override
	public PessoaSSH listAbobora(PessoaSSH pessoaSSH) throws UnsupportedEncodingException, ParseException {
		String loginDN = "cn=admin,dc=ufrn,dc=br";
		String password = "gob0l1nux";
		String searchBase = "ou=pessoa,dc=ufrn,dc=br", searchFilter = "(uid="+pessoaSSH.getUid()+")";
		int searchScope = LDAPConnection.SCOPE_ONE;
		//String[] atributos = {"userPassword", "cn", "uid", "mail", "gidNumber"};
		String[] atributos = {"userPassword", "cn", "uid", "mail"};
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
				LDAPAttribute attributecn = nextEntry.getAttribute("cn");
				LDAPAttribute attributemail = nextEntry.getAttribute("mail");
				LDAPAttribute attributeuserPassword = nextEntry.getAttribute("userPassword");
				//LDAPAttribute attributegidNumber = nextEntry.getAttribute("gidNumber");

				pessoaSSH.setUid(attributeuid.getStringValue());
				pessoaSSH.setCn(attributecn.getStringValue());
				pessoaSSH.setEmail(attributemail.getStringValue());
				pessoaSSH.setUserPassword(attributeuserPassword.getStringValue());
				//pessoaSSH.setGidNumber(attributegidNumber.getStringValue());

			}
		} catch( LDAPException e ) {
			System.out.println("Error " + e.toString() );
		}



		return pessoaSSH;
	}





}
