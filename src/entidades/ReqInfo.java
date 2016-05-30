package entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/** Classe ReqInfo que possui os metodos de acesso getter e setters, 
* e também o mapeamento relacional das tabelas via hibernate, da entidade ReqInfo.
*
* @author silas
*
*/

@Entity
@Table
public class ReqInfo implements Serializable, Cloneable{
	private Integer codigo;
	private String categoria;
	private String descat;
	private String descInfo;
	private String info;
	private String protocolo;
	private Servico servico ;

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
	public String getDescat() {
		return descat;
	}

	public void setDescat(String descat) {
		this.descat = descat;
	}

	@Column
	public String getDescInfo() {
		return descInfo;
	}

	@Column
	public void setDescInfo(String descInfo) {
		this.descInfo = descInfo;
	}

	@Column
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
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
		result = prime * result
				+ ((descInfo == null) ? 0 : descInfo.hashCode());
		result = prime * result + ((descat == null) ? 0 : descat.hashCode());
		result = prime * result + ((info == null) ? 0 : info.hashCode());
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
		ReqInfo other = (ReqInfo) obj;
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
		if (descInfo == null) {
			if (other.descInfo != null)
				return false;
		} else if (!descInfo.equals(other.descInfo))
			return false;
		if (descat == null) {
			if (other.descat != null)
				return false;
		} else if (!descat.equals(other.descat))
			return false;
		if (info == null) {
			if (other.info != null)
				return false;
		} else if (!info.equals(other.info))
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