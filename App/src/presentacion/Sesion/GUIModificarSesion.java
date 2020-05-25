package presentacion.Sesion;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import negocio.Sesion.TSesion;
import presentacion.Controller;
import presentacion.GUIMaker;
import presentacion.eventos;
import resources.fechasConverter;

public class GUIModificarSesion extends JFrame {
	private static final long serialVersionUID = 1L;
	private boolean isInit=false;
	private JPanel centerPanel= new JPanel(new GridLayout(4,2));
	private JLabel lblFecha = new JLabel("Fecha: ");
	private JLabel lblHoraIni = new JLabel("Hora Inicio: ");
	private JLabel lblHoraFin = new JLabel("Hora final: "); 
	private JLabel lblTipo = new  JLabel("Tipo: ");
	private JLabel lblIdA = new  JLabel("ID alumno: ");
	private JLabel lblIdP = new  JLabel("ID profesor: ");
	private JLabel lblActivo = new JLabel("¿Desea reactivarla?: ");
	private JCheckBox cbActivo = new JCheckBox();
	private JTextField tfFecha = new JTextField();
	private JTextField tfHoraIni = new JTextField();
	private JTextField tfHoraFin = new JTextField();
	private JTextField tfTipo = new JTextField();
	private JTextField tfIdA = new JTextField();
	private JTextField tfIdP = new JTextField();
	
	JButton actualizar = new JButton("Actualizar");
	JButton btnCancelar=new JButton("Cancelar");
	public void initGui() {
		if(isInit) {
			setVisible(true);
			return;
		}
		isInit=true;
		GUIMaker.getInstance().configurateSubWindow(this,220,110,"Modificar una sesion ");
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
				Controller.getInstance().accion(eventos.BUSCAR_SESION,aux);
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
	public void updatePanel(TSesion ses) {
		centerPanel.revalidate();
		centerPanel.repaint();
		tfFecha.setText(fechasConverter.fechaToString(ses.getFecha())); //////
		tfHoraIni.setText(fechasConverter.horaToString(ses.getHoraini()));
		tfHoraFin.setText(fechasConverter.horaToString(ses.getHorafin()));
		tfTipo.setText(ses.getTipo());
		cbActivo.setSelected(ses.isActivo());
		tfIdP.setText(Integer.toString(ses.getIdProfesor()));
		tfIdA.setText(Integer.toString(ses.getIdAlumno()));
		centerPanel.add(lblFecha); centerPanel.add(tfFecha);
		centerPanel.add(lblHoraIni);centerPanel.add(tfHoraIni);
		centerPanel.add(lblHoraFin);centerPanel.add(tfHoraFin);
		centerPanel.add(lblTipo);centerPanel.add(tfTipo);
		if(!ses.isActivo()) {centerPanel.add(lblActivo);centerPanel.add(cbActivo);}
		centerPanel.add(lblIdA);centerPanel.add(tfIdA);
		centerPanel.add(lblIdP);centerPanel.add(tfIdP);

		actualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date date = fechasConverter.StringFechaToDate(tfFecha.getText()), 
						horaI= fechasConverter.StringHoraToDate(tfHoraIni.getText()), 
						horaF= fechasConverter.StringHoraToDate(tfHoraFin.getText());
				TSesion s= new TSesion(ses.getId(),date, horaI,horaF,tfTipo.getText(),cbActivo.isSelected(), ses.getIdAlumno(),ses.getIdProfesor());  
				Controller.getInstance().accion(eventos.MODIFICAR_SESION, s);
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
		this.setSize(850, 245);
	}
}
