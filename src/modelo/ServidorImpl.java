package modelo;

import java.util.List;


import entidades.Servidor;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import modelo.IServidor;

public class ServidorImpl implements IServidor{
	private Session sessao;

	public ServidorImpl(Session sessao){
		this.sessao = sessao;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Servidor> listar() {
		return sessao.createCriteria(Servidor.class).addOrder(Order.asc("hostname")).list();
	}

	@Override
	public Servidor porCodigo(Integer codigo) {
		return (Servidor) sessao.get(Servidor.class, codigo);
	}

	@Override
	public Servidor salvar(Servidor servidor) {
		return (Servidor) sessao.merge(servidor);
	}

	@Override
	public void remover(Servidor servidor) {
		this.sessao.delete(servidor);

	}

	@Override
	public void editar(Servidor servidor) {
		this.sessao.update(servidor);
	}

}
