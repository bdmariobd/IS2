package presentacion.Vehiculo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import negocio.Vehiculo.TVehiculo;
import presentacion.Controller;
import presentacion.GUIMaker;
import presentacion.eventos;

public class GUIVehiculoImp extends GUIVehiculo implements ActionListener {
	private static final long serialVersionUID = 1L;
	private GUIAltaVehiculo gAltaVeh;
	private GUIBajaVehiculo gBajaVeh;
	private GUIMostrarVehiculo gMostrarVeh;
	private GUIModificarVehiculo gModVeh;
	private GUIRegistrarVehiculo gRegVeh;
	private GUIMostrarTodos gTodosVeh;
	private GUIAsignarVeh gAsigVeh;
	private boolean init=false;
	
	
	public void initGui() {
		if(init) {
			setVisible(true); 
			return;
		}
		init=true;
		gAltaVeh=new GUIAltaVehiculo();
		gBajaVeh=new GUIBajaVehiculo();
		gMostrarVeh=new GUIMostrarVehiculo();
		gModVeh=new GUIModificarVehiculo();
		gRegVeh=new GUIRegistrarVehiculo();
		gTodosVeh= new GUIMostrarTodos();
		gAsigVeh = new GUIAsignarVeh();
		String[] botones = {"Dar de alta un vehiculo", "Dar de baja un vehiculo", "Registrar daños de un vehiculo", 
				"Mostrar un vehiculo", "Mostrar todos los vehiculos", "Modificar un vehiculo","Asignar un vehículo a un profesor","Volver"};
		String[] extra = {"Principal"};
		add(GUIMaker.getInstance().getPanel(botones,extra, "Autoescuela PM", this));
		GUIMaker.getInstance().configurateWindow(this);
		
	}
	@SuppressWarnings("unchecked")
	@Override
	//Respuestas de las operaciones que se han invocado
	public void update(int event, Object res) {
		// TODO Auto-generated method stub
		String msg="Error no contemplado";
		
		//errores de las operaciones. Más detalles en SAVehiculo
		switch(event) {
		case eventos.ASIGNAR_VEHPROF_OK:
			JOptionPane.showMessageDialog(null,"Añadido correctamente(idcoche="+res+").","Información",JOptionPane.INFORMATION_MESSAGE);
			break;
		case eventos.ASIGNAR_VEHPROF_KO:
			if((int)res==-2)msg = "Error, compruebe los IDs introducidos.";
			else if((int)res==-1)msg = "Error, no existe.";
			else if((int)res == -4) msg= "Ya está vinculado";
				JOptionPane.showMessageDialog(null, msg,"Información",JOptionPane.INFORMATION_MESSAGE);
			break;	
		case eventos.ALTA_OK_VEHICULO:
			JOptionPane.showMessageDialog(null, "Añadido correctamente(id="+res+").","Información",JOptionPane.INFORMATION_MESSAGE);
			break;
		case eventos.ALTA_KO_VEHICULO:
			if((int)res==-2)msg = "Error, matricula repetida.";
			else if((int)res==-3) msg = "Error, compruebe tamaño y formato de los datos.";
			else if((int)res==-1)msg = "Error, no existe.";
			else if((int)res==-4) msg="Error de conexion con la base de datos.";
			else if((int)res==-5) msg="Error desconocido, consulte con administrador.";
				JOptionPane.showMessageDialog(null, msg,"Información",JOptionPane.INFORMATION_MESSAGE);
			break;
		case eventos.BAJA_OK_VEHICULO:
			JOptionPane.showMessageDialog(null, "Borrado correctamente (id= "+res+").","Información",JOptionPane.INFORMATION_MESSAGE);
			break;
		case eventos.BAJA_KO_VEHICULO:
			if((int)res==-6) msg = "Error, ya se habia dado de baja.";
			if((int)res==-1)msg = "Error, no existe.";
			if((int)res==-3)msg = "Error, invalidez de los datos.";
			else if((int)res==-4) msg="Error de conexion con la base de datos.";
			else if((int)res==-5) msg="Error desconocido, consulte con administrador.";
			JOptionPane.showMessageDialog(null, msg,"Información",JOptionPane.INFORMATION_MESSAGE);
			break;
		case eventos.MOSTRAR_TODOS_OK_VEHICULO:
			gTodosVeh.mostrarVehiculos((List<TVehiculo>) res);
			break;
		case eventos.MOSTRAR_TODOS_KO_VEHICULO:
			msg= "No se han podido mostrar los vehiculos";
			JOptionPane.showMessageDialog(null, msg,"Información",JOptionPane.INFORMATION_MESSAGE);
			break;
		case eventos.MOSTRAR_UNO_OK_VEHICULO:
			gMostrarVeh.mostrarUno((TVehiculo) res);
			break;
		case eventos.MOSTRAR_UNO_KO_VEHICULO:
			msg= "No se han podido mostrar el vehiculos";
			JOptionPane.showMessageDialog(null, msg,"Información",JOptionPane.INFORMATION_MESSAGE);
			break;
		case eventos.BUSCAR_VEHICULO_OK:
			gModVeh.updatePanel((TVehiculo) res);
			break;
			
		case eventos.BUSCAR_VEHICULO_KO:
			msg= "No se ha encontrado el vehículo";
			JOptionPane.showMessageDialog(null, msg,"Información",JOptionPane.INFORMATION_MESSAGE);
			break;
			
		case eventos.MODIFICAR_OK_VEHICULO:
			JOptionPane.showMessageDialog(null, "Modificado correctamente.","Información",JOptionPane.INFORMATION_MESSAGE);
			break;
		case eventos.MODIFICAR_KO_VEHICULO:
			if((int)res==-1)msg = "Error, no existe.";
			if((int)res==-2)msg = "Error, matricula repetida.";
			else if((int)res==-3) msg = "Error, compruebe tamaño y formato de los datos.";
			else if((int)res==-4) msg="Error de conexion con la base de datos.";
			else if((int)res==-5) msg="Error desconocido, consulte con administrador.";
			JOptionPane.showMessageDialog(null, msg,"Información",JOptionPane.INFORMATION_MESSAGE);
			break;
		case eventos.REGISTRAR_DANOS_OK:
			JOptionPane.showMessageDialog(null, "Añadido correctamente.","Información",JOptionPane.INFORMATION_MESSAGE);
			break;
		case eventos.REGISTRAR_DANOS_KO:
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
		if(e.getActionCommand()=="Dar de alta un vehiculo") {
			gAltaVeh.initGui();
		}
		else if(e.getActionCommand()=="Dar de baja un vehiculo") {
			gBajaVeh.initGui();
		}
		else if(e.getActionCommand()=="Registrar daños de un vehiculo") {
			gRegVeh.initGui();
		}
		else if(e.getActionCommand()=="Mostrar un vehiculo") {
			gMostrarVeh.initGui();
		}
		else if(e.getActionCommand()=="Mostrar todos los vehiculos") {
			Controller.getInstance().accion(eventos.MOSTRAR_TODOS_VEHICULO, null);
		}
		else if(e.getActionCommand()=="Modificar un vehiculo") {
			gModVeh.initGui();
		}
		else if(e.getActionCommand()=="Asignar un vehículo a un profesor") {
			gAsigVeh.initGui();
		}
		else if(e.getActionCommand()=="Volver"){
			this.dispose();
			Controller.getInstance().accion(eventos.GUI_PRINC, null);
		}
				
	}
}

