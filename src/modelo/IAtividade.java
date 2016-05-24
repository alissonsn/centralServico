package modelo;

import java.util.List;

import entidades.Atividade;

/** Interface que contém as assinaturas de metodos das atividades.
*
* @author silas
*
*/

public interface IAtividade {
	public List<Atividade> listar();
	public Atividade porCodigo(Integer codigo);
	public Atividade salvar(Atividade atividade);
	public void remover(Atividade atividade);
	public void editar(Atividade atividade);
}
