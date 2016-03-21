package modelo;

import java.util.List;


import entidades.SolFalha;

public interface ISolFalha {
	public List<SolFalha> listar();
	public SolFalha porCodigo(Integer codigo);
	public SolFalha salvar(SolFalha solFalha);
	public void remover(SolFalha solFalha);
	public void editar(SolFalha solFalha);
}
