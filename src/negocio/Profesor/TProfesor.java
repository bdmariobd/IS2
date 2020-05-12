package negocio.Profesor;

public class TProfesor {
	private int id, idSucursal, sueldo, telefono;
	private String DNI, nombre, apellidos, email;
	private boolean activo;

	public TProfesor(int id, int idSucursal, String DNI, String nombre, String apellidos, int telefono, String email, int sueldo,
			boolean activo) {
		this.id = id;
		this.idSucursal = idSucursal;
		this.DNI = DNI;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.email = email;
		this.sueldo = sueldo;
		this.activo = activo;
	}

	public int getId() {
		return id;
	}
	public int getIdSucursal() {
		return idSucursal;
	}

	public String getDNI() {
		return DNI;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public int getTelefono() {
		return telefono;
	}

	public String getEmail() {
		return email;
	}

	public int getSueldo() {
		return sueldo;
	}

	public boolean getActivo() {
		return activo;
	}

}
