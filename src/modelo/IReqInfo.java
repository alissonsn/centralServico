package modelo;

import java.util.List;

import entidades.ReqInfo;

/** Interface que contém as assinaturas de metodos de requisição de informação.
*
* @author silas
*
*/

public interface IReqInfo {

	public List<ReqInfo> listar();
	public ReqInfo porCodigo(Integer codigo);
	public ReqInfo salvar(ReqInfo reqInfo);
	public void remover(ReqInfo reqInfo);
	public void editar(ReqInfo reqInfo);
}
