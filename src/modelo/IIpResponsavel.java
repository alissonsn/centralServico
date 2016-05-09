package modelo;

import java.util.List;

import entidades.IpResponsavel;
import entidades.Responsavel;

public interface IIpResponsavel {
	public List<IpResponsavel> listar();
	public IpResponsavel porCodigo(Integer codigo);
	public IpResponsavel salvar(IpResponsavel ipresponsavel);
	public void remover(IpResponsavel ipresponsavel);
	public void editar(IpResponsavel ipresponsavel);
}
