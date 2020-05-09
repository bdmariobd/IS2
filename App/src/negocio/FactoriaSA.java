package negocio;

import negocio.Vehiculo.SAVehiculo;
import negocio.Alumno.SAalumno;
import negocio.Sucursal.SASucursal;
import negocio.Test.SATest;
public abstract class FactoriaSA {
    private static FactoriaSA instance;

    public static FactoriaSA getInstance() {

        if(instance==null) {
            instance = new FactoriaSAIMP();
        }

        return instance;
    }

    public abstract SAVehiculo generateSAVehiculo();
    public abstract SASucursal generateSASucursal();
    public abstract SATest generateSATest();
	public abstract SAalumno generateSAalumno();
}