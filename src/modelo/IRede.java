package modelo;

import java.util.List;

import entidades.Equipe;
import entidades.Rede;

public interface IRede {
	public List<Rede> listar();
	public Rede porCodigo(Integer codigo);
	public Rede salvar(Rede rede);
	public void remover(Rede rede);
	public void editar(Rede rede);
}
