package presentacion.Profesor;

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

import negocio.Profesor.TProfesor;
import presentacion.Controller;
import presentacion.GUIMaker;
import presentacion.eventos;


public class GUIModificarProfesor extends JFrame{
	private static final long serialVersionUID = 1L;

	private boolean init=false;
	
	JPanel centerPanel= new JPanel(new GridLayout(5,2));
	JLabel lblIdSucursal = new JLabel("Id sucursal: ");
	JLabel lblDNI = new JLabel("DNI: ");
	JLabel lblNombre = new JLabel("Nombre: ");
	JLabel lblApellido = new JLabel("Apellido: ");
	JLabel lblemail = new JLabel("Email: ");
	JLabel lbltelefono = new JLabel("Telefono: ");
	JLabel lblSueldo = new JLabel("Sueldo: ");
	JLabel lblActivo = new JLabel("¿Está activo?: ");
	JCheckBox tfActivo = new JCheckBox();
	JTextField tfIdSucursal= new JTextField();
	JTextField tfDNI= new JTextField();
	JTextField tfNombre = new JTextField();
	JTextField tfApellidos = new JTextField();
	JTextField tfEmail = new JTextField();
	JTextField tfTelefono = new JTextField();
	JTextField tfSueldo = new JTextField();
	JButton actualizar = new JButton("Actualizar");
	JButton btnCancelar=new JButton("Cancelar");
	
	public void initGui() {
		if(init) {
			setVisible(true);
			return;
		}
		init=true;
		GUIMaker.getInstance().configurateSubWindow(this,220,110,"Modificar un profesor ");
		this.setLayout(new FlowLayout());
		JPanel upPanel = new JPanel(new BorderLayout());
		JLabel lblID = new JLabel("Introduce ID: ");
		JTextField tfID= new JTextField();
		tfID.setColumns(5);
		JButton traerDB = new JButton("Buscar en la base de datos");
		traerDB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int aux = Integer.parseInt(tfID.getText());
				Controller.getInstance().accion(eventos.BUSCAR_PROFESOR, aux);
				}
			});
		upPanel.add(lblID,BorderLayout.NORTH);
		upPanel.add(tfID,BorderLayout.CENTER);
		upPanel.add(traerDB,BorderLayout.SOUTH);
		this.add(upPanel);
		this.pack();
		this.setVisible(true);
	}
	//panel con los datos cargados.
	public void updatePanel(TProfesor a) {
		tfIdSucursal.setText(String.valueOf(a.getIdSucursal()));
		tfDNI.setText(a.getDNI());
		tfNombre.setText(a.getNombre());
		tfApellidos.setText(a.getApellidos());
		tfEmail.setText(a.getEmail());
		tfTelefono.setText(String.valueOf(a.getTelefono()));
		tfSueldo.setText(String.valueOf(a.getSueldo()));
		
		tfActivo.setSelected(a.getActivo());
		centerPanel.add(lblIdSucursal); centerPanel.add(tfIdSucursal);
		centerPanel.add(lblDNI); centerPanel.add(tfDNI);
		centerPanel.add(lblNombre);centerPanel.add(tfNombre);
		centerPanel.add(lblApellido);centerPanel.add(tfApellidos);
		centerPanel.add(lbltelefono);centerPanel.add(tfTelefono);
		centerPanel.add(lblemail);centerPanel.add(tfEmail);
		centerPanel.add(lblSueldo);centerPanel.add(tfSueldo);
		centerPanel.add(lblActivo);centerPanel.add(tfActivo);
		
		actualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TProfesor profesor= new TProfesor(a.getId(),Integer.valueOf(tfIdSucursal.getText()),tfDNI.getText(),
						tfNombre.getText(),tfApellidos.getText(),Integer.valueOf(tfTelefono.getText()),tfEmail.getText(),Integer.valueOf(tfSueldo.getText()), tfActivo.isSelected());
				Controller.getInstance().accion(eventos.MODIFICAR_PROFESOR, profesor);
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
