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

import entidades.PessoaDns;
import entidades.PessoaWifi;

public class PessoaDnsDAOImpl implements PessoaDnsDAO{



	@Override
	public boolean login(PessoaDns pessoaDNs) {
		LDAPConnection conn = new LDAPConnection();
		boolean estado = false;
		try {
			conn.connect("10.3.226.126",389);
			String baseAdmin  = "uid="+ pessoaDNs.getUid()+ ",ou=admin,ou=dns,dc=ufrn,dc=br";
			conn.bind(LDAPConnection.LDAP_V3, baseAdmin, pessoaDNs.getSenha().getBytes());
			if (conn.isBound()) {
				FacesContext fc = FacesContext.getCurrentInstance();
				HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
				session.setAttribute("usuarioDns", pessoaDNs.getUid());
				session.setAttribute("senhaDns", pessoaDNs.getSenha());
				session.setAttribute("ldapDns", conn);
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
		LDAPConnection conexao = (LDAPConnection) session.getAttribute("ldapDNs");
		conexao.disconnect();
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
}
