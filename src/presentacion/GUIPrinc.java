package presentacion;

import javax.swing.JFrame;

public abstract class GUIPrinc extends JFrame{
	private static GUIPrinc instancia;
	
	public static GUIPrinc getInstance(){
		if(instancia==null) instancia = new GuiPrincIMP();
		return instancia;
	}

	protected abstract void reInit();
}
