package presentacion.Sucursal;


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

import negocio.Sucursal.TSucursal;
import presentacion.Controller;
import presentacion.GUIMaker;
import presentacion.eventos;

public class GUIModificarSucursal extends JFrame {
	
	private boolean init=false, init2=false;
	JPanel centerPanel= new JPanel(new GridLayout(5,2));
	JLabel lblCiudad = new JLabel("Ciudad: ");
	JLabel lblTelefono = new JLabel("Telefono: ");
	JLabel lblDireccion = new JLabel("Direccion: ");
	JLabel lblActivo = new JLabel("¿Está activa?: ");
	JCheckBox tfActivo = new JCheckBox();
	JTextField tfCiudad= new JTextField();
	JTextField tfTelefono = new JTextField();
	JTextField tfDireccion = new JTextField();
	JButton actualizar = new JButton("Actualizar");
	JButton btnCancelar=new JButton("Cancelar");
	public void initGui() {
		if(init) {
			setVisible(true);
			return;
		}
		init=true;
		GUIMaker.getInstance().configurateSubWindow(this,220,110,"Modificar una sucursal ");
		this.setLayout(new FlowLayout());
		JPanel upPanel = new JPanel(new BorderLayout());
		JLabel lblID = new JLabel("Introduce ID: ");
		JTextField tfID= new JTextField();
		tfID.setColumns(5);
		JButton traerDB = new JButton("Buscar en la base de datos");
		traerDB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int aux = Integer.parseInt(tfID.getText());
				Controller.getInstance().accion(eventos.BUSCAR_SUCURSAL,aux );
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
	public void updatePanel(TSucursal suc) {
		String x = Integer.toString(suc.getTelefono());
		tfCiudad.setText(suc.getCiudad());
		tfTelefono.setText(x);
		tfDireccion.setText(suc.getDireccion());
		tfActivo.setSelected(suc.isActivo());
		centerPanel.add(lblCiudad); centerPanel.add(tfCiudad);
		centerPanel.add(lblTelefono);centerPanel.add(tfTelefono);
		centerPanel.add(lblDireccion);centerPanel.add(tfDireccion);
		centerPanel.add(lblActivo);centerPanel.add(tfActivo);
		actualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TSucursal s= new TSucursal(suc.getId(),
						tfCiudad.getText(),Integer.parseInt(tfTelefono.getText()),tfDireccion.getText(),tfActivo.isSelected());
				Controller.getInstance().accion(eventos.MODIFICAR_SUCURSAL, s);
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
