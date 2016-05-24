package modelo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import entidades.IpResponsavel;
import entidades.Responsavel;

/** Classe IpResponsavelImpl que implementa a Interface das IIpResponsavel, esta classe implementa crud de responsavel pela ti nos setores.
*
* @author silas
*
*/

public class IpResponsavelImpl implements IIpResponsavel{
	private Session sessao;

	public IpResponsavelImpl(Session sessao){
		this.sessao = sessao;
	}
	
	/** Metodo que consulta todas os responsaveis cadastrados.
	 *
	 * @return List<IpResponsavel>, Contendo todas as pesoas cadastradas ordenadas pelo servidor que elas tomam conta.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<IpResponsavel> listar() {
		return sessao.createCriteria(IpResponsavel.class).addOrder(Order.asc("nomeServidor")).list();
	}

	/** Metodo que consulta Responsavel especifico por seu codigo de identificação.
	 * @param codigo, codigo de identificação do responsavel da ti no setor. 
	 * @return IpResponsavel, retorna responsavel cadastrado.
	 */
	@Override
	public IpResponsavel porCodigo(Integer codigo) {
		return (IpResponsavel) sessao.get(IpResponsavel.class, codigo);
	}

	/** Metodo que cria IpResponsavel.
	 * @param ipresponsavel, requer objeto ipresponsavel para sua criação.
	 * @return IpResponsavel, retorna a ipresponsavel cadastrada.
	 */
	@Override
	public IpResponsavel salvar(IpResponsavel ipresponsavel) {
		return (IpResponsavel) sessao.merge(ipresponsavel);
	}

	/** Metodo que remove ipresponsavel.
	 * @param ipresponsavel, requer objeto ipresponsavel para sua remoção.
	 */
	@Override
	public void remover(IpResponsavel ipresponsavel) {
		this.sessao.delete(ipresponsavel);
	}

	/** Metodo que atualiza ipresponsavel.
	 * @param ipresponsavel, requer objeto ipresponsavel para sua atualização.
	 */
	@Override
	public void editar(IpResponsavel ipresponsavel) {
		this.sessao.update(ipresponsavel);
	}

}