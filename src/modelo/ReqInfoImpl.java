package modelo;

import java.util.List;

import entidades.ReqInfo;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import modelo.IReqInfo;

public class ReqInfoImpl implements IReqInfo{
	private Session sessao;

	public ReqInfoImpl(Session sessao){
		this.sessao = sessao;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ReqInfo> listar() {
		return sessao.createCriteria(ReqInfo.class).addOrder(Order.asc("categoria")).list();
	}

	@Override
	public ReqInfo porCodigo(Integer codigo) {
		return (ReqInfo) sessao.get(ReqInfo.class, codigo);
	}

	@Override
	public ReqInfo salvar(ReqInfo reqInfo) {
		return (ReqInfo) sessao.merge(reqInfo);
	}

	@Override
	public void remover(ReqInfo reqInfo) {
		this.sessao.delete(reqInfo);

	}

	@Override
	public void editar(ReqInfo reqInfo) {
		this.sessao.update(reqInfo);
	}

}
