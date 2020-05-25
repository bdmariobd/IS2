package presentacion.Sesion;

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

import negocio.Sesion.TSesion;
import presentacion.Controller;
import presentacion.GUIMaker;
import presentacion.eventos;
import resources.fechasConverter;

public class GUIMostrarSesion extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel panelcentro = new JPanel(new FlowLayout());
	private String[] colNames = {"id", "fecha","hora inicial","hora final","tipo","id Alumno","id Profesor","activo"};
	private JTable tabla;
	private boolean isInit=false, isInit2=false;
	public void mostrarUno(TSesion a) {
		
		String[][]datos = {{Integer.toString(a.getId()),fechasConverter.fechaToString(a.getFecha())
			,fechasConverter.horaToString(a.getHoraini()),fechasConverter.horaToString(a.getHorafin()),
			a.getTipo(), Integer.toString(a.getIdAlumno()), Integer.toString(a.getIdProfesor()), Boolean.toString(a.isActivo())}};
		DefaultTableModel tmodel = new DefaultTableModel(datos,colNames) {
			private static final long serialVersionUID = 2L;

			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		tabla.setModel(tmodel);
		if(isInit2) {
			return;
		}
		isInit2=true;
						
		JPanel p= new JPanel(new BorderLayout());
		p.add(tabla,BorderLayout.CENTER);
		p.add(tabla.getTableHeader(), BorderLayout.NORTH);
		Border _defaultBorder = BorderFactory.createLineBorder(Color.black, 1);
		p.setBorder(BorderFactory.createTitledBorder(_defaultBorder, "Resultado", TitledBorder.LEFT,TitledBorder.TOP));
		panelcentro.add(p);
		
		panelcentro.setSize(600, 200);
		this.setVisible(true);
	}
	public void initGui() {
		if(isInit) {
			setVisible(true);
			return;
		}
		isInit=true;
		tabla= new JTable();
		this.setLayout(new BorderLayout());
		GUIMaker.getInstance().configurateSubWindow(this,650,200,"Mostrar una sesion ");
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
				Controller.getInstance().accion(eventos.MOSTRAR_UNO_SESION,dato);
				}
			});
		
		panelcentro.add(btnBuscar);
		add(panelcentro,BorderLayout.CENTER);
		this.setVisible(true);
		
	}
}
