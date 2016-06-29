package modelo;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.novell.ldap.LDAPException;

import entidades.PessoaDns;
import entidades.Registro;

/** Interface que contém as assinaturas de metodos de Registro.
*
* @author silas
*
*/

public interface RegistroDAO {
	
	/** Metodo que cria registro direto.
	 * @param registro, requer objeto registro para sua criação.
	 * @throws UnsupportedEncodingException 
	 * @throws LDAPException 
	 */
	public void createRegistroDireto(Registro registro) throws UnsupportedEncodingException, LDAPException;
	
	/** Metodo que cria registro reverso.
	 * @param registro, requer objeto registro para sua criação.
	 * @throws UnsupportedEncodingException 
	 * @throws LDAPException 
	 */
	public void createRegistroReverso(Registro registro) throws UnsupportedEncodingException, LDAPException;
	
	/** Metodo que remove registro.
	 * @param registro, requer objeto registro para sua remoção.
	 */
	public void delete(Registro registro) throws UnsupportedEncodingException, LDAPException;
	
	/** Metodo que consulta registro especifico por seu dominio. 
	 * @return Registro, Contendo registro cadastrado.
	 */
	public Registro find(String dominio);
	
	/** Metodo que consulta todas registro cadastrado. 
	 * @return List<Registro>, Contendo todos os registros cadastrados.
	 */
	public List<Registro> findAll();
	
	/** Metodo que consulta todos registros reversos cadastrados. 
	 * @return List<String'>, Contendo todos os registros reversos cadastrados.
	 */
	public List<String> listarRegistroReverso(Registro registro,
			String relativeDomainName, String zoneName)
			throws UnsupportedEncodingException;

	List<String> listarRegistroDireto(Registro registro)
			throws UnsupportedEncodingException;

	

}
