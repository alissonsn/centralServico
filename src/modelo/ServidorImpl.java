package modelo;

import java.util.List;


import entidades.Servidor;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import modelo.IServidor;


/** Classe ServidorImpl que implementa a Interface IServidor, esta classe implementa crud da entidade Servidor.
*
* @author silas
*
*/
public class ServidorImpl implements IServidor{
	
	//Atributo de sessao do hibernate
	private Session sessao;

	/**Construtor da desta classe que inicializa o atributo de sessao.
	*/
	public ServidorImpl(Session sessao){
		this.sessao = sessao;
	}
	
	/** Metodo que consulta todas os servidores cadastrados.
	 *
	 * @return List<Servidor>, Contendo todos os servidores cadastrados ordenados por seu hostname.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Servidor> listar() {
		return sessao.createCriteria(Servidor.class).addOrder(Order.asc("hostname")).list();
	}

	/** Metodo que consulta servidor especifico por seu codigo de identificação.
	 * @param codigo, codigo de identificação do servidor. 
	 * @return Servidor, retorna o servidor cadastrado.
	 */
	@Override
	public Servidor porCodigo(Integer codigo) {
		return (Servidor) sessao.get(Servidor.class, codigo);
	}

	/** Metodo que cria servidor.
	 * @param servidor, requer objeto servidor para sua criação.
	 * @return Servidor, retorna o servidor cadastrado.
	 */
	@Override
	public Servidor salvar(Servidor servidor) {
		return (Servidor) sessao.merge(servidor);
	}

	/** Metodo que remove servidor.
	 * @param servidor, requer objeto servidor para sua remoção.
	 */
	@Override
	public void remover(Servidor servidor) {
		this.sessao.delete(servidor);
	}

	/** Metodo que atualiza servidor.
	 * @param servidor, requer objeto servidor para sua atualização.
	 */
	@Override
	public void editar(Servidor servidor) {
		this.sessao.update(servidor);
	}
}