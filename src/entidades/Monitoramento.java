package entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/** Classe Monitoramento que possui os metodos de acesso getter e setters, 
 * e também o mapeamento relacional das tabelas via hibernate, da entidade Monitoramento.
*
* @author silas
*
*/

@Entity
@Table
public class Monitoramento implements Serializable, Cloneable{
	private Integer codigo;
	private String defMonitoramento;
	private String categoria;
	private String descategoria;
	private String itemMonitoramento;
	private String protocolo;
	private Servico servico;

	@Id
	@GeneratedValue
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	@Column
	public String getDefMonitoramento() {
		return defMonitoramento;
	}

	public void setDefMonitoramento(String defMonitoramento) {
		this.defMonitoramento = defMonitoramento;
	}

	@Column
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	@Column
	public String getDescategoria() {
		return descategoria;
	}

	public void setDescategoria(String descategoria) {
		this.descategoria = descategoria;
	}

	@Column
	public String getItemMonitoramento() {
		return itemMonitoramento;
	}

	public void setItemMonitoramento(String itemMonitoramento) {
		this.itemMonitoramento = itemMonitoramento;
	}

	@Column
	public String getProtocolo() {
		return protocolo;
	}

	public void setProtocolo(String protocolo) {
		this.protocolo = protocolo;
	}

	@ManyToOne
	@JoinColumn(name="codigo_servico", referencedColumnName="codigo")
	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime
				* result
				+ ((defMonitoramento == null) ? 0 : defMonitoramento.hashCode());
		result = prime * result
				+ ((descategoria == null) ? 0 : descategoria.hashCode());
		result = prime
				* result
				+ ((itemMonitoramento == null) ? 0 : itemMonitoramento
						.hashCode());
		result = prime * result
				+ ((protocolo == null) ? 0 : protocolo.hashCode());
		result = prime * result + ((servico == null) ? 0 : servico.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Monitoramento other = (Monitoramento) obj;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (defMonitoramento == null) {
			if (other.defMonitoramento != null)
				return false;
		} else if (!defMonitoramento.equals(other.defMonitoramento))
			return false;
		if (descategoria == null) {
			if (other.descategoria != null)
				return false;
		} else if (!descategoria.equals(other.descategoria))
			return false;
		if (itemMonitoramento == null) {
			if (other.itemMonitoramento != null)
				return false;
		} else if (!itemMonitoramento.equals(other.itemMonitoramento))
			return false;
		if (protocolo == null) {
			if (other.protocolo != null)
				return false;
		} else if (!protocolo.equals(other.protocolo))
			return false;
		if (servico == null) {
			if (other.servico != null)
				return false;
		} else if (!servico.equals(other.servico))
			return false;
		return true;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

}
