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
import entidades.Equipe;
import entidades.IpResponsavel;
import entidades.Responsavel;
import entidades.Setor;
import modelo.ICargo;
import modelo.IEquipe;
import modelo.IIpResponsavel;
import modelo.IResponsavel;
import modelo.ISetor;
import util.Repositorios;

@ManagedBean(name="cadastroIpResponsavelBean")
@RequestScoped
public class IpResponsavelController implements Serializable{

	private Repositorios repositorios = new Repositorios();
	private IpResponsavel ipresponsavel = new IpResponsavel();
	private List<IpResponsavel> ipresponsaveis = new ArrayList<IpResponsavel>();
	private List<Setor> setores = new ArrayList<Setor>();
	private List<Cargo> cargos = new ArrayList<Cargo>();

	@PostConstruct
	public void init(){
		IIpResponsavel ipresponsaveis = this.repositorios.getIpResponsavel();
		ISetor setores = this.repositorios.getSetor();
		ICargo cargos = this.repositorios.getCargo();
		
		this.ipresponsaveis = ipresponsaveis.listar();
		this.setores = setores.listar();
		this.cargos = cargos.listar();
		
	}


	public void cadastrar(){
		IIpResponsavel ipresponsaveis = this.repositorios.getIpResponsavel();
		ipresponsaveis.salvar(ipresponsavel);
		this.ipresponsavel = new IpResponsavel();
		


		String msg = "Cadastro efetuado com sucesso!";
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}

	public void update(IpResponsavel ipresponsavel){
		IIpResponsavel ipresponsaveis = this.repositorios.getIpResponsavel();
		ipresponsaveis.editar(ipresponsavel);
	}

	public void excluir(IpResponsavel ipresponsavel){
		IIpResponsavel ipresponsaveis = this.repositorios.getIpResponsavel();
		ipresponsaveis.remover(ipresponsavel);
		this.init();
	}
	
	
	public IpResponsavel getIpresponsavel() {
		return ipresponsavel;
	}


	public void setIpresponsavel(IpResponsavel ipresponsavel) throws CloneNotSupportedException {
		this.ipresponsavel = ipresponsavel;
		if (this.ipresponsavel == null) {
			this.ipresponsavel = new IpResponsavel();
		}else{
			this.ipresponsavel = (IpResponsavel) ipresponsavel.clone();
		}
	}

	public List<IpResponsavel> getIpresponsaveis() {
		return ipresponsaveis;
	}


	public void setIpresponsaveis(List<IpResponsavel> ipresponsaveis) {
		this.ipresponsaveis = ipresponsaveis;
	}


	public List<Setor> getSetores() {
		return setores;
	}


	public void setSetores(List<Setor> setores) {
		this.setores = setores;
	}


	public List<Cargo> getCargos() {
		return cargos;
	}


	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}
}