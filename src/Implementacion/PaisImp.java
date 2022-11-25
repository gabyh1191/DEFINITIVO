package Implementacion;

import java.sql.*;
import java.util.LinkedList;

import DAO.PaisDAO;
import Principal.Pais;

public class PaisImp implements PaisDAO {
	private Statement sent;

	public PaisImp(Statement sent) {
		this.sent = sent;
	}

	/** Verifica si un pais ya se encuentra en la base de datos **/
	public boolean existePais(String nom) {
		try {
			ResultSet resul = sent.executeQuery("SELECT * FROM PAIS WHERE nombre='" + nom + "'");
			return resul.next(); // devuelve true si el pais existe, false si no
		} catch (SQLException e) {
			System.out.println("ERROR SQL (idSedeValido): " + e.getMessage());
			return false;
		}
	}

	/** Ingresa un pais a la base de datos **/
	public void ingresarPais(String nom, String idioma) {
		try {
			sent.executeUpdate("INSERT INTO pais (nombre,idioma) VALUES ('" + nom + "', '" + idioma + "');");
			System.out.println("Se ha agregado " + nom + " a la base de datos.\n");
		} catch (SQLException e) {
			System.out.println("ERROR SQL (ingresarPais): " + e.getMessage());
		}
	}

	/** Muestra listado de paises **/
	public boolean mostrarPaises() {
		try {
			ResultSet resul = sent.executeQuery("SELECT * FROM PAIS ORDER BY idpais ASC");
			if (!resul.next()) {
				System.out.println("No hay paises cargados, primero ingrese un pais.");
			} else {
				System.out.println("\n-----------LISTA PAISES-----------");
				
				do {
					System.out.println(
							"\t" + resul.getInt(1) + "	" + resul.getString(2) + "(" + resul.getString(3) + ")");
				} while (resul.next());
				System.out.println("\t0\tEXIT");
				System.out.println("----------------------------------");
				return true;
			}
		} catch (SQLException e) {
			System.out.println("ERROR SQL (mostrarPaises): " + e.getMessage());
		}
		return false;
	}

	/**
	 * Verifica que el id ingresado coincida con el id de uno de los paises
	 * almacenados en la base de datos
	 **/
	public boolean idPaisValido(int id) {
		ResultSet resul;
		try {
			resul = sent.executeQuery("SELECT * FROM PAIS WHERE idpais=" + id);
			return resul.next();// devuelve true si el id coincide con un pais, false sino
		} catch (SQLException e) {
			System.out.println("ERROR SQL (idPaisValido): " + e.getMessage());
			return false;
		}
	}

	/** Devuelve el nombre del pais, dado el id recibido como parámetro **/
	public String getNombrePais(int id) {
		ResultSet resul = null;
		try {
			resul = sent.executeQuery("SELECT nombre,idioma FROM PAIS WHERE idpais=" + id);
			if (resul.next()) {
				String nom = resul.getString("nombre");
				resul.close();
				return nom;
			}
		} catch (SQLException e) {
			System.out.println("ERROR SQL (getPais): " + e.getMessage());
		}
		return "error";
	}

	// devuelve todos los paises
	@Override
	public LinkedList<Pais> getPaises() {
		try {
			ResultSet resul = sent.executeQuery("SELECT * FROM PAIS");
			LinkedList<Pais> l = new LinkedList<Pais>();
			while (resul.next()) {
				l.add(new Pais(resul.getInt("idpais"), resul.getString("nombre"), resul.getString("idioma")));
			}
			return l;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
