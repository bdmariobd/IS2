package presentacion.Test;


	import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import negocio.Test.TTest;
import presentacion.Controller;
import presentacion.GUIMaker;
import presentacion.eventos;

	@SuppressWarnings("serial")
	public class GUIAltaTest extends JFrame {
		private boolean isInit=false;
		private JButton btnAlta = new JButton("Dar de alta");
		private JButton btnCancelar = new JButton("Cancelar");
		private String[] labels = {"Insertar tipo","Insertar numero de preguntas" };
		private JLabel etiqGeneral;
		//private JCheckBox actividad = new JCheckBox("¿Está activo?",true);
		private JTextField[] inputs = new JTextField[labels.length];
		
		public void initGui() {
		if(isInit) {
			this.setVisible(true);
			return; //a
		}
		isInit=true;
		this.setLayout(new GridLayout(6,2,5,10));
		for(int i = 0; i<labels.length;i++) {
			etiqGeneral = new JLabel(labels[i]+": ", SwingConstants.CENTER);
			inputs[i] = new JTextField(20);
			add(etiqGeneral);
			etiqGeneral.setLabelFor(inputs[i]);
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
				for(int i=0;i<labels.length;i++) { // Menos uno por que danos que es la ultima puede ser vacia,jjaja aqui no salu2
					if(inputs[i].getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "tipo y numero de preguntas no pueden estar vacias","ERROR",JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				
				try {
					TTest s= new TTest(0,inputs[0].getText(), 
							Integer.parseInt(inputs[1].getText()),true);
					Controller.getInstance().accion(eventos.ALTA_TEST, s);
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

