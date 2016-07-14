package modelo;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;

import com.novell.ldap.LDAPException;
import entidades.PessoaDns;

/** Interface que contém as assinaturas de metodos da classe PessoaDnsDAOImpl.
*
* @author silas
*
*/

public interface PessoaDnsDAO {
	
	/** Metodo de login.
	 *@param pessoaDNs, contém objeto para fazer o login.
	 * @return boolean, retorna true se o login der sucesso e falso caso contrario.
	 */
	public boolean login(PessoaDns pessoaDNs);
	
	/** Metodo de logout.
	 */
	public void logout() throws LDAPException;
	
	/** Metodo que cria usuario dns.
	 * @param pessoaDNS, requer objeto pessoaDNS para sua criação.
	 */
	public void create(PessoaDns pessoaDNS);
	
	/** Metodo que consulta todas os usuario cadastrado.
	 *
	 * @return List<PessoaDns>, Contendo todos os usuarios cadastrados.
	 */
	public ArrayList<PessoaDns> findAll() throws UnsupportedEncodingException, ParseException;
	
	/** Metodo que migra usuario de uma base para outra.
	 *@param pessoaDNS, requer objeto par migração
	 */
	public void migrate(PessoaDns pessoaDNS) throws UnsupportedEncodingException;
}