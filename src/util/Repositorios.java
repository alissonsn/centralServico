package util;

import java.io.Serializable;

import org.hibernate.Session;

import modelo.AtividadeImpl;
import modelo.EquipeImpl;
import modelo.FalhaImpl;
import modelo.IAtividade;
import modelo.IEquipe;
import modelo.IFalha;
import modelo.IMonitoramento;
import modelo.IReqInfo;
import modelo.IResponsavel;
import modelo.IServico;
import modelo.IServidor;
import modelo.ISolFalha;
import modelo.MonitoramentoImpl;
import modelo.ReqInfoImpl;
import modelo.ResponsavelImpl;
import modelo.ServicoImpl;
import modelo.ServidorImpl;
import modelo.SolFalhaImpl;


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

	private Session getSession() {
		return (Session) FacesUtil.getRequestAttribute("session");
	}

}
