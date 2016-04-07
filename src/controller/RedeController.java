package controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import modelo.IRede;
import entidades.Rede;
import util.Repositorios;

@ManagedBean(name="cadastroRedeBean")
@RequestScoped
public class RedeController {
	private Repositorios repositorios = new Repositorios();
	private Rede rede = new Rede();
	private List<Rede> listaredes = new ArrayList<Rede>();
	
	
	@PostConstruct
	public void init(){
		IRede redes = this.repositorios.getRede();
		this.listaredes = redes.listar();
	}
	
	public void cadastrar(Rede rede){
		IRede redes = this.repositorios.getRede();
		redes.criar(rede);
		this.rede = new Rede();
	}

	public void remover(Rede rede){
		IRede redes = this.repositorios.getRede();
		redes.deletar(rede);
		
	}
	
	public void atualizar(Rede rede){
		IRede redes = this.repositorios.getRede();
		redes.atualizar(rede);
		this.init();
	}
	
	public Rede getRede() {
		return rede;
	}



	public void setRede(Rede rede) {
		this.rede = rede;
	}

	public List<Rede> getListaredes() {
		return listaredes;
	}

	public void setListaredes(List<Rede> listaredes) {
		this.listaredes = listaredes;
	}

	
}
