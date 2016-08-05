package modelo;

import java.util.List;

import entidades.Rede;

/** Interface que contém as assinaturas de metodos de rede.
*
* @author silas
*
*/

public interface IRede {
	
	/** Metodo que lista todas as redes cadastradas.
	 *
	 * @return List<Rede>, Contendo todas as redes cadastradas.
	 */
	public List<Rede> listar();
	
	/** Metodo que lista determinada rede especificada por seu codigo identificador.
	 *
	 * @return Rede, Retorna a rede especificada.
	 */
	public Rede porCodigo(Integer codigo);
	
	/** Metodo que cadastrada uma rede.
	 *
	 *@param rede, objeto rede que será persistido.
	 * @return List<Atividade>, Contendo todas as atividades cadastradas ordenadas por sua definicao.
	 */
	public Rede salvar(Rede rede);
	
	/** Metodo que remove uma rede.
	 *
	 *@param rede, objeto rede que será removido.
	 */
	public void remover(Rede rede);
	
	/** Metodo que atualiza uma rede.
	 *
	 *@param rede, objeto rede que será persistido.
	 */
	public void editar(Rede rede);
}