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
public class Atividade implements Serializable{
	private Integer codigo;
	private String definicao;
	private String escopoentrada;
	private String disponibilidade;
	private String solicitacaoAtividade;
	private String protocolo;
	private Equipe equipe;
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
	public String getDefinicao() {
		return definicao;
	}
	public void setDefinicao(String definicao) {
		this.definicao = definicao;
	}
	@Column
	public String getEscopoentrada() {
		return escopoentrada;
	}
	public void setEscopoentrada(String escopoentrada) {
		this.escopoentrada = escopoentrada;
	}
	
	@Column
	public String getSolicitacaoAtividade() {
		return solicitacaoAtividade;
	}
	@Column
	public String getDisponibilidade() {
		return disponibilidade;
	}
	public void setDisponibilidade(String disponibilidade) {
		this.disponibilidade = disponibilidade;
	}
	public void setSolicitacaoAtividade(String solicitacaoAtividade) {
		this.solicitacaoAtividade = solicitacaoAtividade;
	}
	@Column
	public String getProtocolo() {
		return protocolo;
	}
	public void setProtocolo(String protocolo) {
		this.protocolo = protocolo;
	}
	public Equipe getEquipe() {
		return equipe;
	}
	@ManyToOne
	@JoinColumn(name="codigo_equipe", referencedColumnName="codigo")
	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
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
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result
				+ ((definicao == null) ? 0 : definicao.hashCode());
		result = prime * result
				+ ((disponibilidade == null) ? 0 : disponibilidade.hashCode());
		result = prime * result + ((equipe == null) ? 0 : equipe.hashCode());
		result = prime * result
				+ ((escopoentrada == null) ? 0 : escopoentrada.hashCode());
		result = prime * result
				+ ((protocolo == null) ? 0 : protocolo.hashCode());
		result = prime * result + ((servico == null) ? 0 : servico.hashCode());
		result = prime
				* result
				+ ((solicitacaoAtividade == null) ? 0 : solicitacaoAtividade
						.hashCode());
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
		Atividade other = (Atividade) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (definicao == null) {
			if (other.definicao != null)
				return false;
		} else if (!definicao.equals(other.definicao))
			return false;
		if (disponibilidade == null) {
			if (other.disponibilidade != null)
				return false;
		} else if (!disponibilidade.equals(other.disponibilidade))
			return false;
		if (equipe == null) {
			if (other.equipe != null)
				return false;
		} else if (!equipe.equals(other.equipe))
			return false;
		if (escopoentrada == null) {
			if (other.escopoentrada != null)
				return false;
		} else if (!escopoentrada.equals(other.escopoentrada))
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
		if (solicitacaoAtividade == null) {
			if (other.solicitacaoAtividade != null)
				return false;
		} else if (!solicitacaoAtividade.equals(other.solicitacaoAtividade))
			return false;
		return true;
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

}
