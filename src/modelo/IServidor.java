package modelo;

import java.util.List;


import entidades.Servidor;

/** Interface que contém as assinaturas de metodos de servidor.
*
* @author silas
*
*/

public interface IServidor {
	public List<Servidor> listar();
	public Servidor porCodigo(Integer codigo);
	public Servidor salvar(Servidor servidor);
	public void remover(Servidor servidor);
	public void editar(Servidor servidor);
}
