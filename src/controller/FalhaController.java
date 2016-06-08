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


/** Classe FalhaController responsavel por unir as views referentes da classe falha.
*
* @author silas
*
*/

@ManagedBean(name="cadastroFalhaBean")
@RequestScoped
public class FalhaController implements Serializable{

	private Repositorios repositorios = new Repositorios();
	private Falha falha = new Falha();
	private List<Falha> falhas  = new ArrayList<Falha>();
	private List<Servico> servicos  = new ArrayList<Servico>();

	/**Metodo de inicialização, responsavel por inicializar todas as listas desta classe.
	 * @see respositorios, utilizando a instancia que implementa das interfaces IFalha, IServico respectivamente.
	*/
	@PostConstruct
	public void init(){
		IFalha falhas = this.repositorios.getFalha();
		IServico servicos = this.repositorios.getServico();
		this.falhas = falhas.listar();
		this.servicos = servicos.listar();
	}


	/** Metodo que cria uma falha.
	 * @see respositorios, utilizando a instancia que implementa a interface da classe falha.
	 */
	public void cadastrar(){
		//Interface falha é instanciada com sua implementação
		IFalha falhas = this.repositorios.getFalha();
		//Chamando o metodos salvar falha.
		falhas.salvar(falha);
	}

	/** Metodo que atualiza uma falha.
	 * @see respositorios, utilizando a instancia que implementa a interface da classe falha.
	 */
	public void update(Falha falha){
		//Interface falha é instanciada com sua implementação
		IFalha falhas = this.repositorios.getFalha();
		//Chamando o metodos editar falha.
		falhas.editar(falha);
	}

	/** Metodo que remove uma falha.
	 * @see respositorios, utilizando a instancia que implementa a interface da classe falha.
	 */
	public void excluir(Falha falha){
		//Interface falha é instanciada com sua implementação
		IFalha falhas = this.repositorios.getFalha();
		//Chamando o metodo remove falha.
		falhas.remover(falha);
		//Chama o metodo init para atualizar as listas da falha
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