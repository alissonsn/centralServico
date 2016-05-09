package entidades;

import java.io.Serializable;
import java.util.Date;


public class PessoaSSH implements Serializable{
		private String uid;
		private String cn;
		private String senha;
		private String uidNumber;
		private String gidNumber;
		private String email;
		private String userPassword;
		private String modificador;
		private Date ultimaModificacao;
		private String mensagem;


		public String getUid() {
			return uid;
		}

		public void setUid(String uid) {
			this.uid = uid;
		}

		public String getCn() {
			return cn;
		}

		public void setCn(String cn) {
			this.cn = cn;
		}

		public String getSenha() {
			return senha;
		}

		public void setSenha(String senha) {
			this.senha = senha;
		}

		public String getUidNumber() {
			return uidNumber;
		}

		public void setUidNumber(String uidNumber) {
			this.uidNumber = uidNumber;
		}

		public String getGidNumber() {
			return gidNumber;
		}

		public void setGidNumber(String gidNumber) {
			this.gidNumber = gidNumber;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getUserPassword() {
			return userPassword;
		}

		public void setUserPassword(String userPassword) {
			this.userPassword = userPassword;
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
			result = prime * result + ((cn == null) ? 0 : cn.hashCode());
			result = prime * result + ((email == null) ? 0 : email.hashCode());
			result = prime * result
					+ ((gidNumber == null) ? 0 : gidNumber.hashCode());
			result = prime * result
					+ ((mensagem == null) ? 0 : mensagem.hashCode());
			result = prime * result
					+ ((modificador == null) ? 0 : modificador.hashCode());
			result = prime * result + ((senha == null) ? 0 : senha.hashCode());
			result = prime * result + ((uid == null) ? 0 : uid.hashCode());
			result = prime * result
					+ ((uidNumber == null) ? 0 : uidNumber.hashCode());
			result = prime
					* result
					+ ((ultimaModificacao == null) ? 0 : ultimaModificacao
							.hashCode());
			result = prime * result
					+ ((userPassword == null) ? 0 : userPassword.hashCode());
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
			PessoaSSH other = (PessoaSSH) obj;
			if (cn == null) {
				if (other.cn != null)
					return false;
			} else if (!cn.equals(other.cn))
				return false;
			if (email == null) {
				if (other.email != null)
					return false;
			} else if (!email.equals(other.email))
				return false;
			if (gidNumber == null) {
				if (other.gidNumber != null)
					return false;
			} else if (!gidNumber.equals(other.gidNumber))
				return false;
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
			if (uidNumber == null) {
				if (other.uidNumber != null)
					return false;
			} else if (!uidNumber.equals(other.uidNumber))
				return false;
			if (ultimaModificacao == null) {
				if (other.ultimaModificacao != null)
					return false;
			} else if (!ultimaModificacao.equals(other.ultimaModificacao))
				return false;
			if (userPassword == null) {
				if (other.userPassword != null)
					return false;
			} else if (!userPassword.equals(other.userPassword))
				return false;
			return true;
		}

		@Override
		public Object clone() throws CloneNotSupportedException {
		return super.clone();
		}
}