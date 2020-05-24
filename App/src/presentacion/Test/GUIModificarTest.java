package presentacion.Test;


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

import negocio.Test.TTest;
import presentacion.Controller;
import presentacion.GUIMaker;
import presentacion.eventos;

public class GUIModificarTest extends JFrame {
	private static final long serialVersionUID = 1L;
	private boolean init=false;
	private JPanel centerPanel= new JPanel(new GridLayout(5,2));
	private JLabel lblTipo = new JLabel("Tipo: ");
	private JLabel lblNpreguntas = new JLabel("Numero de preguntas: ");
	private JLabel lblActivo = new JLabel("¿Desea reactivarla?:");
	private JCheckBox cbActivo = new JCheckBox();
	private JTextField tfTipo= new JTextField();
	private JTextField tfNpreguntas = new JTextField();
	private JButton btnActualizar = new JButton("Actualizar");
	private JButton btnCancelar=new JButton("Cancelar");
	public void initGui() {
		if(init) {
			setVisible(true);
			return;
		}
		init=true;
		GUIMaker.getInstance().configurateSubWindow(this,220,110,"Modificar un test ");
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
				Controller.getInstance().accion(eventos.BUSCAR_TEST,aux );
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
	public void updatePanel(TTest t) {
		centerPanel.revalidate();
		centerPanel.repaint();
		String x = Integer.toString(t.getNumpreguntas());
		tfTipo.setText(t.getTipo());
		tfNpreguntas.setText(x);
		cbActivo.setSelected(t.isActivo());
		centerPanel.add(lblTipo); centerPanel.add(tfTipo);
		centerPanel.add(lblNpreguntas);centerPanel.add(tfNpreguntas);
		if(!t.isActivo()) {centerPanel.add(lblActivo);centerPanel.add(cbActivo);}
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TTest s= new TTest(t.getId(),
						tfTipo.getText(),Integer.parseInt(tfNpreguntas.getText()),cbActivo.isSelected());
				Controller.getInstance().accion(eventos.MODIFICAR_TEST, s);
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
