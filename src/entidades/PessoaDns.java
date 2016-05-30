package entidades;

import java.io.Serializable;
import java.util.Date;

/** Classe PessoaDns que possui os metodos de acesso getter e setters da entidade PessoaDns.
*
* @author silas
*
*/

public class PessoaDns implements Serializable{
		private String uid;
		private String senha;
		private String modificador;
		private Date ultimaModificacao;
		private String mensagem;


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
		
		public String getModificador() {
			return modificador;
		}

		public void setModificador(String modificador) {
			this.modificador = modificador;
		}

		public Date getUltimaModificacao() {
			return ultimaModificacao;
		}

		public void setUltimaModificacao(Date ultimaModificacao) {
			this.ultimaModificacao = ultimaModificacao;
		}

		public String getMensagem() {
			return mensagem;
		}

		public void setMensagem(String mensagem) {
			this.mensagem = mensagem;
		}
		

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((mensagem == null) ? 0 : mensagem.hashCode());
			result = prime * result + ((modificador == null) ? 0 : modificador.hashCode());
			result = prime * result + ((senha == null) ? 0 : senha.hashCode());
			result = prime * result + ((uid == null) ? 0 : uid.hashCode());
			result = prime * result + ((ultimaModificacao == null) ? 0 : ultimaModificacao.hashCode());
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
			PessoaDns other = (PessoaDns) obj;
			if (mensagem == null) {
				if (other.mensagem != null)
					return false;
			} else if (!mensagem.equals(other.mensagem))
				return false;
			if (modificador == null) {
				if (other.modificador != null)
					return false;
			} else if (!modificador.equals(other.modificador))
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
			return true;
		}

		@Override
		public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
		}
}
