package modelo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.novell.ldap.LDAPException;

import entidades.PessoaWifi;

public interface PessoaWifiDAO {
	public boolean login(PessoaWifi pessoaWifi);
	public void logout() throws LDAPException;
	public boolean isValidate();
	
	public void create(PessoaWifi pessoaWifi);
	public void update(PessoaWifi pessoaWifi);
	public void delete(String uid);
	public PessoaWifi find(String uid);
	public List<PessoaWifi> findAll();
	
}
