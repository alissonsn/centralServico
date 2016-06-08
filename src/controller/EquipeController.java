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
import entidades.Equipe;
import util.Repositorios;

/** Classe EquipeController responsavel por unir as views referentes da classe equipe.
*
* @author silas
*
*/

@ManagedBean(name="cadastroEquipeBean")
@RequestScoped
public class EquipeController implements Serializable{

	private Repositorios repositorios = new Repositorios();
	private Equipe equipe = new Equipe();
	private List<Equipe> equipes = new ArrayList<Equipe>();

	/**Metodo de inicialização, responsavel por inicializar todas as listas desta classe.
	 * @see respositorios, utilizando a instancia que implementa da interface IEquipe.
	*/
	@PostConstruct
	public void init(){
		IEquipe equipes = this.repositorios.getEquipe();
		this.equipes = equipes.listar();
	}

	/** Metodo que cria uma equipe.
	 * @see respositorios, utilizando a instancia que implementa a interface da classe equipe.
	 */
	public void cadastrar(){
		//Interface equipe é instanciada com sua implementação
		IEquipe equipes = this.repositorios.getEquipe();
		//Chamando o metodos salvar equipe
		equipes.salvar(equipe);
		
	}

	/** Metodo que atualiza uma equipe.
	 * @param equipe, objeto equipe a ser atualizado
	 * @see respositorios, utilizando a instancia que implementa a interface da classe equipe.
	 */	
	public void update(Equipe equipe){
		//Interface equipe é instanciada com sua implementação
		IEquipe equipes = this.repositorios.getEquipe();
		//Chamando o metodos editar equipe
		equipes.editar(equipe);
	}

	/** Metodo que remove uma equipe.
	 * @param equipe, objeto equipe a ser removido
	 * @see respositorios, utilizando a instancia que implementa a interface da classe equipe.
	 */
	public void excluir(Equipe equipe){
		//Interface equipe é instanciada com sua implementação
		IEquipe equipes = this.repositorios.getEquipe();
		//Chamando o metodos remover equipe
		equipes.remover(equipe);
		//Chama o metodo init para atualizar as listas da equipe
		this.init();
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) throws CloneNotSupportedException {
		this.equipe = equipe;
		if (this.equipe == null) {
			this.equipe = new Equipe();
		}else{
			this.equipe = (Equipe) equipe.clone();
		}
	}

	public List<Equipe> getEquipes() {
		return equipes;
	}

	public void setEquipes(List<Equipe> equipes) {
		this.equipes = equipes;
	}
}