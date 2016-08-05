package modelo;

import java.util.List;

import entidades.ReqInfo;

/** Interface que contém as assinaturas de metodos de requisição de informação.
*
* @author silas
*
*/

public interface IReqInfo {

	/** Metodo que consulta todas as requisições de informações cadastradas.
	 *
	 * @return List<ReqInfo>, Contendo todas as requisições cadastradas.
	 */
	public List<ReqInfo> listar();
	
	/** Metodo que consulta requisição especifica por seu codigo de identificação.
	 * @param codigo, codigo de identificação da rede. 
	 * @return ReqInfo, retorna a requisição cadastrada.
	 */
	public ReqInfo porCodigo(Integer codigo);
	
	/** Metodo que cria requisição.
	 * @param reqInfo, requer objeto reqInfo para sua criação.
	 * @return ReqInfo, retorna a requisição cadastrada.
	 */
	public ReqInfo salvar(ReqInfo reqInfo);
	
	/** Metodo que remove requisição.
	 * @param reqInfo, requer objeto reqInfo para sua remoção.
	 */
	public void remover(ReqInfo reqInfo);
	
	/** Metodo que atualiza reqInfo.
	 * @param reqInfo, requer objeto reqInfo para sua atualização.
	 */
	public void editar(ReqInfo reqInfo);
}