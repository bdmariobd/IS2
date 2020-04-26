package negocio;

import negocio.Vehiculo.SAVehiculo;
import negocio.Vehiculo.SAVehiculoImp;
import negocio.Sucursal.SASucursal;
import negocio.Sucursal.SASucursalIMP;

public class FactoriaSAIMP extends FactoriaSA {


    @Override
    public SAVehiculo generateSAVehiculo() {
        // TODO Auto-generated method stub
        return new SAVehiculoImp();
    }

    @Override
    public SASucursal generateSASucursal() {
        // TODO Auto-generated method stub
        return new SASucursalIMP();
    }

}