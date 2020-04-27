package negocio.Alumno;

public class TAlumno {
	private int id;
	private String DNI, nombre, apellidos, telefono, email;
	private boolean amaxofobia, activo;
	
	public TAlumno (int id, String DNI, String nombre, String apellidos, String telefono, String email, boolean amaxofobia, boolean activo) {
		this.id = id;
		this.DNI = DNI;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.email = email;		
		this.amaxofobia = amaxofobia;
		this.activo = activo;
	}
	
	public int getId() {
		return id;
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
	
	public String getTelefono() {
		return telefono;
	}
	
	public String getEmail() {
		return email;
	}
	
	public boolean getAmaxofobia() {
		return amaxofobia;
	}
	
	public boolean getActivo() {
		return activo;
	}
	
}
