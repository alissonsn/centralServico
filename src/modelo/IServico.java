package modelo;

import java.util.List;

import entidades.Servico;

/** Interface que contém as assinaturas de metodos das serviço.
*
* @author silas
*
*/

public interface IServico {

	/** Metodo que consulta todas os servicos cadastrados.
	 *
	 * @return List<Servico>, Contendo todos os servicos cadastrados.
	 */
	public List<Servico> listar();
	
	/** Metodo que consulta servico especifico por seu codigo de identificação.
	 * @param codigo, codigo de identificação do servico. 
	 * @return Servico, retorna o servico cadastrado.
	 */
	public Servico porCodigo(Integer codigo);
	
	/** Metodo que cria servico.
	 * @param servico, requer objeto servico para sua criação.
	 * @return Servico, retorna o servico cadastrado.
	 */
	public Servico salvar(Servico servico);
	
	/** Metodo que remove servico.
	 * @param servico, requer objeto servico para sua remoção.
	 */
	public void remover(Servico servico);
	
	/** Metodo que atualiza servico.
	 * @param servico, requer objeto servico para sua atualização.
	 */
	public void editar(Servico servico);
}
