package presentacion.Vehiculo;

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

public class GUIBajaVehiculo extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel principal = new JPanel(new GridLayout(2,2));
	private JButton btnBaja = new JButton("Dar de baja");
	private JPanel prueba = new JPanel(new BorderLayout());
	private JLabel lblId = new JLabel("Introduce id del vehiculo para darlo de baja: ");
	private JTextField campoID= new JTextField();
	private JButton btnCancelar = new JButton("Cancelar");
	public void initGui() {
		
		GUIMaker.getInstance().configurateSubWindow(this,520,100,"Registrar una baja ");
		lblId.setHorizontalAlignment(JLabel.CENTER);
		campoID.setColumns(5);
		prueba.add(campoID,BorderLayout.LINE_START);
		principal.add(lblId);
		principal.add(prueba);
		principal.add(btnBaja);
		principal.add(btnCancelar);
		add(principal);
		btnBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!campoID.getText().isEmpty()) Controller.getInstance().accion(eventos.BAJA_VEHICULO, (campoID.getText()));
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
