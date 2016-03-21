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
import modelo.IResponsavel;
import entidades.Equipe;
import entidades.Responsavel;
import util.Repositorios;

@ManagedBean(name="cadastroResponsavelBean")
@RequestScoped
public class ResponsavelController implements Serializable{

	private Repositorios repositorios = new Repositorios();
	private Responsavel responsavel = new Responsavel();
	private List<Responsavel> responsaveis = new ArrayList<Responsavel>();
	private List<Equipe> equipes = new ArrayList<Equipe>();
	

	@PostConstruct
	public void init(){
		IResponsavel responsaveis = this.repositorios.getResponsavel();
		IEquipe equipes = this.repositorios.getEquipe();
		this.responsaveis = responsaveis.listar();
		this.equipes = equipes.listar();
		
	}


	public void cadastrar(){
		IResponsavel responsaveis = this.repositorios.getResponsavel();
		responsaveis.salvar(responsavel);
		this.responsavel = new Responsavel();


		String msg = "Cadastro efetuado com sucesso!";
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}

	public void update(Responsavel responsavel){
		IResponsavel responsaveis = this.repositorios.getResponsavel();
		responsaveis.editar(responsavel);
	}

	public void excluir(Responsavel responsavel){
		IResponsavel responsaveis = this.repositorios.getResponsavel();
		responsaveis.remover(responsavel);
		this.init();
	}


	public Responsavel getResponsavel() {
		return responsavel;
	}


	public void setResponsavel(Responsavel responsavel) throws CloneNotSupportedException {
		this.responsavel = responsavel;
		if (this.responsavel == null) {
			this.responsavel = new Responsavel();			
		}else{
			this.responsavel = (Responsavel) responsavel.clone();
		}
	}


	public List<Responsavel> getResponsaveis() {
		return responsaveis;
	}

	public void setResponsaveis(List<Responsavel> responsaveis) {
		this.responsaveis = responsaveis;
	}

	public List<Equipe> getEquipes() {
		return equipes;
	}

	public void setEquipes(List<Equipe> equipes) {
		this.equipes = equipes;
	}

}