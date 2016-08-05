package entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/** Classe Responsavel que possui os metodos de acesso getter e setters, 
* e também o mapeamento relacional das tabelas via hibernate, da entidade Responsavel.
*
* @author silas
*
*/

@Entity
@Table
public class Responsavel implements Serializable, Cloneable{
	private Integer codigo;
	private String cel;
	private String matricula;
	private String nome;
	private String email;
	private String ramal;
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
	public String getCel() {
		return cel;
	}
	public void setCel(String cel) {
		this.cel = cel;
	}
	@Column
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	@Column
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Column
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column
	public String getRamal() {
		return ramal;
	}
	public void setRamal(String ramal) {
		this.ramal = ramal;
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
		result = prime * result + ((cel == null) ? 0 : cel.hashCode());
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((equipe == null) ? 0 : equipe.hashCode());
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((ramal == null) ? 0 : ramal.hashCode());
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
		Responsavel other = (Responsavel) obj;
		if (cel == null) {
			if (other.cel != null)
				return false;
		} else if (!cel.equals(other.cel))
			return false;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (equipe == null) {
			if (other.equipe != null)
				return false;
		} else if (!equipe.equals(other.equipe))
			return false;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (ramal == null) {
			if (other.ramal != null)
				return false;
		} else if (!ramal.equals(other.ramal))
			return false;
		return true;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

}
