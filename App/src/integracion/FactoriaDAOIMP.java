package integracion;

import integracion.Vehiculo.DAOVehiculo;
import integracion.Vehiculo.DAOVehiculoIMP;
import integracion.Alumno.DAOAlumno;
import integracion.Alumno.DAOAlumnoIMP;
import integracion.Sucursal.DAOSucursal;
import integracion.Sucursal.DAOSucursalIMP;
import integracion.Test.DAOTest;
import integracion.Test.DAOTestIMP;

public class FactoriaDAOIMP extends FactoriaDAO {

    @Override
    public DAOVehiculo generateDAOVehiculo() {
        // TODO Auto-generated method stub
        return new DAOVehiculoIMP();
    }
    public DAOSucursal generateDAOSucursal() {
        // TODO Auto-generated method stub
        return new DAOSucursalIMP();
    }
    @Override
	public DAOAlumno generateDAOAlumno() {
		// TODO Auto-generated method stub
		return new DAOAlumnoIMP();
	}
    @Override
    public DAOTest generateDAOTest() {
    	// TODO Auto-generated method stub
    	return new DAOTestIMP();
    }
}