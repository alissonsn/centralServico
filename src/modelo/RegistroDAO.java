package modelo;

import java.util.List;

import entidades.Registro;


public interface RegistroDAO {
	public void createRegistroDireto(Registro registro);
	public void createRegistroReverso(Registro registro);
	public void update(Registro registro);
	public void delete(String ip);
	public Registro find(String dominio);
	public List<Registro> findAll();
}
