package DAO;

import Principal.Futbolista;

public interface FutbolistaDAO {
	//declaración de métodos para acceder a la base de datos
	public boolean existeFutbolista (int dni, int idpais);
	public void ingresarFutbolista(Futbolista fut);
}
