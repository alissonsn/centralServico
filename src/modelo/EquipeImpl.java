package modelo;

import java.util.List;

import entidades.Equipe;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import modelo.IEquipe;

public class EquipeImpl implements IEquipe{
	private Session sessao;

	public EquipeImpl(Session sessao){
		this.sessao = sessao;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Equipe> listar() {
		return sessao.createCriteria(Equipe.class).addOrder(Order.asc("nome")).list();
	}

	@Override
	public Equipe porCodigo(Integer codigo) {
		return (Equipe) sessao.get(Equipe.class, codigo);
	}

	@Override
	public Equipe salvar(Equipe equipe) {
		return (Equipe) sessao.merge(equipe);
	}

	@Override
	public void remover(Equipe equipe) {
		this.sessao.delete(equipe);

	}

	@Override
	public void editar(Equipe equipe) {
		this.sessao.update(equipe);
	}

}
