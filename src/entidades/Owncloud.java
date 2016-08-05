package entidades;

import java.io.Serializable;
import java.util.Date;

/** Classe PessoaSSH que possui os metodos de acesso getter e setters da entidade PessoaSSH.
*
* @author silas
*
*/

public class Owncloud implements Serializable{
		private String uid;
		private String cn;
		private String sn;
		private String userPassword;
		private String modificador;
		private Date ultimaModificacao;
		
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

		public String getSn() {
			return sn;
		}

		public void setSn(String sn) {
			this.sn = sn;
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

		

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((cn == null) ? 0 : cn.hashCode());
			result = prime * result
					+ ((modificador == null) ? 0 : modificador.hashCode());
			result = prime * result + ((sn == null) ? 0 : sn.hashCode());
			result = prime * result + ((uid == null) ? 0 : uid.hashCode());
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
			Owncloud other = (Owncloud) obj;
			if (cn == null) {
				if (other.cn != null)
					return false;
			} else if (!cn.equals(other.cn))
				return false;
			if (modificador == null) {
				if (other.modificador != null)
					return false;
			} else if (!modificador.equals(other.modificador))
				return false;
			if (sn == null) {
				if (other.sn != null)
					return false;
			} else if (!sn.equals(other.sn))
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