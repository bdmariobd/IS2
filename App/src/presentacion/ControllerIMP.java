package presentacion;


import java.util.List;

import negocio.FactoriaSA;
import negocio.Vehiculo.SAVehiculo;
import negocio.Vehiculo.TVehiculo;
import presentacion.Sucursal.GUISucursal;
import presentacion.Vehiculo.GUIVehiculo;
import negocio.Sucursal.SASucursal;
import negocio.Sucursal.TSucursal;

public class ControllerIMP extends Controller {
	private SAVehiculo saVeh;
	private SASucursal saSuc;
	public ControllerIMP() {
		FactoriaSA factoria= FactoriaSA.getInstance();
		saVeh= FactoriaSA.getInstance().generateSAVehiculo();
		saSuc = FactoriaSA.getInstance().generateSASucursal();
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
			
			case eventos.GUI_SUCURSAL: GUISucursal.getInstance();
			
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
			break;
			case eventos.ALTA_SUCURSAL: 
                id = saSuc.create((TSucursal) datos); 
                if(id<0) GUISucursal.getInstance().update(eventos.ALTA_KO_SUCURSAL, id);
                else GUISucursal.getInstance().update(eventos.ALTA_OK_SUCURSAL, id);

            break;
            case eventos.BAJA_SUCURSAL:
                id= saSuc.delete((String) datos);
                if(id<0) GUISucursal.getInstance().update(eventos.BAJA_KO_SUCURSAL, id);
                else GUIVehiculo.getInstance().update(eventos.BAJA_OK_SUCURSAL, id);
            break;
            case eventos.MOSTRAR_TODOS_SUCURSAL:
                List<TSucursal>list2 = saSuc.readAll();
                //if(list!=null)
                GUISucursal.getInstance().update(eventos.MOSTRAR_TODOS_OK_SUCURSAL, list2);
                //else //ventana error
            break;
            case eventos.MOSTRAR_UNO_SUCURSAL:
                TSucursal suc = saSuc.read((int) datos);
                if(suc==null) GUISucursal.getInstance().update(eventos.MOSTRAR_UNO_KO_SUCURSAL, suc);
                else GUISucursal.getInstance().update(eventos.MOSTRAR_UNO_OK_SUCURSAL, suc);
            break;
            case eventos.MODIFICAR_SUCURSAL:
                id= saSuc.update((TSucursal) datos);
                if(id<0) GUISucursal.getInstance().update(eventos.MODIFICAR_KO_SUCURSAL, id);
                else GUISucursal.getInstance().update(eventos.MODIFICAR_OK_SUCURSAL, id);
            break;
            case eventos.BUSCAR_SUCURSAL:
                TSucursal s = saSuc.read((int) datos);
                if(s!=null)GUISucursal.getInstance().update(eventos.BUSCAR_SUCURSAL_OK, s);
                //else GUISucursal.getInstance().update(eventos.BUSCAR_SUCURSAL_KO, v);
            break;
		}
	}

}
