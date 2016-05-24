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

import util.Utilitaria;

import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPAttributeSet;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;
import com.novell.ldap.LDAPSearchResults;

import entidades.PessoaDns;

/** Classe PessoaDnsDAOImpl que implementa a Interface das PessoasDnsDAO, esta classe implementa os metodos da classe PessoaDns.
*
* @author silas
* @see   Utilitaria 
*/
public class PessoaDnsDAOImpl implements PessoaDnsDAO{


	/** Metodo de login num diretorio LDAP.
	 *  @param pessoaDNs
	 * @return boolean, retorna verdadeiro login sucesso falso login invalido.
	 */
	@Override
	public boolean login(PessoaDns pessoaDNs) {
		LDAPConnection conn = new LDAPConnection();
		boolean estado = false;
		try {
			conn.connect("10.3.156.9",389);
			String baseAdmin  = "uid="+ pessoaDNs.getUid()+ ",ou=admin,ou=dns,dc=ufrn,dc=br";
			conn.bind(LDAPConnection.LDAP_V3, baseAdmin, pessoaDNs.getSenha().getBytes());
			if (conn.isBound()) {
				FacesContext fc = FacesContext.getCurrentInstance();
				HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
				session.setAttribute("usuarioDns", pessoaDNs.getUid());
				session.setAttribute("senhaDns", pessoaDNs.getSenha());
				//session.setAttribute("ldapDns", conn);
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
		//LDAPConnection conexao = (LDAPConnection) session.getAttribute("ldapDNs");
		//conexao.disconnect();
		session.removeAttribute("usuarioDns");
		session.removeAttribute("senhaDns");
		session.invalidate();



	}

	@Override
	public boolean isValidate() {
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = (HttpSession) req.getSession();
		String usuario = (String) session.getAttribute("usuarioDns");
		String senha = (String) session.getAttribute("senhaDns");
		boolean pagina = false;
		if (usuario != null && senha != null) {
			pagina = true;
		}else{
			pagina = false;
		}
		return pagina;
	}

	/** Metodo que migra um usuario da base de um servidor ldap para outro.
	 *  @param pessoaDNS, usuario que será migrado.
	 *  @see listAbobora, lista o usuario do outro servidor que será migrado.
	 */
	
	@SuppressWarnings("deprecation")
	@Override
	public void migrate(PessoaDns pessoaDNS) throws UnsupportedEncodingException {
		PessoaDns pessoaAbobora = listAbobora(pessoaDNS);

		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = (HttpSession) req.getSession();
		String usuario = (String) session.getAttribute("usuarioDns");
		String senha = (String) session.getAttribute("senhaDns");
		LDAPAttributeSet attributes = new LDAPAttributeSet();



		String[] objectClass = new String[4];
	    objectClass[0] = "top";
	    objectClass[1] = "inetOrgPerson";
	    objectClass[2] = "organizationalPerson";
	    objectClass[3] = "person";

	    attributes.add(new LDAPAttribute("objectClass", objectClass));


	    attributes.add(new LDAPAttribute("cn", pessoaAbobora.getUid()));
	    attributes.add(new LDAPAttribute("sn", pessoaAbobora.getUid()));
	    //attributes.add(new LDAPAttribute("gidNumber", "10"));
	    //attributes.add(new LDAPAttribute("uidNumber", "20000"));
	    attributes.add(new LDAPAttribute("uid", pessoaAbobora.getUid()));
	    //attributes.add(new LDAPAttribute("mail", pessoaAbobora.getEmail()));
	    //attributes.add(new LDAPAttribute("homeDirectory", "/bin/"+pessoaAbobora.getUid()));
	    //attributes.add(new LDAPAttribute("gecos", pessoaAbobora.getUid()+",,,"));
	    attributes.add(new LDAPAttribute("userPassword", pessoaAbobora.getSenha()));

	    LDAPConnection conn = new LDAPConnection();
		try {
			conn.connect("10.3.156.9",389);
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
		String base = "uid="+pessoaAbobora.getUid()+",ou=admin,ou=dns,dc=ufrn,dc=br";
		LDAPEntry entry = new LDAPEntry(base, attributes);
		try {
			conn.add(entry);

		} catch (LDAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	/** Metodo que lista um usuario da base de um servidor ldap.
	 *  @param pessoaDNS, usuario que será listado.
	 *  @return PessoaDns, retorna o usuario especificado.
	 */
	private PessoaDns listAbobora(PessoaDns pessoaDNS) throws UnsupportedEncodingException {
		String loginDN = "cn=admin,dc=ufrn,dc=br";
		String password = "gob0l1nux";
		String searchBase = "ou=pessoa,dc=ufrn,dc=br", searchFilter = "(uid="+pessoaDNS.getUid()+")";
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
				//LDAPAttribute attributecn = nextEntry.getAttribute("cn");
				//LDAPAttribute attributemail = nextEntry.getAttribute("mail");
				LDAPAttribute attributeuserPassword = nextEntry.getAttribute("userPassword");
				//LDAPAttribute attributegidNumber = nextEntry.getAttribute("gidNumber");

				pessoaDNS.setUid(attributeuid.getStringValue());
				//pessoaSSH.setCn(attributecn.getStringValue());
				//pessoaSSH.setEmail(attributemail.getStringValue());
				pessoaDNS.setSenha(attributeuserPassword.getStringValue());
				//pessoaSSH.setGidNumber(attributegidNumber.getStringValue());

			}
		} catch( LDAPException e ) {
			System.out.println("Error " + e.toString() );
		}




		return pessoaDNS;
	}

	/** Metodo que lista todos os usuario da base de um servidor ldap.
	 *  @return ArrayList<PessoaDns>, retorna a lista usuarios da base dns.
	 */
	@Override
	public ArrayList<PessoaDns> findAll() throws UnsupportedEncodingException, ParseException {
		ArrayList<PessoaDns> pessoas = new ArrayList<PessoaDns>();
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = (HttpSession) req.getSession();
		String usuario = (String) session.getAttribute("usuarioDns");
		String senha = (String) session.getAttribute("senhaDns");

		String loginDN = "uid="+usuario+",ou=admin,ou=dns,dc=ufrn,dc=br";
		String searchBase = "ou=admin,ou=dns,dc=ufrn,dc=br",
								searchFilter = "(uid=*)";
		int searchScope = LDAPConnection.SCOPE_ONE;
		String[] atributos = {"uid", "modifiersName", "modifyTimestamp"};
		LDAPConnection lc = new LDAPConnection();
		try {
			lc.connect("10.3.156.9", 389 );
			lc.bind( LDAPConnection.LDAP_V3, loginDN,  senha.getBytes("UTF8"));
			LDAPSearchResults searchResults = lc.search(searchBase, searchScope, searchFilter, atributos, false);
			while (searchResults.hasMore() ) {
				PessoaDns pessoaDNS = new PessoaDns();
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




				pessoaDNS.setUid(attributeuid.getStringValue());
				pessoaDNS.setModificador(attributemodifiersName.getStringValue());
				pessoaDNS.setUltimaModificacao(date);

				pessoas.add(pessoaDNS);
			}
		} catch( LDAPException e ) {
			PessoaDns pessoaDns = new PessoaDns();
			pessoaDns.setMensagem(e.toString());
			System.out.println("Error " + e.toString() );

		}



		return pessoas;
	}

	/** Metodo que cria usuario da base dns de um servidor ldap.
	 *  @param pessoaDNS, requer objeto pessoaDNS para sua criação.
	 */
	@Override
	public void create(PessoaDns pessoaDNS) {
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = (HttpSession) req.getSession();
		String usuario = (String) session.getAttribute("usuarioDns");
		String senha = (String) session.getAttribute("senhaDns");
		LDAPAttributeSet attributes = new LDAPAttributeSet();


		String dnAdmin = "uid="+ usuario+",ou=admin,ou=dns,dc=ufrn,dc=br";

		String[] objectClass = new String[4];
	    objectClass[0] = "top";
	    objectClass[1] = "inetOrgPerson";
	    objectClass[2] = "organizationalPerson";
	    objectClass[3] = "person";

	    attributes.add(new LDAPAttribute("objectClass", objectClass));

	    attributes.add(new LDAPAttribute("cn", pessoaDNS.getUid()));
	    //attributes.add(new LDAPAttribute("dialupAccess", "access_attr"));
	    //attributes.add(new LDAPAttribute("sambaSID", "S-1-5-21-4190300969-615862220-67323155-1000"));
	    attributes.add(new LDAPAttribute("sn", pessoaDNS.getUid()));
	    attributes.add(new LDAPAttribute("uid", pessoaDNS.getUid()));
	    //attributes.add(new LDAPAttribute("mail", pessoaWifi.getEmail()));
	    //attributes.add(new LDAPAttribute("brPersonCPF", pessoaWifi.getCPF()));
	    //attributes.add(new LDAPAttribute("schacDateofBirth", dataFormatada));
	    //attributes.add(new LDAPAttribute("sambaNTPassword",  this.SambaNTPassword(pessoaWifi.getSenha())));
	    attributes.add(new LDAPAttribute("userPassword", pessoaDNS.getSenha()));
	    //attributes.add(new LDAPAttribute("pwdAttribute", "userPassword"));
	    //attributes.add(new LDAPAttribute("pwdPolicySubentry", pessoaWifi.getValidade()));


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
		String basedn = "uid="+pessoaDNS.getUid()+",ou=admin,ou=dns,dc=ufrn,dc=br";
		LDAPEntry entry = new LDAPEntry(basedn, attributes);
		try {
			conn.add(entry);

		} catch (LDAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
