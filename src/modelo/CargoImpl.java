package modelo;

import java.util.List;


import entidades.Cargo;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

/** Classe AtividadeImpl que implementa a Interface dos cargos, esta classe implementa crud de cargo..
*
* @author silas
*
*/

public class CargoImpl implements ICargo{
	
	//Atributo de sessao do hibernate
	private Session sessao;
	
	/**Construtor da desta classe que inicializa o atributo de sessao.
	*/
	public CargoImpl(Session sessao){
		this.sessao = sessao;
	}

	/** Metodo que consulta todos os cargos cadastrados.
	 *
	 * @return List<Cargo>, Contendo todos os cargos cadastrados ordenados por seu nome.
	 */
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Cargo> listar() {
		return sessao.createCriteria(Cargo.class).addOrder(Order.asc("nome")).list();
	}

	/** Metodo que consulta cargo especificoo por seu codigo de identificação.
	 * @param codigo, codigo de identificação da cargo. 
	 * @return Cargo, retorna o cargo cadastrado.
	 */
	@Override
	public Cargo porCodigo(Integer codigo) {
		return (Cargo) sessao.get(Cargo.class, codigo);
	}

	/** Metodo que cria um cargo.
	 * @param cargo, requer objeto cargo para sua criação.
	 * @return Cargo, retorna a cargo cadastrado.
	 */
	
	@Override
	public Cargo salvar(Cargo cargo) {
		return (Cargo) sessao.merge(cargo);
	}

	/** Metodo que remove cargo.
	 * @param cargo, requer objeto cargo para sua remoção.
	 */
	
	@Override
	public void remover(Cargo cargo) {
		this.sessao.delete(cargo);

	}

	/** Metodo que atualiza cargo.
	 * @param cargo, requer objeto cargo para sua atualização.
	 */
	@Override
	public void editar(Cargo cargo) {
		this.sessao.update(cargo);
	}

}