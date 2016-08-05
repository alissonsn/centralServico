package modelo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import modelo.IResponsavel;
import entidades.Responsavel;

public class ResponsavelImpl implements IResponsavel{
	
	//Atributo de sessao do hibernate
	private Session sessao;

	/**Construtor da desta classe que inicializa o atributo de sessao.
	*/
	public ResponsavelImpl(Session sessao){
		this.sessao = sessao;
	}
	
	/** Metodo que consulta todas os responsaveis cadastrados.
	 *
	 * @return List<Responsavel>, Contendo todas os responsaveis cadastrados ordenados por seu nome.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Responsavel> listar() {
		return sessao.createCriteria(Responsavel.class).addOrder(Order.asc("nome")).list();
	}

	/** Metodo que consulta responsavel especifico por seu codigo de identificação.
	 * @param codigo, codigo de identificação do responsavel. 
	 * @return Responsavel, retorna o responsavel cadastrado.
	 */
	@Override
	public Responsavel porCodigo(Integer codigo) {
		return (Responsavel) sessao.get(Responsavel.class, codigo);
	}

	/** Metodo que cria responsavel.
	 * @param responsavel, requer objeto responsavel para sua criação.
	 * @return Responsavel, retorna o responsavel cadastrado.
	 */
	@Override
	public Responsavel salvar(Responsavel responsavel) {
		return (Responsavel) sessao.merge(responsavel);
	}

	/** Metodo que remove responsavel.
	 * @param responsavel, requer objeto responsavel para sua remoção.
	 */
	@Override
	public void remover(Responsavel responsavel) {
		this.sessao.delete(responsavel);
	}

	/** Metodo que atualiza responsavel.
	 * @param responsavel, requer objeto responsavel para sua atualização.
	 */
	@Override
	public void editar(Responsavel responsavel) {
		this.sessao.update(responsavel);
	}

}