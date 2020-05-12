package presentacion.Vehiculo;

import java.awt.Component;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import negocio.Vehiculo.TVehiculo;
import presentacion.GUIMaker;


public class GUIMostrarTodos extends JFrame {
	private boolean init=false;
	JTable jt;
	String[] colNames = {"id", "idSucursal","tipo","da�os","activo","matricula"};
	
	
	public String getValueAt(List<TVehiculo> lista, int arg0, int arg1) {
		// TODO Auto-generated method stub
		String s = null;
		switch (arg1) {
		case 0:
			s = Integer.toString(lista.get(arg0).getId());
			break;
		case 1:
			s = Integer.toString(lista.get(arg0).getIdSucursal());
			break;
		case 2:
			s = lista.get(arg0).getTipo();
			break;
		case 3:
			s = lista.get(arg0).getDa�os();
			break;
		case 4:
			s = Boolean.toString(lista.get(arg0).isActivo());
			break;
		case 5:
			s = lista.get(arg0).getMatricula();
			break;
		}
		return s;
	}
	
	public void mostrarVehiculos(List<TVehiculo> lista) {
		if(init) {
			setVisible(true);
			actualizarTabla(lista);
			return;
		}
		init=true;
		GUIMaker.getInstance().configurateSubWindow(this, 1200, 800, "Mostrar todos los vehiculos");
		jt= new JTable();
		actualizarTabla(lista);
		jt.getTableHeader().setReorderingAllowed(false);
		JScrollPane p= new JScrollPane(jt);
		this.pack();
		this.add(p);
		this.setSize(720,450);
		this.setVisible(true);
		
	}
	private void actualizarTabla(List<TVehiculo> lista) {
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

