package presentacion.Sesion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import negocio.Sesion.TSesion;
import presentacion.Controller;
import presentacion.GUIMaker;
import presentacion.eventos;

public class GUISesionImp extends GUISesion implements ActionListener {
	private static final long serialVersionUID = 1L;
	private GUIAltaSesion gAltaSes;
	private GUIBajaSesion gBajaSes;
	private GUIMostrarTodasSesionesAlumno gMostrarSesA;
	private GUIMostrarTodasSesionesProfesor gMostrarSesP;
	private GUIModificarSesion gModSes;
	private GUIMostrarSesion gMosSes;
	
	public GUISesionImp() {
		super();
		gAltaSes=new GUIAltaSesion();
		gBajaSes=new GUIBajaSesion();
		gMostrarSesA=new GUIMostrarTodasSesionesAlumno();
		gMostrarSesP=new GUIMostrarTodasSesionesProfesor();
		gModSes=new GUIModificarSesion();
		gMosSes= new GUIMostrarSesion();
		initGui();
	}
	public void initGui() {
		String[] botones = {"Dar de alta una sesion", "Dar de baja una sesion",
				"Mostrar una sesion", "Mostrar sesiones de un profesor","Mostrar sesiones de un alumno", "Modificar una sesion","Volver"};
		String[] extra = {"Principal"};
		add(GUIMaker.getInstance().getPanel(botones,extra, "Autoescuela PM", this));
		GUIMaker.getInstance().configurateWindow(this);
		
	}
	@SuppressWarnings("unchecked")
	@Override
	//Respuestas de las operaciones que se han invocado
	public void update(int event, Object res) {
		// TODO Auto-generated method stub
		String msg="Error desconocido";
		
		//errores de las operaciones. Más detalles en SAVSucursal
		switch(event) {
		case eventos.ALTA_OK_SESION:
			JOptionPane.showMessageDialog(null, "Añadido correctamente(id="+res+").","Información",JOptionPane.INFORMATION_MESSAGE);
			break;
		case eventos.ALTA_KO_SESION:
			if((int)res==-2)msg = "Error, id Sesion repetido.";
			else if((int)res==-3) msg = "Error, compruebe tamaño y formato de los datos.";
			else if((int)res==-1)msg = "Error, no existe.";
			else if((int)res==-4) msg="Error de conexion con la base de datos.";
			else if((int)res==-5) msg="Error desconocido, consulte con administrador.";
			else if((int)res==-7) msg="Error, el profesor no figura en la base de datos.";
			else if((int)res==-8) msg="Error, el alumno no figura en la base de datos.";
			else if((int)res==-9) msg="Error, el profesor no esta disponible en esa fecha";
			else if((int)res==-10) msg="Error, el alumno no esta disponible en esa fecha";
			else if((int)res==-11) msg="Error, hora con formato invalido. Pruebe 'HH:mm'";
			else if((int)res==-12) msg="Error, fecha con formato invalido. Pruebe 'yyyy-MM-dd'";
			JOptionPane.showMessageDialog(null, msg,"Información",JOptionPane.INFORMATION_MESSAGE);
			break;
		case eventos.BAJA_OK_SESION:
			JOptionPane.showMessageDialog(null, "Borrado correctamente (id= "+res+").","Información",JOptionPane.INFORMATION_MESSAGE);
			break;
		case eventos.BAJA_KO_SESION:
			if((int)res==-6) msg = "Error, ya se habia dado de baja.";
			if((int)res==-1)msg = "Error, no existe.";
			if((int)res==-3)msg = "Error, invalidez de los datos.";
			else if((int)res==-4) msg="Error de conexion con la base de datos.";
			else if((int)res==-5) msg="Error desconocido, consulte con administrador.";
			JOptionPane.showMessageDialog(null, msg,"Información",JOptionPane.INFORMATION_MESSAGE);
			break;
		case eventos.MOSTRAR_TODOS_OK_SESION_ALUMNO:
			gMostrarSesA.mostrarSesiones((List<TSesion>) res);
			break;
		case eventos.MOSTRAR_TODOS_KO_SESION_ALUMNO:
			msg= "No se han podido mostrar las sesiones";
			JOptionPane.showMessageDialog(null, msg,"Información",JOptionPane.INFORMATION_MESSAGE);
			break;
		case eventos.MOSTRAR_TODOS_OK_SESION_PROFESOR:
			gMostrarSesP.mostrarSesiones((List<TSesion>) res);
			break;
		case eventos.MOSTRAR_TODOS_KO_SESION_PROFESOR:
			msg= "No se han podido mostrar las sesiones";
			JOptionPane.showMessageDialog(null, msg,"Información",JOptionPane.INFORMATION_MESSAGE);
			break;
		case eventos.MOSTRAR_UNO_OK_SESION:
			gMosSes.mostrarUno((TSesion) res);
			break;
		case eventos.MOSTRAR_UNO_KO_SESION:
			msg= "No se han podido mostrar la sesion";
			JOptionPane.showMessageDialog(null, msg,"Información",JOptionPane.INFORMATION_MESSAGE);
			break;
		case eventos.BUSCAR_SESION_OK:
			gModSes.updatePanel((TSesion) res);
			break;
		case eventos.BUSCAR_SESION_KO:
			msg= "No se ha encontrado la sesión";
			JOptionPane.showMessageDialog(null, msg,"Información",JOptionPane.INFORMATION_MESSAGE);
			break;
		case eventos.MODIFICAR_OK_SESION:
			JOptionPane.showMessageDialog(null, "Modificada correctamente.","Información",JOptionPane.INFORMATION_MESSAGE);
			break;
		case eventos.MODIFICAR_KO_SESION:
			if((int)res==-2)msg = "Error, id Sesion repetido.";
			else if((int)res==-3) msg = "Error, compruebe tamaño y formato de los datos.";
			else if((int)res==-1)msg = "Error, no existe.";
			else if((int)res==-4) msg="Error de conexion con la base de datos.";
			else if((int)res==-5) msg="Error desconocido, consulte con administrador.";
			else if((int)res==-7) msg="Error, el profesor no figura en la base de datos.";
			else if((int)res==-8) msg="Error, el alumno no figura en la base de datos.";
			else if((int)res==-9) msg="Error, el profesor no esta disponible en esa fecha";
			else if((int)res==-10) msg="Error, el alumno no esta disponible en esa fecha";
			else if((int)res==-11) msg="Error, hora con formato invalido. Pruebe 'HH:mm'";
			else if((int)res==-12) msg="Error, fecha con formato invalido. Pruebe 'yyyy-MM-dd'";
			JOptionPane.showMessageDialog(null, msg,"Información",JOptionPane.INFORMATION_MESSAGE);
			break;
			
		}
	}
	@Override
	//Llamada a las operaciones
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="Dar de alta una sesion") {
			gAltaSes.initGui();
		}
		else if(e.getActionCommand()=="Dar de baja una sesion") {
			gBajaSes.initGui();
		}
		else if(e.getActionCommand()=="Mostrar una sesion") {
			gMosSes.initGui();
		}
		else if(e.getActionCommand()=="Mostrar sesiones de un alumno") {
			gMostrarSesA.initGui();
		}
		else if(e.getActionCommand()=="Mostrar sesiones de un profesor") {
			gMostrarSesP.initGui();
		}
		else if(e.getActionCommand()=="Modificar una sesion") {
			gModSes.initGui();
		}
		else if(e.getActionCommand()=="Volver"){
			this.dispose();
			Controller.getInstance().accion(eventos.GUI_PRINC, null);
		}
	}
}

