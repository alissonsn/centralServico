package modelo;

import java.util.List;

import entidades.Equipe;

/** Interface que contém as assinaturas de metodos das equipes.
*
* @author silas
*
*/

public interface IEquipe {
	public List<Equipe> listar();
	public Equipe porCodigo(Integer codigo);
	public Equipe salvar(Equipe equipe);
	public void remover(Equipe equipe);
	public void editar(Equipe equipe);
}
