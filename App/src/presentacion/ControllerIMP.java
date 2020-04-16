package presentacion;


import java.util.List;

import negocio.FactoriaSA;
import negocio.Vehiculo.SAVehiculo;
import negocio.Vehiculo.TVehiculo;
import presentacion.Vehiculo.GUIVehiculo;

public class ControllerIMP extends Controller {
	private SAVehiculo saVeh;
	public ControllerIMP() {
		FactoriaSA factoria= FactoriaSA.getInstance();
		saVeh= FactoriaSA.getInstance().generateSAVehiculo();
	}
	@Override
	public void accion(int evento, Object datos) {
		// TODO Auto-generated method stub
		int id;
		switch(evento) {
			case eventos.GUI_ALUMNO:
				
			break;
			
			case eventos.GUI_PROFESOR:
				
			break;
			
			case eventos.GUI_VEHICULO: GUIVehiculo.getInstance();
			break;
			
			case eventos.GUI_SUCURSAL:
			
			break;
			case eventos.GUI_TEST:
				
			break;
			
			case eventos.GUI_SESION:
			break;
			
			case eventos.GUI_PRINC: GUIPrinc.getInstance().reInit();
			break;
			
			case eventos.ALTA_VEHICULO: 
				id = saVeh.create((TVehiculo) datos); 
				if(id<0) GUIVehiculo.getInstance().update(eventos.ALTA_KO_VEHICULO, id);
				else GUIVehiculo.getInstance().update(eventos.ALTA_OK_VEHICULO, id);
				
			break;
			case eventos.BAJA_VEHICULO:
				id= saVeh.delete((int) datos);
				if(id<0) GUIVehiculo.getInstance().update(eventos.BAJA_KO_VEHICULO, id);
				else GUIVehiculo.getInstance().update(eventos.BAJA_OK_VEHICULO, id);
			break;
			case eventos.MOSTRAR_TODOS_VEHICULO:
				List<TVehiculo> list = saVeh.readAll();
				//if(list!=null)
				GUIVehiculo.getInstance().update(eventos.MOSTRAR_TODOS_OK_VEHICULO, list);
				//else //ventana error
			break;
			case eventos.MOSTRAR_UNO_VEHICULO:
				TVehiculo veh = saVeh.read((int) datos);
				if(veh==null) GUIVehiculo.getInstance().update(eventos.MOSTRAR_UNO_K0_VEHICULO, veh);
				else GUIVehiculo.getInstance().update(eventos.MOSTRAR_UNO_OK_VEHICULO, veh);
				//else ventana error
			break;
			case eventos.REGISTRAR_DANOS:
				id=saVeh.regDmg((String[]) datos);
			break;
			case eventos.MODIFICAR_VEHICULO:
				id= saVeh.update((TVehiculo) datos);
			break;
			case eventos.BUSCAR_VEHICULO:
				TVehiculo v = saVeh.read((int) datos);
				GUIVehiculo.getInstance().update(eventos.BUSCAR_VEHICULO_OK, v);
			break;
		}
	}

}
