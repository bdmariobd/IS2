package tNegocio.Profesor;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestMethodOrder;
import negocio.FactoriaSA;
import negocio.Profesor.TProfesor;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import tAux.DBUtil;

@TestMethodOrder(OrderAnnotation.class)
public class tProfesorNeg {

	@BeforeAll
	public static void initSetUp() {
		//Vaciamos la tabla correspondiente.
		try {
			DBUtil.deleteTable("Sucursal");
			DBUtil.deleteTable("Sesion");
			DBUtil.deleteTable("Profesor");
			DBUtil.addSomething("INSERT INTO `Sucursal` (`id`, `ciudad`, `telefono`, `direccion`, `activo`) VALUES ('1', 'Lorca', '915411555', 'Av del Triangulo Longo',1);");
			//Una activada
			DBUtil.addSomething("INSERT INTO `Sucursal` (`id`, `ciudad`, `telefono`, `direccion`, `activo`) VALUES ('2', 'Teruel', '955555555', 'Av del Triangulo Corto',0);");
			//Una desactivada
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	@Test
	@Order(1)
	void testSACreate() {
		TProfesor a = new TProfesor(0,1,"99998888Z","Rodrigo","El Incel",915439223,"junit@gmail.com",1500,true);
		TProfesor aRepe = new TProfesor(0,1,"99998888Z","Rodrigo","pero mal",915439223,"junit@gmail.com",1500,true);
		TProfesor aMAL = new TProfesor(0,1,"1Z","Eduardo","El Respiraciones",9,"junit@gmail.com",1500,true);
		TProfesor aSINsuc = new TProfesor(0,99,"77776666B","Sucursal que","no existe",915439223,"junit@gmail.com",1500,true);
		TProfesor c = new TProfesor(0,1,"55558888Z","Fran","El Trader",925439223,"junit@gmail.com",1500,false);
		assertEquals(1,FactoriaSA.getInstance().generateSAProfesor().create(a));
		assertEquals(2,FactoriaSA.getInstance().generateSAProfesor().create(c));
		assertEquals(-3,FactoriaSA.getInstance().generateSAProfesor().create(aMAL));
		assertEquals(-2,FactoriaSA.getInstance().generateSAProfesor().create(aRepe));
		assertEquals(-6,FactoriaSA.getInstance().generateSAProfesor().create(aSINsuc));
	}
	@Test
	@Order(2)
	void testSARead() {
		TProfesor leido = FactoriaSA.getInstance().generateSAProfesor().read(1);
		assertEquals(1,leido.getId());
		assertEquals("99998888Z",leido.getDNI());
		assertEquals("Rodrigo",leido.getNombre());
		assertEquals("El Incel",leido.getApellidos());
		assertEquals(915439223,leido.getTelefono());
		assertEquals("junit@gmail.com",leido.getEmail());
		assertEquals(1500,leido.getSueldo());
		assertEquals(true,leido.getActivo());
	}
}
