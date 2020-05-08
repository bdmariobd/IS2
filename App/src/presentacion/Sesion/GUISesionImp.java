package presentacion.Sesion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import negocio.Sesion.TSesion;
import presentacion.Controller;
import presentacion.GUIMaker;
import presentacion.eventos;

public class GUISesionImp extends GUISesion implements ActionListener {
	private static final long serialVersionUID = 1L;
	private GUIAltaSesion gAltaSes;
	private GUIBajaSesion gBajaSes;
	private GUIMostrarSesion gMostrarSes;
	private GUIModificarSesion gModSes;
	//private GUIRegistrarVehiculo gRegVeh;
	private GUIMostrarTodasSesiones gTodosSes;
	
	public GUISesionImp() {
		super();
		gAltaSes=new GUIAltaSesion();
		gBajaSes=new GUIBajaSesion();
		gMostrarSes=new GUIMostrarSesion();
		gModSes=new GUIModificarSesion();
		gTodosSes= new GUIMostrarTodasSesiones();
		initGui();
	}
	public void initGui() {
		String[] botones = {"Dar de alta una sesion", "Dar de baja una sesion",
				"Mostrar una sesion", "Mostrar todas las sesiones", "Modificar una sesion"};
		String[] extra = {"Principal"};
		add(GUIMaker.getInstance().getPanel(botones,extra, "Autoescuela PM", this));
		GUIMaker.getInstance().configurateWindow(this);
		
	}
	@Override
	//Respuestas de las operaciones que se han invocado
	public void update(int event, Object res) {
		// TODO Auto-generated method stub
		String msg="";
		
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
		case eventos.MOSTRAR_TODOS_OK_SESION:
			gTodosSes.mostrarSesiones((List<TSesion>) res);
			break;
		case eventos.MOSTRAR_TODOS_KO_SESION:
			msg= "No se han podido mostrar las sesiones";
			JOptionPane.showMessageDialog(null, msg,"Información",JOptionPane.INFORMATION_MESSAGE);
			break;
		case eventos.MOSTRAR_UNO_OK_SESION:
			gMostrarSes.mostrarUno((TSesion) res);
			break;
		case eventos.MOSTRAR_UNO_KO_SESION:
			msg= "No se han podido mostrar la sesion";
			JOptionPane.showMessageDialog(null, msg,"Información",JOptionPane.INFORMATION_MESSAGE);
			break;
		case eventos.BUSCAR_SESION_OK:
			gModSes.updatePanel((TSesion) res);
			break;
		case eventos.MODIFICAR_OK_SESION:
			JOptionPane.showMessageDialog(null, "Modificada correctamente.","Información",JOptionPane.INFORMATION_MESSAGE);
			break;
		case eventos.MODIFICAR_KO_SESION:
			if((int)res==-1)msg = "Error, no existe.";
			else if((int)res==-3) msg = "Error, compruebe tamaño y formato de los datos.";
			else if((int)res==-4) msg="Error de conexion con la base de datos.";
			else if((int)res==-5) msg="Error desconocido, consulte con administrador.";
			JOptionPane.showMessageDialog(null, msg,"Información",JOptionPane.INFORMATION_MESSAGE);
			break;
		default:
			
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
			gMostrarSes.initGui();
		}
		else if(e.getActionCommand()=="Mostrar todas las sesiones") {
			Controller.getInstance().accion(eventos.MOSTRAR_TODOS_SESION, null);
		}
		else if(e.getActionCommand()=="Modificar una sesion") {
			gModSes.initGui();
		}
		
	}
}

