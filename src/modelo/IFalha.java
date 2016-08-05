package modelo;

import java.util.List;

import entidades.Falha;

/** Interface que contém as assinaturas de metodos das falhas.
*
* @author silas
*
*/

public interface IFalha {
	
	/** Metodo que consulta todas as falhas cadastradas.
	 *
	 * @return List<Falha>, Contendo todas as falhas cadastradas.
	 */
	public List<Falha> listar();
	
	/** Metodo que consulta falha especifica por seu codigo de identificação.
	 * @param codigo, codigo de identificação da falha. 
	 * @return Falha, retorna a falha cadastrada.
	 */
	public Falha porCodigo(Integer codigo);
	
	/** Metodo que cria falha.
	 * @param falha, requer objeto falha para sua criação.
	 * @return Falha, retorna a falha cadastrada.
	 */
	public Falha salvar(Falha falha);
	
	/** Metodo que remove a falha.
	 * @param falha, requer objeto falha para sua remoção.
	 */
	public void remover(Falha falha);
	
	/** Metodo que atualiza falha.
	 * @param falha, requer objeto falha para sua atualização.
	 */
	public void editar(Falha falha);
}
