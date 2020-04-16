package presentacion.Vehiculo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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
	
	private JButton btnBaja = new JButton("Dar de baja");
	private JPanel prueba = new JPanel(new BorderLayout());
	private JLabel tfLabel = new JLabel("Introduce id del vehiculo para darlo de baja: ");
	private JTextField campoID= new JTextField();
	private JButton btnCancelar = new JButton("Cancelar");
	public void initGui() {
	
		this.setLayout(new GridLayout(2,2));
		GUIMaker.getInstance().configurateSubWindow(this,675,150,"Registrar una baja ");
		tfLabel.setHorizontalAlignment(JLabel.CENTER);
		campoID.setColumns(5);
		prueba.add(campoID,BorderLayout.LINE_START);
		add(tfLabel);
		add(prueba);
		add(btnBaja);
		add(btnCancelar);
		/////////////////////
		btnBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!campoID.getText().isEmpty()) Controller.getInstance().accion(eventos.BAJA_VEHICULO,  Integer.parseInt(campoID.getText()));
				else JOptionPane.showMessageDialog(null, "ID VACIA, por favor, inserta una ID valida.","ERROR",JOptionPane.WARNING_MESSAGE);
				}
			});
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				}
			});
		}
	
}
