package presentacion;


import java.util.List;

import negocio.FactoriaSA;
import negocio.Vehiculo.SAVehiculo;
import negocio.Vehiculo.TVehiculo;
import presentacion.Alumno.GUIAlumno;
import presentacion.Vehiculo.GUIVehiculo;
import negocio.Alumno.SAalumno;
import negocio.Alumno.TAlumno;

public class ControllerIMP extends Controller {
	private SAVehiculo saVeh;
	private SAalumno saAlu;
	public ControllerIMP() {
		FactoriaSA factoria= FactoriaSA.getInstance();
		saVeh= FactoriaSA.getInstance().generateSAVehiculo();
		saAlu = FactoriaSA.getInstance().generateSAalumno();
	}
	@Override
	public void accion(int evento, Object datos) {
		// TODO Auto-generated method stub
		int id;
		switch(evento) {
			case eventos.GUI_ALUMNO: GUIAlumno.getInstance();
				
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
				id= saVeh.delete((String) datos);
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
				if(veh==null) GUIVehiculo.getInstance().update(eventos.MOSTRAR_UNO_KO_VEHICULO, veh);
				else GUIVehiculo.getInstance().update(eventos.MOSTRAR_UNO_OK_VEHICULO, veh);
			break;
			case eventos.REGISTRAR_DANOS:
				id=saVeh.regDmg((String[]) datos);
				if(id<0) GUIVehiculo.getInstance().update(eventos.REGISTRAR_DANOS_KO, id);
				else GUIVehiculo.getInstance().update(eventos.REGISTRAR_DANOS_OK, id);
			break;
			case eventos.MODIFICAR_VEHICULO:
				id= saVeh.update((TVehiculo) datos);
				if(id<0) GUIVehiculo.getInstance().update(eventos.MODIFICAR_KO_VEHICULO, id);
				else GUIVehiculo.getInstance().update(eventos.MODIFICAR_OK_VEHICULO, id);
			break;
			case eventos.BUSCAR_VEHICULO:
				TVehiculo v = saVeh.read((int) datos);
				if(v!=null)GUIVehiculo.getInstance().update(eventos.BUSCAR_VEHICULO_OK, v);
				//else GUIVehiculo.getInstance().update(eventos.BUSCAR_VEHICULO_KO, v);
			
			case eventos.ALTA_ALUMNO:
				id = saAlu.create((TAlumno) datos); 
				if(id<0) GUIAlumno.getInstance().update(eventos.ALTA_KO_ALUMNO, id);
				else GUIAlumno.getInstance().update(eventos.ALTA_OK_ALUMNO, id);
			break;
			
			case eventos.BAJA_ALUMNO:
				id= saAlu.delete((String) datos);
				if(id<0) GUIAlumno.getInstance().update(eventos.BAJA_KO_ALUMNO, id);
				else GUIAlumno.getInstance().update(eventos.BAJA_OK_ALUMNO, id);
			break;
		}
	}

}
