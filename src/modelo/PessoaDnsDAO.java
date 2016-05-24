package modelo;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;

import com.novell.ldap.LDAPException;
import entidades.PessoaDns;
import entidades.PessoaSSH;

/** Interface que contém as assinaturas de metodos da classe PessoaDnsDAOImpl.
*
* @author silas
*
*/

public interface PessoaDnsDAO {
	public boolean login(PessoaDns pessoaDNs);
	public void logout() throws LDAPException;
	public boolean isValidate();
	
	public void create(PessoaDns pessoaDNS);
	public ArrayList<PessoaDns> findAll() throws UnsupportedEncodingException, ParseException;
	public void migrate(PessoaDns pessoaDNS) throws UnsupportedEncodingException;
	
	
}