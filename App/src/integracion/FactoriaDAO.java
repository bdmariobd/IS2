package integracion;

import integracion.Alumno.DAOAlumno;
import integracion.Profesor.DAOProfesor;
import integracion.Sucursal.DAOSucursal;
import integracion.Vehiculo.DAOVehiculo;
import integracion.Sesion.DAOSesion;

public abstract class FactoriaDAO {
    private static FactoriaDAO instance;

    public static FactoriaDAO getInstance() {

        if(instance==null) {
            instance = new FactoriaDAOIMP();
        }

        return instance;
    }

    public abstract DAOVehiculo generateDAOVehiculo();
    public abstract DAOSucursal generateDAOSucursal();
/*
    public abstract DAOMarca generateDAOMarca();

    public abstract DAOProducto generateDAOProducto();

    public abstract DAOVenta generateDAOVenta();

    public abstract DAOCliente generateDAOCliente();
    */

	public abstract DAOAlumno generateDAOAlumno();
	public abstract DAOProfesor generateDAOProfesor();
	public abstract DAOSesion generateDAOSesion();
}