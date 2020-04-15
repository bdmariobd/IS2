package negocio;

import negocio.Vehiculo.SAVehiculo;
import negocio.Vehiculo.SAVehiculoImp;

public class FactoriaSAIMP extends FactoriaSA {


	@Override
	public SAVehiculo generateSAVehiculo() {
		// TODO Auto-generated method stub
		return new SAVehiculoImp();
	}

}
