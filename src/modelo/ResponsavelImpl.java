package modelo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import modelo.IResponsavel;
import entidades.Responsavel;

public class ResponsavelImpl implements IResponsavel{
	private Session sessao;

	public ResponsavelImpl(Session sessao){
		this.sessao = sessao;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Responsavel> listar() {
		return sessao.createCriteria(Responsavel.class).addOrder(Order.asc("nome")).list();
	}

	@Override
	public Responsavel porCodigo(Integer codigo) {
		return (Responsavel) sessao.get(Responsavel.class, codigo);
	}

	@Override
	public Responsavel salvar(Responsavel responsavel) {
		return (Responsavel) sessao.merge(responsavel);
	}

	@Override
	public void remover(Responsavel responsavel) {
		this.sessao.delete(responsavel);
	}

	@Override
	public void editar(Responsavel responsavel) {
		this.sessao.update(responsavel);
	}

}