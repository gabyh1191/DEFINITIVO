package DAO;

import Principal.Sede;

public interface SedeDAO {
	public boolean mostrarSedes();
	public boolean idSedeValido(int id);
	public boolean existeSede(String nom, int idpais);
	public void modificarNombre(int id, String nom);
	public void modificarCapacidad(int id, int cap);
	public void modificarPais(int id, int idpais);
	public void eliminarSede(int sede);
	public void ingresarSede(Sede sede);
	public Sede getSede(int id);
}
