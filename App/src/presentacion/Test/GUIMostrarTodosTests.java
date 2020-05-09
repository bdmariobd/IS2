package presentacion.Test;


import java.awt.Component;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import negocio.Test.TTest;
import presentacion.GUIMaker;


public class GUIMostrarTodosTests extends JFrame {
	private boolean init=false;
	JTable jt;
	String[] colNames ={"id", "tipo","numero de preguntas","activo"};
	
	
	public String getValueAt(List<TTest> lista, int arg0, int arg1) {
		// TODO Auto-generated method stub
		String s = null;
		switch (arg1) {
		case 0:
			s = Integer.toString(lista.get(arg0).getId());
			break;
		case 1:
			s = lista.get(arg0).getTipo();
			break;
		case 2:
			s = Integer.toString(lista.get(arg0).getNumpreguntas());
			break;
		case 3:
			s = Boolean.toString(lista.get(arg0).isActivo());
			break;
		}
		return s;
	}
	
	public void mostrarTest(List<TTest> lista) {
		if(init) {
			setVisible(true);
			actualizarTabla(lista);
			return;
		}
		init=true;
		GUIMaker.getInstance().configurateSubWindow(this, 1200, 800, "Mostrar todos los test");
		jt= new JTable();
		actualizarTabla(lista);
		jt.getTableHeader().setReorderingAllowed(false);
		JScrollPane p= new JScrollPane(jt);
		this.pack();
		this.add(p);
		this.setSize(720,450);
		this.setVisible(true);
		
	}
	private void actualizarTabla(List<TTest> lista) {
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
