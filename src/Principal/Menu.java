package Principal;

import sql.miConexion;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import DAO.*;

public class Menu {
	private static Connection con = miConexion.getCon();
	private static Scanner leer = new Scanner(System.in);

	public static void main(String[] args) {
		try {
			Statement sent = con.createStatement();
			FactoryDAO.sent = sent;
			int i;
			System.out.println("\nBIENVENIDO AL MUNDIAL"); // imprime el menu general
			do {
				System.out.println("\n --------Menu--------");
				System.out.println(
						"¿Con que desea trabajar? " + "\n(Ingrese un numero segun la opcion que desea seleccionar)");
				System.out.println("0: EXIT " + "\n1: Futbolista " + "\n2: Pais " + "\n3: Sede");
				System.out.print("Opcion elegida: ");
				i = leer.nextInt(); // lee un entero
				leer.nextLine();
				switch (i) { // dado el entero ingresado, comienza una accion
				case 0:
					System.out.println("\n---------Fin del programa---------");
					break;
				case 1:
					menuFutbolista();// va al metodo menuFutbolista
					break;
				case 2:
					menuPais();// va al metodo menuPais
					break;
				case 3:
					menuSede();// va al metodo menuSede
					break;
				default:
					System.out.println("Opcion no valida");
				}
			} while (i != 0);
		} catch (SQLException e) {
			System.out.println("Error al conectar con la base de datos: " + e.getMessage());
		}

	}

	/** Muestra el menu con las opciones para el futbolista **/
	private static void menuFutbolista() {
		int j;
		System.out.println("\n ---Menu Futbolista--");// imprime el menuFutbolista
		System.out.println("¿Que desea hacer? " + "\n(Ingrese un numero segun la opcion que desea seleccionar)");
		System.out.println("0: BACK " + "\n1: Agregar un futbolista ");
		System.out.print("Opcion elegida: ");
		j = leer.nextInt();// lee un entero
		leer.nextLine();
		switch (j) {// dado el entero ingresado, comienza una accion
		case 0:
			break;
		case 1:
			ingresarDatosFutbolista();
			break;
		default:
			System.out.println("Opcion invalida");
		}
	}

	/** Muestra el menu con las opciones para el pais **/
	private static void menuPais() {
		int j;
		System.out.println("\n ---Menu Pais--");
		System.out.println("¿Que desea hacer? " + "\n(Ingrese un numero segun la opcion que desea seleccionar)");
		System.out.println("0: BACK " + "\n1: Agregar pais ");
		System.out.print("Opcion elegida: ");
		j = leer.nextInt();// lee un entero
		leer.nextLine();
		switch (j) {//
		case 0:
			break;
		case 1:
			ingresarDatosPais();
			break;
		default:
			System.out.println("Opcion invalida");
		}
	}

	/** Muestra el menu con las opciones para la sede **/
	private static void menuSede() {
		int j;
		System.out.println("\n ---Menu Sede--");// imprime menuSede
		System.out.println("¿Que desea hacer? " + "\n(Ingrese un numero segun la opcion que desea seleccionar)");
		System.out.println(
				"0: BACK " + "\n1: Agregar sede " + "\n2: Actualizar datos de una sede" + "\n3: Eliminar una sede");
		System.out.print("Opcion elegida: ");
		j = leer.nextInt();// lee un entero
		leer.nextLine();
		switch (j) {
		case 0:
			break;
		case 1:
			ingresarDatosSede();// va al metodo ingresarDatosSede
			break;
		case 2:
			modificarSede();// va al metodo modificarSede
			break;
		case 3:
			eliminarSede();// va al metodo eliminarSede
			break;
		default:
			System.out.println("Opcion invalida");
		}
	}

	/**
	 * Ingresa los datos de un futbolista y los manda a la base de datos (usando el
	 * dao) para ser almacenados
	 **/
	private static void ingresarDatosFutbolista() {
		// ingreso de datos del futbolista
		System.out.println("\nINGRESO DE UN FUTBOLISTA");
		System.out.print("Nombre: ");
		String nom = leer.nextLine();// lee un string
		System.out.print("Apellido: ");
		String ape = leer.nextLine();// lee un string
		System.out.print("Ingrese dni: ");
		int dni = leer.nextInt();// lee un dni
		leer.nextLine();
		PaisDAO p = FactoryDAO.getPaisDAO();
		int idpais = elegirUnPais(p); // elige entre los paises previamente ingresados.
		// En caso de no haber, retorna -1
		if (idpais != -1 && idpais != 0) {
			FutbolistaDAO f = FactoryDAO.getFutbolistaDAO();
			if (!f.existeFutbolista(dni, idpais)) { // verifica que el futbolista no exista ya en la
				// base de datos (a traves de su dni y pais)
				System.out.print("Ingrese tel: ");
				long tel = leer.nextLong();// lee un long
				leer.nextLine();
				System.out.print("Ingrese email: ");
				String mail = leer.nextLine();// lee un string
				Futbolista fut = new Futbolista(nom, ape, dni, tel, mail, idpais); // instancia un
				// futbolista con los datos ingresados

				System.out.println("Confirme los datos ingresados\n" + fut + "\tPais: " + getPais(idpais));
				System.out.print("\n¿Desea agregar a " + fut.getNombre() + " " + fut.getApellido() + " ("
						+ getPais(idpais) + ") (SI para guardar):   ");// confirma los datos ingresados
				String respuesta = leer.nextLine(); // lee un string
				if (respuesta.equals("SI")) {// si la respuesta coincide, manda al futbolista para que se
					// almacene en la base de datos
					f.ingresarFutbolista(fut);
				} else
					System.out.println("Los datos ingresados no han sido almacenados.");
			} else
				System.out.println("El futbolista ya se encuentra almacenado en la base de datos.");

		} else
			System.out.println("Los datos ingresados no han sido almacenados.");
	}

