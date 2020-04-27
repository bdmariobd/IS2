package presentacion.Alumno;

import javax.swing.JFrame;


public abstract class GUIAlumno extends JFrame{
	private static GUIAlumno instancia;
	public abstract void update(int event,Object res);
	public static GUIAlumno getInstance() {
		if(instancia==null)	instancia=new GUIAlumnoImp();
		return instancia;
	}
}
