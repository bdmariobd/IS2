package presentacion.Sucursal;


import java.awt.Component;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import negocio.Sucursal.TSucursal;
import presentacion.GUIMaker;


public class GUIMostrarTodasSucursales extends JFrame {
	private boolean init=false;
	JTable jt;
	String[] colNames ={"id", "ciudad","telefono","direccion","activo"};
	
	
	public String getValueAt(List<TSucursal> lista, int arg0, int arg1) {
		// TODO Auto-generated method stub
		String s = null;
		switch (arg1) {
		case 0:
			s = Integer.toString(lista.get(arg0).getId());
			break;
		case 1:
			s = lista.get(arg0).getCiudad();
			break;
		case 2:
			s = Integer.toString(lista.get(arg0).getTelefono());
			break;
		case 3:
			s = lista.get(arg0).getDireccion();
			break;
		case 4:
			s = Boolean.toString(lista.get(arg0).isActivo());
			break;
		}
		return s;
	}
	
	public void mostrarSucursales(List<TSucursal> lista) {
		if(init) {
			setVisible(true);
			actualizarTabla(lista);
			return;
		}
		init=true;
		GUIMaker.getInstance().configurateSubWindow(this, 1200, 800, "Mostrar todos las sucursales");
		jt= new JTable();
		actualizarTabla(lista);
		jt.getTableHeader().setReorderingAllowed(false);
		JScrollPane p= new JScrollPane(jt);
		this.pack();
		this.add(p);
		this.setSize(720,450);
		this.setVisible(true);
		
	}
	private void actualizarTabla(List<TSucursal> lista) {
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
