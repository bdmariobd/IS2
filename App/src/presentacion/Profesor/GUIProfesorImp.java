package presentacion.Profesor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import negocio.Profesor.TProfesor;
import presentacion.Controller;
import presentacion.GUIMaker;
import presentacion.eventos;

public class GUIProfesorImp extends GUIProfesor implements ActionListener{
	private static final long serialVersionUID = 1L;
	private GUIAltaProfesor gAltaProfesor;
	private GUIBajaProfesor gBajaProfesor;
	private GUIModificarProfesor gModProfesor;
	private GUIMostrarProfesor gMostrarProfesor;
	private GUIMostrarTodosProfesores gTodosProfesores;
	
	public GUIProfesorImp() {
		super();
		gAltaProfesor = new GUIAltaProfesor();
		gBajaProfesor = new GUIBajaProfesor();
		gModProfesor = new GUIModificarProfesor();
		gMostrarProfesor = new GUIMostrarProfesor();
		gTodosProfesores = new GUIMostrarTodosProfesores();
		initGui();
	}
	
	public void initGui() {
		String[] botones = {"Dar de alta un Profesor", "Dar de baja un Profesor", "Modificar un Profesor", 
				"Mostrar un Profesor", "Mostrar todos los Profesores", "Volver"};
		String[] extra = {"Principal"};
		add(GUIMaker.getInstance().getPanel(botones,extra, "Autoescuela PM", this));
		GUIMaker.getInstance().configurateWindow(this);
	}
	

	@Override
	public void update(int event, Object res) {
		String msg = "Error no contemplado";
		
		switch(event) {
		case eventos.ALTA_OK_PROFESOR:
			JOptionPane.showMessageDialog(null, "Añadido correctamente(id="+res+").","Información",JOptionPane.INFORMATION_MESSAGE);
			gAltaProfesor.dispose();
			break;
			
		case eventos.ALTA_KO_PROFESOR:
			if((int)res==-2)msg = "Error, DNI repetido.";
			else if((int)res==-3) msg = "Error, compruebe tamaño y formato de los datos.";
			else if((int)res==-1)msg = "Error, no existe.";
			else if((int)res==-6)msg = "Error, no existe sucursal con ese id";
			else if((int)res==-4) msg="Error de conexion con la base de datos.";
			else if((int)res==-5) msg="Error desconocido, consulte con administrador.";
				JOptionPane.showMessageDialog(null, msg,"Información",JOptionPane.INFORMATION_MESSAGE);
			break;
			
		case eventos.BAJA_OK_PROFESOR:
			JOptionPane.showMessageDialog(null, "Borrado correctamente (id= "+res+").","Información",JOptionPane.INFORMATION_MESSAGE);
			gBajaProfesor.dispose();
			break;
			
		case eventos.BAJA_KO_PROFESOR:
			if((int)res==-6) msg = "Error, ya se habia dado de baja.";
			if((int)res==-1)msg = "Error, no existe.";
			if((int)res==-3)msg = "Error, invalidez de los datos.";
			else if((int)res==-4) msg="Error de conexion con la base de datos.";
			else if((int)res==-5) msg="Error desconocido, consulte con administrador.";
			JOptionPane.showMessageDialog(null, msg,"Información",JOptionPane.INFORMATION_MESSAGE);
			break;
			
		case eventos.MODIFICAR_OK_PROFESOR:
			JOptionPane.showMessageDialog(null, "Modificado correctamente.","Información",JOptionPane.INFORMATION_MESSAGE);
			gModProfesor.dispose();
			break;
			
		case eventos.MODIFICAR_KO_PROFESOR:
			if((int)res==-1)msg = "Error, no existe.";
			else if((int)res==-3) msg = "Error, compruebe tamaño y formato de los datos.";
			else if((int)res==-4) msg="Error de conexion con la base de datos.";
			else if((int)res==-5) msg="Error desconocido, consulte con administrador.";
			else if((int)res==-6) msg="Error, no existe sucursal con ese id";
			JOptionPane.showMessageDialog(null, msg,"Información",JOptionPane.INFORMATION_MESSAGE);
			break;
			
		case eventos.MOSTRAR_TODOS_OK_PROFESOR:
			gTodosProfesores.mostrarProfesores((List<TProfesor>) res);
			break;
			
		case eventos.MOSTRAR_TODOS_KO_PROFESOR:
			msg= "No se han podido mostrar los Profesores";
			JOptionPane.showMessageDialog(null, msg,"Información",JOptionPane.INFORMATION_MESSAGE);
			break;
			
		case eventos.MOSTRAR_UNO_OK_PROFESOR:
			gMostrarProfesor.mostrarUno((TProfesor) res);
			break;
			
		case eventos.MOSTRAR_UNO_KO_PROFESOR:
			msg= "No se han podido mostrar el Profesor";
			JOptionPane.showMessageDialog(null, msg,"Información",JOptionPane.INFORMATION_MESSAGE);
			break;
			
		case eventos.BUSCAR_PROFESOR_OK:
			gModProfesor.updatePanel((TProfesor) res);
			break;
			
		default:
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand()=="Dar de alta un Profesor") {
			gAltaProfesor.initGui();
		}
		
		else if(e.getActionCommand()=="Dar de baja un Profesor") {
			gBajaProfesor.initGui();
		}
		
		else if(e.getActionCommand()=="Mostrar un Profesor") {
			gMostrarProfesor.initGui();
		}
		
		else if(e.getActionCommand()=="Mostrar todos los Profesores") {
			Controller.getInstance().accion(eventos.MOSTRAR_TODOS_PROFESOR, null);
		}
		
		else if(e.getActionCommand()=="Modificar un Profesor") {
			gModProfesor.initGui();
		}
		else if(e.getActionCommand()=="Volver"){
			this.dispose();
			Controller.getInstance().accion(eventos.GUI_PRINC, null);
		}
	}
}
