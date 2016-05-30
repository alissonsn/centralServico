package entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/** Classe Servidor que possui os metodos de acesso getter e setters, 
 * e também o mapeamento relacional das tabelas via hibernate, da entidade Servidor.
*
* @author silas
*
*/

@Entity
@Table
public class Servidor implements Serializable, Cloneable{
	private Integer codigo;
	private String cpu;
	private String hostname;
	private String ip;
	private String local;
	private String memoria;
	private String os;
	private Responsavel responsavel;

	@Id
	@GeneratedValue
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	@Column
	public String getCpu() {
		return cpu;
	}
	public void setCpu(String cpu) {
		this.cpu = cpu;
	}
	@Column
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	@Column
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	@Column
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	@Column
	public String getMemoria() {
		return memoria;
	}
	public void setMemoria(String memoria) {
		this.memoria = memoria;
	}
	@Column
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	@ManyToOne
	@JoinColumn(name="codigo_responsavel", referencedColumnName="codigo")
	public Responsavel getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((cpu == null) ? 0 : cpu.hashCode());
		result = prime * result
				+ ((hostname == null) ? 0 : hostname.hashCode());
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result + ((local == null) ? 0 : local.hashCode());
		result = prime * result + ((memoria == null) ? 0 : memoria.hashCode());
		result = prime * result + ((os == null) ? 0 : os.hashCode());
		result = prime * result
				+ ((responsavel == null) ? 0 : responsavel.hashCode());
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
		Servidor other = (Servidor) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (cpu == null) {
			if (other.cpu != null)
				return false;
		} else if (!cpu.equals(other.cpu))
			return false;
		if (hostname == null) {
			if (other.hostname != null)
				return false;
		} else if (!hostname.equals(other.hostname))
			return false;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		if (local == null) {
			if (other.local != null)
				return false;
		} else if (!local.equals(other.local))
			return false;
		if (memoria == null) {
			if (other.memoria != null)
				return false;
		} else if (!memoria.equals(other.memoria))
			return false;
		if (os == null) {
			if (other.os != null)
				return false;
		} else if (!os.equals(other.os))
			return false;
		if (responsavel == null) {
			if (other.responsavel != null)
				return false;
		} else if (!responsavel.equals(other.responsavel))
			return false;
		return true;
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

}

