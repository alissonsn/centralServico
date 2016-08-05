package util;

import java.io.Serializable;

import modelo.AtividadeImpl;
import modelo.CargoImpl;
import modelo.EquipeImpl;
import modelo.FalhaImpl;
import modelo.IAtividade;
import modelo.ICargo;
import modelo.IEquipe;
import modelo.IFalha;
import modelo.IIpResponsavel;
import modelo.IMonitoramento;
import modelo.IRede;
import modelo.IReqInfo;
import modelo.IResponsavel;
import modelo.IServico;
import modelo.IServidor;
import modelo.ISetor;
import modelo.ISolFalha;
import modelo.IpResponsavelImpl;
import modelo.MonitoramentoImpl;
import modelo.RedeImpl;
import modelo.ReqInfoImpl;
import modelo.ResponsavelImpl;
import modelo.ServicoImpl;
import modelo.ServidorImpl;
import modelo.SetorImpl;
import modelo.SolFalhaImpl;

import org.hibernate.Session;


public class Repositorios implements Serializable {

	public IEquipe getEquipe(){
		return new EquipeImpl(this.getSession());
	}

	public IResponsavel getResponsavel(){
		return new ResponsavelImpl(this.getSession());
	}

	public IServidor getServidor(){
		return new ServidorImpl(this.getSession());
	}

	public IServico getServico(){
		return new ServicoImpl(this.getSession());
	}

	public IReqInfo getReqInfo(){
		return new ReqInfoImpl(this.getSession());
	}

	public IFalha getFalha(){
		return new FalhaImpl(this.getSession());
	}

	public IAtividade getAtividade(){
		return new AtividadeImpl(this.getSession());
	}

	public IMonitoramento getMonitoramento(){
		return new MonitoramentoImpl(this.getSession());
	}

	public ISolFalha getSolFalha(){
		return new SolFalhaImpl(this.getSession());
	}

	public IRede getRede(){
		return new RedeImpl(this.getSession());
	}

	public ISetor getSetor(){
		return new SetorImpl(this.getSession());
	}

	public ICargo getCargo(){
		return new CargoImpl(this.getSession());
	}
	
	public IIpResponsavel getIpResponsavel(){
		return new IpResponsavelImpl(this.getSession());
	}
	
	private Session getSession() {
		return (Session) FacesUtil.getRequestAttribute("session");
	}

}
