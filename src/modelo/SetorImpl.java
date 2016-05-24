package modelo;

import java.util.List;


import entidades.Setor;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

public class SetorImpl implements ISetor{
	private Session sessao;

	public SetorImpl(Session sessao){
		this.sessao = sessao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Setor> listar() {
		return sessao.createCriteria(Setor.class).addOrder(Order.asc("local")).list();
	}

	@Override
	public Setor porCodigo(Integer codigo) {
		return (Setor) sessao.get(Setor.class, codigo);
	}

	@Override
	public Setor salvar(Setor setor) {
		return (Setor) sessao.merge(setor);
	}

	@Override
	public void remover(Setor setor) {
		this.sessao.delete(setor);

	}

	@Override
	public void editar(Setor setor) {
		this.sessao.update(setor);
	}

}
