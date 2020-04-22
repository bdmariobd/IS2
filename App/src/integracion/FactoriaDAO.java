package integracion;

import integracion.Vehiculo.DAOVehiculo;
import integracion.Alumno.DAOAlumno;

public abstract class FactoriaDAO {
	private static FactoriaDAO instance;

	public static FactoriaDAO getInstance() {
		
		if(instance==null) {
			instance = new FactoriaDAOIMP();
		}
		
		return instance;
	}

	public abstract DAOVehiculo generateDAOVehiculo();
	
	public abstract DAOAlumno generateDAOAlumno();
/*
	public abstract DAOMarca generateDAOMarca();

	public abstract DAOProducto generateDAOProducto();

	public abstract DAOVenta generateDAOVenta();

	public abstract DAOCliente generateDAOCliente();
	*/
}
