package presentacion.Sucursal;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import negocio.Sucursal.TSucursal;
import presentacion.Controller;
import presentacion.GUIMaker;
import presentacion.eventos;

public class GUISucursalImp extends GUISucursal implements ActionListener {
	private static final long serialVersionUID = 1L;
	private GUIAltaSucursal gAltaSuc;
	private GUIBajaSucursal gBajaSuc;
	private GUIMostrarSucursal gMostrarSuc;
	private GUIModificarSucursal gModSuc;
	//private GUIRegistrarVehiculo gRegVeh;
	private GUIMostrarTodasSucursales gTodosSuc;
	
	public GUISucursalImp() {
		super();
		gAltaSuc=new GUIAltaSucursal();
		gBajaSuc=new GUIBajaSucursal();
		gMostrarSuc=new GUIMostrarSucursal();
		gModSuc=new GUIModificarSucursal();
		//gRegVeh=new GUIRegistrarVehiculo();
		gTodosSuc= new GUIMostrarTodasSucursales();
		initGui();
	}
	public void initGui() {
		String[] botones = {"Dar de alta una sucursal", "Dar de baja una sucursal",
				"Mostrar una sucursal", "Mostrar todas las sucursales", "Modificar una sucursal", "Volver"};
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
		case eventos.ALTA_OK_SUCURSAL:
			JOptionPane.showMessageDialog(null, "Añadido correctamente(id="+res+").","Información",JOptionPane.INFORMATION_MESSAGE);
			break;
		case eventos.ALTA_KO_SUCURSAL:
			if((int)res==-2)msg = "Error, id Sucursal repetido.";
			else if((int)res==-3) msg = "Error, compruebe tamaño y formato de los datos.";
			else if((int)res==-1)msg = "Error, no existe.";
			else if((int)res==-4) msg="Error de conexion con la base de datos.";
			else if((int)res==-5) msg="Error desconocido, consulte con administrador.";
				JOptionPane.showMessageDialog(null, msg,"Información",JOptionPane.INFORMATION_MESSAGE);
			break;
		case eventos.BAJA_OK_SUCURSAL:
			JOptionPane.showMessageDialog(null, "Borrado correctamente (id= "+res+").","Información",JOptionPane.INFORMATION_MESSAGE);
			break;
		case eventos.BAJA_KO_SUCURSAL:
			if((int)res==-6) msg = "Error, ya se habia dado de baja.";
			if((int)res==-1)msg = "Error, no existe.";
			if((int)res==-3)msg = "Error, invalidez de los datos.";
			else if((int)res==-4) msg="Error de conexion con la base de datos.";
			else if((int)res==-5) msg="Error desconocido, consulte con administrador.";
			JOptionPane.showMessageDialog(null, msg,"Información",JOptionPane.INFORMATION_MESSAGE);
			break;
		case eventos.MOSTRAR_TODOS_OK_SUCURSAL:
			gTodosSuc.mostrarSucursales((List<TSucursal>) res);
			break;
		case eventos.MOSTRAR_TODOS_KO_SUCURSAL:
			msg= "No se han podido mostrar las sucursales";
			JOptionPane.showMessageDialog(null, msg,"Información",JOptionPane.INFORMATION_MESSAGE);
			break;
		case eventos.MOSTRAR_UNO_OK_SUCURSAL:
			gMostrarSuc.mostrarUno((TSucursal) res);
			break;
		case eventos.MOSTRAR_UNO_KO_SUCURSAL:
			msg= "No se han podido mostrar la sucursal";
			JOptionPane.showMessageDialog(null, msg,"Información",JOptionPane.INFORMATION_MESSAGE);
			break;
		case eventos.BUSCAR_SUCURSAL_OK:
			gModSuc.updatePanel((TSucursal) res);
			break;
		case eventos.MODIFICAR_OK_SUCURSAL:
			JOptionPane.showMessageDialog(null, "Modificada correctamente.","Información",JOptionPane.INFORMATION_MESSAGE);
			break;
		case eventos.MODIFICAR_KO_SUCURSAL:
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
		if(e.getActionCommand()=="Dar de alta una sucursal") {
			gAltaSuc.initGui();
		}
		else if(e.getActionCommand()=="Dar de baja una sucursal") {
			gBajaSuc.initGui();
		}
		else if(e.getActionCommand()=="Mostrar una sucursal") {
			gMostrarSuc.initGui();
		}
		else if(e.getActionCommand()=="Mostrar todas las sucursales") {
			Controller.getInstance().accion(eventos.MOSTRAR_TODOS_SUCURSAL, null);
		}
		else if(e.getActionCommand()=="Modificar una sucursal") {
			gModSuc.initGui();
		}
		else if(e.getActionCommand()=="Volver"){
			this.dispose();
			Controller.getInstance().accion(eventos.GUI_PRINC, null);
		}
	}
}

