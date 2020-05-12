package presentacion.Profesor;

import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import negocio.Profesor.TProfesor;
import presentacion.GUIMaker;

public class GUIMostrarTodosProfesores extends JFrame {
	private static final long serialVersionUID = 1L;
	private boolean init = false;
	JTable jt;
	String[] colNames = { "id", "idSucursal", "DNI", "nombre", "apellidos", "telefono", "email", "sueldo", "activo" };

	public String getVprofeeAt(List<TProfesor> lista, int arg0, int arg1) {
		String s = null;
		switch (arg1) {
		case 0:
			s = Integer.toString(lista.get(arg0).getId());
			break;
		case 1:
			s = Integer.toString(lista.get(arg0).getIdSucursal());
			break;
		case 2:
			s = lista.get(arg0).getDNI();
			break;
		case 3:
			s = lista.get(arg0).getNombre();
			break;
		case 4:
			s = lista.get(arg0).getApellidos();
			break;
		case 5:
			s = Integer.toString(lista.get(arg0).getTelefono());
			break;
		case 6:
			s = lista.get(arg0).getEmail();
			break;
		case 7:
			s = Integer.toString(lista.get(arg0).getSueldo());
			break;
		case 8:
			s = Boolean.toString(lista.get(arg0).getActivo());
		}
		return s;
	}

	public void mostrarProfesores(List<TProfesor> lista) {
		if (init) {
			setVisible(true);
			actualizarTabla(lista);
			return;
		}
		init = true;
		GUIMaker.getInstance().configurateSubWindow(this, 1200, 800, "Mostrar todos los profesores");
		jt = new JTable();
		actualizarTabla(lista);
		jt.getTableHeader().setReorderingAllowed(false);
		JScrollPane p = new JScrollPane(jt);
		this.pack();
		this.add(p);
		this.setSize(720, 450);
		this.setVisible(true);
	}

	private void actualizarTabla(List<TProfesor> lista) {
		String[][] datos = new String[lista.size()][colNames.length];
		for (int i = 0; i < lista.size(); ++i)
			for (int j = 0; j < colNames.length; ++j)
				datos[i][j] = getVprofeeAt(lista, i, j);

		DefaultTableModel tmodel = new DefaultTableModel(datos, colNames) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		jt.setModel(tmodel);
	}
}
