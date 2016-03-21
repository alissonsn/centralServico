package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;




import modelo.IAtividade;
import modelo.IEquipe;
import modelo.IServico;

import com.sun.org.apache.xml.internal.security.Init;

import entidades.Atividade;
import entidades.Equipe;
import entidades.Servico;
import util.Repositorios;

@ManagedBean(name="cadastroAtividadeBean")
@RequestScoped
public class AtividadeController implements Serializable{
	private Repositorios repositorios = new Repositorios();
	private Atividade atividade = new Atividade();
	private List<Atividade> atividades = new ArrayList<Atividade>();
	private List<Equipe> equipes  = new ArrayList<Equipe>();
	private List<Servico> servicos = new ArrayList<Servico>();

	@PostConstruct
	public void init(){
		IAtividade atividades = this.repositorios.getAtividade();
		IEquipe equipes = this.repositorios.getEquipe();
		IServico servicos = this.repositorios.getServico();
		this.atividades = atividades.listar();
		this.equipes = equipes.listar();
		this.servicos = servicos.listar();
	}

	public void cadastrar(){
		IAtividade atividades = this.repositorios.getAtividade();
		atividades.salvar(atividade);
		this.atividade = new Atividade();

		String msg = "Cadastro efetuado com sucesso!";
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}

	public void update(Atividade atividade){
		IAtividade atividades = this.repositorios.getAtividade();
		atividades.editar(atividade);
	}

	public void remover(Atividade atividade){
		IAtividade atividades = this.repositorios.getAtividade();
		atividades.remover(atividade);
		this.init();
	}

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) throws CloneNotSupportedException {
		this.atividade = atividade;
		if (this.atividade == null) {
			this.atividade = new Atividade();
		}else{
			this.atividade = (Atividade) atividade.clone();
		}
	}

	public List<Atividade> getAtividades() {
		return atividades;
	}

	public void setAtividades(List<Atividade> atividades) {
		this.atividades = atividades;
	}

	public List<Equipe> getEquipes() {
		return equipes;
	}

	public void setEquipes(List<Equipe> equipes) {
		this.equipes = equipes;
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}


}
