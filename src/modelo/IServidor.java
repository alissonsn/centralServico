package modelo;

import java.util.List;


import entidades.Servidor;

/** Interface que contém as assinaturas de metodos do servidor.
*
* @author silas
*
*/
public interface IServidor {
	
	/** Metodo que consulta todas os servidores cadastrados.
	 *
	 * @return List<Servidor>, Contendo todos os servidores cadastrados.
	 */
	public List<Servidor> listar();
	
	/** Metodo que consulta servidor especifico por seu codigo de identificação.
	 * @param codigo, codigo de identificação do servidor. 
	 * @return Servidor, retorna o servidor cadastrado.
	 */
	public Servidor porCodigo(Integer codigo);
	
	/** Metodo que cria servidor.
	 * @param servidor, requer objeto servidor para sua criação.
	 * @return Servidor, retorna o servidor cadastrado.
	 */
	public Servidor salvar(Servidor servidor);
	
	/** Metodo que remove servidor.
	 * @param servidor, requer objeto servidor para sua remoção.
	 */
	public void remover(Servidor servidor);
	
	/** Metodo que atualiza servidor.
	 * @param servidor, requer objeto servidor para sua atualização.
	 */
	public void editar(Servidor servidor);
}