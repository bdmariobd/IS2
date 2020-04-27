package integracion;

import integracion.Alumno.DAOAlumno;
import integracion.Alumno.DAOAlumnoIMP;
import integracion.Vehiculo.DAOVehiculo;
import integracion.Vehiculo.DAOVehiculoIMP;

public class FactoriaDAOIMP extends FactoriaDAO {

	@Override
	public DAOVehiculo generateDAOVehiculo() {
		// TODO Auto-generated method stub
		return new DAOVehiculoIMP();
	}

	@Override
	public DAOAlumno generateDAOAlumno() {
		// TODO Auto-generated method stub
		return new DAOAlumnoIMP();
	}
	
	
	

}
