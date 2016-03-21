package modelo;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPAttributeSet;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;

import entidades.PessoaWifi;

public class PessoaWifiDAOImpl implements PessoaWifiDAO{


	@Override
	public void create(PessoaWifi pessoaWifi) {
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
	    attributes.add(new LDAPAttribute("sambaNTPassword", pessoaWifi.getSenha()));
	    attributes.add(new LDAPAttribute("userPassword", pessoaWifi.getSenha()));
	    attributes.add(new LDAPAttribute("pwdAttribute", "userPassword"));
	    attributes.add(new LDAPAttribute("pwdPolicySubentry", pessoaWifi.getValidade()));


	    LDAPConnection conn = new LDAPConnection();
		try {
			conn.connect("10.3.226.126",389);
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

	@Override
	public void update(PessoaWifi pessoaWifi) {
		LDAPConnection conn = new LDAPConnection();
		//(String base, int scope, String filter, String[] attrs, boolean typesOnly)
		//conn.search("uid=*,ou=802.1x,dc=ufrn,dc=br", 0, , arg3, arg4);
		LDAPAttributeSet attributes = new LDAPAttributeSet();


	}

	@Override
	public void delete(String uid) {
		// TODO Auto-generated method stub

	}

	@Override
	public PessoaWifi find(String uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PessoaWifi> findAll() {


		String[] attrIDs = {"sn", "telephonenumber", "golfhandicap", "mail"};
		return null;
	}

	@Override
	public boolean login(PessoaWifi pessoaWifi) {
		LDAPConnection conn = new LDAPConnection();
		boolean estado = false;
		try {
			conn.connect("10.3.226.126",389);
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

	@Override
	public void logout() throws LDAPException {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		LDAPConnection conexao = (LDAPConnection) session.getAttribute("ldapWifi");
		conexao.disconnect();
		session.invalidate();



	}

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
