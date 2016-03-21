package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import modelo.IEquipe;
import modelo.IServico;
import modelo.IServidor;
import entidades.Equipe;
import entidades.Servico;
import entidades.Servidor;
import util.Repositorios;



@ManagedBean(name="cadastroServicoBean")
@RequestScoped
public class ServicoController implements Serializable{

	private Repositorios repositorios = new Repositorios();
	private Servico servico = new Servico();
	private List<Servico> servicos = new ArrayList<Servico>();
	private List<Servidor> servidores = new ArrayList<Servidor>();
	private List<Equipe> equipes = new ArrayList<Equipe>();



	@PostConstruct
	public void init(){
		IServico servicos = this.repositorios.getServico();
		IEquipe equipes = this.repositorios.getEquipe();
		IServidor servidores = this.repositorios.getServidor();
		this.servicos = servicos.listar();
		this.equipes = equipes.listar();
		this.servidores = servidores.listar();
	}

	public void cadastrar(){
		IServico servicos = this.repositorios.getServico();
		servicos.salvar(servico);
		this.servico = new Servico();

		String msg = "Cadastro efetuado com sucesso!";
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}

	public void update(Servico servico){
		IServico servicos = this.repositorios.getServico();
		servicos.editar(servico);
	}

	public void excluir(Servico servico){
		IServico servicos = this.repositorios.getServico();
		servicos.remover(servico);
		this.init();
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) throws CloneNotSupportedException {
		this.servico = servico;
		if (this.servico == null) {
			this.servico = new Servico();
		}else{
			this.servico = (Servico) servico.clone();
		}
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public List<Equipe> getEquipes() {
		return equipes;
	}

	public void setEquipes(List<Equipe> equipes) {
		this.equipes = equipes;
	}

	public List<Servidor> getServidores() {
		return servidores;
	}

	public void setServidores(List<Servidor> servidores) {
		this.servidores = servidores;
	}
}