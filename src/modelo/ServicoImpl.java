package modelo;

import java.util.List;

import entidades.Servico;


import org.hibernate.Session;
import org.hibernate.criterion.Order;

import modelo.IServico;

public class ServicoImpl implements IServico{
	private Session sessao;

	public ServicoImpl(Session sessao){
		this.sessao = sessao;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Servico> listar() {
		return sessao.createCriteria(Servico.class).addOrder(Order.asc("nome")).list();
	}

	@Override
	public Servico porCodigo(Integer codigo) {
		return (Servico) sessao.get(Servico.class, codigo);
	}

	@Override
	public Servico salvar(Servico servico) {
		return (Servico) sessao.merge(servico);
	}

	@Override
	public void remover(Servico servico) {
		this.sessao.delete(servico);

	}

	@Override
	public void editar(Servico servico) {
		this.sessao.update(servico);
	}

}
