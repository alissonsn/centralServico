package modelo;

import java.util.List;

import entidades.Equipe;
import entidades.Rede;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import modelo.IEquipe;

public class RedeImpl implements IRede{
	private Session sessao;

	public RedeImpl(Session sessao){
		this.sessao = sessao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Rede> listar() {
		return sessao.createCriteria(Rede.class).addOrder(Order.asc("ip")).list();
	}

	@Override
	public Rede porCodigo(Integer codigo) {
		return (Rede) sessao.get(Rede.class, codigo);
	}

	@Override
	public Rede salvar(Rede rede) {
		return (Rede) sessao.merge(rede);
	}

	@Override
	public void remover(Rede rede) {
		this.sessao.delete(rede);

	}

	@Override
	public void editar(Rede rede) {
		this.sessao.update(rede);
	}

}
