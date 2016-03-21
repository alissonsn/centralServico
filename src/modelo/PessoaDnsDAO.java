package modelo;

import java.util.List;

import com.novell.ldap.LDAPException;

import entidades.PessoaDns;
import entidades.PessoaWifi;

public interface PessoaDnsDAO {
	public boolean login(PessoaDns pessoaDNs);
	public void logout() throws LDAPException;
	public boolean isValidate();
}
