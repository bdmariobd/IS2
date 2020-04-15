package integracion;

import integracion.Vehiculo.DAOVehiculo;
import integracion.Vehiculo.DAOVehiculoIMP;

public class FactoriaDAOIMP extends FactoriaDAO {

	@Override
	public DAOVehiculo generateDAOVehiculo() {
		// TODO Auto-generated method stub
		return new DAOVehiculoIMP();
	}
	

}
