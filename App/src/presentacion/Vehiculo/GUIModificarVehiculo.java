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
import presentacion.eventos;

public class GUIModificarVehiculo extends JFrame {
	
	
	//panel que solo pregunta por la id
	public void initGui() {
		this.setLayout(new GridLayout(2,2));
		JPanel upPanel = new JPanel(new BorderLayout());
		JLabel lblID = new JLabel("Introduce ID: ");
		JTextField tfID= new JTextField();
		tfID.setColumns(5);
		JButton traerDB = new JButton("Buscar en la base de datos");
		traerDB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		JPanel centerPanel= new JPanel(new GridLayout(5,2));
		JLabel lblTipo = new JLabel("Tipo: ");
		JTextField tfTipo= new JTextField(veh.getTipo());
		JLabel lblDanos = new JLabel("Daños: ");
		JTextField tfDanos = new JTextField(veh.getDaños());
		JLabel lblActivo = new JLabel("¿Está activo?: ");
		JCheckBox tfActivo = new JCheckBox();
		tfActivo.setSelected(veh.isActivo());
		JLabel lblMatricula = new JLabel("Matricula: ");
		JTextField tfMatricula = new JTextField(veh.getMatricula());
		centerPanel.add(lblTipo); centerPanel.add(tfTipo);
		centerPanel.add(lblDanos);centerPanel.add(tfDanos);
		centerPanel.add(lblActivo);centerPanel.add(tfActivo);
		centerPanel.add(lblMatricula);centerPanel.add(tfMatricula);
		JButton actualizar = new JButton("Actualizar");
		actualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TVehiculo v= new TVehiculo(veh.getId(),veh.getIdSucursal(),
						tfTipo.getText(),tfMatricula.getText(),tfActivo.isSelected(),tfDanos.getText());
				Controller.getInstance().accion(eventos.MODIFICAR_VEHICULO, v);
			}
			});
		centerPanel.add(actualizar);
		this.add(centerPanel);
	}
}
