package modelo;

import java.util.List;

import entidades.ReqInfo;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import modelo.IReqInfo;

/** Classe ReqInfoImpl que implementa a Interface das requisições de informações, esta classe implementa crud de reqinfo..
*
* @author silas
*
*/

public class ReqInfoImpl implements IReqInfo{
	
	//Atributo de sessao do hibernate
	private Session sessao;

	/**Construtor da desta classe que inicializa o atributo de sessao.
	*/
	public ReqInfoImpl(Session sessao){
		this.sessao = sessao;
	}
	
	/** Metodo que consulta todas as requisições de informações cadastradas.
	 *
	 * @return List<ReqInfo>, Contendo todas as requisições cadastradas ordenadas por sua categoria.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ReqInfo> listar() {
		return sessao.createCriteria(ReqInfo.class).addOrder(Order.asc("categoria")).list();
	}

	/** Metodo que consulta requisição especifica por seu codigo de identificação.
	 * @param codigo, codigo de identificação da rede. 
	 * @return ReqInfo, retorna a requisição cadastrada.
	 */
	@Override
	public ReqInfo porCodigo(Integer codigo) {
		return (ReqInfo) sessao.get(ReqInfo.class, codigo);
	}

	/** Metodo que cria requisição.
	 * @param reqInfo, requer objeto reqInfo para sua criação.
	 * @return ReqInfo, retorna a requisição cadastrada.
	 */
	@Override
	public ReqInfo salvar(ReqInfo reqInfo) {
		return (ReqInfo) sessao.merge(reqInfo);
	}

	/** Metodo que remove requisição.
	 * @param reqInfo, requer objeto reqInfo para sua remoção.
	 */
	@Override
	public void remover(ReqInfo reqInfo) {
		this.sessao.delete(reqInfo);

	}

	/** Metodo que atualiza reqInfo.
	 * @param reqInfo, requer objeto reqInfo para sua atualização.
	 */
	@Override
	public void editar(ReqInfo reqInfo) {
		this.sessao.update(reqInfo);
	}
}