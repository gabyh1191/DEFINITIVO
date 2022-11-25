package Principal;

public class Sede {
	private String nombre;
	private int capacidad;
	private int idPais;

	public Sede(String nombre) {
		this.nombre = nombre;
	}

	public Sede(String nombre, int capacidad, int idPais) {
		this.nombre = nombre;
		this.capacidad = capacidad;
		this.idPais = idPais;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public int getPais() {
		return idPais;
	}

	public void setPais(int idpais) {
		this.idPais = idpais;
	}

	public String toString() {
		return "\tNombre: " + this.nombre + "\n\tCapacidad: " + this.capacidad + "\n";
	}
}