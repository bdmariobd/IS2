package presentacion.Vehiculo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

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
	
	public GUIVehiculoImp() {
		super();
		gAltaVeh=new GUIAltaVehiculo();
		gBajaVeh=new GUIBajaVehiculo();
		gMostrarVeh=new GUIMostrarVehiculo();
		gModVeh=new GUIModificarVehiculo();
		gRegVeh=new GUIRegistrarVehiculo();
		gTodosVeh= new GUIMostrarTodos();
		initGui();
	}
	public void initGui() {
		String[] botones = {"Dar de alta un vehiculo", "Dar de baja un vehiculo", "Registrar daños de un vehiculo", 
				"Mostrar un vehiculo", "Mostrar todos los vehiculos", "Modificar un vehiculo"};
		String[] extra = {"Principal"};
		add(GUIMaker.getInstance().getPanel(botones,extra, "Autoescuela PM", this));
		GUIMaker.getInstance().configurateWindow(this);
		
	}
	@Override
	public void update(int event, Object res) {
		// TODO Auto-generated method stub
		switch(event) {
		case eventos.MOSTRAR_TODOS_OK_VEHICULO:
			gTodosVeh.mostrarVehiculos((List<TVehiculo>) res);
			break;
		
		case eventos.MOSTRAR_UNO_OK_VEHICULO:
			System.out.println("debugear con prints es de pros");
			gMostrarVeh.mostrarUno((TVehiculo) res);
			break;
		case eventos.BUSCAR_VEHICULO_OK:
			gModVeh.updatePanel((TVehiculo) res);
			break;
		}
	}
	@Override
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
			System.out.println("debugear con prints es de pros");
			gMostrarVeh.initGui();
		}
		else if(e.getActionCommand()=="Mostrar todos los vehiculos") {
			Controller.getInstance().accion(eventos.MOSTRAR_TODOS_VEHICULO, null);
		}
		else if(e.getActionCommand()=="Modificar un vehiculo") {
			gModVeh.initGui();
		}
		
	}
}

