package modelo;

import java.util.List;

import entidades.Servico;


import org.hibernate.Session;
import org.hibernate.criterion.Order;

import modelo.IServico;

/** Classe ServicoImpl que implementa a Interface IServico, esta classe implementa crud de servico..
*
* @author silas
*
*/
public class ServicoImpl implements IServico{
	
	//Atributo de sessao do hibernate
	private Session sessao;

	/**Construtor da desta classe que inicializa o atributo de sessao.
	*/
	public ServicoImpl(Session sessao){
		this.sessao = sessao;
	}
	
	/** Metodo que consulta todas os servicos cadastrados.
	 *
	 * @return List<Servico>, Contendo todos os servicos cadastrados ordenados por seu nome.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Servico> listar() {
		return sessao.createCriteria(Servico.class).addOrder(Order.asc("nome")).list();
	}

	/** Metodo que consulta servico especifico por seu codigo de identificação.
	 * @param codigo, codigo de identificação do servico. 
	 * @return Servico, retorna o servico cadastrado.
	 */
	@Override
	public Servico porCodigo(Integer codigo) {
		return (Servico) sessao.get(Servico.class, codigo);
	}

	/** Metodo que cria servico.
	 * @param servico, requer objeto servico para sua criação.
	 * @return Servico, retorna o servico cadastrado.
	 */
	@Override
	public Servico salvar(Servico servico) {
		return (Servico) sessao.merge(servico);
	}

	/** Metodo que remove servico.
	 * @param servico, requer objeto servico para sua remoção.
	 */
	@Override
	public void remover(Servico servico) {
		this.sessao.delete(servico);

	}

	/** Metodo que atualiza servico.
	 * @param servico, requer objeto servico para sua atualização.
	 */
	@Override
	public void editar(Servico servico) {
		this.sessao.update(servico);
	}
}