package presentacion.Profesor;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import presentacion.Controller;
import presentacion.GUIMaker;
import presentacion.eventos;


public class GUIBajaProfesor extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel principal = new JPanel(new GridLayout(2,2));
	private JButton btnBaja = new JButton("Dar de baja");
	private JPanel prueba = new JPanel(new BorderLayout());
	private JLabel tfLabel = new JLabel("Introduce el ID del profesor para darle de baja: ");
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
				if(!campoID.getText().isEmpty()) Controller.getInstance().accion(eventos.BAJA_PROFESOR, (campoID.getText()));
				else JOptionPane.showMessageDialog(null, "ID VACÍA, por favor, inserta una ID válida.","ERROR",JOptionPane.WARNING_MESSAGE);
				}
			});
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				}
			});
		}
}
