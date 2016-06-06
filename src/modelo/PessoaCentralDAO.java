package modelo;

import entidades.PessoaCentral;

/** Interface que contém as assinaturas de metodos da classe PessoaCentralDAOImpl.
*
* @author silas
*
*/

public interface PessoaCentralDAO {
	
	/** Metodo de login.
	 *@param pessoaCentral, contém objeto para fazer o login.
	 * @return boolean, retorna true se o login der sucesso e falso caso contrario.
	 */
	public boolean login(PessoaCentral pessoaCentral);
	
	/** Metodo de logout.
	 */
	public void logout();
}