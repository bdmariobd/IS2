package presentacion.Test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import negocio.Test.TTest;
import presentacion.Controller;
import presentacion.GUIMaker;
import presentacion.eventos;

public class GUITestImp extends GUITest implements ActionListener {
	private static final long serialVersionUID = 1L;
	private GUIAltaTest gAltaT;
	private GUIBajaTest gBajaT;
	private GUIMostrarTest gMostrarT;
	private GUIModificarTest gModT;
	private GUIMostrarTodosTests gTodosT;
	
	public GUITestImp() {
		super();
		gAltaT=new GUIAltaTest();
		gBajaT=new GUIBajaTest();
		gMostrarT=new GUIMostrarTest();
		gModT=new GUIModificarTest();
		//gRegVeh=new GUIRegistrarVehiculo();
		gTodosT= new GUIMostrarTodosTests();
		initGui();
	}
	public void initGui() {
		String[] botones = {"Dar de alta un test", "Dar de baja un test",
				"Mostrar un test", "Mostrar todos los tests", "Modificar un test", "Volver"};
		String[] extra = {"Principal"};
		add(GUIMaker.getInstance().getPanel(botones,extra, "Autoescuela PM", this));
		GUIMaker.getInstance().configurateWindow(this);
		
	}
	@SuppressWarnings("unchecked")
	@Override
	//Respuestas de las operaciones que se han invocado
	public void update(int event, Object res) {
		// TODO Auto-generated method stub
		String msg="";
		
		//errores de las operaciones. Más detalles en SAVSucursal
		switch(event) {
		case eventos.ALTA_OK_TEST:
			JOptionPane.showMessageDialog(null, "Añadido correctamente(id="+res+").","Información",JOptionPane.INFORMATION_MESSAGE);
			break;
		case eventos.ALTA_KO_TEST:
			if((int)res==-2)msg = "Error, id Test repetido.";
			else if((int)res==-3) msg = "Error, compruebe tamaño y formato de los datos.";
			else if((int)res==-1)msg = "Error, no existe.";
			else if((int)res==-4) msg="Error de conexion con la base de datos.";
			else if((int)res==-5) msg="Error desconocido, consulte con administrador.";
				JOptionPane.showMessageDialog(null, msg,"Información",JOptionPane.INFORMATION_MESSAGE);
			break;
		case eventos.BAJA_OK_TEST:
			JOptionPane.showMessageDialog(null, "Borrado correctamente (id= "+res+").","Información",JOptionPane.INFORMATION_MESSAGE);
			break;
		case eventos.BAJA_KO_TEST:
			if((int)res==-6) msg = "Error, ya se habia dado de baja.";
			if((int)res==-1)msg = "Error, no existe.";
			if((int)res==-3)msg = "Error, invalidez de los datos.";
			else if((int)res==-4) msg="Error de conexion con la base de datos.";
			else if((int)res==-5) msg="Error desconocido, consulte con administrador.";
			JOptionPane.showMessageDialog(null, msg,"Información",JOptionPane.INFORMATION_MESSAGE);
			break;
		case eventos.MOSTRAR_TODOS_OK_TEST:
			gTodosT.mostrarTest((List<TTest>) res);
			break;
		case eventos.MOSTRAR_TODOS_KO_TEST:
			msg= "No se han podido mostrar los test";
			JOptionPane.showMessageDialog(null, msg,"Información",JOptionPane.INFORMATION_MESSAGE);
			break;
		case eventos.MOSTRAR_UNO_OK_TEST:
			gMostrarT.mostrarUno((TTest) res);
			break;
		case eventos.MOSTRAR_UNO_KO_TEST:
			msg= "No se han podido mostrar la test";
			JOptionPane.showMessageDialog(null, msg,"Información",JOptionPane.INFORMATION_MESSAGE);
			break;
		case eventos.BUSCAR_TEST_OK:
			gModT.updatePanel((TTest) res);
			break;
		case eventos.BUSCAR_TEST_KO:
			msg= "No se ha encontrado el test";
			JOptionPane.showMessageDialog(null, msg,"Información",JOptionPane.INFORMATION_MESSAGE);
			break;
		case eventos.MODIFICAR_OK_TEST:
			JOptionPane.showMessageDialog(null, "Modificada correctamente.","Información",JOptionPane.INFORMATION_MESSAGE);
			break;
		case eventos.MODIFICAR_KO_TEST:
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
		if(e.getActionCommand()=="Dar de alta un test") {
			gAltaT.initGui();
		}
		else if(e.getActionCommand()=="Dar de baja un test") {
			gBajaT.initGui();
		}
		else if(e.getActionCommand()=="Mostrar un test") {
			gMostrarT.initGui();
		}
		else if(e.getActionCommand()=="Mostrar todos los tests") {
			Controller.getInstance().accion(eventos.MOSTRAR_TODOS_TEST, null);
		}
		else if(e.getActionCommand()=="Modificar un test") {
			gModT.initGui();
		}
		else if(e.getActionCommand()=="Volver"){
			this.dispose();
			Controller.getInstance().accion(eventos.GUI_PRINC, null);
		}
		
	}
}

