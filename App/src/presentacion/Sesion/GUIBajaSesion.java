package presentacion.Sesion;


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

import negocio.Sesion.TSesion;
import presentacion.Controller;
import presentacion.GUIMaker;
import presentacion.eventos;

public class GUIBajaSesion extends JFrame {
	private JPanel principal = new JPanel(new GridLayout(2,2));
	private JButton btnBaja = new JButton("Borrar");
	private JPanel prueba = new JPanel(new BorderLayout());
	private JLabel tfLabel = new JLabel("Introduce id de la Sesion para darle de baja: ");
	private JTextField campoID= new JTextField();
	private JButton btnCancelar = new JButton("Cancelar");
	public void initGui() {
		
		GUIMaker.getInstance().configurateSubWindow(this,520,100,"Borrar sesion ");
		tfLabel.setHorizontalAlignment(JLabel.CENTER);
		campoID.setColumns(5);
		prueba.add(campoID,BorderLayout.LINE_START);
		principal.add(tfLabel);
		principal.add(prueba);
		principal.add(btnBaja);
		principal.add(btnCancelar);
		add(principal);
		btnBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!campoID.getText().isEmpty()) Controller.getInstance().accion(eventos.BAJA_SESION, (campoID.getText()));
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
