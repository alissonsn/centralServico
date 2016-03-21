package modelo;

import java.util.List;

import entidades.Zona;

public interface ZonaDAO {
	public void createZonaDireta(Zona dns);
	public void createZonaReversa(Zona dns);
	public void update(Zona dns);
	public void delete(String uid);
	public Zona find(String uid);
	public List<Zona> findAll();
}
