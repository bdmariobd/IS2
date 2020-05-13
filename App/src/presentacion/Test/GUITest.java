package presentacion.Test;


import javax.swing.JFrame;

public abstract class GUITest extends JFrame {
	
	private static GUITest instancia;
	public abstract void initGui();
	public abstract void update(int event,Object res);
	public static GUITest getInstance() {
		if(instancia==null)	instancia=new GUITestImp();
		return instancia;
	}

}
