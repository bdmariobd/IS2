package presentacion.Vehiculo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import negocio.Vehiculo.TVehiculo;
import presentacion.Controller;
import presentacion.GUIMaker;
import presentacion.eventos;

public class GUIBajaVehiculo extends JFrame {
	public void initGui() {
		this.setLayout(new BorderLayout());
		JPanel baja = new JPanel(new BorderLayout());
		baja.setSize(675, 150);
		this.add(baja);
		GUIMaker.getInstance().configurateWindow(this);
		JButton btnBaja = new JButton("Dar de baja");
		JLabel tfLabel = new JLabel("Introduce id del vehiculo para darlo de baja: ");
		JTextField campoID= new JTextField();
		JButton btnCancelar = new JButton("Cancelar");
		JPanel botonera = new JPanel(new FlowLayout());
		botonera.add(btnBaja);
		botonera.add(btnCancelar);
		baja.add(tfLabel,BorderLayout.LINE_START);
		baja.add(botonera,BorderLayout.SOUTH);
		baja.add(campoID,BorderLayout.CENTER);
		btnBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((String) campoID.getText()!=null) Controller.getInstance().accion(eventos.BAJA_VEHICULO,  Integer.parseInt(campoID.getText()));
				else JOptionPane.showMessageDialog(null, "ID VACIA, por favor, inserta una ID valida.","ERROR",JOptionPane.WARNING_MESSAGE); // no funca esto
				System.out.println((String) campoID.getText());
			}
			});
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				}
			});
		}
	
}
