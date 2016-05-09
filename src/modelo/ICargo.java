package modelo;

import java.util.List;

import entidades.Cargo;
import entidades.Setor;

public interface ICargo {
	public List<Cargo> listar();
	public Cargo porCodigo(Integer codigo);
	public Cargo salvar(Cargo cargo);
	public void remover(Cargo cargo);
	public void editar(Cargo cargo);
}
