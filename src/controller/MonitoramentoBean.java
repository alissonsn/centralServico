package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import modelo.IMonitoramento;
import modelo.IServico;
import entidades.Monitoramento;
import entidades.Servico;
import util.Repositorios;


@ManagedBean(name="cadastroMonitoramentoBean")
@RequestScoped
public class MonitoramentoBean implements Serializable{
	private Repositorios repositorios = new Repositorios();
	private Monitoramento monitoramento = new Monitoramento();
	private List<Monitoramento> monitoramentos = new ArrayList<Monitoramento>();
	private List<Servico> servicos = new ArrayList<Servico>();

	@PostConstruct
	public void init(){
		IMonitoramento monitoramentos = this.repositorios.getMonitoramento();
		IServico servicos = this.repositorios.getServico();
		this.monitoramentos = monitoramentos.listar();
		this.servicos = servicos.listar();
	}

	public void cadastrar(){
		IMonitoramento monitoramentos = this.repositorios.getMonitoramento();
		monitoramentos.salvar(monitoramento);
		this.monitoramento = new Monitoramento();
		String msg = "Cadastro efetuado com sucesso!";
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}

	public void update(Monitoramento monitoramento){
		IMonitoramento monitoramentos = this.repositorios.getMonitoramento();
		monitoramentos.editar(monitoramento);
	}

	public void remover(Monitoramento monitoramento){
		IMonitoramento monitoramentos = this.repositorios.getMonitoramento();
		monitoramentos.remover(monitoramento);
		this.init();
	}

	public Monitoramento getMonitoramento() {
		return monitoramento;
	}

	public void setMonitoramento(Monitoramento monitoramento) throws CloneNotSupportedException {
		this.monitoramento = monitoramento;
		if (this.monitoramento == null) {
			this.monitoramento  = new Monitoramento();
		}else{
			this.monitoramento = (Monitoramento) monitoramento.clone();
		}
	}

	public List<Monitoramento> getMonitoramentos() {
		return monitoramentos;
	}

	public void setMonitoramentos(List<Monitoramento> monitoramentos) {
		this.monitoramentos = monitoramentos;
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

}