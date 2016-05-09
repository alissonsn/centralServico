package modelo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import entidades.IpResponsavel;
import entidades.Responsavel;

public class IpResponsavelImpl implements IIpResponsavel{
	private Session sessao;

	public IpResponsavelImpl(Session sessao){
		this.sessao = sessao;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<IpResponsavel> listar() {
		return sessao.createCriteria(IpResponsavel.class).addOrder(Order.asc("nomeServidor")).list();
	}

	@Override
	public IpResponsavel porCodigo(Integer codigo) {
		return (IpResponsavel) sessao.get(IpResponsavel.class, codigo);
	}

	@Override
	public IpResponsavel salvar(IpResponsavel ipresponsavel) {
		return (IpResponsavel) sessao.merge(ipresponsavel);
	}

	@Override
	public void remover(IpResponsavel ipresponsavel) {
		this.sessao.delete(ipresponsavel);
	}

	@Override
	public void editar(IpResponsavel ipresponsavel) {
		this.sessao.update(ipresponsavel);
	}

}