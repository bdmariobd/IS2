package presentacion.Vehiculo;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import negocio.Vehiculo.TVehProf;
import presentacion.Controller;
import presentacion.GUIMaker;
import presentacion.eventos;

@SuppressWarnings("serial")
public class GUIAsignarVeh extends JFrame {
	private boolean isInit = false;
	private JButton btnAsignar = new JButton("Asignar");
	private JButton btnCancelar = new JButton("Cancelar");
	private String[] labels = {"Insertar ID vehiculo","Insertar ID profesor"};
	private JLabel etiqGeneral;
	private JTextField[] inputs = new JTextField[labels.length];
	
	public void initGui() {
		if(isInit) {
			this.setVisible(true);
			return;
		}
		isInit=true;
		this.setLayout(new GridLayout(3,2,5,10));
        for(int i = 0; i<labels.length;i++) {
            etiqGeneral = new JLabel(labels[i]+": ", SwingConstants.CENTER);
            inputs[i] = new JTextField(20);
            add(etiqGeneral);
            etiqGeneral.setLabelFor(inputs[i]);
            add(inputs[i]);
        }
        add(btnAsignar);
        add(btnCancelar);
        setVisible(true);

		GUIMaker.getInstance().configurateSubWindow(this,410,250,"Asignar un alumno");
		
		//Botones
		btnAsignar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<2;i++) { 
					if(inputs[i].getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "ID vehiculo o ID profesor vacios","ERROR",JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				
				try {
					TVehProf pv = new TVehProf(Integer.parseInt(inputs[1].getText()),Integer.parseInt(inputs[0].getText()));
					Controller.getInstance().accion(eventos.ASIGNAR_VEHPROF, pv);
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
