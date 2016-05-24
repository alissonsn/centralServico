package modelo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import entidades.Monitoramento;

/** Classe MonitoramentoImpl que implementa a Interface dos monitoramentos, esta classe implementa crud de monitoramento..
*
* @author silas
*
*/

public class MonitoramentoImpl implements IMonitoramento{
	private Session sessao;
	
	public MonitoramentoImpl(Session sessao) {
		this.sessao = sessao;
	}
	
	/** Metodo que consulta todos os monitoramentos cadastrados.
	 *
	 * @return List<Monitoramento>, Contendo todos os monitoramentos cadastrados ordenados por sua categoria.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Monitoramento> listar() {
		return sessao.createCriteria(Monitoramento.class).addOrder(Order.asc("categoria")).list();
	}

	
	/** Metodo que consulta monitoramento especifico por seu codigo de identificação.
	 * @param codigo, codigo de identificação do monitoramento. 
	 * @return Monitoramento, retorna o monitoramento cadastrado.
	 */
	@Override
	public Monitoramento porCodigo(Integer codigo) {
		return (Monitoramento) sessao.get(Monitoramento.class, codigo);
	}

	/** Metodo que cria monitoramento.
	 * @param monitoramento, requer objeto monitoramento para sua criação.
	 * @return Monitoamento, retorna o monitoramento cadastrado.
	 */
	@Override
	public Monitoramento salvar(Monitoramento monitoramento) {
		return (Monitoramento) sessao.merge(monitoramento);
	}

	/** Metodo que remove monitoramento.
	 * @param monitoramento, requer objeto monitoramento para sua remoção.
	 */
	@Override
	public void remover(Monitoramento monitoramento) {
		this.sessao.delete(monitoramento);
	}

	/** Metodo que atualiza monitoramento.
	 * @param monitoramento, requer objeto monitoramento para sua atuaização.
	 */
	@Override
	public void editar(Monitoramento monitoramento) {
		this.sessao.update(monitoramento);
	}

}