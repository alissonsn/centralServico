package modelo;

import com.novell.ldap.LDAPException;
import entidades.PessoaDns;


public interface PessoaDnsDAO {
	public boolean login(PessoaDns pessoaDNs);
	public void logout() throws LDAPException;
	public boolean isValidate();
}
