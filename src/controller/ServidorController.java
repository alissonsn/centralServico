package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import modelo.IResponsavel;
import modelo.IServidor;
import util.Repositorios;
import entidades.Responsavel;
import entidades.Servidor;



@ManagedBean(name="cadastroServidorBean")
@RequestScoped
public class ServidorController implements Serializable{

	private Repositorios repositorios = new Repositorios();
	private Servidor servidor = new Servidor();
	private List<Servidor> servidores = new ArrayList<Servidor>();
	private List<Responsavel> responsaveis = new ArrayList<Responsavel>();



	@PostConstruct
	public void init(){
		IResponsavel responsaveis = this.repositorios.getResponsavel();
		IServidor servidores = this.repositorios.getServidor();
		this.responsaveis = responsaveis.listar();
		this.servidores = servidores.listar();
	}

	public void cadastrar(){
		IServidor servidores = this.repositorios.getServidor();
		servidores.salvar(servidor);
		this.servidor = new Servidor();


		String msg = "Cadastro efetuado com sucesso!";
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}

	public void update(Servidor servidor){
		IServidor servidores = this.repositorios.getServidor();
		servidores.editar(servidor);
	}

	public void excluir(Servidor servidor){
		IServidor servidores = this.repositorios.getServidor();
		servidores.remover(servidor);
		this.init();
	}


	public Servidor getServidor() {
		return servidor;
	}


	public void setServidor(Servidor servidor) throws CloneNotSupportedException {
		this.servidor = servidor;
		if (this.servidor == null) {
			this.servidor = new Servidor();
		}else{
			this.servidor = (Servidor) servidor.clone();
		}
	}


	public List<Servidor> getServidores() {
		return servidores;
	}


	public void setServidores(List<Servidor> servidores) {
		this.servidores = servidores;
	}


	public List<Responsavel> getResponsaveis() {
		return responsaveis;
	}


	public void setResponsaveis(List<Responsavel> responsaveis) {
		this.responsaveis = responsaveis;
	}



}