package modelo;

import java.util.List;

import entidades.Servico;



public interface IServico {

	public List<Servico> listar();
	public Servico porCodigo(Integer codigo);
	public Servico salvar(Servico servico);
	public void remover(Servico servico);
	public void editar(Servico servico);
}
