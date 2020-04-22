package presentacion.Alumno;

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

import negocio.Alumno.TAlumno;
import presentacion.Controller;
import presentacion.GUIMaker;
import presentacion.eventos;


public class GUIBajaAlumno extends JFrame{
	private JPanel principal = new JPanel(new GridLayout(2,2));
	private JButton btnBaja = new JButton("Dar de baja");
	private JPanel prueba = new JPanel(new BorderLayout());
	private JLabel tfLabel = new JLabel("Introduce el ID del alumno para darlo de baja: ");
	private JTextField campoID= new JTextField();
	private JButton btnCancelar = new JButton("Cancelar");
	public void initGui() {
		
		GUIMaker.getInstance().configurateSubWindow(this,520,100,"Registrar una baja ");
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
				if(!campoID.getText().isEmpty()) Controller.getInstance().accion(eventos.BAJA_ALUMNO, (campoID.getText()));
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
