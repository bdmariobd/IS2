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

	private boolean isInit=false;
	private JPanel centerPanel= new JPanel(new GridLayout(4,2));
	private JLabel lblDNI = new JLabel("DNI: ");
	private JLabel lblNombre = new JLabel("Nombre: ");
	private JLabel lblApellido = new JLabel("Apellido: ");
	private JLabel lblemail = new JLabel("Email: ");
	private JLabel lbltelefono = new JLabel("Telefono: ");
	private JLabel lblAmaxofobia = new JLabel("¿Tiene amaxofobia?: ");
	private JLabel lblActivo = new JLabel("¿Desea reactivarla?: ");
	private JCheckBox cbActivo = new JCheckBox();
	private JCheckBox cbAmaxofobia = new JCheckBox();
	private JTextField tfDNI= new JTextField();
	private JTextField tfNombre = new JTextField();
	private JTextField tfApellidos = new JTextField();
	private JTextField tfEmail = new JTextField();
	private JTextField tfTelefono = new JTextField();
	private JButton btnActualizar = new JButton("Actualizar");
	private JButton btnCancelar=new JButton("Cancelar");
	
	public void initGui() {
		if(isInit) {
			setVisible(true);
			return;
		}
		isInit=true;
		GUIMaker.getInstance().configurateSubWindow(this,220,110,"Modificar un alumno ");
		this.setLayout(new FlowLayout());
		JPanel upPanel = new JPanel(new BorderLayout());
		JLabel lblID = new JLabel("Introduce ID: ");
		JTextField tfID= new JTextField();
		tfID.setColumns(5);
		JButton traerDB = new JButton("Buscar en la base de datos");
		traerDB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				centerPanel.remove(lblActivo);centerPanel.remove(cbActivo);
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
		centerPanel.revalidate();
		centerPanel.repaint();
		tfDNI.setText(a.getDNI());
		tfNombre.setText(a.getNombre());
		tfApellidos.setText(a.getApellidos());
		tfEmail.setText(a.getEmail());
		tfTelefono.setText(String.valueOf(a.getTelefono()));
		cbAmaxofobia.setSelected(a.getAmaxofobia());
		cbActivo.setSelected(a.getActivo());
		centerPanel.add(lblDNI); centerPanel.add(tfDNI);
		centerPanel.add(lblNombre);centerPanel.add(tfNombre);
		centerPanel.add(lblApellido);centerPanel.add(tfApellidos);
		centerPanel.add(lbltelefono);centerPanel.add(tfTelefono);
		centerPanel.add(lblemail);centerPanel.add(tfEmail);
		if(!a.getActivo()) {centerPanel.add(lblActivo);centerPanel.add(cbActivo);}
		centerPanel.add(lblAmaxofobia);centerPanel.add(cbAmaxofobia);
		
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TAlumno alu= new TAlumno(a.getId(),tfDNI.getText(),
						tfNombre.getText(),tfApellidos.getText(),Integer.valueOf(tfTelefono.getText()),tfEmail.getText(),cbAmaxofobia.isSelected(), cbActivo.isSelected());
				Controller.getInstance().accion(eventos.MODIFICAR_ALUMNO, alu);
			}
			});
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				}
			});
		centerPanel.add(btnCancelar);
		centerPanel.add(btnActualizar);
		this.add(centerPanel);
		this.setSize(800, 245);
	}

}
