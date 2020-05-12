package presentacion;


import java.util.List;

import negocio.FactoriaSA;
import negocio.Alumno.SAalumno;
import negocio.Alumno.TAlumno;
import negocio.Alumno.TRelleno;
import negocio.Vehiculo.SAVehiculo;
import negocio.Vehiculo.TVehiculo;
import presentacion.Alumno.GUIAlumno;
import presentacion.Sucursal.GUISucursal;
import presentacion.Test.GUITest;
import presentacion.Vehiculo.GUIVehiculo;
import negocio.Sucursal.SASucursal;
import negocio.Sucursal.TSucursal;
import negocio.Test.SATest;
import negocio.Test.TTest;

public class ControllerIMP extends Controller {
	private SAVehiculo saVeh;
	private SASucursal saSuc;
	private SAalumno saAlu;
	private SATest saTest;
	public ControllerIMP() {
		FactoriaSA factoria= FactoriaSA.getInstance();
		saVeh= FactoriaSA.getInstance().generateSAVehiculo();
		saSuc = FactoriaSA.getInstance().generateSASucursal();
		saAlu = FactoriaSA.getInstance().generateSAalumno();
		saTest = FactoriaSA.getInstance().generateSATest();
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
			
			case eventos.GUI_SUCURSAL: GUISucursal.getInstance();
			
			break;
			case eventos.GUI_TEST: GUITest.getInstance();
				
			break;
			
			case eventos.GUI_SESION:
			break;
			
			case eventos.GUI_PRINC: GUIPrinc.getInstance().reInit();
			break;
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
			case eventos.MODIFICAR_ALUMNO:
				id= saAlu.update((TAlumno) datos);
				if(id<0) GUIAlumno.getInstance().update(eventos.MODIFICAR_KO_ALUMNO, id);
				else GUIAlumno.getInstance().update(eventos.MODIFICAR_OK_ALUMNO, id);
			break;
			case eventos.BUSCAR_ALUMNO:
				TAlumno a = saAlu.read((int) datos);
				if(a!=null)GUIAlumno.getInstance().update(eventos.BUSCAR_ALUMNO_OK, a);
				//else GUIAlumno.getInstance().update(eventos.BUSCAR_ALUMNO_KO, a);
			break;
			case eventos.MOSTRAR_UNO_ALUMNO:
				TAlumno alu = saAlu.read((int) datos);
				if(alu==null) GUIAlumno.getInstance().update(eventos.MOSTRAR_UNO_KO_ALUMNO, alu);
				else GUIAlumno.getInstance().update(eventos.MOSTRAR_UNO_OK_ALUMNO, alu);
			break;
			case eventos.MOSTRAR_TODOS_ALUMNO:
				List<TAlumno> lista = saAlu.readAll();
				//if(list!=null)
				GUIAlumno.getInstance().update(eventos.MOSTRAR_TODOS_OK_ALUMNO, lista);
				//else //ventana error
			break;
			case eventos.RELLENAR_TEST:
				id = saAlu.rellenar((TRelleno) datos); 
				if(id<0) GUIAlumno.getInstance().update(eventos.RELLENAR_KO_TEST, id);
				else GUIAlumno.getInstance().update(eventos.RELLENAR_OK_TEST, id);
			break;
			case eventos.MOSTRAR_TEST_ALUMNO: /////7
				List<TRelleno> listaR = saAlu.readAllR((int)datos);
				//if(list!=null)
				GUIAlumno.getInstance().update(eventos.MOSTRAR_OK_TEST_ALUMNO, listaR);
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
            //ajdjd
            case eventos.ALTA_TEST: 
                id = saTest.create((TTest) datos); 
                if(id<0) GUITest.getInstance().update(eventos.ALTA_KO_TEST, id);
                else GUITest.getInstance().update(eventos.ALTA_OK_TEST, id);

            break;
            case eventos.BAJA_TEST:
            	id= saTest.delete((String) datos);
                if(id<0) GUITest.getInstance().update(eventos.BAJA_KO_TEST, id);
                else GUITest.getInstance().update(eventos.BAJA_OK_TEST, id);
            break;
            case eventos.MOSTRAR_TODOS_TEST:
            	List<TTest> list3 = saTest.readAll();
				//if(list!=null)
				GUITest.getInstance().update(eventos.MOSTRAR_TODOS_OK_TEST, list3);
				//else //ventana error
            break;
            case eventos.MOSTRAR_UNO_TEST:
                TTest test = saTest.read((int) datos);
                if(test==null) GUITest.getInstance().update(eventos.MOSTRAR_UNO_KO_TEST, test);
                else GUITest.getInstance().update(eventos.MOSTRAR_UNO_OK_TEST, test);
            break;
            case eventos.MODIFICAR_TEST:
            	id= saTest.update((TTest) datos);
                if(id<0) GUITest.getInstance().update(eventos.MODIFICAR_KO_TEST, id);
                else GUITest.getInstance().update(eventos.MODIFICAR_OK_TEST, id);
            break;
            case eventos.BUSCAR_TEST:
                TTest t = saTest.read((int) datos);
                if(t!=null)GUITest.getInstance().update(eventos.BUSCAR_TEST_OK, t);
                //else GUISucursal.getInstance().update(eventos.BUSCAR_SUCURSAL_KO, v);
            break;
		}
	}

}
