package presentacion;

public abstract class Controller {
	private static Controller instancia;
	public static Controller getInstance() {
		if(instancia==null) instancia= new ControllerIMP();
		return instancia;
	}
	
	public abstract void accion(int evento, Object datos);
}
