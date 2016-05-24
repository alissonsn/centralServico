package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import modelo.IRede;
import modelo.ISetor;
import util.Repositorios;
import entidades.Rede;
import entidades.Setor;

@ManagedBean(name="cadastroSetorBean")
@RequestScoped
public class SetorController implements Serializable{

	private Repositorios repositorios = new Repositorios();
	private Setor setor = new Setor();
	private List<Setor> setores = new ArrayList<Setor>();
	

	@PostConstruct
	public void init(){
		ISetor setores = this.repositorios.getSetor();
		this.setores = setores.listar();
	}


	public void cadastrar(){
		ISetor setores= this.repositorios.getSetor();
		setores.salvar(setor);
		this.setor = new Setor();

		String msg = "Cadastro efetuado com sucesso!";
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}

	public void update(Setor setor){
		ISetor setores= this.repositorios.getSetor();
		setores.editar(setor);
	}

	public void excluir(Setor setor){
		ISetor setores= this.repositorios.getSetor();
		setores.remover(setor);
		this.init();
	}


	public Setor getSetor() {
		return setor;
	}


	public void setSetor(Setor setor) throws CloneNotSupportedException {
		this.setor = setor;
		if (this.setor == null) {
			this.setor = new Setor();
		}else{
			this.setor = (Setor) setor.clone();
		}
	}


	public List<Setor> getSetores() {
		return setores;
	}


	public void setSetores(List<Setor> setores) {
		this.setores = setores;
	}
}