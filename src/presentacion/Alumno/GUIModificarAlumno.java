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
import javax.swing.JPanel;
import javax.swing.JTextField;

import negocio.Alumno.TAlumno;
import presentacion.Controller;
import presentacion.GUIMaker;
import presentacion.eventos;


public class GUIModificarAlumno extends JFrame{
	private static final long serialVersionUID = 1L;

	private boolean init=false;
	
	JPanel centerPanel= new JPanel(new GridLayout(5,2));
	JLabel lblDNI = new JLabel("DNI: ");
	JLabel lblNombre = new JLabel("Nombre: ");
	JLabel lblApellido = new JLabel("Apellido: ");
	JLabel lblemail = new JLabel("Email: ");
	JLabel lbltelefono = new JLabel("Telefono: ");
	JLabel lblAmaxofobia = new JLabel("¿Tiene amaxofobia?: ");
	JLabel lblActivo = new JLabel("¿Está activo?: ");
	JCheckBox tfActivo = new JCheckBox();
	JCheckBox tfAmaxofobia = new JCheckBox();
	JTextField tfDNI= new JTextField();
	JTextField tfNombre = new JTextField();
	JTextField tfApellidos = new JTextField();
	JTextField tfEmail = new JTextField();
	JTextField tfTelefono = new JTextField();
	JButton actualizar = new JButton("Actualizar");
	JButton btnCancelar=new JButton("Cancelar");
	
	public void initGui() {
		if(init) {
			setVisible(true);
			return;
		}
		init=true;
		GUIMaker.getInstance().configurateSubWindow(this,220,110,"Modificar un alumno ");
		this.setLayout(new FlowLayout());
		JPanel upPanel = new JPanel(new BorderLayout());
		JLabel lblID = new JLabel("Introduce ID: ");
		JTextField tfID= new JTextField();
		tfID.setColumns(5);
		JButton traerDB = new JButton("Buscar en la base de datos");
		traerDB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int aux = Integer.parseInt(tfID.getText());
				Controller.getInstance().accion(eventos.BUSCAR_ALUMNO, aux);
				}
			});
		upPanel.add(lblID,BorderLayout.NORTH);
		upPanel.add(tfID,BorderLayout.CENTER);
		upPanel.add(traerDB,BorderLayout.SOUTH);
		this.add(upPanel);
		this.pack();
		this.setVisible(true);
	}
	//panel con los datos cargados
	public void updatePanel(TAlumno a) {
		tfDNI.setText(a.getDNI());
		tfNombre.setText(a.getNombre());
		tfApellidos.setText(a.getApellidos());
		tfEmail.setText(a.getEmail());
		tfTelefono.setText(String.valueOf(a.getTelefono()));
		tfAmaxofobia.setSelected(a.getAmaxofobia());
		tfActivo.setSelected(a.getActivo());
		centerPanel.add(lblDNI); centerPanel.add(tfDNI);
		centerPanel.add(lblNombre);centerPanel.add(tfNombre);
		centerPanel.add(lblApellido);centerPanel.add(tfApellidos);
		centerPanel.add(lbltelefono);centerPanel.add(tfTelefono);
		centerPanel.add(lblemail);centerPanel.add(tfEmail);
		centerPanel.add(lblActivo);centerPanel.add(tfActivo);
		centerPanel.add(lblAmaxofobia);centerPanel.add(tfAmaxofobia);
		
		actualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TAlumno alu= new TAlumno(a.getId(),tfDNI.getText(),
						tfNombre.getText(),tfApellidos.getText(),Integer.valueOf(tfTelefono.getText()),tfEmail.getText(),tfAmaxofobia.isSelected(), tfActivo.isSelected());
				Controller.getInstance().accion(eventos.MODIFICAR_ALUMNO, alu);
			}
			});
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				}
			});
		centerPanel.add(btnCancelar);
		centerPanel.add(actualizar);
		this.add(centerPanel);
		this.setSize(800, 245);
	}

}
