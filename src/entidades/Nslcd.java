package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/** Classe PessoaSSH que possui os metodos de acesso getter e setters da entidade PessoaSSH.
*
* @author silas
*
*/

public class Nslcd implements Serializable{
		private String uid;
		private String cn;
		private String senha;
		private String uidNumber;
		private String flagAdmin;
		private String servidor;
		private List<String> listaServidores;
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

		public String getFlagAdmin() {
			return flagAdmin;
		}

		public void setFlagAdmin(String flagAdmin) {
			this.flagAdmin = flagAdmin;
		}
		
		public String getServidor() {
			return servidor;
		}

		public void setServidor(String servidor) {
			this.servidor = servidor;
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

		public List<String> getListaServidores() {
			return listaServidores;
		}

		public void setListaServidores(List<String> listaServidores) {
			this.listaServidores = listaServidores;
		}

		@Override
		public Object clone() throws CloneNotSupportedException {
		return super.clone();
		}
}