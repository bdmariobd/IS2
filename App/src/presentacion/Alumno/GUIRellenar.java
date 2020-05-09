package presentacion.Alumno;

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
import negocio.Alumno.TAlumno;
import negocio.Alumno.TRelleno;
import presentacion.Controller;
import presentacion.GUIMaker;
import presentacion.eventos;

@SuppressWarnings("serial")
public class GUIRellenar extends JFrame{
	private boolean init = false;
	private JButton btnAlta = new JButton("Rellenar");
	private JButton btnCancelar = new JButton("Cancelar");
	private String[] labels = {"Insertar ID alumno","Insertar ID test", "Insertar numero de fallos"};
	private JLabel etiqgeneral;
	private JTextField[] inputs = new JTextField[labels.length];
	
	public void initGui() {
		if(init) {
			this.setVisible(true);
			return;
		}
		init=true;
		this.setLayout(new GridLayout(8,2,5,10));
        for(int i = 0; i<labels.length;i++) {
            etiqgeneral = new JLabel(labels[i]+": ", SwingConstants.CENTER);
            inputs[i] = new JTextField(20);
            add(etiqgeneral);
            etiqgeneral.setLabelFor(inputs[i]);
            add(inputs[i]);
        }

        add(new JPanel());
        add(new JPanel());
        add(btnAlta);
        add(btnCancelar);
        setVisible(true);

		GUIMaker.getInstance().configurateSubWindow(this,410,250,"Rellenar un test");
		
		//Botones
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<labels.length-1;i++) { // Menos uno por que danos que es la ultima puede ser vacia
					if(inputs[i].getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "ID alumno, ID test y numero de fallos","ERROR",JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				
				try {
					TRelleno a = new TRelleno(Integer.parseInt(inputs[0].getText()),Integer.parseInt(inputs[1].getText()),Integer.parseInt(inputs[2].getText()));
					Controller.getInstance().accion(eventos.RELLENAR_TEST, a);
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
	