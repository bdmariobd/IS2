package presentacion.Sesion;


import javax.swing.JFrame;

public abstract class GUISesion extends JFrame {
	private static final long serialVersionUID = 1L;
	private static GUISesion instancia;
	public abstract void update(int event,Object res);
	public static GUISesion getInstance() {
		if(instancia==null)	instancia=new GUISesionImp();
		return instancia;
	}
	public abstract void initGui();

}

