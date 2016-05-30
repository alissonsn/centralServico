package modelo;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import util.Utilitaria;
import jcifs.util.Hexdump;
import jcifs.util.MD4;

import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPAttributeSet;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;
import com.novell.ldap.LDAPSearchResults;

import entidades.PessoaWifi;

/** Classe PessoaWifiImpl que implementa a Interface das PessoasWifiDAO, esta classe implementa os metodos da classe PessoaWifi.
*
* @author silas
* @see   Utilitaria 
*/

public class PessoaWifiDAOImpl implements PessoaWifiDAO{
	Utilitaria util = new Utilitaria();

	/** Metodo que cria pessoawifi.
	 * @param pessoawifi, requer objeto pessoawifi para sua criação.
	 */
	@Override
	public void create(PessoaWifi pessoaWifi) throws UnsupportedEncodingException {
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = (HttpSession) req.getSession();
		String usuario = (String) session.getAttribute("usuarioWifi");
		String senha = (String) session.getAttribute("senhaWifi");
		LDAPAttributeSet attributes = new LDAPAttributeSet();


		String dnAdmin = "uid="+ usuario+",ou=admin,ou=802.1x,dc=ufrn,dc=br";

		String[] objectClass = new String[9];
	    objectClass[0] = "top";
	    objectClass[1] = "inetOrgPerson";
	    objectClass[2] = "organizationalPerson";
	    objectClass[3] = "person";
	    objectClass[4] = "radiusprofile";
	    objectClass[5] = "sambaSamAccount";
	    objectClass[6] = "schacPersonalCharacteristics";
	    objectClass[7] = "brPerson";
	    objectClass[8] = "pwdPolicy";


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
	    attributes.add(new LDAPAttribute("sambaNTPassword",  this.SambaNTPassword(pessoaWifi.getSenha())));
	    attributes.add(new LDAPAttribute("userPassword", pessoaWifi.getSenha()));
	    attributes.add(new LDAPAttribute("pwdAttribute", "userPassword"));
	    attributes.add(new LDAPAttribute("pwdPolicySubentry", pessoaWifi.getValidade()));


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
		String basedn = "uid="+pessoaWifi.getUid()+",ou=802.1x,dc=ufrn,dc=br";
		LDAPEntry entry = new LDAPEntry(basedn, attributes);
		try {
			conn.add(entry);

		} catch (LDAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	/** Metodo que consulta todas as pessoas cadastradas na rede wifi visitantes num diretorio LDAP..
	 *
	 * @return List<PessoaWifi>, Contendo todas as pessoas cadastradas no diretorio LDAP.
	 */
	@Override
	public ArrayList<PessoaWifi> findAll() throws UnsupportedEncodingException, ParseException {
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = (HttpSession) req.getSession();
		String usuario = (String) session.getAttribute("usuarioWifi");
		String senha = (String) session.getAttribute("senhaWifi");
		
		
		ArrayList<PessoaWifi> pessoa = new ArrayList<PessoaWifi>();
		String loginDN = "uid=" + usuario +",ou=admin,ou=802.1x,dc=ufrn,dc=br";
		String searchBase = "ou=802.1x,dc=ufrn,dc=br", searchFilter = "(objectClass=pwdPolicy)";
		int searchScope = LDAPConnection.SCOPE_ONE;
		String[] atributos = {"uid", "modifiersName", "modifyTimestamp", "pwdPolicySubentry"};

		LDAPConnection lc = new LDAPConnection();
		try {
			lc.connect("10.3.156.9", 389 );
			lc.bind( LDAPConnection.LDAP_V3, loginDN,  senha.getBytes("UTF8"));
			LDAPSearchResults searchResults = lc.search(searchBase, searchScope, searchFilter, atributos, false);
			while (searchResults.hasMore() ) {
				PessoaWifi pessoaWifi = new PessoaWifi();
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
				LDAPAttribute attributepwdPolicySubentry = nextEntry.getAttribute("pwdPolicySubentry");

				Date data = util. TimestampDateString(attributemodifyTimestamp.getStringValue());
				String dataFormatada = util.DateString(data);


				pessoaWifi.setUid(attributeuid.getStringValue());
				pessoaWifi.setModificador(attributemodifiersName.getStringValue());
				pessoaWifi.setUltimaModificacao(dataFormatada);
				pessoaWifi.setValidade(attributepwdPolicySubentry.getStringValue());
				pessoa.add(pessoaWifi);
			}
		} catch( LDAPException e ) {
			System.out.println("Error " + e.toString() );
		}



		return pessoa;
	}

	/** Metodo de login num diretorio LDAP.
	 *  @param pessoaWifi
	 * @return boolean, retorna verdadeiro login sucesso falso login invalido.
	 */
	@Override
	public boolean login(PessoaWifi pessoaWifi) {
		LDAPConnection conn = new LDAPConnection();
		boolean estado = false;
		try {
			conn.connect("10.3.156.9",389);
			String baseAdmin  = "uid="+ pessoaWifi.getUid()+ ",ou=admin,ou=802.1x,dc=ufrn,dc=br";
			conn.bind(LDAPConnection.LDAP_V3, baseAdmin, pessoaWifi.getSenha().getBytes());
			if (conn.isBound()) {
				FacesContext fc = FacesContext.getCurrentInstance();
				HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
				session.setAttribute("usuarioWifi", pessoaWifi.getUid());
				session.setAttribute("senhaWifi", pessoaWifi.getSenha());
				session.setAttribute("ldapWifi", conn);
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

	/** Metodo de logout num diretorio LDAP.
	 */
	@Override
	public void logout() throws LDAPException {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		LDAPConnection conexao = (LDAPConnection) session.getAttribute("ldapWifi");
		session.removeAttribute("usuarioWifi");
		session.removeAttribute("senhaWifi");
		conexao.disconnect();
		session.invalidate();
	}

	/** Metodo que verifica vencimento da conta.
	 *  @param hoje, pessoaWifi, data de hoje e a pessoa.
	 * @return boolean, retorna true se a conta ainda está ativa falso se esta expirada.
	 */
	@Override
	public boolean verificavencimento(Date hoje, PessoaWifi pessoaWifi){
		boolean data;
		Date validade = util.expiraConta(pessoaWifi);
		if (validade.before(hoje)){
			data = true;
		}
		else if (validade.after(hoje))
			data = false;
		else
			data = true;
		return data;
	}

	/** Metodo isValidate.
	 * @return boolean, retorna true se o usuario ainda está conectado falso caso contrário.
	 */
	@Override
	public boolean isValidate() {
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = (HttpSession) req.getSession();

		String usuario = (String) session.getAttribute("usuarioWifi");
		String senha = (String) session.getAttribute("senhaWifi");
		boolean pagina = false;
		if (usuario != null && senha != null) {
			pagina = true;
		}else{
			pagina = false;
		}
		return pagina;
	}
}
