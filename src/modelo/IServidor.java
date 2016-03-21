package modelo;

import java.util.List;


import entidades.Servidor;

public interface IServidor {
	public List<Servidor> listar();
	public Servidor porCodigo(Integer codigo);
	public Servidor salvar(Servidor servidor);
	public void remover(Servidor servidor);
	public void editar(Servidor servidor);
}
