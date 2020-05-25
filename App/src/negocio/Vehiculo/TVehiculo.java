package negocio.Vehiculo;

public class TVehiculo {
	private int id, idSucursal, idP;
	
	private String tipo, matricula, daños;
	private boolean activo;
	
	public TVehiculo(int id, int idSucursal, String tipo, String matricula, boolean activo, String daños) {
		this.id = id;
		this.idSucursal = idSucursal;
		this.tipo = tipo;
		this.matricula = matricula;
		this.daños = daños;
		this.activo = activo;
		idP=-1;
	}
	public void setIdP(int idP) {
		this.idP = idP;
	}
	public int getId() {
		return id;
	}
	public int getIdP() {
		return idP;
	}
	public int getIdSucursal() {
		return idSucursal;
	}
	public String getTipo() {
		return tipo;
	}
	public String getMatricula() {
		return matricula;
	}
	public String getDaños() {
		return daños;
	}
	public boolean isActivo() {
		return activo;
	}
	
}
