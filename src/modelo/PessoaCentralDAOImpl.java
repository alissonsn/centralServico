package modelo;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPException;

import entidades.PessoaCentral;

/** Classe PessoaCentralDAOImpl que implementa a Interface das PessoasCentralDAO, esta classe implementa os metodos da classe PessoaCentral.
*
* @author silas 
*/
public class PessoaCentralDAOImpl implements PessoaCentralDAO{

	/** Metodo de login num diretorio LDAP.
	 *  @param pessoaCentral
	 * @return boolean, retorna verdadeiro login sucesso falso login invalido.
	 */
	@Override
	public boolean login(PessoaCentral pessoaCentral) {
		LDAPConnection conn = new LDAPConnection();
		boolean estado = false;
		try {
			conn.connect("10.3.156.9",389);
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

	/** Metodo de logout num diretorio LDAP.
	 */
	@Override
	public void logout() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.removeAttribute("usuarioCentral");
		session.removeAttribute("senhaCentral");
		session.invalidate();
	}
}