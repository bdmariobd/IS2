package presentacion.Vehiculo;

import javax.swing.JFrame;

public abstract class GUIVehiculo extends JFrame {
	private static final long serialVersionUID = 1L;
	private static GUIVehiculo instancia;
	public abstract void update(int event,Object res);
	public abstract void initGui();
	public static GUIVehiculo getInstance() {
		if(instancia==null)	instancia=new GUIVehiculoImp();
		return instancia;
	}

}
