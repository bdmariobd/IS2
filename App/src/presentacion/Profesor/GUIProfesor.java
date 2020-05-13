package presentacion.Profesor;

import javax.swing.JFrame;


public abstract class GUIProfesor extends JFrame{
	private static final long serialVersionUID = 1L;
	private static GUIProfesor instancia;
	public abstract void initGui();
	public abstract void update(int event,Object res);
	public static GUIProfesor getInstance() {
		if(instancia==null)	instancia=new GUIProfesorImp();
		return instancia;
	}
}
