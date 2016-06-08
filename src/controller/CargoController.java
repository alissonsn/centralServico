package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import entidades.Cargo;
import modelo.ICargo;
import util.Repositorios;

/** Classe CargoController responsavel por unir as views referentes da classe cargo.
*
* @author silas
*
*/

@ManagedBean(name="cadastroCargoBean")
@RequestScoped
public class CargoController implements Serializable{

	private Repositorios repositorios = new Repositorios();
	private Cargo cargo = new Cargo();
	private List<Cargo> cargos = new ArrayList<Cargo>();
	
	/**Metodo de inicialização, responsavel por inicializar todas as listas desta classe.
	 * @see respositorios, utilizando a instancia que implementa da interface Icargo.
	*/
	@PostConstruct
	public void init(){
		ICargo cargos = this.repositorios.getCargo();
		this.cargos = cargos.listar();
	}

	/** Metodo que cria uma cargo.
	 * @see respositorios, utilizando a instancia que implementa a interface da classe cargo.
	 */
	public void cadastrar(){
		//Interface cargo  é instanciada com sua implementação
		ICargo cargos = this.repositorios.getCargo();
		//Chamando o metodos salvar cargo
		cargos.salvar(cargo);
	}

	/** Metodo que atualiza um cargo.
	 * @param cargo, objeto atividade a ser atualizado
	 * @see respositorios, utilizando a instancia que implementa a interface da classe cargo.
	 */
	public void update(Cargo cargo){
		//Interface cargo  é instanciada com sua implementação
		ICargo cargos = this.repositorios.getCargo();
		//Chamando o metodos editar cargo
		cargos.editar(cargo);
	}

	/** Metodo que remove um cargo.
	 * @param cargo, objeto atividade a ser removido
	 * @see respositorios, utilizando a instancia que implementa a interface da classe cargo.
	 */
	public void excluir(Cargo cargo){
		//Interface cargo  é instanciada com sua implementação
		ICargo cargos = this.repositorios.getCargo();
		//Chamando o metodos remover cargo
		cargos.remover(cargo);
		//Chama o metodo init para atualizar as listas da cargo
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