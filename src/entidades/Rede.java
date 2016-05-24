package entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/** Classe para objetos Equipe, é o modelo de um objeto Equipe.
*
* @author silas
*
*/

@Entity
@Table
public class Rede implements Serializable, Cloneable{

	private Integer codigo;
	private String ip;
	private String mascara;
	private String vlan;
	private String local;
	private String gateway;
	private String dhcp;
	private String ip_externo;
	private String porta;

	@Id
	@GeneratedValue
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	@Column
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	@Column
	public String getMascara() {
		return mascara;
	}
	public void setMascara(String mascara) {
		this.mascara = mascara;
	}
	@Column
	public String getVlan() {
		return vlan;
	}
	public void setVlan(String vlan) {
		this.vlan = vlan;
	}
	@Column
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	@Column
	public String getGateway() {
		return gateway;
	}
	public void setGateway(String gateway) {
		this.gateway = gateway;
	}
	@Column
	public String getDhcp() {
		return dhcp;
	}
	public void setDhcp(String dhcp) {
		this.dhcp = dhcp;
	}
	@Column
	public String getIp_externo() {
		return ip_externo;
	}
	public void setIp_externo(String ip_externo) {
		this.ip_externo = ip_externo;
	}
	@Column
	public String getPorta() {
		return porta;
	}
	public void setPorta(String porta) {
		this.porta = porta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((dhcp == null) ? 0 : dhcp.hashCode());
		result = prime * result + ((gateway == null) ? 0 : gateway.hashCode());
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result
				+ ((ip_externo == null) ? 0 : ip_externo.hashCode());
		result = prime * result + ((local == null) ? 0 : local.hashCode());
		result = prime * result + ((mascara == null) ? 0 : mascara.hashCode());
		result = prime * result + ((porta == null) ? 0 : porta.hashCode());
		result = prime * result + ((vlan == null) ? 0 : vlan.hashCode());
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
		Rede other = (Rede) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (dhcp == null) {
			if (other.dhcp != null)
				return false;
		} else if (!dhcp.equals(other.dhcp))
			return false;
		if (gateway == null) {
			if (other.gateway != null)
				return false;
		} else if (!gateway.equals(other.gateway))
			return false;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		if (ip_externo == null) {
			if (other.ip_externo != null)
				return false;
		} else if (!ip_externo.equals(other.ip_externo))
			return false;
		if (local == null) {
			if (other.local != null)
				return false;
		} else if (!local.equals(other.local))
			return false;
		if (mascara == null) {
			if (other.mascara != null)
				return false;
		} else if (!mascara.equals(other.mascara))
			return false;
		if (porta == null) {
			if (other.porta != null)
				return false;
		} else if (!porta.equals(other.porta))
			return false;
		if (vlan == null) {
			if (other.vlan != null)
				return false;
		} else if (!vlan.equals(other.vlan))
			return false;
		return true;
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}


}
