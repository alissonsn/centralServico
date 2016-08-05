package modelo;

import java.util.List;

import entidades.Rede;

import org.hibernate.Session;
import org.hibernate.criterion.Order;


/** Classe RedeImpl que implementa a Interface das redes, esta classe implementa crud de ede..
*
* @author silas
*
*/
public class RedeImpl implements IRede{
	
	//Atributo de sessao do hibernate
	private Session sessao;

	/**Construtor da desta classe que inicializa o atributo de sessao.
	*/
	public RedeImpl(Session sessao){
		this.sessao = sessao;
	}

	/** Metodo que consulta todas as redes cadastradas.
	 *
	 * @return List<Rede>, Contendo todas as redes cadastradas ordenadas por seu ip.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Rede> listar() {
		return sessao.createCriteria(Rede.class).addOrder(Order.asc("ip")).list();
	}

	/** Metodo que consulta rede especifica por seu codigo de identificação.
	 * @param codigo, codigo de identificação da rede. 
	 * @return Rede, retorna a rede cadastrada.
	 */
	@Override
	public Rede porCodigo(Integer codigo) {
		return (Rede) sessao.get(Rede.class, codigo);
	}

	/** Metodo que cria rede.
	 * @param rede, requer objeto rede para sua criação.
	 * @return Rede, retorna a rede cadastrada.
	 */
	@Override
	public Rede salvar(Rede rede) {
		return (Rede) sessao.merge(rede);
	}

	/** Metodo que remove atividade.
	 * @param atividade, requer objeto atividade para sua remoção.
	 */
	@Override
	public void remover(Rede rede) {
		this.sessao.delete(rede);

	}

	/** Metodo que atualiza rede.
	 * @param rede, requer objeto rede para sua atualização.
	 */
	@Override
	public void editar(Rede rede) {
		this.sessao.update(rede);
	}

}
