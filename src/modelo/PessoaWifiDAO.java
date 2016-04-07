package modelo;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.novell.ldap.LDAPException;

import entidades.PessoaWifi;

public interface PessoaWifiDAO {
	public boolean login(PessoaWifi pessoaWifi);
	public void logout() throws LDAPException;
	public boolean isValidate();

	public void create(PessoaWifi pessoaWifi);
	public void update(PessoaWifi pessoaWifi);
	public void delete(String uid);
	public ArrayList<PessoaWifi> findAll() throws UnsupportedEncodingException, ParseException;

}
