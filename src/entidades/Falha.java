package entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/** Classe Falha que possui os metodos de acesso getter e setters, 
 * e também o mapeamento relacional das tabelas via hibernate, da entidade Falha.
*
* @author silas
*
*/

@Entity
@Table
public class Falha implements Serializable, Cloneable{
	private Integer codigo;
	private String falha;
	private String defFalha;
	private String palavraChave;
	private String solicitacaoMonitoramento;
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
	public String getFalha() {
		return falha;
	}
	public void setFalha(String falha) {
		this.falha = falha;
	}
	@Column
	public String getDefFalha() {
		return defFalha;
	}
	public void setDefFalha(String defFalha) {
		this.defFalha = defFalha;
	}
	@Column
	public String getPalavraChave() {
		return palavraChave;
	}
	public void setPalavraChave(String palavraChave) {
		this.palavraChave = palavraChave;
	}
	@Column
	public String getSolicitacaoMonitoramento() {
		return solicitacaoMonitoramento;
	}
	public void setSolicitacaoMonitoramento(String solicitacaoMonitoramento) {
		this.solicitacaoMonitoramento = solicitacaoMonitoramento;
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
				+ ((defFalha == null) ? 0 : defFalha.hashCode());
		result = prime * result + ((falha == null) ? 0 : falha.hashCode());
		result = prime * result
				+ ((palavraChave == null) ? 0 : palavraChave.hashCode());
		result = prime * result + ((servico == null) ? 0 : servico.hashCode());
		result = prime
				* result
				+ ((solicitacaoMonitoramento == null) ? 0
						: solicitacaoMonitoramento.hashCode());
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
		Falha other = (Falha) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (defFalha == null) {
			if (other.defFalha != null)
				return false;
		} else if (!defFalha.equals(other.defFalha))
			return false;
		if (falha == null) {
			if (other.falha != null)
				return false;
		} else if (!falha.equals(other.falha))
			return false;
		if (palavraChave == null) {
			if (other.palavraChave != null)
				return false;
		} else if (!palavraChave.equals(other.palavraChave))
			return false;
		if (servico == null) {
			if (other.servico != null)
				return false;
		} else if (!servico.equals(other.servico))
			return false;
		if (solicitacaoMonitoramento == null) {
			if (other.solicitacaoMonitoramento != null)
				return false;
		} else if (!solicitacaoMonitoramento
				.equals(other.solicitacaoMonitoramento))
			return false;
		return true;
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

}
