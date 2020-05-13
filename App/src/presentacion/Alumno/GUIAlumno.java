package presentacion.Alumno;

import javax.swing.JFrame;


public abstract class GUIAlumno extends JFrame{
	private static final long serialVersionUID = 1L;
	private static GUIAlumno instancia;
	public abstract void initGui();
	public abstract void update(int event,Object res);
	public static GUIAlumno getInstance() {
		if(instancia==null)	instancia=new GUIAlumnoImp();
		return instancia;
	}
}
