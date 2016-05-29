package modelo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import entidades.Atividade;
import modelo.IAtividade;

/** Classe AtividadeImpl que implementa a Interface das atividades, esta classe implementa crud de atividade..
*
* @author silas
*
*/

public class AtividadeImpl implements IAtividade{
	
	//Atributo de sessao do hibernate
	private Session sessao;

	/**Construtor da desta classe que inicializa o atributo de sessao.
	*/
	public AtividadeImpl(Session sessao){
		this.sessao = sessao;
	}
	
	
	/** Metodo que consulta todas as atividades cadastradas.
	 *
	 * @return List<Atividade>, Contendo todas as atividades cadastradas ordenadas por sua definicao.
	 */
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Atividade> listar() {
		return sessao.createCriteria(Atividade.class).addOrder(Order.asc("definicao")).list();
	}

	
	/** Metodo que consulta atividade especifica por seu codigo de identificação.
	 * @param codigo, codigo de identificação da atividade. 
	 * @return Atividade, retorna a atividade cadastrada.
	 */
	
	@Override
	public Atividade porCodigo(Integer codigo) {
		return (Atividade) sessao.get(Atividade.class, codigo);
	}

	/** Metodo que cria atividade.
	 * @param atividade, requer objeto atividade para sua criação.
	 * @return Atividade, retorna a atividade cadastrada.
	 */
	
	@Override
	public Atividade salvar(Atividade atividade) {
		return (Atividade) sessao.merge(atividade);
	}

	/** Metodo que remove atividade.
	 * @param atividade, requer objeto atividade para sua remoção.
	 */
	
	@Override
	public void remover(Atividade atividade) {
		sessao.delete(atividade);
	}

	/** Metodo que atualiza atividade.
	 * @param atividade, requer objeto atividade para sua atualização.
	 */
	
	@Override
	public void editar(Atividade atividade) {
		sessao.update(atividade);
	}
}