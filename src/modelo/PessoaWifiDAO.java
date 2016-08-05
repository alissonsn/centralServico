package modelo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;


import com.novell.ldap.LDAPException;

import entidades.PessoaWifi;

/** Interface que contém as assinaturas de metodos da classe PessoaWifi.
*
* @author silas
*
*/

public interface PessoaWifiDAO {
	
	/** Metodo de login num diretorio LDAP.
	 *  @param pessoaWifi
	 * @return boolean, retorna verdadeiro login sucesso falso login invalido.
	 */
	public boolean login(PessoaWifi pessoaWifi);
	
	/** Metodo de logout num diretorio LDAP.
	 * @throws IOException 
	 */
	public void logout() throws LDAPException, IOException;

	/** Metodo que cria pessoawifi.
	 * @param pessoawifi, requer objeto pessoawifi para sua criação.
	 */
	public void create(PessoaWifi pessoaWifi) throws UnsupportedEncodingException;
	
	/** Metodo que consulta todas as pessoas cadastradas na rede wifi visitantes num diretorio LDAP..
	 *
	 * @return List<PessoaWifi>, Contendo todas as pessoas cadastradas no diretorio LDAP.
	 */
	public ArrayList<PessoaWifi> findAll() throws UnsupportedEncodingException, ParseException;
	
	/** Metodo que verifica vencimento da conta.
	 *  @param hoje, pessoaWifi, data de hoje e a pessoa.
	 * @return boolean, retorna true se a conta ainda está ativa falso se esta expirada.
	 */
	boolean verificavencimento(Date hoje, PessoaWifi pessoaWifi);
}
