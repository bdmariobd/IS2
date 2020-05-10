import static org.junit.Assert.*;

import org.junit.Test;
import negocio.FactoriaSA;
import negocio.Alumno.TAlumno;


public class pruebas {
	//private 
	private static int idp=0;
	//private TAlumno transfer;

	
	
	@Test
	public void test() {
		TAlumno transfer = new TAlumno(0,"50638375Z", "Juanjo", "Maranas" , "915439233", "bocasieljuankerarrobagmail", true, true);
		assertEquals(FactoriaSA.getInstance().generateSAalumno().create(transfer),24);
		
	}
	@Test
	public void test1() { // Comprobar que dos transfer son iguales
		TAlumno t = FactoriaSA.getInstance().generateSAalumno().read(12);
		TAlumno a = new TAlumno(12,"50638375Z", "Juanjo", "Maranas" , "915439233", "bocasieljuankerarrobagmail", true, true);
		assertEquals(t.getId(),a.getId());
		assertEquals(t.getDNI(),a.getDNI());
		assertEquals(t.getEmail(),a.getEmail());
		assertEquals(t.getActivo(),a.getActivo());
		assertEquals(t.getApellidos(),a.getApellidos());
		assertEquals(t.getAmaxofobia(),a.getAmaxofobia());
		assertEquals(t.getNombre(),a.getNombre());
		assertEquals(t.getTelefono(),a.getTelefono());
	}
	@Test
	public void test2() { // Caso ya borrado
		assertEquals(-6,FactoriaSA.getInstance().generateSAalumno().delete("12"));
	}
	@Test
	public void test3() { // Caso sin borrar
		assertEquals(24, FactoriaSA.getInstance().generateSAalumno().delete("24"));
	}
	
}


