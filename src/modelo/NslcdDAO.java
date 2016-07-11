package modelo;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.novell.ldap.LDAPException;

import entidades.Nslcd;




public interface NslcdDAO {
	
	
	public boolean login(Nslcd nslcd);
	
	
	public void logout();
	
	
	public Nslcd listAbobora(Nslcd nslcd);
	
	
	public Nslcd poruid(String uid);


	public ArrayList<Nslcd> findAll(String sistemaOperacional);


	public void migrate(Nslcd nslcd, String sistemaOperacional, String flagAdmin) throws UnsupportedEncodingException, LDAPException;

	public List<String> procurarUsuario(Nslcd nslcd, String sistemaOperacional)
			throws UnsupportedEncodingException;
	
	
	public void delete(String sistemaOperacional, Nslcd nslcd) throws UnsupportedEncodingException, LDAPException;
	
}