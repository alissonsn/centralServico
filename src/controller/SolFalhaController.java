package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import modelo.IFalha;
import modelo.IResponsavel;
import modelo.IServidor;
import modelo.ISolFalha;
import util.Repositorios;
import entidades.Falha;
import entidades.Responsavel;
import entidades.Servidor;
import entidades.SolFalha;



@ManagedBean(name="cadastroSolFalhaBean")
@RequestScoped
public class SolFalhaController implements Serializable{

	private Repositorios repositorios = new Repositorios();
	private SolFalha solFalha = new SolFalha();
	private List<SolFalha> solFalhas = new ArrayList<SolFalha>();
	private List<Falha> falhas = new ArrayList<Falha>();




	@PostConstruct
	public void init(){
		ISolFalha solFalhas = this.repositorios.getSolFalha();
		IFalha falhas = this.repositorios.getFalha();
		this.solFalhas = solFalhas.listar();
		this.falhas = falhas.listar();


	}

	public void cadastrar(){
		ISolFalha solFalhas = this.repositorios.getSolFalha();
		solFalhas.salvar(solFalha);
		this.solFalha = new SolFalha();




		String msg = "Cadastro efetuado com sucesso!";
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}

	public void update(SolFalha solFalha){
		ISolFalha solFalhas = this.repositorios.getSolFalha();
		solFalhas.editar(solFalha);


	}

	public void excluir(SolFalha solFalha){
		ISolFalha solFalhas = this.repositorios.getSolFalha();
		solFalhas.remover(solFalha);
		this.init();
	}

	public SolFalha getSolFalha() {
		return solFalha;
	}

	public void setSolFalha(SolFalha solFalha) throws CloneNotSupportedException {
		this.solFalha = solFalha;
		if (this.solFalha == null) {
			this.solFalha = new SolFalha();
		}else{
			this.solFalha = (SolFalha) solFalha.clone();
		}
	}

	public List<SolFalha> getSolFalhas() {
		return solFalhas;
	}

	public void setSolFalhas(List<SolFalha> solFalhas) {
		this.solFalhas = solFalhas;
	}

	public List<Falha> getFalhas() {
		return falhas;
	}

	public void setFalhas(List<Falha> falhas) {
		this.falhas = falhas;
	}
}