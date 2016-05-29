package modelo;

import java.util.List;


import entidades.Monitoramento;

/** Interface que contém as assinaturas de metodos de monitoramento.
*
* @author silas
*
*/

public interface IMonitoramento {
	
	/** Metodo que consulta todos os monitoramentos cadastrados.
	 *
	 * @return List<Monitoramento>, Contendo todos os monitoramentos cadastrados.
	 */
	public List<Monitoramento> listar();
	
	/** Metodo que consulta monitoramento especifico por seu codigo de identificação.
	 * @param codigo, codigo de identificação do monitoramento. 
	 * @return Monitoramento, retorna o monitoramento cadastrado.
	 */
	public Monitoramento porCodigo(Integer codigo);
	
	/** Metodo que cria monitoramento.
	 * @param monitoramento, requer objeto monitoramento para sua criação.
	 * @return Monitoamento, retorna o monitoramento cadastrado.
	 */
	public Monitoramento salvar(Monitoramento monitoramento);
	
	/** Metodo que remove monitoramento.
	 * @param monitoramento, requer objeto monitoramento para sua remoção.
	 */
	public void remover(Monitoramento monitoramento);
	
	/** Metodo que atualiza monitoramento.
	 * @param monitoramento, requer objeto monitoramento para sua atuaização.
	 */
	public void editar(Monitoramento monitoramento);
}
