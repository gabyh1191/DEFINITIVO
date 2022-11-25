package DAO;

import java.util.LinkedList;
import Principal.Pais;

public interface PaisDAO {
	public boolean existePais (String nom);
	public void ingresarPais(String nom, String idioma);
	public boolean mostrarPaises();
	public boolean idPaisValido(int id);
	public String getNombrePais(int id);
	public LinkedList<Pais> getPaises();
}
