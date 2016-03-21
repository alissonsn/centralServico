package modelo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import entidades.Atividade;
import modelo.IAtividade;

public class AtividadeImpl implements IAtividade{
	private Session sessao;

	public AtividadeImpl(Session sessao){
		this.sessao = sessao;
	}

	@Override
	public List<Atividade> listar() {
		return sessao.createCriteria(Atividade.class).addOrder(Order.asc("definicao")).list();
	}

	@Override
	public Atividade porCodigo(Integer codigo) {
		return (Atividade) sessao.get(Atividade.class, codigo);
	}

	@Override
	public Atividade salvar(Atividade atividade) {
		return (Atividade) sessao.merge(atividade);
	}

	@Override
	public void remover(Atividade atividade) {
		sessao.delete(atividade);
	}

	@Override
	public void editar(Atividade atividade) {
		sessao.update(atividade);
	}

}
