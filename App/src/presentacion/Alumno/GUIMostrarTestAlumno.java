package presentacion.Alumno;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import negocio.Alumno.TRelleno;
import presentacion.Controller;
import presentacion.GUIMaker;
import presentacion.eventos;


public class GUIMostrarTestAlumno extends JFrame {
	private static final long serialVersionUID = 1L;
	private boolean isInit=false;
	private boolean isInit2=false;
	private JPanel panelCentro = new JPanel(new FlowLayout());
	private JTable tabla;
	private String[] colNames ={"idAlumno","idTest", "numero de fallos"};
	
	public String getValueAt(List<TRelleno> lista, int arg0, int arg1) {
		String s = null;
		switch (arg1) {
		case 0:
			s = Integer.toString(lista.get(arg0).getIdAlumno());
			break;
		case 1:
			s = Integer.toString(lista.get(arg0).getIdTest());
			break;
		case 2:
			s = Integer.toString(lista.get(arg0).getNumFallos());
			break;
		}
		return s;
	
	}
	
	public void initGui() {
		if(isInit) {
			setVisible(true);
			return;
		}
		
		isInit=true;
		tabla= new JTable();
		this.setLayout(new BorderLayout());
		GUIMaker.getInstance().configurateSubWindow(this,350,200,"Mostrar test de un alumno ");
		JPanel panelEtiq = new JPanel(new FlowLayout());
		JLabel lbl= new JLabel ("Inserta ID alumno, por favor: ");
		JTextField idCampo = new JTextField();
		idCampo.setColumns(5);
		panelEtiq.add(lbl);
		panelEtiq.add(idCampo);
		add(panelEtiq, BorderLayout.NORTH);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dato = Integer.parseInt(idCampo.getText());
				Controller.getInstance().accion(eventos.MOSTRAR_TEST_ALUMNO,dato);
				}
			});
		
		panelCentro.add(btnBuscar);
		add(panelCentro,BorderLayout.WEST);
		this.setVisible(true);
		
	}
	public void mostrarTest(List<TRelleno> lista) {
		
		if(isInit2) {
			setVisible(true);
			actualizarTabla(lista);
			return;
			
		}
		isInit2=true;
		GUIMaker.getInstance().configurateSubWindow(this, 230, 260, "Mostrar test de alumno");
		actualizarTabla(lista);
		tabla.getTableHeader().setReorderingAllowed(false);
		JScrollPane p= new JScrollPane(tabla);
		this.pack();
		this.add(p);
		this.setSize(420,250);
		this.setVisible(true);
		
	}
	
	private void actualizarTabla(List<TRelleno> lista) {
		String[][]datos= new String[lista.size()][colNames.length];
		for(int i=0;i<lista.size();++i) 
			for(int j=0;j<colNames.length;++j) 
				datos[i][j]= getValueAt(lista,i,j);
				
		DefaultTableModel tmodel = new DefaultTableModel(datos,colNames) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		tabla.setModel(tmodel);
	}
}
