package modelo;

import java.util.List;

import entidades.Rede;

public interface IRede {
	public Rede criar(Rede rede);
	public void deletar(Rede rede);
	public void atualizar(Rede rede);
	public Rede pesquisar(String ip);
	public List<Rede> listar();
}
