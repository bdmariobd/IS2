package negocio.Vehiculo;

public class TVehiculo {
	private int id, idSucursal, idP;
	
	private String tipo, matricula, da�os;
	private boolean activo;
	
	public TVehiculo(int id, int idSucursal, String tipo, String matricula, boolean activo, String da�os) {
		this.id = id;
		this.idSucursal = idSucursal;
		this.tipo = tipo;
		this.matricula = matricula;
		this.da�os = da�os;
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
	public String getDa�os() {
		return da�os;
	}
	public boolean isActivo() {
		return activo;
	}
	
}
