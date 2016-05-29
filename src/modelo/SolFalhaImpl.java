package modelo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import entidades.SolFalha;

/** Classe SolFalhaImpl que implementa a Interface ISolFalha, esta classe implementa crud da entidade SolFalha.
*
* @author silas
*
*/
public class SolFalhaImpl implements ISolFalha{

	//Atributo de sessao do hibernate
	private Session sessao;

	/**Construtor da desta classe que inicializa o atributo de sessao.
	*/
	public SolFalhaImpl(Session sessao){
		this.sessao = sessao;
	}
	
	/** Metodo que consulta todas os solução de falha cadastradas.
	 *
	 * @return List<SolFalha>, Contendo todos as soluções de falhas cadastrados ordenados por sua descrição.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SolFalha> listar() {
		return sessao.createCriteria(SolFalha.class).addOrder(Order.asc("descricao")).list();
	}

	/** Metodo que consulta solução de falha especifico por seu codigo de identificação.
	 * @param codigo, codigo de identificação do solução de falha. 
	 * @return SolFalha, retorna solução de falha cadastrada.
	 */
	@Override
	public SolFalha porCodigo(Integer codigo) {
		return (SolFalha) sessao.get(SolFalha.class, codigo);
	}

	/** Metodo que cria solução de falha.
	 * @param solFalha, requer objeto solFalha para sua criação.
	 * @return SolFalha, retorna o solução da falha cadastrado.
	 */
	@Override
	public SolFalha salvar(SolFalha solFalha) {
		return (SolFalha) sessao.merge(solFalha);
	}

	/** Metodo que remove solFalha.
	 * @param solFalha, requer objeto solFalha para sua remoção.
	 */
	@Override
	public void remover(SolFalha solFalha) {
			sessao.delete(solFalha);
	}

	/** Metodo que atualiza solFalha.
	 * @param solFalha, requer objeto solFalha para sua atualização.
	 */
	@Override
	public void editar(SolFalha solFalha) {
			sessao.update(solFalha);
	}
}