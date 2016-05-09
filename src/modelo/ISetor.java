package modelo;

import java.util.List;

import entidades.Equipe;
import entidades.Rede;
import entidades.Setor;

public interface ISetor {
	public List<Setor> listar();
	public Setor porCodigo(Integer codigo);
	public Setor salvar(Setor setor);
	public void remover(Setor setor);
	public void editar(Setor setor);
}
