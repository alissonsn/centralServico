package modelo;

import java.util.List;

import entidades.IpResponsavel;
import entidades.Responsavel;

/** Interface que contém as assinaturas de metodos das Responsaveis de Ti dos setores.
*
* @author silas
*
*/

public interface IIpResponsavel {
	
	/** Metodo que consulta todas os responsaveis cadastrados.
	 *
	 * @return List<IpResponsavel>, Contendo todas as pesoas cadastradas.
	 */
	public List<IpResponsavel> listar();
	
	/** Metodo que consulta Responsavel especifico por seu codigo de identificação.
	 * @param codigo, codigo de identificação do responsavel da ti no setor. 
	 * @return IpResponsavel, retorna responsavel cadastrado.
	 */
	public IpResponsavel porCodigo(Integer codigo);
	
	/** Metodo que cria IpResponsavel.
	 * @param ipresponsavel, requer objeto ipresponsavel para sua criação.
	 * @return IpResponsavel, retorna a ipresponsavel cadastrada.
	 */
	public IpResponsavel salvar(IpResponsavel ipresponsavel);
	
	/** Metodo que remove ipresponsavel.
	 * @param ipresponsavel, requer objeto ipresponsavel para sua remoção.
	 */
	public void remover(IpResponsavel ipresponsavel);
	
	/** Metodo que atualiza ipresponsavel.
	 * @param ipresponsavel, requer objeto ipresponsavel para sua atualização.
	 */
	public void editar(IpResponsavel ipresponsavel);
}