	/**
	 * Ingresa los datos de un pais y los manda a la base de datos (usando el dao)
	 * para ser almacenados
	 **/
	private static void ingresarDatosPais() {
		// ingreso de datos de un pais
		System.out.println("\nINGRESO DE UN PAIS");
		System.out.print("Nombre: ");
		String nom = leer.nextLine(); // lee un string
		PaisDAO p = FactoryDAO.getPaisDAO();
		if (!p.existePais(nom)) { // verifica que el pais no exista ya en la base de datos (a traves del nombre)
			System.out.print("Idioma: ");
			String idioma = leer.next(); // lee un String
			Pais pa = new Pais(nom, idioma); // instancia un Pais con los datos ingresados
			System.out.print("\nConfirme los datos ingresados:" + pa + "\n¿Desea agregar a " + pa.getNombre()
					+ "? (SI para guardar):   ");// confirma los datos ingresados
			String respuesta = leer.next();
			if (respuesta.equals("SI")) {// si la respuesta coincide, manda el Pais para que se
				// almacene en la base de datos
				p.ingresarPais(nom, idioma);
			} else
				System.out.println("El pais no fue almacenado en la base de datos.");
		} else
			System.out.println("El pais ingresado ya se encuentra en la base de datos.");
	}

	/**
	 * Muestra los paises almacenados en la base de datos y se elige uno entre ellos
	 **/
	private static int elegirUnPais(PaisDAO p) {
		int id = -1;
		boolean valido = false;
		if (p.mostrarPaises()) { // si hay paises los imprime y devuelve true, sino avisa que
			// no hay existencias y devuelve false
			do {
				System.out.print("\nID del pais (): ");
				id = leer.nextInt(); // lee un entero
				if (id != 0) {
					valido = p.idPaisValido(id); // verifica que el id, correspondiente a un pais,
					// sea valido (true si lo es, false si no)
					if (!valido)
						System.out.println("ID invalido");
				} else
					return 0;
			} while (!valido); // mientras que no se ingrese un id valido para un pais, no sale
		}
		return id;
	}

	/** Ingresa una sede a la base de datos **/
	private static void ingresarDatosSede() {
		// Ingreso de datos
		System.out.println("\nINGRESO DE UNA SEDE");
		System.out.print("Nombre: ");
		String nombre = leer.nextLine();
		PaisDAO p = FactoryDAO.getPaisDAO();
		int idpais = elegirUnPais(p);
		if (idpais != -1 && idpais != 0) { // si el pais es valido
			SedeDAO s = FactoryDAO.getSedeDAO();
			if (!s.existeSede(nombre, idpais)) {
				System.out.print("Capacidad: ");
				int capacidad = leer.nextInt();
				leer.nextLine();
				Sede sede = new Sede(nombre, capacidad, idpais);
				System.out.print("\nConfirmar los datos ingresados\n" + sede + "\tPais: " + getPais(idpais)
						+ "\n\n¿Desea agregar " + sede.getNombre() + "? (SI para guardar):   ");
				String respuesta = leer.next();
				if (respuesta.equals("SI")) {
					s.ingresarSede(sede);
				} else
					System.out.println("La sede no fue almacenada en la base de datos.");
			} else
				System.out.println("La sede ya se encuentra almacenada en la base de datos");

		}
	}

	/** Elige una sede de las que estan almacenadas en la base de datos **/
	private static int elegirUnaSede(SedeDAO s) {
		int id = -1;
		boolean valido = false;
		if (s.mostrarSedes()) { // si hay sedes los imprime y devuelve true, sino avisa que
			// no hay existencias y devuelve false
			do {
				System.out.print("\nSede: ");
				id = leer.nextInt(); // lee un entero
				if (id != 0) { // si es 0, el usuario no encontro la sede que queria
					valido = s.idSedeValido(id); // verifica que el id, correspondiente a un pais,
					// sea valido (true si lo es, false si no)
					if (!valido)
						System.out.println("ID invalido");
				} else
					return 0;
			} while (!valido); // mientras que no se ingrese un id valido para un pais, no sale
		}
		return id;
	}

