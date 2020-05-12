package presentacion.Alumno;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import negocio.Alumno.TAlumno;
import presentacion.Controller;
import presentacion.GUIMaker;
import presentacion.eventos;

public class GUIAlumnoImp extends GUIAlumno implements ActionListener{
	private static final long serialVersionUID = 1L;
	private GUIAltaAlumno gAltaAlu;
	private GUIBajaAlumno gBajaAlu;
	private GUIModificarAlumno gModAlu;
	private GUIMostrarAlumno gMostrarAlu;
	private GUIMostrarTodos gTodosAlu;
	
	public GUIAlumnoImp() {
		super();
		gAltaAlu = new GUIAltaAlumno();
		gBajaAlu = new GUIBajaAlumno();
		gModAlu = new GUIModificarAlumno();
		gMostrarAlu = new GUIMostrarAlumno();
		gTodosAlu = new GUIMostrarTodos();
		initGui();
	}
	
	public void initGui() {
		String[] botones = {"Dar de alta un alumno", "Dar de baja un alumno", "Modificar un alumno", 
				"Mostrar un alumno", "Mostrar todos los alumnos"};
		String[] extra = {"Principal"};
		add(GUIMaker.getInstance().getPanel(botones,extra, "Autoescuela PM", this));
		GUIMaker.getInstance().configurateWindow(this);
	}
	

	@Override
	public void update(int event, Object res) {
		// TODO Auto-generated method stub
		String msg = "";
		
		switch(event) {
		case eventos.ALTA_OK_ALUMNO:
			JOptionPane.showMessageDialog(null, "Añadido correctamente(id="+res+").","Información",JOptionPane.INFORMATION_MESSAGE);
			break;
			
		case eventos.ALTA_KO_ALUMNO:
			if((int)res==-2)msg = "Error, DNI repetido.";
			else if((int)res==-3) msg = "Error, compruebe tamaño y formato de los datos.";
			else if((int)res==-1)msg = "Error, no existe.";
			else if((int)res==-4) msg="Error de conexion con la base de datos.";
			else if((int)res==-5) msg="Error desconocido, consulte con administrador.";
				JOptionPane.showMessageDialog(null, msg,"Información",JOptionPane.INFORMATION_MESSAGE);
			break;
			
		case eventos.BAJA_OK_ALUMNO:
			JOptionPane.showMessageDialog(null, "Borrado correctamente (id= "+res+").","Información",JOptionPane.INFORMATION_MESSAGE);
			break;
			
		case eventos.BAJA_KO_ALUMNO:
			if((int)res==-6) msg = "Error, ya se habia dado de baja.";
			if((int)res==-1)msg = "Error, no existe.";
			if((int)res==-3)msg = "Error, invalidez de los datos.";
			else if((int)res==-4) msg="Error de conexion con la base de datos.";
			else if((int)res==-5) msg="Error desconocido, consulte con administrador.";
			JOptionPane.showMessageDialog(null, msg,"Información",JOptionPane.INFORMATION_MESSAGE);
			break;
			
		case eventos.MODIFICAR_OK_ALUMNO:
			JOptionPane.showMessageDialog(null, "Modificado correctamente.","Información",JOptionPane.INFORMATION_MESSAGE);
			break;
			
		case eventos.MODIFICAR_KO_ALUMNO:
			if((int)res==-1)msg = "Error, no existe.";
			else if((int)res==-3) msg = "Error, compruebe tamaño y formato de los datos.";
			else if((int)res==-4) msg="Error de conexion con la base de datos.";
			else if((int)res==-5) msg="Error desconocido, consulte con administrador.";
			JOptionPane.showMessageDialog(null, msg,"Información",JOptionPane.INFORMATION_MESSAGE);
			break;
			
		case eventos.MOSTRAR_TODOS_OK_ALUMNO:
			gTodosAlu.mostrarAlumnos((List<TAlumno>) res);
			break;
			
		case eventos.MOSTRAR_TODOS_KO_ALUMNO:
			msg= "No se han podido mostrar los alumnos";
			JOptionPane.showMessageDialog(null, msg,"Información",JOptionPane.INFORMATION_MESSAGE);
			break;
			
		case eventos.MOSTRAR_UNO_OK_ALUMNO:
			gMostrarAlu.mostrarUno((TAlumno) res);
			break;
			
		case eventos.MOSTRAR_UNO_KO_ALUMNO:
			msg= "No se han podido mostrar el alumno";
			JOptionPane.showMessageDialog(null, msg,"Información",JOptionPane.INFORMATION_MESSAGE);
			break;
			
		case eventos.BUSCAR_ALUMNO_OK:
			gModAlu.updatePanel((TAlumno) res);
			break;
			
		default:
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand()=="Dar de alta un alumno") {
			gAltaAlu.initGui();
		}
		
		else if(e.getActionCommand()=="Dar de baja un alumno") {
			gBajaAlu.initGui();
		}
		
		else if(e.getActionCommand()=="Mostrar un alumno") {
			gMostrarAlu.initGui();
		}
		
		else if(e.getActionCommand()=="Mostrar todos los alumnos") {
			Controller.getInstance().accion(eventos.MOSTRAR_TODOS_ALUMNO, null);
		}
		
		else if(e.getActionCommand()=="Modificar un alumno") {
			gModAlu.initGui();
		}
	}
}
