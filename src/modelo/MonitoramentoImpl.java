package modelo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import entidades.Monitoramento;
import modelo.IMonitoramento;

public class MonitoramentoImpl implements IMonitoramento{
	private Session sessao;

	public MonitoramentoImpl(Session sessao) {
		this.sessao = sessao;
	}

	@Override
	public List<Monitoramento> listar() {
		return sessao.createCriteria(Monitoramento.class).addOrder(Order.asc("categoria")).list();
	}

	@Override
	public Monitoramento porCodigo(Integer codigo) {
		return (Monitoramento) sessao.get(Monitoramento.class, codigo);
	}

	@Override
	public Monitoramento salvar(Monitoramento monitoramento) {
		return (Monitoramento) sessao.merge(monitoramento);
	}

	@Override
	public void remover(Monitoramento monitoramento) {
		sessao.delete(monitoramento);
	}

	@Override
	public void editar(Monitoramento monitoramento) {
		sessao.update(monitoramento);
	}

}
