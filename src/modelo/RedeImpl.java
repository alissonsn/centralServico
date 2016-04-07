package modelo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import entidades.Monitoramento;
import entidades.Rede;

public class RedeImpl implements IRede{
	private Session sessao;
	
	public RedeImpl(Session sessao){
		this.sessao = sessao;
	}
	
	@Override
	public Rede criar(Rede rede) {
		return (Rede) sessao.merge(rede);
		
	}

	@Override
	public void deletar(Rede rede) {
		sessao.delete(rede);
		
	}

	@Override
	public void atualizar(Rede rede) {
		sessao.update(rede);
		
	}

	@Override
	public Rede pesquisar(String ip) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Rede> listar() {
		return sessao.createCriteria(Rede.class).addOrder(Order.asc("ip")).list();
	}

}
