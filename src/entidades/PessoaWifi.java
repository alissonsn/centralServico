package entidades;

import java.io.Serializable;
import java.util.Date;

/** Classe PessoaWifi que possui os metodos de acesso getter e setters da entidade PessoaWifi.
*
* @author silas
*
*/

public class PessoaWifi implements Serializable{
		private String uid;
		private String senha;
		private String CPF;
		private String email;
		private Date nascimento;
		private String validade;
		private String modificador;
		private String ultimaModificacao;
		private String dn;


		public String getUid() {
			return uid;
		}

		public void setUid(String uid) {
			this.uid = uid;
		}

		public String getSenha() {
			return senha;
		}

		public void setSenha(String senha) {
			this.senha = senha;
		}

		public String getCPF() {
			return CPF;
		}

		public void setCPF(String cPF) {
			CPF = cPF;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public Date getNascimento() {
			return nascimento;
		}

		public void setNascimento(Date nascimento) {
			this.nascimento = nascimento;
		}

		public String getValidade() {
			return validade;
		}

		public void setValidade(String validade) {
			this.validade = validade;
		}

		public String getModificador() {
			return modificador;
		}

		public void setModificador(String modificador) {
			this.modificador = modificador;
		}

		public String getUltimaModificacao() {
			return ultimaModificacao;
		}

		public void setUltimaModificacao(String ultimaModificacao) {
			this.ultimaModificacao = ultimaModificacao;
		}

		public String getDn() {
			return dn;
		}

		public void setDn(String dn) {
			this.dn = dn;
		}


		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((CPF == null) ? 0 : CPF.hashCode());
			result = prime * result + ((dn == null) ? 0 : dn.hashCode());
			result = prime * result + ((email == null) ? 0 : email.hashCode());
			result = prime * result
					+ ((modificador == null) ? 0 : modificador.hashCode());
			result = prime * result
					+ ((nascimento == null) ? 0 : nascimento.hashCode());
			result = prime * result + ((senha == null) ? 0 : senha.hashCode());
			result = prime * result + ((uid == null) ? 0 : uid.hashCode());
			result = prime
					* result
					+ ((ultimaModificacao == null) ? 0 : ultimaModificacao
							.hashCode());
			result = prime * result
					+ ((validade == null) ? 0 : validade.hashCode());
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
			PessoaWifi other = (PessoaWifi) obj;
			if (CPF == null) {
				if (other.CPF != null)
					return false;
			} else if (!CPF.equals(other.CPF))
				return false;
			if (dn == null) {
				if (other.dn != null)
					return false;
			} else if (!dn.equals(other.dn))
				return false;
			if (email == null) {
				if (other.email != null)
					return false;
			} else if (!email.equals(other.email))
				return false;
			if (modificador == null) {
				if (other.modificador != null)
					return false;
			} else if (!modificador.equals(other.modificador))
				return false;
			if (nascimento == null) {
				if (other.nascimento != null)
					return false;
			} else if (!nascimento.equals(other.nascimento))
				return false;
			if (senha == null) {
				if (other.senha != null)
					return false;
			} else if (!senha.equals(other.senha))
				return false;
			if (uid == null) {
				if (other.uid != null)
					return false;
			} else if (!uid.equals(other.uid))
				return false;
			if (ultimaModificacao == null) {
				if (other.ultimaModificacao != null)
					return false;
			} else if (!ultimaModificacao.equals(other.ultimaModificacao))
				return false;
			if (validade == null) {
				if (other.validade != null)
					return false;
			} else if (!validade.equals(other.validade))
				return false;
			return true;
		}

		@Override
		public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
		}
}
