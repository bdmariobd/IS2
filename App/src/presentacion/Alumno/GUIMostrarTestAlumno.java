package presentacion.Alumno;


import java.awt.BorderLayout;
import java.awt.Component;
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
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import negocio.Alumno.TAlumno;
import negocio.Alumno.TRelleno;
import presentacion.Controller;
import presentacion.GUIMaker;
import presentacion.eventos;


public class GUIMostrarTestAlumno extends JFrame {
	private boolean init=false;
	private boolean init2=false;
	JPanel panelcentro = new JPanel(new FlowLayout());
	JTable jt;
	String[] colNames ={"idAlumno","idTest", "numero de fallos"};
	
	public String getValueAt(List<TRelleno> lista, int arg0, int arg1) {
		// TODO Auto-generated method stub
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
		if(init) {
			setVisible(true);
			return;
		}
		
		init=true;
		jt= new JTable();
		this.setLayout(new BorderLayout());
		GUIMaker.getInstance().configurateSubWindow(this,350,200,"Mostrar test de un alumno ");
		JPanel panelEtiq = new JPanel(new FlowLayout());
		JLabel lbl= new JLabel ("Inserta ID alumano, por favor: ");
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
		
		panelcentro.add(btnBuscar);
		add(panelcentro,BorderLayout.WEST);
		this.setVisible(true);
		
	}
	public void mostrarTest(List<TRelleno> lista) {
		
		if(init2) {
			setVisible(true);
			actualizarTabla(lista);
			return;
			
		}
		init2=true;
		GUIMaker.getInstance().configurateSubWindow(this, 230, 260, "Mostrar test de alumno");
		actualizarTabla(lista);
		jt.getTableHeader().setReorderingAllowed(false);
		JScrollPane p= new JScrollPane(jt);
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
			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		jt.setModel(tmodel);
	}
}
