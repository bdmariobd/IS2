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
public class tProfesorNegTest {

	
	
	private TProfesor a = new TProfesor(0,1,"99998888Z","Rodrigo","El Incel",915439223,"junit@gmail.com",1500,true);
	private TProfesor aRepe = new TProfesor(0,1,"99998888Z","Rodrigo","pero mal",915439223,"junit@gmail.com",1500,true);
	private TProfesor aMAL = new TProfesor(0,1,"1Z","Eduardo","El Respiraciones",9,"junit@gmail.com",1500,true);
	private TProfesor aSINsuc = new TProfesor(0,99,"77776666B","Sucursal que","no existe",915439223,"junit@gmail.com",1500,true);
	private TProfesor b = new TProfesor(0,1,"55558888Z","Fran","El Trader",925439223,"junit@gmail.com",1500,false);
	TProfesor leido = FactoriaSA.getInstance().generateSAProfesor().read(1);
	TProfesor cambio = new TProfesor(1,1,"99998888Z","Rodrigo","El Incel",915439223,"junit@gmail.com",2200,true);
	TProfesor telfIncorrecto = new TProfesor(1,1,"99998888Z","Rodrigo","El Incel",93,"junit@gmail.com",2200,true);
	private TProfesor sinSucValida = new TProfesor(1,99,"99998888Z","Rodrigo","El Incel",915439223,"junit@gmail.com",2200,true);
	private TProfesor otraIDmismoDNI = new TProfesor(2,99,"99998888Z","Rodrigo","El Incel",915439223,"junit@gmail.com",2200,true);
	@BeforeAll
	public static void initSetUp() {
		//Vaciamos la tabla correspondiente.
		try {
			DBUtil.deleteTable("Sucursal");
			DBUtil.deleteTable("Sesion");
			DBUtil.deleteTable("Profesor");
			DBUtil.deleteTable("Alumno");
			DBUtil.deleteTable("Vehiculos");
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
		
		assertEquals(1,FactoriaSA.getInstance().generateSAProfesor().create(a));
		assertEquals(2,FactoriaSA.getInstance().generateSAProfesor().create(b));
		assertEquals(-3,FactoriaSA.getInstance().generateSAProfesor().create(aMAL));
		assertEquals(-2,FactoriaSA.getInstance().generateSAProfesor().create(aRepe));
		assertEquals(-6,FactoriaSA.getInstance().generateSAProfesor().create(aSINsuc));
	}
	@Test
	@Order(2)
	void testSARead() {
		
		assertEquals(1,leido.getId());
		assertEquals("99998888Z",leido.getDNI());
		assertEquals("Rodrigo",leido.getNombre());
		assertEquals("El Incel",leido.getApellidos());
		assertEquals(915439223,leido.getTelefono());
		assertEquals("junit@gmail.com",leido.getEmail());
		assertEquals(1500,leido.getSueldo());
		assertEquals(true,leido.getActivo());
	}
	@Test
	@Order(3)
	void testSAUpdate() {
		
		// Antiguo sueldo 1500
		// Nuevo sueldo 2200
		assertEquals(1,FactoriaSA.getInstance().generateSAProfesor().update(cambio));
		TProfesor actualizado = FactoriaSA.getInstance().generateSAProfesor().read(1);
		//Correcto update.
		assertEquals(2200,actualizado.getSueldo());
		assertEquals(-3,FactoriaSA.getInstance().generateSAProfesor().update(telfIncorrecto));
		assertEquals(-6,FactoriaSA.getInstance().generateSAProfesor().update(sinSucValida));
		assertEquals(-2,FactoriaSA.getInstance().generateSAProfesor().update(otraIDmismoDNI));
	}
	@Test
	@Order(4)
	void testSADelete() {
		//Vamos a vincular profesor 1 con un vehiculo 1 y con sucursal 1.
		DBUtil.addSomething("INSERT INTO `Vehiculos` (`id`, `idSucursal`, `tipo`, `danos`, `activo`, `matricula`) VALUES ('1', '1', 'Toyota Auris', 'Roce', '1', '7654DRT');");
		DBUtil.addSomething("INSERT INTO `VehiculoProfesor` (`idCoche`, `idProfesor`) VALUES ('1', '1');");
		DBUtil.addSomething("INSERT into Alumno VALUES (1,'12345678Q','Eufalio','Macetero',912465867,'xxGamer@gmail.com',0,1)");
		DBUtil.addSomething("INSERT INTO `Sesion` (`id`, `fecha`, `horaini`, `horafin`, `tipo`, `activo`, `idAlumno`, `idProfesor`) VALUES ('1', '2020-06-22', '9:00', '10:30', 'Permiso B', '1', '1', '1');"); 
		assertEquals(-3,FactoriaSA.getInstance().generateSAProfesor().delete("je")); // Mal id.
		assertEquals(-1,FactoriaSA.getInstance().generateSAProfesor().delete("99")); //Borrar uno que no existe.
		assertEquals(-6,FactoriaSA.getInstance().generateSAProfesor().delete("2")); //Borrar uno desactivado
		assertEquals(-7,FactoriaSA.getInstance().generateSAProfesor().delete("1")); // Sesion pendiente.
		try {
			DBUtil.deleteTable("Sesion");
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Error de concurrencia");
		}
		assertEquals(1,FactoriaSA.getInstance().generateSAProfesor().delete("1")); // sin sesiones pendiente.
		
	}
}
