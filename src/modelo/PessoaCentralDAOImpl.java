package modelo;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPException;

import entidades.PessoaCentral;

public class PessoaCentralDAOImpl implements PessoaCentralDAO{

	@Override
	public boolean login(PessoaCentral pessoaCentral) {
		LDAPConnection conn = new LDAPConnection();
		boolean estado = false;
		try {
			conn.connect("10.3.226.126",389);
			String baseAdmin  = "uid="+ pessoaCentral.getUid()+ ",ou=admin,ou=802.1x,dc=ufrn,dc=br";
			conn.bind(LDAPConnection.LDAP_V3, baseAdmin, pessoaCentral.getSenha().getBytes());
			if (conn.isBound()) {
				FacesContext fc = FacesContext.getCurrentInstance();
				HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
				session.setAttribute("usuarioCentral", pessoaCentral.getUid());
				session.setAttribute("senhaCentral", pessoaCentral.getSenha());
				session.setAttribute("ldap", conn);
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
		session.removeAttribute("usuarioCentral");
		session.removeAttribute("senhaCentral");
		session.invalidate();
	}


	@Override
	public boolean isValidate() {
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = (HttpSession) req.getSession();
		String usuario = (String) session.getAttribute("usuarioCentral");
		String senha = (String) session.getAttribute("senhaCentral");
		boolean pagina = false;
		if (usuario != null && senha != null) {
			pagina = true;
		}else{
			pagina = false;
		}
		return pagina;
	}

}
