package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import modelo.IAtividade;
import modelo.IEquipe;
import modelo.IServico;

import entidades.Atividade;
import entidades.Equipe;
import entidades.Servico;
import util.Repositorios;

/** Classe AtividadeController responsavel por unir as views referentes da classe atividade.
*
* @author silas
*
*/

@ManagedBean(name="cadastroAtividadeBean")
@RequestScoped
public class AtividadeController implements Serializable{
	
	private Repositorios repositorios = new Repositorios();
	private Atividade atividade = new Atividade();
	private List<Atividade> atividades = new ArrayList<Atividade>();
	private List<Equipe> equipes  = new ArrayList<Equipe>();
	private List<Servico> servicos = new ArrayList<Servico>();

	/**Metodo de inicialização, responsavel por inicializar todas as listas desta classe.
	 * @see respositorios, utilizando a instancia que implementa das interfaces IAtividade, IEqquipe, IServico respectivamente.
	*/
	@PostConstruct
	public void init(){
		IAtividade atividades = this.repositorios.getAtividade();
		IEquipe equipes = this.repositorios.getEquipe();
		IServico servicos = this.repositorios.getServico();
		this.atividades = atividades.listar();
		this.equipes = equipes.listar();
		this.servicos = servicos.listar();
	}

	/** Metodo que cria uma atividade.
	 * @see respositorios, utilizando a instancia que implementa a interface da classe atividade.
	 */
	public void cadastrar(){
		//Interface atividade é instanciada com sua implementação
		IAtividade atividades = this.repositorios.getAtividade();
		//Chamando o metodos salvar atividade
		atividades.salvar(atividade);
	}

	/** Metodo que atualiza uma atividade.
	 * @param atividade, objeto atividade a ser atualizado
	 * @see respositorios, utilizando a instancia que implementa a interface da classe atividade.
	 */
	public void update(Atividade atividade){
		//Interface atividade é instanciada com sua implementação
		IAtividade atividades = this.repositorios.getAtividade();
		//Chamando o metodo editar atividade
		atividades.editar(atividade);
	}

	/** Metodo que remove uma atividade.
	 * @param atividade, objeto atividade a ser removido
	 * @see respositorios, utilizando a instancia que implementa a interface da classe atividade.
	 */
	public void remover(Atividade atividade){
		//Interface atividade é instanciada com sua implementação
		IAtividade atividades = this.repositorios.getAtividade();
		//Chamando o metodo remove atividade
		atividades.remover(atividade);
		//Chama o metodo init para atualizar as listas da atividade
		this.init();
	}

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) throws CloneNotSupportedException {
		this.atividade = atividade;
		if (this.atividade == null) {
			this.atividade = new Atividade();
		}else{
			this.atividade = (Atividade) atividade.clone();
		}
	}

	public List<Atividade> getAtividades() {
		return atividades;
	}

	public void setAtividades(List<Atividade> atividades) {
		this.atividades = atividades;
	}

	public List<Equipe> getEquipes() {
		return equipes;
	}

	public void setEquipes(List<Equipe> equipes) {
		this.equipes = equipes;
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}
}