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

import util.Utilitaria;

import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPAttributeSet;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;
import com.novell.ldap.LDAPSearchResults;

import entidades.PessoaSSH;


/** Classe PessoaSSHDAOImpl que implementa a Interface das PessoasSSHDAO, esta classe implementa os metodos da classe PessoaSSH.
*
* @author silas
* @see   Utilitaria 
*/

public class PessoaSSHDAOImpl implements PessoaSSHDAO{
	int uid = 1001;
	
	/** Metodo de login num diretorio LDAP.
	 *  @param pessoaSSH
	 * @return boolean, retorna verdadeiro login sucesso falso login invalido.
	 */
	@Override
	public boolean login(PessoaSSH pessoaSSH) {
		LDAPConnection conn = new LDAPConnection();
		boolean estado = false;
		try {
			conn.connect("10.3.156.9",389);
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

	/** Metodo de logout num diretorio LDAP.
	 */
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

	/** Metodo que cria usuario da base ssh de um servidor ldap.
	 *  @param pessoaSSH, requer objeto pessoaSSH para sua criação.
	 */
	@Override
	public void create(PessoaSSH pessoaSSH) {
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = (HttpSession) req.getSession();
		String usuario = (String) session.getAttribute("usuarioSSH");
		String senha = (String) session.getAttribute("senhaSSH");
		LDAPAttributeSet attributes = new LDAPAttributeSet();


		String dnAdmin = "uid="+ usuario+",ou=admin,ou=ssh,dc=ufrn,dc=br";

		String[] objectClass = new String[4];
	    objectClass[0] = "top";
	    objectClass[1] = "posixAccount";
	    objectClass[2] = "shadowAccount";
	    objectClass[3] = "account";

	    attributes.add(new LDAPAttribute("objectClass", objectClass));

	    
	    attributes.add(new LDAPAttribute("cn", pessoaSSH.getUid()));
	    attributes.add(new LDAPAttribute("gidNumber", pessoaSSH.getGidNumber()));
	    attributes.add(new LDAPAttribute("uidNumber", pessoaSSH.getUidNumber()));
	    attributes.add(new LDAPAttribute("uid", pessoaSSH.getUid()));
	    //attributes.add(new LDAPAttribute("mail", pessoaAbobora.getEmail()));
	    attributes.add(new LDAPAttribute("homeDirectory", "/bin/"+pessoaSSH.getUid()));
	    attributes.add(new LDAPAttribute("gecos", pessoaSSH.getUid()+",,,"));
	    attributes.add(new LDAPAttribute("userPassword", pessoaSSH.getUserPassword()));
	    
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
		String base = "uid="+pessoaSSH.getUid()+",ou=ssh,dc=ufrn,dc=br";
		LDAPEntry entry = new LDAPEntry(base, attributes);
		try {
			conn.add(entry);

		} catch (LDAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/** Metodo que lista todos os usuario da base de um servidor ldap.
	 *  @return ArrayList<PessoaSSH>, retorna a lista usuarios da base ssh.
	 */
	@Override
	public ArrayList<PessoaSSH> findAll() throws UnsupportedEncodingException,
			ParseException {
		
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = (HttpSession) req.getSession();
		String usuario = (String) session.getAttribute("usuarioSSH");
		String senha = (String) session.getAttribute("senhaSSH");
		//LDAPAttributeSet attributes = new LDAPAttributeSet();


		String dnAdmin = "uid="+ usuario+",ou=admin,ou=ssh,dc=ufrn,dc=br";
		
		
		
		ArrayList<PessoaSSH> pessoa = new ArrayList<PessoaSSH>();
		String searchBase = "ou=ssh,dc=ufrn,dc=br", searchFilter = "(uid=*)";
		int searchScope = LDAPConnection.SCOPE_ONE;
		String[] atributos = {"uid", "modifiersName", "modifyTimestamp"};
		LDAPConnection lc = new LDAPConnection();
		try {
			lc.connect("10.3.156.9", 389 );
			lc.bind( LDAPConnection.LDAP_V3, dnAdmin,  senha.getBytes("UTF8"));
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

	/** Metodo que migra um usuario da base de um servidor ldap para outro.
	 *  @param pessoaSSH, usuario que será migrado.
	 *  @see listAbobora, lista o usuario do outro servidor que será migrado.
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void migrate(PessoaSSH pessoaSSH) throws UnsupportedEncodingException, ParseException {
		
		PessoaSSH pessoaAbobora = listAbobora(pessoaSSH);

		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = (HttpSession) req.getSession();
		String usuario = (String) session.getAttribute("usuarioSSH");
		String senha = (String) session.getAttribute("senhaSSH");
		LDAPAttributeSet attributes = new LDAPAttributeSet();

		String dnAdmin = "uid="+ usuario+",ou=admin,ou=ssh,dc=ufrn,dc=br";

		String[] objectClass = new String[4];
	    objectClass[0] = "top";
	    objectClass[1] = "posixAccount";
	    objectClass[2] = "shadowAccount";
	    objectClass[3] = "account";

	    attributes.add(new LDAPAttribute("objectClass", objectClass));

	    uid += 1;
	    String numero = "" + uid;
	    attributes.add(new LDAPAttribute("cn", pessoaAbobora.getUid()));
	    attributes.add(new LDAPAttribute("gidNumber", "10"));
	    attributes.add(new LDAPAttribute("uidNumber", numero));
	    attributes.add(new LDAPAttribute("uid", pessoaAbobora.getUid()));
	    attributes.add(new LDAPAttribute("homeDirectory", "/bin/"+pessoaAbobora.getUid()));
	    attributes.add(new LDAPAttribute("gecos", pessoaAbobora.getUid()+",,,"));
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
		String base = "uid="+pessoaAbobora.getUid()+",ou=ssh,dc=ufrn,dc=br";
		LDAPEntry entry = new LDAPEntry(base, attributes);
		try {
			conn.add(entry);

		} catch (LDAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	/** Metodo que lista um usuario da base de um servidor ldap.
	 *  @param pessoaSSH, usuario que será listado.
	 *  @return PessoaSSH, retorna o usuario especificado.
	 */
	@Override
	public PessoaSSH listAbobora(PessoaSSH pessoaSSH) throws UnsupportedEncodingException, ParseException {
		String loginDN = "cn=admin,dc=ufrn,dc=br";
		String password = "gob0l1nux";
		String searchBase = "ou=pessoa,dc=ufrn,dc=br", searchFilter = "(uid="+pessoaSSH.getUid()+")";
		int searchScope = LDAPConnection.SCOPE_ONE;
		//String[] atributos = {"userPassword", "cn", "uid", "mail", "gidNumber"};
		String[] atributos = {"userPassword", "uid", "mail"};
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
				//LDAPAttribute attributeuidNumber = nextEntry.getAttribute("uidNumber");
				LDAPAttribute attributemail = nextEntry.getAttribute("mail");
				LDAPAttribute attributeuserPassword = nextEntry.getAttribute("userPassword");
				//LDAPAttribute attributegidNumber = nextEntry.getAttribute("gidNumber");

				pessoaSSH.setUid(attributeuid.getStringValue());
				pessoaSSH.setEmail(attributemail.getStringValue());
				pessoaSSH.setUserPassword(attributeuserPassword.getStringValue());
				//pessoaSSH.setUidNumber(attributeuidNumber.getStringValue());
				//pessoaSSH.setGidNumber(attributegidNumber.getStringValue());

			}
		} catch( LDAPException e ) {
			System.out.println("Error " + e.toString() );
		}



		return pessoaSSH;
	}

	/** Metodo que lista o usuario por uid.
	 *  @param uid, uid de usuario que será listado.
	 *  @return PessoaSSH, retorna o usuario especificado.
	 */
	@Override
	public PessoaSSH poruid(String uid) throws UnsupportedEncodingException, ParseException {
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = (HttpSession) req.getSession();
		String usuario = (String) session.getAttribute("usuarioSSH");
		String senha = (String) session.getAttribute("senhaSSH");
		//LDAPAttributeSet attributes = new LDAPAttributeSet();

		PessoaSSH pessoa = new PessoaSSH();
		String dnAdmin = "uid="+ usuario+",ou=admin,ou=ssh,dc=ufrn,dc=br";
		
		
		String searchBase = "ou=ssh,dc=ufrn,dc=br", searchFilter = "(uid="+uid+")";
		int searchScope = LDAPConnection.SCOPE_ONE;
		String[] atributos = {"uid", "modifiersName", "modifyTimestamp"};
		LDAPConnection lc = new LDAPConnection();
		try {
			lc.connect("10.3.156.9", 389 );
			lc.bind( LDAPConnection.LDAP_V3, dnAdmin,  senha.getBytes("UTF8"));
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



				
				pessoa.setUid(attributeuid.getStringValue());
				pessoa.setModificador(attributemodifiersName.getStringValue());
				pessoa.setUltimaModificacao(date);
				
		
			}
		} catch( LDAPException e ) {
			PessoaSSH pessoaSSH = new PessoaSSH();
			pessoaSSH.setMensagem(e.toString());
			System.out.println("Error " + e.toString() );
			
		}

		return pessoa;	
	}
}