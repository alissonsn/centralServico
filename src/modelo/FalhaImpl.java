package modelo;

import java.util.List;

import entidades.Falha;


import org.hibernate.Session;
import org.hibernate.criterion.Order;

import modelo.IFalha;

public class FalhaImpl implements IFalha{
	private Session sessao;

	public FalhaImpl(Session sessao){
		this.sessao = sessao;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Falha> listar() {
		return sessao.createCriteria(Falha.class).addOrder(Order.asc("falha")).list();
	}

	@Override
	public Falha porCodigo(Integer codigo) {
		return (Falha) sessao.get(Falha.class, codigo);
	}

	@Override
	public Falha salvar(Falha falha) {
		return (Falha) sessao.merge(falha);
	}

	@Override
	public void remover(Falha falha) {
		this.sessao.delete(falha);
	}

	@Override
	public void editar(Falha falha) {
		this.sessao.update(falha);
	}

}