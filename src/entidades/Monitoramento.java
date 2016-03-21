package entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Monitoramento implements Serializable{
	private Integer codigo;
	private String categoria;
	private String defMonitoramento;
	private String desCat;
	private String item;
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
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	@Column
	public String getDefMonitoramento() {
		return defMonitoramento;
	}
	public void setDefMonitoramento(String defMonitoramento) {
		this.defMonitoramento = defMonitoramento;
	}
	@Column
	public String getDesCat() {
		return desCat;
	}
	public void setDesCat(String desCat) {
		this.desCat = desCat;
	}
	@Column
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
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
		result = prime * result + ((desCat == null) ? 0 : desCat.hashCode());
		result = prime * result + ((item == null) ? 0 : item.hashCode());
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
		if (desCat == null) {
			if (other.desCat != null)
				return false;
		} else if (!desCat.equals(other.desCat))
			return false;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
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
