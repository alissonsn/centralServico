package modelo;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;

import com.novell.ldap.LDAPException;

import entidades.PessoaSSH;
import entidades.PessoaWifi;

/** Interface que contém as assinaturas de metodos da classe PessoaSSHDAOImpl.
*
* @author silas
*
*/

public interface PessoaSSHDAO {
	public boolean login(PessoaSSH pessoaSSH);
	public void logout() throws LDAPException;
	public boolean isValidate();

	public void create(PessoaSSH pessoaSSH);
	public void update(PessoaSSH pessoaSSH);
	public void delete(String uid);
	public PessoaSSH listAbobora(PessoaSSH pessoaSSH) throws UnsupportedEncodingException, ParseException;
	public ArrayList<PessoaSSH> findAll() throws UnsupportedEncodingException, ParseException;
	public PessoaSSH poruid(String uid) throws UnsupportedEncodingException, ParseException;
	public void migrate(PessoaSSH pessoaSSH) throws UnsupportedEncodingException, ParseException;


}