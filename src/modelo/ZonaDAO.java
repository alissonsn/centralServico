package modelo;

import java.util.List;

import entidades.Zona;

public interface ZonaDAO {
	
	/** Metodo que cria zona direta.
	 * @param zona, requer objeto zona  para sua criação.
	 */
	public void createZonaDireta(Zona zona);
	
	/** Metodo que cria zona reversa.
	 * @param zona, requer objeto zona  para sua criação.
	 */
	public void createZonaReversa(Zona zona);
}
