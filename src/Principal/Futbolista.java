package Principal;

public class Futbolista {
	private String nombre;
	private String apellido;
	private int docID;
	private long telefono;
	private String email;
	private int idPais;

	public Futbolista() {
	}

	public Futbolista(String nombre, String apellido, int docId, long telefono, String email, int idpais) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.docID = docId;
		this.telefono = telefono;
		this.email = email;
		this.idPais = idpais;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getDocID() {
		return this.docID;
	}

	public void setDocID(int docId) {
		this.docID = docId;
	}

	public long getTelefono() {
		return telefono;
	}

	public void setTelefono(long telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getIdPais() {
		return idPais;
	}

	public void setIdPais(int idPais) {
		this.idPais = idPais;
	}

	public String toString() {
		return "\tNombre: " + this.nombre + "\n\tApellido: " + this.getApellido() + "\n\tDocumento: " + this.docID
				+ "\n\tTelefono: " + this.telefono + "\n\tEmail: " + this.email + "\n";
	}

}
