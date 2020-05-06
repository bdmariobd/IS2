package presentacion.Profesor;

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
import negocio.Profesor.TProfesor;
import presentacion.Controller;
import presentacion.GUIMaker;
import presentacion.eventos;

public class GUIAltaProfesor extends JFrame {
	private static final long serialVersionUID = 1L;
	private boolean init = false;
	private JButton btnAlta = new JButton("Dar de alta");
	private JButton btnCancelar = new JButton("Cancelar");
	private String[] labels = { "Insertar idSucursal", "Insertar DNI", "Insertar nombre", "Insertar apellidos",
			"Insertar telefono", "Insertar email", "Insertar sueldo" };
	private JCheckBox actividad = new JCheckBox("¿Está activo?", true);
	private JLabel etiqgeneral;
	private JTextField[] inputs = new JTextField[labels.length];

	public void initGui() {
		if (init) {
			this.setVisible(true);
			return;
		}
		init = true;
		this.setLayout(new GridLayout(8, 2, 5, 10));
		for (int i = 0; i < labels.length-1; i++) {
			etiqgeneral = new JLabel(labels[i] + ": ", SwingConstants.CENTER);
			inputs[i] = new JTextField(20);
			add(etiqgeneral);
			etiqgeneral.setLabelFor(inputs[i]);
			add(inputs[i]);
		}

		//add(new JPanel());
		add(actividad);
		add(new JPanel());
		add(btnAlta);
		add(btnCancelar);
		setVisible(true);

		GUIMaker.getInstance().configurateSubWindow(this, 410, 250, "Registrar un alta");

		// Botones
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < labels.length - 1; i++) {
					if (inputs[i].getText().isEmpty()) {
						JOptionPane.showMessageDialog(null,
								"Los campos no pueden ser vacíos", "ERROR",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}

				try {
					TProfesor a = new TProfesor(0, Integer.valueOf(inputs[0].getText()), inputs[1].getText(),
							inputs[2].getText(), inputs[3].getText(), Integer.valueOf(inputs[4].getText()),
							inputs[5].getText(), Integer.valueOf(inputs[6].getText()), actividad.isSelected());
					Controller.getInstance().accion(eventos.ALTA_PROFESOR, a);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Compruebe formato de los datos. \n (" + ex.toString() + ")",
							"Información", JOptionPane.WARNING_MESSAGE);
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
