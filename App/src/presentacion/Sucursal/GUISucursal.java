package presentacion.Sucursal;


import javax.swing.JFrame;

public abstract class GUISucursal extends JFrame {
	
	private static GUISucursal instancia;
	public abstract void update(int event,Object res);
	public static GUISucursal getInstance() {
		if(instancia==null)	instancia=new GUISucursalImp();
		return instancia;
	}

}
