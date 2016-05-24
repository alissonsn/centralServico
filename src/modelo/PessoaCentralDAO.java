package modelo;

import entidades.PessoaCentral;

/** Interface que contém as assinaturas de metodos da classe PessoaCentralDAOImpl.
*
* @author silas
*
*/

public interface PessoaCentralDAO {
	public boolean login(PessoaCentral pessoaCentral);
	public void logout();
	public boolean isValidate();
}