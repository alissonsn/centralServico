package modelo;

import java.util.ArrayList;

import entidades.Nslcd;




public interface NslcdDAO {
	
	
	public boolean login(Nslcd nslcd);
	
	
	public void logout();
	
	
	public Nslcd listAbobora(Nslcd nslcd);
	
	
	public Nslcd poruid(String uid);


	public ArrayList<Nslcd> findAll(String sistemaOperacional);


	public void migrate(Nslcd nslcd, String sistemaOperacional, String flagAdmin);
}