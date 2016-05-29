package modelo;

import java.util.List;


import entidades.SolFalha;

/** Interface que contém as assinaturas de metodos das soluções de falhas.
*
* @author silas
*
*/

public interface ISolFalha {
	
	/** Metodo que consulta todas os solução de falha cadastradas.
	 *
	 * @return List<SolFalha>, Contendo todos as soluções de falhas cadastrados ordenados por sua descrição.
	 */
	public List<SolFalha> listar();
	
	/** Metodo que consulta solução de falha especifico por seu codigo de identificação.
	 * @param codigo, codigo de identificação do solução de falha. 
	 * @return SolFalha, retorna solução de falha cadastrada.
	 */
	public SolFalha porCodigo(Integer codigo);
	
	/** Metodo que cria solução de falha.
	 * @param solFalha, requer objeto solFalha para sua criação.
	 * @return SolFalha, retorna o solução da falha cadastrado.
	 */
	public SolFalha salvar(SolFalha solFalha);
	
	/** Metodo que remove solFalha.
	 * @param solFalha, requer objeto solFalha para sua remoção.
	 */
	public void remover(SolFalha solFalha);
	
	/** Metodo que atualiza solFalha.
	 * @param solFalha, requer objeto solFalha para sua atualização.
	 */
	public void editar(SolFalha solFalha);
}