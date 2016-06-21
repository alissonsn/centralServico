package modelo;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;

import com.novell.ldap.LDAPException;
import entidades.Owncloud;

public interface IOwncloud {
	
	
	
	public boolean login(Owncloud owncloud);
	
	
	public void logout() throws LDAPException;

	
	public void create(Owncloud owncloud);
	
	
	public Owncloud listAbobora(Owncloud owncloud) throws UnsupportedEncodingException, ParseException;
	
	
	public ArrayList<Owncloud> findAll() throws UnsupportedEncodingException, ParseException;
	
	
	public Owncloud poruid(String uid) throws UnsupportedEncodingException, ParseException;
	
	
	public void migrate(Owncloud owncloud) throws UnsupportedEncodingException, ParseException;


}