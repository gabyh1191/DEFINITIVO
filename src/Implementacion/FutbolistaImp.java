package Implementacion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import DAO.FutbolistaDAO;
import Principal.Futbolista;

public class FutbolistaImp implements FutbolistaDAO {
	private Statement sent;

	public FutbolistaImp(Statement sent) {
		this.sent = sent;
	}

	/**
	 * Verifica si existe un futbolista en la base de datos con dados dni e idpais
	 **/
	public boolean existeFutbolista(int dni, int idpais) {
		try {
			ResultSet resul = sent
					.executeQuery("SELECT * FROM futbolista WHERE docIdentidad=" + dni + " AND idpais=" + idpais);
			return resul.next(); // devuelve true si el futbolista existe, false si no
		} catch (SQLException e) {
			System.out.println("ERROR SQL (existeFutbolista): " + e.getMessage());
			return false;
		}
	}

	/** Ingresa al futbolista recibido como parametro en la base de datos **/
	public void ingresarFutbolista(Futbolista f) {
		// Almacenamiento en la base de datos del futbolista ingresado
		String query = "INSERT INTO futbolista(nombre,apellido,docIdentidad,telefono,email," + "idPais) " + "VALUES ('"
				+ f.getNombre() + "','" + f.getApellido() + "'," + f.getDocID() + "," + f.getTelefono() + ",'"
				+ f.getEmail() + "'," + f.getIdPais() + ")";
		try {
			sent.executeUpdate(query);
			System.out.println("Se ha agregado a " + f.getNombre() + " " + f.getApellido() + " a la base de datos.\n");
		} catch (SQLException e) {
			System.out.println("ERROR SQL (ingresarFutbolista): " + e.getMessage());
		}
	}
}
