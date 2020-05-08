package integracion;

import integracion.Vehiculo.DAOVehiculo;
import integracion.Vehiculo.DAOVehiculoIMP;
import integracion.Alumno.DAOAlumno;
import integracion.Alumno.DAOAlumnoIMP;
import integracion.Profesor.DAOProfesor;
import integracion.Profesor.DAOProfesorIMP;
import integracion.Sucursal.DAOSucursal;
import integracion.Sucursal.DAOSucursalIMP;
import integracion.Sesion.DAOSesion;
import integracion.Sesion.DAOSesionIMP;

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
	public DAOProfesor generateDAOProfesor() {
		// TODO Auto-generated method stub
		return new DAOProfesorIMP();
	}
	@Override
	public DAOSesion generateDAOSesion() {
		// TODO Auto-generated method stub
		return new DAOSesionIMP();
	}
}