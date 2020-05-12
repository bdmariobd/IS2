package presentacion;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class GUIMaker {
private static GUIMaker instancia;
	
	public static GUIMaker getInstance(){
		if(instancia==null) instancia = new GuiMakerIMP();
		return instancia;
	}
	//botones: acciones definidas en srs. Extra: botones para volver a interfaces anteriores. 
	//Text: nombre de interfaz actual. Window:frame que la invoca 
	public abstract JPanel getPanel(String[]botones, String[]extra, String text, JFrame window);
	public abstract void configurateWindow(JFrame window);
	//reactiva la animacion
	public abstract void reInit();
	public abstract JPanel panelAlta(String[] etiq);
	public abstract void configurateSubWindow(JFrame window, int h, int w, String title);
}
