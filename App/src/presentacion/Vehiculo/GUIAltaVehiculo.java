package presentacion.Vehiculo;
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
import javax.swing.SwingConstants;
import negocio.Vehiculo.TVehiculo;
import presentacion.Controller;
import presentacion.GUIMaker;
import presentacion.eventos;

@SuppressWarnings("serial")
public class GUIAltaVehiculo extends JFrame {
	private boolean init=false;
	private JButton btnAlta = new JButton("Dar de alta");
	private JButton btnCancelar = new JButton("Cancelar");
	private String[] labels = {"Insertar matricula","Insertar ID sucursal", "Insertar tipo",
			"Insertar daños"};
	private JLabel etiqgeneral;
	//private JCheckBox actividad = new JCheckBox("¿Está activo?",true);
	private JTextField[] inputs = new JTextField[labels.length];
	
	public void initGui() {
	if(init) {
		this.setVisible(true);
		return;
	}
	init=true;
	this.setLayout(new GridLayout(6,2,5,10));
	for(int i = 0; i<labels.length;i++) {
		etiqgeneral = new JLabel(labels[i]+": ", SwingConstants.CENTER);
		inputs[i] = new JTextField(20);
		add(etiqgeneral);
		etiqgeneral.setLabelFor(inputs[i]);
		add(inputs[i]);
	}
	//Anadir componentes
	//add(new JPanel());
	//add(actividad);
	add(btnCancelar);
	add(btnAlta);
	setVisible(true);
	GUIMaker.getInstance().configurateSubWindow(this,410,250,"Registrar un alta");
	//Botones
	btnAlta.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			for(int i=0;i<labels.length-1;i++) { // Menos uno por que danos que es la ultima puede ser vacia
				if(inputs[i].getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Matricula, tipo y ID sucursal no pueden ser vacios","ERROR",JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			
			try {
				TVehiculo v= new TVehiculo(0,Integer.parseInt(inputs[1].getText()),
						inputs[2].getText(),inputs[0].getText().toUpperCase(),true,inputs[3].getText());
				Controller.getInstance().accion(eventos.ALTA_VEHICULO, v);
			}
			catch(Exception ex){
				JOptionPane.showMessageDialog(null, "Compruebe formato de los datos. \n ("+ex.toString()+")","Información",JOptionPane.WARNING_MESSAGE);
			}
		}
		});
	btnCancelar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			dispose();
			}
		});
	}
	
}
