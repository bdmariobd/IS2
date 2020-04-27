package presentacion;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class GuiPrincIMP extends GUIPrinc implements ActionListener {
	
	private final static String version = "Version 0.1";
	private final static String credits = "Mario Blanco Dominguez\n Javier I. Sotelino Barriga, Alberto Chaves, Francisco Boccassi"; // poneros xd
	public GuiPrincIMP(){
		super();
		initGui();
	}
	private void initGui() {
		String[] botones = {"Alumno", "Profesor", "Sucursal", "Sesion", "Test", "Vehiculo"};
		add(GUIMaker.getInstance().getPanel(botones,null, "Autoescuela PM", this));
		GUIMaker.getInstance().configurateWindow(this);
		 StringBuilder buff = new StringBuilder();
	        buff.append("<html><table>");
	        buff.append(String.format("<tr>Mario Blanco Domínguez</tr>"));
	        buff.append(String.format("<tr>Javier I. Sotelino Barriga</tr>"));
	        buff.append(String.format("<tr>Javier I. Sotelino Barriga</tr>"));
	        buff.append(String.format("<tr>Javier I. Sotelino Barriga</tr>"));
	        buff.append(String.format("<tr>Javier I. Sotelino Barriga</tr>"));
	        buff.append(String.format("<tr>"+version+"</tr>"));
	        buff.append("</table></html>");
		
		JLabel info= new JLabel(buff.toString());
		Border _defaultBorder = BorderFactory.createLineBorder(Color.black, 1);
		info.setBorder(BorderFactory.createTitledBorder(_defaultBorder, "Informacion", TitledBorder.LEFT,TitledBorder.TOP));
		JPanel pinfo = new JPanel();
		pinfo.add(info);
		add(pinfo,BorderLayout.LINE_START);
	}
	public void reInit() {
		this.setVisible(true);
		GUIMaker.getInstance().reInit();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.setVisible(false);
		if(e.getActionCommand()=="Alumno") {
			Controller.getInstance().accion(eventos.GUI_ALUMNO, null);
		}
		else if(e.getActionCommand()=="Profesor") {
			Controller.getInstance().accion(eventos.GUI_PROFESOR, null);
		}
		else if(e.getActionCommand()=="Sucursal") {
			Controller.getInstance().accion(eventos.GUI_SUCURSAL, null);
		}
		else if(e.getActionCommand()=="Sesion") {
			Controller.getInstance().accion(eventos.GUI_SESION, null);
		}
		else if(e.getActionCommand()=="Test") {
			Controller.getInstance().accion(eventos.GUI_TEST, null);
		}
		else if(e.getActionCommand()=="Vehiculo") {
			Controller.getInstance().accion(eventos.GUI_VEHICULO, null);
		}
	}
}
