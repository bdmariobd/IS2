package presentacion.Vehiculo;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import negocio.Vehiculo.TVehiculo;
import presentacion.Controller;
import presentacion.GUIMaker;
import presentacion.eventos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIMostrarVehiculo extends JFrame {
	
	JPanel panelcentro = new JPanel(new FlowLayout());
	

	public void mostrarUno(TVehiculo veh) {
		String[] colNames = {"id", "idSucursal","tipo","daños","activo","matricula"};
		String[][]datos = {{Integer.toString(veh.getId()),Integer.toString(veh.getIdSucursal()),
			veh.getTipo(),veh.getDaños(), Boolean.toString(veh.isActivo()),veh.getMatricula()}};
						
		JTable jt= new JTable(datos,colNames);
		jt.setRowHeight(30);
		JPanel p= new JPanel();
		p.setSize(500, 100);
		p.add(jt);
		panelcentro.add(p);
		this.setVisible(true);
	}
	public void initGui() {
		this.setLayout(new BorderLayout());
		GUIMaker.getInstance().configurateWindow(this);
		JPanel panelEtiq = new JPanel(new FlowLayout());
		JLabel lbl= new JLabel ("Inserta una ID, por favor: ");
		JTextField idCampo = new JTextField();
		idCampo.setColumns(5);
		panelEtiq.add(lbl);
		panelEtiq.add(idCampo);
		add(panelEtiq, BorderLayout.NORTH);
		
		JButton btnBuscar = new JButton("Buscar!");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dato = Integer.parseInt(idCampo.getText());
				Controller.getInstance().accion(eventos.MOSTRAR_UNO_VEHICULO,dato);
				}
			});
		
		panelcentro.add(btnBuscar);
		add(panelcentro,BorderLayout.CENTER);
		this.setSize(600,120);
		this.setVisible(true);
		
	}
	
}
