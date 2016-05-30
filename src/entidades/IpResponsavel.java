package entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/** Classe IpResponsavel que possui os metodos de acesso getter e setters, 
 * e também o mapeamento relacional das tabelas via hibernate, da entidade IpResponsavel.
*
* @author silas
*
*/

@Entity
@Table
public class IpResponsavel implements Serializable, Cloneable{
	private Integer codigo;
	private String nomeServidor;
	private Setor setor;
	private String email;
	private String telefone;
	private String celular;
	private Cargo cargo;
	private String funcao;
	private String lotacao;
	

	@Id
	@GeneratedValue
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	@Column
	public String getNomeServidor() {
		return nomeServidor;
	}
	public void setNomeServidor(String nomeServidor) {
		this.nomeServidor = nomeServidor;
	}
	@ManyToOne
	@JoinColumn(name="codigo_setor", referencedColumnName="codigo")
	public Setor getSetor() {
		return setor;
	}
	public void setSetor(Setor setor) {
		this.setor = setor;
	}
	@Column
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	@Column
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	@ManyToOne
	@JoinColumn(name="codigo_cargo", referencedColumnName="codigo")
	public Cargo getCargo() {
		return cargo;
	}
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	@Column
	public String getFuncao() {
		return funcao;
	}
	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	@Column
	public String getLotacao() {
		return lotacao;
	}
	public void setLotacao(String lotacao) {
		this.lotacao = lotacao;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cargo == null) ? 0 : cargo.hashCode());
		result = prime * result + ((celular == null) ? 0 : celular.hashCode());
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((funcao == null) ? 0 : funcao.hashCode());
		result = prime * result + ((lotacao == null) ? 0 : lotacao.hashCode());
		result = prime * result + ((nomeServidor == null) ? 0 : nomeServidor.hashCode());
		result = prime * result + ((setor == null) ? 0 : setor.hashCode());
		result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
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
		IpResponsavel other = (IpResponsavel) obj;
		if (cargo == null) {
			if (other.cargo != null)
				return false;
		} else if (!cargo.equals(other.cargo))
			return false;
		if (celular == null) {
			if (other.celular != null)
				return false;
		} else if (!celular.equals(other.celular))
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
		if (funcao == null) {
			if (other.funcao != null)
				return false;
		} else if (!funcao.equals(other.funcao))
			return false;
		if (lotacao == null) {
			if (other.lotacao != null)
				return false;
		} else if (!lotacao.equals(other.lotacao))
			return false;
		if (nomeServidor == null) {
			if (other.nomeServidor != null)
				return false;
		} else if (!nomeServidor.equals(other.nomeServidor))
			return false;
		if (setor == null) {
			if (other.setor != null)
				return false;
		} else if (!setor.equals(other.setor))
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		return true;
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

}
