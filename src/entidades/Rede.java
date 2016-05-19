package entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Rede implements Serializable{
	
	private Integer codigo;
	private String ip;
	private String mascara;
	private String vlan;
	private String local;
	private String gateway;
	private String dhcp;
	private String ip_externo;
	private String porta;
	private String net;	
	private String host;
	
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
	public String getporta() {
		return porta;
	}
	public void setporta(String porta) {
		this.porta = porta;
	}
	@Column
	public String getNet() {
		return net;
	}
	public void setNet(String net) {
		this.net = net;
	}
	@Column
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	
	
}

