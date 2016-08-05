package entidades;

import java.io.Serializable;

/** Classe Zona que possui os metodos de acesso getter e setters da entidade Zona.
*
* @author silas
*
*/

public class Zona implements Serializable{
	private String zona;
	private String dominio;
	private String ip;
	private String nomeMaquina;


	public String getZona() {
		return zona;
	}
	public void setZona(String zona) {
		this.zona = zona;
	}
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dominio == null) ? 0 : dominio.hashCode());
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result
				+ ((nomeMaquina == null) ? 0 : nomeMaquina.hashCode());
		result = prime * result + ((zona == null) ? 0 : zona.hashCode());
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
		Zona other = (Zona) obj;
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
		if (zona == null) {
			if (other.zona != null)
				return false;
		} else if (!zona.equals(other.zona))
			return false;
		return true;
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
}
