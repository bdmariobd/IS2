package presentacion.Vehiculo;

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

import negocio.Vehiculo.TVehiculo;
import presentacion.Controller;
import presentacion.GUIMaker;
import presentacion.eventos;

public class GUIModificarVehiculo extends JFrame {
	private static final long serialVersionUID = 1L;
	private boolean init=false, init2=false;
	JPanel centerPanel= new JPanel(new GridLayout(5,2));
	JLabel lblTipo = new JLabel("Tipo: ");
	JLabel lblDanos = new JLabel("Daños: ");
	JLabel lblActivo = new JLabel("¿Desea reactivarla?: ");
	JCheckBox tfActivo = new JCheckBox();
	JLabel lblMatricula = new JLabel("Matricula: ");
	JTextField tfTipo= new JTextField();
	JTextField tfDanos = new JTextField();
	JTextField tfMatricula = new JTextField();
	JButton actualizar = new JButton("Actualizar");
	JButton btnCancelar=new JButton("Cancelar");
	public void initGui() {
		if(init) {
			setVisible(true);
			return;
		}
		init=true;
		GUIMaker.getInstance().configurateSubWindow(this,220,110,"Modificar un vehículo ");
		this.setLayout(new FlowLayout());
		JPanel upPanel = new JPanel(new BorderLayout());
		JLabel lblID = new JLabel("Introduce ID: ");
		JTextField tfID= new JTextField();
		tfID.setColumns(5);
		JButton traerDB = new JButton("Buscar en la base de datos");
		traerDB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				centerPanel.remove(lblActivo);centerPanel.remove(tfActivo);
				int aux = Integer.parseInt(tfID.getText());
				Controller.getInstance().accion(eventos.BUSCAR_VEHICULO,aux );
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
	public void updatePanel(TVehiculo veh) {
		centerPanel.revalidate();
		centerPanel.repaint();
		tfTipo.setText(veh.getTipo());
		tfDanos.setText(veh.getDaños());
		tfActivo.setSelected(veh.isActivo());
		tfMatricula.setText(veh.getMatricula());
		centerPanel.add(lblTipo); centerPanel.add(tfTipo);
		centerPanel.add(lblDanos);centerPanel.add(tfDanos);
		if(!veh.isActivo()) {centerPanel.add(lblActivo);centerPanel.add(tfActivo);}
		centerPanel.add(lblMatricula);centerPanel.add(tfMatricula);
		tfDanos.setColumns(30);
		actualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TVehiculo v= new TVehiculo(veh.getId(),veh.getIdSucursal(),
						tfTipo.getText(),tfMatricula.getText(),tfActivo.isSelected(),tfDanos.getText());
				Controller.getInstance().accion(eventos.MODIFICAR_VEHICULO, v);
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
