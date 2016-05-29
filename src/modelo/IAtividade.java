package modelo;

import java.util.List;

import entidades.Atividade;

/** Interface que contém as assinaturas de metodos das atividades.
*
* @author silas
*
*/

public interface IAtividade {
	/** Metodo que lista todas as atividades cadastradas.
	 * @return List<Atividade>, Lista que Contem todas as atividades cadastradas.
	 */
	public List<Atividade> listar();
	
	/** Metodo que consulta atividade especifica por seu codigo de identificação.
	 * @param codigo, codigo de identificação da atividade. 
	 * @return Atividade, retorna a atividade cadastrada.
	 */
	public Atividade porCodigo(Integer codigo);
	
	/** Metodo que cria atividade.
	 * @param atividade, requer objeto atividade para sua criação.
	 * @return Atividade, retorna a atividade cadastrada.
	 */
	public Atividade salvar(Atividade atividade);
	
	/** Metodo que remove atividade.
	 * @param atividade, requer objeto atividade para sua remoção.
	 */
	public void remover(Atividade atividade);
	
	/** Metodo que atualiza atividade.
	 * @param atividade, requer objeto atividade para sua atualização.
	 */
	public void editar(Atividade atividade);
}
