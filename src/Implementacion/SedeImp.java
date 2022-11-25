package Implementacion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import DAO.FactoryDAO;
import DAO.PaisDAO;
import DAO.SedeDAO;
import Principal.*;

public class SedeImp implements SedeDAO {
	private Statement sent;

	public SedeImp(Statement sent) {
		this.sent = sent;
	}

	/** Verifica que el id ingresado sea valido **/
	public boolean mostrarSedes() {
		ResultSet resul;
		try {
			PaisDAO p = FactoryDAO.getPaisDAO();
			LinkedList<Pais> paises = p.getPaises();
			resul = sent.executeQuery("SELECT * FROM SEDE ORDER BY idsede ASC");
			if (!resul.next())
				System.out.println("No hay sedes cargadas. Primero ingrese una.");
			else {
				System.out.println("-----------LISTA SEDES-----------");
				String pais = "error";
				do {
					for (Pais ps : paises) {
						if (ps.getId() == resul.getInt("idpais")) {
							pais = ps.getNombre();
							break;
						}
					}
					int idsede = resul.getInt("idsede");
					Sede s = new Sede(resul.getString("nombre"), resul.getInt("capacidad"), resul.getInt("idpais"));
					System.out.println("\t" + idsede + "\t" + s.getNombre() + ", " + pais + " (Capacidad: "+resul.getInt("capacidad")+")");
				} while (resul.next());
				System.out.println("\t0\tEXIT");
				System.out.println("----------------------------------");
				return true;
			}
		} catch (SQLException e) {
			System.out.println("ERROR SQL (mostrarSedes): " + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	/** Verifica que el id ingresado sea valido **/
	public boolean idSedeValido(int id) {
		ResultSet resul;
		try {
			resul = sent.executeQuery("SELECT * FROM SEDE WHERE idsede=" + id);
			return resul.next(); // devuelve true si se encuentra en la base de datos
		} catch (SQLException e) {
			System.out.println("ERROR SQL (idSedeValido): " + e.getMessage());
			return false;
		}
	}

	/** Verifica si una sede ya se encuentra en la base de datos **/
	public boolean existeSede(String nom, int idpais) {
		try {
			ResultSet resul = sent.executeQuery("SELECT * FROM SEDE WHERE nombre='" + nom + "' AND idpais=" + idpais);
			return resul.next(); // devuelve true si existe la sede en la base datos, false si no
		} catch (SQLException e) {
			System.out.println("ERROR SQL (existeSede): " + e.getMessage());
			return false;
		}
	}

	/** Modifica el nombre de una sede especifica **/
	public void modificarNombre(int id, String nom) {
		try {
			sent.executeUpdate("UPDATE sede SET nombre='" + nom + "' WHERE idsede=" + id);
			System.out.println("Se ha modificado exitosamente.");
		} catch (SQLException e) {
			System.out.println("ERROR SQL (modificarNombreSede)");
		}
	}

	/** Modifica la capacidad de una sede especifica **/
	public void modificarCapacidad(int id, int cap) {
		try {
			sent.executeUpdate("UPDATE sede SET capacidad='" + cap + "' WHERE idsede=" + id);
			System.out.println("Se ha modificado exitosamente.");
		} catch (SQLException e) {
			System.out.println("ERROR SQL (modificarCapacidadSede)");
		}
	}

	/** Modifica el pais de una sede especifica */
	public void modificarPais(int id, int idpais) {
		try {
			sent.executeUpdate("UPDATE sede SET idpais='" + idpais + "' WHERE idsede=" + id);
			System.out.println("Se ha modificado exitosamente.");
		} catch (SQLException e) {
			System.out.println("ERROR SQL (modificarPaisSede)");
		}
	}

	/** Elimina la sede ingresada por el usuario **/
	public void eliminarSede(int sede) {
		try {
			String query = "DELETE FROM sede WHERE idsede=" + sede;
			sent.executeUpdate(query);
			System.out.println("Se ha modificado exitosamente.");
		} catch (SQLException e) {
			System.out.println("ERROR SQL (eliminarSede): " + e.getMessage());
		}
	}

	/** Ingreso por teclado de una sede. **/
	public void ingresarSede(Sede sede) {
		try {
			sent.executeUpdate("INSERT INTO sede (nombre,capacidad,idpais) VALUES ('" + sede.getNombre() + "', "
					+ sede.getCapacidad() + "," + sede.getPais() + ");");
			System.out.println("Se ha agregado " + sede.getNombre() + " a la base de datos.\n");
		} catch (SQLException e) {
			System.out.println("ERROR SQL (ingresarSede): " + e.getMessage());
		}
	}

	/** Devuelve la sede, dado el id recibido como parámetro **/
	public Sede getSede(int id) {
		ResultSet resul;
		try {
			resul = sent.executeQuery("SELECT * FROM SEDE WHERE idsede=" + id);
			if (resul.next()) {
				return new Sede(resul.getString("nombre"), resul.getInt("capacidad"), resul.getInt("idpais"));
			}
		} catch (SQLException e) {
			System.out.println("ERROR SQL (getSede): " + e.getMessage());
		}
		return null;
	}
}
