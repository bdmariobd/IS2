package presentacion.Alumno;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import negocio.Alumno.TAlumno;
import presentacion.Controller;
import presentacion.GUIMaker;
import presentacion.eventos;

public class GUIMostrarAlumno extends JFrame{
	private static final long serialVersionUID = 1L;
	JPanel panelcentro = new JPanel(new FlowLayout());
	String[] colNames = {"id", "DNI","nombre","apellidos","telefono","email","amaxofobia","activo"};
	JTable jt;
	private boolean init=false, init2=false;
	public void mostrarUno(TAlumno a) {
		
		String[][]datos = {{Integer.toString(a.getId()),a.getDNI(),
			a.getNombre(),a.getApellidos(),Integer.toString(a.getTelefono()),a.getEmail(), Boolean.toString(a.getAmaxofobia()), Boolean.toString(a.getActivo())}};
		DefaultTableModel tmodel = new DefaultTableModel(datos,colNames) {
			private static final long serialVersionUID = -3918467539897813165L;

			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		jt.setModel(tmodel);
		if(init2) {
			return;
		}
		init2=true;
						
		JPanel p= new JPanel(new BorderLayout());
		p.add(jt,BorderLayout.CENTER);
		p.add(jt.getTableHeader(), BorderLayout.NORTH);
		Border _defaultBorder = BorderFactory.createLineBorder(Color.black, 1);
		p.setBorder(BorderFactory.createTitledBorder(_defaultBorder, "Resultado", TitledBorder.LEFT,TitledBorder.TOP));
		panelcentro.add(p);
		
		panelcentro.setSize(600, 200);
		this.setVisible(true);
	}
	public void initGui() {
		if(init) {
			setVisible(true);
			return;
		}
		init=true;
		jt= new JTable();
		this.setLayout(new BorderLayout());
		GUIMaker.getInstance().configurateSubWindow(this,650,200,"Mostrar un alumno ");
		JPanel panelEtiq = new JPanel(new FlowLayout());
		JLabel lbl= new JLabel ("Inserta una ID, por favor: ");
		JTextField idCampo = new JTextField();
		idCampo.setColumns(5);
		panelEtiq.add(lbl);
		panelEtiq.add(idCampo);
		add(panelEtiq, BorderLayout.NORTH);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dato = Integer.parseInt(idCampo.getText());
				Controller.getInstance().accion(eventos.MOSTRAR_UNO_ALUMNO,dato);
				}
			});
		
		panelcentro.add(btnBuscar);
		add(panelcentro,BorderLayout.CENTER);
		this.setVisible(true);
		
	}
}
