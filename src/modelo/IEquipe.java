package modelo;

import java.util.List;

import entidades.Equipe;

/** Interface que contém as assinaturas de metodos das equipes.
*
* @author silas
*
*/

public interface IEquipe {
	
	/** Metodo que consulta todas as equipes cadastradas.
	 *
	 * @return List<Equipe>, Contendo todas as equipes cadastradas ordenadas por seu nome.
	 */
	public List<Equipe> listar();
	
	/** Metodo que consulta equipe especifica por seu codigo de identificação.
	 * @param codigo, codigo de identificação da equipe. 
	 * @return Equipe, retorna a equipe cadastrada.
	 */
	public Equipe porCodigo(Integer codigo);
	
	/** Metodo que cria equipe.
	 * @param equipe, requer objeto equipe para sua criação.
	 * @return Equipe, retorna a equipe cadastrada.
	 */
	public Equipe salvar(Equipe equipe);
	
	/** Metodo que remove equipe.
	 * @param equipe, requer objeto equipe para sua remoção.
	 */
	public void remover(Equipe equipe);
	
	/** Metodo que atualiza equipe.
	 * @param equipe, requer objeto equipe para sua atualização.
	 */
	public void editar(Equipe equipe);
}
