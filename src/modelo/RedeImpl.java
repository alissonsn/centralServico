package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import testes.BuscaRede;
import testes.ListaRede;
import entidades.Rede;

public class RedeImpl implements IRede {
	private Session sessao;

	// private List<Rede> lista = new ArrayList<Rede>();
	// private ListaRede lista2;

	public RedeImpl(Session sessao) {
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
		return sessao.createCriteria(Rede.class).addOrder(Order.asc("ip"))
				.list();
	}

	public List<Rede> Buscarede(Rede rede) {
		
		ListaRede listarRedes  = new ListaRede();
		List<Rede> lista = null;
		
		try {
			lista = listarRedes.ListaRede(rede.getHost(),"255.255.252.0");
			if(lista.isEmpty()){
				lista = listarRedes.ListaRede(rede.getHost(),"255.255.255.0");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;	
	}
}