package presentacion;


import java.util.List;

import negocio.FactoriaSA;
import negocio.Alumno.SAalumno;
import negocio.Alumno.TAlumno;
import negocio.Profesor.SAProfesor;
import negocio.Profesor.TProfesor;
import negocio.Sucursal.SASucursal;
import negocio.Sucursal.TSucursal;
import negocio.Vehiculo.SAVehiculo;
import negocio.Vehiculo.TVehProf;
import negocio.Vehiculo.TVehiculo;
import presentacion.Alumno.GUIAlumno;
import presentacion.Profesor.GUIProfesor;
import presentacion.Sucursal.GUISucursal;
import presentacion.Vehiculo.GUIVehiculo;
import negocio.Test.SATest;
import negocio.Test.TTest;
import presentacion.Test.GUITest;
import negocio.Alumno.TRelleno;

public class ControllerIMP extends Controller {
	private SAVehiculo saVeh;
	private SASucursal saSuc;
	private SAalumno saAlu;
	private SAProfesor saProfe;
	private SATest saTest;
	public ControllerIMP() {
		//FactoriaSA factoria= FactoriaSA.getInstance();
		saVeh= FactoriaSA.getInstance().generateSAVehiculo();
		saSuc = FactoriaSA.getInstance().generateSASucursal();
		saAlu = FactoriaSA.getInstance().generateSAalumno();
		saProfe = FactoriaSA.getInstance().generateSAProfesor();
		saTest = FactoriaSA.getInstance().generateSATest();
	}
	@Override
	public void accion(int evento, Object datos) {
		// TODO Auto-generated method stub
		int id;
		switch(evento) {
			case eventos.GUI_ALUMNO: GUIAlumno.getInstance().initGui();
				
			break;
			
			case eventos.GUI_PROFESOR: GUIProfesor.getInstance().initGui();;
				
			break;
			
			case eventos.GUI_VEHICULO: GUIVehiculo.getInstance().initGui();;
			break;
			
			case eventos.GUI_SUCURSAL: GUISucursal.getInstance().initGui();
			
			break;
			case eventos.GUI_TEST: GUITest.getInstance().initGui();
				
			break;
			
			case eventos.GUI_SESION:
			break;
			
			case eventos.GUI_PRINC: GUIPrinc.getInstance().reInit();
			break;
			
			case eventos.ALTA_PROFESOR:
				id = saProfe.create((TProfesor) datos); 
				if(id<0) GUIProfesor.getInstance().update(eventos.ALTA_KO_PROFESOR, id);
				else GUIProfesor.getInstance().update(eventos.ALTA_OK_PROFESOR, id);
			break;
			
			case eventos.BAJA_PROFESOR:
				id= saProfe.delete((String) datos);
				if(id<0) GUIProfesor.getInstance().update(eventos.BAJA_KO_PROFESOR, id);
				else GUIProfesor.getInstance().update(eventos.BAJA_OK_PROFESOR, id);
			break;
			case eventos.MODIFICAR_PROFESOR:
				id= saProfe.update((TProfesor) datos);
				if(id<0) GUIProfesor.getInstance().update(eventos.MODIFICAR_KO_PROFESOR, id);
				else GUIProfesor.getInstance().update(eventos.MODIFICAR_OK_PROFESOR, id);
			break;
			case eventos.BUSCAR_PROFESOR:
				TProfesor p = saProfe.read((int) datos);
				if(p!=null)GUIProfesor.getInstance().update(eventos.BUSCAR_PROFESOR_OK, p);
				//else GUIProfesor.getInstance().update(eventos.BUSCAR_PROFESOR_KO, p);
			break;
			case eventos.MOSTRAR_UNO_PROFESOR:
				TProfesor profe = saProfe.read((int) datos);
				if(profe==null) GUIProfesor.getInstance().update(eventos.MOSTRAR_UNO_KO_PROFESOR, profe);
				else GUIProfesor.getInstance().update(eventos.MOSTRAR_UNO_OK_PROFESOR, profe);
			break;
			case eventos.MOSTRAR_TODOS_PROFESOR:
				List<TProfesor> listaProfes = saProfe.readAll();
				//if(list!=null)
				GUIProfesor.getInstance().update(eventos.MOSTRAR_TODOS_OK_PROFESOR, listaProfes);
				//else //ventana error
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
                else GUISucursal.getInstance().update(eventos.BAJA_OK_SUCURSAL, id);
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
                //else GUITest.getInstance().update(eventos.BUSCAR_TEST_KO, v);
            break;
            case eventos.RELLENAR_TEST:
				id = saAlu.rellenar((TRelleno) datos); 
				if(id<0) GUIAlumno.getInstance().update(eventos.RELLENAR_KO_TEST, id);
				else GUIAlumno.getInstance().update(eventos.RELLENAR_OK_TEST, id);
			break;
            case eventos.ASIGNAR_VEHPROF:
            	id= saVeh.asigVehProf((TVehProf) datos);
            	if(id<0) GUIVehiculo.getInstance().update(eventos.ASIGNAR_VEHPROF_KO, id);
            	else GUIVehiculo.getInstance().update(eventos.ASIGNAR_VEHPROF_OK, id);
            case eventos.MOSTRAR_TEST_ALUMNO: /////7
				List<TRelleno> listaR = saAlu.readAllR((int)datos);
				//if(list!=null)
				GUIAlumno.getInstance().update(eventos.MOSTRAR_OK_TEST_ALUMNO, listaR);
			break;
		}
	}

}
