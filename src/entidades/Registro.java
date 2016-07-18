package entidades;

import java.io.Serializable;

/** Classe Registro que possui os metodos de acesso getter e setters da entidade Registro.
*
* @author silas
*
*/

public class Registro implements Serializable{
	private String dominio;
	private String ip;
	private String nomeMaquina;
	private String ptrrecord;
	private String tipo;
	private String apelido;
	private String soa;



	public String getDominio() {
		return dominio;
	}
	public void setDominio(String dominio) {
		this.dominio = dominio;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getNomeMaquina() {
		return nomeMaquina;
	}
	public void setNomeMaquina(String nomeMaquina) {
		this.nomeMaquina = nomeMaquina;
	}
	public String getPtrrecord() {
		return ptrrecord;
	}
	public void setPtrrecord(String ptrrecord) {
		this.ptrrecord = ptrrecord;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}		
	public String getApelido() {
		return apelido;
	}
	public void setApelido(String apelido) {
		this.apelido = apelido;
	}
	public String getSoa() {
		return soa;
	}
	public void setSoa(String soa) {
		this.soa = soa;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apelido == null) ? 0 : apelido.hashCode());
		result = prime * result + ((dominio == null) ? 0 : dominio.hashCode());
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result
				+ ((nomeMaquina == null) ? 0 : nomeMaquina.hashCode());
		result = prime * result
				+ ((ptrrecord == null) ? 0 : ptrrecord.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		Registro other = (Registro) obj;
		if (apelido == null) {
			if (other.apelido != null)
				return false;
		} else if (!apelido.equals(other.apelido))
			return false;
		if (dominio == null) {
			if (other.dominio != null)
				return false;
		} else if (!dominio.equals(other.dominio))
			return false;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		if (nomeMaquina == null) {
			if (other.nomeMaquina != null)
				return false;
		} else if (!nomeMaquina.equals(other.nomeMaquina))
			return false;
		if (ptrrecord == null) {
			if (other.ptrrecord != null)
				return false;
		} else if (!ptrrecord.equals(other.ptrrecord))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
}
