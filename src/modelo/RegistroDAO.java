package modelo;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.novell.ldap.LDAPException;

import entidades.PessoaDns;
import entidades.Registro;


public interface RegistroDAO {
	public void createRegistroDireto(Registro registro);
	public void createRegistroReverso(Registro registro);
	public void update(Registro registro);
	public void delete(Registro registro) throws UnsupportedEncodingException, LDAPException;
	public Registro find(String dominio);
	public List<Registro> findAll();
	public List<String> listarRegistroReverso(Registro registro)
			throws UnsupportedEncodingException;

}
