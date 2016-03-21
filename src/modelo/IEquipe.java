package modelo;

import java.util.List;

import entidades.Equipe;

public interface IEquipe {
	public List<Equipe> listar();
	public Equipe porCodigo(Integer codigo);
	public Equipe salvar(Equipe equipe);
	public void remover(Equipe equipe);
	public void editar(Equipe equipe);
}
