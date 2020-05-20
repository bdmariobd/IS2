package presentacion.Sesion;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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

public class GUIModificarSesion extends JFrame {
	
	private boolean init=false, init2=false;
	JPanel centerPanel= new JPanel(new GridLayout(5,2));
	JLabel lblFecha = new JLabel("Fecha: ");
	JLabel lblHoraIni = new JLabel("Hora Inicio: ");
	JLabel lblHoraFin = new JLabel("Hora final: "); 
	JLabel lblTipo = new  JLabel("Tipo: ");
	JLabel lblIdA = new  JLabel("ID alumno: ");
	JLabel lblIdP = new  JLabel("ID profesor: ");
	JLabel lblActivo = new JLabel("¿Está activa?: ");
	JCheckBox tfActivo = new JCheckBox();
	JTextField tfFecha = new JTextField();
	JTextField tfHoraIni = new JTextField();
	JTextField tfHoraFin = new JTextField();
	JTextField tfTipo = new JTextField();
	JTextField tfIdA = new JTextField();
	JTextField tfIdP = new JTextField();
	JButton actualizar = new JButton("Actualizar");
	JButton btnCancelar=new JButton("Cancelar");
	public void initGui() {
		if(init) {
			setVisible(true);
			return;
		}
		init=true;
		GUIMaker.getInstance().configurateSubWindow(this,220,110,"Modificar una sesion ");
		this.setLayout(new FlowLayout());
		JPanel upPanel = new JPanel(new BorderLayout());
		JLabel lblID = new JLabel("Introduce ID: ");
		JTextField tfID= new JTextField();
		tfID.setColumns(5);
		JButton traerDB = new JButton("Buscar en la base de datos");
		traerDB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int aux = Integer.parseInt(tfID.getText());
				Controller.getInstance().accion(eventos.BUSCAR_SESION,aux );
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
		tfFecha.setText(ses.getFecha().toString()); //////
		tfHoraIni.setText(ses.getHoraini().toString());
		tfHoraFin.setText(ses.getHorafin().toString());
		tfTipo.setText(ses.getTipo().toString());
		tfIdP.setText(ses.getTipo().toString());
		tfIdA.setText(ses.getTipo().toString());
		tfActivo.setSelected(ses.isActivo());
		centerPanel.add(lblFecha); centerPanel.add(tfFecha);
		centerPanel.add(lblHoraIni);centerPanel.add(tfHoraIni);
		centerPanel.add(lblHoraFin);centerPanel.add(tfHoraFin);
		centerPanel.add(lblTipo);centerPanel.add(tfTipo);
		centerPanel.add(lblActivo);centerPanel.add(tfActivo);
		centerPanel.add(lblIdA);centerPanel.add(tfIdA);
		centerPanel.add(lblIdP);centerPanel.add(tfIdP);

		actualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
				Date date=null;
				try {
					date = format.parse(tfFecha.getText());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				DateFormat formatter = new SimpleDateFormat("HH:mm");
				Time horaI=null;
				try {
					horaI = (Time)formatter.parse(tfHoraIni.getText());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Time horaF=null;
				try {
					horaF = (Time)formatter.parse(tfHoraFin.getText());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				TSesion s= new TSesion(0,date, horaI,horaF,tfTipo.getText(),tfActivo.isSelected(), Integer.parseInt(tfIdA.getText()),Integer.parseInt(tfIdP.getText()));  //VDFSFDSdsfDF
				Controller.getInstance().accion(eventos.ALTA_SESION, s);
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
