package controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import modelo.IRede;
import util.Repositorios;
import entidades.Rede;

@ManagedBean(name = "cadastroRedeBean")
@RequestScoped
public class RedeController {
	private Repositorios repositorios = new Repositorios();
	private Rede rede = new Rede();
	private List<Rede> listaredes = new ArrayList<Rede>();
	private List<Rede> listaRede = new ArrayList<Rede>();
	private boolean exibeMsgListaVazia = false;
	
	@PostConstruct
	public void init() {
		IRede redes = this.repositorios.getRede();
		this.listaredes = redes.listar();
	}

	public void cadastrar(Rede rede) {
		IRede redes = this.repositorios.getRede();
		redes.criar(rede);
		this.rede = new Rede();
	}

	public void remover(Rede rede) {
		IRede redes = this.repositorios.getRede();
		redes.deletar(rede);

	}

	public void atualizar(Rede rede) {
		IRede redes = this.repositorios.getRede();
		redes.atualizar(rede);
		this.init();
	}
	
	public List<Rede> listarRede(){
		IRede redes = this.repositorios.getRede();
		listaRede = redes.Buscarede(rede);
		if(listaRede.isEmpty()){
			exibeMsgListaVazia = true;
		}
		return listaRede;
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
	
	public List<Rede> getListaRede() {
		return listaRede;
	}

	public void setListaRede(List<Rede> listaRede) {
		this.listaRede = listaRede;
	}
	public boolean setexibeMsgListaVazia(){
		return exibeMsgListaVazia;
	}
	public boolean getexibeMsgListaVazia(){
		return exibeMsgListaVazia;
	}
}