	/** Elimina de la base de datos una sede especifica **/
	private static void eliminarSede() {
		System.out.println("Elija el numero de la sede que desea eliminar: ");
		SedeDAO s = FactoryDAO.getSedeDAO();
		int id = elegirUnaSede(s);// muestra el listado de sedes. Elige una devuelve
		// el id, o devuelve 0 (si no encontro la que queria) o -1 (si ocurrio un error)
		if (id != -1 && id != 0) {
			Sede sede = s.getSede(id);
			System.out.print("\nSede a eliminar:\n" + sede + "\tPais: " + getPais(sede.getPais())
					+ "\n¿Seguro que desea elimarla?(SI para guardar):   ");
			String respuesta = leer.next();
			if (respuesta.equals("SI")) {
				s.eliminarSede(id);
			} else
				System.out.println("La sede no fue almacenada en la base de datos.");
		}
	}

	/** Modifica un dato especifico de una sede **/
	private static void modificarSede() {
		System.out.println("Elija el numero de la sede que desea modificar: ");
		SedeDAO s = FactoryDAO.getSedeDAO();
		int id = elegirUnaSede(s);// muestra el listado de sedes. Elige una devuelve
		// el id, o devuelve 0 (si no encontro la que queria) o -1 (si ocurrio un error)
		if (id != -1 && id != 0) {
			System.out.println("\nSeleccione el campo que desea modificar");
			System.out.println("0-\tEXIT\n1-\tNombre\n2-\tCapacidad\n3-\tPais");
			System.out.print("Opcion elegida: ");
			int respuesta = leer.nextInt();
			leer.nextLine();
			switch (respuesta) {
			case 0:
				break;
			case 1: // modifica el nombre de una sede
				modificarNombreSede(s, id);
				break;
			case 2: // modifica la capacidad de la sede
				modificarCapacidadSede(s, id);
				break;
			case 3: // modifica el pais de la sede
				modificarPaisSede(s, id);
				break;
			default:
				System.out.println("Opcion no disponible.");
			}
		}
	}

	private static void modificarNombreSede(SedeDAO s, int id) {
		System.out.print("Nuevo nombre: ");
		String nombre = leer.nextLine(); // lee un String
		if (!s.existeSede(nombre, s.getSede(id).getPais())) {
			Sede sede = new Sede(nombre, s.getSede(id).getCapacidad(), s.getSede(id).getPais());
			System.out.print("\nConfirmar los datos ingresados\n" + sede + "\tPais: " + getPais(sede.getPais())
					+ "\n\n¿Desea agregar " + sede.getNombre() + "? (SI para guardar):   ");
			String respuesta = leer.next();
			if (respuesta.equals("SI")) {
				s.modificarNombre(id, nombre);
			} else
				System.out.println("La sede no fue almacenada en la base de datos.");
		} else
			System.out.println(
					"Los cambios que quiere realizar, coinciden con una sede que \nya se encuentran en la base de datos");
	}

	private static void modificarCapacidadSede(SedeDAO s, int id) {
		System.out.print("Capacidad: ");
		int cap = leer.nextInt(); // lee un entero
		Sede sede = new Sede(s.getSede(id).getNombre(), cap, s.getSede(id).getPais());
		System.out.print("\nConfirmar los datos ingresados\n" + sede + "\tPais: " + getPais(sede.getPais())
				+ "\n\n¿Desea agregar " + sede.getNombre() + "? (SI para guardar):   ");
		String respuesta = leer.next();
		if (respuesta.equals("SI")) {
			s.modificarCapacidad(id, cap);
		} else
			System.out.println("La sede no fue almacenada en la base de datos.");
	}

	private static void modificarPaisSede(SedeDAO s, int id) {
		PaisDAO p = FactoryDAO.getPaisDAO();
		int idpais = elegirUnPais(p);
		if (idpais != -1 && idpais != 0)
			if (!s.existeSede(s.getSede(id).getNombre(), idpais)) {
				Sede sede = new Sede(s.getSede(id).getNombre(), s.getSede(id).getCapacidad(), idpais);
				System.out.print("\nConfirmar los datos ingresados\n" + sede + "\tPais: " + getPais(sede.getPais())
						+ "\n\n¿Desea agregar " + sede.getNombre() + "? (SI para guardar):   ");
				String respuesta = leer.next();
				if (respuesta.equals("SI")) {
					s.modificarPais(id, idpais);
				} else
					System.out.println("La sede no fue almacenada en la base de datos.");

			} else
				System.out.println(
						"Los cambios que quiere realizar, coinciden con una sede que \nya se encuentran en la base de datos");
	}

	private static String getPais(int id) {
		PaisDAO p = FactoryDAO.getPaisDAO();
		return p.getNombrePais(id);
	}
}
