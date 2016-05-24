package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import entidades.Cargo;
import entidades.Setor;
import modelo.ICargo;
import modelo.ISetor;
import util.Repositorios;

@ManagedBean(name="cadastroCargoBean")
@RequestScoped
public class CargoController implements Serializable{

	private Repositorios repositorios = new Repositorios();
	private Cargo cargo = new Cargo();
	private List<Cargo> cargos = new ArrayList<Cargo>();
	

	@PostConstruct
	public void init(){
		ICargo cargos = this.repositorios.getCargo();
		this.cargos = cargos.listar();
		
	}


	public void cadastrar(){
		ICargo cargos = this.repositorios.getCargo();
		cargos.salvar(cargo);
		this.cargo = new Cargo();

		String msg = "Cadastro efetuado com sucesso!";
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}

	public void update(Cargo cargo){
		ICargo cargos = this.repositorios.getCargo();
		cargos.editar(cargo);
	}

	public void excluir(Cargo cargo){
		ICargo cargos = this.repositorios.getCargo();
		cargos.remover(cargo);
		this.init();
	}
	
	public Cargo getCargo() {
		return cargo;
	}


	public void setCargo(Cargo cargo) throws CloneNotSupportedException {
		this.cargo = cargo;
		if (this.cargo == null) {
			this.cargo = new Cargo();
		}else{
			this.cargo = (Cargo) cargo.clone();
		}
	}


	public List<Cargo> getCargos() {
		return cargos;
	}


	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}
}