package modelo;

import java.util.List;

import entidades.Equipe;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import modelo.IEquipe;

/** Classe EquipeImpl que implementa a Interface das IEquipe, esta classe implementa crud de equipe..
*
* @author silas
*
*/

public class EquipeImpl implements IEquipe{
	//Atributo de sessao do hibernate
	private Session sessao;

	/**Construtor da desta classe que inicializa o atributo de sessao.
	*/
	public EquipeImpl(Session sessao){
		this.sessao = sessao;
	}
	
	/** Metodo que consulta todas as equipes cadastradas.
	 *
	 * @return List<Equipe>, Contendo todas as equipes cadastradas ordenadas por seu nome.
	 */
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Equipe> listar() {
		return sessao.createCriteria(Equipe.class).addOrder(Order.asc("nome")).list();
	}

	/** Metodo que consulta equipe especifica por seu codigo de identificação.
	 * @param codigo, codigo de identificação da equipe. 
	 * @return Equipe, retorna a equipe cadastrada.
	 */
	@Override
	public Equipe porCodigo(Integer codigo) {
		return (Equipe) sessao.get(Equipe.class, codigo);
	}

	/** Metodo que cria equipe.
	 * @param equipe, requer objeto equipe para sua criação.
	 * @return Equipe, retorna a equipe cadastrada.
	 */
	@Override
	public Equipe salvar(Equipe equipe) {
		return (Equipe) sessao.merge(equipe);
	}

	/** Metodo que remove equipe.
	 * @param equipe, requer objeto equipe para sua remoção.
	 */
	@Override
	public void remover(Equipe equipe) {
		this.sessao.delete(equipe);

	}

	/** Metodo que atualiza equipe.
	 * @param equipe, requer objeto equipe para sua atualização.
	 */
	@Override
	public void editar(Equipe equipe) {
		this.sessao.update(equipe);
	}
}