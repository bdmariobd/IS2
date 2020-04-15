package presentacion.Vehiculo;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import negocio.Vehiculo.TVehiculo;
import presentacion.Controller;
import presentacion.GUIMaker;
import presentacion.eventos;

public class GUIAltaVehiculo extends JFrame {
	private boolean init=false;
	public void initGui() {
	if(init) {
		setVisible(true);
		return;
	}
	init=true;
	this.setLayout(new GridLayout(6,2,5,10));
	//Declaracion de componentes.
	JButton btnAlta = new JButton("Dar de alta");
	JButton btnCancelar = new JButton("Cancelar");
	String[] labels = {"Insertar matricula","Insertar sucursal", "Insertar tipo",
			"Insertar daños"};
	JLabel etiqgeneral;
	JCheckBox actividad = new JCheckBox("¿Está activo?",true);
	JTextField[] inputs = new JTextField[labels.length];
	for(int i = 0; i<labels.length;i++) {
		etiqgeneral = new JLabel(labels[i]+": ", SwingConstants.CENTER);
		inputs[i] = new JTextField(20);
		add(etiqgeneral);
		etiqgeneral.setLabelFor(inputs[i]);
		add(inputs[i]);
	}
	//Anadir componentes
	add(new JPanel());
	add(actividad);
	add(btnAlta);
	add(btnCancelar);
	setVisible(true);
	GUIMaker.getInstance().configurateWindow(this);
	//Botones
	btnAlta.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			TVehiculo v= new TVehiculo(100,Integer.parseInt(inputs[1].getText()),
					inputs[2].getText(),inputs[0].getText(),actividad.isSelected(),inputs[3].getText());
			Controller.getInstance().accion(eventos.ALTA_VEHICULO, v);
		}
		});
	btnCancelar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			dispose();
			}
		});
	}
	
}
