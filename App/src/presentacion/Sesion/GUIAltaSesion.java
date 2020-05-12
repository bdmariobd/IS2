package presentacion.Sesion;


	import java.awt.GridLayout;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JButton;
	import javax.swing.JCheckBox;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JOptionPane;
	import javax.swing.JPanel;
	import javax.swing.JTextField;
	import javax.swing.SwingConstants;
	import negocio.Sesion.TSesion;
	import presentacion.Controller;
	import presentacion.GUIMaker;
	import presentacion.eventos;

	@SuppressWarnings("serial")
	public class GUIAltaSesion extends JFrame {
		private boolean init=false;
		private JButton btnAlta = new JButton("Dar de alta");
		private JButton btnCancelar = new JButton("Cancelar");
		private String[] labels = {"Insertar fecha","Insertar hora inicio","Insertar hora fin", "Insertar tipo"," Insetar ID profesor", "Insertar ID alumno" };
		private JLabel etiqgeneral;
		private JCheckBox actividad = new JCheckBox("¿Está activo?",true);
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
		//Anadir componentesaa
		add(new JPanel());
		add(actividad);
		add(btnAlta);
		add(btnCancelar);
		setVisible(true);
		GUIMaker.getInstance().configurateSubWindow(this,410,250,"Registrar un alta");
		//Botones
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<labels.length;i++) { 
					if(inputs[i].getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "fecha, horaIni, horaFin, tipo, id profesor e id alumno no pueden estar vacias","ERROR",JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				
				try {
					
					DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
					Date date = format.parse(inputs[0].getText());
					DateFormat formatter = new SimpleDateFormat("HH:mm");
					Time horaI = (Time)formatter.parse(inputs[1].getText());
					Time horaF = (Time)formatter.parse(inputs[2].getText());
					TSesion s= new TSesion(0,date, horaI,horaF,inputs[3].getText(), Integer.parseInt(inputs[4].getText()),Integer.parseInt(inputs[5].getText()));  //VDFSFDSdsfDF
					Controller.getInstance().accion(eventos.ALTA_SUCURSAL, s);
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

