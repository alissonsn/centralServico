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
import modelo.IRede;
import util.Repositorios;
import entidades.Equipe;
import entidades.Rede;

@ManagedBean(name="cadastroRedeBean")
@RequestScoped
public class RedeController implements Serializable{

	private Repositorios repositorios = new Repositorios();
	private Rede rede = new Rede();
	private List<Rede> redes = new ArrayList<Rede>();

	@PostConstruct
	public void init(){
		IRede redes = this.repositorios.getRede();
		this.redes = redes.listar();
	}


	public void cadastrar(){
		IRede redes = this.repositorios.getRede();
		redes.salvar(rede);
		this.rede = new Rede();


		String msg = "Cadastro efetuado com sucesso!";
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}

	public void update(Rede rede){
		IRede redes = this.repositorios.getRede();
		redes.editar(rede);
	}

	public void excluir(Rede rede){
		IRede redes = this.repositorios.getRede();
		redes.remover(rede);
		this.init();
	}



	public Rede getRede() {
		return rede;
	}


	public void setRede(Rede rede) throws CloneNotSupportedException {
		this.rede = rede;
		if (this.rede == null) {
			this.rede = new Rede();
		}else{
			this.rede = (Rede) rede.clone();
		}
	}


	public List<Rede> getRedes() {
		return redes;
	}


	public void setRedes(List<Rede> redes) {
		this.redes = redes;
	}
}