package entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/** Classe Servico que possui os metodos de acesso getter e setters, 
 * e também o mapeamento relacional das tabelas via hibernate, da entidade Servico.
*
* @author silas
*
*/

@Entity
@Table
public class Servico implements Serializable, Cloneable{
	private Integer codigo;
	private String nome;
	private String ip;
	private Servidor servidor;
	private Equipe equipe;

	@Id
	@GeneratedValue
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	@Column
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	@ManyToOne
	@JoinColumn(name="codigo_servidor", referencedColumnName="codigo")
	public Servidor getServidor() {
		return servidor;
	}

	public void setServidor(Servidor servidor) {
		this.servidor = servidor;
	}

	@ManyToOne
	@JoinColumn(name="codigo_equipe", referencedColumnName="codigo")
	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((equipe == null) ? 0 : equipe.hashCode());
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result
				+ ((servidor == null) ? 0 : servidor.hashCode());
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
		Servico other = (Servico) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (equipe == null) {
			if (other.equipe != null)
				return false;
		} else if (!equipe.equals(other.equipe))
			return false;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (servidor == null) {
			if (other.servidor != null)
				return false;
		} else if (!servidor.equals(other.servidor))
			return false;
		return true;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

}