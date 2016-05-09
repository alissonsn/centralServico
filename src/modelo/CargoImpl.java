package modelo;

import java.util.List;


import entidades.Cargo;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

public class CargoImpl implements ICargo{
	private Session sessao;

	public CargoImpl(Session sessao){
		this.sessao = sessao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cargo> listar() {
		return sessao.createCriteria(Cargo.class).addOrder(Order.asc("nome")).list();
	}

	@Override
	public Cargo porCodigo(Integer codigo) {
		return (Cargo) sessao.get(Cargo.class, codigo);
	}

	@Override
	public Cargo salvar(Cargo cargo) {
		return (Cargo) sessao.merge(cargo);
	}

	@Override
	public void remover(Cargo cargo) {
		this.sessao.delete(cargo);

	}

	@Override
	public void editar(Cargo cargo) {
		this.sessao.update(cargo);
	}

}
