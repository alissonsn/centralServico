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
	
	/** Metodo de login num diretorio LDAP.
	 *  @param pessoaSSH
	 * @return boolean, retorna verdadeiro login sucesso falso login invalido.
	 */
	public boolean login(PessoaSSH pessoaSSH);
	
	/** Metodo de logout num diretorio LDAP.
	 */
	public void logout() throws LDAPException;
	
	/** Metodo isValidate.
	 * @return boolean, retorna true se o usuario ainda está conectado falso caso contrário.
	 */
	public boolean isValidate();

	/** Metodo que cria usuario dns.
	 * @param pessoaSSH, requer objeto pessoaSSH para sua criação.
	 */
	public void create(PessoaSSH pessoaSSH);
	
	/** Metodo que lista um usuario da base de um servidor ldap.
	 *  @param pessoaSSH, usuario que será listado.
	 *  @return PessoaSSH, retorna o usuario especificado.
	 */
	public PessoaSSH listAbobora(PessoaSSH pessoaSSH) throws UnsupportedEncodingException, ParseException;
	
	/** Metodo que lista todos os usuario da base de um servidor ldap.
	 *  @return ArrayList<PessoaSSH>, retorna a lista usuarios da base ssh.
	 */
	public ArrayList<PessoaSSH> findAll() throws UnsupportedEncodingException, ParseException;
	
	/** Metodo que lista o usuario por uid.
	 *  @param uid, uid de usuario que será listado.
	 *  @return PessoaSSH, retorna o usuario especificado.
	 */
	public PessoaSSH poruid(String uid) throws UnsupportedEncodingException, ParseException;
	
	/** Metodo que migra um usuario da base de um servidor ldap para outro.
	 *  @param pessoaSSH, usuario que será migrado.
	 *  @see listAbobora, lista o usuario do outro servidor que será migrado.
	 */
	public void migrate(PessoaSSH pessoaSSH) throws UnsupportedEncodingException, ParseException;


}