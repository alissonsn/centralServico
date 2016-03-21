package modelo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import entidades.SolFalha;

public class SolFalhaImpl implements ISolFalha{

	private Session sessao;

	public SolFalhaImpl(Session sessao){
		this.sessao = sessao;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<SolFalha> listar() {
		return sessao.createCriteria(SolFalha.class).addOrder(Order.asc("descricao")).list();
	}

	@Override
	public SolFalha porCodigo(Integer codigo) {
		return (SolFalha) sessao.get(SolFalha.class, codigo);
	}

	@Override
	public SolFalha salvar(SolFalha solFalha) {
		return (SolFalha) sessao.merge(solFalha);
	}

	@Override
	public void remover(SolFalha solFalha) {
			sessao.delete(solFalha);
	}

	@Override
	public void editar(SolFalha solFalha) {
			sessao.update(solFalha);
	}

}
