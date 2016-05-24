package modelo;

import java.util.List;

import entidades.Falha;


import org.hibernate.Session;
import org.hibernate.criterion.Order;

import modelo.IFalha;

/** Classe FalhaImpl que implementa a Interface das IFalha, esta classe implementa crud de falha..
*
* @author silas
*
*/

public class FalhaImpl implements IFalha{
	private Session sessao;

	public FalhaImpl(Session sessao){
		this.sessao = sessao;
	}
	
	/** Metodo que consulta todas as falhas cadastradas.
	 *
	 * @return List<Falha>, Contendo todas as falhas cadastradas ordenadas por sua falha.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Falha> listar() {
		return sessao.createCriteria(Falha.class).addOrder(Order.asc("falha")).list();
	}

	/** Metodo que consulta falha especifica por seu codigo de identificação.
	 * @param codigo, codigo de identificação da falha. 
	 * @return Falha, retorna a falha cadastrada.
	 */
	@Override
	public Falha porCodigo(Integer codigo) {
		return (Falha) sessao.get(Falha.class, codigo);
	}

	/** Metodo que cria falha.
	 * @param falha, requer objeto falha para sua criação.
	 * @return Falha, retorna a falha cadastrada.
	 */
	@Override
	public Falha salvar(Falha falha) {
		return (Falha) sessao.merge(falha);
	}

	/** Metodo que remove a falha.
	 * @param falha, requer objeto falha para sua remoção.
	 */
	@Override
	public void remover(Falha falha) {
		this.sessao.delete(falha);
	}

	/** Metodo que atualiza falha.
	 * @param falha, requer objeto falha para sua atualização.
	 */
	@Override
	public void editar(Falha falha) {
		this.sessao.update(falha);
	}

}