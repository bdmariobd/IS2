package negocio;

import negocio.Alumno.SAalumno;
import negocio.Profesor.SAProfesor;
import negocio.Sucursal.SASucursal;
import negocio.Vehiculo.SAVehiculo;
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
	public abstract SAalumno generateSAalumno();
	public abstract SAProfesor generateSAProfesor();
}