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
import entidades.Equipe;
import util.Repositorios;

@ManagedBean(name="cadastroEquipeBean")
@RequestScoped
public class EquipeController implements Serializable{

	private Repositorios repositorios = new Repositorios();
	private Equipe equipe = new Equipe();
	private List<Equipe> equipes = new ArrayList<Equipe>();

	@PostConstruct
	public void init(){
		IEquipe equipes = this.repositorios.getEquipe();
		this.equipes = equipes.listar();
	}


	public void cadastrar(){
		IEquipe equipes = this.repositorios.getEquipe();
		equipes.salvar(equipe);
		this.equipe = new Equipe();


		String msg = "Cadastro efetuado com sucesso!";
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}

	public void update(Equipe equipe){
		IEquipe equipes = this.repositorios.getEquipe();
		equipes.editar(equipe);
	}

	public void excluir(Equipe equipe){
		IEquipe equipes = this.repositorios.getEquipe();
		equipes.remover(equipe);
		this.init();
	}


	public Equipe getEquipe() {
		return equipe;
	}


	public void setEquipe(Equipe equipe) throws CloneNotSupportedException {
		this.equipe = equipe;
		if (this.equipe == null) {
			this.equipe = new Equipe();
		}else{
			this.equipe = (Equipe) equipe.clone();
		}
	}


	public List<Equipe> getEquipes() {
		return equipes;
	}


	public void setEquipes(List<Equipe> equipes) {
		this.equipes = equipes;
	}


}