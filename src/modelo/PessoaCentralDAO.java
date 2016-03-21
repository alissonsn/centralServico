package modelo;

import com.novell.ldap.LDAPException;

import entidades.PessoaCentral;


public interface PessoaCentralDAO {
	public boolean login(PessoaCentral pessoaCentral);
	public void logout();
	public boolean isValidate();
}
