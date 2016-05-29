package modelo;

import java.util.List;


import entidades.Setor;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

/** Classe SertorImpl que implementa a Interface ISetor, esta classe implementa crud da entidade Setor.
*
* @author silas
*
*/
public class SetorImpl implements ISetor{
	
	//Atributo de sessao do hibernate
	private Session sessao;

	/**Construtor da desta classe que inicializa o atributo de sessao.
	*/
	public SetorImpl(Session sessao){
		this.sessao = sessao;
	}

	/** Metodo que consulta todas os setores cadastrados.
	 *
	 * @return List<Setor>, Contendo todos os setores cadastrados ordenados por seu local.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Setor> listar() {
		return sessao.createCriteria(Setor.class).addOrder(Order.asc("local")).list();
	}

	/** Metodo que consulta setor especifico por seu codigo de identificação.
	 * @param codigo, codigo de identificação do setor. 
	 * @return Setor, retorna o setor cadastrado.
	 */
	@Override
	public Setor porCodigo(Integer codigo) {
		return (Setor) sessao.get(Setor.class, codigo);
	}

	/** Metodo que cria setor.
	 * @param setor, requer objeto setor para sua criação.
	 * @return Setor, retorna o setor cadastrado.
	 */
	@Override
	public Setor salvar(Setor setor) {
		return (Setor) sessao.merge(setor);
	}

	/** Metodo que remove setor.
	 * @param setor, requer objeto setor para sua remoção.
	 */
	@Override
	public void remover(Setor setor) {
		this.sessao.delete(setor);

	}

	/** Metodo que atualiza setor.
	 * @param setor, requer objeto setor para sua atualização.
	 */
	@Override
	public void editar(Setor setor) {
		this.sessao.update(setor);
	}
}