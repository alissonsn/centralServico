package modelo;

import java.util.List;

import entidades.Responsavel;

public interface IResponsavel {
	public List<Responsavel> listar();
	public Responsavel porCodigo(Integer codigo);
	public Responsavel salvar(Responsavel responsavel);
	public void remover(Responsavel responsavel);
	public void editar(Responsavel responsavel);
}
