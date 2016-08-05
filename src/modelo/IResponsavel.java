package modelo;

import java.util.List;

import entidades.Responsavel;

/** Interface que contém as assinaturas de metodos da classe responsavel.
*
* @author silas
*
*/

public interface IResponsavel {
	
	/** Metodo que consulta todas os responsaveis cadastrados.
	 *
	 * @return List<Responsavel>, Contendo todas os responsaveis cadastrados.
	 */
	public List<Responsavel> listar();
	
	/** Metodo que consulta responsavel especifico por seu codigo de identificação.
	 * @param codigo, codigo de identificação do responsavel. 
	 * @return Responsavel, retorna o responsavel cadastrado.
	 */
	public Responsavel porCodigo(Integer codigo);
	
	/** Metodo que cria responsavel.
	 * @param responsavel, requer objeto responsavel para sua criação.
	 * @return Responsavel, retorna o responsavel cadastrado.
	 */
	public Responsavel salvar(Responsavel responsavel);
	
	/** Metodo que remove responsavel.
	 * @param responsavel, requer objeto responsavel para sua remoção.
	 */
	public void remover(Responsavel responsavel);
	
	/** Metodo que atualiza responsavel.
	 * @param responsavel, requer objeto responsavel para sua atualização.
	 */
	public void editar(Responsavel responsavel);
}