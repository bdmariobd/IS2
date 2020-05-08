package presentacion.Sesion;


import javax.swing.JFrame;
import presentacion.Sesion.GUISesionImp;

public abstract class GUISesion extends JFrame {
	
	private static GUISesion instancia;
	public abstract void update(int event,Object res);
	public static GUISesion getInstance() {
		if(instancia==null)	instancia=new GUISesionImp();
		return instancia;
	}

}

