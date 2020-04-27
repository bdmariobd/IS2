package negocio;

import negocio.Vehiculo.SAVehiculo;
import negocio.Alumno.SAalumno;

public abstract class FactoriaSA {
	private static FactoriaSA instance;

	public static FactoriaSA getInstance() {
		
		if(instance==null) {
			instance = new FactoriaSAIMP();
		}
		
		return instance;
	}

	public abstract SAVehiculo generateSAVehiculo();
	
	public abstract SAalumno generateSAalumno();
}
