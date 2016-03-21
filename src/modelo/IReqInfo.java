package modelo;

import java.util.List;

import entidades.ReqInfo;



public interface IReqInfo {

	public List<ReqInfo> listar();
	public ReqInfo porCodigo(Integer codigo);
	public ReqInfo salvar(ReqInfo reqInfo);
	public void remover(ReqInfo reqInfo);
	public void editar(ReqInfo reqInfo);
}
