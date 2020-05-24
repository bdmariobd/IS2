package presentacion.Vehiculo;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import presentacion.Controller;
import presentacion.GUIMaker;
import presentacion.eventos;

@SuppressWarnings("serial")
public class GUIRegistrarVehiculo extends JFrame {
	private boolean isInit=false;
	public void initGui() {
		if(isInit) {
			setVisible(true);
			return;
		}
		isInit=true;
		GUIMaker.getInstance().configurateSubWindow(this,840,120,"Registrar daños en un vehículo ");
		JPanel reg = new JPanel(new GridLayout(3,2));
		this.add(reg);
		this.setVisible(true);
		JLabel idInsert = new JLabel("Inserta la ID del coche para registrar daños: ");
		JLabel dmgInsert = new JLabel("Inserta los daños del vehículo: ");
		JButton ok= new JButton("Insertar");
		JButton cancel = new JButton("Cancelar");
		JTextField idCampo=new JTextField();
		JTextField danosCampo=new JTextField();
		reg.add(idInsert);
		reg.add(idCampo);
		reg.add(dmgInsert);
		reg.add(danosCampo);
		reg.add(ok);
		reg.add(cancel);
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String [] datos = {idCampo.getText(),danosCampo.getText()};
				Controller.getInstance().accion(eventos.REGISTRAR_DANOS, datos);
			}
			
		});
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				}
			});
		}
		
		
}
	
	
	

