package modelo;

import java.util.List;

import entidades.Atividade;
import entidades.Monitoramento;

public interface IMonitoramento {
	public List<Monitoramento> listar();
	public Monitoramento porCodigo(Integer codigo);
	public Monitoramento salvar(Monitoramento monitoramento);
	public void remover(Monitoramento monitoramento);
	public void editar(Monitoramento monitoramento);
}
