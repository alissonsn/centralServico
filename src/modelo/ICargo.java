package modelo;

import java.util.List;

import entidades.Cargo;

/** Interface que contém as assinaturas de metodos dos cargos.
*
* @author silas
*
*/

public interface ICargo {
	
	/** Metodo que consulta todos os cargos cadastrados.
	 *
	 * @return List<Cargo>, Contendo todos os cargos cadastrados ordenados.
	 */
	public List<Cargo> listar();
	
	/** Metodo que consulta cargo especificoo por seu codigo de identificação.
	 * @param codigo, codigo de identificação da cargo. 
	 * @return Cargo, retorna o cargo cadastrado.
	 */
	public Cargo porCodigo(Integer codigo);
	
	/** Metodo que cria um cargo.
	 * @param cargo, requer objeto cargo para sua criação.
	 * @return Cargo, retorna a cargo cadastrado.
	 */
	public Cargo salvar(Cargo cargo);
	
	/** Metodo que remove cargo.
	 * @param cargo, requer objeto cargo para sua remoção.
	 */
	public void remover(Cargo cargo);
	
	/** Metodo que atualiza cargo.
	 * @param cargo, requer objeto cargo para sua atualização.
	 */
	public void editar(Cargo cargo);
}