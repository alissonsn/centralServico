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
import modelo.IServico;
import entidades.Falha;
import entidades.Servico;
import util.Repositorios;


@ManagedBean(name="cadastroFalhaBean")
@RequestScoped
public class FalhaController implements Serializable{

	private Repositorios repositorios = new Repositorios();
	private Falha falha = new Falha();
	private List<Falha> falhas  = new ArrayList<Falha>();
	private List<Servico> servicos  = new ArrayList<Servico>();

	@PostConstruct
	public void init(){
		IFalha falhas = this.repositorios.getFalha();
		IServico servicos = this.repositorios.getServico();
		this.falhas = falhas.listar();
		this.servicos = servicos.listar();
	}


	public void cadastrar(){
		IFalha falhas = this.repositorios.getFalha();
		falhas.salvar(falha);
		this.falha = new Falha();


		String msg = "Cadastro efetuado com sucesso!";
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}

	public void update(Falha falha){
		IFalha falhas = this.repositorios.getFalha();
		falhas.editar(falha);
	}

	public void excluir(Falha falha){
		IFalha falhas = this.repositorios.getFalha();
		falhas.remover(falha);
		this.init();
	}


	public Falha getFalha() {
		return falha;
	}


	public void setFalha(Falha falha) throws CloneNotSupportedException {
		this.falha = falha;
		if (this.falha == null) {
			this.falha = new Falha();
		}else{
			this.falha = (Falha) falha.clone();
		}
	}


	public List<Falha> getFalhas() {
		return falhas;
	}


	public void setFalhas(List<Falha> falhas) {
		this.falhas = falhas;
	}


	public List<Servico> getServicos() {
		return servicos;
	}


	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}
}