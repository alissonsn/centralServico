package modelo;

import java.util.List;

import entidades.Falha;

/** Interface que contém as assinaturas de metodos das falhas.
*
* @author silas
*
*/

public interface IFalha {
	public List<Falha> listar();
	public Falha porCodigo(Integer codigo);
	public Falha salvar(Falha falha);
	public void remover(Falha falha);
	public void editar(Falha falha);
}
